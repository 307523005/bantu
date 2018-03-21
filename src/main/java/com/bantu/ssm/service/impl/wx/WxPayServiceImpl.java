package com.bantu.ssm.service.impl.wx;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import com.bantu.ssm.entity.wx.Payment;
import com.bantu.ssm.service.wx.WxPayService;
import com.bantu.ssm.util.MessageUtil;
import com.bantu.ssm.util.RoundUtil;
import com.bantu.ssm.util.wx.DefinedChars;
import com.bantu.ssm.util.wx.PayUtil;
import com.bantu.ssm.util.wx.PayUtils;
@Service
public class WxPayServiceImpl implements WxPayService{

	@Override
	public Map pay(String openid, String appId, Integer total_fee,
			HttpServletRequest request) throws Exception {
		Map<String,Object> ret=new HashMap();
		
		Integer userid=1111;
		String body="海外购-订单支付";
		body = new String(body.getBytes("UTF-8"), "UTF-8");
		String appid = DefinedChars.XCXAPPID;
		String mch_id = DefinedChars.MCH_ID;
		String nonce_str = RoundUtil.getUUID32();
		String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String code = PayUtil.createCode(8);
		String out_trade_no = mch_id + today + code;// 商户订单号
		String spbill_create_ip = DefinedChars.CREATE_IP;
		String notify_url = "https://hiwego.admin.pleaz.cn:8080/wxpay/back";
		String trade_type = "JSAPI";// 交易类型
		// 封装支付参数
		Payment payment = new Payment();
		payment.setAppid(appid);
		payment.setMch_id(mch_id);
		payment.setNonce_str(nonce_str);
		String newbody = new String(body.getBytes("UTF-8"), "UTF-8");// 以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
		payment.setBody(newbody);
		payment.setOut_trade_no(out_trade_no);
		payment.setTotal_fee(total_fee);//订单总金额，单位为分
		payment.setSpbill_create_ip(spbill_create_ip);
		payment.setNotify_url(notify_url);
		payment.setTrade_type(trade_type);
		payment.setOpenid(openid);
		System.out.println(">>>封装支付参数为：" + payment);
		// 把请求参数打包成Map
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("appid", payment.getAppid());
		sParaTemp.put("mch_id", payment.getMch_id());
		sParaTemp.put("nonce_str", payment.getNonce_str());
		sParaTemp.put("body", payment.getBody());
		sParaTemp.put("out_trade_no", payment.getOut_trade_no());
		sParaTemp.put("total_fee", payment.getTotal_fee().toString());
		sParaTemp.put("spbill_create_ip", payment.getSpbill_create_ip());
		sParaTemp.put("notify_url", payment.getNotify_url());
		sParaTemp.put("trade_type", payment.getTrade_type());
		sParaTemp.put("openid", payment.getOpenid());
		// 除去Map中的空值和签名参数
		Map<String, String> sPara = PayUtil.paraFilter(sParaTemp);
		String prestr = PayUtil.createLinkString(sPara); // 把Map所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		String key = "&key=" + DefinedChars.API_Key; // 商户支付密钥
		// MD5运算生成签名
		String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
		System.out.println(">>>生成签名" + mysign);
		payment.setSign(mysign);
		// 打包要发送的xml
		String respXml = MessageUtil.messageToXML(payment);
		System.out.println(">>>打包要发送的xml为\n" + respXml);
		// 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
		respXml = respXml.replace("__", "_");
		String url = DefinedChars.PRE_PAY;// 统一下单API接口链接
		String param = respXml;
		String result = PayUtil.httpRequest(url, "POST", param);
		System.out.println("微信返回结果为："+result);
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = new ByteArrayInputStream(result.getBytes());
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(in);
		System.out.println(document.toString());
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		for (Element element : elementList) {
			map.put(element.getName(), element.getText());
		}
		// 返回信息
		String return_code = map.get("return_code");// 返回状态码
		String return_msg = map.get("return_msg");// 返回信息
		System.out.println(">>>微信返回信息return_msg："+return_msg);
		// 请求成功
	//	if (return_code.equals("SUCCESS")) {
			
			String prepay_id = map.get("prepay_id");// 返回的预付单信息
			String nonceStr = RoundUtil.getUUID32();
			Long timeStamp = System.currentTimeMillis() / 1000;
			String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id
					+ "&signType=MD5&timeStamp=" + timeStamp;
			// 再次签名
			String paySign = PayUtil.sign(stringSignTemp, "&key=" + DefinedChars.API_Key, "utf-8").toUpperCase();// 自己的秘钥？
			ret.put("nonceStr", nonceStr);
			ret.put("package", "prepay_id=" + prepay_id);
			ret.put("timeStamp", timeStamp + "");
			ret.put("paySign", paySign);
	//	}
		return ret;
	}

	@Override
	public void back(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		  BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));  
	        String line = null;  
	    	String key = "123";
	        StringBuilder sb = new StringBuilder();  
	        while((line = br.readLine()) != null){  
	            sb.append(line);  
	        }  
	        //sb为微信返回的xml  
	        String notityXml = sb.toString();  
	        String resXml = "";  
	        System.out.println("接收到的报文：" + notityXml);  
	      
	        Map map = PayUtils.doXMLParse(notityXml);  
	          
	        String returnCode = (String) map.get("return_code");  
	        if("SUCCESS".equals(returnCode)){  
	            //验证签名是否正确  
	            if(PayUtils.verify(PayUtils.createLinkString(map), (String)map.get("sign"), key, "GBK")){  
	                /**此处添加自己的业务逻辑代码start**/  
	                  
	                  
	                /**此处添加自己的业务逻辑代码end**/  
	                //通知微信服务器已经支付成功  
	                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
	                + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
	            }  
	        }else{  
	            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
	            + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
	        }  
	        System.out.println(resXml);  
	        System.out.println("微信支付回调数据结束");  
	  
	  
	        BufferedOutputStream out = new BufferedOutputStream(  
	                response.getOutputStream());  
	        out.write(resXml.getBytes());  
	        out.flush();  
	        out.close();  
			
		
	}

}

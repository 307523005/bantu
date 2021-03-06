package com.bantu.ssm.service.impl.wx;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bantu.ssm.service.wx.PaymentService;
import com.bantu.ssm.util.IpUtils;
import com.bantu.ssm.util.RoundUtil;
import com.bantu.ssm.util.wx.PayUtils;
/**
 * 16:00支付接口
 * @author Administrator
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService{
	/** 
     * @Description: 发起微信支付 
     * @param request 
     */  
    public Map wxPay(String openid,String appId ,String total_fee,HttpServletRequest request ){  
    	//充值金额
    		//String total_fee ="100";
      	String  mch_id="1230000109";
   	 String trade_type="JSAPI" ;
   	 String notify_url="https://??/??/weixin/api/wxNotify";//http://www.weixin.qq.com/wxpay/pay.php
    		//md5秘钥
    		String key = "123";
        try{  
            //生成的随机字符串32  
            String nonce_str =RoundUtil.getUUID32();  
            //商品名称  
            String body = "csspmc";  
            //获取客户端的ip地址  
            String spbill_create_ip = IpUtils.getIpAddr(request);  
              //生成商户订单号 
           String out_trade_no = RoundUtil.orderNo28(openid);
            //组装参数，用户生成统一下单接口的签名  
            Map<String, String> packageParams = new HashMap<String, String>();  
            packageParams.put("appId", appId);  
            packageParams.put("mch_id", mch_id); //商户号	 
            packageParams.put("nonce_str", nonce_str);  
            packageParams.put("body", body);  
            packageParams.put("out_trade_no", "20150806125346");//商户订单号  
            packageParams.put("total_fee", "1");//支付金额，这边需要转成字符串类型，否则后面的签名会失败  
            packageParams.put("spbill_create_ip", spbill_create_ip);  
            packageParams.put("notify_url", notify_url);//支付成功后的回调地址  
            packageParams.put("trade_type", trade_type);//支付方式  
            packageParams.put("openid", openid);  
                 System.err.println("appId"+ appId+"mch_id"+mch_id);
                String prestr = PayUtils.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串   
              
                //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口  
                String mysign = PayUtils.sign(prestr, key, "utf-8").toUpperCase();  
              
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去  
            String xml = "<xml>" + "<appId>" + appId + "</appId>"   
                    + "<body><![CDATA[" + body + "]]></body>"   
                    + "<mch_id>" + mch_id + "</mch_id>"   
                    + "<nonce_str>" + nonce_str + "</nonce_str>"   
                    + "<notify_url>" + notify_url + "</notify_url>"   
                    + "<openid>" + openid + "</openid>"   
                    + "<out_trade_no>" + out_trade_no + "</out_trade_no>"   
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"   
                    + "<total_fee>" + total_fee+ "</total_fee>"  
                    + "<trade_type>" +trade_type + "</trade_type>"   
                    + "<sign>" + mysign + "</sign>"  
                    + "</xml>";  
              
            System.out.println("调试模式_统一下单接口 请求XML数据：11111" + xml);  
  
            //调用统一下单接口，并接受返回的结果  
            String result = PayUtils.httpRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", "POST", xml);  
              
            System.out.println("调试模式_统一下单接口 返回XML数据：22222" + result);  
              
            // 将解析结果存储在HashMap中     
            Map map = PayUtils.doXMLParse(result);  
              
            String return_code = (String) map.get("return_code");//返回状态码  
              
            Map<String, Object> response = new HashMap<String, Object>();//返回给小程序端需要的参数  
            if(return_code=="SUCCESS"||return_code.equals(return_code)){     
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息     
                response.put("nonceStr", nonce_str);  
                response.put("package", "prepay_id=" + prepay_id);  
                Long timeStamp = System.currentTimeMillis() / 1000;     
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误  
                //拼接签名需要的参数  
                String stringSignTemp = "appId=" + appId + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;     
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法  
                String paySign = PayUtils.sign(stringSignTemp, key, "utf-8").toUpperCase();  
                  
                response.put("paySign", paySign);  
            }  
              
            response.put("appId", appId);  
              
            return response;  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return null;  
    }  


}

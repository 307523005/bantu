package com.bantu.ssm.service.impl.wx;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bantu.ssm.entity.wx.RefundPay;
import com.bantu.ssm.service.wx.RefundPayService;
import com.bantu.ssm.util.MessageUtil;
import com.bantu.ssm.util.RoundUtil;
import com.bantu.ssm.util.wx.DefinedChars;
import com.bantu.ssm.util.wx.GetSSL;
import com.bantu.ssm.util.wx.PayUtil;
@Service
public class RefundPayServiceImpl implements RefundPayService{
	/**
	 * 微信退款
	 */

	@Override
	public Map refund() throws Exception {
		Integer orderid=12121;
		Integer payOrder_fee=20;
		System.out.println("》》》启动退款接口 传入参数为：[订单id:" + orderid + "]");
	//	Order order = os.findOne(orderid);// 获取需要退款的订单
		// 封装退款参数
		RefundPay refundPay = new RefundPay();
		refundPay.setAppid(DefinedChars.XCXAPPID);//微信分配的小程序ID
		refundPay.setMch_id(DefinedChars.MCH_ID);
		refundPay.setNonce_str(RoundUtil.getUUID32());
		refundPay.setOp_user_id(DefinedChars.MCH_ID);// 操作员默认为商户号
		String mch_id = DefinedChars.MCH_ID;
		String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String code = PayUtil.createCode(8);
		String out_trade_no = mch_id + today + code;// 商户订单号
		refundPay.setOut_trade_no(out_trade_no);// 商户订单号
		refundPay.setOut_refund_no("refund"+mch_id + today + code);// 商户退款号
		refundPay.setRefund_fee(payOrder_fee);// 退款金额
		refundPay.setTotal_fee(payOrder_fee);// 订单金额

		// 把请求参数打包成Map
		Map<String, String> refp = new HashMap<String, String>();
		refp.put("appid", refundPay.getAppid());
		refp.put("mch_id", refundPay.getMch_id());
		refp.put("nonce_str", refundPay.getNonce_str());
		refp.put("op_user_id", refundPay.getOp_user_id());
		refp.put("out_trade_no", refundPay.getOut_trade_no());
		refp.put("out_refund_no", refundPay.getOut_refund_no());
		refp.put("total_fee", refundPay.getTotal_fee().toString());
		refp.put("refund_fee", refundPay.getRefund_fee().toString());
		// 除去Map中的空值和签名参数
		Map<String, String> ref = PayUtil.paraFilter(refp);

		String preStr = PayUtil.createLinkString(ref);
		String key = "&key=" + DefinedChars.API_Key; // 商户支付密钥
		// MD5运算生成签名
		String mysign = PayUtil.sign(preStr, key, "utf-8").toUpperCase();
		System.out.println(">>>生成签名为：" + mysign);
		refundPay.setSign(mysign);

		String respXml = MessageUtil.refundPayToXML(refundPay);
		System.out.println(">>>打包要发送的xml为:\n" + respXml);
		respXml = respXml.replace("__", "_");
		String url = DefinedChars.REFUND;
		String param = respXml;
		SSLConnectionSocketFactory ssl = GetSSL.getSSL();
		String result = PayUtil.sslPost(url, param, ssl);
		System.out.println("微信返回结果为：" + result);
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		InputStream in = new ByteArrayInputStream(result.getBytes());
		// 读取输入流
		SAXReader reader = new SAXReader();
		reader.setEncoding("GB2312");// 这里设置文件编码
		Document document = reader.read(in);

		// System.out.println(document.toString());
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		for (Element element : elementList) {
			map.put(element.getName(), element.getText());
		}
		// 返回信息
		String return_code = map.get("return_code");// 返回状态码
		String return_msg = map.get("return_msg");// 返回信息
		String result_code = map.get("result_code");
		String refund_id = map.get("refund_id");//微信退款单号
		System.out.println(">>>微信返回信息return_msg：" + return_msg);

	/*	if ("SUCCESS".equals(return_code)) {
			if ("SUCCESS".equals(result_code)) {
				RefundPayPo rpp = new RefundPayPo(refundPay);
				System.out.println(">>>退款成功,春村退款信息");
				order.setStatus(-1);// 订单状态改为卖家拒绝
				os.save(order);
				rpp.setRefundId(map.get("refund_id"));
				RefundPayPo res = rps.save(rpp);
				System.out.println(">>>储存结果为" + res);*/
				return map;
				/*} else {
				System.out.println(">>>退款失败，错误信息为：" + map.get("err_code_des"));
				return map.get("err_code_des");
			}
		} else {
			return return_msg;
		}*/

	}

}

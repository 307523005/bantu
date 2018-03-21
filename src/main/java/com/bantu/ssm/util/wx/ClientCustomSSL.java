package com.bantu.ssm.util.wx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.bantu.ssm.util.MD5Util;
import com.bantu.ssm.util.RoundUtil;

/**
 * 发放普通红包 
 * 
 */
public class ClientCustomSSL {
	// 微信支付分配的商户号
	private static String mch_id = "1486753972";// 1486753972

	// 服务号appid 公众号的
	private static String appid = "wx69597c922178c253";
	// 提供方名称
	private static String nickName = "qxw";
	// 发红包者名称 商户 公众号
	private static String sendName = "qxw";
	// 接口调用方IP
	private static String clientIp = "127.0.0.1";
	// 商户密钥
	private static String partnerkey = "01CD44266BD33E20ECE737CB06505075";
	// 加密字符编码，我用的UTF-8
	private static String charset = "UTF-8";
	// *证书文件目录
	private static String cerfile = "D:/p12/cert/apiclient_cert.p12";

	/**
	 * @Title: getOrderNo
	 * @Description: 生成商户订单号
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	private static String getOrderNo() {
		String order = mch_id
				+ new SimpleDateFormat("yyyyMMddss").format(new Date());
		Random r = new Random();
		for (int i = 0; i < 2; i++) {
			order += r.nextInt(9000) + 1000;
		}
		return order;
	}

	public static void main(String[] args) throws Exception {
		// 商户订单号
		String orderNNo = getOrderNo();
		SortedMap<Object, Object> paramMap = new TreeMap<Object, Object>();
		paramMap.put("act_name", "111");// 活动名称
		paramMap.put("client_ip", clientIp);// 接口调用机器IP地址
		paramMap.put("mch_billno", orderNNo);// 商户订单
		paramMap.put("mch_id", mch_id);// 商户号
		paramMap.put("nonce_str", RoundUtil.getUUID32());// 随机字符串
		paramMap.put("re_openid", "oHl4T0Yga5hkBrSalSdJg2gCNC-I");// 用户openid
		paramMap.put("remark", "ceshi");// 备注
		paramMap.put("send_name", sendName);// 发红包者名称,商户名称（微信红包发送者名称,最好用英文）
		paramMap.put("total_amount", "100");// 付款金额
		paramMap.put("total_num", "1");// 红包发送总人数
		paramMap.put("wishing", "111");// 红包祝福语
		paramMap.put("wxappid", appid);// 商户appid
		String sign = createSign("utf-8", paramMap);
		System.out.println("---sign----" + sign);
		paramMap.put("sign", sign);
		String reuqestXml = getRequestXml(paramMap);
		System.out.println("-----reuqestXml---" + reuqestXml);
		// 下载证书后将apiclient_cert.p12放在src目录下面（出于安全考虑，请自行下载自己的证书）
		String apiclient_certLocation = "apiclient_cert.p12";
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		URL url2 = ClientCustomSSL.class.getClassLoader().getResource(
				apiclient_certLocation);
		URI uri = url2.toURI();
		System.out.println(uri + "-----uri---");
		FileInputStream instream = new FileInputStream(new File(uri));// P12文件目录
		/*
		 * KeyStore keyStore = KeyStore.getInstance("PKCS12"); FileInputStream
		 * instream = new FileInputStream(new File( cerfile));// 放退款证书的路径
		 */
		try {
			keyStore.load(instream, mch_id.toCharArray());
		} finally {
			instream.close();
		}

		SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();
		try {

			HttpPost httpPost = new HttpPost(
					"https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");//

			System.out.println("executing request" + httpPost.getRequestLine());
			StringEntity reqEntity = new StringEntity(reuqestXml);
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: "
							+ entity.getContentLength());
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(entity.getContent(), "UTF-8"));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						System.out.println(text);
					}

				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
/**
 * 生成签名
 * @param charSet
 * @param parameters
 * @return
 */
	public static String createSign(String charSet,
			SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + "01CD44266BD33E20ECE737CB06505075");
		String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
		return sign;
	}
/**
 * 生成xml文件
 * @param parameters
 * @return
 */
	public static String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k)
					|| "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
}

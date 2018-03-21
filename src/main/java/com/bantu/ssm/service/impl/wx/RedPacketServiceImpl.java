package com.bantu.ssm.service.impl.wx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.print.DocFlavor.STRING;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bantu.ssm.service.wx.RedPacketService;
import com.bantu.ssm.util.MD5Util;
import com.bantu.ssm.util.RoundUtil;
import com.bantu.ssm.util.wx.ClientCustomSSL;
import com.bantu.ssm.util.wx.DefinedChars;
import com.bantu.ssm.util.wx.PayUtils;
import com.jpay.ext.kit.PaymentKit;
import com.jpay.util.HttpUtils;
import com.jpay.util.IOUtils;

/**
 * @ClassName: WxRedPacketService
 * @Description: 微信红包
 * @author niuhao
 * @date 2018-1-19 上午10:41:43
 */
@Service
@Transactional
public class RedPacketServiceImpl implements RedPacketService {

	private Logger logger = Logger.getLogger(RedPacketServiceImpl.class);

/**
 * 发送红包方法
 */
	public int sendRedPacket(String openId, String money, String orderNo,
			String userId) throws Exception {
		String mch_id = DefinedChars.MCH_ID;
		SortedMap<Object, Object> paramMap = new TreeMap<Object, Object>();
		paramMap.put("act_name", "111");// 活动名称
		paramMap.put("client_ip", DefinedChars.CREATE_IP);// 接口调用机器IP地址
		paramMap.put("mch_billno", orderNo);// 商户订单
		paramMap.put("mch_id", mch_id);// 商户号
		paramMap.put("nonce_str", RoundUtil.getUUID32());// 随机字符串
		paramMap.put("re_openid", "oHl4T0Yga5hkBrSalSdJg2gCNC-I");// 用户openid
		paramMap.put("remark", "ceshi");// 备注
		paramMap.put("send_name", DefinedChars.BODY);// 发红包者名称,商户名称（微信红包发送者名称,最好用英文）
		paramMap.put("total_amount", money);// 红包金额
		paramMap.put("total_num", "1");// 红包发送总人数
		paramMap.put("wishing", "111");// 红包祝福语
		paramMap.put("wxappid", DefinedChars.WXGZAPPID);// 商户appid
		String sign = createSign("utf-8", paramMap);
		System.out.println("---sign----" + sign);
		paramMap.put("sign", sign);
		String reuqestXml = getRequestXml(paramMap);
		System.out.println("-----reuqestXml---" + reuqestXml);
		// 下载证书后将apiclient_cert.p12放在src目录下面（出于安全考虑，请自行下载自己的证书）
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		URL url2 = ClientCustomSSL.class.getClassLoader().getResource(
				DefinedChars.P12cerfile);//
		URI uri = url2.toURI();
		System.out.println(uri + "-----uri---");
		// 微信返回结果
		String jsonStr = null;
		FileInputStream instream = new FileInputStream(new File(uri));// P12文件目录
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
			HttpPost httpPost = new HttpPost(DefinedChars.RED_PACK);// 发放普通红包
			System.out.println("executing request" + httpPost.getRequestLine());
			StringEntity reqEntity = new StringEntity(reuqestXml);
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			try {
				System.out.println("----------------------------------------"
						+ response.getStatusLine() + entity.getContentLength());
				if (entity != null) {
					jsonStr = toStringInfo(response.getEntity(), "UTF-8");
					System.out.println(response.getStatusLine());
				}
				System.out.println("----------jsonStr--------" + jsonStr);
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		try {
			// 红包发送成功，状态判断
			if (jsonStr.indexOf("发放成功") > -1) {
				return 1;
			} else if (jsonStr.indexOf("OPENID_ERROR") > -1) {
				// openid和appid不匹配
				return 2;
			} else {
				// 其它错误
				return 8;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 生成签名
	 * 
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
		sb.append("key=" + DefinedChars.API_Key);
		String sign = MD5Util.MD5Encode(sb.toString(), charSet).toUpperCase();
		return sign;
	}

	/**
	 * 生成xml
	 * 
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

/**
 * 返回对象转换XML
 * @param entity
 * @param defaultCharset
 * @return
 * @throws Exception
 * @throws IOException
 */
	private String toStringInfo(HttpEntity entity, String defaultCharset)
			throws Exception, IOException {
		final InputStream instream = entity.getContent();
		if (instream == null) {
			return null;
		}
		try {
			Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
					"HTTP entity too large to be buffered in memory");
			int i = (int) entity.getContentLength();
			if (i < 0) {
				i = 4096;
			}
			Charset charset = null;

			if (charset == null) {
				charset = Charset.forName(defaultCharset);
			}
			if (charset == null) {
				charset = HTTP.DEF_CONTENT_CHARSET;
			}
			final Reader reader = new InputStreamReader(instream, charset);
			final CharArrayBuffer buffer = new CharArrayBuffer(i);
			final char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			return buffer.toString();
		} finally {
			instream.close();
		}
	}

}
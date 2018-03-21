package com.bantu.ssm.util.wx;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import com.alibaba.fastjson.JSONObject;
import com.bantu.ssm.util.MyX509TrustManager;

/**
 * 获取短连接工具
 * 
 * @author Administrator
 * 
 */
public class ShortConnect {
	public static JSONObject Long2Short(String longurl, String token) {
		JSONObject jsonObject = null;
		String sUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
		try {
			sUrl = sUrl.replace("ACCESS_TOKEN", token);
			String param = "{\"action\":\"long2short\"," + "\"long_url\":\""
					+ longurl + "\"}";
			jsonObject = httpRequest(sUrl, "POST", param);
			System.err.println("jsonObject:" + jsonObject);
			boolean containsValue = jsonObject.containsKey("errcode");
			if (containsValue) {
				String errcode = jsonObject.getString("errcode");
				System.err.println("errcode" + errcode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
			// System.out.println("jsonObject="+jsonObject);
		} catch (ConnectException ce) {
			ce.printStackTrace();
			System.out.println("网络链接失败！");
		} catch (UnknownHostException uhe) {
			uhe.printStackTrace();
			System.out.println("微信API无法访问....！");
			// httpRequest(requestUrl, requestMethod, outputStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	/*
	 * public static void main(String[] args) { String token =
	 * GetAccess_token.getToken(); System.out.println(token); String longurl =
	 * "http://localhost:8080/bantu/html/json.html?key=123"; String shorts =
	 * Long2Short(longurl, token).getString("short_url");
	 * System.out.println(shorts); }
	 */

}

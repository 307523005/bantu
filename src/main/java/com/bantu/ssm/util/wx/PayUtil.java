package com.bantu.ssm.util.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
/**
 * WX-two用
 * @author Administrator
 *
 */
public class PayUtil {
	/**
	 * md5签名字符串
	 * 
	 * @param text需要签名的字符串
	 * @param key
	 *            密钥
	 * @param input_charset编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String input_charset) {
		text = text + key;
		System.out.println("连接商户key:\n"+text);
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}

	/**
	 * 签名字符串
	 * 
	 * @param text需要签名的字符串
	 * @param sign
	 *            签名结果
	 * @param key密钥
	 * @param input_charset
	 *            编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key, String input_charset) {
		text = text + key;
		String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}

	/**
	 * 生成6位或10位随机数 param codeLength(多少位)
	 * 
	 * @return
	 */
	public static String createCode(int codeLength) {
		String code = "";
		for (int i = 0; i < codeLength; i++) {
			code += (int) (Math.random() * 9);
		}
		return code;
	}

	@SuppressWarnings("unused")
	private static boolean isValidChar(char ch) {
		if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
			return true;
		if ((ch >= 0x4e00 && ch <= 0x7fff) || (ch >= 0x8000 && ch <= 0x952f))
			return true;// 简体中文汉字编码
		return false;
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> sArray) {
		Map<String, String> result = new HashMap<String, String>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
					|| key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		//为传入的params排序
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	/**
	 * 
	 * @param requestUrl请求地址
	 * @param requestMethod请求方法
	 * @param outputStr参数
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		// 创建SSLContext
		StringBuffer buffer = null;
		try {
			URL url = new URL(requestUrl);
			/** 此处的urlConnection对象实际上是根据URL的   
			 // 请求协议(此处是http)生成的URLConnection类   
			 // 的子类HttpURLConnection,故此处最好将其转化   
			 // 为HttpURLConnection类型的对象,以便用到   
			 // HttpURLConnection更多的API.如下:   */
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   http正文内，因此需要设为true, 默认情况下是false; 			  
			conn.setDoOutput(true);
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			conn.setDoInput(true);
			// Post 请求不能使用缓存   
			//httpUrlConnection.setUseCaches(false);   
			//连接，从上述url.openConnection()至此的配置必须要在connect之前完成，
			//.connect()打开一个通信链接到这个URL引用的资源,如果还没有建立连接。
			conn.connect();

			// 往服务器端写内容
			if (null != outputStr) {
				// 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，   
				// 所以在开发中不调用上述的connect()也可以)。 
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("GBK"));
				os.close();
			}
			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "GBK");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String sslPost(String url,  String xml, SSLConnectionSocketFactory ssl) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(ssl).build();
		HttpPost post = new HttpPost(url);
		StringEntity se=new StringEntity(xml);
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
		post.setEntity(se);
		HttpResponse response = client.execute(post);
		System.out.println(response.toString());
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return result;
	}
}


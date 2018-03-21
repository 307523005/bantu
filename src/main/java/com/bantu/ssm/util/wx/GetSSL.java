package com.bantu.ssm.util.wx;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;

/**
 * 用于导入安全证书
 * @author Administrator
 *
 */
public class GetSSL {
	/**
	 * 
	 * @param url发送的网址
	 * @throws Exception
	 */
	public static SSLConnectionSocketFactory getSSL() throws Exception{
		String key=DefinedChars.MCH_ID;		
		KeyStore keyStore = KeyStore.getInstance("PKCS12");//创建PKCS12管理工具的实例
		FileInputStream instream = new FileInputStream(new File("apiclient_cert.p12"));//读取证书存放地址
		try {
            keyStore.load(instream, key.toCharArray());//输入密码，默认为商户号
        } finally {
            instream.close();
        }
		SSLContext sslcontext= SSLContexts.custom().loadKeyMaterial(keyStore, key.toCharArray()).build();//
		//SSLConnectionSocketFactory(SSLContext, String[], String[], X509HostnameVerifier) 被弃用
		//使用SSLConnectionSocketFactory(javax.net.ssl.SSLContext, String[], String[], javax.net.ssl.HostnameVerifier)代替
		SSLConnectionSocketFactory sslsf=new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);//设置httpclient的SSLSocketFactory
		return sslsf;
//		//使用上面配置的ssl的httpclient
//		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
//		 try {
//			 	//HttpClient模拟get，post请求并发送请求参数
//	            HttpGet httpget = new HttpGet(url);
////			 	HttpPost httpPost=new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
//	            System.out.println("执行请求:" + httpget.getRequestLine());
//	            //执行请求，获取响应
//	            CloseableHttpResponse response = httpclient.execute(httpget);
//	            try {
//	            	//获取响应的实体内容，就是我们所要抓取得网页内容
//	                HttpEntity entity = response.getEntity();
//
//	                System.out.println("----------------------------------------");
//	                //看请求是否成功，这儿打印的是http状态码
//	                System.out.println(response.getStatusLine());
//	                //将其打印到控制台上面
//	                //方法一：使用EntityUtils
//	                if (entity != null) {
//	                    System.out.println("响应内容长度: " + entity.getContentLength());
//	                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
//	                    String text;
//	                    while ((text = bufferedReader.readLine()) != null) {
//	                        System.out.println(text);
//	                    }
//	                   
//	                }
//	                EntityUtils.consume(entity);
//	              //方法二  :使用inputStream
//	                /* if (entity != null) {
//	                     inputStream = entity.getContent();
//
//	                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//	                     String line = "";
//	                     while ((line = bufferedReader.readLine()) != null) {
//	                         System.out.println(line);
//
//	                     }
//	                 }
//	            } finally {
//	                response.close();
//	            }
//	        } finally {
//	            httpclient.close();
//	        }
	}
}

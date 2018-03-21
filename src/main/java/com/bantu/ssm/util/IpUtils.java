package com.bantu.ssm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



public class IpUtils {
	 /** 
     * IpUtils工具类方法 
     * 获取真实的本机ip地址 
     * @param request 
     * @return 
     */  
    public static String getIpAddr(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if(ip!=null && !"unKnown".equalsIgnoreCase(ip)){  
             //多次反向代理后会有多个ip值，第一个ip才是真实ip  
            int index = ip.indexOf(",");  
            if(index != -1){  
                return ip.substring(0,index);  
            }else{  
                return ip;  
            }  
        }  
        ip = request.getHeader("X-Real-IP");  
        if(ip!=null && !"unKnown".equalsIgnoreCase(ip)){  
           return ip;  
        }  
        return request.getRemoteAddr();  
    }  
    /** 
     * 获取访问用户的客户端IP（适用于公网与局域网）. 
     */  
    public static  String getIpNetwork( HttpServletRequest request)  
            throws Exception {  
        if (request == null) {  
            throw (new Exception("getIpAddr method HttpServletRequest Object is null"));  
        }  
        String ipString = request.getHeader("x-forwarded-for");  
        if (isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
            ipString = request.getHeader("Proxy-Client-IP");  
        }  
        if (isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
            ipString = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
            ipString = request.getRemoteAddr();  
        }  
      
        // 多个路由时，取第一个非unknown的ip  
        final String[] arr = ipString.split(",");  
        for (final String str : arr) {  
            if (!"unknown".equalsIgnoreCase(str)) {  
                ipString = str;  
                break;  
            }  
        }  
      
        return ipString;  
    }  
    /** 
     * @param s 获取本机服务器的公网ip
     * @return 如果<tt>s</tt>为<tt>null</tt>或空白字符串返回<tt>true</tt> 
     */  
    public static boolean isBlank(String s) {  
        return s == null ? true : s.trim().length() == 0;  
    }  
    public static String getMyIP() throws IOException {    
        String url="http://ip.chinaz.com/getip.aspx";  
        InputStream is = new URL(url).openStream();  
        try {  
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
            
          StringBuilder sb = new StringBuilder();  
          int cp;  
          while ((cp = rd.read()) != -1) {  
            sb.append((char) cp);  
          }  
          String jsonText =  sb.toString();;  
          jsonText=jsonText.replaceAll("'", "");  
          jsonText=jsonText.substring(1,jsonText.length()-1);  
          jsonText=jsonText.replaceAll(",", "<br/>");  
          return jsonText;  
        } finally {  
          is.close();  
         // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");  
        }  
    } 

}

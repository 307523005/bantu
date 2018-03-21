package com.bantu.ssm.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求返回结果参数封装
 * 
 * @author Administrator
 * 
 */
public class Result {

	public static final Map<String, String> OK_MAP = new HashMap<String, String>();// OK(200,"请求已成功"),
	public static final Map<String, String> Created_MAP = new HashMap<String, String>();// 
	public static final Map<String, String> Bad_Request = new HashMap<String, String>();// 
	public static final Map<String, String> No_Content = new HashMap<String, String>();// 
	public static final Map<String, String> Reset_Content = new HashMap<String, String>();// 
	public static final Map<String, String> Token_invalid = new HashMap<String, String>();// 
	public static final Map<String, String> Token_Expired = new HashMap<String, String>();// 
	public static final Map<String, String> Internal_Server_Error = new HashMap<String, String>();// 
	public static final Map<String, String> Not_Found = new HashMap<String, String>();// 
	static {
		OK_MAP.put("resCode", "200");
		OK_MAP.put("resMessage", "成功");
		Created_MAP.put("resCode", "201");
		Created_MAP.put("resMessage", "请求已经被实现,而且已经依据请求的需要创建了新的资源");
		No_Content.put("resCode", "204");
		No_Content.put("resMessage", "成功处理了请求,但不需要返回任何实体内容");
		Reset_Content.put("resCode", "205");
		Reset_Content.put("resMessage", "成功处理了请求,并要求请求者重置文档视图");
		Bad_Request.put("resCode", "400");
		Bad_Request.put("resMessage", "包含语法错误,当前请求无法被服务器理解");
		Token_invalid.put("resCode", "401.1");
		Token_invalid.put("resMessage", "token错误");
		Token_Expired.put("resCode", "401.2");
		Token_Expired.put("resMessage", "token过期");
		Internal_Server_Error.put("resCode", "500");
		Internal_Server_Error.put("resMessage", "服务器遇到了未曾预料的状况,无法完成对请求的处理");
		Not_Found.put("resCode", "404");
		Not_Found.put("resMessage", "请求失败,请求所希望得到的资源未被在服务器上发现");

	}
}

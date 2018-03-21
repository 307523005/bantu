package com.bantu.ssm.util;


import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import com.bantu.ssm.util.wx.DefinedChars;

/**
 *className:PageUtil.java
 *discription:
 *author:钮豪
 *createTime:2018-2-5
 *version:1.0.0
 */
public class PageUtil {

	private List list;
	private int count;
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public PageUtil(List list, int count) {
		super();
		this.list = list;
		this.count = count;
	}
	public PageUtil() {
		super();
	}
	
}

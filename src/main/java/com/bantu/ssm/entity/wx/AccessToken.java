package com.bantu.ssm.entity.wx;

public class AccessToken {
	// 获取到的凭证
	private String access_token;
	// 凭证有效时间，单位：秒
	private int expires_in;
	// 获取到的凭证
	private String GZHaccess_token;
	// 凭证有效时间，单位：秒
	private int GZHexpires_in;
	// 获取到的凭证
	private String XCXaccess_token;
	// 凭证有效时间，单位：秒
	private int XCXexpires_in;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public String getGZHaccess_token() {
		return GZHaccess_token;
	}
	public void setGZHaccess_token(String gZHaccess_token) {
		GZHaccess_token = gZHaccess_token;
	}
	public int getGZHexpires_in() {
		return GZHexpires_in;
	}
	public void setGZHexpires_in(int gZHexpires_in) {
		GZHexpires_in = gZHexpires_in;
	}
	public String getXCXaccess_token() {
		return XCXaccess_token;
	}
	public void setXCXaccess_token(String xCXaccess_token) {
		XCXaccess_token = xCXaccess_token;
	}
	public int getXCXexpires_in() {
		return XCXexpires_in;
	}
	public void setXCXexpires_in(int xCXexpires_in) {
		XCXexpires_in = xCXexpires_in;
	}

	

}

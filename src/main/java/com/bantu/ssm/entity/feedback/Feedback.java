package com.bantu.ssm.entity.feedback;
/**
 * 反馈表
 * @author Administrator
 *
 */
public class Feedback {
private Integer feedback_id;//自增id
private Integer feedback_isdelete;
private String feedback_uid;//用户id
private String feedback_text;//反馈内容
private String feedback_addtime;//添加时间
private String user_phonebrand ;//手机品牌
private String user_phonemodel ;// 手机型号
private String user_wxversion ;//微信版本
private String user_pixelRatio;//	设备像素比	
private String user_screenWidth;//	屏幕宽度	1.1.0
private String user_screenHeight;//	屏幕高度	1.1.0
private String user_windowWidth;//	可使用窗口宽度	
private String user_windowHeight;//	可使用窗口高度	
private String user_language;//	微信设置的语言	
private String user_system;//	操作系统版本	
private String user_platform;//	客户端平台	
private String user_fontSizeSetting;//	用户字体大小设置。以“我-设置-通用-字体大小”中的设置为准，单位：px	1.5.0
private String user_SDKVersion;//	客户端基础库版本
private String begintime;//	
private String endtime;//	


public String getBegintime() {
	return begintime;
}
public void setBegintime(String begintime) {
	this.begintime = begintime;
}
public String getEndtime() {
	return endtime;
}
public void setEndtime(String endtime) {
	this.endtime = endtime;
}
public String getUser_phonebrand() {
	return user_phonebrand;
}
public void setUser_phonebrand(String user_phonebrand) {
	this.user_phonebrand = user_phonebrand;
}
public String getUser_phonemodel() {
	return user_phonemodel;
}
public void setUser_phonemodel(String user_phonemodel) {
	this.user_phonemodel = user_phonemodel;
}
public String getUser_wxversion() {
	return user_wxversion;
}
public void setUser_wxversion(String user_wxversion) {
	this.user_wxversion = user_wxversion;
}
public String getUser_pixelRatio() {
	return user_pixelRatio;
}
public void setUser_pixelRatio(String user_pixelRatio) {
	this.user_pixelRatio = user_pixelRatio;
}
public String getUser_screenWidth() {
	return user_screenWidth;
}
public void setUser_screenWidth(String user_screenWidth) {
	this.user_screenWidth = user_screenWidth;
}
public String getUser_screenHeight() {
	return user_screenHeight;
}
public void setUser_screenHeight(String user_screenHeight) {
	this.user_screenHeight = user_screenHeight;
}
public String getUser_windowWidth() {
	return user_windowWidth;
}
public void setUser_windowWidth(String user_windowWidth) {
	this.user_windowWidth = user_windowWidth;
}
public String getUser_windowHeight() {
	return user_windowHeight;
}
public void setUser_windowHeight(String user_windowHeight) {
	this.user_windowHeight = user_windowHeight;
}
public String getUser_language() {
	return user_language;
}
public void setUser_language(String user_language) {
	this.user_language = user_language;
}
public String getUser_system() {
	return user_system;
}
public void setUser_system(String user_system) {
	this.user_system = user_system;
}
public String getUser_platform() {
	return user_platform;
}
public void setUser_platform(String user_platform) {
	this.user_platform = user_platform;
}
public String getUser_fontSizeSetting() {
	return user_fontSizeSetting;
}
public void setUser_fontSizeSetting(String user_fontSizeSetting) {
	this.user_fontSizeSetting = user_fontSizeSetting;
}
public String getUser_SDKVersion() {
	return user_SDKVersion;
}
public void setUser_SDKVersion(String user_SDKVersion) {
	this.user_SDKVersion = user_SDKVersion;
}
public Integer getFeedback_isdelete() {
	return feedback_isdelete;
}
public void setFeedback_isdelete(Integer feedback_isdelete) {
	this.feedback_isdelete = feedback_isdelete;
}
public Integer getFeedback_id() {
	return feedback_id;
}
public void setFeedback_id(Integer feedback_id) {
	this.feedback_id = feedback_id;
}
public String getFeedback_uid() {
	return feedback_uid;
}
public void setFeedback_uid(String feedback_uid) {
	this.feedback_uid = feedback_uid;
}
public String getFeedback_text() {
	return feedback_text;
}
public void setFeedback_text(String feedback_text) {
	this.feedback_text = feedback_text;
}
public String getFeedback_addtime() {
	return feedback_addtime;
}
public void setFeedback_addtime(String feedback_addtime) {
	this.feedback_addtime = feedback_addtime;
}

}

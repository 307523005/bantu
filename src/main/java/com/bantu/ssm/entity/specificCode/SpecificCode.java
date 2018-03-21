package com.bantu.ssm.entity.specificCode;
/**
 *名片分享生成动态url表
 * @author Administrator
 *2018-1-3
 */
public class SpecificCode {
private String specificCode_id;//连接id
private String specificCode_userid;//生成的用户id
private String  specificCode_code;//特定码
private String  specificCode_picpath;//二维码路径
private String  specificCode_shorturl;//短连接
private String  specificCode_longurl;//长连接
private String  specificCode_addtime;//生成时间
public String getSpecificCode_id() {
	return specificCode_id;
}
public void setSpecificCode_id(String specificCode_id) {
	this.specificCode_id = specificCode_id;
}
public String getSpecificCode_userid() {
	return specificCode_userid;
}
public void setSpecificCode_userid(String specificCode_userid) {
	this.specificCode_userid = specificCode_userid;
}
public String getSpecificCode_code() {
	return specificCode_code;
}
public void setSpecificCode_code(String specificCode_code) {
	this.specificCode_code = specificCode_code;
}
public String getSpecificCode_picpath() {
	return specificCode_picpath;
}
public void setSpecificCode_picpath(String specificCode_picpath) {
	this.specificCode_picpath = specificCode_picpath;
}
public String getSpecificCode_addtime() {
	return specificCode_addtime;
}
public void setSpecificCode_addtime(String specificCode_addtime) {
	this.specificCode_addtime = specificCode_addtime;
}
public String getSpecificCode_shorturl() {
	return specificCode_shorturl;
}
public void setSpecificCode_shorturl(String specificCode_shorturl) {
	this.specificCode_shorturl = specificCode_shorturl;
}
public String getSpecificCode_longurl() {
	return specificCode_longurl;
}
public void setSpecificCode_longurl(String specificCode_longurl) {
	this.specificCode_longurl = specificCode_longurl;
}

}

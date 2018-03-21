package com.bantu.ssm.entity.wxuser;

public class Wxuser {
	private String appid ;
	private String secret ;
	private String code ;
	private Integer id ;
	private String user_uid ;//用户id
	private String user_nickname ;//微信昵称
	private String user_gender ;//性别 1男  2女
	private String user_province ;//省份
	private String user_city ;// 城市
	private String user_country ;//国家
	private String user_openid ;//微信小程序唯一标识
	private String user_gzhopenid ;//微信公众号唯一标识
	private String user_unionid ;//微信唯一标识
	private String user_deptpic ;//头像路径
	private String user_registeredtime ;//注册时间
	private String user_logindtime ;//最后登录时间
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
	private String user_form_id;//	发送模板消息用
	
	public String getUser_form_id() {
		return user_form_id;
	}


	public void setUser_form_id(String user_form_id) {
		this.user_form_id = user_form_id;
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


	public String getUser_gzhopenid() {
		return user_gzhopenid;
	}


	public void setUser_gzhopenid(String user_gzhopenid) {
		this.user_gzhopenid = user_gzhopenid;
	}


	public String getUser_unionid() {
		return user_unionid;
	}


	public void setUser_unionid(String user_unionid) {
		this.user_unionid = user_unionid;
	}


	public void setAll() {
		
	}
	

	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getSecret() {
		return secret;
	}


	public void setSecret(String secret) {
		this.secret = secret;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getUser_deptpic() {
		return user_deptpic;
	}
	public void setUser_deptpic(String user_deptpic) {
		this.user_deptpic = user_deptpic;
	}
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUser_uid() {
		return user_uid;
	}


	public void setUser_uid(String user_uid) {
		this.user_uid = user_uid;
	}

	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_province() {
		return user_province;
	}
	public void setUser_province(String user_province) {
		this.user_province = user_province;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public String getUser_country() {
		return user_country;
	}
	public void setUser_country(String user_country) {
		this.user_country = user_country;
	}
	public String getUser_openid() {
		return user_openid;
	}
	public void setUser_openid(String user_openid) {
		this.user_openid = user_openid;
	}


	public String getUser_registeredtime() {
		return user_registeredtime;
	}


	public void setUser_registeredtime(String user_registeredtime) {
		this.user_registeredtime = user_registeredtime;
	}


	public String getUser_logindtime() {
		return user_logindtime;
	}


	public void setUser_logindtime(String user_logindtime) {
		this.user_logindtime = user_logindtime;
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
/**
 * 修改用
 * @param user_nickname
 * @param user_gender
 * @param user_province
 * @param user_city
 * @param user_country
 * @param user_openid
 * @param user_deptpic
 * @param user_logindtime
 * @param user_phonebrand
 * @param user_phonemodel
 * @param user_wxversion
 */

	public Wxuser(String user_openid,String user_nickname, String user_gender,
			String user_province, String user_city, String user_country,
			String user_unionid, String user_deptpic, String user_logindtime,
			String user_phonebrand, String user_phonemodel,
			String user_wxversion ,String user_pixelRatio,String user_screenWidth,String user_screenHeight,String user_windowWidth,String user_windowHeight,String user_language
			,String user_system,String user_platform,String user_fontSizeSetting,String user_SDKVersion) {
		super();
		this.user_openid = user_openid;
		this.user_nickname = user_nickname;
		this.user_gender = user_gender;
		this.user_province = user_province;
		this.user_city = user_city;
		this.user_country = user_country;
		this.user_unionid = user_unionid;
		this.user_deptpic = user_deptpic;
		this.user_logindtime = user_logindtime;
		this.user_phonebrand = user_phonebrand;
		this.user_phonemodel = user_phonemodel;
		this.user_wxversion = user_wxversion;
		this.user_pixelRatio = user_pixelRatio;
		this.user_screenWidth = user_screenWidth;
		this.user_screenHeight = user_screenHeight;
		this.user_windowWidth = user_windowWidth;
		this.user_windowHeight = user_windowHeight;
		this.user_language = user_language;
		this.user_system = user_system;
		this.user_platform = user_platform;
		this.user_fontSizeSetting = user_fontSizeSetting;
		this.user_SDKVersion = user_SDKVersion;
	}

/**
 * 小程序注册用构造函数
 * @param user_nickname
 * @param user_gender
 * @param user_province
 * @param user_city
 * @param user_country
 * @param user_openid
 * @param user_deptpic
 * @param user_registeredtime
 * @param user_logindtime
 * @param user_phonebrand
 * @param user_phonemodel
 * @param user_wxversion
 */
	public Wxuser(String user_nickname, String user_gender,
			String user_province, String user_city, String user_country,
			String user_openid, String user_deptpic,
			String user_registeredtime, String user_logindtime,
			String user_phonebrand, String user_phonemodel,
			String user_wxversion,String user_uid,String user_pixelRatio,String user_screenWidth,String user_screenHeight,String user_windowWidth,String user_windowHeight,String user_language
			,String user_system,String user_platform,String user_fontSizeSetting,String user_SDKVersion,String user_unionid) {
		super();
		this.user_nickname = user_nickname;
		this.user_gender = user_gender;
		this.user_province = user_province;
		this.user_city = user_city;
		this.user_country = user_country;
		this.user_openid = user_openid;
		this.user_deptpic = user_deptpic;
		this.user_registeredtime = user_registeredtime;
		this.user_logindtime = user_logindtime;
		this.user_phonebrand = user_phonebrand;
		this.user_phonemodel = user_phonemodel;
		this.user_wxversion = user_wxversion;
		this.user_uid = user_uid;
		this.user_pixelRatio = user_pixelRatio;
		this.user_screenWidth = user_screenWidth;
		this.user_screenHeight = user_screenHeight;
		this.user_windowWidth = user_windowWidth;
		this.user_windowHeight = user_windowHeight;
		this.user_language = user_language;
		this.user_system = user_system;
		this.user_platform = user_platform;
		this.user_fontSizeSetting = user_fontSizeSetting;
		this.user_SDKVersion = user_SDKVersion;
		this.user_unionid = user_unionid;
	}
	

	public Wxuser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

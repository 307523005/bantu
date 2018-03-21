package com.bantu.ssm.entity.mycard;

/**
 * 个人名片表 2018-1-6
 * 
 * @author Administrator
 * 
 */
public class Mycard {
	private Integer mycard_id;// 唯一自增id
	private String mycard_cardid;// 名片id
	private String mycard_uid;// 用户id
	private String mycard_name;// 姓名
	private String mycard_position;// 职位
	private String mycard_mobile;// 手机号
	private String mycard_companyname;// 公司名称
	private String mycard_phone;// 电话
	private String mycard_mail;// 邮箱
	private String mycard_add;// 地址
	private String mycard_website;// 公司网址
	private String mycard_profile;// 公司简介
	private String mycard_scope;// 经营范围
	private Integer mycard_Isdelete;// 是否删除 0否 1 是
	private String mycard_addtime;// 添加时间
	private String mycard_templateid;// 模板类型id
	private String mycard_ismy ;//是否是我的 0 收藏他人的    1 我自己的
	private String mycard_iscompile ;//是否自己编辑的     0 不是（不是自己编辑的就是别人分享的） 1是
	private String mycard_initial ;//首字母
	private String mycard_iseffective ;//首字母

	
	public String getMycard_iseffective() {
		return mycard_iseffective;
	}
	public void setMycard_iseffective(String mycard_iseffective) {
		this.mycard_iseffective = mycard_iseffective;
	}
	public String getMycard_initial() {
		return mycard_initial;
	}
	public void setMycard_initial(String mycard_initial) {
		this.mycard_initial = mycard_initial;
	}
	public String getMycard_iscompile() {
		return mycard_iscompile;
	}
	public void setMycard_iscompile(String mycard_iscompile) {
		this.mycard_iscompile = mycard_iscompile;
	}
	public String getMycard_cardid() {
		return mycard_cardid;
	}
	public void setMycard_cardid(String mycard_cardid) {
		this.mycard_cardid = mycard_cardid;
	}
	public Integer getMycard_id() {
		return mycard_id;
	}
	public void setMycard_id(Integer mycard_id) {
		this.mycard_id = mycard_id;
	}
	public String getMycard_uid() {
		return mycard_uid;
	}
	public void setMycard_uid(String mycard_uid) {
		this.mycard_uid = mycard_uid;
	}
	public String getMycard_name() {
		return mycard_name;
	}
	public void setMycard_name(String mycard_name) {
		this.mycard_name = mycard_name;
	}
	public String getMycard_position() {
		return mycard_position;
	}
	public void setMycard_position(String mycard_position) {
		this.mycard_position = mycard_position;
	}
	public String getMycard_mobile() {
		return mycard_mobile;
	}
	public void setMycard_mobile(String mycard_mobile) {
		this.mycard_mobile = mycard_mobile;
	}
	public String getMycard_companyname() {
		return mycard_companyname;
	}
	public void setMycard_companyname(String mycard_companyname) {
		this.mycard_companyname = mycard_companyname;
	}
	public String getMycard_phone() {
		return mycard_phone;
	}
	public void setMycard_phone(String mycard_phone) {
		this.mycard_phone = mycard_phone;
	}
	public String getMycard_mail() {
		return mycard_mail;
	}
	public void setMycard_mail(String mycard_mail) {
		this.mycard_mail = mycard_mail;
	}
	public String getMycard_add() {
		return mycard_add;
	}
	public void setMycard_add(String mycard_add) {
		this.mycard_add = mycard_add;
	}
	public String getMycard_website() {
		return mycard_website;
	}
	public void setMycard_website(String mycard_website) {
		this.mycard_website = mycard_website;
	}
	public String getMycard_profile() {
		return mycard_profile;
	}
	public void setMycard_profile(String mycard_profile) {
		this.mycard_profile = mycard_profile;
	}
	public String getMycard_scope() {
		return mycard_scope;
	}
	public void setMycard_scope(String mycard_scope) {
		this.mycard_scope = mycard_scope;
	}
	public Integer getMycard_Isdelete() {
		return mycard_Isdelete;
	}
	public void setMycard_Isdelete(Integer mycard_Isdelete) {
		this.mycard_Isdelete = mycard_Isdelete;
	}
	public String getMycard_addtime() {
		return mycard_addtime;
	}
	public void setMycard_addtime(String mycard_addtime) {
		this.mycard_addtime = mycard_addtime;
	}
	public String getMycard_templateid() {
		return mycard_templateid;
	}
	public void setMycard_templateid(String mycard_templateid) {
		this.mycard_templateid = mycard_templateid;
	}
	public String getMycard_ismy() {
		return mycard_ismy;
	}
	public void setMycard_ismy(String mycard_ismy) {
		this.mycard_ismy = mycard_ismy;
	}
	
	public Mycard() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 添加自己的名片
	 * @param mycard_uid
	 * @param mycard_name
	 * @param mycard_position
	 * @param mycard_mobile
	 * @param mycard_companyname
	 * @param mycard_phone
	 * @param mycard_mail
	 * @param mycard_add
	 * @param mycard_website
	 * @param mycard_profile
	 * @param mycard_scope
	 * @param mycard_addtime
	 * @param mycard_templateid
	 * @param mycard_ismy
	 */
	public Mycard(String mycard_cardid, String mycard_uid, String mycard_name,
			String mycard_position, String mycard_mobile,
			String mycard_companyname, String mycard_phone, String mycard_mail,
			String mycard_add, String mycard_website, String mycard_profile,
			String mycard_scope,
			String mycard_addtime, String mycard_templateid,
			String mycard_ismy) {
		super();
		this.mycard_cardid = mycard_cardid;
		this.mycard_uid = mycard_uid;
		this.mycard_name = mycard_name;
		this.mycard_position = mycard_position;
		this.mycard_mobile = mycard_mobile;
		this.mycard_companyname = mycard_companyname;
		this.mycard_phone = mycard_phone;
		this.mycard_mail = mycard_mail;
		this.mycard_add = mycard_add;
		this.mycard_website = mycard_website;
		this.mycard_profile = mycard_profile;
		this.mycard_scope = mycard_scope;
		this.mycard_addtime = mycard_addtime;
		this.mycard_templateid = mycard_templateid;
		this.mycard_ismy = mycard_ismy;
	}

}

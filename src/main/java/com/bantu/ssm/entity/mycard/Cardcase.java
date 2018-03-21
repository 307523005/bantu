package com.bantu.ssm.entity.mycard;

public class Cardcase {
	private Integer cardcase_id  ;// 唯一自增id
	private String cardcase_uid  ;//  用户id
	private String cardcase_cardid  ;// 名片id
	private Integer cardcase_isdelete  ;// 是否删除  0 否   1是
	private Integer cardcase_iscompile;// 是否自己编辑的 0 不是（不是自己编辑的就是别人分享的） 1是
	private String cardcase_ismy;// 是否是我的 0 收藏他人的    1 我自己的
	private String cardcase_addtime;// 添加时间
	
	public String getCardcase_addtime() {
		return cardcase_addtime;
	}
	public void setCardcase_addtime(String cardcase_addtime) {
		this.cardcase_addtime = cardcase_addtime;
	}
	public String getCardcase_ismy() {
		return cardcase_ismy;
	}
	public void setCardcase_ismy(String cardcase_ismy) {
		this.cardcase_ismy = cardcase_ismy;
	}
	public Integer getCardcase_id() {
		return cardcase_id;
	}
	public void setCardcase_id(Integer cardcase_id) {
		this.cardcase_id = cardcase_id;
	}
	public String getCardcase_uid() {
		return cardcase_uid;
	}
	public void setCardcase_uid(String cardcase_uid) {
		this.cardcase_uid = cardcase_uid;
	}
	public String getCardcase_cardid() {
		return cardcase_cardid;
	}
	public void setCardcase_cardid(String cardcase_cardid) {
		this.cardcase_cardid = cardcase_cardid;
	}
	public Integer getCardcase_isdelete() {
		return cardcase_isdelete;
	}
	public void setCardcase_isdelete(Integer cardcase_isdelete) {
		this.cardcase_isdelete = cardcase_isdelete;
	}
	public Integer getCardcase_iscompile() {
		return cardcase_iscompile;
	}
	public void setCardcase_iscompile(Integer cardcase_iscompile) {
		this.cardcase_iscompile = cardcase_iscompile;
	}

}

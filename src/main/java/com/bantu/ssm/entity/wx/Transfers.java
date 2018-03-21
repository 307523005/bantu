package com.bantu.ssm.entity.wx;

public class Transfers {
	private String mch_appid;//公众账号appid
	private String mchid;//微信支付分配的商户号
	private String nonce_str;//随机字符串
	private String sign;//签名
	private String partner_trade_no;//商户订单号
	private String openid;//商户appid下，某用户的openid
	private String check_name;//校验用户姓名选项,NO_CHECK：不校验真实姓名;FORCE_CHECK：强校验真实姓名
	private Integer amount;//金额，单位为分
	private String desc;//企业付款操作说明信息
	private String spbill_create_ip;//调用接口的机器Ip地址
	
	public Transfers() {}

	public Transfers(String mch_appid, String mchid, String nonce_str, String sign, String partner_trade_no,
			String openid, String check_name, Integer amount, String desc, String spbill_create_ip) {
		this.mch_appid = mch_appid;
		this.mchid = mchid;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.partner_trade_no = partner_trade_no;
		this.openid = openid;
		this.check_name = check_name;
		this.amount = amount;
		this.desc = desc;
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getMch_appid() {
		return mch_appid;
	}

	public void setMch_appid(String mch_appid) {
		this.mch_appid = mch_appid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPartner_trade_no() {
		return partner_trade_no;
	}

	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCheck_name() {
		return check_name;
	}

	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	
}

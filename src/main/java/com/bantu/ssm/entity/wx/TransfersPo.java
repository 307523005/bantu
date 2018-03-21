package com.bantu.ssm.entity.wx;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class TransfersPo {
	@Id
	@GeneratedValue
	private Integer id;
	private String mchAppid;
	private String mchid;
	private String nonceStr;
	private String sign;
	private String partnerTradeNo;//商户订单号
	private String openId;
	private String checkName;
	private Integer amount;
	private String wxdesc;//desc与sql数据库冲突，改为wxdesc
	private String spbillCreateIp;
	/**
	 * 交易完成时间
	 */
	private Date expire;
	

	
	@Override
	public String toString() {
		return "TransfersPo [id=" + id + ", mchAppid=" + mchAppid + ", mchid=" + mchid + ", nonceStr=" + nonceStr
				+ ", sign=" + sign + ", partnerTradeNo=" + partnerTradeNo + ", openId=" + openId + ", checkName="
				+ checkName + ", amount=" + amount + ", wxdesc=" + wxdesc + ", spbillCreateIp=" + spbillCreateIp
				+ ", expire=" + expire + "]";
	}

	public TransfersPo(Transfers tf) {
		this.mchAppid=tf.getMch_appid();
		this.mchid=tf.getMchid();
		this.nonceStr=tf.getNonce_str();
		this.sign=tf.getSign();
		this.partnerTradeNo=tf.getPartner_trade_no();
		this.openId=tf.getOpenid();
		this.checkName=tf.getCheck_name();
		this.amount=tf.getAmount();
		this.wxdesc=tf.getDesc();
		this.spbillCreateIp=tf.getSpbill_create_ip();
	}

	public TransfersPo() {}
	public TransfersPo(String mchAppid, String mchid, String nonceStr, String sign, String partnerTradeNo,
			String openId, String checkName, Integer amount, String desc, String spbillCreateIp, Date expire) {
		super();
		this.mchAppid = mchAppid;
		this.mchid = mchid;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.partnerTradeNo = partnerTradeNo;
		this.openId = openId;
		this.checkName = checkName;
		this.amount = amount;
		this.wxdesc = desc;
		this.spbillCreateIp = spbillCreateIp;
		this.expire = expire;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMchAppid() {
		return mchAppid;
	}
	public void setMchAppid(String mchAppid) {
		this.mchAppid = mchAppid;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}
	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getWxdesc() {
		return wxdesc;
	}

	public void setWxdesc(String wxdesc) {
		this.wxdesc = wxdesc;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	 
}

package com.bantu.ssm.entity.wx;


import java.util.Date;

/**
 * 退款表
 * @author Administrator
 *
 */
public class RefundPayPo {
	private Integer id;
	private String appid;//小程序id
	private String mchId;//商户号
	private String deviceInfo;//？设备号 PC网页或公众号内支付请传"WEB"
	private String nonceStr;//随机字符串
	private String sign;//签名
	private String signType;//？签名类型 默认MD5
	private String transactionId;//微信订单号(和商户订单号二选一)
	private String outTradeNo;//商户订单号(本次使用商户订单号)
	/**
	 * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	 */
	private String outRefundNo;//商户退款号
	private Integer totalFee;//订单金额
	private Integer refundFee;//退款金额
//	private String refundFeeType;//货币类型 默认为人民币CNY
	private String opUserId;//操作员 默认为商户号
//	private String refundAccount;//退款资金来源仅 针对老资金流商户使用
//REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
//REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	private String refundId;//微信退款单号
	private Date reTime;//退款时间
	public RefundPayPo() {}
	
	public RefundPayPo(RefundPay rp) {
		this.appid=rp.getAppid();
		this.mchId=rp.getMch_id();
		this.deviceInfo=rp.getDevice_info();
		this.nonceStr=rp.getNonce_str();
		this.sign=rp.getSign();
		this.signType=rp.getSign_type();
		this.transactionId=rp.getTransaction_id();
		this.outTradeNo=rp.getOut_trade_no();
		this.outRefundNo=rp.getOut_refund_no();
		this.totalFee=rp.getTotal_fee();
		this.refundFee=rp.getRefund_fee();
		this.opUserId=rp.getOp_user_id();
		this.reTime=new Date();
	}

	public String toString() {
		return "RefundPay [id=" + id + ", appid=" + appid + ", mchId=" + mchId + ", deviceInfo=" + deviceInfo
				+ ", nonceStr=" + nonceStr + ", sign=" + sign + ", signType=" + signType + ", transactionId="
				+ transactionId + ", outTradeNo=" + outTradeNo + ", outRefundNo=" + outRefundNo + ", totalFee="
				+ totalFee + ", refundFee=" + refundFee + ", opUserId=" + opUserId + ", refundId=" + refundId
				+ ", reTime=" + reTime + "]";
	}



	public Date getReTime() {
		return reTime;
	}
	public void setReTime(Date reTime) {
		this.reTime = reTime;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
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
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	public Integer getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(Integer refundFee) {
		this.refundFee = refundFee;
	}
	public String getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}	
}

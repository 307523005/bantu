package com.bantu.ssm.entity.wx;


public class PaymentPo {
	private Integer id;
	private String appid;//小程序ID.
    private String mchId;//商户号.
    private String deviceInfo;//设备号 PC网页或公众号内支付请传"WEB"
    private String nonceStr;//随机字符串.
    private String sign;//签名
    private String body;//商品描述?.商品简单描述，该字段须严格按照规范传递 如：暮雨集团-订单
    private String detail;//商品详情    
    private String attach;//附加数据？传进来的设定为orderid
    private String outTradeNo;//商户订单号.
    private String transactionId;//微信订单号
    private String feeType;//货币类型
    private String spbillCreateIp;//终端IP.
    private String timeStart;//交易起始时间
    private String timeExpire;//交易结束时间 
    private String goodsTag;//商品标记
    private Integer totalFee;//总金额.
    private String notifyUrl;//通知地址.    
    private String tradeType;//交易类型.  
    private String limitPay;//指定支付方式
    private String openid;//用户标识.
	
	public PaymentPo(Payment payment) {
		this.appid=payment.getAppid();
		this.mchId=payment.getMch_id();
		this.deviceInfo=payment.getDevice_info();
		this.nonceStr=payment.getNonce_str();
		this.sign=payment.getSign();
		this.body=payment.getBody();
		this.detail=payment.getDetail();
		this.attach=payment.getAttach();
		this.outTradeNo=payment.getOut_trade_no();
		this.transactionId=payment.getTransaction_id();
		this.feeType=payment.getFee_type();
		this.spbillCreateIp=payment.getSpbill_create_ip();
		this.timeStart=payment.getTime_start();
		this.timeExpire=payment.getTime_expire();
		this.goodsTag=payment.getGoods_tag();
		this.totalFee=payment.getTotal_fee();
		this.notifyUrl=payment.getNotify_url();
		this.tradeType=payment.getTrade_type();
		this.limitPay=payment.getLimit_pay();
		this.openid=payment.getOpenid();
	}
	@Override
	public String toString() {
		return "PaymentPo [id=" + id + ", appid=" + appid + ", mchId=" + mchId + ", deviceInfo=" + deviceInfo
				+ ", nonceStr=" + nonceStr + ", sign=" + sign + ", body=" + body + ", detail=" + detail + ", attach="
				+ attach + ", outTradeNo=" + outTradeNo + ", transactionId=" + transactionId + ", feeType=" + feeType
				+ ", spbillCreateIp=" + spbillCreateIp + ", timeStart=" + timeStart + ", timeExpire=" + timeExpire
				+ ", goodsTag=" + goodsTag + ", totalFee=" + totalFee + ", notifyUrl=" + notifyUrl + ", tradeType="
				+ tradeType + ", limitPay=" + limitPay + ", openid=" + openid + "]";
	}
	
	public PaymentPo() {}
	
	public PaymentPo(String appid, String mchId, String deviceInfo, String nonceStr, String sign, String body,
			String detail, String attach, String outTradeNo, String transactionId, String feeType,
			String spbillCreateIp, String timeStart, String timeExpire, String goodsTag, Integer totalFee,
			String notifyUrl, String tradeType, String limitPay, String openid) {
		this.appid = appid;
		this.mchId = mchId;
		this.deviceInfo = deviceInfo;
		this.nonceStr = nonceStr;
		this.sign = sign;
		this.body = body;
		this.detail = detail;
		this.attach = attach;
		this.outTradeNo = outTradeNo;
		this.transactionId = transactionId;
		this.feeType = feeType;
		this.spbillCreateIp = spbillCreateIp;
		this.timeStart = timeStart;
		this.timeExpire = timeExpire;
		this.goodsTag = goodsTag;
		this.totalFee = totalFee;
		this.notifyUrl = notifyUrl;
		this.tradeType = tradeType;
		this.limitPay = limitPay;
		this.openid = openid;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}
	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public String getTimeExpire() {
		return timeExpire;
	}
	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}
	public String getGoodsTag() {
		return goodsTag;
	}
	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getLimitPay() {
		return limitPay;
	}
	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
  
}

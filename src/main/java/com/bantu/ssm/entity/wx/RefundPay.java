package com.bantu.ssm.entity.wx;
/**
 * 退款
 * @author Administrator
 *
 */
public class RefundPay {
	private String appid;//小程序id
	private String mch_id;//商户号
	private String device_info;//？设备号 PC网页或公众号内支付请传"WEB"
	private String nonce_str;//随机字符串
	private String sign;//签名
	private String sign_type;//？签名类型 默认MD5
	private String transaction_id;//微信订单号(和商户订单号二选一)
	private String out_trade_no;//商户订单号(本次使用商户订单号)
	/**
	 * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
	 */
	private String out_refund_no;//商户退款号
	private Integer total_fee;//订单金额
	private Integer refund_fee;//退款金额
//	private String refund_fee_type;//货币类型 默认为人民币CNY
	private String op_user_id;//操作员 默认为商户号
//	private String refund-account;//退款资金来源仅 针对老资金流商户使用
//REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
//REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
	
	
	
	public RefundPay() {}

	@Override
	public String toString() {
		return "RefundPay [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info + ", nonce_str="
				+ nonce_str + ", sign=" + sign + ", sign_type=" + sign_type + ", transaction_id=" + transaction_id
				+ ", out_trade_no=" + out_trade_no + ", out_refund_no=" + out_refund_no + ", total_fee=" + total_fee
				+ ", refund_fee=" + refund_fee + ", op_user_id=" + op_user_id + "]";
	}

	public RefundPay(String appid, String mch_id, String device_info, String nonce_str, String sign, String sign_type,
			String transaction_id, String out_trade_no, String out_refund_no, Integer total_fee, Integer refund_fee,
			String op_user_id) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.sign_type = sign_type;
		this.transaction_id = transaction_id;
		this.out_trade_no = out_trade_no;
		this.out_refund_no = out_refund_no;
		this.total_fee = total_fee;
		this.refund_fee = refund_fee;
		this.op_user_id = op_user_id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
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
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}	
}

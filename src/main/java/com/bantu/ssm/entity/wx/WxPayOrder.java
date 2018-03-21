package com.bantu.ssm.entity.wx;



/**
 * 微信支付订单实体类
 * @author Administrator
 *
 */

public class WxPayOrder {

	private String appid;
	private String mch_id;
    /**
     *  终端设备号(门店号或收银设备ID)
     *  PC网页或公众号内支付请传"WEB"
     *  总之值为WEB
     */
    private String device_info;
    /**
     * 随机字符串，不长于32位
     */
	private String nonce_str;
	private String sign;
	private String body;
	/**
     * 附加数据，在查询API和支付通知中原样返回，
     * 该字段主要用于商户携带订单的自定义数据
     */
    private String attach;
	private String out_trade_no;
	private Integer total_fee;
	/**
     * 终端IP（用户）
     */
	private String spbill_create_ip;
	/**
     * 通知地址
     */
	private String  notify_url;
	/**
     * 交♂易类型
     * 小程序取值：JSAPI
     */
	private String trade_type;
	
	/**
     * 用户标识
     */
    private String openid;

    
	@Override
	public String toString() {
		return "WxPayOrder [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info + ", nonce_str="
				+ nonce_str + ", sign=" + sign + ", body=" + body + ", attach=" + attach + ", out_trade_no="
				+ out_trade_no + ", total_fee=" + total_fee + ", spbill_create_ip=" + spbill_create_ip + ", notify_url="
				+ notify_url + ", trade_type=" + trade_type + ", openid=" + openid + "]";
	}

	public WxPayOrder() {
		this.trade_type = "JSAPI";
        this.device_info = "WEB";
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
}

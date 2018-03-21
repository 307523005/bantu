package com.bantu.ssm.entity.wx;
/**
 *用户支付订单记录，支付用，退款用
 * @author Administrator
 *
 */
public class PayOrder {
	private Integer id ;
	private String payOrder_id ;//商户订单号out_trade_no
	private String payOrder_fee ;//total_fee总金额
	private String payOrder_starttime ;//交易起始时间time_start
	private String payOrder_endtime ;//交易结束时间	time_expire
	private String payOrder_openid ;//openid//用户标识
	private String payOrder_transaction;//微信订单号	transaction_id  二选一《商户订单号
}

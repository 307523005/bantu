package com.bantu.ssm.service.wx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface PaymentService {
	/**
	 * 微信小程序支付 一
	 * @param openid
	 * @param appId
	 * @param total_fee
	 * @param request
	 * @return
	 * @throws Exception
	 */
    public Map wxPay(String openid,String appId ,String total_fee,HttpServletRequest request )throws Exception;
}

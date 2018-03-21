package com.bantu.ssm.service.wx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface WxPayService {
	/**
	 * 微信小程序支付接口
	 * @param openid
	 * @param appId
	 * @param total_fee
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map pay(String openid,String appId,Integer total_fee,HttpServletRequest request)
			throws Exception;
	/**
	 * 微信回执通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void back(HttpServletRequest request,HttpServletResponse response)  throws Exception;
}

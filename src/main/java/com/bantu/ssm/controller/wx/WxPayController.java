package com.bantu.ssm.controller.wx;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bantu.ssm.service.wx.WxPayService;
/**
 * 微信支付封装
 * @author Administrator
 *2017-12-18
 */
@Controller
@RequestMapping("/wxpaystwo")
public class WxPayController {
	@Resource
	private WxPayService wxPayService;
	/**
	 * 微信小程序支付接口
	 * @param openid
	 * @param appId
	 * @param total_fee
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/pay")
	public Map pay(String openid,String appId,Integer total_fee,HttpServletRequest request)
			throws Exception {
		 Map wxPay =wxPayService.pay(openid, appId, total_fee, request);
		return wxPay;
	}
	/**
	 * 微信回执通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/back")
	public void back(HttpServletRequest request,HttpServletResponse response)  throws Exception{
		wxPayService.back(request, response);
	}


	
}

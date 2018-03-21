package com.bantu.ssm.controller.wx;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bantu.ssm.service.wx.RefundPayService;
/**
 * 微信退款
 * @author Administrator
 *2017-12-28
 */

@Controller
@RequestMapping("/refundPaytwo")
public class RefundPayController {
	@Resource
	private RefundPayService refundPayService;
	/**
	 * 退款接口
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/refundPay")
	public Map refund() throws Exception {
		 Map  refund = refundPayService.refund();
		return refund;
	}
}

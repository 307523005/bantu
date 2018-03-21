package com.bantu.ssm.controller.wx;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bantu.ssm.service.wx.RedPacketService;
import com.bantu.ssm.util.RoundUtil;

@Controller
@RequestMapping("/RedPacket")
public class RedPacketController {
	@Resource
	private RedPacketService packetService;


	/**
	 * 
	 * @param openId微信openid
	 * @param money红包金额
	 * @param actName活动名称
	 * @param wishing红包祝福语
	 * @param remark备注
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/sendRedPacket")
	public Map sendRedPacket() throws Exception {
		String openId="oHl4T0Yga5hkBrSalSdJg2gCNC-I";
		String money="100";
		String userId="017577af3a934749";
		String orderNo= RoundUtil.orderNo28(userId);
		
		int sendRedPacket = packetService.sendRedPacket(openId, money, orderNo, userId);
		Map  sendRedPacketMap = new HashMap();
		sendRedPacketMap.put("res", sendRedPacket);
		return sendRedPacketMap;
	}
}

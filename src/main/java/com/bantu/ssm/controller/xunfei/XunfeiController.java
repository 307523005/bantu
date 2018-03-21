package com.bantu.ssm.controller.xunfei;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bantu.ssm.service.wx.WxPayService;
import com.bantu.ssm.service.xunfei.XunFeiService;
/**
 * 讯飞语音转换
 * @author Administrator
 *2017-12-18
 */
@Controller
@RequestMapping("/xunfei")
public class XunfeiController {
	@Resource
	private XunFeiService xunFeiService;
	/**
	 * 讯飞语音转换
	 * @param openid
	 * @param appId
	 * @param total_fee
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/xun")//MultipartFile multi
	 public Map<String,String> speechRecognition() throws Exception{
		   Map<String,String> map = xunFeiService.speechRecognition();
		   System.err.println(map+"-------------------++++++++++++++++++++");
		  return map;
		
	}


}

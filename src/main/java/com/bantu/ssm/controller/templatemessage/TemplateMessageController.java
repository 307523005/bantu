package com.bantu.ssm.controller.templatemessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bantu.ssm.entity.feedback.Feedback;
import com.bantu.ssm.entity.templatemessage.TemplateMessage;
import com.bantu.ssm.service.feedback.FeedbackService;
import com.bantu.ssm.service.templatemessage.TemplateMessageService;
import com.bantu.ssm.util.PageUtil;
import com.bantu.ssm.util.ParamsUtil;

/**
 * 小程序通知信息 2018-1-13 钮豪
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/templateMessage")
public class TemplateMessageController {
	@Resource
	private TemplateMessageService templateMessageService;

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getList")
	// method = RequestMethod.POST, produces = "text/html;charset=UTF-8"
	public Map getList(ParamsUtil paramsUtil) throws Exception {
		System.out.println(paramsUtil.getRows() + "-------getRows-----------"
				+ paramsUtil.getPage() + "-------getPage-----------");
		PageUtil pageUtil = templateMessageService.getPage(paramsUtil
				.getParamsMap());
		return paramsUtil.getResultMap(pageUtil.getList(), pageUtil.getCount());
	}

	/**
	 * 添加
	 * 
	 * @param templateMessage
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/add")
	public void add(TemplateMessage templateMessage) throws Exception {
		int list = templateMessageService.add(templateMessage);
	}

	/**
	 * 修改
	 * 
	 * @param templateMessage
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/update")
	public void update(TemplateMessage templateMessage) throws Exception {
		templateMessageService.update(templateMessage);
	}

	/**
	 * 根据id得到信息
	 * 
	 * @param id
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getById")
	public List<TemplateMessage> getById(String id) throws Exception {
		List<TemplateMessage> byId = templateMessageService.getById(id);
		return byId;
	}

}

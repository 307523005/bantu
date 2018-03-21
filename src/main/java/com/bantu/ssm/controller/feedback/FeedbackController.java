package com.bantu.ssm.controller.feedback;

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
import com.bantu.ssm.service.feedback.FeedbackService;
import com.bantu.ssm.util.PageUtil;
import com.bantu.ssm.util.ParamsUtil;
/**
 * 反馈信息
 * 2018-1-13
 * 钮豪
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/Feedback")
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;
	/**
	 * 查询所有反馈
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/getList")
	// method = RequestMethod.POST, produces = "text/html;charset=UTF-8"
	public Map  getList(ParamsUtil  paramsUtil) throws Exception {
		System.out.println(paramsUtil.getRows()+"-------getRows-----------"+paramsUtil.getPage()+"-------getPage-----------");
		PageUtil pageUtil = feedbackService.getPage(paramsUtil.getParamsMap());
		return paramsUtil.getResultMap(pageUtil.getList(), pageUtil.getCount());
	/*	Map map = new HashMap();
		 List list = feedbackService.getList(map);
		 System.err.println(list.get(0)+"----------feedback_uid--------------");*/
	}
	/**
	 * 添加反馈
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/add")
	public Map add(Feedback  feedback) throws Exception {
		Map map = new HashMap();
		
		System.out.println(feedback.getFeedback_text()+"feedback.getFeedback_text()+++++++++-----*");
		 Map list = feedbackService.add(feedback);
		return list;
	}
	/**
	 * 删除反馈
	 * @param feedback_id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Map delete(String  feedback_id) throws Exception {
		System.out.println("------------feedback_id-------"+feedback_id);
		Map map = new HashMap();
		Map list = feedbackService.delete(feedback_id);
		return list;
	}
	/**
	 * 批量删除反馈
	 * @param feedback_id
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/batchDelete")
	public void batchDelete(String[]  feedback_id) throws Exception {
		System.out.println("------------feedback_id-------"+feedback_id);

		String ad ="";
		System.out.println(feedback_id.length);
		for (int i = 0; i < feedback_id.length; i++) {
			ad = feedback_id[i];
			Map list = feedbackService.delete(ad);
		}

		Map map = new HashMap();
		
	}
	
	/**
	 * 后台查询导出反馈表
	 * @param feedback_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportExcelEmployee")
	public void   exportExcelEmployee(HttpServletResponse resp,String user_system,String begintime,String endtime,Integer feedback_isdelete) throws Exception {
		System.out.println("--------------"+feedback_isdelete+user_system);
		feedbackService.exportExcelEmployee(resp, user_system, begintime, endtime,feedback_isdelete);
	}
	
}

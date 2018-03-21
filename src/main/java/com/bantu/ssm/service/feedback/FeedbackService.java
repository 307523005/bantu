package com.bantu.ssm.service.feedback;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.bantu.ssm.entity.feedback.Feedback;
import com.bantu.ssm.util.PageUtil;

public interface FeedbackService {
	/**
	 * 得到所有信息
	 * 
	 * @param map
	 * @return
	 */
	public List<Feedback> getPageList(Map map) throws Exception;

	/**
	 * 导出信息
	 * 
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> getSumNow(Feedback feedback) throws Exception;
	/**
	 *  根据uid时间得到当天总数量 
	 * 
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> getCountNow(Feedback feedback);
	/**
	 * 文件夹获取数量
	 */
	public int getCount(Map map) throws Exception;

	/**
	 * 获取分页参数
	 * 
	 * @param map
	 * @return
	 */
	public PageUtil getPage(Map map) throws Exception;

	/**
	 * 添加新反馈
	 * 
	 * @param feedback
	 * @return
	 */
	public Map add(Feedback feedback) throws Exception;

	/**
	 * 删除
	 * 
	 * @param feedback_id
	 * @return
	 */
	public Map delete(String feedback_id) throws Exception;

	/**
	 * 招聘信息导出
	 * 
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public Map exportExcelEmployee(HttpServletResponse resp,String user_platform,String begintime,String endtime,Integer feedback_isdelete)
			throws Exception;
}

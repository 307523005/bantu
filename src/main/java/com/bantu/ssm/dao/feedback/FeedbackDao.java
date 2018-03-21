package com.bantu.ssm.dao.feedback;

import java.util.List;
import java.util.Map;

import com.bantu.ssm.entity.feedback.Feedback;
import com.bantu.ssm.util.PageUtil;
/**
 * 反馈信息
 * 2018-1-13
 * 钮豪
 * @author Administrator
 *
 */
public interface FeedbackDao {
	/**
	 * 得到所有信息
	 * @param map
	 * @return
	 */
	public List<Feedback> getPageList(Map  map);
	/**
	 * 导出信息
	 * 
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> getSumNow(Feedback feedback);
	/**
	 *  根据uid时间得到当天总数量 
	 * 
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> getCountNow(Feedback feedback);
	/**
	 * 获取数量
	 */
	public int  getCount(Map map);
	/**
	 * 获取分页参数
	 * @param map
	 * @return
	 */
	public PageUtil  getPage(Map map);
	
	/**
	 * 添加新反馈
	 * @param feedback 
	 * @return
	 */
	public int add(Feedback feedback);
	/**
	 * 删除
	 * @param feedback_id
	 * @return
	 */
	public int delete(String feedback_id);

}

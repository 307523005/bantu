package com.bantu.ssm.service.templatemessage;

import java.util.List;
import java.util.Map;

import com.bantu.ssm.entity.feedback.Feedback;
import com.bantu.ssm.entity.templatemessage.TemplateMessage;
import com.bantu.ssm.util.PageUtil;

public interface TemplateMessageService {
	/**
	 * 得到所有信息
	 * @param map
	 * @return
	 */
	public List<TemplateMessage> getPageList(Map  map);
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
	 * 添加信息
	 * @param templateMessage
	 * @return
	 */
	public int add(TemplateMessage templateMessage);
	/**
	 * 修改信息
	 * @return
	 */
	public int update(TemplateMessage templateMessage);
	/**
	 * 根据id获取信息
	 * @param TemplateMessage_id
	 * @return
	 */
	public List<TemplateMessage> getById(String TemplateMessage_id);
}

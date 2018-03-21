package com.bantu.ssm.service.impl.feedback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bantu.ssm.dao.feedback.FeedbackDao;
import com.bantu.ssm.entity.feedback.Feedback;
import com.bantu.ssm.service.feedback.FeedbackService;
import com.bantu.ssm.util.ExcelUitl;
import com.bantu.ssm.util.PageUtil;
import com.bantu.ssm.util.Result;

/**
 * 反馈信息 2018-1-13 钮豪
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	private Map resObjectMap = new HashMap();
	@Autowired
	private FeedbackDao feedbackDao;

	/**
	 * 添加
	 */
	@Override
	public Map add(Feedback feedback) throws Exception{
		resObjectMap.clear();
		String nowTimeOne = new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date());// new Date()为获取当前系统时间
		// 添加前查询用户当天反馈条数
		Feedback feedbackTow = new Feedback();
		feedbackTow.setFeedback_uid(feedback.getFeedback_uid());
		feedbackTow.setFeedback_addtime(nowTimeOne);
		List<Feedback> sumNow = feedbackDao.getCountNow(feedbackTow);
		System.out.println(sumNow.size()+"-------------");
		//条数大于5，不添加
		if (sumNow.size() >=5 ) {
			resObjectMap.put("res", Result.No_Content);
			resObjectMap.put("resObject", "您当天反馈已经超过5次");
		} else {
			feedback.setFeedback_addtime(nowTimeOne);
			int add = feedbackDao.add(feedback);
			if (add > 0) {
				resObjectMap.put("res", Result.OK_MAP);
				resObjectMap.put("resObject", add);
			} else {
				resObjectMap.put("res", Result.Not_Found);
			}

		}

		return resObjectMap;
	}

	/**
	 * 删除
	 */
	@Override
	public Map delete(String feedback_id) throws Exception{
		resObjectMap.clear();
		// TODO Auto-generated method stub
		int add = feedbackDao.delete(feedback_id);
		if (add > 0) {
			resObjectMap.put("res", Result.OK_MAP);
			resObjectMap.put("resObject", add);
		} else {
			resObjectMap.put("res", Result.Not_Found);
		}
		return resObjectMap;
	}
	/**
	 * 招聘信息导出
	 * @param resp
	 * @throws Exception
	 */
	@Override
	public Map  exportExcelEmployee(HttpServletResponse resp,String user_system,String begintime,String endtime,Integer feedback_isdelete) throws Exception{
		resObjectMap.clear();
		Feedback feedback = new Feedback();
		feedback.setBegintime(begintime);
		feedback.setEndtime(endtime);
		feedback.setUser_system(user_system);
		feedback.setFeedback_isdelete(feedback_isdelete);
		List<Feedback> deptList = feedbackDao.getSumNow(feedback);
		LinkedHashMap<String,String> maps = new LinkedHashMap<String,String>();
		maps.put("feedback_id", "自增id");
		maps.put("feedback_uid", "用户id");
		maps.put("feedback_text", "反馈内容");
		maps.put("feedback_addtime", "时间");
		maps.put("user_phonemodel", "手机型号");
		maps.put("user_wxversion", "微信版本");
		maps.put("user_system", "操作系统版本");
		maps.put("feedback_isdelete", "是否处理（1是，0否）");
	
		
		ExcelUitl.listToExcel(deptList, maps, "反馈信息表", resp);
		return resObjectMap;
	}
	/**
	 * @param 页面查询
	 */
	@Override
	public List<Feedback> getPageList(Map map)throws Exception {
		// TODO Auto-generated method stub
		return feedbackDao.getPageList(map);
	}
	/**
	 * 文件夹获取数量
	 */
	@Override
	public int getCount(Map map)throws Exception {
		// TODO Auto-generated method stub
		return feedbackDao.getCount(map);
	}
	/**
	 * 获取分页参数
	 * @param map
	 * @return
	 */
	@Override
	public PageUtil getPage(Map map)throws Exception {
		// TODO Auto-generated method stub
		List<Feedback> pageList = feedbackDao.getPageList(map);
		Object object = map.get("rows");
		Object object2 = map.get("page");
		System.out.println(object2+"-*--*-**-**--*-*"+pageList.size()+object);
		return new PageUtil(pageList,feedbackDao.getCount(map));
	}

	@Override
	public List<Feedback> getSumNow(Feedback feedback) throws Exception {
		// TODO Auto-generated method stub
		return feedbackDao.getSumNow(feedback);
	}

	@Override
	public List<Feedback> getCountNow(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.getCountNow(feedback);
	}
	

}

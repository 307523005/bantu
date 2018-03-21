package com.bantu.ssm.dao.wxuser;

import java.util.List;
import java.util.Map;
import com.bantu.ssm.entity.wxuser.Wxuser;
import com.bantu.ssm.util.PageUtil;

public interface WxuserDao {

	/**
	 * 得到数量
	 * 
	 * @param map
	 * @return
	 */
	public int getCount(Map map);
	/**
	 * 得到所有信息分页
	 * 
	 * @param map
	 * @return
	 */
	public List<Wxuser> getPageList(Map map);
	/**
	 * 获取分页参数
	 * 
	 * @param map
	 * @return
	 */
	public PageUtil getPage(Map map) ;

	/**
	 * 得到所有用户信息
	 * 
	 * @param map
	 * @return
	 */
	public List<Wxuser> getList(Wxuser wxuser);
	

	/**
	 * 添加用户信息
	 * 
	 * @param wxuser
	 * @return
	 */
	public int add(Wxuser wxuser);

	/**
	 * 修改用户信息
	 * 
	 * @param wxuser
	 * @return
	 */
	public int update(Wxuser wxuser);

	/**
	 * 根据openid获取用户信息
	 * 
	 * @param openid
	 * @return
	 */
	public List<Wxuser> getByOpenid(String openid);

	/**
	 * 根据user_uid获取用户信息
	 * 
	 * @param user_uid
	 * @return
	 */
	public List<Wxuser> getByUser_uid(String user_uid);
}

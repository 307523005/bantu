package com.bantu.ssm.service.emp;

import java.util.List;
import java.util.Map;

import com.bantu.ssm.entity.emp.Emp;
import com.bantu.ssm.util.PageUtil;

public interface EmpService {
	/**
	 * 删除
	 * @param emp
	 * @return
	 */
	public int delete(Emp emp);
	

	/**
	 * 添加
	 * @param emp
	 * @return
	 */
	public int add(Emp emp);


	/**
	 * 修改
	 * @param emp
	 * @return
	 */
	public int update(Emp emp);
	/**
	 * 查询
	 * @param emp
	 * @return
	 */
	public List<Emp> getPageList(Map  map);
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
	 * 根据账号密码查询
	 * @param emp
	 * @return
	 */
	public List<Emp> getListByEmp(Emp emp);
}

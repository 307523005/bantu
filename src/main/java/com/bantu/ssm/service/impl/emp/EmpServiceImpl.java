package com.bantu.ssm.service.impl.emp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bantu.ssm.dao.emp.EmpDao;
import com.bantu.ssm.entity.emp.Emp;
import com.bantu.ssm.service.emp.EmpService;
import com.bantu.ssm.util.PageUtil;
@Service
@Transactional
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao empDao;

	@Override
	public int delete(Emp emp) {
		// TODO Auto-generated method stub
		return empDao.delete(emp);
	}

	@Override
	public int add(Emp emp) {
		// TODO Auto-generated method stub
		return empDao.add(emp);
	}

	@Override
	public int update(Emp emp) {
		// TODO Auto-generated method stub
		return empDao.update(emp);
	}

	@Override
	public List<Emp> getPageList(Map map) {
		// TODO Auto-generated method stub
		return empDao.getPageList(map);
	}

	@Override
	public int getCount(Map map) {
		// TODO Auto-generated method stub
		return empDao.getCount(map);
	}

	@Override
	public PageUtil getPage(Map map) {
		List<Emp> pageList = empDao.getPageList(map);
		Object object = map.get("rows");
		Object object2 = map.get("page");
		System.out.println(object2+"-*--*-**-**--*-*"+pageList.size()+object);
		return new PageUtil(pageList,empDao.getCount(map));
	}

	@Override
	public List<Emp> getListByEmp(Emp emp) {
		// TODO Auto-generated method stub
		return empDao.getListByEmp(emp);
	}

}

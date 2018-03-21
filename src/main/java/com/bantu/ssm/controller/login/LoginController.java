package com.bantu.ssm.controller.login;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bantu.ssm.entity.emp.Emp;
import com.bantu.ssm.service.emp.EmpService;

@Controller
@RequestMapping("/LoginController")
public class LoginController {

	@Autowired
	private EmpService empService ;
	@Autowired
	private HttpSession session;
	/*
	@RequestMapping("/login")
	public String login(Emp emp) throws Exception{
		List<Emp> userList = empService.getListByEmp(emp);
		if(userList!=null&&userList.size()>0&&userList.get(0).getEmp_id()==1){
			session.setAttribute("empId", userList.get(0).getEmp_id());
			session.setAttribute("empSession", userList.get(0));
			return "web/main";
		}
		return "login";
	}*/
	@ResponseBody
	@RequestMapping("/login")
	public String login(Emp emp,HttpSession session,Model model) throws Exception{
		//为密码加密
		//AESUtil aesUtil = new AESUtil();
	/*	String encrypt = aesUtil.encrypt(emp.getPassWord());
		emp.setPassWord(encrypt);*/
		List<Emp> mapList =empService.getListByEmp(emp);
		System.out.println(emp.getEmp_password()+emp.getEmp_id()+"+++"+mapList.size());
	if (mapList!=null&&mapList.size()>0) {
		session.setAttribute("userName", mapList.get(0).getEmp_id());
		session.setAttribute("userlist", mapList.get(0));
		session.setAttribute("empSession", mapList.get(0));
		return "1";
		
	}else {
		model.addAttribute("msg", "用户名或者密码错误！！");
		return "2";
	}
	}
	
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session) throws Exception{
		if(session==null){
			return "/zhuye/login";
		}
		session.invalidate();
		return "/zhuye/login";
	}
	/**
	 *获取登录人的信息传到后台主页上方
	 */
	@ResponseBody
	@RequestMapping("/getUserList")
	public Emp  getUserList(HttpSession session) throws Exception{
		//获取session
		 Emp emp= (Emp)session.getAttribute("userlist");
		
		
		return emp;
	}
}

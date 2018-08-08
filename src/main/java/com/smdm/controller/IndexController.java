package com.smdm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smdm.bean.Admin;
import com.smdm.bean.Msg;
import com.smdm.service.AdminService;

@Controller
public class IndexController {
	@Autowired
	AdminService adminService;
	
	/*
	 * 管理员登录验证
	 */
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	@ResponseBody
	public Msg adminLogin(@RequestParam(value="account",defaultValue="")String account, 
			@RequestParam(value="password",required=false)String password,
			HttpSession session) {
		
		if(account.isEmpty())
			return Msg.fail(1,"管理员账号为空！");
		
		//判断账号存在与否
		boolean test1 = adminService.accountIsExist(account);
		if(test1==false)
			return Msg.fail(2,"管理员账号不存在！");
		else if(password.isEmpty())
			return Msg.success(4,"管理员账号存在，但未输入密码！");
			
		//判断账号密码匹配与否
		List<Admin> test2 = adminService.checkAccountPSW(account, password);
		if(test2 != null && test2.size()>0){
			session.setAttribute("adminObj", test2.get(0));
			return Msg.success(5,"登录成功！").add("list", test2);
		}	
		else 
			return Msg.fail(3,"密码错误！");
			
	}
	
	/*
	 * 管理员注册验证
	 */
//	@RequestMapping(value = "/adminRegiste", method = RequestMethod.GET)
//	@ResponseBody
//	public Msg adminRegister(@RequestParam(value="account",defaultValue="")String account, 
//			@RequestParam(value="password",required=false)String password,
//			@RequestParam(value="password2",required=false)String password2) {
//		
//		if(account.isEmpty())
//			return Msg.fail(1,"账号为空！");
//		
//		boolean test1 = adminService.accountIsExist(account);
//		if(test1==true)
//			return Msg.fail(2,"此账号已注册！");
//		else if(password.isEmpty() || password2.isEmpty())
//			return Msg.success(3,"此账号可以注册，但未输入密码！");
//		else if(password.equals(password2)==false)
//			return Msg.fail(4,"两次输入的密码不相同！");
//		else {
//			//进行插入操作
//			int result = adminService.registerAdmin(account,password);
//			if(result <= 0)
//				return Msg.fail(5,"用户账号注册失败！");
//			else
//				return Msg.success(6, "注册成功！");
//		}
//			
//	}
	
	
	/*
	 * 重定向AdminLogin.jsp
	 */
	@RequestMapping(value ="/toAdminLogin")
	public ModelAndView toAdminLogin() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("adminLogin");
		return mView;
	}
	
	/*
	 * index.jsp跳转到AdminLogin.jsp
	 */
	@RequestMapping(value ="/home")
	public ModelAndView toAdmin() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("adminLogin");
		return mView;
	}
	
	/*
	 * 登录成功后跳转到管理员首页adminIndex.jsp
	 */
	@RequestMapping(value ="/adminIndex")
	public ModelAndView toAdminIndex() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("adminIndex");
		return mView;
	}
	
	/*
	 * 退出登录，注销session,跳转到管理员登录页面
	 */
	@RequestMapping(value ="/adminLogout")
	public ModelAndView toAdminLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("adminLogin");
		return mView;
	}
	
	/*
	 * 切换账号，注销session,跳转到管理员登录页面
	 */
	@RequestMapping(value ="/adminSwitch")
	public ModelAndView toAdminSwitch(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("adminLogin");
		return mView;
	}
}

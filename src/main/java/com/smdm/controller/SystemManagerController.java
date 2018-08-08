package com.smdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smdm.bean.Admin;
import com.smdm.bean.Msg;
import com.smdm.bean.User;
import com.smdm.service.AdminService;
import com.smdm.service.UserService;

@RequestMapping ("/systemManagement")
@Controller
public class SystemManagerController {
	@Autowired
	AdminService adminService;
	@Autowired
	UserService userService;
	
	/*
	 * 重定向adminList.jsp
	 */
	@RequestMapping(value ="/adminList")
	public ModelAndView toAdminList() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("adminList");
		return mView;
	}
	
	/*
	 * 重定向userList.jsp
	 */
	@RequestMapping(value ="/userList")
	public ModelAndView toUserList() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("userList");
		return mView;
	}
	
	/*
	 * 获取管理员列表（通过id或者获取所有）
	 */
	@ResponseBody
	@RequestMapping(value ="/getAdminList", method = RequestMethod.GET)
	public Msg getAdminList(@RequestParam(value="id",required=false)Integer id){
		
		if(id!=null) {
			//通过id获取公告
			List<Admin> result = adminService.getAdminById(id);
			if(result.size()<=0)
				return Msg.fail(0, "通过id获取公告失败，公告为null！").add("list", result);
			else 
				return Msg.success(1,"通过id获取公告成功！").add("list", result);
		}
		else {
			//获取所有公告
			List<Admin> result = adminService.getAllAdmin();
			return Msg.success(2,"获取所有公告成功！").add("list", result);
		}
	}
	
	
	/*
	 * 添加管理员
	 */
	@ResponseBody
	@RequestMapping(value ="/addAdmin", method = RequestMethod.POST)
	public Msg addAdmin(@RequestParam(value="account",defaultValue="")String account, 
			@RequestParam(value="name",defaultValue="")String name,
			@RequestParam(value="password",required=false)String password,
			@RequestParam(value="password2",required=false)String password2) {
		
		if(account.isEmpty())
			return Msg.fail(1,"账号为空！");
		
		boolean test1 = adminService.accountIsExist(account);
		if(test1==true)
			return Msg.fail(2,"此账号已添加！");
		else if(password.isEmpty() || password2.isEmpty())
			return Msg.success(3,"此账号可以添加，但未输入密码！");
		else if(password.equals(password2)==false)
			return Msg.fail(4,"两次输入的密码不相同！");
		else {
			//进行插入操作
			int result = adminService.registerAdmin(account,password,name);
			if(result <= 0)
				return Msg.fail(5,"管理员账号添加失败！");
			else
				return Msg.success(6, "添加成功！");
		}
	}
	
	/*
	 * 删除管理员
	 */
	@ResponseBody
	@RequestMapping(value ="/deleteAdmin", method = RequestMethod.POST)
	public Msg deleteAdmin(@RequestParam(value="id")Integer id){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		
		int result = adminService.deleteAdminById(id);
//		if(result<=0)
//			return Msg.fail(1, "删除管理员账号失败！");
//		else
			return Msg.success(2, "删除管理员账号成功！");
	}
	
	/*
	 * 搜索管理员名字
	 */
	@ResponseBody
	@RequestMapping(value ="/searchAdmin", method = RequestMethod.GET)
	public Msg searchAdmin(@RequestParam(value="key",defaultValue="")String key) {
		List<Admin> result = adminService.searchAdmin(key);
		return Msg.success(1, "搜索管理员名字成功！").add("list", result);
	}
	
	
	/*
	 * 获取用户列表（通过id或者获取所有）
	 */
	@ResponseBody
	@RequestMapping(value ="/getUserList", method = RequestMethod.GET)
	public Msg getUserList(@RequestParam(value="id",required=false)Integer id){
		
		if(id!=null) {
			//通过id获取用户
			List<User> result = userService.getUserById(id);
			if(result.size()<=0)
				return Msg.fail(0, "通过id获取用户失败，用户为null！").add("list", result);
			else 
				return Msg.success(1,"通过id获取用户成功！").add("list", result);
		}
		else {
			//获取所有用户
			List<User> result = userService.getAllUser();
			return Msg.success(2,"获取所有用户成功！").add("list", result);
		}
	}
	
	/*
	 * 更新用户（状态）
	 */
	@ResponseBody
	@RequestMapping(value ="/updateUser", method = RequestMethod.POST)
	public Msg updateUser(@RequestParam(value="id")Integer id,
			@RequestParam(value="status")Integer status){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		if(status==null)
			return Msg.fail(1, "用户状态错误错误！");
		
		int result = userService.updateUserByStatus(id, status);
//		if(result<=0)
//			return Msg.fail(2, "修改用户状态失败！");
//		else
			return Msg.success(3, "修改用户状态成功！");
	}
	
	/*
	 * 搜索用户名字
	 */
	@ResponseBody
	@RequestMapping(value ="/searchUser", method = RequestMethod.GET)
	public Msg searchUser(@RequestParam(value="key",defaultValue="")String key) {
		List<User> result = userService.searchUser(key);
		return Msg.success(1, "搜索用户名字成功！").add("list", result);
	}
}


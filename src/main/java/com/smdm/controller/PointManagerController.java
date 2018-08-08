package com.smdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smdm.bean.Announcement;
import com.smdm.bean.Msg;
import com.smdm.bean.User;
import com.smdm.service.UserService;

@RequestMapping ("/PointManagement")
@Controller
public class PointManagerController {
	@Autowired
	UserService userService;

	/*
	 * 重定向pointRanking.jsp
	 */
	@RequestMapping(value ="/pointRanking")
	public ModelAndView toPointRanking() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("pointRanking");
		return mView;
	}
	
	/*
	 * 获取积分列表
	 */
	@ResponseBody
	@RequestMapping(value ="/getPointRanking", method = RequestMethod.GET)
	public Msg getPointRanking(){	
		//获取积分列表
		List<User> result = userService.getAllUserByOrder();
		if(result.size()<=0)
			return Msg.success(0,"获取积分列表成功，但没有数据！").add("list", result);
		else
			return Msg.success(1,"获取积分列表成功！").add("list", result);
	}
	
	/*
	 * 搜索用户名称
	 */
	@ResponseBody
	@RequestMapping(value ="/searchName", method = RequestMethod.GET)
	public Msg searchName(@RequestParam(value="key",defaultValue="")String key) {
		List<User> result = userService.searchUser(key);
		return Msg.success(1, "搜索公告标题成功！").add("list", result);
	}
}

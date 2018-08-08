package com.smdm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smdm.bean.Admin;
import com.smdm.bean.Msg;

@RequestMapping ( "/fruits" )
@Controller
public class TestController {

	
	public TestController() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("controller初始化");
	}
	
	@RequestMapping(value = "/A")
	@ResponseBody
	public Msg AAAAAAAAAAAAAAAA() {
			return Msg.fail(3,"密码错误！");
	}

}

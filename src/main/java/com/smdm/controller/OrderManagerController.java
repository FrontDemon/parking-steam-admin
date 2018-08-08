package com.smdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smdm.bean.Msg;
import com.smdm.bean.Orders;
import com.smdm.service.LotService;
import com.smdm.service.OrdersService;
import com.smdm.service.UserService;

@RequestMapping ("/OrderManagement")
@Controller
public class OrderManagerController {
	@Autowired
	OrdersService ordersService;
	@Autowired
	UserService userService;
	@Autowired
	LotService lotService;

	/*
	 * 重定向orderList.jsp
	 */
	@RequestMapping(value ="/orderList")
	public ModelAndView toOrderList() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("orderList");
		return mView;
	}
	
	/*
	 * 获取订单列表（通过id或者获取所有）
	 */
	@ResponseBody
	@RequestMapping(value ="/getOrder", method = RequestMethod.GET)
	public Msg getOrder(@RequestParam(value="id",required=false)Integer id){
		
		if(id!=null) {
			//通过id获取公告
			Orders result = ordersService.getOrderById(id);
			if(result==null)
				return Msg.fail(0, "通过id获取订单失败，订单为null！").add("list", result);
			else 
				return Msg.success(1,"通过id获取订单成功！").add("list", result);
		}
		else {
			//获取所有公告
			List<Orders> result = ordersService.getAllOrders();
			return Msg.success(2,"获取所有订单成功！").add("list", result);
		}
	}
	
	/*
	 * 删除订单
	 */
	@ResponseBody
	@RequestMapping(value ="/deleteOrder", method = RequestMethod.POST)
	public Msg deleteOrder(@RequestParam(value="id")Integer id){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		
		int result = ordersService.deleteOrderById(id);
//		if(result<=0)
//			return Msg.fail(1, "删除订单失败！");
//		else
			return Msg.success(2, "删除订单成功！");
	}
	
	/*
	 * 搜索订单***************************************************************
	 */
	@ResponseBody
	@RequestMapping(value ="/searchOrder", method = RequestMethod.GET)
	public Msg searchOrder(@RequestParam(value="key",defaultValue="")String key) {
		List<Orders> result = ordersService.searchOrders(key);
		return Msg.success(1, "搜索公告标题成功！").add("list", result);
	}
}

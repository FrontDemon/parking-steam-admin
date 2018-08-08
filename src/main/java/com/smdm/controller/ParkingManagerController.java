package com.smdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smdm.bean.Lot;
import com.smdm.bean.Msg;
import com.smdm.service.LotService;

@RequestMapping ("/parkingManagement")
@Controller
public class ParkingManagerController {
	@Autowired
	LotService LotService;

	/*
	 * 重定向parkingList.jsp
	 */
	@RequestMapping(value ="/parkingList")
	public ModelAndView toParkingList() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("parkingList");
		return mView;
	}
	
	/*
	 * 获取车位列表（通过id或者获取所有）
	 */
	@ResponseBody
	@RequestMapping(value ="/getParking", method = RequestMethod.GET)
	public Msg getParking(@RequestParam(value="id",required=false)Integer id){
		
		if(id!=null) {
			//通过id获取车位
			Lot result = LotService.getParkingtById(id);
			if(result==null)
				return Msg.fail(0, "通过id获取车位失败，车位为null！").add("list", result);
			else 
				return Msg.success(1,"通过id获取车位成功！").add("list", result);
		}
		else {
			//获取所有车位
			List<Lot> result = LotService.getAllParking();
			return Msg.success(2,"获取所有车位成功！").add("list", result);
		}
	}
	
	/*
	 * 添加车位
	 */
	@ResponseBody
	@RequestMapping(value ="/addParking", method = RequestMethod.POST)
	public Msg addParking(@RequestParam(value="number")String number,
			@RequestParam(value="price")Double price,
			@RequestParam(value="address")String address) {
			
		if(number==null)
			return Msg.fail(0, "车位编号为空，添加失败！");
		if(price==null)
			return Msg.fail(1, "车位价格为空，添加失败！");
		if(price<0)
			return Msg.fail(2, "车位价格不能为负数，添加失败！");
		if(address==null)
			return Msg.fail(3, "车位地址为空，添加失败！");
		
		int result = LotService.insertParking(number, price, address);		
//		if(result>0)
			return Msg.success(4, "添加车位成功！");
//		else
//			return Msg.fail(5, "添加车位失败！");
	}
	
	/*
	 * 更新车位
	 */
	@ResponseBody
	@RequestMapping(value ="/updateParking", method = RequestMethod.POST)
	public Msg updateParking(@RequestParam(value="id")Integer id,
			@RequestParam(value="number")String number,
			@RequestParam(value="price")Double price,
			@RequestParam(value="address")String address){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		
		int result = LotService.updateParking(id, number, price, address);
		if(result<=0)
			return Msg.fail(1, "修改车位失败！");
		else
			return Msg.success(2, "修改车位成功！");
	}
	
	/*
	 * 删除车位
	 */
	@ResponseBody
	@RequestMapping(value ="/deleteParking", method = RequestMethod.POST)
	public Msg deleteParking(@RequestParam(value="id")Integer id){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		
		int result = LotService.deleteParkingById(id);
//		if(result<=0)
//			return Msg.fail(1, "删除车位失败！");
//		else
			return Msg.success(2, "删除车位成功！");
	}
	
	/*
	 * 搜索车位编号
	 */
	@ResponseBody
	@RequestMapping(value ="/searchParking", method = RequestMethod.GET)
	public Msg searchParking(@RequestParam(value="number",defaultValue="")String number) {
		List<Lot> result = LotService.searchParking(number);
		return Msg.success(1, "搜索车位成功！").add("list", result);
	}
	
	/*
	 * 返回添加车位视图park-add.jsp
	 */
	@RequestMapping(value ="/park-add")
	public ModelAndView toParkAdd() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("park-add");
		return mView;
	}
	
	/*
	 * 返回编辑车位视图park-edit.jsp
	 */
	@RequestMapping(value ="/park-edit")
	public ModelAndView toNoticeEdit(@RequestParam(value="id")Integer id) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("id", id);
		mView.setViewName("park-edit");
		return mView;
	}
}

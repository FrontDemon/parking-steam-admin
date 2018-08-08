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
import com.smdm.bean.Announcement;
import com.smdm.bean.Msg;
import com.smdm.service.AdminService;
import com.smdm.service.AnnouncementService;

@RequestMapping ("/infoManagement")
@Controller
public class InfoManagerController {
	@Autowired
	AdminService adminService;
	@Autowired
	AnnouncementService announcementService ;
	
	/*
	 * 重定向announcement.jsp
	 */
	@RequestMapping(value ="/announcement")
	public ModelAndView toAnnouncenment() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("announcement");
		return mView;
	}
	
	/*
	 * 获取公告（通过id或者获取所有）（带有公告发布者的信息，即管理员信息）
	 */
	@ResponseBody
	@RequestMapping(value ="/getAnnouncement", method = RequestMethod.GET)
	public Msg getAnnouncement(@RequestParam(value="id",required=false)Integer id){
		
		if(id!=null) {
			//通过id获取公告
			List<Announcement> result = announcementService.getAnnouncementByIdWithAdmin(id);
			if(result.size()<=0)
				return Msg.fail(0, "通过id获取公告失败，数量为零！").add("list", result);
			else 
				return Msg.success(1,"通过id获取公告成功！").add("list", result);
		}
		else {
			//获取所有公告
			List<Announcement> result = announcementService.getAllAnnouncementWithAdmin();
			return Msg.success(2,"获取所有公告成功！").add("list", result);
		}
	}
	
	/*
	 * 添加公告
	 */
	@ResponseBody
	@RequestMapping(value ="/addAnnouncement", method = RequestMethod.POST)
	public Msg addAnnouncement(@RequestParam(value="account")String account,
			@RequestParam(value="title",defaultValue="空")String title,
			@RequestParam(value="context",defaultValue="空")String context) {
		List<Admin> item = adminService.getAdminByAccount(account);
		if(item.size()<=0)
			return Msg.fail(0, "管理员账号不存在，添加公告失败！");
		
		int result = announcementService.insertAnnouncement(item.get(0).getId(), title, context);	
//		System.out.println(result);
//		if(result>0)
			return Msg.success(1, "添加公告成功！");
//		else
//			return Msg.fail(2, "添加公告失败！");
	}
	
	/*
	 * 更新公告
	 */
	@ResponseBody
	@RequestMapping(value ="/updateAnnouncement", method = RequestMethod.POST)
	public Msg updateAnnouncement(@RequestParam(value="id")Integer id,
			@RequestParam(value="title",defaultValue="空")String title,
			@RequestParam(value="context",defaultValue="空")String context,
			@RequestParam(value="account")String account){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		
		List<Admin> item = adminService.getAdminByAccount(account);
		if(item.size()<=0)
			return Msg.fail(1, "管理员账号不存在，修改公告失败！");
		
		int result = announcementService.updateAnnouncement(id, title, context, item.get(0).getId());
//		if(result<=0)
//			return Msg.fail(2, "修改公告失败！");
//		else
			return Msg.success(3, "修改公告成功！");
	}
	
	/*
	 * 删除公告
	 */
	@ResponseBody
	@RequestMapping(value ="/deleteAnnouncement", method = RequestMethod.POST)
	public Msg deleteAnnouncement(@RequestParam(value="id")Integer id){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		
		int result = announcementService.deleteAnnouncementById(id);
//		if(result<=0)
//			return Msg.fail(1, "删除公告失败！");
//		else
			return Msg.success(2, "删除公告成功！");
	}
	
	/*
	 * 搜索公告
	 */
	@RequestMapping(value ="/searchAnnouncement", method = RequestMethod.GET)
	@ResponseBody
	public Msg searchAnnouncement(@RequestParam(value="title",defaultValue="")String title) {
		List<Announcement> result = announcementService.searchAnnouncement(title);
		return Msg.success(1, "搜索公告标题成功！").add("list", result);
	}
	
	/*
	 * 返回添加公告视图notice-add.jsp
	 */
	@RequestMapping(value ="/notice-add")
	public ModelAndView toNoticeAdd() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("notice-add");
		return mView;
	}
	
	/*
	 * 返回编辑公告视图notice-edit.jsp
	 */
	@RequestMapping(value ="/notice-edit")
	public ModelAndView toNoticeEdit(@RequestParam(value="id")Integer id) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("id", id);
		mView.setViewName("notice-edit");
		return mView;
	}
}

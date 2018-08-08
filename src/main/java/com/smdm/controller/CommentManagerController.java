package com.smdm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smdm.bean.Admin;
import com.smdm.bean.Comment;
import com.smdm.bean.Msg;
import com.smdm.service.AdminService;
import com.smdm.service.CommentService;

@RequestMapping ("/CommentManagement")
@Controller
public class CommentManagerController {
	@Autowired
	CommentService commentService;
	@Autowired
	AdminService adminService;

	/*
	 * 重定向commentList.jsp
	 */
	@RequestMapping(value ="/commentList")
	public ModelAndView toCommentList() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("commentList");
		return mView;
	}
	
	/*
	 * 获取留言列表（通过id或者获取所有）
	 */
	@ResponseBody
	@RequestMapping(value ="/getComment", method = RequestMethod.GET)
	public Msg getComment(@RequestParam(value="id",required=false)Integer id){
		
		if(id!=null) {
			//通过id获取留言
			Comment result = commentService.getCommentById(id);
			if(result==null)
				return Msg.fail(0, "通过id获取留言失败，留言为null！").add("list", result);
			else 
				return Msg.success(1,"通过id获取留言成功！").add("list", result);
		}
		else {
			//获取所有留言
			List<Comment> result = commentService.getAllComment();
			return Msg.success(2,"获取所有留言成功！").add("list", result);
		}
	}
	
	/*
	 * 更新留言
	 */
	@ResponseBody
	@RequestMapping(value ="/updateComment", method = RequestMethod.POST)
	public Msg updateComment(@RequestParam(value="id")Integer id,
			@RequestParam(value="replication",defaultValue="空")String replication,
			@RequestParam(value="adminId")Integer adminId){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		if(adminId==null)
			return Msg.fail(1, "adminId错误！");
		
		List<Admin> item = adminService.getAdminById(adminId);
		if(item.size()<=0)
			return Msg.fail(2, "管理员账号不存在，修改留言失败！");
		
		int result = commentService.updateComment(id, replication, new Date(), adminId);
//		if(result<=0)
//			return Msg.fail(3, "修改留言失败！");
//		else
			return Msg.success(4, "修改留言成功！");
	}
	
	/*
	 * 删除留言
	 */
	@ResponseBody
	@RequestMapping(value ="/deleteComment", method = RequestMethod.POST)
	public Msg deleteComment(@RequestParam(value="id")Integer id){
		
		if(id==null)
			return Msg.fail(0, "id错误！");
		
		int result = commentService.deleteCommentById(id);
//		if(result<=0)
//			return Msg.fail(1, "删除留言失败！");
//		else
			return Msg.success(2, "删除留言成功！");
	}
	
	/*
	 * 搜索留言
	 */
	@ResponseBody
	@RequestMapping(value ="/searchComment", method = RequestMethod.GET)
	public Msg searchComment(@RequestParam(value="key",defaultValue="")String key) {
		List<Comment> result = commentService.searchComment(key);
		return Msg.success(1, "搜索留言成功！").add("list", result);
	}
	
	/*
	 * 返回回复留言视图comment-edit.jsp
	 */
	@RequestMapping(value ="/comment-edit")
	public ModelAndView toCommentEdit(@RequestParam(value="id")Integer id) {
		ModelAndView mView = new ModelAndView();
		mView.addObject("id", id);
		mView.setViewName("comment-edit");
		return mView;
	}
}

package com.smdm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdm.bean.Comment;
import com.smdm.bean.CommentExample;
import com.smdm.dao.CommentMapper;

@Service
public class CommentService {
	@Autowired
	CommentMapper commentMapper;

	//获取留言（通过id）
	public Comment getCommentById(Integer id) {
		return commentMapper.selectByPrimaryKeyWithDetails(id);
	}

	//获取所有留言
	public List<Comment> getAllComment() {
		return commentMapper.selectAllWithDetails();
	}

	//更新留言（id、回复内容、回复时间、管理员id）
	public int updateComment(Integer id, String replication, Date date, Integer adminId) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setReplication(replication);
		comment.setReplyTime(date);
		comment.setAdminId(adminId);
		return commentMapper.updateByPrimaryKeySelective(comment);
	}

	//删除留言（通过id）
	public int deleteCommentById(Integer id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	//搜索留言（通过留言内容）
	public List<Comment> searchComment(String key) {
		CommentExample example = new CommentExample();
		example.or().andCommentLike("%"+key+"%");
		return commentMapper.selectByExampleWithDetails(example);
	}
}

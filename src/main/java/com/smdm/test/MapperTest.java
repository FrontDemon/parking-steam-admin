package com.smdm.test;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smdm.bean.Announcement;
import com.smdm.bean.AnnouncementExample;
import com.smdm.bean.Comment;
import com.smdm.bean.AnnouncementExample.Criteria;
import com.smdm.dao.AnnouncementMapper;
import com.smdm.dao.CommentMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	SqlSession sqlSession;
	
	@Test
    public void testCRUD() {
//		AnnouncementMapper anno = sqlSession.getMapper(AnnouncementMapper.class);
//		AnnouncementExample example = new AnnouncementExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andIdEqualTo(2);
//		List<Announcement> result = anno.selectByExampleWithAdmin(example);
//		
//		for(Announcement item : result) {
//			System.out.print(item.getAdmin().getName());
//			System.out.print(item.getId());
//			System.out.println(item.getTitle());
//		}
		CommentMapper aaaext = sqlSession.getMapper(CommentMapper.class);
		Comment comment = new Comment();
		comment.setId(1);
		comment.setReplication("管理员回复！！！");
		comment.setReplyTime(new Date());
		comment.setAdminId(1);
		aaaext.updateByPrimaryKeySelective(comment);
	}
}

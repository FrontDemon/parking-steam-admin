package com.smdm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdm.bean.Announcement;
import com.smdm.bean.AnnouncementExample;
import com.smdm.bean.AnnouncementExample.Criteria;
import com.smdm.dao.AnnouncementMapper;

@Service
public class AnnouncementService {
	@Autowired
	AnnouncementMapper announcementMapper;

	//插入公告（管理员id、标题、内容、时间）
	public int insertAnnouncement(Integer adminId, String title, String context){
		return announcementMapper.insert(new Announcement(null, title, context, new Date(), adminId));
	}

	//获取公告通过id
	public Announcement getAnnouncementById(Integer id) {
		return announcementMapper.selectByPrimaryKey(id);
	}
	
	//获取公告通过id（带管理员信息）
	public List<Announcement> getAnnouncementByIdWithAdmin(Integer id) {
		AnnouncementExample example = new AnnouncementExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		return announcementMapper.selectByExampleWithAdmin(example);
	}
	
	//获取所有公告
	public List<Announcement> getAllAnnouncement() {
		return announcementMapper.selectByExample(null);
	}
	
	//获取所有公告（带管理员信息）
	public List<Announcement> getAllAnnouncementWithAdmin() {
		return announcementMapper.selectByExampleWithAdmin(null);
	}

	//更新公告
	public int updateAnnouncement(Integer id, String title, String context, Integer adminId) {
		Announcement announcement = new Announcement(id, title, context, null, adminId);
		return announcementMapper.updateByPrimaryKeySelective(announcement);	
	}

	//删除公告
	public int deleteAnnouncementById(Integer id) {		
		return announcementMapper.deleteByPrimaryKey(id);
	}

	//搜索公告
	public List<Announcement> searchAnnouncement(String title) {
		
		if(title.isEmpty())
			return announcementMapper.selectByExampleWithAdmin(null);
		
		AnnouncementExample example = new AnnouncementExample();
		Criteria criteria = example.createCriteria();
		String key = "%"+title+"%";
		criteria.andTitleLike(key);
		return announcementMapper.selectByExampleWithAdmin(example);
	}
}

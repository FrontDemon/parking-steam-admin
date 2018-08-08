package com.smdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdm.bean.Admin;
import com.smdm.bean.AdminExample;
import com.smdm.bean.AdminExample.Criteria;
import com.smdm.dao.AdminMapper;

@Service
public class AdminService {
	@Autowired
	AdminMapper adminMapper;
	
	/*
	 * 验证账号与密码
	 */
	public List<Admin> checkAccountPSW(String account, String password) {
		
		if(account==null || account.isEmpty() || password==null || password.isEmpty())
			return null;
		
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account).andPasswordEqualTo(password);
		List<Admin> result = adminMapper.selectByExample(example);
		
		return result;
		
	}
	
	/*
	 * 判断账号是否存在
	 */
	public boolean accountIsExist(String account) {
		
		if(account==null || account.isEmpty())
			return false;
		
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<Admin> result = adminMapper.selectByExample(example);
		
		if(result.size()>0)
			return true;
		else 
			return false;
	}

	/*
	 * 注册账号
	 */
	public int registerAdmin(String account, String password,String name) {
		if(account==null || account.isEmpty() || password==null || password.isEmpty())
			return -1;
		Admin admin = new Admin();
		admin.setAccount(account);
		admin.setPassword(password);
		admin.setName(name);
		int result = adminMapper.insertSelective(admin);
		return result;
	}
	
	/*
	 * 获取管理员通过账号
	 */
	public List<Admin> getAdminByAccount(String account) {
		
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<Admin> result = adminMapper.selectByExample(example);
		return result;
	}
	
	/*
	 * 获取管理员通过id
	 */
	public List<Admin> getAdminById(Integer id) {
		AdminExample example = new AdminExample();
		example.or().andIdEqualTo(id);
		return adminMapper.selectByExample(example);
	}

	/*
	 * 获取所有管理员
	 */
	public List<Admin> getAllAdmin() {
		return adminMapper.selectByExample(null);
	}

	/*
	 * 删除管理员通过id
	 */
	public int deleteAdminById(Integer id) {
		return adminMapper.deleteByPrimaryKey(id);
	}

	/*
	 * 搜索符合关键字的管理员
	 */
	public List<Admin> searchAdmin(String key) {
		AdminExample example = new AdminExample();
		example.or().andNameLike("%"+key+"%");
		return adminMapper.selectByExample(example);
	}
}

package com.smdm.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdm.bean.User;
import com.smdm.bean.UserExample;
import com.smdm.bean.UserExample.Criteria;
import com.smdm.dao.UserMapper;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	/*
	 * 验证账号与密码
	 */
	public List<User> checkAccountPSW(String account, String password) {
		
		if(account==null || account.isEmpty() || password==null || password.isEmpty())
			return null;
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account).andPasswordEqualTo(password);
		List<User> result = userMapper.selectByExample(example);
		
		return result;
		
	}
	
	/*
	 * 判断账号是否存在
	 */
	public boolean accountIsExist(String account) {
		
		if(account==null || account.isEmpty())
			return false;
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(account);
		List<User> result = userMapper.selectByExample(example);
		
		if(result.size()>0)
			return true;
		else 
			return false;
	}

	/*
	 * 注册账号
	 */
	public int registerUser(String account, String password) {
		if(account==null || account.isEmpty() || password==null || password.isEmpty())
			return -1;
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		int result = userMapper.insertSelective(user);
		return result;
	}

	/*
	 * 获取所有用户（通过积分point排序）
	 */
	public List<User> getAllUserByOrder() {
		UserExample example = new UserExample();
		example.setOrderByClause("point");
		return userMapper.selectByExample(example);
	}
	/*
	 * 获取用户（通过id）
	 */
	public List<User> getUserById(Integer id) {	
		UserExample example = new UserExample();
		example.or().andIdEqualTo(id);
		return userMapper.selectByExample(example);
	}

	/*
	 * 获取所有用户
	 */
	public List<User> getAllUser() {
		return userMapper.selectByExample(null);
	}

	/*
	 * 更新用户的状态
	 */
	public int updateUserByStatus(Integer id, Integer status) {
		User user = new User();
		user.setId(id);
		user.setStatus(status);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	/*
	 * 搜索用户（通过名字）
	 */
	public List<User> searchUser(String key) {
		UserExample example = new UserExample();
		example.or().andNameLike("%"+key+"%");
		return userMapper.selectByExample(example);
	}

}

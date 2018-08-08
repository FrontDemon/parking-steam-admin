package com.smdm.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdm.bean.Lot;
import com.smdm.bean.LotExample;
import com.smdm.bean.LotExample.Criteria;
import com.smdm.dao.LotMapper;

@Service
public class LotService {
	@Autowired
	LotMapper lotMapper;

	//获取车位列表（通过id）
	public Lot getParkingtById(Integer id) {
		return lotMapper.selectByPrimaryKey(id);
	}

	//获取所有车位列表
	public List<Lot> getAllParking() {
		return lotMapper.selectByExample(null);
	}

	//插入车位
	public int insertParking(String number, Double price, String address) {
		Lot parking = new Lot(null, number, price, address, null);
		return lotMapper.insertSelective(parking);
	}

	//更新车位
	public int updateParking(Integer id, String number, Double price, String address) {
		Lot parking = new Lot(id, number, price, address, null);
		return lotMapper.updateByPrimaryKeySelective(parking);
	}

	//删除车位
	public int deleteParkingById(Integer id) {
		return lotMapper.deleteByPrimaryKey(id);
	}

	//搜索车位
	public List<Lot> searchParking(String number) {
		if(number.isEmpty())
			return lotMapper.selectByExample(null);
		
		LotExample example = new LotExample();
		Criteria criteria = example.createCriteria();
		criteria.andNumberLike("%"+number+"%");
		return lotMapper.selectByExample(example);
	}
}

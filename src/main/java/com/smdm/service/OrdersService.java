package com.smdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smdm.bean.Announcement;
import com.smdm.bean.Lot;
import com.smdm.bean.Orders;
import com.smdm.bean.OrdersExample;
import com.smdm.bean.OrdersExample.Criteria;
import com.smdm.dao.OrdersMapper;

@Service
public class OrdersService {
	@Autowired
	OrdersMapper ordersMapper;

	//获取订单（通过id）
	public Orders getOrderById(Integer id) {
		return ordersMapper.selectByPrimaryKeyWithDetails(id);
	}

	//获取所有订单
	public List<Orders> getAllOrders() {
		return ordersMapper.selectAllWithDetails();
	}

	//删除订单（通过id）
	public int deleteOrderById(Integer id) {
		return ordersMapper.deleteByPrimaryKey(id);
	}

	//搜索订单
	public List<Orders> searchOrders(String key) {
		return null;
	}

}

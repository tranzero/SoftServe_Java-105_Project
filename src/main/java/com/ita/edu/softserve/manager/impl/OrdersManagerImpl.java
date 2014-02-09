package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.OrdersDAO;
import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.OrdersManager;

@Service("ordersManager")
public class OrdersManagerImpl implements OrdersManager {

	@Autowired
	private OrdersDAO ordersDao;

	public static OrdersManager getInstance() {
		return ManagerFactory.getManager(OrdersManager.class);
	}

	@Override
	public Orders findOrder(Integer id) {
		return ordersDao.findById(id);
	}

	@Override
	public List<Orders> findOrdersByUserId(Integer id) {
		return ordersDao.findOrdersByUserId(id);
	}
	

}

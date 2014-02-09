package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Orders;

@Service
public interface OrdersManager extends BaseManager {

	public Orders findOrder(Integer id);
	
	public List<Orders> findOrdersByUserId(Integer id);
}

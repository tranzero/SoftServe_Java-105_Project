package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Orders;

public interface OrdersDAO extends AbstractDAOIface<Orders> {

	public List<Orders> findOrdersByUserId(int id);
}

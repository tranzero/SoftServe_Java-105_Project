package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.entity.Post;

/**
 * @author nvrubl
 *
 */
public interface OrdersDAO extends AbstractDAOIface<Orders> {

	/**
	 * Find orders by user ID
	 * @param id user ID
	 * @return List of found orders
	 */
	public List<Orders> findOrdersByUserId(int id);
	
	public long getOrdersListCount();

	public List<Orders> getOrdersForOnePage(int from, int count);
}

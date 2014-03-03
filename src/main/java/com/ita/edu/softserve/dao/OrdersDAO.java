package com.ita.edu.softserve.dao;

import java.util.Date;
import java.util.List;

import com.ita.edu.softserve.entity.Orders;

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
	
	/**
	 * Find size of the list
	 * @return size of the list
	 */
	public long getOrdersListCount();

	/**
	 * Find orders for one page
	 * @param from from what element list will begin
	 * @param count how match elements will be in the list 
	 * @return List of lines witch will be printed on one page
	 */
	public List<Orders> getOrdersForOnePage(int from, int count);
	
	public Orders findByUserIdAndOrderDate(Integer userId, Date date);
}

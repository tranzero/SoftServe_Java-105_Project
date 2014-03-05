package com.ita.edu.softserve.manager;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Orders;

/**
 * @author nvrubl
 *
 */
@Service
public interface OrdersManager extends BaseManager {

	/**
	 * Find order by order ID
	 * @param id order ID
	 * @return one order
	 */
	Orders findOrder(Integer id);
	
	/**
	 * Find all orders
	 * @return list of all orders
	 */
	List<Orders> findAllOrders();
	
	/**
	 * Find Orders by user ID
	 * @param id user ID
	 * @return list of found orders
	 */
	List<Orders> findOrdersByUserId(Integer id);

	/**
	 * @param from
	 * @param count
	 * @return list of orders for one page
	 */
	List<Orders> getOrdersForPage(int from, int count);
	
	/**
	 * Create Order
	 * @param userId user ID
	 * @param date Date
	 */
	void createOrder(Integer userId,Date date);
	
	/**
	 * @return orders list count
	 */
	public long getOrdersListCount();
	
	/**
	 * Find Order by user id and Order Date
	 * @param userId user ID
	 * @param date DAte
	 * @return one order 
	 */
	Orders findByUserIdAndOrderDate(Integer userId,Date date);
	
}

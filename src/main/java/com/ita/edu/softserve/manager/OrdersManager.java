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
	public Orders findOrder(Integer id);
	
	/**
	 * Find all orders
	 * @return list of all orders
	 */
	public List<Orders> findAllOrders();
	
	/**
	 * Find Order by user ID
	 * @param id user ID
	 * @return list of found orders
	 */
	public List<Orders> findOrdersByUserId(Integer id);

	public long getOrdersListCount();

	public List<Orders> getOrdersForPage(int from, int count);
	
	public void createOrder(Integer userId,Date date);
	
	public Orders findByUserIdAndOrderDate(Integer userId,Date date);
	
}

package com.ita.edu.softserve.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.OrdersDAO;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.OrdersManager;
import com.ita.edu.softserve.manager.TicketsManager;

/**
 * Orders Manager class.
 * 
 * @author nvrubl
 *
 */
@Service("ordersManager")
public class OrdersManagerImpl implements OrdersManager {

	/**
	 * Get access to ORDERS DAO
	 */
	@Autowired
	private OrdersDAO ordersDao;
	
	@Autowired
	private UsersDAO usersDao;

	@Autowired
	private TripsDAO tripsDao;

	public static OrdersManager getInstance() {
		return ManagerFactory.getManager(OrdersManager.class);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Orders findOrder(Integer id) {
		return ordersDao.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Orders> findOrdersByUserId(Integer id) {
		return ordersDao.findOrdersByUserId(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Orders> findAllOrders() {
		return ordersDao.getAllEntities();
	}

	@Override
	public long getOrdersListCount() {
		return ordersDao.getOrdersListCount();

	}

	@Override
	public List<Orders> getOrdersForPage(int from, int count) {
		
		return ordersDao.getOrdersForOnePage(from, count);

	}
	
	@Transactional
	@Override
	public void createOrder(Integer userId,Date date){
		
		Orders order = new Orders(usersDao.findById(userId),date);
		ordersDao.save(order);
		
	}
	
	@Transactional
	@Override
	public Orders findByUserIdAndOrderDate(Integer userId,Date date){
		
		return (Orders)ordersDao.findByUserIdAndOrderDate(userId, date);
	}

}

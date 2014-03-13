package com.ita.edu.softserve.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.OrdersDAO;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.exception.OrdersManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.OrdersManager;

/**
 * Orders Manager class.
 * 
 * @author nvrubl
 *
 */
/**
 * @author Comp
 *
 */
@Service("ordersManager")
public class OrdersManagerImpl implements OrdersManager {

	private static final Logger LOGGER = Logger
			.getLogger(OrdersManagerImpl.class);
	
	private final String findOrderMessage = "Could not find such order";
	private final String findOrdersByUserId = "Could not find Orders list by such User id";
	private final String getAllOrders = "Could not get list of Orders";
	private final String getOrdersListCount = "Could not get Orders list count";
	private final String getOrdersForPage = "Could not get Orders for one page";
	
	/**
	 * Get access to ORDERS DAO
	 */
	@Autowired
	private OrdersDAO ordersDao;
	
	
	/**
	 * Get access to USERS DAO
	 */
	@Autowired
	private UsersDAO usersDao;

	/**
	 *Get access to TRIPS DAO 
	 */
	@Autowired
	private TripsDAO tripsDao;

	public static OrdersManager getInstance() {
		return ManagerFactory.getManager(OrdersManager.class);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Orders findOrder(Integer id) {
		try {
			return ordersDao.findById(id);
		} catch (RuntimeException e) {
			RuntimeException ex = new OrdersManagerException(findOrderMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Orders> findOrdersByUserId(Integer id) {
		try {
			return ordersDao.findOrdersByUserId(id);
		} catch (RuntimeException e) {
			RuntimeException ex= new OrdersManagerException(findOrdersByUserId, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Orders> findAllOrders() {
		try {
			return ordersDao.getAllEntities();
		} catch (RuntimeException e) {
			RuntimeException ex= new OrdersManagerException(getAllOrders, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public long getOrdersListCount() {
		try {
			return ordersDao.getOrdersListCount();
		} catch (RuntimeException e) {
			RuntimeException ex = new OrdersManagerException(getOrdersListCount,e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw e;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Orders> getOrdersForPage(int from, int count) {
		try {
		return ordersDao.getOrdersForOnePage(from, count);
		} catch (RuntimeException e) {
			RuntimeException ex = new OrdersManagerException(getOrdersForPage,e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw e;
		}
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

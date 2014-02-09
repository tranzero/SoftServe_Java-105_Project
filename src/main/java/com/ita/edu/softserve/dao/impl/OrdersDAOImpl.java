package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.OrdersDAO;
import com.ita.edu.softserve.entity.Orders;

@Repository("ordersDao")
public class OrdersDAOImpl extends AbstractDAO<Orders> implements OrdersDAO {

	@Override
	public Class<Orders> getEntityClass() {
		return Orders.class;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> findOrdersByUserId(int id) {
		Query query = entityManager.createNamedQuery(Orders.FIND_BY_USERID).setParameter(1, id);
		List<Orders> list = query.getResultList();
		return list;
	}

}

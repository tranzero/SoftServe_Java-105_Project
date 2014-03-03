package com.ita.edu.softserve.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.OrdersDAO;
import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.entity.Post;

/**
 * @author nvrubl
 *
 */
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
	
	@Override
	public long getOrdersListCount() {
		return (long) find((Query)entityManager
					.createNamedQuery(Orders.FIND_ORDER_LIST_COUNT));
	}
	
	@Override
    public List<Orders> getOrdersForOnePage (int from, int count) {
		
		return this.getOrdersForPaging(from, count);
    }
	
	private List<Orders> getOrdersForPaging(int from, int count) {
		Query query = entityManager
			.createNamedQuery(
					Orders.FIND_ORDER_LIST_FOR_PAGING)
			.setFirstResult(from).setMaxResults(count);
		return (List<Orders>)getRange(from, count, query);
	    }
	
	@Override
	public Orders findByUserIdAndOrderDate(Integer userId, Date date){
		
		Query query = entityManager.createNamedQuery(Orders.FIND_BY_USERID_AND_ORDER_DATE).setParameter(1, userId).setParameter(2, date);
		
		return (Orders) query.getSingleResult();
	}

}

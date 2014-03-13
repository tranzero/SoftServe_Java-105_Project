package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TicketsDAO;
import com.ita.edu.softserve.entity.Tickets;

@Repository
public class TicketsDAOImpl extends AbstractDAO<Tickets> implements TicketsDAO {

	@Override
	public Class<Tickets> getEntityClass() {
		return Tickets.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tickets> findTicketsByOrderId(Integer id) {

		Query query = entityManager.createNamedQuery(Tickets.FIND_BY_ORDERID)
				.setParameter(1, id);

		return (List<Tickets>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tickets> findTicketsByTripId(Integer id) {

		Query query = entityManager.createNamedQuery(Tickets.FIND_BY_TRIPID)
				.setParameter(1, id);

		return (List<Tickets>) query.getResultList();
	}
}

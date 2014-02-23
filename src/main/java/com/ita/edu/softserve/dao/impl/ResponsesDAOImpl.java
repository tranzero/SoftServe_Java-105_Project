package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.ResponsesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Responses;

@Repository("responsesDao")
public class ResponsesDAOImpl extends AbstractDAO<Responses> implements ResponsesDAO {

	@Override
	public Class<Responses> getEntityClass() {
		return Responses.class;
	}

	@Override
	public List<Responses> findResponsesByRouteId(Integer routeId) {
		Query query = entityManager.createNamedQuery(Responses.FIND_BY_ROUTE_ID)
				.setParameter(1, routeId);
		
		return (List<Responses>) query.getResultList();
	}

	@Override
	public List<Responses> findResponsesByTripId(Integer tripId) {
		Query query = entityManager.createNamedQuery(Responses.FIND_BY_TRIP_ID)
				.setParameter(1, tripId);
		
		return (List<Responses>) query.getResultList();
	}

	@Override
	public List<Responses> findResponsesByTranportId(Integer transportId) {
		Query query = entityManager.createNamedQuery(Responses.FIND_BY_TRANSPORT_ID)
				.setParameter(1, transportId);
		
		return (List<Responses>) query.getResultList();
	}

	@Override
	public List<Responses> findUncheckedResponses() {
		Query query = entityManager.createNamedQuery(Responses.FIND_UNCHECKED_RESPONSES);
		
		return (List<Responses>) query.getResultList();		
	}	

}

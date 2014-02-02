package com.ita.edu.softserve.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Transports;

/**
 * @author Roman
 */
@Repository("transportsDao")
public class TransportsDaoImpl extends AbstractDAO<Transports> implements TransportsDao{
	
	@Override
	public Class<Transports> getEntityClass() {
		return Transports.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Transports findByRouteId(int id) {
		Query query = entityManager.createNamedQuery(
				Transports.FIND_BY_ROUTEID).setParameter(1, id);
		
		return (Transports) query.getSingleResult();
	}
}

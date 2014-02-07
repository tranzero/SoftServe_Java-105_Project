package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.dao.TripsDao;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;

/**
 * @author dnycktc
 */
@Repository("tripsDao")
public class TripsDaoImpl extends AbstractDAO<Trips> implements TripsDao{

	@Override
	public Class<Trips> getEntityClass() {
		return Trips.class;
	}

	@Override
	public List<Trips> findByTransportId(int id) {
		Query query = entityManager
				.createNamedQuery(Trips.FIND_BY_TRANSPORTID).setParameter(1,
						id);
		List<Trips> result = query.getResultList();
		return result;
	}

	@Override
	public void saveOrUpdate(Trips entity) {
		if (entity.getTripId() == null) {
			entityManager.persist(entity);
			entityManager.refresh(entity);
		} else {
			entityManager.merge(entity);
		}
		
	}

}

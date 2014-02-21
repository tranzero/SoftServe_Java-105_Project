package com.ita.edu.softserve.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;

/**
 * @author Roman
 */
@Repository("transportsDao")
public class TransportsDaoImpl extends AbstractDAO<Transports> implements
		TransportsDao {

	@Override
	public Class<Transports> getEntityClass() {
		return Transports.class;
	}

	/**
	 * Finds Transport by route id.
	 * @param id to find object.
	 */
	@Override
	public Transports findByRouteId(int id) {
		Query query = entityManager
				.createNamedQuery(Transports.FIND_BY_ROUTEID).setParameter(1,
						id);

		return (Transports) query.getSingleResult();
	}

	/**
	 * Saves a Transport into the Transports table if not exist or updates
	 * existing one.
	 * @param entity to save or update.
	 */
	@Override
	public void saveOrUpdate(final Transports entity) {
		if (entity.getTransportId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}
	@Override
	public List<Transports> findByDate(String date) {
		Query query = entityManager
				.createNamedQuery(Transports.FIND_BY_DATE)
				.setParameter(1, date);

		return (List<Transports>) query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TransportTravel> findByTwoStations(String stationName1,
			String stationName2) {
		Query query = entityManager
				.createNamedQuery(Transports.FIND_BY_TWO_STATIONS)
				.setParameter(1, stationName1).setParameter(2, stationName2);

		return (List<TransportTravel>) query.getResultList();
	}

	@Override
	public List<TransportTravel> getTransportByTwoStForLimits(
			String stationName1, String stationName2, int firstElement,
			int count, int orderBy) {
		Query query = null;
/*		
		if (orderBy == 1) {
			query = entityManager
					.createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_LNAME)
					.setParameter(1, stationName1).setParameter(2, stationName2)
					.setFirstResult(firstElement).setMaxResults(count);			
		} else if (orderBy == 2) {
			query = entityManager
					.createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_TCODE)
					.setParameter(1, stationName1).setParameter(2, stationName2)
					.setFirstResult(firstElement).setMaxResults(count);			
		} else if (orderBy == 3) {
			query = entityManager
					.createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_DEP)
					.setParameter(1, stationName1).setParameter(2, stationName2)
					.setFirstResult(firstElement).setMaxResults(count);			
		} else if (orderBy == 4) {
			query = entityManager
					.createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_DURATION)
					.setParameter(1, stationName1).setParameter(2, stationName2)
					.setFirstResult(firstElement).setMaxResults(count);			
		} else {
*/			query = entityManager
					.createNamedQuery(Transports.FIND_BY_TWO_STATIONS)
					.setParameter(1, stationName1).setParameter(2, stationName2)
					.setFirstResult(firstElement).setMaxResults(count);
//		}

		return (List<TransportTravel>) query.getResultList();
	}

	@Override
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2) {
		//this will be changed soon
		return findByTwoStations(stationName1, stationName2).size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsForLimits(int firstElement, int count) {
		Query query = entityManager
				.createNamedQuery(Transports.TRANSPORTS_FIND_ALL)
				.setFirstResult(firstElement).setMaxResults(count);
		
		return (List<Transports>) query.getResultList();
	}

	@Override
	public long getTransportsListCount() {
		return (long) find((Query) entityManager
				.createNamedQuery(Transports.TRANSPORTS_FIND_COUNT));
	}

}

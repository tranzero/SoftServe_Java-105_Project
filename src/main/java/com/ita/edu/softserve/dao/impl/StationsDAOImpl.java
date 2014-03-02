package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author admin
 * 
 */
@Repository("stationsDao")
public class StationsDAOImpl extends AbstractDAO<Stations> implements
		StationsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> findByStations(String stationName) {
		Query query = entityManager.createNamedQuery(Stations.FIND_BY_NAME)
				.setParameter(1, stationName);

		return query.getResultList();
	}

	@Override
	public Stations findByName(String stationName) {
		Query query = entityManager.createNamedQuery(Stations.FIND_BY_NAME)
				.setParameter(1, stationName);
		return (Stations) query.getSingleResult();
	}

	/**
	 * Saves a Station into table if not exist or update existing one.
	 * 
	 * @param entity
	 *            the entity to save or update into Stations table.
	 */
	@Override
	public void saveOrUpdate(final Stations entity) {
		if (entity.getStationId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	@Override
	public long getStationsListCount() {
		return (long) find((Query) entityManager
				.createNamedQuery(Stations.STATIONS_FIND_COUNT));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> getStationsForLimits(int firstElement, int count) {
		Query query = entityManager
				.createNamedQuery(Stations.STATIONS_FIND_ALL)
				.setFirstResult(firstElement).setMaxResults(count);
		return (List<Stations>) query.getResultList();
	}

	/*
	 * @Override public Stations findByStations(String stationName) { Query
	 * query = entityManager.createNamedQuery(
	 * Stations.FIND_BY_NAME_QUERY).setParameter(1, stationName); return
	 * (Stations) query.getSingleResult(); }
	 */
	@Override
	public Class<Stations> getEntityClass() {

		return Stations.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> findByLineName(String lineName) {
		Query query = entityManager
				.createNamedQuery(Stations.FIND_BY_LINE_NAME).setParameter(1,
						lineName);
		List<Stations> list = query.getResultList();
		return list;
	}

	@Override
	public long getStationsListCriteriaCount(String searchString) {
		Query query = entityManager.createNamedQuery(
				Stations.GET_COUNT_STATIONS_WITH_CRITERIA).setParameter(
				Stations.SEARCH_STRING, searchString);

		return (long) find(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> getStationsForOnePageWithCriteria(int firstElement,
			int count, String searchString, String orderByParam,
			String orderByDirection) {

		Query query = entityManager
				.createQuery(
						Stations.FIND_STATIONS_LIST_BY_CRITERIA + orderByParam
								+ " " + orderByDirection)
				.setParameter(Stations.SEARCH_STRING, searchString)
				.setFirstResult(firstElement).setMaxResults(count);
		return (List<Stations>) query.getResultList();
	}
}

package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Stations;

/**
 * @author admin
 */
@Repository("stationsDao")
public class StationsDAOImpl extends AbstractDAO<Stations> implements
		StationsDAO {

	@Override
	public Class<Stations> getEntityClass() {
		return Stations.class;
	}

	/**
	 * Finds <code>Stations</code> by stationName.
	 * 
	 * @param stationName
	 *            - the name of station to find.
	 * 
	 * @return the List of <code>Stations</code> objects.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> findByStations(String stationName) {
		Query query = entityManager.createNamedQuery(Stations.FIND_BY_NAME)
				.setParameter(1, stationName);

		return query.getResultList();
	}

	/**
	 * Finds <code>Stations</code> by name of the station.
	 * 
	 * @param stationName
	 *            - the name to find object by
	 * @return the <code>Stations</code> object
	 */
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
	 *            - the entity to save or update into Stations table.
	 */
	@Override
	public void saveOrUpdate(final Stations entity) {
		if (entity.getStationId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	/**
	 * Finds the number of <code>Stations</code> and return it quantity.
	 * 
	 * @return the count of <code>Stations</code> objects.
	 */
	@Override
	public long getStationsListCount() {
		return (long) find((Query) entityManager
				.createNamedQuery(Stations.STATIONS_FIND_COUNT));
	}

	/**
	 * Searches all stations with given limit of amount
	 * 
	 * @param firstElement
	 *            Starting element for result list
	 * @param count
	 *            capacity of result list
	 * @return List of Stations according given limits.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> getStationsForLimits(int firstElement, int count) {
		Query query = entityManager
				.createNamedQuery(Stations.STATIONS_FIND_ALL)
				.setFirstResult(firstElement).setMaxResults(count);
		return (List<Stations>) query.getResultList();
	}

	/**
	 * Finds <code>List of Stations</code> by line name.
	 * 
	 * @param lineName
	 *            - the name of line.
	 * @return the List of <code>Stations</code> objects.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> findByLineName(String lineName) {
		Query query = entityManager
				.createNamedQuery(Stations.FIND_BY_LINE_NAME).setParameter(1,
						lineName);
		List<Stations> list = query.getResultList();
		return list;
	}

	/**
	 * @param searchString
	 *            - string for matching.
	 * 
	 * @return number of stations elements that corresponding to searchString.
	 */
	@Override
	public long getStationsListCriteriaCount(String searchString) {
		Query query = entityManager.createNamedQuery(
				Stations.GET_COUNT_STATIONS_WITH_CRITERIA).setParameter(
				Stations.SEARCH_STRING, searchString);

		return (long) find(query);
	}

	/**
	 * @param firstElement
	 *            - Starting element for result list
	 * @param count
	 *            - capacity of result list
	 * @param searchString
	 *            - searchString for matching
	 * @param orderByParam
	 *            - the column, using for sorting
	 * @param orderByDirection
	 *            - sorting direction
	 * @return List of Stations according given limits and criteria.
	 */
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

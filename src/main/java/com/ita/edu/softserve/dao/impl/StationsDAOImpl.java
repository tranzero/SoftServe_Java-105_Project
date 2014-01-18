package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Stops;

/**
 * 
 * @author iryna
 * 
 */
public class StationsDAOImpl extends AbstractDAOClass implements StationsDAO {

	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public Stations findByStations(String stationName) {
		Query query = entityManager.createNamedQuery(
				Stations.FIND_BY_NAME_QUERY).setParameter(1, stationName);
		return (Stations) find(query);
	}

	@Override
	public void save(Stations station) {
		entityManager.persist(station);
	}

	@Override
	public void remove(Stations station) {
		entityManager.remove(station);
	}

	@Override
	public Stations update(Stations station) {
		return entityManager.merge(station);
	}

}

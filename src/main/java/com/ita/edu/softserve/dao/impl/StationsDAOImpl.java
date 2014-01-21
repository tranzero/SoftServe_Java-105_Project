package com.ita.edu.softserve.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
 * 
 */
@Repository
public class StationsDAOImpl extends AbstractDAO<Stations> implements
		StationsDAO {

	@Override
	public Stations findByStations(String stationName) {
		Query query = entityManager.createNamedQuery(
				Stations.FIND_BY_NAME_QUERY).setParameter(1, stationName);
		return (Stations) query.getSingleResult();
	}

	@Override
	protected Class<Stations> getEntityClass() {

		return Stations.class;
	}
}

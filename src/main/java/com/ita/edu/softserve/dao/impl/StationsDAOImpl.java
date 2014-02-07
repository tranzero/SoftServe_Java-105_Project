package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
 * 
 */
@Repository ("stationsDao")
public class StationsDAOImpl extends AbstractDAO<Stations> implements
		StationsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Stations> findByStations(String stationName) {
		Query query = entityManager.createNamedQuery(
				Stations.FIND_BY_NAME).setParameter(1, stationName);
		
		return query.getResultList();
	}
	
	@Override
    public Stations findByName(String stationName) {
            Query query = entityManager.createNamedQuery(Stations.FIND_BY_NAME)
                            .setParameter(1, stationName);
            return (Stations) query.getSingleResult();
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
	@Override
    public List<Stations> findByLineName(String lineName){
		Query query = entityManager.createNamedQuery(Stations.FIND_BY_LINE_NAME).setParameter(1, lineName);
		List<Stations> list = query.getResultList();
		return list;
	}
}

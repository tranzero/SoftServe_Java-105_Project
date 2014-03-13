package com.ita.edu.softserve.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.entity.Stops;

/**
 * 
 * @author iryna
 * 
 */
@Repository
public class StopsDAOImpl extends AbstractDAO<Stops> implements StopsDAO {

	@Override
	public Class<Stops> getEntityClass() {
		return Stops.class;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Stops findByStationsOnLineId(int id) {
		Query query = entityManager.createNamedQuery(
				Stops.FIND_BY_STATIONONLINEID).setParameter(1, id);
		
		return (Stops) query.getSingleResult();
	}
}

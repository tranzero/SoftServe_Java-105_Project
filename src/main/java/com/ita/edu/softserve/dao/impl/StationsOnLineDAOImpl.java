package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * 
 * @author iryna
 * 
 */
public class StationsOnLineDAOImpl extends AbstractDAOClass implements
		StationsOnLineDAO {
	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public StationsOnLine findByID(int id) {
		Query query = entityManager.createNamedQuery(
				StationsOnLine.FIND_BY_ID_QUERY).setParameter(1, id);
		return (StationsOnLine) find(query);
	}

	@Override
	public void save(StationsOnLine stationsOnLine) {
		entityManager.persist(stationsOnLine);
	}

	@Override
	public void remove(StationsOnLine stationsOnLine) {
		entityManager.remove(stationsOnLine);
	}

	@Override
	public StationsOnLine update(StationsOnLine stationsOnLine) {
		return entityManager.merge(stationsOnLine);
	}

}

package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.entity.Stops;

/**
 * 
 * @author iryna
 * 
 */
public class StopsDAOImpl extends AbstractDAOClass implements StopsDAO {

	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public Stops findByID(int id) {
		Query query = entityManager.createNamedQuery(Stops.FIND_BY_ID)
				.setParameter(1, id);
		return (Stops) find(query);

	}

	@Override
	public void save(Stops stop) {
		entityManager.persist(stop);

	}

	@Override
	public void remove(Stops stop) {
		entityManager.remove(stop);

	}

	@Override
	public Stops update(Stops stop) {
		return entityManager.merge(stop);
	}

}

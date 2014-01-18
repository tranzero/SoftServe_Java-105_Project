package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 * 
 */
public class RoutesDAOImpl extends AbstractDAOClass implements RoutesDAO {

	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public Routes findByCode(String routeCode) {
		Query query = entityManager.createNamedQuery(Lines.FIND_BY_NAME)
				.setParameter(1, routeCode);
		return (Routes) find(query);
	}

	@Override
	public void save(Routes route) {
		entityManager.persist(route);
	}

	@Override
	public void remove(Routes route) {
		entityManager.remove(route);
	}

	@Override
	public Routes update(Routes route) {
		return entityManager.merge(route);
	}

}

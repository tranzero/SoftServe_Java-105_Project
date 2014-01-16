package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.RoutesDAO;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Routes route) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Routes route) {
		// TODO Auto-generated method stub

	}

	@Override
	public Routes update(Routes route) {
		// TODO Auto-generated method stub
		return null;
	}

}

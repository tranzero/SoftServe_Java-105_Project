package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.entity.Stops;

public class StopsDAOImpl extends AbstractDAOClass implements StopsDAO{

	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
	
	@Override
	public Stops findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Stops stop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Stops stop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Stops update(Stops stop) {
		// TODO Auto-generated method stub
		return null;
	}

}

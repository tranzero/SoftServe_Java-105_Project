package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(StationsOnLine stationsOnLine) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(StationsOnLine stationsOnLine) {
		// TODO Auto-generated method stub

	}

	@Override
	public StationsOnLine update(StationsOnLine stationsOnLine) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.ita.edu.softserve.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ita.edu.softserve.daoiface.AbstractDAOClass;
import com.ita.edu.softserve.daoiface.StopsDAO;
import com.ita.edu.softserve.entity.Stops;

/**
 * 
 * @author iryna
 *
 */
public class StationsDAOImpl extends AbstractDAOClass implements StopsDAO{

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

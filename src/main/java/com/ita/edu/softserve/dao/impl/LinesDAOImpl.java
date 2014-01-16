package com.ita.edu.softserve.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ita.edu.softserve.daoiface.AbstractDAOClass;
import com.ita.edu.softserve.daoiface.LinesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 *
 */
public class LinesDAOImpl extends AbstractDAOClass implements LinesDAO{

	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
	
	@Override
	public Lines findByName(String lineName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Lines lines) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Lines lines) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Lines update(Lines lines) {
		// TODO Auto-generated method stub
		return null;
	}

}

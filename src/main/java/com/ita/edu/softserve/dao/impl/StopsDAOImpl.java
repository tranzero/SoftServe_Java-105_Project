package com.ita.edu.softserve.dao.impl;

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
	protected Class<Stops> getEntityClass() {
		
		return Stops.class;
	}

	
}

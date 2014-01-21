package com.ita.edu.softserve.dao.impl;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * 
 * @author iryna
 * 
 */
@Repository
public class StationsOnLineDAOImpl extends AbstractDAO<StationsOnLine> implements
		StationsOnLineDAO {

	@Override
	protected Class<StationsOnLine> getEntityClass() {
		return StationsOnLine.class;
	}
	
}

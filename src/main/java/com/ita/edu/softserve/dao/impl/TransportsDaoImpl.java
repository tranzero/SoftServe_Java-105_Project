package com.ita.edu.softserve.dao.impl;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Transports;

/**
 * @author Roman
 */
@Repository("transportsDao")
public class TransportsDaoImpl extends AbstractDAO<Transports> implements TransportsDao{
	
	@Override
	public Class<Transports> getEntityClass() {
		return Transports.class;
	}
}

package com.ita.edu.softserve.dao.impl;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.AbstractDAOIface;
import com.ita.edu.softserve.dao.TicketsDAO;
import com.ita.edu.softserve.entity.Tickets;

@Repository
public class TicketsDAOImpl extends AbstractDAO<Tickets> implements TicketsDAO{

	@Override
	public Class<Tickets> getEntityClass() {
		return Tickets.class;
	}

}

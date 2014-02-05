package com.ita.edu.softserve.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.SourceDao;
import com.ita.edu.softserve.entity.Source;

@Repository("sourceDao")
public class SourceDaoImpl extends AbstractDAO<Source> implements SourceDao {

	@PersistenceContext(name = "primary")
	private EntityManager entityManager;

	@Override
	public Class<Source> getEntityClass() {

		return Source.class;
	}

}

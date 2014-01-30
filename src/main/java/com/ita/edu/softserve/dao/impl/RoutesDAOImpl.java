package com.ita.edu.softserve.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 * 
 */
@Repository
public class RoutesDAOImpl extends AbstractDAO<Routes> implements RoutesDAO {

	@Override
	public Routes findByCode(String routeCode) {
		Query query = entityManager.createNamedQuery(Lines.FIND_BY_NAME)
				.setParameter(1, routeCode);
		return (Routes) query.getSingleResult();
	}

	@Override
	public Class<Routes> getEntityClass() {

		return Routes.class;
	}
}
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
 * class RoutesDAOImpl
 * 
 */
@Repository
public class RoutesDAOImpl extends AbstractDAO<Routes> implements RoutesDAO {

	@Override
	public Routes findByCode(String routeCode) {
		Query query = entityManager.createNamedQuery(Routes.FIND_BY_CODE)
				.setParameter(1, routeCode);
		return (Routes) query.getSingleResult();
	}

	@Override
	public Class<Routes> getEntityClass() {

		return Routes.class;
	}
}

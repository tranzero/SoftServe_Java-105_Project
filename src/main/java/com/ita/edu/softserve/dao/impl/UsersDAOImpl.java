package com.ita.edu.softserve.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Users;

/**
 * 
 * class UsersDAOImpl
 * 
 */
@Repository("usersDao")
public class UsersDAOImpl extends AbstractDAO<Users> implements UsersDAO {

	@Override
	public Users findByName(String name) {
		Query query = entityManager.createNamedQuery(Users.FIND_BY_NAME)
				.setParameter(1, name);
		return (Users) query.getSingleResult();
	}

	@Override
	public Class<Users> getEntityClass() {
		return Users.class;
	}

}
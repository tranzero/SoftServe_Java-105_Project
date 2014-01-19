package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAOClass;
import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Users;

/**
 * 
 * class UsersDAOImpl
 * 
 */
@Repository
public class UsersDAOImpl extends AbstractDAOClass implements UsersDAO {
	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public Users findByName(String name) {
		Query query = entityManager.createNamedQuery(Users.FIND_BY_NAME)
				.setParameter(1, name);
		return (Users) find(query);
	}

	@Override
	public void save(Users user) {
		entityManager.persist(user);
	}

	@Override
	public void remove(Users user) {
		entityManager.remove(user);
	}

	@Override
	public Users update(Users user) {
		return entityManager.merge(user);
	}

	@Override
	public List<Users> getAllUsers() {
		Query query = entityManager.createNamedQuery(Users.GET_ALL_USERS);
		@SuppressWarnings("unchecked")
		List<Users> usersList = query.getResultList();
		return usersList;
	}

}

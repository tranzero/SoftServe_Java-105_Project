package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.UsersDAO;

import com.ita.edu.softserve.entity.Users;

/**
 * class UsersDAOImpl
 * 
 * @author iryna
 * 
 */
@Repository("usersDao")
public class UsersDAOImpl extends AbstractDAO<Users> implements UsersDAO {

	/**
	 * Find user by name
	 */
	@Override
	public Users findByName(String name) {
		Query query = entityManager.createNamedQuery(Users.FIND_BY_NAME)
				.setParameter(1, name);
		return (Users) query.getSingleResult();
	}

	/**
	 * Get count all users
	 */
	@Override
	public Long getCountAllUsers() {
		Query query = entityManager.createNamedQuery(Users.GET_COUNT_ALL_USERS);
		return (Long) query.getSingleResult();
	}

	@Override
	public Class<Users> getEntityClass() {
		return Users.class;
	}

	/**
	 * Find user by username
	 */
	@Override
	public Users findByUsername(String username) {
		Query query = entityManager.createNamedQuery(Users.FIND_BY_USERNAME)
				.setParameter(1, username);
		return (Users) query.getSingleResult();
	}

	/**
	 * For paging - getUsersListCount
	 */
	@Override
	public long getUsersListCount() {
		return (long) find((Query) entityManager
				.createNamedQuery(Users.GET_COUNT_ALL_USERS));

	}

	/**
	 * For paging - getUsersForOnePag
	 */
	@Override
	public List<Users> getUsersForOnePage(int from, int count) {
		return this.getUsersForPaging(from, count);
	}

	/**
	 * For paging - getUsersForPaging
	 */
	public List<Users> getUsersForPaging(int from, int count) {
		Query query = entityManager.createNamedQuery(Users.GET_ALL_USERS)
				.setFirstResult(from).setMaxResults(count);
		return (List<Users>) getRange(from, count, query);

	}

}
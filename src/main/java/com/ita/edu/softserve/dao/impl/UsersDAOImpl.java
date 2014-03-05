package com.ita.edu.softserve.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Trips;
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
	 * Update User Data (for validator)
	 */
	public void updateUserData(final Users entity) {
		if (entity.getUserId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
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
	 * Find user by name
	 */
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

	/**
	 * Get count all users
	 */
	@Override
	public Long getCountAllUsers() {
		Query query = entityManager.createNamedQuery(Users.GET_COUNT_ALL_USERS);
		return (Long) query.getSingleResult();
	}

	/**
	 * For pagging 1 - getUsersListCount
	 */
	@Override
	public long getUsersListCount() {
		return (long) find((Query) entityManager
				.createNamedQuery(Users.GET_COUNT_ALL_USERS));

	}

	@Override
	public long getUsersListCountWithCriteria(String searchString,
			List<Role> roleArray, Date minDate, Date maxDate) {
		Query query = entityManager
				.createNamedQuery(Users.GET_COUNT_USERS_WITH_CRITERIA)
				.setParameter(Users.SEARCH_STRING_NAME, searchString)
				.setParameter(Users.ROLE_ARRAY_NAME, roleArray)
				.setParameter(Users.MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(Users.MAX_DATE_NAME, maxDate, TemporalType.DATE);
		return (long) find(query);
	}

	@Override
	public List<Users> getUsersForOnePageWithCriteria(int firstElement,
			int count, String searchString, List<Role> roleArray, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection) {

		Query query = entityManager
				.createQuery(
						Users.GET_USERS_WITH_CRITERIA_QUERY + orderByParam
								+ " " + orderByDirection)
				.setParameter(Users.SEARCH_STRING_NAME, searchString)
				.setParameter(Users.ROLE_ARRAY_NAME, roleArray)
				.setParameter(Users.MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(Users.MAX_DATE_NAME, maxDate, TemporalType.DATE)
				.setFirstResult(firstElement).setMaxResults(count);
		return (List<Users>) query.getResultList();
	}

	/**
	 * For pagging2 - getUsersForOnePag
	 */
	@Override
	public List<Users> getUsersForOnePage(int from, int count) {
		return this.getUsersForPaging(from, count);
	}

	/**
	 * For pagging2 - getUsersForPaging
	 */
	public List<Users> getUsersForPaging(int from, int count) {
		Query query = entityManager.createNamedQuery(Users.GET_ALL_USERS)
				.setFirstResult(from).setMaxResults(count);
		return (List<Users>) getRange(from, count, query);

	}

}
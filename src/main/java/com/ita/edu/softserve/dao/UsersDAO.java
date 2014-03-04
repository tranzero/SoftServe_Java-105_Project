package com.ita.edu.softserve.dao;

import java.util.Date;
import java.util.List;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;

/**
 * interface UsersDAO
 * 
 * @author iryna
 * 
 */
public interface UsersDAO extends AbstractDAOIface<Users> {

	/**
	 * Finds Users by name
	 * 
	 * @param name
	 * @return
	 */
	Users findByName(String name);

	/**
	 * Finds Users by username
	 * 
	 * @param username
	 * @return
	 */
	Users findByUsername(String username);

	/**
	 * Finds the number of Users *
	 * 
	 * @return
	 */
	Long getCountAllUsers();

	// for paging
	public long getUsersListCount();

	public List<Users> getUsersForOnePage(int from, int count);

	/**
	 * Update User Data
	 * 
	 * @param entity
	 */
	public void updateUserData(Users entity);

	// for pagging 1
	long getUsersListCountWithCriteria(String searchString,
			List<Role> roleArray, Date minDate, Date maxDate);

	List<Users> getUsersForOnePageWithCriteria(int firstElement, int count,
			String searchString, List<Role> roleArray, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection);
}
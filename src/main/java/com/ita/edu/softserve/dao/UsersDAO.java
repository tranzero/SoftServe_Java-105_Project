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
	 * Finds User by name
	 * 
	 * @param name
	 * @return
	 */
	Users findByName(String name);

	/**
	 * Finds User by username
	 * 
	 * @param username
	 * @return
	 */
	Users findByUsername(String username);

	/**
	 * Update User Data
	 * 
	 * @param entity
	 */
	public void updateUserData(Users entity);

	// For pagging1
	long getUsersListCountWithCriteria(String searchString,
			List<Role> roleArray, Date minDate, Date maxDate);

	List<Users> getUsersForOnePageWithCriteria(int firstElement, int count,
			String searchString, List<Role> roleArray, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection);

	/**
	 * Finds the number of Users
	 * 
	 * @return
	 */
	Long getCountAllUsers();

	// For pagging2
	public long getUsersListCount();

	public List<Users> getUsersForOnePage(int from, int count);
}
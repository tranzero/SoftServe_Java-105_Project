package com.ita.edu.softserve.dao;

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
}
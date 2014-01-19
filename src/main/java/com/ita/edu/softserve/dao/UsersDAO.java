package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Users;

/**
 * 
 * interface UsersDAO
 * 
 */
public interface UsersDAO {

	/**
	 * Find Users by name
	 * 
	 * @param name
	 * @return
	 */
	Users findByName(String name);

	/**
	 * Save a new user
	 * 
	 * @param user
	 */
	void save(Users user);

	/**
	 * Remove a user
	 * 
	 * @param user
	 */
	void remove(Users user);

	/**
	 * Update a user
	 * 
	 * @param user
	 * @return
	 */
	Users update(Users user);

	/**
	 * Get all users from DB
	 * 
	 * @return
	 */
	public List<Users> getAllUsers();

}

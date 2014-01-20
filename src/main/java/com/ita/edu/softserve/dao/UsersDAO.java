package com.ita.edu.softserve.dao;

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



}

package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Stops;
import com.ita.edu.softserve.entity.Users;

/**
 * 
 * @author iryna
 * 
 */
public interface StopsDAO {
	/**
	 * Find Stops by id
	 * 
	 * @param id
	 * @return
	 */
	Stops findByID(int id);

	/**
	 * Save a new stop
	 * 
	 * @param stop
	 */
	void save(Stops stop);

	/**
	 * Remove a stop
	 * 
	 * @param stop
	 */
	void remove(Stops stop);

	/**
	 * Update a stop
	 * 
	 * @param stop
	 * @return
	 */
	Stops update(Stops stop);
	//

}

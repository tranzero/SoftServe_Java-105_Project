package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * 
 * @author iryna
 * 
 */
public interface StationsOnLineDAO {
	/**
	 * Find StationsOnLine by id
	 * 
	 * @param id
	 * @return
	 */
	StationsOnLine findByID(int id);

	/**
	 * Save a new stationsOnLine
	 * 
	 * @param stationsOnLine
	 */
	void save(StationsOnLine stationsOnLine);

	/**
	 * Remove a stationsOnLine
	 * 
	 * @param stationsOnLine
	 */
	void remove(StationsOnLine stationsOnLine);

	/**
	 * Update a stationsOnLine
	 * 
	 * @param stationsOnLine
	 * @return
	 */
	StationsOnLine update(StationsOnLine stationsOnLine);

	//
}

package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Lines;

/**
 * 
 * @author iryna
 * 
 */
public interface LinesDAO {

	/**
	 * Find Lines by lineName
	 * 
	 * @param lineName
	 * @return
	 */
	Lines findByName(String lineName);

	/**
	 * Save new line
	 * 
	 * @param line
	 */
	void save(Lines line);

	/**
	 * Remove line
	 * 
	 * @param line
	 */
	void remove(Lines line);

	/**
	 * Update line
	 * 
	 * @param line
	 * @return
	 */
	Lines update(Lines line);

}

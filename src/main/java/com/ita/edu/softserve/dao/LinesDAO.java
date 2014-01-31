package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Lines;

/**
 * 
 * @author iryna
 * 
 */

public interface LinesDAO extends AbstractDAOIface<Lines> {

	/**
	 * Find Lines by lineName
	 * 
	 * @param lineName
	 * @return Lines
	 */
	public Lines findByName(String lineName);

}

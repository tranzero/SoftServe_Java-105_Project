package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

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
	public Lines findByName(String lineName);

	

}

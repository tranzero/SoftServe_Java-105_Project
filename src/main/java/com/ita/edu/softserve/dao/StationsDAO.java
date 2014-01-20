package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
 * 
 */
public interface StationsDAO {
	
	/**
	 * Find Stations by stationName
	 * 
	 * @param stationName
	 * @return
	 */
	Stations findByStations(String stationName);

	
}

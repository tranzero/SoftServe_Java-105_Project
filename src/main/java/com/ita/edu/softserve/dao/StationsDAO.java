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
	 * Find all stations.
	 * @author Роман
	 */
	List<Stations> findAllStations();
	
	/**
	 * Find Stations by stationName
	 * 
	 * @param stationName
	 * @return
	 */
	Stations findByStations(String stationName);

	
}

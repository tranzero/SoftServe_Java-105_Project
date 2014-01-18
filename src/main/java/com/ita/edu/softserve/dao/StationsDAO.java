package com.ita.edu.softserve.dao;

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

	/**
	 * Save new station
	 * 
	 * @param station
	 */
	void save(Stations station);

	/**
	 * Remove a station
	 * 
	 * @param station
	 */
	void remove(Stations station);

	/**
	 * Update a sation
	 * 
	 * @param station
	 * @return
	 */
	Stations update(Stations station);
	//
}

package com.ita.edu.softserve.service;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * Manager interface for stations.
 * 
 * @author Roman
 */
public interface StationsManager {
	
	List<Stations> findByStations(String stationName);

	/**
	 * Gets list of all Stations.
	 */
	List<Stations> findAllStations();
	
	/**
	 * Gets Station by ID.
	 */
	Stations findStationsById(int id);

	/**
	 * Saves station.
	 */
	void saveStations(Stations... station);

	/**
	 * Removes station.
	 */
	void removeStations(Stations... station);

	/**
	 * Updates station.
	 */
	List<Stations> updateStations(Stations... station);
}

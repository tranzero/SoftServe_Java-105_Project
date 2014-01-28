package com.ita.edu.softserve.service;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * Service interface for stations.
 * 
 * @author Roman
 */
public interface StationsManager extends BaseManager  {

	/**
	 * Get list of all Stations.
	 */
	List<Stations> findAllStations();
	
	/**
	 * Get Station by ID.
	 */
	Stations findStationsById(int id);

	/**
	 * Save station
	 */
	void saveStations(Stations... station);

	/**
	 * Remove station
	 */
	void removeStations(Stations... station);

	/**
	 * Update station
	 */
	List<Stations> updateStations(Stations... station);
}

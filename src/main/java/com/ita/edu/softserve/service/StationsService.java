package com.ita.edu.softserve.service;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * Service interface for stations.
 * 
 * @author Roman
 */
public interface StationsService {

	/**
	 * Get list of all Stations.
	 */
	List<Stations> findAllStations();
	
	/**
	 * Get Station by ID.
	 */
	Stations findStationsById(int id);

	/**
	 * Save entities
	 */
	void saveStations(Stations... entities);

	/**
	 * Remove entities
	 */
	void removeStations(Stations... entities);

	/**
	 * Update entities
	 */
	List<Stations> updateStations(Stations... entities);
}

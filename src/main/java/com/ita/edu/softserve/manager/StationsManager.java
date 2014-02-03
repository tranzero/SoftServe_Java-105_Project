package com.ita.edu.softserve.manager;

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
	void removeStations(Integer stationId);

	/**
	 * Update stations
	 */
	List<Stations> updateStations(Stations... station);

	/**
	 * Update exact station by Id
	 */
	void updateStation(Integer stationId, String stationCode, String stationName);
	/**
	 * Return all stations in certain line
	 */
	List<Stations> getStationsOnCertainLine(String lineName);
}

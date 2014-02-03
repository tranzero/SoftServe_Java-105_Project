package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * Service interface for stations.
 * 
 * @author admin
 */
public interface StationsManager extends BaseManager {

	/**
	 * Get list of all Stations.
	 */
	List<Stations> findAllStations();

	/**
	 * Get Station by ID.
	 */
	Stations findStationsById(int id);

	/**
	 * 
	 * Save station by parametrs
	 * 
	 */
	void createStation(String stationCode, String stationName);

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
	void editStation(Integer stationId, String stationCode, String stationName);
	
	public List<Stations> getStationsOnCertainLine(String lineName);

}
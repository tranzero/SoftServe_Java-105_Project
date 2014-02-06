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
	 * Gets list of all Stations.
	 */
	List<Stations> findAllStations();

	/**
	 * Gets Station by ID.
	 */
	Stations findStationsById(Integer id);

	/**
	 * 
	 * Saves station by parameters.
	 * 
	 */
	void createStation(String stationCode, String stationName);

	/**
	 * Saves station.
	 */
	void saveStations(Stations... station);

	/**
	 * Removes station.
	 */
	void removeStations(Integer stationId);

	/**
	 * Updates stations.
	 */
	List<Stations> updateStations(Stations... station);

	/**
	 * Updates exact station by Id.
	 */
	void editStation(Integer stationId, String stationCode, String stationName);
	
	/**
	 * Finds station by its Name.
	 */
	public Stations findByStationName(String stationName); 
	
	public List<Stations> getStationsOnCertainLine(String lineName);

}
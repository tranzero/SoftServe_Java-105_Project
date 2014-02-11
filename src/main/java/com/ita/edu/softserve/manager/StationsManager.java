package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;

/**
 * Service interface for stations.
 * 
 * @author admin
 */
public interface StationsManager extends BaseManager {

	/**
	 * Gets list of all Stations.
	 * @throws StationManagerException 
	 */
	List<Stations> findAllStations() throws StationManagerException;

	/**
	 * Gets Station by ID.
	 * @throws StationManagerException 
	 */
	Stations findStationsById(Integer id) throws StationManagerException;

	/**
	 * 
	 * Saves station by parameters.
	 * @throws StationManagerException 
	 * 
	 */
	void createStation(String stationCode, String stationName) throws StationManagerException;

	/**
	 * Removes station.
	 * @throws StationManagerException 
	 */
	void removeStations(Integer stationId) throws StationManagerException;

	/**
	 * Updates exact station by Id.
	 * @throws StationManagerException 
	 */
	void editStation(Integer stationId, String stationCode, String stationName) throws StationManagerException;
	
	/**
	 * Finds station by Name.
	 * @throws StationManagerException 
	 */
	public Stations findByStationName(String stationName) throws StationManagerException; 
	
	public List<Stations> getStationsOnCertainLine(String lineName);

	List<Stations> getStationsNotOnCertainLine(String lineName);
	
	

}
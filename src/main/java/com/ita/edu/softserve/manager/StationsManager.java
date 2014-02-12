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
	
	/**
	 * Finds stations on certain line by line name.
	 * @throws StationManagerException 
	 */
	public List<Stations> getStationsOnCertainLine(String lineName) throws StationManagerException;

	/**
	 * Finds stations which not exist on certain line by line name.
	 * @throws StationManagerException 
	 */
	List<Stations> getStationsNotOnCertainLine(String lineName) throws StationManagerException;

	List<Stations> getStationsForLimit(int firstElement, int count);

	List<Stations> getStationsForPage(int pageNumber, int count);

	long getStationsListCount();
	
	

}
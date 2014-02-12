package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * @author iryna
 */
public interface StationsDAO extends AbstractDAOIface<Stations> {

	/**
	 * Find Stations by stationName
	 */
	public List<Stations> findByStations(String stationName);
	
	/**
	 * Find Stations by name of the station
	 */
	public Stations findByName(String stationName); 
	
	public List<Stations> findByLineName(String lineName);

	/**
	 * Saves a Station to database if not exist or update existing one.
	 */
	public void saveOrUpdate(Stations entity);

	/**
	 * Method for pagging.
	 */
	long getStationsListCount();

	List<Stations> getStationsForLimits(int firstElement, int count);

}

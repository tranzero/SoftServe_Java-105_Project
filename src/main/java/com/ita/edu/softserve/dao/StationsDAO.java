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
	List<Stations> findByStations(String stationName);
	
	/**
	 * Find Stations by name of the station
	 */
	public Stations findByName(String stationName); 
	public List<Stations> findByLineName(String lineName);

}

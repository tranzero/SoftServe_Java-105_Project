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

}

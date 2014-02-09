package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;

/**
 * @author Roman
 */
public interface TransportsDao extends AbstractDAOIface<Transports> {

	/**
	 * Finds Transport by route id.
	 */
	Transports findByRouteId(int id);
	
	/**
	 * Saves a Transport to database if not exist or update existing one.
	 */
	void saveOrUpdate(Transports entity);
	
	public List<TransportTravel> findByTwoStations(String stationName1,
			String stationName2);
}

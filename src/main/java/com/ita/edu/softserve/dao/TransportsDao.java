package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
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
	 * Saves a Transport into the Transports table if not exist or updates existing one.
	 */
	void saveOrUpdate(Transports entity);
	
	
	List<Transports> getTransportsForLimits(int firstElement, int count);
	
	long getTransportsListCount();
	
	/**
	 * Get transport by two stations
	 * 
	 * @param stationName1
	 * @param stationName2
	 * @return
	 */
	public List<TransportTravel> findByTwoStations(String stationName1,
			String stationName2);
	
	public List<TransportTravel> getTransportByTwoStForLimits(String stationName1,
			String stationName2, int firstElement, int count, int orderBy);
	
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2);
	
}

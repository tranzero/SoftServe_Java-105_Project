package com.ita.edu.softserve.manager;

import java.text.ParseException;
import java.util.List;

import com.ita.edu.softserve.entity.Transports;

/**
 * @author Roman
 */
public interface TransportsManager extends BaseManager {

	/**
	 * Finds transports by ID.
	 */
	Transports findTransportsById(int id);

	/**
	 * Saves transports.
	 */
	void saveTransports(Transports... entities);

	/**
	 * Removes transports.
	 */
	void removeTransports(Transports... entities);

	/**
	 * Updates transports.
	 */
	List<Transports> updateTransports(Transports... entities);

	/**
	 * Gets all transports.
	 */
	List<Transports> getAllTransports();
	
	void saveOrUpdateTransport(final Transports entity);

	/**
	 * Saves new transport into database.
	 */
	Transports createTransport(String transportCode, String startTime, String routes);
	
	/**
	 * Get transport by two stations in certain order
	 */
	List<Transports> getTransportByTwoStations(String stationName1,
			String stationName2);
}

package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Trips;

public interface TripsDao extends AbstractDAOIface<Trips> {

	/**
	 * Searches trip by transport ID
	 * 
	 * @param id
	 *            transport ID
	 * @return List of found trips
	 */
	List<Trips> findByTransportId(int id);

	/**
	 * Searches all trips with given limit of amount
	 * 
	 * @param firstElement
	 *            Starting element for result list
	 * @param count
	 *            capacity of result list
	 * @return List of trips according given limits
	 */

	List<Trips> getTripsForLimits(int firstElement, int count);

	/**
	 * Returns count of all trips
	 * 
	 * @return Count of all trips
	 */

	long getTripsListCount();

	/**
	 * Saves a Trip to database if not exist or update existing one.
	 */
	void saveOrUpdate(Trips entity);

}

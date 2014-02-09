package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Trips;

public interface TripsDao extends AbstractDAOIface<Trips>{
	
	/**
	 * Find trip by transport ID
	 * @param id transport ID
	 * @return List of found trips
	 */
	List<Trips> findByTransportId(int id);
	
	List<Trips> getTripsForLimits(int firstElement, int count);
	
	long getTripsListCount();
	
	/**
	 * Saves a Trip to database if not exist or update existing one.
	 */	
	void saveOrUpdate (Trips entity);

}

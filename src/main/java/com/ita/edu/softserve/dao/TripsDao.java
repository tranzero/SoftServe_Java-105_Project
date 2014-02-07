package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Trips;

public interface TripsDao extends AbstractDAOIface<Trips>{
	
	/**
	 * Find trip by transport ID
	 * @param id transport ID
	 * @return 
	 */
	List<Trips> findByTransportId(int id);
	
	void saveOrUpdate (Trips entity);

}

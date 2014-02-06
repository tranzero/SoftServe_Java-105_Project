package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Transports;

/**
 * @author Roman
 */
public interface TransportsDao extends AbstractDAOIface<Transports> {

	/**
	 * Finds Transports by route id.
	 */
	Transports findByRouteId(int id);
	
	/**
	 * Saves a Transport to database if not exist or update existing one.
	 */
	void saveOrUpdate(Transports entity);
}

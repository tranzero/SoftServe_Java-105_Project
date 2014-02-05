package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Transports;

/**
 * @author Roman
 */
public interface TransportsDao extends AbstractDAOIface<Transports> {

	/**
	 * Find Transports by route id
	 */
	Transports findByRouteId(int id);
	
	void saveOrUpdate(final Transports entity);
}

package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 * 
 */
public interface RoutesDAO {
	/**
	 * Find Routes by routeCode
	 * 
	 * @param routeCode
	 * @return
	 */
	Routes findByCode(String routeCode);

	/**
	 * Save a new route
	 * 
	 * @param route
	 */
	void save(Routes route);

	/**
	 * Remove a route
	 * 
	 * @param route
	 */
	void remove(Routes route);

	/**
	 * Update a route
	 * 
	 * @param route
	 * @return
	 */
	Routes update(Routes route);

	//
}

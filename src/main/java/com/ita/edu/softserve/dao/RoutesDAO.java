package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 * 
 */
public interface RoutesDAO {
	//
	Routes findByCode(String routeCode);

	void save(Routes route);

	void remove(Routes route);

	Routes update(Routes route);

	//
}

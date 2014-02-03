package com.ita.edu.softserve.dao;


import com.ita.edu.softserve.entity.Stops;

/**
 * 
 * @author iryna
 * 
 */
public interface StopsDAO extends AbstractDAOIface<Stops>{
	
	Stops findByStationsOnLineId(int id);
	
}

package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.StationsOnLine;


/**
 * 
 * @author iryna
 * 
 */
public interface StationsOnLineDAO {
	
	public List<StationsOnLine> findByStationId(int id);
	
}

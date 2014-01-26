package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
 * @author Roman
 * 
 */
public interface StationsDAO {

	/**
	 * Find Stations by stationName
	 */
	List<Stations> findByStations(String stationName);

	Stations findById(int id);

	void save(Stations... entities);

	void remove(Stations... entities);

	List<Stations> update(Stations[] entities);

}

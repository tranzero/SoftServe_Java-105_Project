package com.ita.edu.softserve.daoiface;

import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
 * 
 */
public interface StationsDAO {
	//
	Stations findByStations(String stationName);

	void save(Stations station);

	void remove(Stations station);

	Stations update(Stations station);
	//
}

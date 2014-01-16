package com.ita.edu.softserve.daoiface;

import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * 
 * @author iryna
 *
 */
public interface StationsOnLineDAO {
	//
	StationsOnLine findByID(int id);

    void save(StationsOnLine stationsOnLine);

    void remove(StationsOnLine stationsOnLine);

    StationsOnLine update(StationsOnLine stationsOnLine);
    
    //
}

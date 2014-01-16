package com.ita.edu.softserve.daoiface;

import com.ita.edu.softserve.entity.Lines;


/**
 * 
 * @author iryna
 *
 */
public interface LinesDAO {
	//
	Lines findByName(String lineName);

    void save(Lines line);

    void remove(Lines line);

    Lines update(Lines line);
    
    //
}

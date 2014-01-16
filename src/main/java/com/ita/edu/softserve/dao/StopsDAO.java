package com.ita.edu.softserve.dao;

import com.ita.edu.softserve.entity.Stops;
import com.ita.edu.softserve.entity.Users;

/**
 * 
 * @author iryna
 * 
 */
public interface StopsDAO {
	//+

	Stops findByID(int id);

	void save(Stops stop);

	void remove(Stops stop);

	Stops update(Stops stop);
	//

}

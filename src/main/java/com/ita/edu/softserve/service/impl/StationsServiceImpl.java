package com.ita.edu.softserve.service.impl;

import java.util.List;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.StationsService;

public class StationsServiceImpl implements StationsService {

	/**
	 * Obtain list of all stations.
	 * @author Роман
	 */
	public List<Stations> findAllStation() {
		 return new StationsDAOImpl().findAllStations();
	}

}

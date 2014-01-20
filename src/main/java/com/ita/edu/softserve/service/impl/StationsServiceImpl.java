package com.ita.edu.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.StationsService;

public class StationsServiceImpl implements StationsService {
	
	@Autowired
	private StationsDAOImpl stationDao;
	
	/**
	 * Obtain list of all stations.
	 * @author Roman
	 */
	@Transactional
	@Override
	public List<Stations> findAllStations() {
		 return stationDao.findAllStations();
	}

}

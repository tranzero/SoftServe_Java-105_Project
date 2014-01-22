package com.ita.edu.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.StationsService;

/**
 * This is station service class.
 * @author Роман
 * 
 */
@Service
public class StationsServiceImpl implements StationsService {
	/**
	 * 
	 */
	@Autowired
	private StationsDAOImpl stationDao;

	/**
	 * Constructor without arguments.
	 */
	public StationsServiceImpl() {
	}

	/**
	 * Constructor with one argument.
	 * @param stationDao
	 */
	public StationsServiceImpl(StationsDAOImpl stationDao) {
		this.stationDao = stationDao;
	}

	/**
	 * Return list of all stations.
	 * 
	 * @author Roman
	 */
	@Transactional
	@Override
	public List<Stations> findAllStations() {
		return stationDao.getAllEntities();
	}

}

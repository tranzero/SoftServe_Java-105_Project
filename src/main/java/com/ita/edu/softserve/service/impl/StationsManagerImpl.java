package com.ita.edu.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.StationsManager;

/**
 * This is station manager class.
 * 
 * @author Roman
 * 
 */
@Service("stationsManager")
public class StationsManagerImpl implements StationsManager {

	/**
	 * Object to get access to DAO layer.
	 */
	@Autowired
	private StationsDAO stationDao;

	/**
	 * Constructor without arguments.
	 */
	public StationsManagerImpl() {
	}

	/**
	 * @return list of all stations.
	 */
	@Override
	public List<Stations> findByStations(String stationName) {

		return stationDao.findByStations(stationName);
	}

	/**
	 * @return list of all stations.
	 */
	@Transactional
	@Override
	public List<Stations> findAllStations() {

		return stationDao.getAllEntities();
	}

	/**
	 * @return Station found by ID.
	 */
	@Override
	public Stations findStationsById(int id) {

		return stationDao.findById(id);
	}

	/**
	 * Saves Stations in database.
	 */
	@Override
	public void saveStations(Stations... station) {

		for (Stations stop : station)
			stationDao.save(stop);
	}

	/**
	 * Removes Stations from database.
	 */
	@Override
	public void removeStations(Stations... station) {

		for (Stations stop : station)
			stationDao.remove(stop);
	}

	/**
	 * Updates database and get list of all stations.
	 * 
	 * @return list of all stations.
	 */
	@Override
	public List<Stations> updateStations(Stations... station) {
		List<Stations> stationUpdateResult = new ArrayList<Stations>();
		for (Stations stop : station) {
			stationUpdateResult.add((Stations) stationDao.update(stop));
		}

		return stationUpdateResult;
	}
}

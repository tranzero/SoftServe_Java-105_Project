package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.StationsManager;

/**
 * This is station service class.
 * 
 * @author Roman
 * 
 */
@Service("stationsService")
public class StationsManagerImpl implements StationsManager {

	/**
	 * Class to get access to DAO layer.
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
	 * 
	 * @author Roman
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Stations> findAllStations() {
		return ((AbstractDAO<Stations>) stationDao).getAllEntities();
	}

	/**
	 * @return Station found by ID.
	 */
	@Transactional
	@Override
	public Stations findStationsById(int id) {
		return stationDao.findById(id);
	}

	/**
	 * Save Stations in database.
	 */
	@Transactional
	@Override
	public void saveStations(Stations... station) {

		for (Stations stop : station)
			stationDao.save(stop);
	}

	/**
	 * Remove Stations from database.
	 */
	@Transactional
	@Override
	public void removeStations(Stations... station) {

		for (Stations stop : station)
			stationDao.remove(stop);
	}

	/**
	 * Update database and get list of all stations.
	 * 
	 * @return list of all stations.
	 */
	@Transactional
	@Override
	public List<Stations> updateStations(Stations... station) {
		List<Stations> stationUpdateResult = new ArrayList<Stations>();
		for (Stations stop : station) {
			stationUpdateResult.add((Stations) stationDao.update(stop));
		}
		return stationUpdateResult;

	}

	public static StationsManager getInstance() {
		return ManagerFactory.getManager(StationsManager.class);
	}
}

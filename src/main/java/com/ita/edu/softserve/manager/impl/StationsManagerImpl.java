package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.StationsManager;

/**
 * This is station service class.
 * 
 * @author admin
 * 
 */
@Service("stationsService")
public class StationsManagerImpl implements StationsManager {

	private static final Logger LOGGER = Logger.getLogger(Stations.class);

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
	 */
	@Transactional
	@Override
	public List<Stations> findAllStations() {
		return stationDao.getAllEntities();
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
	 * Save Station in database using parametrs.
	 * 
	 * @param stationCode
	 * 
	 * @param stationName
	 */
	@Transactional
	@Override
	public void createStation(String stationCode, String stationName) {

		Stations station = new Stations(stationCode, stationName);
		stationDao.save(station);
	}

	/**
	 * Save Stations in database.
	 */
	@Transactional
	@Override
	public void saveStations(Stations... stations) {

		for (Stations station : stations)
			stationDao.save(station);
	}

	/**
	 * Remove Stations by Id from database.
	 */
	@Transactional
	@Override
	public void removeStations(Integer stationId) {

		Stations station = null;
		try {
			station = (Stations) stationDao.findById(stationId);
			stationDao.remove(station);
		} catch (NoResultException e) {
			LOGGER.error("No such station!", e);
		}
	}

	/**
	 * Update Stations table in DB and get list of all stations.
	 * 
	 * @return list of all stations.
	 */
	@Transactional
	@Override
	public List<Stations> updateStations(Stations... stations) {
		List<Stations> stationUpdateResult = new ArrayList<Stations>();

		for (Stations station : stations) {
			stationUpdateResult.add((Stations) stationDao.update(station));
		}
		return stationUpdateResult;
	}

	@Override
	@Transactional
	public void editStation(Integer stationId, String stationCode,
			String stationName) {

		Stations station = stationDao.findById(stationId);

		station.setStationCode(stationCode);
		station.setStationName(stationName);

		stationDao.update(station);

	}

	public static StationsManager getInstance() {
		return ManagerFactory.getManager(StationsManager.class);
	}
}

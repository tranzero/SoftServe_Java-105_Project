package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;
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

	private static final Logger LOGGER = Logger
			.getLogger(StationManagerException.class);

	/**
	 * Class to get access to DAO layer.
	 */
	@Autowired
	private StationsDAO stationDao;

	@Autowired
	private LinesDAO lineDao;

	@Autowired
	private StationsOnLineDAO stlDao;

	/**
	 * Constructor without arguments.
	 */
	public StationsManagerImpl() {
	}

	/**
	 * @return list of all stations.
	 * @throws StationManagerException
	 * 
	 */
	@Transactional
	@Override
	public List<Stations> findAllStations() throws StationManagerException {
		try {
			return stationDao.getAllEntities();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new StationManagerException(
					"Could not find List of Stations", e);
		}
	}

	/**
	 * @return Station found by ID.
	 * @throws StationManagerException
	 */
	@Transactional
	@Override
	public Stations findStationsById(Integer id) throws StationManagerException {
		try {
			return stationDao.findById(id);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new StationManagerException(
					"Could not find Station by this Id", e);
		}
	}

	/**
	 * Saves Station in database using parameters.
	 * 
	 * @param stationCode
	 * 
	 * @param stationName
	 * @throws StationManagerException
	 */
	@Transactional
	@Override
	public void createStation(String stationCode, String stationName)
			throws StationManagerException {

		try {
			Stations station = new Stations(stationCode, stationName);
			stationDao.save(station);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new StationManagerException("Could not create Station", e);
		}
	}

	/**
	 * Removes Stations by Id from database.
	 * 
	 * @throws StationManagerException
	 */
	@Transactional
	@Override
	public void removeStations(Integer stationId)
			throws StationManagerException {

		Stations station = null;
		try {
			station = (Stations) stationDao.findById(stationId);
			stationDao.remove(station);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new StationManagerException("Could not remove Station", e);
		}
	}

	/**
	 * Updates exact station by Id.
	 * 
	 * @throws StationManagerException
	 */
	@Override
	@Transactional
	public void editStation(Integer stationId, String stationCode,
			String stationName) throws StationManagerException {

		try {
			Stations station = stationDao.findById(stationId);

			station.setStationCode(stationCode);
			station.setStationName(stationName);
			stationDao.update(station);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new StationManagerException("Could not update Station", e);
		}

	}

	/**
	 * Finds station by Name.
	 * 
	 * @throws StationManagerException
	 */
	public Stations findByStationName(String stationName)
			throws StationManagerException {

		try {
			return stationDao.findByName(stationName);
		} catch (Exception e) {
			throw new StationManagerException("Could not find Station", e);
		}
	}

	public static StationsManager getInstance() {
		return ManagerFactory.getManager(StationsManager.class);
	}

	public List<Stations> getStationsOnCertainLine(String lineName) {

		return stationDao.findByLineName(lineName);

	}
}
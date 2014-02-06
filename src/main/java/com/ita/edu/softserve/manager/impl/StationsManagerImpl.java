package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
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
	public Stations findStationsById(Integer id) {
		return stationDao.findById(id);
	}

	/**
	 * Saves Station in database using parameters.
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
	 * Saves Stations in database.
	 */
	@Transactional
	@Override
	public void saveStations(Stations... stations) {

		for (Stations station : stations)
			stationDao.save(station);
	}

	/**
	 * Removes Stations by Id from database.
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
	 * Updates Stations table in DB and get list of all stations.
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
	
	public Stations findByStationName(String stationName) {
		Stations station = null;
		try {
			 station = stationDao.findByName(stationName);
		} catch (NoResultException e) {
			LOGGER.error("No such station!", e);
		}
		return station;
	} 

	public static StationsManager getInstance() {
		return ManagerFactory.getManager(StationsManager.class);
		}
	
	public List<Stations> getStationsOnCertainLine(String lineName){
			Lines line =lineDao.findByName(lineName);
			List<StationsOnLine> stlList = stlDao.findByLineId(line.getLineId());
			List<Stations> stationsList = new ArrayList<Stations>();
			for (StationsOnLine stl : stlList){
				stationsList.add(stl.getStationId());
			}
			return stationsList;
		
		
	}
}
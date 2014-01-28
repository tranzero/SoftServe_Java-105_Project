package com.ita.edu.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.ManagerFactory;
import com.ita.edu.softserve.service.StationsManager;

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
	 * Constructor with one argument.
	 * 
	 * @param stationDao
	 */
	public StationsManagerImpl(StationsDAO stationDao) {
		this.stationDao = stationDao;
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
	@Override
	public Stations findStationsById(int id) {
		return stationDao.findById(id);
	}
	/**
	 *  Save Stations in database.
	 */
	@Override
	public void saveStations(Stations... station) {
		
		for (Stations stop: station)
		stationDao.save(stop);
	}
	
	/**
	 *  Remove Stations from database.
	 */
	@Override
	public void removeStations(Stations... station) {
		
		for (Stations stop: station)
		stationDao.remove(stop);
	}
	/**
	 * Update database and get list of all stations.
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
	public static StationsManager getInstance() {
		return ManagerFactory.getManager(StationsManager.class); 
	}
}

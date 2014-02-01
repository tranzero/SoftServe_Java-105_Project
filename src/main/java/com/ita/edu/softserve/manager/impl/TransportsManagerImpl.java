package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;

/**
 * This is transports manager class.
 * 
 * @author Roman
 * 
 */
@Service("transportsManager")
public class TransportsManagerImpl implements TransportsManager {

	/**
	 * Object to get access to DAO layer.
	 */
	@Autowired
	public TransportsDao tripsDao;

	/**
	 * Constructor without arguments.
	 */
	public TransportsManagerImpl() {
		super();
	}

	/**
	 * @return transport found by Id.
	 * 
	 */
	@Transactional
	@Override
	public Transports findTransportsById(int id) {
		return tripsDao.findById(id);
	}

	/**
	 * Saves Transports in database.
	 */
	@Transactional
	@Override
	public void saveTransports(Transports... entities) {
		tripsDao.save(entities);
	}

	/**
	 * Removes Transports from database.
	 */
	@Transactional
	@Override
	public void removeTransports(Transports... entities) {
		tripsDao.remove(entities);
	}

	/**
	 * Updates database and get list of all Transports.
	 * 
	 * @return list of all stations.
	 */
	@Transactional
	@Override
	public List<Transports> updateTransports(Transports... entities) {
		return tripsDao.update(entities);
	}

	/**
	 * Gets list of all transports.
	 * 
	 * @return list of all transports.
	 */
	@Transactional
	@Override
	public List<Transports> getAllTransports() {
		return tripsDao.getAllEntities();
	}
}

package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.TripsDao;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.TripsManager;

@Service("tripsManager")
public class TripsManagerImpl implements TripsManager {
	
	@Autowired
	private TripsDao tripsDao;
	
	public TripsManagerImpl(){
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Trips> getAllTrips() {
		return tripsDao.getAllEntities();
	}
	
	@Transactional(readOnly = true)
	@Override
	public long getTripsListCount() {
		return tripsDao.getTripsListCount();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForPage(int pageNumber, int count) {
		return getTripsForLimit((pageNumber-1)*count, count);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForLimit(int firstElement, int count) {
		return tripsDao.getTripsForLimits(firstElement, count);
	}
}

package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.ResponsesDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.ResponsesManager;
import com.ita.edu.softserve.manager.TransportsManager;

@Service("responsesManager")
public class ResponsesManagerImpl implements ResponsesManager {
	
	/**
	 * Gets access to Responses DAO.
	 */
	@Autowired
	public ResponsesDAO responsesDao;
	
	@Override
	public List<Responses> getResponsesByRouteId(Integer routeId) {
		return responsesDao.findResponsesByRouteId(routeId);
	}
	
	@Override
	public List<Responses> getResponsesByTripId(Integer tripId) {
		return responsesDao.findResponsesByTripId(tripId);
	}
	
	@Override
	public List<Responses> getResponsesByTransportId(Integer transportId) {
		return responsesDao.findResponsesByTranportId(transportId);
	}
	
	public static ResponsesManager getInstance() {
		return ManagerFactory.getManager(ResponsesManager.class);
	}

}

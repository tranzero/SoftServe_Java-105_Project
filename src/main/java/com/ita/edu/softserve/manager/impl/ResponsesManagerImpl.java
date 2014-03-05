package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.ResponsesDAO;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.ResponsesManager;

@Service("responsesManager")
public class ResponsesManagerImpl implements ResponsesManager {

	/**
	 * Gets access to Responses DAO.
	 */
	@Autowired
	public ResponsesDAO responsesDao;

	/**
	 * Gets access to Users DAO.
	 */
	@Autowired
	public UsersDAO usersDao;

	/**
	 * Gets access to Trips DAO.
	 */
	@Autowired
	public TripsDAO tripsDao;

	/**
	 * Finds Responses by route id.
	 * 
	 * @param routeId
	 *            the Id to find responses by.
	 * @return responses finded by routeId
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Responses> getResponsesByRouteId(Integer routeId) {
		return responsesDao.findResponsesByRouteId(routeId);
	}

	/**
	 * Finds Responses by trip id.
	 * 
	 * @param tripId
	 *            the Id to find responses by.
	 * @return responses finded by tripId
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Responses> getResponsesByTripId(Integer tripId) {
		return responsesDao.findResponsesByTripId(tripId);
	}

	/**
	 * Finds Responses by transport id.
	 * 
	 * @param transportId
	 *            the Id to find responses by.
	 * @return responses finded by transportId
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Responses> getResponsesByTransportId(Integer transportId) {
		return responsesDao.findResponsesByTranportId(transportId);
	}

	public static ResponsesManager getInstance() {
		return ManagerFactory.getManager(ResponsesManager.class);
	}

	/**
	 * 
	 * @param userId
	 *            - Id of user, that adds response
	 * @param tripId
	 *            - that response is for
	 * @param responseText
	 *            - text of response
	 */
	@Transactional
	@Override
	public void addResponse(Integer userId, Integer tripId, String responseText) {
		
		Users user = usersDao.findById(userId);
		Trips trip = tripsDao.findById(tripId);
		
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		Responses response = new Responses(user, trip, responseText, sqlDate);
		
		responsesDao.save(response);
	}

	/**
	 * 
	 * @param responseId
	 *            - Id of response to delete
	 */
	@Transactional
	@Override
	public void delResponse(Integer responseId) {
		Responses response = responsesDao.findById(responseId);
		responsesDao.remove(response);
	}

	/**
	 * Finds `unchecked` Responses
	 * 
	 * @return `unchecked` Responses
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Responses> getUncheckedResponses() {
		return responsesDao.findUncheckedResponses();
	}
	
	/**
	 * 
	 * @param responseId
	 *            - Id of response to mark as `checked`
	 */
	@Transactional
	@Override
	public void markAsChecked(Integer responseId) {
		Responses response = responsesDao.findById(responseId);
		response.setChecked(true);
		responsesDao.update(response);
	}
}

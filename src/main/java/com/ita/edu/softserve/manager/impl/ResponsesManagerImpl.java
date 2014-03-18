package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.ResponsesDAO;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.dao.UsersDAO;
import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.exception.ResponsesManagerException;
import com.ita.edu.softserve.exception.TransprtsManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.ResponsesManager;

@Service("responsesManager")
public class ResponsesManagerImpl implements ResponsesManager {

	private static final Logger LOGGER = Logger
			.getLogger(ResponsesManagerImpl.class);

	private static String ENTITY_NAME = Responses.class.getSimpleName();

	private static final String ADD_MESSAGE = " was added to DB by userId = ";
	private static final String REMOVE_MESSAGE = " was removed from DB responseId = ";
	private static final String MARKED_MESSAGE = " was marked as `checked` responseId = ";

	private static final String COULD_NOT_FIND_RESPONSES_BY_ROUTEID = "Could not find Responses by routeId";
	private static final String COULD_NOT_FIND_RESPONSES_BY_TRIPID = "Could not find Responses by tripId";
	private static final String COULD_NOT_FIND_RESPONSES_BY_TRANSPORTID = "Could not find Responses by transportId";
	private static final String COULD_NOT_ADD_RESPONSE = "Could not add Response to DB";
	private static final String COULD_NOT_DEL_RESPONSE = "Could not delete Response to DB";
	private static final String COULD_NOT_FIND_UNCHECKED_RESPONSES = "Could not find `unchecked` Responses";
	private static final String COULD_NOT_MARK_RESPONSE_AS_CHECKED = "Could not mark response as `checked`";

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
		try {

			return responsesDao.findResponsesByRouteId(routeId);

		} catch (RuntimeException e) {
			RuntimeException ex = new ResponsesManagerException(
					COULD_NOT_FIND_RESPONSES_BY_ROUTEID, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
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
		try {

			return responsesDao.findResponsesByTripId(tripId);

		} catch (RuntimeException e) {
			RuntimeException ex = new ResponsesManagerException(
					COULD_NOT_FIND_RESPONSES_BY_TRIPID, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
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
		try {
			return responsesDao.findResponsesByTranportId(transportId);
		} catch (RuntimeException e) {
			RuntimeException ex = new ResponsesManagerException(
					COULD_NOT_FIND_RESPONSES_BY_TRANSPORTID, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
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

		try {
			Users user = usersDao.findById(userId);
			Trips trip = tripsDao.findById(tripId);

			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			Responses response = new Responses(user, trip, responseText,
					sqlDate);

			responsesDao.save(response);
			
			LOGGER.info(ENTITY_NAME + ADD_MESSAGE + userId);
		} catch (RuntimeException e) {
			RuntimeException ex = new ResponsesManagerException(
					COULD_NOT_ADD_RESPONSE, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * 
	 * @param responseId
	 *            - Id of response to delete
	 */
	@Transactional
	@Override
	public void delResponse(Integer responseId) {
		try {
			Responses response = responsesDao.findById(responseId);
			responsesDao.remove(response);
			
			LOGGER.info(ENTITY_NAME + REMOVE_MESSAGE + responseId);
		} catch (RuntimeException e) {
			RuntimeException ex = new ResponsesManagerException(
					COULD_NOT_DEL_RESPONSE, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Finds `unchecked` Responses
	 * 
	 * @return `unchecked` Responses
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Responses> getUncheckedResponses() {
		try {
			return responsesDao.findUncheckedResponses();
		} catch (RuntimeException e) {
			RuntimeException ex = new ResponsesManagerException(
					COULD_NOT_FIND_UNCHECKED_RESPONSES, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * 
	 * @param responseId
	 *            - Id of response to mark as `checked`
	 */
	@Transactional
	@Override
	public void markAsChecked(Integer responseId) {
		try {
			Responses response = responsesDao.findById(responseId);
			response.setChecked(true);
			responsesDao.update(response);
			
			LOGGER.info(ENTITY_NAME + MARKED_MESSAGE + responseId);
		} catch (RuntimeException e) {
			RuntimeException ex = new ResponsesManagerException(
					COULD_NOT_MARK_RESPONSE_AS_CHECKED, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}
}

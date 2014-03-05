package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Responses;

public interface ResponsesManager extends BaseManager {

	/**
	 * Finds Responses by route id.
	 * 
	 * @param routeId
	 *            the Id to find responses by.
	 * @return responses finded by routeId
	 */
	public List<Responses> getResponsesByRouteId(Integer routeId);

	/**
	 * Finds Responses by trip id.
	 * 
	 * @param tripId
	 *            the Id to find responses by.
	 * @return responses finded by tripId
	 */
	public List<Responses> getResponsesByTripId(Integer tripId);

	/**
	 * Finds Responses by transport id.
	 * 
	 * @param transportId
	 *            the Id to find responses by.
	 * @return responses finded by transportId
	 */
	public List<Responses> getResponsesByTransportId(Integer transportId);

	/**
	 * 
	 * @param userId
	 *            - Id of user, that adds response
	 * @param tripId
	 *            - that response is for
	 * @param responseText
	 *            - text of response
	 */
	public void addResponse(Integer userId, Integer tripId, String responseText);

	/**
	 * 
	 * @param responseId
	 *            - Id of response to delete
	 */
	public void delResponse(Integer responseId);

	/**
	 * Finds `unchecked` Responses
	 * 
	 * @return `unchecked` Responses
	 */
	public List<Responses> getUncheckedResponses();

	/**
	 * 
	 * @param responseId
	 *            - Id of response to mark as `checked`
	 */
	void markAsChecked(Integer responseId);
}

package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Responses;

/**
 * @author yuraloga
 */
public interface ResponsesDAO extends AbstractDAOIface<Responses>{
	
	/**
	 * Finds Responses by route id.
	 * 
	 * @param routeId
	 *            the Id to find responses by.
	 * @return responses finded by routeId
	 */
	List<Responses> findResponsesByRouteId(Integer routeId);
	
	/**
	 * Finds Responses by trip id.
	 * 
	 * @param tripId
	 *            the Id to find responses by.
	 * @return responses finded by tripId
	 */
	List<Responses> findResponsesByTripId(Integer tripId);
	
	/**
	 * Finds Responses by transport id.
	 * 
	 * @param transportId
	 *            the Id to find responses by.
	 * @return responses finded by transportId
	 */
	List<Responses> findResponsesByTransportId(Integer transportId);
	
	/**
	 * Finds `unchecked` Responses
	 * 
	 * @return `unchecked` Responses
	 */
	List<Responses> findUncheckedResponses();	
	
}

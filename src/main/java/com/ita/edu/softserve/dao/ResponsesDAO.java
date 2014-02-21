package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Responses;

public interface ResponsesDAO extends AbstractDAOIface<Responses>{
	
	List<Responses> findResponsesByRouteId(Integer routeId);
	
	List<Responses> findResponsesByTripId(Integer tripId);
	
	List<Responses> findResponsesByTranportId(Integer transportId);	
}

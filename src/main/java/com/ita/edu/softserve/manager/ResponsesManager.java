package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Responses;

public interface ResponsesManager extends BaseManager {
	
	List<Responses> getResponsesByRouteId(Integer routeId);
	
	List<Responses> getResponsesByTripId(Integer tripId);
	
	List<Responses> getResponsesByTransportId(Integer transportId);
	
}

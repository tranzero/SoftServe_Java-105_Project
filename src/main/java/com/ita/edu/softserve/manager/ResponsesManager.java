package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Responses;

public interface ResponsesManager extends BaseManager {
	
	public List<Responses> getResponsesByRouteId(Integer routeId);
	
	public List<Responses> getResponsesByTripId(Integer tripId);
	
	public List<Responses> getResponsesByTransportId(Integer transportId);
	
	public void addResponse(Integer userId, Integer tripId, String responseText);
	
	public void delResponse(Integer responseId);
	
	public List<Responses> getUncheckedResponses();
	
	void markAsChecked(Integer responseId);
}

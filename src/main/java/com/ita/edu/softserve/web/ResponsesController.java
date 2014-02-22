package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.ResponsesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.StationsManager;

@Controller
public class ResponsesController {

	/**
	 * URL pattern that map controller
	 */
	private static final String ROUTE_RESPONSES = "/routeResponses/{routeId}";
	
	private static final String TRIP_RESPONSES = "/tripResponses/{tripId}";
	
	private static final String TRANSPORT_RESPONSES = "/transportResponses/{transportId}";

	private static final String RESPONSES_LIST = "ResponsesList";

	private static final String RESPONSES_JSP_PAGE = "responses";

	@Autowired
	private ResponsesManager responsesManager;

	@RequestMapping(value = ROUTE_RESPONSES, method = RequestMethod.GET)
	public String routeResponses(@PathVariable("routeId") Integer routeId,
			Map<String, Object> modelMap) {

		modelMap.put(RESPONSES_LIST,
				responsesManager.getResponsesByRouteId(routeId));

		return RESPONSES_JSP_PAGE;
	}

	@RequestMapping(value = TRIP_RESPONSES, method = RequestMethod.GET)
	public String tripResponses(@PathVariable("tripId") Integer tripId,
			Map<String, Object> modelMap) {

		modelMap.put(RESPONSES_LIST,
				responsesManager.getResponsesByTripId(tripId));

		return RESPONSES_JSP_PAGE;
	}

	@RequestMapping(value = TRANSPORT_RESPONSES, method = RequestMethod.GET)
	public String transportResponses(
			@PathVariable("transportId") Integer transportId,
			Map<String, Object> modelMap) {

		modelMap.put(RESPONSES_LIST,
				responsesManager.getResponsesByTransportId(transportId));

		return RESPONSES_JSP_PAGE;
	}

}

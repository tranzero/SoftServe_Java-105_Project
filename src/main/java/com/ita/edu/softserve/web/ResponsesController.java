package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.PostForMainPageManager;
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

	private static final String ADD_COMMENT = "/addComment/{tripId}";

	private static final String RESPONSES_INFO = "/responsesInfoPage";

	private static final String RESPONSES_LIST = "ResponsesList";

	private static final String RESPONSES_JSP_PAGE = "responses";

	private static final String ADD_COMMENT_JSP_PAGE = "addComment";

	private static final String RESPONSES_INFO_JSP_PAGE = "responsesInfoPage";

	private static final String COMMENT_TEXT_PARAM = "commentText";

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

	@RequestMapping(value = ADD_COMMENT, method = RequestMethod.GET)
	public String addCommentPage(Map<String, Object> modelMap) {
		return ADD_COMMENT_JSP_PAGE;
	}

	@RequestMapping(value = ADD_COMMENT, method = RequestMethod.POST)
	public String addComment(@PathVariable("tripId") Integer tripId,
			@ModelAttribute(COMMENT_TEXT_PARAM) String commentText,
			Map<String, Object> modelMap) {

		// here instead of 1, must be something like getCurrentUser{Logged}, that
		// returns Users Object or userId
		responsesManager.addResponse(1, tripId, commentText);

		return RESPONSES_INFO_JSP_PAGE;
	}

}

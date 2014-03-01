package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.ResponsesManager;
import com.ita.edu.softserve.manager.UserNameService;

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

	private static final String CHECK_RESPONSES = "/checkResponses";

	private static final String DEL_RESPONSE = "/delResponse/{responseId}";
	
	private static final String MARK_AS_CHECKED = "/markAsChecked/{responseId}";
	
	private static final String RESPONSES_LIST = "ResponsesList";

	private static final String UNCHECKED_RESPONSES_LIST = "uncheckedResponsesList";

	private static final String RESPONSES_JSP_PAGE = "responses";

	private static final String ADD_COMMENT_JSP_PAGE = "addComment";

	private static final String RESPONSES_INFO_JSP_PAGE = "responsesInfoPage";

	private static final String COMMENT_TEXT_PARAM = "commentText";
	
	private static final String TRIP_ID_PARAM = "tripId";
	
	private static final String RESPONSE_ID_PARAM = "responseId";

	private static final String CHECK_RESPONSES_JSP_PAGE = "checkResponses";

	private static final String REDIRECT_CHECK_RESPONSES_JSP_PAGE = "redirect:/checkResponses";

	@Autowired
	private ResponsesManager responsesManager;

	@Autowired
	private UserNameService userNameService;
	
	@Autowired
	private Validator responsesValidator;
	
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
	public String addCommentPage(ModelMap modelMap) {
		modelMap.addAttribute("responses", new Responses());

		return ADD_COMMENT_JSP_PAGE;
	}

	@RequestMapping(value = ADD_COMMENT, method = RequestMethod.POST)
	public String addComment(@PathVariable(TRIP_ID_PARAM) Integer tripId,
			@ModelAttribute("responses") Responses responses,
			BindingResult bindingResult, ModelMap modelMap) {

		responsesValidator.validate(responses, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return ADD_COMMENT_JSP_PAGE;
		}
		responsesManager.addResponse(userNameService.getLoggedUserId(), tripId, responses.getComment());

		return RESPONSES_INFO_JSP_PAGE;
	}

	@RequestMapping(value = CHECK_RESPONSES, method = RequestMethod.GET)
	public String checkResponses(Map<String, Object> modelMap) {

		modelMap.put(UNCHECKED_RESPONSES_LIST,
				responsesManager.getUncheckedResponses());

		return CHECK_RESPONSES_JSP_PAGE;
	}

	@RequestMapping(value = DEL_RESPONSE, method = RequestMethod.GET)
	public String delResponse(@PathVariable(RESPONSE_ID_PARAM) Integer responseId,
			Map<String, Object> modelMap) {
		responsesManager.delResponse(responseId);
		
		return REDIRECT_CHECK_RESPONSES_JSP_PAGE;
	}

	@RequestMapping(value = MARK_AS_CHECKED, method = RequestMethod.GET)
	public String markAsChecked(@PathVariable(RESPONSE_ID_PARAM) Integer responseId,
			Map<String, Object> modelMap) {
		responsesManager.markAsChecked(responseId);
		
		return REDIRECT_CHECK_RESPONSES_JSP_PAGE;
	}
}

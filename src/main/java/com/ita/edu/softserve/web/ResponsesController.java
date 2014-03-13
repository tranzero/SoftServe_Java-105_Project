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
import com.ita.edu.softserve.manager.ResponsesManager;
import com.ita.edu.softserve.manager.UserNameService;

@Controller
public class ResponsesController {

	/**
	 * URL pattern that map routeResponses controller
	 */
	private static final String ROUTE_RESPONSES_URL = "/routeResponses/{routeId}";

	/**
	 * URL pattern that map tripResponses controller
	 */
	private static final String TRIP_RESPONSES_URL = "/tripResponses/{tripId}";

	/**
	 * URL pattern that map transportResponses controller
	 */
	private static final String TRANSPORT_RESPONSES_URL = "/transportResponses/{transportId}";

	/**
	 * URL pattern that map addComment controller
	 */
	private static final String ADD_COMMENT_URL = "/addComment/{tripId}";

	/**
	 * URL pattern that map checkResponses controller
	 */
	private static final String CHECK_RESPONSES_URL = "/checkResponses";

	/**
	 * URL pattern that map delResponses controller
	 */
	private static final String DEL_RESPONSE_URL = "/delResponse/{responseId}";

	/**
	 * URL pattern that map markAsChecked controller
	 */
	private static final String MARK_AS_CHECKED_URL = "/markAsChecked/{responseId}";

	/**
	 * List that contains responses that will be transmitted to jsp
	 */
	private static final String RESPONSES_LIST = "ResponsesList";

	/**
	 * List that contains unchecked responses that will be transmitted to jsp
	 */
	private static final String UNCHECKED_RESPONSES_LIST = "uncheckedResponsesList";

	/**
	 * responses jsp page
	 */
	private static final String RESPONSES_JSP_PAGE = "responses";

	/**
	 * addComment jsp page
	 */
	private static final String ADD_COMMENT_JSP_PAGE = "addComment";

	/**
	 * responsesInfoPage jsp
	 */
	private static final String RESPONSES_INFO_JSP_PAGE = "responsesInfoPage";

	/**
	 * checkResponses jsp page
	 */
	private static final String CHECK_RESPONSES_JSP_PAGE = "checkResponses";

	/**
	 * Redirect to checkResponses jsp page
	 */
	private static final String REDIRECT_CHECK_RESPONSES_JSP_PAGE = "redirect:/checkResponses";

	/**
	 * Path variable tripId
	 */
	private static final String TRIP_ID_PARAM = "tripId";

	/**
	 * Path variable routeId
	 */
	private static final String ROUTE_ID_PARAM = "routeId";

	/**
	 * Path variable responseId
	 */
	private static final String RESPONSE_ID_PARAM = "responseId";

	/**
	 * Path variable transportId
	 */
	private static final String TRANSPORT_ID_PARAM = "transportId";

	/**
	 * ModelMap attribute
	 */
	private static final String RESPONSES_PARAM = "responses";

	/**
	 * Field for using responses manager methods
	 */
	@Autowired
	private ResponsesManager responsesManager;

	/**
	 * Field for using user name service methods
	 */
	@Autowired
	private UserNameService userNameService;

	/**
	 * Field for using responses validator
	 */
	@Autowired
	private Validator responsesValidator;

	/**
	 * 
	 * @param routeId
	 *            - Id of route, to find responses by
	 * @param modelMap
	 *            - modelmap to fill
	 * @return responses jsp
	 */
	@RequestMapping(value = ROUTE_RESPONSES_URL, method = RequestMethod.GET)
	public String routeResponses(@PathVariable(ROUTE_ID_PARAM) Integer routeId,
			Map<String, Object> modelMap) {

		modelMap.put(RESPONSES_LIST,
				responsesManager.getResponsesByRouteId(routeId));

		return RESPONSES_JSP_PAGE;
	}

	/**
	 * 
	 * @param tripId
	 *            - Id of trip, to find responses by
	 * @param modelMap
	 *            - modelmap to fill
	 * @return responses jsp
	 */
	@RequestMapping(value = TRIP_RESPONSES_URL, method = RequestMethod.GET)
	public String tripResponses(@PathVariable(TRIP_ID_PARAM) Integer tripId,
			Map<String, Object> modelMap) {

		modelMap.put(RESPONSES_LIST,
				responsesManager.getResponsesByTripId(tripId));

		return RESPONSES_JSP_PAGE;
	}

	/**
	 * 
	 * @param transportId
	 *            - Id of transport, to find responses by
	 * @param modelMap
	 *            - modelmap to fill
	 * @return responses jsp
	 */
	@RequestMapping(value = TRANSPORT_RESPONSES_URL, method = RequestMethod.GET)
	public String transportResponses(
			@PathVariable(TRANSPORT_ID_PARAM) Integer transportId,
			Map<String, Object> modelMap) {

		modelMap.put(RESPONSES_LIST,
				responsesManager.getResponsesByTransportId(transportId));

		return RESPONSES_JSP_PAGE;
	}

	/**
	 * 
	 * @param modelMap
	 *            - modelmap to fill
	 * @return addComment jsp
	 */
	@RequestMapping(value = ADD_COMMENT_URL, method = RequestMethod.GET)
	public String addCommentPage(ModelMap modelMap) {
		modelMap.addAttribute("responses", new Responses());

		return ADD_COMMENT_JSP_PAGE;
	}

	/**
	 * 
	 * @param tripId
	 *            - Id of trip< that comment is for
	 * @param responses
	 *            - comment for trip
	 * @param bindingResult
	 * @param modelMap
	 *            - modelmap to fill
	 * @return responsesInfoPage jsp
	 */
	@RequestMapping(value = ADD_COMMENT_URL, method = RequestMethod.POST)
	public String addComment(@PathVariable(TRIP_ID_PARAM) Integer tripId,
			@ModelAttribute(RESPONSES_PARAM) Responses responses,
			BindingResult bindingResult, ModelMap modelMap) {

		responsesValidator.validate(responses, bindingResult);

		if (bindingResult.hasErrors()) {
			return ADD_COMMENT_JSP_PAGE;
		}
		responsesManager.addResponse(userNameService.getLoggedUserId(), tripId,
				responses.getComment());

		return RESPONSES_INFO_JSP_PAGE;
	}

	/**
	 * 
	 * @param modelMap
	 *            - modelMap to fill
	 * @return checkResponses jsp
	 */
	@RequestMapping(value = CHECK_RESPONSES_URL, method = RequestMethod.GET)
	public String checkResponses(Map<String, Object> modelMap) {

		modelMap.put(UNCHECKED_RESPONSES_LIST,
				responsesManager.getUncheckedResponses());

		return CHECK_RESPONSES_JSP_PAGE;
	}

	/**
	 * 
	 * @param responseId
	 *            - Id of response to delete
	 * @param modelMap
	 *            - modelMap to fill
	 * @return checkResponses jsp
	 */
	@RequestMapping(value = DEL_RESPONSE_URL, method = RequestMethod.GET)
	public String delResponse(
			@PathVariable(RESPONSE_ID_PARAM) Integer responseId,
			Map<String, Object> modelMap) {
		responsesManager.delResponse(responseId);

		return REDIRECT_CHECK_RESPONSES_JSP_PAGE;
	}

	/**
	 * 
	 * @param responseId
	 *            - Id of response to check as marked
	 * @param modelMap
	 *            - modelMap to fill
	 * @return checkResponses jsp
	 */
	@RequestMapping(value = MARK_AS_CHECKED_URL, method = RequestMethod.GET)
	public String markAsChecked(
			@PathVariable(RESPONSE_ID_PARAM) Integer responseId,
			Map<String, Object> modelMap) {
		responsesManager.markAsChecked(responseId);

		return REDIRECT_CHECK_RESPONSES_JSP_PAGE;
	}
}

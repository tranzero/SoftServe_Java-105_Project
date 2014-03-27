package com.ita.edu.softserve.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.components.Encoder;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validation.StationsValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer;

@Controller
public class StationsController {

	private static final String STATIONS_LIST = "stationsList";

	private static final String STATION = "station";

	private static final String STATION_ID = "stationId";

	private static final String SEARCH_STRING = "isSearchString";

	private static final String STATIONS_FOR_USERS_URL = "/stationsForUsers";

	private static final String STATIONS_FOR_USERS_PAGE_URL = "/stationsForUsersPage";

	private static final String STATIONS_FOR_USERS_JSP_PAGE = "stationsForUsers";

	private static final String STATIONS_FOR_USERS_PAGE_JSP = "stationsForUsersPage";

	private static final String STATIONS_URL = "/stations";

	private static final String STATIONS_PAGE_URL = "/stationsPage";

	private static final String STATIONS_JSP_PAGE = "stations";

	private static final String STATIONS_PAGE_JSP = "stationsPage";

	private static final String STATIONS_ON_CERTAIN_LINE_URL = "stationsoncertainline/{line}";

	private static final String LINE = "line";

	private static final String DELETE_STATION_ID_URL = "/delete/{stationId}";

	private static final String REDIRECT_STATIONS = "redirect:/stations";

	private static final String STATION_EDIT_URL_GET = "/stationEdit/{station}";

	private static final String STATION_EDIT_JSP_PAGE = "stationEdit";

	private static final String STATION_EDIT_URL_POST = "/stationEdit/addStation";

	private static final String ADD_STATION_URL_GET = "/addStation";

	private static final String ADD_STATION_JSP_PAGE = "addStation";

	private static final String ADD_STATION_URL_POST = "/addStation";

	private static final String ORDER_BY_PARAMETR = "orderByParam";

	private static final String ORDER_BY_DIRECTION = "orderByDirection";

	private static final String CRITERIA_CONTAINER = "container";

	private static final String ENCODER_ATTRIBUTE_NAME = "encoder";

	private static final String LANGUAGE_NAME = "language";

	/**
	 * Field for using paging-related controller-level methods (class realized
	 * using singleton).
	 */
	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	PageInfoContainer container;

	@Autowired
	private StationsManager stationsManager;

	@Autowired
	private StationsValidator stationsValidator;

	@Autowired
	StationsCriteriaContainer stationsCriteriaContainer;

	@Autowired
	Encoder encoder;

	private void putSearchStringIsEmpty(
			StationsCriteriaContainer stationsCriteriaContainer,
			Map<String, Object> modelMap) {
		modelMap.put(SEARCH_STRING, ValidatorUtil
				.isEmptyString(stationsCriteriaContainer.getSearchString()));
	}

	/**
	 * Method for Sorting, Searching and Pagging.
	 * 
	 * @param pageNumber
	 *            - Number of displaying page (spring-defined).
	 * @param resultsPerPage
	 *            - Amount of results per page (spring-defined).
	 * @param searchString
	 *            - string for filter.
	 * @param orderByParam
	 *            - the column, using for sorting.
	 * @param orderByDirection
	 *            - sorting direction.
	 * @param modelMap
	 *            - Model map to fill.
	 * @param locale
	 *            - Used spring locale.
	 */
	private void deployStationsParameters(Integer pageNumber,
			Integer resultsPerPage, String searchString, String orderByParam,
			String orderByDirection, Map<String, Object> modelMap, Locale locale) {

		stationsCriteriaContainer.setValuableInfo(searchString, orderByParam,
				orderByDirection);
		putSearchStringIsEmpty(stationsCriteriaContainer, modelMap);
		stationsManager.validateStationListCriteria(stationsCriteriaContainer,
				locale);
		long count = stationsManager
				.getStationsListCountUsingContainer(stationsCriteriaContainer);

		container.setPageNumber(pageNumber);
		container.setResultsPerPage(resultsPerPage);
		container.setCount(count);
		paginationManager.validatePaging(container);

		PagingController.deployPaging(modelMap, container, paginationManager);

		modelMap.put(CRITERIA_CONTAINER, stationsCriteriaContainer);
		modelMap.put(ENCODER_ATTRIBUTE_NAME, encoder);
		modelMap.put(STATIONS_LIST, stationsManager
				.getStationsForLimitUsingContainers(stationsCriteriaContainer,
						container));

		modelMap.put(LANGUAGE_NAME, locale.getLanguage());

	}

	/**
	 * Prints all stations.
	 * 
	 * @param modelMap
	 *            - Model map to fill.
	 * 
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = STATIONS_FOR_USERS_URL, method = RequestMethod.GET)
	public String listStations(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Stations.SEARCH_STRING, required = false) String searchString,
			@RequestParam(value = ORDER_BY_PARAMETR, required = false) String orderByParam,
			@RequestParam(value = ORDER_BY_DIRECTION, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {

		deployStationsParameters(pageNumber, resultsPerPage, searchString,
				orderByParam, orderByDirection, modelMap, locale);

		return STATIONS_FOR_USERS_JSP_PAGE;
	}

	/**
	 * Prints all stations.
	 * 
	 * @param modelMap
	 *            - Model map to fill.
	 * 
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = STATIONS_FOR_USERS_PAGE_URL, method = RequestMethod.GET)
	public String listStationsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Stations.SEARCH_STRING, required = false) String searchString,
			@RequestParam(value = ORDER_BY_PARAMETR, required = false) String orderByParam,
			@RequestParam(value = ORDER_BY_DIRECTION, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {

		deployStationsParameters(pageNumber, resultsPerPage, searchString,
				orderByParam, orderByDirection, modelMap, locale);

		return STATIONS_FOR_USERS_PAGE_JSP;
	}

	/**
	 * Print all Stations where manager can manage them.
	 * 
	 * @param pageNumber
	 *            - Number of displaying page (spring-defined).
	 * @param resultsPerPage
	 *            - Amount of results per page (spring-defined).
	 * @param modelMap
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = STATIONS_URL, method = RequestMethod.GET)
	public String manageStations(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Stations.SEARCH_STRING, required = false) String searchString,
			@RequestParam(value = ORDER_BY_PARAMETR, required = false) String orderByParam,
			@RequestParam(value = ORDER_BY_DIRECTION, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {

		deployStationsParameters(pageNumber, resultsPerPage, searchString,
				orderByParam, orderByDirection, modelMap, locale);

		return STATIONS_JSP_PAGE;
	}

	/**
	 * Print all Stations where manager can manage them.
	 * 
	 * @param pageNumber
	 *            - Number of displaying page (spring-defined).
	 * @param resultsPerPage
	 *            - Amount of results per page (spring-defined).
	 * @param modelMap
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = STATIONS_PAGE_URL, method = RequestMethod.GET)
	public String manageStationsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Stations.SEARCH_STRING, required = false) String searchString,
			@RequestParam(value = ORDER_BY_PARAMETR, required = false) String orderByParam,
			@RequestParam(value = ORDER_BY_DIRECTION, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {

		deployStationsParameters(pageNumber, resultsPerPage, searchString,
				orderByParam, orderByDirection, modelMap, locale);

		return STATIONS_PAGE_JSP;
	}

	/**
	 * Delete Station by Id.
	 * 
	 * @param stationId
	 *            - the Id of Station to remove.
	 * @param map
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = DELETE_STATION_ID_URL, method = RequestMethod.GET)
	public String deleteStation(@PathVariable(STATION_ID) Integer stationId,
			ModelMap map) {

		stationsManager.removeStations(stationId);

		return REDIRECT_STATIONS;
	}

	/**
	 * Return jsp stationEdit.
	 * 
	 * @param stationId
	 *            - the Id of Station to remove.
	 * @param modelMap
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = STATION_EDIT_URL_GET, method = RequestMethod.GET)
	public String editStation(@PathVariable(STATION) Integer stationId,
			ModelMap modelMap) {

		Stations station = stationsManager.findStationsById(stationId);
		modelMap.put(STATION, station);

		return STATION_EDIT_JSP_PAGE;
	}

	/**
	 * Update station to DB - RequestMethod.POST
	 * 
	 * @param station
	 *            - the station object to update.
	 * @param bindingResult
	 * @param modelMap
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = STATION_EDIT_URL_POST, method = RequestMethod.POST)
	public String updateStationToDB(@ModelAttribute(STATION) Stations station,
			BindingResult bindingResult, ModelMap modelMap) {

		stationsValidator.validate(station, bindingResult);
		if (bindingResult.hasErrors()) {
			return STATION_EDIT_JSP_PAGE;
		}
		stationsManager.saveOrUpdateStation(station);
		return REDIRECT_STATIONS;
	}

	/**
	 * @param model
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = ADD_STATION_URL_GET, method = RequestMethod.GET)
	public String addStations(ModelMap model) {

		model.addAttribute(STATION, new Stations());
		return ADD_STATION_JSP_PAGE;
	}

	/**
	 * Save station to DB
	 * 
	 * @param modelMap
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = ADD_STATION_URL_POST, method = RequestMethod.POST)
	public String addStationToBD(@ModelAttribute(STATION) Stations station,
			BindingResult result, ModelMap modelMap) {

		stationsValidator.validate(station, result);
		if (result.hasErrors()) {
			return ADD_STATION_JSP_PAGE;
		}
		stationsManager.saveOrUpdateStation(station);

		return REDIRECT_STATIONS;
	}

	/**
	 * @param modelMap
	 *            - Model map to fill.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = STATIONS_ON_CERTAIN_LINE_URL, method = RequestMethod.GET)
	public String stationsOnCertainLine(@PathVariable(LINE) String lineName,
			Map<String, Object> modelMap) {

		modelMap.put(STATIONS_LIST,
				stationsManager.getStationsOnCertainLine(lineName));

		return STATIONS_FOR_USERS_JSP_PAGE;
	}

}

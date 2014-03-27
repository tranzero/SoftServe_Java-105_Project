package com.ita.edu.softserve.web;

import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.validation.RoutesValidator;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;

@Controller
public class RoutesController {

	/**
	 * The name of key with which the routes value is to be associated.
	 */
	private static final String ROUTES_TRIPS_LIST = "RoutesTripsList";
	/**
	 * URL pattern that map controller getRoutesTripsByStation.
	 */
	private static final String ROUTES_TRIP_URL_PATTERN = "/routesTrips";

	/**
	 * URL pattern that map controller getRoutesTripsByStation for paging.
	 */
	private static final String ROUTES_TRIP_PAGE_URL_PATTERN = "/routesTripsPage";
	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ROUTES_TRIPS_JSP = "routesTrips";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ROUTES_TRIPS_PAGE_JSP = "routesTripsPage";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String MODEL_ROUTE = "routes";

	/**
	 * The name of jsp that defines Spring to redirect.
	 */
	private static final String REDIRECT_ROUTES_EDIT = "redirect:/routesAllEdit";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ADD_ROUTE_JSP = "addRoute";
	/**
	 * URL pattern that map controller routeForm.
	 */
	private static final String FORM_ROUTE_URL_PATTERN = "/formRoute";

	/**
	 * URL pattern that map controller updateRouteToDB.
	 */
	private static final String EDIT_ROUTE_PAGE_URL_PATTERN = "editRoutePage";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String EDIT_ROUTE_JSP = "editRoute";

	/**
	 * URL pattern that map controller editRoute.
	 */
	private static final String EDIT_ROUTE_PATH = "/editRoute";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ROUTE = "route";

	/**
	 * Define when is not error
	 */
	public static final String IS_NO_ERROR = "0";

	/**
	 * URL pattern that map controller edit Route to DB.
	 */
	private static final String EDIT_ROUTE_ALL_URL_PATTERN = "/routesAllEdit";

	/**
	 * The name of jsp that defines Spring to redirect.
	 */
	private static final String REDIRECT_ROUTES_ALL_EDIT = "routesAllEdit";

	/**
	 * Define when is routeCode
	 */
	public static final String ROUTE_CODE = "r.routeCode";

	/**
	 * Define order by desc
	 */
	public static final String DESC = "DESC";

	/**
	 * URL pattern that map controller updateRouteToDB.
	 */
	private static final String ROUTES_ALL_EDIT_URL_PATTERN = "/routesAllEditPage";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ROUTES_ALL_EDIT_JSP = "routesAllEditPage";

	/**
	 * URL pattern that map controller getRoutes.
	 */
	private static final String ROUTES_URL_PATTERN = "/routes";

	/**
	 * URL pattern that map controller to show route
	 */
	private static final String ROUTES_PAGE_URL_PATTERN = "/routesPage";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ROUTES_JSP = "routesPage";

	/**
	 * The name of model
	 */
	private static final String ROUTE_LIST = "routesList";

	/**
	 * URL pattern that map controller addRouteToBD.
	 */
	private static final String ADD_ROUTE_PAGE_URL_PATTERN = "addRoutePage";

	/**
	 * Name of routeId field
	 */
	private static final String ROUTE_ID_CODE = "routeId";

	/**
	 * Name of routeCode field
	 */
	private static final String ROUTE_FIELD_CODE = "routeCode";

	/**
	 * Name of lineName field.
	 */
	private static final String LINE_NAME_FIELD_CODE = "lineName";

	/**
	 * Name of stationStartName field.
	 */
	private static final String STATION_START_NAME_FIELD_CODE = "stationStart";

	/**
	 * Name of stationEndName field.
	 */
	private static final String STATION_END_NAME_FIELD_CODE = "stationEnd";

	/**
	 * URL pattern that map controller edit Route to DB.
	 */
	private static final String DELETE_ROUTE_URL_PATTERN = "/deleteRoute/{routeToDelete}";

	/**
	 * The name of route to delete
	 */
	private static final String ROUTES_DELETE = "routeToDelete";

	/**
	 * URL pattern that map controller to getStationAutoCompleteList.
	 */
	private static final String GET_STATION_AUTO_COMPLETE_LIST_URL_PATTERN = "getStationAutoCompleteList";

	/**
	 * Name of stations field.
	 */
	private static final String STATIONS_FIELD_CODE = "stations";

	/**
	 * The part of station name
	 */
	private static final String STATION_START_WITH = "stationStartsWith";

	/**
	 * URL pattern that map controller to getStationOnLineAutoCompleteList.
	 */
	private static final String GET_STATION_ON_LINE_AUTO_COMPLETE_LIST_URL_PATTERN = "getStationOnLineAutoCompleteList";

	/**
	 * URL pattern that map controller to getLineAutoCompleteList.
	 */
	private static final String GET_LINE_AUTO_COMPLETE_LIST_URL_PATTERN = "getLineAutoCompleteList";

	/**
	 * The part of line name
	 */
	private static final String LINE_START_WITH = "lineStartsWith";

	/**
	 * Name of lines field.
	 */
	private static final String LINES_FIELD_CODE = "lines";

	/**
	 * URL pattern that map controller to findRouteCodeInDB.
	 */
	private static final String FIND_ROUTECODE_IN_DB_URL_PATTERN = "findRouteCodeInDB";

	/**
	 * Name of nameStation field.
	 */
	private static final String NAME_STATION_FIELD_CODE = "nameStation";

	/**
	 * Name of "timeMin" field.
	 */
	private static final String TIME_MIN_FIELD_CODE = "timeMin";

	/**
	 * Name of "timeMax" field.
	 */
	private static final String TIME_MAX_FIELD_CODE = "timeMax";

	/**
	 * Name of "findBy" field.
	 */
	private static final String FIND_BY_FIELD_CODE = "findBy";

	/**
	 * Name of "findByArr" field.
	 */
	private static final String FIND_BY_ARR_FIELD_CODE = "findByArr";

	/**
	 * Name of "findByDep" field.
	 */
	private static final String FIND_BY_DEP_FIELD_CODE = "findByDep";

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	private RoutesManager routesManager;

	@Autowired
	private LinesManager linesManager;

	@Autowired
	RoutesValidator routesValidator;

	/**
	 * Controller method for displaying editing Routes.
	 * 
	 */
	@RequestMapping(value = EDIT_ROUTE_ALL_URL_PATTERN, method = RequestMethod.GET)
	public String printRoutes(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);

		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		modelMap.put(ROUTE_LIST, routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),
				ROUTE_CODE, DESC));
		return REDIRECT_ROUTES_ALL_EDIT;
	}

	/**
	 * Controller for displaying getting routes ID from the Routes table and
	 * finds it in the Routes table then puts found object in Map as request
	 * attribute.
	 * 
	 * @param routeId
	 *            routes ID to get from the database.
	 * @param modelMap
	 *            Model map to fill.
	 * @return editRoute jsp to use.
	 */
	@RequestMapping(value = EDIT_ROUTE_PATH, method = RequestMethod.GET)
	public String editRoute(@RequestParam(ROUTE_ID_CODE) int routeId,
			Map<String, Object> modelMap) {

		Routes route = routesManager.findRoutesById(routeId);
		modelMap.put(ROUTE, route);
		return EDIT_ROUTE_JSP;
	}

	/**
	 * Displays getting a routes object and saves it into the Routes table.
	 * 
	 * @return the jsp name.
	 */
	@RequestMapping(value = EDIT_ROUTE_PAGE_URL_PATTERN, method = RequestMethod.POST)
	@ResponseBody
	public String updateRouteToDB(

			@RequestParam(value = ROUTE_ID_CODE, required = false) Integer routeId,
			@RequestParam(value = ROUTE_FIELD_CODE, required = false) String routeCode,
			@RequestParam(value = LINE_NAME_FIELD_CODE, required = false) String lineName,
			@RequestParam(value = STATION_START_NAME_FIELD_CODE, required = false) String stationStart,
			@RequestParam(value = STATION_END_NAME_FIELD_CODE, required = false) String stationEnd,
			Map<String, Object> modelMap) {

		String[] routeCheck = new RoutesValidatorConverter()
				.getValidateRouteErrors(new Routes(routeId, routeCode,
						lineName, stationStart, stationEnd), routesValidator,
						routesManager);

		if (routeCheck[0].equals(IS_NO_ERROR)) {
			routesManager.updateRoute(routeId, lineName, routeCode,
					stationStart, stationEnd);
		}
		return new Gson().toJson(routeCheck);
	}

	/**
	 * Return page with all routes for edit
	 */
	@RequestMapping(value = ROUTES_ALL_EDIT_URL_PATTERN, method = RequestMethod.GET)
	public String printRoutesPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(ROUTE_LIST, routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),
				ROUTE_CODE, DESC));
		return ROUTES_ALL_EDIT_JSP;
	}

	/**
	 * Controller method for displaying all routes
	 * 
	 */
	@RequestMapping(value = ROUTES_URL_PATTERN, method = RequestMethod.GET)
	public String printListRoutes(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(ROUTE_LIST, routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),
				ROUTE_CODE, DESC));
		return MODEL_ROUTE;
	}

	/**
	 * Controller method for displaying routes for editing.
	 * 
	 */
	@RequestMapping(value = ROUTES_PAGE_URL_PATTERN, method = RequestMethod.GET)
	public String printListRoutesPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);

		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		modelMap.put(ROUTE_LIST, routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),
				ROUTE_CODE, DESC));
		return ROUTES_JSP;
	}

	/**
	 * Map name of jsp addRoute to formRoute.
	 * 
	 * @return the jsp name.
	 */
	@RequestMapping(value = FORM_ROUTE_URL_PATTERN, method = RequestMethod.GET)
	public String routeForm(ModelMap modelMap) {
		return ADD_ROUTE_JSP;
	}

	/**
	 * Controller method for displaying adding route page. Adds new route into
	 * the Routes table.
	 * 
	 */
	@RequestMapping(value = ADD_ROUTE_PAGE_URL_PATTERN, method = RequestMethod.POST)
	@ResponseBody
	public String addRouteToBD(
			@RequestParam(value = ROUTE_FIELD_CODE, required = false) String routeCode,
			@RequestParam(value = LINE_NAME_FIELD_CODE, required = false) String lineName,
			@RequestParam(value = STATION_START_NAME_FIELD_CODE, required = false) String stationStart,
			@RequestParam(value = STATION_END_NAME_FIELD_CODE, required = false) String stationEnd,
			Map<String, Object> modelMap) {

		String[] routeCheck = new RoutesValidatorConverter()
				.getValidateRouteErrors(new Routes(0, routeCode, lineName,
						stationStart, stationEnd), routesValidator,
						routesManager);

		if (routeCheck[0].equals(IS_NO_ERROR)) {
			routesManager.createRoute(lineName, routeCode, stationStart,
					stationEnd);
		}
		return new Gson().toJson(routeCheck);
	}

	/**
	 * Delete route from DB
	 * 
	 * @param routeId
	 */
	@RequestMapping(value = DELETE_ROUTE_URL_PATTERN, method = RequestMethod.GET)
	public String deleteRoute(@PathVariable(ROUTES_DELETE) Integer routeId) {
		routesManager.removeRouteById(routeId);
		return REDIRECT_ROUTES_EDIT;
	}

	/**
	 * Find stations which name start as input parameter from DB
	 * 
	 * @param stationStartsWith
	 */
	@RequestMapping(value = GET_STATION_AUTO_COMPLETE_LIST_URL_PATTERN, method = RequestMethod.GET)
	@ResponseBody
	public String getStationsList(
			@RequestParam(value = STATION_START_WITH, required = false) String stationStartsWith,
			Map<String, Object> modelMap) {

		List<String> stationList = routesManager
				.getStationNameListCriteria(stationStartsWith);

		Map<String, List<String>> stationMap = new HashMap<String, List<String>>();
		stationMap.put(STATIONS_FIELD_CODE, stationList);
		return new Gson().toJson(stationMap);
	}

	/**
	 * Find stations on line which name start as input parameter
	 * 
	 * @param stationStartsWith
	 * @param lineName
	 */
	@RequestMapping(value = GET_STATION_ON_LINE_AUTO_COMPLETE_LIST_URL_PATTERN, method = RequestMethod.GET)
	@ResponseBody
	public String getStationsOnLineList(
			@RequestParam(value = STATION_START_WITH, required = false) String stationStartsWith,
			@RequestParam(value = LINE_NAME_FIELD_CODE, required = false) String lineName,
			Map<String, Object> modelMap) {

		List<String> stationList = routesManager
				.getStationNameByLineListCriteria(stationStartsWith, lineName);

		Map<String, List<String>> stationMap = new HashMap<String, List<String>>();
		stationMap.put(STATIONS_FIELD_CODE, stationList);
		return new Gson().toJson(stationMap);
	}

	/**
	 * Find lines which name start as input parameter
	 * 
	 * @param lineStartsWith
	 */
	@RequestMapping(value = GET_LINE_AUTO_COMPLETE_LIST_URL_PATTERN, method = RequestMethod.GET)
	@ResponseBody
	public String getLinesList(
			@RequestParam(value = LINE_START_WITH, required = false) String lineStartsWith,
			Map<String, Object> modelMap) {

		List<String> lineList = routesManager
				.getLineNameListCriteria(lineStartsWith);

		Map<String, List<String>> stationMap = new HashMap<String, List<String>>();
		stationMap.put(LINES_FIELD_CODE, lineList);
		return new Gson().toJson(stationMap);
	}

	/**
	 * Find route by name in DB
	 * 
	 * @param routeCode
	 */
	@RequestMapping(value = FIND_ROUTECODE_IN_DB_URL_PATTERN, method = RequestMethod.GET)
	@ResponseBody
	public String findRouteCodeInDB(
			@RequestParam(value = ROUTE_FIELD_CODE, required = false) String routeCode,
			Map<String, Object> modelMap) {

		String[] routeCheck = new RoutesValidatorConverter()
				.validateRouteCodeToStringArray(routeCode);

		return new Gson().toJson(routeCheck);
	}

	/**
	 * Find Routes of transports that are arriving from certain station during
	 * certain times
	 * 
	 * @param stationName
	 *            - name arriving station
	 * @param timeMin
	 *            - minimum time arrival
	 * @param timeMax
	 *            - maximum time arrival
	 * @param findBy
	 *            - chose arrival or departing
	 * 
	 */

	@RequestMapping(value = ROUTES_TRIP_URL_PATTERN, method = RequestMethod.GET)
	public String getRoutesTripsByStation(
			@RequestParam(value = NAME_STATION_FIELD_CODE, required = false) String nameStation,
			@RequestParam(value = TIME_MIN_FIELD_CODE, required = false) String timeMin,
			@RequestParam(value = TIME_MAX_FIELD_CODE, required = false) String timeMax,
			@RequestParam(value = FIND_BY_FIELD_CODE, required = false) String findBy,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		if ((nameStation == null) || (timeMin == null) || (timeMax == null)
				|| nameStation.equals("") || timeMin.equals("")
				|| timeMax.equals("")) {
			return ROUTES_TRIPS_JSP;
		}

		long count = getRoutersListByStationNameCount(findBy, nameStation,
				timeMin, timeMax);

		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		getRoutersListByStationNameForPage(findBy, modelMap, nameStation,
				timeMin, timeMax, container);

		return ROUTES_TRIPS_JSP;
	}

	/**
	 * Find Routes of transports that are departing to certain station during
	 * certain times
	 * 
	 * @param stationName
	 *            - name departing station
	 * @param timeMin
	 *            - minimum time departing
	 * @param timeMax
	 *            - maximum time departing
	 * @param findBy
	 *            - chose arrival or departing
	 * 
	 */
	@RequestMapping(value = ROUTES_TRIP_PAGE_URL_PATTERN, method = RequestMethod.GET)
	public String getRoutesTripsByStationPage(
			@RequestParam(value = NAME_STATION_FIELD_CODE, required = false) String nameStation,
			@RequestParam(value = TIME_MIN_FIELD_CODE, required = false) String timeMin,
			@RequestParam(value = TIME_MAX_FIELD_CODE, required = false) String timeMax,
			@RequestParam(value = FIND_BY_FIELD_CODE, required = false) String findBy,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		if ((nameStation == null) || (timeMin == null) || (timeMax == null)
				|| nameStation.equals("") || timeMin.equals("")
				|| timeMax.equals("")) {
			return ROUTES_TRIPS_JSP;
		}

		long count = getRoutersListByStationNameCount(findBy, nameStation,
				timeMin, timeMax);

		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		getRoutersListByStationNameForPage(findBy, modelMap, nameStation,
				timeMin, timeMax, container);
		return ROUTES_TRIPS_PAGE_JSP;
	}

	/**
	 * Returns count for list with routes, which arriving, or departing from
	 * current station
	 */
	private long getRoutersListByStationNameCount(String findBy,
			String nameStation, String timeMin, String timeMax) {
		long count = 0;
		if (findBy.equals(FIND_BY_ARR_FIELD_CODE)) {
			count = routesManager.getRoutersListByStationNameArrivingCount(
					nameStation, parseStringToTime(timeMin),
					parseStringToTime(timeMax));
		}
		if (findBy.equals(FIND_BY_DEP_FIELD_CODE)) {
			count = routesManager.getRoutersListByStationNameDepartingCount(
					nameStation, parseStringToTime(timeMin),
					parseStringToTime(timeMax));
		}
		return count;
	}

	/**
	 * Put to modelMap list with routes, which arriving, or departing from
	 * current station
	 */
	private void getRoutersListByStationNameForPage(String findBy,
			Map<String, Object> modelMap, String nameStation, String timeMin,
			String timeMax, PageInfoContainerImpl container) {
		if (findBy.equals(FIND_BY_ARR_FIELD_CODE)) {
			modelMap.put(ROUTES_TRIPS_LIST, routesManager
					.getRoutersListByStNameArrivingForPage(nameStation,
							parseStringToTime(timeMin),
							parseStringToTime(timeMax),
							(int) container.getPageNumber(),
							(int) container.getResultsPerPage()));
		}
		if (findBy.equals(FIND_BY_DEP_FIELD_CODE)) {
			modelMap.put(ROUTES_TRIPS_LIST, routesManager
					.getRoutersListByStNameDepartingForPage(nameStation,
							parseStringToTime(timeMin),
							parseStringToTime(timeMax),
							(int) container.getPageNumber(),
							(int) container.getResultsPerPage()));
		}
	}
}
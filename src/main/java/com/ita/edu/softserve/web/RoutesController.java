package com.ita.edu.softserve.web;

import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;

import java.io.Console;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ExceptionUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
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
	 * URL pattern that map controller addRouteToBD.
	 */
	private static final String ADD_ROUTE_URL_PATTERN = "/addRoutePage";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ADD_ROUTE_JSP = "addRoute";
	/**
	 * URL pattern that map controller routeForm.
	 */
	private static final String FORM_ROUTE_URL_PATTERN = "/formRoute";
	
	
	private static final Logger LOGGER = Logger
			.getLogger(RoutesController.class);

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	private RoutesManager routesManager;

	@Autowired
	private LinesManager linesManager;
	
	@Autowired
	Validator routesValidator;
	
	/**
	 * Return all routes
	 */
	@RequestMapping(value = "/routesAllEdit", method = RequestMethod.GET)
	public String printRoutes(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),"r.routeCode","DESC"));
		return "routesAllEdit";
	}

	@RequestMapping(value = "/routesAllEditPage", method = RequestMethod.GET)
	public String printRoutesPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl  container = new PageInfoContainerImpl (pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),"r.routeCode","DESC"));
		return "routesAllEditPage";
	}

	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public String printListRoutes(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl  container = new PageInfoContainerImpl (pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),"r.routeCode","DESC"));
		return "routes";
	}

	@RequestMapping(value = "/routesPage", method = RequestMethod.GET)
	public String printListRoutesPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainerImpl  container = new PageInfoContainerImpl (pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage(),"r.routeCode","DESC"));
		return "routesPage";
	}

	
	/**
	 * Map name of jsp addRoute to formRoute.
	 * 
	 * @return the jsp name.
	 */
	@RequestMapping(value = FORM_ROUTE_URL_PATTERN, method = RequestMethod.GET)
	public String routeForm(ModelMap modelMap) {
		//modelMap.addAttribute(MODEL_ROUTE, new Routes());
		return ADD_ROUTE_JSP;
	}

	/**
	 * Controller method for displaying adding route page. Adds new
	 * route into the Routes table.
	 * 
	 * @param routeCode
	 *            Route code.
	 * @return the jsp name.
	 */
	@RequestMapping(value =  ADD_ROUTE_URL_PATTERN, method = RequestMethod.POST)
	public String addRouteToBD(@ModelAttribute("routeCode") String routeCode,
			@ModelAttribute("lineName") String lineName,
			@ModelAttribute("stationStart") String stationStart,
			@ModelAttribute("stationEnd") String stationEnd) {

			routesManager.createRoute(lineName, routeCode, stationStart, stationEnd);// return json
		return REDIRECT_ROUTES_EDIT;
	}
	
	/*@RequestMapping(value = ADD_ROUTE_URL_PATTERN, method = RequestMethod.POST)
	public String addRouteToBD(
			@ModelAttribute(MODEL_ROUTE)Routes route,
			BindingResult bindingResult, ModelMap modelMap) {

		routesValidator.validate(route, bindingResult);

		if (bindingResult.hasErrors()) {
			return ADD_ROUTE_JSP;
		}

		routesManager.createRoute(route);

		return REDIRECT_ROUTES_EDIT;
	}*/
	
	
	
	
	
	
	/*@RequestMapping(value = "/addRt", method = RequestMethod.GET)
	public String transportForm() {
		return "addRoute";
	}
	*/
	/**
	 * Add route to DB
	 * 
	 * @param routeCode
	 * @param lineNam
	 */
	/*@RequestMapping(value = "/addRoute", method = RequestMethod.POST)
	public String addRouteToBD(@ModelAttribute("routeCode") String routeCode,
			@ModelAttribute("lineName") String lineName) {
		try {
			routesManager.createRoute(lineName, routeCode);
		} catch (Exception e) {
		}

		return "redirect:/routesAllEdit";
	}*/

	@RequestMapping(value = "/editRoute/{route}", method = RequestMethod.GET)
	public String editRoute(@PathVariable("route") Integer routeId,
			Map<String, Object> modelMap) {
		Routes route = routesManager.findRoutesById(routeId);
		modelMap.put("route", route);
		return "editRoute";
	}
	
	/**
	 * Edit route in DB
	 * 
	 * @param routeCode
	 * @param lineNam
	 */
	@RequestMapping(value = "/editRoute/{routeId}", method = RequestMethod.POST)
	public String updateRouteToDB(@PathVariable("routeId") Integer routeId,
			@ModelAttribute("routeCode") String routeCode,
			@ModelAttribute("lineName") String lineName) {

		routesManager.updateRoute(routeId, lineName, routeCode);

		return "redirect:/routesAllEdit";
	}

	/**
	 * Delete route from DB
	 * 
	 * @param routeId
	 */
	@RequestMapping(value = "/deleteRoute/{routeToDelete}", method = RequestMethod.GET)
	public String deleteRoute(@PathVariable("routeToDelete") Integer routeId) {
		routesManager.removeRouteById(routeId);

		return "redirect:/routesAllEdit";
	}

	
	/**
	 * Find stations from DB
	 * 
	 * @param stationStartsWith
	 */
	@RequestMapping(value = "getStationAutoCompleteList", method = RequestMethod.GET)
	@ResponseBody
	public String getStationsList(
			@RequestParam(value = "stationStartsWith", required = false) String stationStartsWith,
			Map<String, Object> modelMap) {

		List<String> stationList = routesManager
				.getStationNameListCriteria(stationStartsWith);

		Map<String, List<String>> stationMap = new HashMap<String, List<String>>();
		stationMap.put("stations", stationList);
		return new Gson().toJson(stationMap);
	}
	
	
	@RequestMapping(value = "getStationOnLineAutoCompleteList", method = RequestMethod.GET)
	@ResponseBody
	public String getStationsOnLineList(
			@RequestParam(value = "stationStartsWith", required = false) String stationStartsWith,
			@RequestParam(value = "lineName", required = false) String lineName,
			Map<String, Object> modelMap) {

		List<String> stationList = routesManager
				.getStationNameByLineListCriteria(stationStartsWith, lineName);

		Map<String, List<String>> stationMap = new HashMap<String, List<String>>();
		stationMap.put("stations", stationList);
		return new Gson().toJson(stationMap);
	}
	
	
	@RequestMapping(value = "getLineAutoCompleteList", method = RequestMethod.GET)
	@ResponseBody
	public String getLinesList(
			@RequestParam(value = "lineStartsWith", required = false) String lineStartsWith,
			Map<String, Object> modelMap) {
		List<String> lineList = routesManager
				.getLineNameListCriteria(lineStartsWith);

		Map<String, List<String>> stationMap = new HashMap<String, List<String>>();
		stationMap.put("lines", lineList);
		return new Gson().toJson(stationMap);
	}
	
	/**
	 * Find Routes of transports that are arriving/departing to certain station during
	 * certain times
	 * 
	 * @param stationName
	 *            - name arriving/departing  station
	 * @param timeMin
	 *            - minimum time arrival/departing 
	 * @param timeMax
	 *            - maximum time arrival/departing 
	 * @param findBy
	 *            - chose arrival or departing
	 * 
	 */
	
	@RequestMapping(value = ROUTES_TRIP_URL_PATTERN, method = RequestMethod.GET)
	public String getRoutesTripsByStation(
			@RequestParam(value = "nameStation", required = false) String nameStation,
			@RequestParam(value = "timeMin", required = false) String timeMin,
			@RequestParam(value = "timeMax", required = false) String timeMax,
			@RequestParam(value = "findBy", required = false) String findBy,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		if ((nameStation == null) || (timeMin == null) || (timeMax == null) ||
				nameStation.equals("") || timeMin.equals("") || timeMax.equals("")) {
			return ROUTES_TRIPS_JSP;
		}

		long count = 0;
		if (findBy.equals("findByArr")) {
			count=routesManager.getRoutersListByStationNameArrivingCount(nameStation,parseStringToTime(timeMin), parseStringToTime(timeMax));
		}
		if (findBy.equals("findByDep")) {
			count=routesManager.getRoutersListByStationNameDepartingCount(nameStation,parseStringToTime(timeMin), parseStringToTime(timeMax));
		}		
	
		PageInfoContainerImpl  container = new PageInfoContainerImpl (pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		
		if (findBy.equals("findByArr")) {
			modelMap.put(ROUTES_TRIPS_LIST, routesManager.getRoutersListByStNameArrivingForPage(
					nameStation,
					parseStringToTime(timeMin), parseStringToTime(timeMax), (int)container.getPageNumber(),(int) container.getResultsPerPage()));
		}
		
		if (findBy.equals("findByDep")) {
			modelMap.put(ROUTES_TRIPS_LIST, routesManager.getRoutersListByStNameDepartingForPage(
					nameStation,
					parseStringToTime(timeMin), parseStringToTime(timeMax), (int)container.getPageNumber(), (int)container.getResultsPerPage()));
		}

		return ROUTES_TRIPS_JSP;
	}
	
	@RequestMapping(value = ROUTES_TRIP_PAGE_URL_PATTERN, method = RequestMethod.GET)
	public String getRoutesTripsByStationPage(
			@RequestParam(value = "nameStation", required = false) String nameStation,
			@RequestParam(value = "timeMin", required = false) String timeMin,
			@RequestParam(value = "timeMax", required = false) String timeMax,
			@RequestParam(value = "findBy", required = false) String findBy,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		
		if ((nameStation == null) || (timeMin == null) || (timeMax == null) ||
				nameStation.equals("") || timeMin.equals("") || timeMax.equals("")) {
			return ROUTES_TRIPS_JSP;
		}

		long count = 0;
		
		if (findBy.equals("findByArr")) {
			count=routesManager.getRoutersListByStationNameArrivingCount(nameStation,parseStringToTime(timeMin), parseStringToTime(timeMax));
		}
		if (findBy.equals("findByDep")) {
			count=routesManager.getRoutersListByStationNameDepartingCount(nameStation,parseStringToTime(timeMin), parseStringToTime(timeMax));
		}	
		
		PageInfoContainerImpl  container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);


		if (findBy.equals("findByArr")) {
			modelMap.put(ROUTES_TRIPS_LIST, routesManager.getRoutersListByStNameArrivingForPage(
					nameStation,
					parseStringToTime(timeMin), parseStringToTime(timeMax), (int)container.getPageNumber(),(int) container.getResultsPerPage()));
		}
		
		if (findBy.equals("findByDep")) {
			modelMap.put(ROUTES_TRIPS_LIST, routesManager.getRoutersListByStNameDepartingForPage(
					nameStation,
					parseStringToTime(timeMin), parseStringToTime(timeMax), (int)container.getPageNumber(), (int)container.getResultsPerPage()));
		}
		
		return ROUTES_TRIPS_PAGE_JSP ;
	}
}
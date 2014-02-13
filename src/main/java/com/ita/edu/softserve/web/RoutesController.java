package com.ita.edu.softserve.web;

import static com.ita.edu.softserve.utils.ParseUtil.timeParse;

import java.io.Console;
import java.sql.Time;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ExceptionUtil;
import com.ita.edu.softserve.utils.PageInfoContainer;

@Controller
public class RoutesController {

	private static final Logger LOGGER = Logger
			.getLogger(RoutesController.class);

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	private RoutesManager routesManager;

	@Autowired
	private LinesManager linesManager;

	/**
	 * Return all routes
	 */
	@RequestMapping(value = "/routesAllEdit", method = RequestMethod.GET)
	public String printRoutes(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage()));
		return "routesAllEdit";
	}

	@RequestMapping(value = "/routesAllEditPage", method = RequestMethod.GET)
	public String printRoutesPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage()));
		return "routesAllEditPage";
	}

	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public String printListRoutes(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage()));
		return "routes";
	}

	@RequestMapping(value = "/routesPage", method = RequestMethod.GET)
	public String printListRoutesPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = routesManager.getRoutesListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("routesList", routesManager.getRoutesForPage(
				container.getPageNumber(), container.getResultsPerPage()));
		return "routesPage";
	}

	@RequestMapping(value = "/addRt", method = RequestMethod.GET)
	public String transportForm() {
		return "addRoute";
	}
	
	/**
	 * Add route to DB
	 * 
	 * @param routeCode
	 * @param lineNam
	 */
	@RequestMapping(value = "/addRoute", method = RequestMethod.POST)
	public String addRouteToBD(@ModelAttribute("routeCode") String routeCode,
			@ModelAttribute("lineName") String lineName) {
		try {
			routesManager.createRoute(lineName, routeCode);
		} catch (Exception e) {
		}

		return "redirect:/routesAllEdit";
	}

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

	@RequestMapping(value = "/routesTrips", method = RequestMethod.GET)
	public String findRoutersListByStationIdArriving(Map<String, Object> model) {
		return "routesTrips";
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
	@RequestMapping(value = "/routesFind", method = RequestMethod.GET)
	public String findRoutersListByStationIdArriving(
			@RequestParam("nameStation") String nameStation,
			@RequestParam("timeMin") String timeMin,
			@RequestParam("timeMax") String timeMax,
			@RequestParam("findBy") String findBy, Map<String, Object> model) {
		if (nameStation.equals("") || timeMin.equals("") || timeMax.equals("")) {
			return "routesTrips";
		}

		if (findBy.equals("findByArr")) {
			model.put("RouteTripsList", routesManager
					.findRoutersListByStationNameArriving(nameStation,
							timeParse(timeMin), timeParse(timeMax)));
		}
		if (findBy.equals("findByDep")) {
			model.put("RouteTripsList", routesManager
					.findRoutersListByStationNameDeparting(nameStation,
							timeParse(timeMin), timeParse(timeMax)));
		}
		return "routesTrips";
	}
	
	@RequestMapping(value = "/routestripsbystations", method = RequestMethod.GET)
	public String getRoutesTripsByStations(
			@RequestParam("nameStation") String nameStation,
			@RequestParam("timeMin") String timeMin,
			@RequestParam("timeMax") String timeMax,
			@RequestParam("findBy") String findBy,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		if (nameStation.equals("") || timeMin.equals("") || timeMax.equals("")) {
			return "routestripsbystations";
		}

		long count = 0;
		if (findBy.equals("findByArr")) {
			count=routesManager.getRoutersListByStationNameArrivingCount(nameStation,timeParse(timeMin), timeParse(timeMax));
		}
		if (findBy.equals("findByDep")) {
			count=routesManager.getRoutersListByStationNameDepartingCount(nameStation,timeParse(timeMin), timeParse(timeMax));
		}		
	
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		
		if (findBy.equals("findByArr")) {
			modelMap.put("RouteTripsList", routesManager
					.findRoutersListByStationNameArriving(nameStation,
							timeParse(timeMin), timeParse(timeMax)));
		}
		
		if (findBy.equals("findByDep")) {
			modelMap.put("RouteTripsList", routesManager
					.findRoutersListByStationNameDeparting(nameStation,
							timeParse(timeMin), timeParse(timeMax)));
		}	

		return "routestripsbystations";
	}

	@RequestMapping(value = "/routestripsbystationsPage", method = RequestMethod.GET)
	public String getRoutesTripsByStationsPage(
			@RequestParam("nameStation") String nameStation,
			@RequestParam("timeMin") String timeMin,
			@RequestParam("timeMax") String timeMax,
			@RequestParam("findBy") String findBy,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		if (nameStation.equals("") || timeMin.equals("") || timeMax.equals("")) {
			return "routestripsbystations";
		}

		long count = 0;
		
		if (findBy.equals("findByArr")) {
			count=routesManager.getRoutersListByStationNameArrivingCount(nameStation,timeParse(timeMin), timeParse(timeMax));
		}
		if (findBy.equals("findByDep")) {
			count=routesManager.getRoutersListByStationNameDepartingCount(nameStation,timeParse(timeMin), timeParse(timeMax));
		}	
		
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		System.out.println(nameStation);
		System.out.println(timeMin);
		System.out.println(timeMax);
		System.out.println(pageNumber);
		System.out.println(resultsPerPage);
		System.out.println("nooooo");
		if (findBy.equals("findByArr")) {
			modelMap.put("RouteTripsList", routesManager.getRoutersListByStNameArrivingForPage(
					nameStation,
					timeParse(timeMin), timeParse(timeMax), container.getPageNumber(), container.getResultsPerPage()));
		}
		
		if (findBy.equals("findByDep")) {
			modelMap.put("RouteTripsList", routesManager.getRoutersListByStNameDepartingForPage(
					nameStation,
					timeParse(timeMin), timeParse(timeMax), container.getPageNumber(), container.getResultsPerPage()));
		}
		return "routestripsbystationsPage";
	}
}
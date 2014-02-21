package com.ita.edu.softserve.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;

@Controller
public class StationsController {

	private static final String STATIONS_LIST = "stationsList";

	private static final String STATION = "station";

	private static final String STATION_ID = "stationId";

	private static final String STATION_CODE = "stationCode";

	private static final String STATION_NAME = "stationName";

	private static final String STATIONS_FOR_USERS_URL = "/stationsForUsers";

	private static final String STATIONS_FOR_USERS_JSP_PAGE = "stationsForUsers";

	private static final String STATIONS_URL = "/stations";

	private static final String STATIONS_JSP_PAGE = "stations";

	private static final String DELETE_STATION_ID_URL = "/delete/{stationId}";

	private static final String REDIRECT_STATIONS = "redirect:/stations";

	private static final String STATION_EDIT_URL_GET = "/stationEdit/{station}";

	private static final String STATION_EDIT_JSP_PAGE = "stationEdit";

	private static final String STATION_EDIT_URL_POST = "/stationEdit/{stationToEdit}";

	private static final String STATION_TO_EDIT = "stationToEdit";

	private static final String ADD_STATION_URL_GET = "/addStation";

	private static final String ADD_STATION_JSP_PAGE = "addStation";

	private static final String ADD_STATION_URL_POST = "/addstat";

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	private StationsManager stationsManager;

	/**
	 * Prints all stations.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = STATIONS_FOR_USERS_URL, method = RequestMethod.GET)
	public String listStations(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		paggingForStations(pageNumber, resultsPerPage, modelMap);

		return STATIONS_FOR_USERS_JSP_PAGE;
	}

	/**
	 * \ Print all Stations where manager can manage them.
	 * 
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = STATIONS_URL, method = RequestMethod.GET)
	public String manageStations(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		paggingForStations(pageNumber, resultsPerPage, modelMap);

		return STATIONS_JSP_PAGE;
	}

	/**
	 * Method genered pagging for Satations and filled modalMap to display
	 * filling model map.
	 * 
	 * @param pageNumber
	 *            Number of displaying page.
	 * @param resultsPerPage
	 *            Amount of results per page.
	 * @param modelMap
	 *            Model map to fill.
	 */
	private void paggingForStations(Integer pageNumber, Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = stationsManager.getStationsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Stations> stations = stationsManager.getStationsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put(STATIONS_LIST, stations);
	}

	/**
	 * Delete Station by Id.
	 * 
	 * @param stationId
	 * @param map
	 * @return
	 */
	@RequestMapping(value = DELETE_STATION_ID_URL, method = RequestMethod.GET)
	public String deleteStation(@PathVariable(STATION_ID) Integer stationId,
			Map<String, Object> map) {

		stationsManager.removeStations(stationId);

		return REDIRECT_STATIONS;
	}

	/**
	 * Return jsp stationEdit.
	 * 
	 * @param stationId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = STATION_EDIT_URL_GET, method = RequestMethod.GET)
	public String editStation(@PathVariable("station") Integer stationId,
			Map<String, Object> modelMap) {

		Stations station = stationsManager.findStationsById(stationId);
		modelMap.put(STATION, station);

		return STATION_EDIT_JSP_PAGE;
	}

	/**
	 * Update station to DB - RequestMethod.POST
	 * 
	 * @param stationId
	 * @param stationCode
	 * @param stationName
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = STATION_EDIT_URL_POST, method = RequestMethod.POST)
	public String updateStationToDB(
			@PathVariable(STATION_TO_EDIT) Integer stationId,
			@ModelAttribute(STATION_CODE) String stationCode,
			@ModelAttribute(STATION_NAME) String stationName,
			Map<String, Object> modelMap) {

		stationsManager.editStation(stationId, stationCode, stationName);

		return REDIRECT_STATIONS;
	}

	/**
	 * @return jsp page addStation.
	 */
	@RequestMapping(value = ADD_STATION_URL_GET, method = RequestMethod.GET)
	public String addStations() {
		return ADD_STATION_JSP_PAGE;
	}

	/**
	 * Save station to DB
	 * 
	 * @param stationCode
	 * @param stationName
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = ADD_STATION_URL_POST, method = RequestMethod.POST)
	public String addStationToBD(
			@RequestParam(STATION_CODE) String stationCode,
			@RequestParam(STATION_NAME) String stationName,
			Map<String, Object> modelMap) {

		stationsManager.createStation(stationCode, stationName);

		return REDIRECT_STATIONS;
	}

	@RequestMapping(value = "stationsoncertainline/{line}", method = RequestMethod.GET)
	public String stationsOnCertainLine(@PathVariable("line") String lineName,
			Map<String, Object> modelMap) {

		modelMap.put(STATIONS_LIST,
				stationsManager.getStationsOnCertainLine(lineName));

		return STATIONS_JSP_PAGE;
	}

}
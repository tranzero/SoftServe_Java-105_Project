package com.ita.edu.softserve.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.components.Encoder;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;

@Controller
public class LinesController {

	private static final String LINES_BY_STATION_RESULT_PAGE = "linesByStationResult";
	private static final String LINES_BY_STATION_RESULT_URL = "/linesByStationResult";
	private static final String LINES_BY_STATION_LIST = "linesByStationList";
	private static final String SORT_ORDER = "sortOrder";
	private static final String STATION_NAME = "stationName";
	private static final String LINES_BY_STATION_PAGE = "linesByStation";
	private static final String LINES_BY_STATION_URL = "/linesByStation";
	private static final String ALL_LINES = "allLines";
	private static final String ALL_LINES_PAGE = "allLinesPage";
	private static final String ALL_LINES_ADD_LINE = "newLine";
	private static final String EDIT_LINES_EDIT_LINE = "editLines";
	private static final String DELETE_LINES = "redirect:/allLines";
	private static final String DELETE_STATIONS = "redirect:/editline/";
	private static final String EDIT_STATIONS = "redirect:/editline/";
	private static final String ADD_STATIONS = "addStationsToLine";
	private static final String APPLY_CHANGES = "redirect:/allLines";

	/**
	 * URL pattern that map linesByTwoStations controller
	 */
	private static final String LINES_BY_TWO_STATIONS_URL = "/linesbytwostations";

	/**
	 * URL pattern that map linesByTwoStationsPage controller
	 */
	private static final String LINES_BY_TWO_STATIONS_AJAX_URL = "/linesbytwostationsPage";

	/**
	 * List that contains lines
	 */
	private static final String LINES_LIST = "LinesList";

	/**
	 * LinesByTwoStations jsp page
	 */
	private static final String LINES_BY_TWO_STATIONS_JSP_PAGE = "linesbytwostations";

	/**
	 * LinesByTwoStations jsp page with paging
	 */
	private static final String LINES_BY_TWO_STATIONS_AJAX_JSP_PAGE = "linesbytwostationsPage";

	/**
	 * Variable that represents first station name
	 */
	private static final String STATION_NAME1 = "stationName1";

	/**
	 * Variable that represents second station name
	 */
	private static final String STATION_NAME2 = "stationName2";

	/**
	 * Field for using paginator manager
	 */
	private PaginationManager pageMan = PaginationManager.getInstance();

	/**
	 * Field for using line manager
	 */
	@Autowired
	private LinesManager linesManager;

	/**
	 * Field for using stations manager
	 */
	@Autowired
	private StationsManager stationsManager;

	/**
	 * Field for using stationOnLine manager
	 */
	@Autowired
	private StationOnLineManager stationOnLineManager;

	/**
	 * Field for using stations criteria
	 */
	@Autowired
	StationsCriteriaContainer stationsCriteriaContainer;

	/**
	 * Field for using Encoder
	 */
	@Autowired
	Encoder encoder;

	/**
	 * Field for using pageInfo container
	 */
	@Autowired
	PageInfoContainer container;

	private void putSearchStringIsEmpty(
			StationsCriteriaContainer stationsCriteriaContainer,
			Map<String, Object> modelMap) {
		modelMap.put("isSearchString", ValidatorUtil
				.isEmptyString(stationsCriteriaContainer.getSearchString()));
	}

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
		pageMan.validatePaging(container);

		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put("container", stationsCriteriaContainer);
		modelMap.put("encoder", encoder);
		modelMap.put("stationsList", stationsManager
				.getStationsForLimitUsingContainers(stationsCriteriaContainer,
						container));

		modelMap.put("language", locale.getLanguage());

	}

	@RequestMapping(value = "/allLines", method = RequestMethod.GET)
	public String allLines(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (sortOrder == null) {
			sortOrder = 0;
		}

		long count = linesManager.getAllLinesCount();
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put("Lines", linesManager.getAllLinesForPage(
				(int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));

		return ALL_LINES;
	}

	@RequestMapping(value = "/allLinesPage", method = RequestMethod.GET)
	public String allLinesPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {
		if (sortOrder == null) {
			sortOrder = 0;
		}

		long count = linesManager.getAllLinesCount();
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put("Lines", linesManager.getAllLinesForPage(
				(int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));

		return ALL_LINES_PAGE;
	}

	@RequestMapping(value = "addline", method = RequestMethod.GET)
	public String addLine(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Stations.SEARCH_STRING, required = false) String searchString,
			@RequestParam(value = "orderByParam", required = false) String orderByParam,
			@RequestParam(value = "orderByDirection", required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		deployStationsParameters(pageNumber, resultsPerPage, searchString,
				orderByParam, orderByDirection, modelMap, locale);
		return ALL_LINES_ADD_LINE;
	}

	@RequestMapping(value = "addlinePage", method = RequestMethod.GET)
	public String addLinePg(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Stations.SEARCH_STRING, required = false) String searchString,
			@RequestParam(value = "orderByParam", required = false) String orderByParam,
			@RequestParam(value = "orderByDirection", required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		deployStationsParameters(pageNumber, resultsPerPage, searchString,
				orderByParam, orderByDirection, modelMap, locale);
		return "newLinePage";
	}

	@RequestMapping(value = "editline/{lineId}", method = RequestMethod.GET)
	public String editLine(Map<String, Object> modelMap,
			@PathVariable("lineId") Integer lineId) {
		try {
			modelMap.put("stationsOnLine",
					stationsManager.getStationsOnCertainLine(lineId));
			modelMap.put("lineName", linesManager.findByLineId(lineId)
					.getLineName());
			modelMap.put("lineId", linesManager.findByLineId(lineId)
					.getLineId());
		} catch (StationManagerException e) {
			e.printStackTrace();
		}
		return EDIT_LINES_EDIT_LINE;
	}

	@RequestMapping(value = "deleteline/{lineId}", method = RequestMethod.GET)
	public String deleteLine(@PathVariable("lineId") Integer lineId) {
		linesManager.deleteLine(lineId);
		return DELETE_LINES;
	}

	@RequestMapping(value = "editline/deletestation/{stationId}/{lineId}", method = RequestMethod.GET)
	public String deleteStation(@PathVariable("stationId") Integer stationId,
			@PathVariable("lineId") Integer lineId) {
		stationOnLineManager.removeStation(stationId, lineId);
		return EDIT_STATIONS + lineId;
	}

	@RequestMapping(value = "editline/addstation/{lineId}", method = RequestMethod.GET)
	public String addStation(@PathVariable("lineId") Integer lineId,
			Map<String, Object> modelMap) {
		try {
			modelMap.put("allStations",
					stationsManager.getStationsNotOnCertainLine(lineId));
		} catch (StationManagerException e) {
			e.printStackTrace();
		}

		return ADD_STATIONS;
	}

	@RequestMapping(value = "editline/addstation/changestations/{lineId}", method = RequestMethod.POST)
	public String changeStations(
			@RequestParam("stationsCheck") List<Integer> stationsId,
			@PathVariable("lineId") Integer lineId) {
		stationOnLineManager.updateStationOnLine(lineId, stationsId);
		return DELETE_STATIONS + lineId;
	}

	@RequestMapping(value = "confirmcreating", method = RequestMethod.POST)
	public String confirmCreating(
			@ModelAttribute("newLineName") String lineName,
			@RequestParam("stationsCheck") List<Integer> stationsId) {
		linesManager.createLine(lineName);
		stationOnLineManager.addStationsToLine(
				linesManager.findByLineName(lineName).getLineId(), stationsId);
		return APPLY_CHANGES;
	}

	@RequestMapping(value = "editline/applychanges", method = RequestMethod.POST)
	public String applyChanges(@ModelAttribute("linename") String lineName,
			@ModelAttribute("lineid") Integer lineId) {
		linesManager.updateLine(lineId, lineName);
		return APPLY_CHANGES;
	}

	/**
	 * 
	 * @param stationName1
	 *            - name of first station
	 * @param stationName2
	 *            - name of second station
	 * @param pageNumber
	 *            - number of page, to get results for
	 * @param resultsPerPage
	 *            - result per page
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @param modelMap
	 *            - modelmap to fill
	 * @return linesbytwostations page
	 */
	@RequestMapping(value = LINES_BY_TWO_STATIONS_URL, method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam(value = STATION_NAME1, required = false) String stationName1,
			@RequestParam(value = STATION_NAME2, required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.isEmpty() || stationName2.isEmpty()) {
			return LINES_BY_TWO_STATIONS_JSP_PAGE;
		}
		if (sortOrder == null) {
			sortOrder = 0;
		}

		long count = linesManager.getLinesByTwoStListCount(stationName1,
				stationName2);
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put(LINES_LIST, linesManager.getLinesByTwoStForPage(
				stationName1, stationName2, (int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));

		return LINES_BY_TWO_STATIONS_JSP_PAGE;
	}

	/**
	 * 
	 * @param stationName1
	 *            - name of first station
	 * @param stationName2
	 *            - name of second station
	 * @param pageNumber
	 *            - number of page, to get results for
	 * @param resultsPerPage
	 *            - result per page
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @param modelMap
	 *            - modelmap to fill
	 * @return linesbytwostations page
	 */
	@RequestMapping(value = LINES_BY_TWO_STATIONS_AJAX_URL, method = RequestMethod.GET)
	public String getLinesByTwoStationsPage(
			@RequestParam(value = STATION_NAME1, required = false) String stationName1,
			@RequestParam(value = STATION_NAME2, required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.isEmpty() || stationName2.isEmpty()) {
			return LINES_BY_TWO_STATIONS_AJAX_JSP_PAGE;
		}
		if (sortOrder == null) {
			sortOrder = 0;
		}

		long count = linesManager.getLinesByTwoStListCount(stationName1,
				stationName2);
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put(LINES_LIST, linesManager.getLinesByTwoStForPage(
				stationName1, stationName2, (int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));

		return LINES_BY_TWO_STATIONS_AJAX_JSP_PAGE;
	}

	@RequestMapping(value = LINES_BY_STATION_URL, method = RequestMethod.GET)
	public String linesByStationGet(
			@RequestParam(value = STATION_NAME, required = false) String stationName,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName == null || stationName.equals("")) {
			return LINES_BY_STATION_PAGE;
		}

		long count = linesManager.getLinesByStationCount(stationName);
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put(
				LINES_BY_STATION_LIST,
				linesManager.getLinesByStNameForPage(stationName,
						container.getPageNumber(),
						container.getResultsPerPage(), 1));

		return LINES_BY_STATION_PAGE;

	}

	@RequestMapping(value = LINES_BY_STATION_RESULT_URL, method = RequestMethod.GET)
	public String linesByStationresult(
			@RequestParam(value = STATION_NAME, required = false) String stationName,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName == null || stationName.equals("")) {
			return LINES_BY_STATION_RESULT_PAGE;
		}

		long count = linesManager.getLinesByStationCount(stationName);
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put(
				LINES_BY_STATION_LIST,
				linesManager.getLinesByStNameForPage(stationName,
						container.getPageNumber(),
						container.getResultsPerPage(), 1));

		return LINES_BY_STATION_RESULT_PAGE;

	}

}

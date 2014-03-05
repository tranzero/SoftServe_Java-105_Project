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
	private static final String pageNumberKey = "pageNumber";
	private static final String resultsPerPageKey = "resultsPerPage";
	private static final String sizeOfPagingKey = "sizeOfPaging";
	private static final String maxPageCountKey = "maxPageCount";
	private static final String maxResultCountKey = "maxResultCount";
	private static final String allLines = "allLines";
	private static final String allLinesPage = "allLinesPage";
	private static final String allLinesAddLine = "newLine";
	private static final String editLinesEditLine = "editLines";
	private static final String deleteLines = "redirect:/allLines";
	private static final String deleteStations = "redirect:/editline/";
	private static final String editStations = "redirect:/editline/";
	private static final String addStations = "addStationsToLine";
	private static final String applyChanges = "redirect:/allLines";

	private PaginationManager pageMan = PaginationManager.getInstance();

	@Autowired
	private LinesManager linesManager;

	@Autowired
	private StationsManager stationsManager;

	@Autowired
	private StationOnLineManager stationOnLineManager;

	@Autowired
	StationsCriteriaContainer stationsCriteriaContainer;

	@Autowired
	Encoder encoder;
	
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
	public String allLines(@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
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

	modelMap.put("Lines", linesManager.getAllLinesForPage((int) container.getPageNumber(),
			(int) container.getResultsPerPage(), sortOrder));
		
		/*
		modelMap.put(pageNumberKey, pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());
		int maxPageCount = pageMan.getMaxPageCount(
				pageMan.getDefaultResultPerPage(),
				linesManager.getLinesListCount());
		modelMap.put(maxPageCountKey, maxPageCount);*/
		return allLines;
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

		modelMap.put("Lines", linesManager.getAllLinesForPage((int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));
		
		/*
		long resultCount = linesManager.getLinesListCount();
		modelMap.put(maxResultCountKey, resultCount);
		int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		modelMap.put(maxPageCountKey, maxPageCount);
		int currentPagingPosition = pageMan.getCurrentPagingPosition(
				pageNumber, resultsPerPage);
		modelMap.put(pageNumberKey, pageNumber);
		modelMap.put(resultsPerPageKey, resultsPerPage);
		modelMap.put("lines", linesManager.getLinesForPage(
				currentPagingPosition, resultsPerPage));*/

		return allLinesPage;
	}

	@RequestMapping(value = "addline", method = RequestMethod.GET)
	public String addLine(@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Stations.SEARCH_STRING, required = false) String searchString,
			@RequestParam(value = "orderByParam", required = false) String orderByParam,
			@RequestParam(value = "orderByDirection", required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		deployStationsParameters(pageNumber, resultsPerPage, searchString,
				orderByParam, orderByDirection, modelMap, locale);
		return allLinesAddLine;
	}
	
	@RequestMapping(value = "addlinePage", method = RequestMethod.GET)
	public String addLinePg(@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
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
		return editLinesEditLine;
	}

	@RequestMapping(value = "deleteline/{lineId}", method = RequestMethod.GET)
	public String deleteLine(@PathVariable("lineId") Integer lineId) {
		linesManager.deleteLine(lineId);
		return deleteLines;
	}

	@RequestMapping(value = "editline/deletestation/{stationId}/{lineId}", method = RequestMethod.GET)
	public String deleteStation(@PathVariable("stationId") Integer stationId,
			@PathVariable("lineId") Integer lineId) {
		stationOnLineManager.removeStation(stationId, lineId);
		return editStations + lineId;
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

		return addStations;
	}

	@RequestMapping(value = "editline/addstation/changestations/{lineId}", method = RequestMethod.POST)
	public String changeStations(
			@RequestParam("stationsCheck") List<Integer> stationsId,
			@PathVariable("lineId") Integer lineId) {
		stationOnLineManager.updateStationOnLine(lineId, stationsId);
		return deleteStations + lineId;
	}

	@RequestMapping(value = "confirmcreating", method = RequestMethod.POST)
	public String confirmCreating(
			@ModelAttribute("newLineName") String lineName,
			@RequestParam("stationsCheck") List<Integer> stationsId) {
		linesManager.createLine(lineName);
		stationOnLineManager.addStationsToLine(
				linesManager.findByLineName(lineName).getLineId(), stationsId);
		return applyChanges;
	}

	@RequestMapping(value = "editline/applychanges", method = RequestMethod.GET)
	public String applyChanges() {
		return applyChanges;
	}

	@RequestMapping(value = "/linesbytwostations", method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam(value = "stationName1", required = false) String stationName1,
			@RequestParam(value = "stationName2", required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.equals("") || stationName2.equals("")) {
			return "linesbytwostations";
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

		modelMap.put("LinesList", linesManager.getLinesByTwoStForPage(
				stationName1, stationName2, (int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));

		return "linesbytwostations";
	}

	@RequestMapping(value = "/linesbytwostationsPage", method = RequestMethod.GET)
	public String getLinesByTwoStationsPage(
			@RequestParam(value = "stationName1", required = false) String stationName1,
			@RequestParam(value = "stationName2", required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = SORT_ORDER, required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.equals("") || stationName2.equals("")) {
			return "linesbytwostationsPage";
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

		modelMap.put("LinesList", linesManager.getLinesByTwoStForPage(
				stationName1, stationName2, (int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));

		return "linesbytwostationsPage";
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

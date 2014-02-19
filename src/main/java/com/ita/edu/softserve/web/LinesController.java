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

import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.PageInfoContainer;

@Controller
public class LinesController {

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

	@RequestMapping(value = "/allLines", method = RequestMethod.GET)
	public String allLines(Map<String, Object> modelMap) {
		modelMap.put(pageNumberKey, pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());
		int maxPageCount = pageMan.getMaxPageCount(
				pageMan.getDefaultResultPerPage(),
				linesManager.getLinesListCount());
		modelMap.put(maxPageCountKey, maxPageCount);
		return allLines;
	}

	@RequestMapping(value = "/allLinesPage", method = RequestMethod.POST)
	public String allLinesPage(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("resultsPerPage") int resultsPerPage,
			Map<String, Object> modelMap) {
		long resultCount = linesManager.getLinesListCount();
		modelMap.put(maxResultCountKey, resultCount);
		int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		modelMap.put(maxPageCountKey, maxPageCount);
		int currentPagingPosition = pageMan.getCurrentPagingPosition(
				pageNumber, resultsPerPage);
		modelMap.put(pageNumberKey, pageNumber);
		modelMap.put(resultsPerPageKey, resultsPerPage);
		modelMap.put("lines", linesManager.getLinesForPage(
				currentPagingPosition, resultsPerPage));

		return allLinesPage;
	}

	@RequestMapping(value = "addline", method = RequestMethod.GET)
	public String addLine(Map<String, Object> modelMap) {
		try {
			modelMap.put("stations", stationsManager.findAllStations());
		} catch (StationManagerException e) {
			e.printStackTrace();
		}
		return allLinesAddLine;
	}

	@RequestMapping(value = "editline/{lineName}", method = RequestMethod.GET)
	public String editLine(Map<String, Object> modelMap,
			@PathVariable("lineName") String lineName) {
		try {
			modelMap.put("stationsOnLine",
					stationsManager.getStationsOnCertainLine(lineName));
		} catch (StationManagerException e) {
			e.printStackTrace();
		}
		return editLinesEditLine;
	}

	@RequestMapping(value = "deleteline/{lineName}", method = RequestMethod.GET)
	public String deleteLine(@PathVariable("lineName") String lineName) {
		linesManager.deleteLine(lineName);
		return deleteLines;
	}

	@RequestMapping(value = "editline/deletestation/{stationId}/{lineName}", method = RequestMethod.GET)
	public String deleteStation(@PathVariable("stationId") Integer stationId,
			@PathVariable("lineName") String lineName) {
		stationOnLineManager.removeStation(stationId, linesManager
				.findByLineName(lineName).getLineId());
		return editStations + lineName;
	}

	@RequestMapping(value = "editline/addstation/{lineName}", method = RequestMethod.GET)
	public String addStation(@PathVariable("lineName") String lineName,
			Map<String, Object> modelMap) {
		try {
			modelMap.put("allStations",
					stationsManager.getStationsNotOnCertainLine(lineName));
			modelMap.put("existStations",
					stationsManager.getStationsOnCertainLine(lineName));
		} catch (StationManagerException e) {
			e.printStackTrace();
		}

		return addStations;
	}

	@RequestMapping(value = "editline/addstation/changestations/{lineName}", method = RequestMethod.POST)
	public String changeStations(
			@RequestParam("stationsCheck") List<String> stations,
			@PathVariable("lineName") String lineName) {
		stationOnLineManager.updateStationOnLine(
				linesManager.findByLineName(lineName).getLineId(), stations);
		return deleteStations + lineName;
	}

	@RequestMapping(value = "confirmcreating", method = RequestMethod.POST)
	public String confirmCreating(
			@ModelAttribute("newLineName") String lineName,
			@RequestParam("stationsCheck") List<String> stations) {
		linesManager.createLine(lineName);
		stationOnLineManager.updateStationOnLine(
				linesManager.findByLineName(lineName).getLineId(), stations);
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
			@RequestParam(value = "sortOrder", required = false) Integer sortOrder,
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
		PageInfoContainer container = new PageInfoContainer(pageNumber,
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
			@RequestParam(value = "sortOrder", required = false) Integer sortOrder,
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
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put("LinesList", linesManager.getLinesByTwoStForPage(
				stationName1, stationName2, (int) container.getPageNumber(),
				(int) container.getResultsPerPage(), sortOrder));
		
		return "linesbytwostationsPage";
	}

	@RequestMapping(value = "/linesbystation", method = RequestMethod.GET)
	public String linesByStationGet(
			@RequestParam(value = "stationName", required = false) String stationName,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = "sortOrder", required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName == null || stationName.equals("")) {
			return "linesbystation";
		}

		long count = linesManager.getLinesByStationCount(stationName);
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put(
				"linesbystationlist",
				linesManager.getLinesByStNameForPage(stationName,
						container.getPageNumber(),
						container.getResultsPerPage(),sortOrder));

		return "linesbystation";

	}

	@RequestMapping(value = "/linesbystationresult", method = RequestMethod.GET)
	public String linesByStationresult(
			@RequestParam(value = "stationName", required = false) String stationName,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = "sortOrder", required = false) Integer sortOrder,
			Map<String, Object> modelMap) {

		if (stationName == null || stationName.equals("")) {
			return "linesbystationresult";
		}

		long count = linesManager.getLinesByStationCount(stationName);
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		pageMan.validatePaging(container);
		PagingController.deployPaging(modelMap, container, pageMan);

		modelMap.put(
				"linesbystationlist",
				linesManager.getLinesByStNameForPage(stationName,
						container.getPageNumber(),
						container.getResultsPerPage(),sortOrder));

		return "linesbystationresult";

	}

}

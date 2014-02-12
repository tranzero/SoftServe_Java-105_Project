package com.ita.edu.softserve.web;

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
import com.ita.edu.softserve.utils.ExceptionUtil;
import com.ita.edu.softserve.utils.PageInfoContainer;

@Controller
public class StationsController {

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
	@RequestMapping(value = "/stationsForUsers", method = RequestMethod.GET)
	public String listStations(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = stationsManager.getStationsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("stationsList", stationsManager.getStationsForPage(
				container.getPageNumber(), container.getResultsPerPage()));

		return "stationsForUsers";
	}

	/**\
	 * Print all Stations where manager can manage them.
	 *
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/stations", method = RequestMethod.GET)
	public String manageStations(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = stationsManager.getStationsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("stationsList", stationsManager.getStationsForPage(
				container.getPageNumber(), container.getResultsPerPage()));

		return "stations";
	}

	/**
	 * Delete Station by Id.
	 * 
	 * @param stationId
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/delete/{stationId}", method = RequestMethod.GET)
	public String deleteStation(@PathVariable("stationId") Integer stationId,
			Map<String, Object> map) {
		try {
			stationsManager.removeStations(stationId);
		} catch (StationManagerException e) {
			map.put("incorrectMsg", "Incorrect Station!!!");
			return "result";
		}

		return "redirect:/stations";
	}

	/**
	 * Return jsp stationEdit.
	 * 
	 * @param stationId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/stationEdit/{station}", method = RequestMethod.GET)
	public String editStation(@PathVariable("station") Integer stationId,
			Map<String, Object> modelMap) {

		try {
			Stations station = stationsManager.findStationsById(stationId);
			modelMap.put("station", station);
		} catch (StationManagerException e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}
		return "stationEdit";
	}

	/**
	 *  Update station to DB - RequestMethod.POST
	 * 
	 * @param stationId
	 * @param stationCode
	 * @param stationName
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/stationEdit/{stationToEdit}", method = RequestMethod.POST)
	public String updateStationToDB(
			@PathVariable("stationToEdit") Integer stationId,
			@ModelAttribute("stationCode") String stationCode,
			@ModelAttribute("stationName") String stationName,
			Map<String, Object> modelMap) {
		try {
			stationsManager.editStation(stationId, stationCode, stationName);
		} catch (StationManagerException e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}
		return "redirect:/stations";
	}

	/**
	 * @return jsp page addStation.
	 */
	@RequestMapping(value = "/addStation", method = RequestMethod.GET)
	public String addStations() {
		return "addStation";
	}

	/**
	 * Save station to DB
	 * 
	 * @param stationCode
	 * @param stationName
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/addstat", method = RequestMethod.POST)
	public String addStationToBD(
			@RequestParam("stationCode") String stationCode,
			@RequestParam("stationName") String stationName,
			Map<String, Object> modelMap) {
		try {
			stationsManager.createStation(stationCode, stationName);
		} catch (StationManagerException e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}
		return "redirect:/stations";
	}

	@RequestMapping(value = "stationsoncertainline/{line}", method = RequestMethod.GET)
	public String stationsOnCertainLine(@PathVariable("line") String lineName,
			Map<String, Object> modelMap) {
		try {
			modelMap.put("stationsList",
					stationsManager.getStationsOnCertainLine(lineName));
		} catch (StationManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "stations";
	}

}
package com.ita.edu.softserve.web;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.PageInfoContainer;

@Controller
public class TransportController {

	private static final Logger LOGGER = Logger
			.getLogger(TransportController.class);

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	private TransportsManager transportsManager;

	@Autowired
	private StationOnLineManager stationOnLineManager;

	/**
	 * Prints all transports in browser.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/transport", method = RequestMethod.GET)
	public String printTransports(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(
				"transportsList",
				transportsManager.getTransportsForPage(
						container.getPageNumber(),
						container.getResultsPerPage()));
		return "transport";
	}

	@RequestMapping(value = "/transportpage", method = RequestMethod.GET)
	public String printTransportPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(
				"transportsList",
				transportsManager.getTransportsForPage(
						container.getPageNumber(),
						container.getResultsPerPage()));
		return "transportpage";
	}

	/*--------------------------------------for HTML FORM-----------------------------------*/
	/**
	 * Map name of jsp addTransport to formTransport.htm.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/formTransport.htm", method = RequestMethod.GET)
	public String transportForm() {
		return "addTransport";
	}

	/**
	 * Adds new transport into database.
	 * 
	 * @param transportCode
	 * @param startTime
	 * @param routesCode
	 * @param seatclass1
	 * @param seatclass2
	 * @param seatclass3
	 * @param genprice
	 * @return
	 */
	@RequestMapping(value = "/addTransport.htm", method = RequestMethod.POST)
	public String addTransportToBD(
			@ModelAttribute("transportCode") String transportCode,
			@ModelAttribute("startTime") String startTime,
			@ModelAttribute("routes") String routesCode,
			@ModelAttribute("seatclass1") String seatclass1,
			@ModelAttribute("seatclass2") String seatclass2,
			@ModelAttribute("seatclass3") String seatclass3,
			@ModelAttribute("genprice") String genprice) {

		transportsManager.saveOrUpdateTransport(null, transportCode, startTime,
				routesCode, seatclass1, seatclass2, seatclass3, genprice);

		return "redirect:/transport";
	}

	/*--------------------------------------for Spring FORM-----------------------------------*/

	/*
	 * @RequestMapping("/formTransport.htm") public String
	 * transportForm(Map<String, Object> map) { map.put("transport", new
	 * Transports()); return "addTransport"; }
	 * 
	 * @RequestMapping(value = "/addTransport.htm", method = RequestMethod.POST)
	 * public String addTransportToBD(
	 * 
	 * @ModelAttribute("transport") Transports transport, BindingResult result)
	 * { try { transportsManager.saveTransports(transport); } catch (Exception
	 * e) { LOGGER.error(e.toString()); } return "redirect:/transport"; }
	 */

	/**
	 * Gets transports ID from field in table and finds it in database then puts
	 * it in map as request attribute.
	 * 
	 * @param transportId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/editTransport/{transport}", method = RequestMethod.GET)
	public String editTransport(@PathVariable("transport") Integer transportId,
			Map<String, Object> modelMap) {
		Transports transport = transportsManager
				.findTransportsById(transportId);
		modelMap.put("transport", transport);

		return "editTransport";
	}

	/**
	 * Gets transport object and save it into database.
	 * 
	 * @param transportId
	 * @param transportCode
	 * @param startTime
	 * @param routes
	 * @param seatclass1
	 * @param seatclass2
	 * @param seatclass3
	 * @param genprice
	 * @return
	 */
	@RequestMapping(value = "/editTransport/{transportId}", method = RequestMethod.POST)
	public String updateTransportToDB(
			@PathVariable("transportId") Integer transportId,
			@ModelAttribute("transportCode") String transportCode,
			@ModelAttribute("startTime") String startTime,
			@ModelAttribute("routes") String routes,
			@ModelAttribute("seatclass1") String seatclass1,
			@ModelAttribute("seatclass2") String seatclass2,
			@ModelAttribute("seatclass3") String seatclass3,
			@ModelAttribute("genprice") String genprice) {

		transportsManager
				.saveOrUpdateTransport(transportId, transportCode, startTime,
						routes, seatclass1, seatclass2, seatclass3, genprice);

		return "redirect:/transport";
	}

	/**
	 * Deletes Transport from database.
	 * 
	 * @param transportId
	 * @return
	 */
	@RequestMapping(value = "/removeTransport/{transportToRemove}", method = RequestMethod.GET)
	public String removeTransport(
			@PathVariable("transportToRemove") Integer transportId) {
		transportsManager.removeTransportById(transportId);

		return "redirect:/transport";
	}

	/**
	 * Prints in browser all stations on certain line.
	 * 
	 * @param lineId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getsLineId/{lineIdOnstations}", method = RequestMethod.GET)
	public String printStationOnLine(
			@PathVariable("lineIdOnstations") Integer lineId,
			Map<String, Object> modelMap) {
		List<StationsOnLine> listOfStationsOnLine = stationOnLineManager
				.findStationsOnLine(lineId);

		modelMap.put("listOfStationsOnLine", listOfStationsOnLine);

		return "listOfStationsOnLine";
	}

	/*------------------------------------------------------------------------------*/

	@RequestMapping(value = "/transportTravel", method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam(value = "stationName1", required = false) String stationName1,
			@RequestParam(value = "stationName2", required = false) String stationName2,
			Map<String, Object> model) {

		if (stationName1 == null || stationName2 == null || 
				stationName1.equals("") || stationName2.equals("")) {
			return "transportTravel";
		}

		model.put("TransportTravelList", transportsManager
				.getTransportByTwoStations(stationName1, stationName2));

		return "transportTravel";
	}

}

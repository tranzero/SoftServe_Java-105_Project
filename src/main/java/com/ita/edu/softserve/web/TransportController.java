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

import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.PageInfoContainer;

@Controller
public class TransportController {

	private static final String TRANSPORTPAGE_VIEW_JSP = "transportpageView";
	private static final String TRANSPORTPAGE_VIEW_URL_PATTERN = "/transportpageView";
	private static final String TRANSPORT_VIEW_JSP = "transportView";
	private static final String TRANSPORT_VIEW_URL_PATTERN = "/transportView";
	private static final String TRANSPORT_TRAVEL_LIST = "TransportTravelList";
	private static final String TRANSPORT_TRAVEL_JSP = "transportTravel";
	private static final String STATION_NAME2_REQUEST_PARAM = "stationName2";
	private static final String STATION_NAME1_REQUEST_PARAM = "stationName1";
	private static final String TRANSPORT_TRAVEL_URL_PATTERN = "/transportTravel";
	private static final String LIST_OF_STATIONS_ON_LINE = "listOfStationsOnLine";
	private static final String LINE_ID_ONSTATIONS_PATH_VERIABLE = "lineIdOnstations";
	private static final String GETS_LINE_ID_LINE_ID_ONSTATIONS = "/getsLineId/{lineIdOnstations}";
	private static final String TRANSPORT_TO_REMOVE_PATH_VERIABLE = "transportToRemove";
	private static final String REMOVE_TRANSPORT_TRANSPORT_TO_REMOVE = "/removeTransport/{transportToRemove}";
	private static final String TRANSPORT_ID_MODEL_ATTRIBUTE = "transportId";
	private static final String EDIT_TRANSPORT_TRANSPORT_ID = "/editTransport/{transportId}";
	private static final String EDIT_TRANSPORT_JSP = "editTransport";
	private static final String EDIT_TRANSPORT_TRANSPORT = "/editTransport/{transport}";
	private static final String REDIRECT_TRANSPORT = "redirect:/transport";
	private static final String GENPRICE_MODEL_ATTRIBUTE = "genprice";
	private static final String SEATCLASS3_MODEL_ATTRIBUTE = "seatclass3";
	private static final String SEATCLASS2_MODEL_ATTRIBUTE = "seatclass2";
	private static final String SEATCLASS1_MODEL_ATTRIBUTE = "seatclass1";
	private static final String ROUTES_MODEL_ATTRIBUTE = "routes";
	private static final String START_TIME_MODEL_ATTRIBUTE = "startTime";
	private static final String TRANSPORT_CODE_MODEL_ATTRIBUTE = "transportCode";
	private static final String ADD_TRANSPORT_URL_PATTERN = "/addTransport.htm";
	private static final String ADD_TRANSPORT_JSP = "addTransport";
	private static final String FORM_TRANSPORT_URL_PATTERN = "/formTransport.htm";
	private static final String TRANSPORTPAGE_JSP = "transportpage";
	private static final String TRANSPORTPAGE_URL_PATTERN = "/transportpage";
	private static final String TRANSPORTS_LIST_NAME = "transportsList";
	private static final String TRANSPORT_JSP = "transport";
	private static final String TRANSPORT_URL_PATTERN = "/transport";

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	private TransportsManager transportsManager;

	@Autowired
	private StationOnLineManager stationOnLineManager;

	/**
	 * Prints transports in browser.
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = TRANSPORT_VIEW_URL_PATTERN, method = RequestMethod.GET)
	public String printTransportsView(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Transports> transports = transportsManager.getTransportsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put(TRANSPORTS_LIST_NAME, transports);

		return TRANSPORT_VIEW_JSP;
	}

	/**
	 * Prints page transports in browser.
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = TRANSPORTPAGE_VIEW_URL_PATTERN, method = RequestMethod.GET)
	public String printTransportPageView(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Transports> transports = transportsManager.getTransportsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put(TRANSPORTS_LIST_NAME, transports);

		return TRANSPORTPAGE_VIEW_JSP;
	}

	/**
	 * Prints transports in browser with button to edit and delete rows.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = TRANSPORT_URL_PATTERN, method = RequestMethod.GET)
	public String printTransports(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Transports> transports = transportsManager.getTransportsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put(TRANSPORTS_LIST_NAME, transports);

		return TRANSPORT_JSP;
	}

	/**
	 * Prints page transports in browser.
	 * 
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = TRANSPORTPAGE_URL_PATTERN, method = RequestMethod.GET)
	public String printTransportPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Transports> transports = transportsManager.getTransportsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put(TRANSPORTS_LIST_NAME, transports);

		return TRANSPORTPAGE_JSP;
	}

	/**
	 * Map name of jsp addTransport to formTransport.htm.
	 * 
	 * @return
	 */
	@RequestMapping(value = FORM_TRANSPORT_URL_PATTERN, method = RequestMethod.GET)
	public String transportForm() {
		return ADD_TRANSPORT_JSP;
	}

	/**
	 * Adds new transport into the Transports table.
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
	@RequestMapping(value = ADD_TRANSPORT_URL_PATTERN, method = RequestMethod.POST)
	public String addTransportToBD(
			@ModelAttribute(TRANSPORT_CODE_MODEL_ATTRIBUTE) String transportCode,
			@ModelAttribute(START_TIME_MODEL_ATTRIBUTE) String startTime,
			@ModelAttribute(ROUTES_MODEL_ATTRIBUTE) String routesCode,
			@ModelAttribute(SEATCLASS1_MODEL_ATTRIBUTE) String seatclass1,
			@ModelAttribute(SEATCLASS2_MODEL_ATTRIBUTE) String seatclass2,
			@ModelAttribute(SEATCLASS3_MODEL_ATTRIBUTE) String seatclass3,
			@ModelAttribute(GENPRICE_MODEL_ATTRIBUTE) String genprice) {

		transportsManager.saveOrUpdateTransport(null, transportCode, startTime,
				routesCode, seatclass1, seatclass2, seatclass3, genprice);

		return REDIRECT_TRANSPORT;
	}

	/**
	 * Gets transports ID from the Transports table and finds it in the
	 * Transports table then puts found object in Map as request attribute.
	 * 
	 * @param transportId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = EDIT_TRANSPORT_TRANSPORT, method = RequestMethod.GET)
	public String editTransport(
			@PathVariable(TRANSPORT_JSP) Integer transportId,
			Map<String, Object> modelMap) {
		Transports transport = transportsManager
				.findTransportsById(transportId);
		modelMap.put(TRANSPORT_JSP, transport);

		return EDIT_TRANSPORT_JSP;
	}

	/**
	 * Gets a transport object and save it into the Transports table.
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
	@RequestMapping(value = EDIT_TRANSPORT_TRANSPORT_ID, method = RequestMethod.POST)
	public String updateTransportToDB(
			@PathVariable(TRANSPORT_ID_MODEL_ATTRIBUTE) Integer transportId,
			@ModelAttribute(TRANSPORT_CODE_MODEL_ATTRIBUTE) String transportCode,
			@ModelAttribute(START_TIME_MODEL_ATTRIBUTE) String startTime,
			@ModelAttribute(ROUTES_MODEL_ATTRIBUTE) String routes,
			@ModelAttribute(SEATCLASS1_MODEL_ATTRIBUTE) String seatclass1,
			@ModelAttribute(SEATCLASS2_MODEL_ATTRIBUTE) String seatclass2,
			@ModelAttribute(SEATCLASS3_MODEL_ATTRIBUTE) String seatclass3,
			@ModelAttribute(GENPRICE_MODEL_ATTRIBUTE) String genprice) {

		transportsManager
				.saveOrUpdateTransport(transportId, transportCode, startTime,
						routes, seatclass1, seatclass2, seatclass3, genprice);

		return REDIRECT_TRANSPORT;
	}

	/**
	 * Deletes a transport from the Transports table.
	 * 
	 * @param transportId
	 * @return
	 */
	@RequestMapping(value = REMOVE_TRANSPORT_TRANSPORT_TO_REMOVE, method = RequestMethod.GET)
	public String removeTransport(
			@PathVariable(TRANSPORT_TO_REMOVE_PATH_VERIABLE) Integer transportId) {
		transportsManager.removeTransportById(transportId);

		return REDIRECT_TRANSPORT;
	}

	/**
	 * Prints in browser all stations on certain line.
	 * 
	 * @param lineId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = GETS_LINE_ID_LINE_ID_ONSTATIONS, method = RequestMethod.GET)
	public String printStationOnLine(
			@PathVariable(LINE_ID_ONSTATIONS_PATH_VERIABLE) Integer lineId,
			Map<String, Object> modelMap) {
		List<StationsOnLine> listOfStationsOnLine = stationOnLineManager
				.findStationsOnLine(lineId);

		modelMap.put(LIST_OF_STATIONS_ON_LINE, listOfStationsOnLine);

		return LIST_OF_STATIONS_ON_LINE;
	}

	@RequestMapping(value = TRANSPORT_TRAVEL_URL_PATTERN, method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam(value = STATION_NAME1_REQUEST_PARAM, required = false) String stationName1,
			@RequestParam(value = STATION_NAME2_REQUEST_PARAM, required = false) String stationName2,
			Map<String, Object> model) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.equals("") || stationName2.equals("")) {
			return TRANSPORT_TRAVEL_JSP;
		}

		model.put(TRANSPORT_TRAVEL_LIST, transportsManager
				.getTransportByTwoStations(stationName1, stationName2));

		return TRANSPORT_TRAVEL_JSP;
	}
}

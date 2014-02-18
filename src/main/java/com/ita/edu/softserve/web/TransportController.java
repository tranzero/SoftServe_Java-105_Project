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

/**
 * Base controller class for transport.
 * 
 * @author Roman
 * 
 */
@Controller
public class TransportController {

	private static final String TRANSPORTPAGE_VIEW_JSP = "transportpageView";

	/**
	 * URL pattern that map controller printTransportPageView.
	 */
	private static final String TRANSPORTPAGE_VIEW_URL_PATTERN = "/transportpageView";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String TRANSPORT_VIEW_JSP = "transportView";

	/**
	 * URL pattern that map controller printTransportsView.
	 */
	private static final String TRANSPORT_VIEW_URL_PATTERN = "/transportView";

	/**
	 * The name of key with which the transports value is to be associated.
	 */
	private static final String TRANSPORT_TRAVEL_LIST = "TransportTravelList";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String TRANSPORT_TRAVEL_JSP = "transportTravel";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String TRANSPORT_TRAVEL_PAGE_JSP = "transportTravelPage";
	
	/**
	 * Name of request param to find lines by two stations.
	 */
	private static final String STATION_NAME1_REQUEST_PARAM = "stationName1";

	/**
	 * Name of request param to find lines by two stations.
	 */
	private static final String STATION_NAME2_REQUEST_PARAM = "stationName2";

	/**
	 * URL pattern that map controller getTransportByTwoStations.
	 */
	private static final String TRANSPORT_TRAVEL_URL_PATTERN = "/transportTravel";

	/**
	 * URL pattern that map controller getTransportByTwoStations for paging.
	 */
	private static final String TRANSPORT_TRAVEL_PAGE_URL_PATTERN = "/transportTravelPage";

	/**
	 * The name of key with which the StationsOnLine value is to be associated.
	 */
	private static final String LIST_OF_STATIONS_ON_LINE = "listOfStationsOnLine";

	/**
	 * Path variable to get from view.
	 */
	private static final String LINE_ID_ONSTATIONS_PATH_VERIABLE = "lineIdOnstations";

	/**
	 * URL pattern that map controller printStationOnLine.
	 */
	private static final String GETS_LINE_ID_LINE_ID_ONSTATIONS = "/getsLineId/{lineIdOnstations}";

	/**
	 * Path variable to get Id to remove transport.
	 */
	private static final String TRANSPORT_TO_REMOVE_PATH_VERIABLE = "transportToRemove";

	/**
	 * URL pattern that map controller removeTransport.
	 */
	private static final String REMOVE_TRANSPORT_TRANSPORT_TO_REMOVE = "/removeTransport/{transportToRemove}";

	/**
	 * Path variable to transport Id.
	 */
	private static final String TRANSPORT_ID_MODEL_ATTRIBUTE = "transportId";

	/**
	 * URL pattern that map controller updateTransportToDB.
	 */
	private static final String EDIT_TRANSPORT_TRANSPORT_ID = "/editTransport/{transportId}";
	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String EDIT_TRANSPORT_JSP = "editTransport";

	/**
	 * URL pattern that map controller editTransport.
	 */
	private static final String EDIT_TRANSPORT_TRANSPORT = "/editTransport/{transport}";

	/**
	 * Transport code model attribute to get transport code.
	 */
	private static final String TRANSPORT_CODE_MODEL_ATTRIBUTE = "transportCode";

	/**
	 * Start time model attribute to get start time.
	 */
	private static final String START_TIME_MODEL_ATTRIBUTE = "startTime";

	/**
	 * Routes code model attribute to get routes code.
	 */
	private static final String ROUTES_MODEL_ATTRIBUTE = "routes";

	/**
	 * Seat class 1 model attribute to get number of seats of class 1.
	 */
	private static final String SEATCLASS1_MODEL_ATTRIBUTE = "seatclass1";

	/**
	 * Seat class 2 model attribute to get number of seats of class 2.
	 */
	private static final String SEATCLASS2_MODEL_ATTRIBUTE = "seatclass2";

	/**
	 * Seat class 3 model attribute to get number of seats of class 3.
	 */
	private static final String SEATCLASS3_MODEL_ATTRIBUTE = "seatclass3";

	/**
	 * General price model attribute to get general price.
	 */
	private static final String GENPRICE_MODEL_ATTRIBUTE = "genprice";

	/**
	 * The name of jsp that defines Spring to redirect.
	 */
	private static final String REDIRECT_TRANSPORT = "redirect:/transport";

	/**
	 * URL pattern that map controller addTransportToBD.
	 */
	private static final String ADD_TRANSPORT_URL_PATTERN = "/addTransport.htm";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String ADD_TRANSPORT_JSP = "addTransport";

	/**
	 * URL pattern that map controller transportForm.
	 */
	private static final String FORM_TRANSPORT_URL_PATTERN = "/formTransport.htm";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String TRANSPORTPAGE_JSP = "transportpage";

	/**
	 * URL pattern that map controller printTransportPage.
	 */
	private static final String TRANSPORTPAGE_URL_PATTERN = "/transportpage";

	/**
	 * The name of key with which the transports value is to be associated.
	 */
	private static final String TRANSPORTS_LIST_NAME = "transportsList";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String TRANSPORT = "transport";

	/**
	 * URL pattern that map controller printTransports.
	 */
	private static final String TRANSPORT_URL_PATTERN = "/transport";

	/**
	 * Field for using paging-related controller-level methods (class realized
	 * using singleton)
	 */
	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	/**
	 * Field for using transports-related controller-level methods.
	 */
	@Autowired
	private TransportsManager transportsManager;

	/**
	 * Field for using station on line manager-related controller-level methods.
	 */
	@Autowired
	private StationOnLineManager stationOnLineManager;

	/**
	 * Display transports in browser.
	 * 
	 * @param modelMap
	 *            Model map to fill.
	 * @return transportView jsp to use.
	 */
	@RequestMapping(value = TRANSPORT_VIEW_URL_PATTERN, method = RequestMethod.GET)
	public String displayTransportsView(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		forPrintTransport(pageNumber, resultsPerPage, modelMap);

		return TRANSPORT_VIEW_JSP;
	}

	/**
	 * Displays page transports in browser.
	 * 
	 * @param pageNumber
	 *            Number of displaying page.
	 * @param resultsPerPage
	 *            Amount of results per page.
	 * @param modelMap
	 *            Model map to fill.
	 * @return
	 */
	@RequestMapping(value = TRANSPORTPAGE_VIEW_URL_PATTERN, method = RequestMethod.GET)
	public String displayTransportPageView(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		forPrintTransport(pageNumber, resultsPerPage, modelMap);

		return TRANSPORTPAGE_VIEW_JSP;
	}

	/**
	 * Displays transports in browser with button to edit and delete rows.
	 * 
	 * @param modelMap
	 *            Model map to fill.
	 * @return transport jsp to use.
	 */
	@RequestMapping(value = TRANSPORT_URL_PATTERN, method = RequestMethod.GET)
	public String displayTransports(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		forPrintTransport(pageNumber, resultsPerPage, modelMap);

		return TRANSPORT;
	}

	/**
	 * Displays page transports in browser.
	 * 
	 * @param pageNumber
	 *            Number of displaying page.
	 * @param resultsPerPage
	 *            Amount of results per page.
	 * @param modelMap
	 *            Model map to fill.
	 * @return transportpage jsp to use.
	 */
	@RequestMapping(value = TRANSPORTPAGE_URL_PATTERN, method = RequestMethod.GET)
	public String displayTransportPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		forPrintTransport(pageNumber, resultsPerPage, modelMap);

		return TRANSPORTPAGE_JSP;
	}

	/**
	 * Method to display filling model map used in transports-list related
	 * controllers.
	 * 
	 * @param pageNumber
	 *            Number of displaying page.
	 * @param resultsPerPage
	 *            Amount of results per page.
	 * @param modelMap
	 *            Model map to fill.
	 */
	private void forPrintTransport(Integer pageNumber, Integer resultsPerPage,
			Map<String, Object> modelMap) {

		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Transports> transports = transportsManager.getTransportsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put(TRANSPORTS_LIST_NAME, transports);
	}

	/**
	 * Map name of jsp addTransport to formTransport.htm.
	 * 
	 * @return addTransport jsp to use.
	 */
	@RequestMapping(value = FORM_TRANSPORT_URL_PATTERN, method = RequestMethod.GET)
	public String transportForm() {
		return ADD_TRANSPORT_JSP;
	}

	/**
	 * Controller method for displaying adding transport page. Adds new
	 * transport into the Transports table.
	 * 
	 * @param transportCode
	 *            Transport code.
	 * @param startTime
	 *            Start time.
	 * @param routesCode
	 *            Routes code.
	 * @param seatclass1
	 *            Number of seats class 1.
	 * @param seatclass2
	 *            Number of seats class 2.
	 * @param seatclass3
	 *            Number of seats class 3.
	 * @param genprice
	 *            General price.
	 * @return redirect:/transport
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
	 * Controller for displaying getting transports ID from the Transports table
	 * and finds it in the Transports table then puts found object in Map as
	 * request attribute.
	 * 
	 * @param transportId
	 *            transports ID to get from the database.
	 * @param modelMap
	 *            Model map to fill.
	 * @return editTransport jsp to use.
	 */
	@RequestMapping(value = EDIT_TRANSPORT_TRANSPORT, method = RequestMethod.GET)
	public String editTransport(@PathVariable(TRANSPORT) Integer transportId,
			Map<String, Object> modelMap) {

		Transports transport = transportsManager
				.findTransportsById(transportId);

		modelMap.put(TRANSPORT, transport);

		return EDIT_TRANSPORT_JSP;
	}

	/**
	 * Displays getting a transport object and saves it into the Transports
	 * table.
	 * 
	 * @param transportCode
	 *            Transport code.
	 * @param startTime
	 *            Start time.
	 * @param routesCode
	 *            Routes code.
	 * @param seatclass1
	 *            Number of seats class 1.
	 * @param seatclass2
	 *            Number of seats class 2.
	 * @param seatclass3
	 *            Number of seats class 3.
	 * @param genprice
	 *            General price.
	 * @return redirect:/transport
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
	 * Displays deleting a transport from the Transports table.
	 * 
	 * @param transportId
	 * @return redirect:/transport
	 */
	@RequestMapping(value = REMOVE_TRANSPORT_TRANSPORT_TO_REMOVE, method = RequestMethod.GET)
	public String removeTransport(
			@PathVariable(TRANSPORT_TO_REMOVE_PATH_VERIABLE) Integer transportId) {
		transportsManager.removeTransportById(transportId);

		return REDIRECT_TRANSPORT;
	}

	/**
	 * Displays in browser all stations on certain line.
	 * 
	 * @param lineId
	 *            Line ID.
	 * @param modelMap
	 *            Model Map to fill.
	 * @return listOfStationsOnLine
	 */
	@RequestMapping(value = GETS_LINE_ID_LINE_ID_ONSTATIONS, method = RequestMethod.GET)
	public String displayStationOnLine(
			@PathVariable(LINE_ID_ONSTATIONS_PATH_VERIABLE) Integer lineId,
			Map<String, Object> modelMap) {
		List<StationsOnLine> listOfStationsOnLine = stationOnLineManager
				.findStationsOnLine(lineId);

		modelMap.put(LIST_OF_STATIONS_ON_LINE, listOfStationsOnLine);

		return LIST_OF_STATIONS_ON_LINE;
	}

	/**
	 * Controller method for displaying getting Transport by two stations page.
	 * 
	 * @param stationName1
	 *            Name of station 1.
	 * @param stationName2
	 *            Name of station 2.
	 * @param modelMap
	 *            Model map to fill.
	 * @return
	 */
	@RequestMapping(value = TRANSPORT_TRAVEL_URL_PATTERN, method = RequestMethod.GET)
	public String getTransportByTwoStations(
			@RequestParam(value = STATION_NAME1_REQUEST_PARAM, required = false) String stationName1,
			@RequestParam(value = STATION_NAME2_REQUEST_PARAM, required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.equals("") || stationName2.equals("")) {
			return TRANSPORT_TRAVEL_JSP;
		}

		long count = transportsManager.getTransportByTwoStListCount(
				stationName1, stationName2);
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		modelMap.put(TRANSPORT_TRAVEL_LIST, transportsManager
				.getTransportByTwoStForPage(stationName1, stationName2,
						(int) container.getPageNumber(),
						(int) container.getResultsPerPage()));

		return TRANSPORT_TRAVEL_JSP;
	}

	@RequestMapping(value = TRANSPORT_TRAVEL_PAGE_URL_PATTERN, method = RequestMethod.GET)
	public String getTransportByTwoStationsPage(
			@RequestParam(value = STATION_NAME1_REQUEST_PARAM, required = false) String stationName1,
			@RequestParam(value = STATION_NAME2_REQUEST_PARAM, required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.equals("") || stationName2.equals("")) {
			return TRANSPORT_TRAVEL_JSP;
		}
		
		long count = transportsManager.getTransportByTwoStListCount(
				stationName1, stationName2);
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		modelMap.put(TRANSPORT_TRAVEL_LIST, transportsManager
				.getTransportByTwoStForPage(stationName1, stationName2,
						(int) container.getPageNumber(),
						(int) container.getResultsPerPage()));
		
		return TRANSPORT_TRAVEL_PAGE_JSP;
	}

}

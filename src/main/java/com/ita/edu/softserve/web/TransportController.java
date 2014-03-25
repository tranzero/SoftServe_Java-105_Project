package com.ita.edu.softserve.web;

import java.sql.Time;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.components.Encoder;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.propertyeditors.RoutesEditor;
import com.ita.edu.softserve.propertyeditors.TimeEditor;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TransportsCriteriaContainerImpl;

/**
 * Base controller class for Transports.
 * 
 * @author Roman
 */
@Controller
public class TransportController {

	/**
	 * printTransportsList
	 */
	private static final String TRANSPORT_LIST_MANAGE_NAME = "/transportListManage";
	private static final String TRANSPORT_LIST_MANAGE_JSP_NAME = "transportListManage";
	
	/**
	 * printTransportsListPage
	 */
	private static final String TRANSPORT_LIST_PAGE_MANAGE_NAME = "/transportListPageManage";
	private static final String TRANSPORT_LIST_PAGE_MANAGE_JSP_NAME = "transportListPageManage";

	/**
	 * ModelMap attribute.
	 */
	private static final String ROUTES_LIST = "routesList";

	private static final String TIME_PATTERN = "hh:mm:ss";

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
	 * URL pattern that map controller updateTransportToDB.
	 */
	private static final String EDIT_TRANSPORT_TRANSPORT_ID = "editTransport/addTransport.htm";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String EDIT_TRANSPORT_JSP = "editTransport";

	/**
	 * URL pattern that map controller editTransport.
	 */
	private static final String EDIT_TRANSPORT_TRANSPORT = "/editTransport/{transport}";

	/**
	 * The name of jsp that defines Spring to redirect.
	 */
	private static final String REDIRECT_TRANSPORT = "redirect:/transportListManage";

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
	private static final String MODEL_TRANSPORT = "transport";

	/**
	 * The name of jsp that defines Spring.
	 */
	private static final String TRANSPORT = "transport";

	/**
	 * Defines date attribute.
	 */
	private static final String START_DATE = "sDate";

	/**
	 * Part of URL that defines displaying transports web page.
	 */
	private static final String TRANSPORT_LIST_WEB_NAME = "/transportList";

	/**
	 * Part of URL that defines web page for displaying transports AJAX paging.
	 */
	private static final String TRANSPORT_LIST_PAGE_WEB_NAME = "/transportListPage";

	/**
	 * Spring response that defines displaying Transports into jsp page.
	 */
	private static final String TRANSPORT_LIST_JSP_NAME = "transportList";

	/**
	 * Spring response that defines jsp page for displaying transports AJAX
	 * paging.
	 */
	private static final String TRANSPORT_LIST_PAGE_NAME = "transportListPage";

	/**
	 * Name for in-jsp jstl variable, representing list of transports.
	 */
	private static final String TRANSPORTSLIST_NAME = "transportsList";

	/**
	 * Name for in-jsp jstl variable, representing language (used for jQuery UI
	 * datepicker)
	 */
	private static final String LANGUAGE_NAME = "language";

	/**
	 * Name for showing transport code attribute name.
	 */
	private static final String IS_TRANSPORT_CODE_ATTRIBUTE_NAME = "isTransportCode";

	/**
	 * Name for showing route name attribute name.
	 */
	private static final String IS_ROUTE_NAME_ATTRIBUTE_NAME = "isRouteName";

	/**
	 * Name for showing route code attribute name.
	 */
	private static final String IS_ROUTE_CODE_ATTRIBUTE_NAME = "isRoutesCode";

	/**
	 * Name for showing Class1 places count attribute name.
	 */
	private static final String IS_CLASS1_ATTRIBUTE_NAME = "isClass1";
	/**
	 * Name for showing Class1 places count attribute name.
	 */
	private static final String IS_CLASS2_ATTRIBUTE_NAME = "isClass2";
	/**
	 * Name for showing Class1 places count attribute name.
	 */
	private static final String IS_CLASS3_ATTRIBUTE_NAME = "isClass3";

	/**
	 * Name for showing price attribute name.
	 */
	private static final String IS_PRICE_ATTRIBUTE_NAME = "isPrice";

	/**
	 * Name for criteria container attribute name.
	 */
	private static final String CRITERIA_CONTAINER_ATTRIBUTE_NAME = "container";

	/**
	 * Name for encoder attribute name.
	 */
	private static final String ENCODER_ATTRIBUTE_NAME = "encoder";

	/**
	 * Field for using paging-related controller-level methods (class realized
	 * using singleton).
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

	@Autowired
	private RoutesManager routesManager;

	/**
	 * Gets access to Routes DAO.
	 */
	@Autowired
	private RoutesDAO routesDao;

	@Autowired
	private UserNameService userNameService;

	@Autowired
	private TripsManager tripsManager;

	@Autowired
	private Validator transportsValidator;

	@Autowired
	private Encoder encoder;

	/*------------------For Transport Table Filter Sorting-------------------*/

	private void putFillElementsOptions(TransportsCriteriaContainer container,
			ModelMap modelMap) {

		modelMap.addAttribute(IS_TRANSPORT_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getTransportCode()));
		modelMap.addAttribute(IS_ROUTE_NAME_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRouteName()));
		modelMap.addAttribute(IS_ROUTE_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRoutesCode()));
		modelMap.addAttribute(
				IS_CLASS1_ATTRIBUTE_NAME,
				(container.getSeatClass1() == null)
						|| (container.getSeatClass1() < 0));
		modelMap.addAttribute(
				IS_CLASS2_ATTRIBUTE_NAME,
				(container.getSeatClass2() == null)
						|| (container.getSeatClass2() < 0));
		modelMap.addAttribute(
				IS_CLASS3_ATTRIBUTE_NAME,
				(container.getSeatClass3() == null)
						|| (container.getSeatClass3() < 0));

	}

	private void completeMapForAddTransports(PageInfoContainer container,
			TransportsCriteriaContainer transportCriteriaContainer,
			ModelMap modelMap, Locale locale) {
		putFillElementsOptions(transportCriteriaContainer, modelMap);
		transportsManager.validateTransportCriteria(transportCriteriaContainer);
		modelMap.addAttribute(IS_PRICE_ATTRIBUTE_NAME,
				transportCriteriaContainer.getPrice().equals(Double.MAX_VALUE));
		long count = transportsManager
				.getTransportsListCountWithContainers(transportCriteriaContainer);
		container.setCount(count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.addAttribute(CRITERIA_CONTAINER_ATTRIBUTE_NAME,
				transportCriteriaContainer);
		modelMap.addAttribute(ENCODER_ATTRIBUTE_NAME, encoder);

		List<Transports> transports = transportsManager
				.getTransportsListWithContainers(container,
						transportCriteriaContainer);
		modelMap.addAttribute(TRANSPORTSLIST_NAME, transports);
		modelMap.addAttribute(LANGUAGE_NAME, locale.getLanguage());
	}

	/**
	 * Controller method for displaying transport page.
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined).
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined).
	 * @param modelMap
	 *            Model map to fill.
	 * @param locale
	 *            Used spring locale.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = TRANSPORT_LIST_WEB_NAME, method = RequestMethod.GET)
	public String printTransportsListManage(PageInfoContainerImpl container,
			TransportsCriteriaContainerImpl transportCriteriaContainer,
			ModelMap modelMap, Locale locale) {

		completeMapForAddTransports(container, transportCriteriaContainer,
				modelMap, locale);

		return TRANSPORT_LIST_JSP_NAME;
	}

	/**
	 * Controller method for displaying AJAX-source transport page.
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined).
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined).
	 * @param modelMap
	 *            Model map to fill.
	 * @param locale
	 *            Used spring locale.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = TRANSPORT_LIST_PAGE_WEB_NAME, method = RequestMethod.GET)
	public String printTransportsListPageManage(
			PageInfoContainerImpl container,
			TransportsCriteriaContainerImpl transportCriteriaContainer,
			ModelMap modelMap, Locale locale) {
		completeMapForAddTransports(container, transportCriteriaContainer,
				modelMap, locale);
		return TRANSPORT_LIST_PAGE_NAME;
	}

	/*--------------------*/

	/**
	 * Controller method for displaying transport page.
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined).
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined).
	 * @param modelMap
	 *            Model map to fill.
	 * @param locale
	 *            Used spring locale.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = TRANSPORT_LIST_MANAGE_NAME, method = RequestMethod.GET)
	public String printTransportsList(PageInfoContainerImpl container,
			TransportsCriteriaContainerImpl transportCriteriaContainer,
			ModelMap modelMap, Locale locale) {

		completeMapForAddTransports(container, transportCriteriaContainer,
				modelMap, locale);

		return TRANSPORT_LIST_MANAGE_JSP_NAME;
	}

	/**
	 * Controller method for displaying AJAX-source transport page.
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined).
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined).
	 * @param modelMap
	 *            Model map to fill.
	 * @param locale
	 *            Used spring locale.
	 * @return definition of jsp to use.
	 */
	@RequestMapping(value = TRANSPORT_LIST_PAGE_MANAGE_NAME, method = RequestMethod.GET)
	public String printTransportsListPage(PageInfoContainerImpl container,
			TransportsCriteriaContainerImpl transportCriteriaContainer,
			ModelMap modelMap, Locale locale) {
		completeMapForAddTransports(container, transportCriteriaContainer,
				modelMap, locale);
		return TRANSPORT_LIST_PAGE_MANAGE_JSP_NAME;
	}

	/*--------------END-For Transport Table Filter Sorting-------------------*/

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.setValidator(transportsValidator);

		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setGroupingUsed(false);

		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(
				Integer.class, numberFormat, true));

		binder.registerCustomEditor(Double.class, new CustomNumberEditor(
				Double.class, numberFormat, true));

		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Time.class, new TimeEditor(dateFormat,
				false));

		binder.registerCustomEditor(Routes.class, new RoutesEditor(
				routesManager));
	}

	/**
	 * Map name of jsp addTransport to formTransport.htm.
	 * 
	 * @return the jsp name.
	 */
	@RequestMapping(value = FORM_TRANSPORT_URL_PATTERN, method = RequestMethod.GET)
	public String transportForm(Model modelMap) {

		modelMap.addAttribute(MODEL_TRANSPORT, new Transports());

		List<Routes> routesList = routesManager.getAllRoutes();
		modelMap.addAttribute(ROUTES_LIST, routesList);

		return ADD_TRANSPORT_JSP;
	}

	/**
	 * Controller method for displaying adding transport page. Adds new
	 * transport into the Transports table.
	 * 
	 * @param transportCode
	 *            Transport code.
	 * @return the jsp name.
	 */
	@RequestMapping(value = ADD_TRANSPORT_URL_PATTERN, method = RequestMethod.GET)
	public String addTransportToBD(
			@ModelAttribute(MODEL_TRANSPORT) Transports transport,
			BindingResult bindingResult, Model modelMap) {

		transportsValidator.validate(transport, bindingResult);

		if (bindingResult.hasErrors()) {
			List<Routes> routesList = routesManager.getAllRoutes();
			modelMap.addAttribute(ROUTES_LIST, routesList);

			return ADD_TRANSPORT_JSP;
		}

		transportsManager.saveOrUpdateTransport(transport);

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
			Model modelMap) {

		Transports transport = transportsManager
				.findTransportsById(transportId);
		List<Routes> routesList = routesManager.getAllRoutes();

		modelMap.addAttribute(ROUTES_LIST, routesList);
		modelMap.addAttribute(MODEL_TRANSPORT, transport);

		return EDIT_TRANSPORT_JSP;
	}

	/**
	 * Displays getting a transport object and saves it into the Transports
	 * table.
	 * 
	 * @return the jsp name.
	 */
	@RequestMapping(value = EDIT_TRANSPORT_TRANSPORT_ID, method = RequestMethod.GET)
	public String updateTransportToDB(
			@ModelAttribute(MODEL_TRANSPORT) Transports transport,
			BindingResult bindingResult, Model modelMap) {

		transportsValidator.validate(transport, bindingResult);

		if (bindingResult.hasErrors()) {
			modelMap.addAttribute(ROUTES_LIST, routesManager.getAllRoutes());
			return EDIT_TRANSPORT_JSP;
		}

		transportsManager.saveOrUpdateTransport(transport);

		return REDIRECT_TRANSPORT;
	}

	/**
	 * Displays deleting a transport from the Transports table.
	 * 
	 * @param transportId
	 * @return the jsp name.
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
	 * @return the jsp name.
	 */
	@RequestMapping(value = GETS_LINE_ID_LINE_ID_ONSTATIONS, method = RequestMethod.GET)
	public String displayStationOnLine(
			@PathVariable(LINE_ID_ONSTATIONS_PATH_VERIABLE) Integer lineId,
			Model modelMap) {
		List<StationsOnLine> listOfStationsOnLine = stationOnLineManager
				.findStationsOnLine(lineId);

		modelMap.addAttribute(LIST_OF_STATIONS_ON_LINE, listOfStationsOnLine);

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
	 * @return the jsp name.
	 */
	@RequestMapping(value = TRANSPORT_TRAVEL_URL_PATTERN, method = RequestMethod.GET)
	public String getTransportByTwoStations(
			@RequestParam(value = STATION_NAME1_REQUEST_PARAM, required = false) String stationName1,
			@RequestParam(value = STATION_NAME2_REQUEST_PARAM, required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = START_DATE, required = false) String sDate,
			ModelMap modelMap) {

		if ((stationName1 == null) || (stationName2 == null)
				|| stationName1.isEmpty() || stationName2.isEmpty()) {
			return TRANSPORT_TRAVEL_JSP;
		}

		long count = transportsManager.getTransportByTwoStListCount(
				stationName1, stationName2);
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		modelMap.addAttribute(TRANSPORT_TRAVEL_LIST, transportsManager
				.getTransportByTwoStForPage(stationName1, stationName2,
						(int) container.getPageNumber(),
						(int) container.getResultsPerPage(), sDate));
		modelMap.addAttribute("user", userNameService.getLoggedUserId());

		return TRANSPORT_TRAVEL_JSP;
	}

	/**
	 * @param stationName1
	 * @param stationName2
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param sDate
	 * @param orderBy
	 * @param modelMap
	 * @return the jsp name.
	 */
	@RequestMapping(value = TRANSPORT_TRAVEL_PAGE_URL_PATTERN, method = RequestMethod.GET)
	public String getTransportByTwoStationsPage(
			@RequestParam(value = STATION_NAME1_REQUEST_PARAM, required = false) String stationName1,
			@RequestParam(value = STATION_NAME2_REQUEST_PARAM, required = false) String stationName2,
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = START_DATE, required = false) String sDate,
			ModelMap modelMap) {

		if ((stationName1 == null) || (stationName2 == null)
				|| stationName1.isEmpty() || stationName2.isEmpty()) {
			return TRANSPORT_TRAVEL_JSP;
		}

		long count = transportsManager.getTransportByTwoStListCount(
				stationName1, stationName2);
		PageInfoContainerImpl container = new PageInfoContainerImpl(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		modelMap.addAttribute(TRANSPORT_TRAVEL_LIST, transportsManager
				.getTransportByTwoStForPage(stationName1, stationName2,
						(int) container.getPageNumber(),
						(int) container.getResultsPerPage(), sDate));
		modelMap.addAttribute("user", userNameService.getLoggedUserId());

		return TRANSPORT_TRAVEL_PAGE_JSP;
	}

}

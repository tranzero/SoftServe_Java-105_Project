package com.ita.edu.softserve.web;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.dao.impl.TripsDAOImpl;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;

@Controller
public class TripsController {

	/**
	 * String for ukrainian language representation in locale format (used in
	 * formatting date)
	 */

	public static final String UKRAINIAN = "ua";

	/**
	 * String for spanish language representation in locale format (used in
	 * formatting date)
	 */

	public static final String SPANISH = "es";

	/**
	 * String for ukrainian or spanish date format
	 */

	public static final String UKRAINIAN_OR_SPANISH_DATE_FORMAT = "dd.MM.yyyy";
	/**
	 * String for default date format
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
	/**
	 * Part of URL that defines trips web page
	 */
	private static final String TRIPS_WEB_NAME = "/trips";
	/**
	 * Part of URL that defines web page for trips AJAX paging
	 */
	private static final String TRIPSPAGE_WEB_NAME = "/tripspage";
	/**
	 * Part of URL that defines trips manager web page
	 */
	private static final String MANAGETRIPS_WEB_NAME = "/tripsmanager";
	/**
	 * Part of URL that defines web page for trips manager AJAX paging
	 */
	private static final String MANAGETRIPSPAGE_WEB_NAME = "/tripsmanagerpage";
	/**
	 * Part of URL that defines adding trips web page
	 */
	private static final String ADDTRIP_WEB_NAME = "/addTrip";
	/**
	 * Part of URL that defines web page for adding trips AJAX paging
	 */
	private static final String ADDTRIPPAGE_WEB_NAME = "/addTripPage";
	/**
	 * Part of URL that defines potential web page responsible for action of
	 * adding trips
	 */
	private static final String ADDNEWTRIPS_WEB_NAME = "/addNewTrips";
	/**
	 * Spring response that defines trips jsp page
	 */
	private static final String TRIPS_SPRING_NAME = "trips";
	/**
	 * Spring response that defines jsp page for trips AJAX paging
	 */
	private static final String TRIPSPAGE_SPRING_NAME = "tripspage";
	/**
	 * Spring response that defines trips manager jsp page
	 */
	private static final String MANAGETRIPS_SPRING_NAME = "tripsmanager";
	/**
	 * Spring response that defines jsp page for trips manager AJAX paging
	 */
	private static final String MANAGETRIPSPAGE_SPRING_NAME = "tripsmanagerpage";
	/**
	 * Spring response that defines adding trips jsp page
	 */
	private static final String ADDTRIP_SPRING_NAME = "addTrip";
	/**
	 * Spring response that defines jsp page for adding trips AJAX paging
	 */
	private static final String ADDTRIPPAGE_SPRING_NAME = "addTripPage";
	/**
	 * name for in-jsp jstl variable, representing list of trips
	 */
	private static final String TRIPSLIST_NAME = "tripsList";
	/**
	 * name for in-jsp jstl variable, representing list of transports
	 */
	private static final String TRANSPORTSLIST_NAME = "transportsList";
	/**
	 * name for in-jsp jstl variable, representing date format
	 */
	private static final String DATEFORMAT_NAME = "dateFormat";
	/**
	 * name for in-jsp jstl variable, representing language (used for jQuery UI
	 * datepicker)
	 */
	private static final String LANGUAGE_NAME = "language";

	/**
	 * name for mark of error in addTrip.jsp
	 */
	private static final String ERRORMARK = "errormark";

	/**
	 * name for minimum date in adding trips attribute name
	 */
	private static final String FROM_ATTRIBUTE_NAME = "from";
	/**
	 * name for maximum date in adding trips attribute name
	 */
	private static final String TO_ATTRIBUTE_NAME = "to";
	/**
	 * name for transport id in adding trips attribute name
	 */
	private static final String TRANSPORTID_ATTRIBUTE_NAME = "transportid";

	/**
	 * name for showing transport code attribute name
	 */
	private static final String IS_TRANSPORT_CODE_ATTRIBUTE_NAME = "isTransportCode";

	/**
	 * name for showing route name attribute name
	 */
	private static final String IS_ROUTE_NAME_ATTRIBUTE_NAME = "isRouteName";

	/**
	 * name for showing Class1 places count attribute name
	 */
	private static final String IS_CLASS1_ATTRIBUTE_NAME = "isClass1";
	/**
	 * name for showing Class1 places count attribute name
	 */
	private static final String IS_CLASS2_ATTRIBUTE_NAME = "isClass2";
	/**
	 * name for showing Class1 places count attribute name
	 */
	private static final String IS_CLASS3_ATTRIBUTE_NAME = "isClass3";

	
	/**
	 * name for showing minimal date count attribute name
	 */
	private static final String IS_MIN_DATE_ATTRIBUTE_NAME = "isMinDate";
	
	/**
	 * name for showing minimal date count attribute name
	 */
	private static final String IS_MAX_DATE_ATTRIBUTE_NAME = "isMaxDate";
	
	
	/**
	 * name for criteria container attribute name
	 */
	private static final String CRITERIA_CONTAINER_ATTRIBUTE_NAME = "container";
	
	/**
	 * Field for using trips-related controller-level methods
	 */

	@Autowired
	private TripsManager tripsManager;

	/**
	 * Field for using transports-related controller-level methods
	 */

	@Autowired
	private TransportsManager transportsManager;

	/**
	 * Field for using paging-related controller-level methods (class realized
	 * using singleton)
	 */

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	/**
	 * Puts boolean variables in the page to define if non-default values should
	 * be shown in form elements
	 * 
	 * @param tripsCriteriaContainer
	 *            container with filtering info
	 * @param modelMap
	 *            Model map to fill
	 */
	private void putFillElementsOptions(
			TripsCriteriaContainer tripsCriteriaContainer,
			Map<String, Object> modelMap) {
		modelMap.put(IS_TRANSPORT_CODE_ATTRIBUTE_NAME, ValidatorUtil
				.isEmptyString(tripsCriteriaContainer.getTransportCode()));
		modelMap.put(IS_ROUTE_NAME_ATTRIBUTE_NAME, ValidatorUtil
				.isEmptyString(tripsCriteriaContainer.getRouteName()));
		modelMap.put(IS_CLASS1_ATTRIBUTE_NAME,
				(tripsCriteriaContainer.getRemSeatClass1() == null)
						|| (tripsCriteriaContainer.getRemSeatClass1() < 0));
		modelMap.put(IS_CLASS2_ATTRIBUTE_NAME,
				(tripsCriteriaContainer.getRemSeatClass1() == null)
						|| (tripsCriteriaContainer.getRemSeatClass1() < 0));
		modelMap.put(IS_CLASS3_ATTRIBUTE_NAME,
				(tripsCriteriaContainer.getRemSeatClass1() == null)
						|| (tripsCriteriaContainer.getRemSeatClass1() < 0));
		modelMap.put(IS_MIN_DATE_ATTRIBUTE_NAME, ValidatorUtil
				.isEmptyString(tripsCriteriaContainer.getMinDateString()));
		modelMap.put(IS_MAX_DATE_ATTRIBUTE_NAME, ValidatorUtil
				.isEmptyString(tripsCriteriaContainer.getMaxDateString()));

	}

	/**
	 * Method for filling model map used in transports-list related controllers
	 * 
	 * @param pageNumber
	 *            Number of displaying page
	 * @param resultsPerPage
	 *            Amount of results per page
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 */

	private void completeMapForAddTrip(Integer pageNumber,
			Integer resultsPerPage, Map<String, Object> modelMap, Locale locale) {
		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Transports> transports = transportsManager.getTransportsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put(TRANSPORTSLIST_NAME, transports);
		modelMap.put(LANGUAGE_NAME, locale.getLanguage());
	}

	/**
	 * Method for filling model map used in trips-list related controllers
	 * 
	 * @param pageNumber
	 *            Number of displaying page
	 * @param resultsPerPage
	 *            Amount of results per page
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 */

	private void completeMapForTrips(Integer pageNumber,
			Integer resultsPerPage, String transportCode, String routeName,
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, String minDate, String maxDate,
			String orderByParam, String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		TripsCriteriaContainer tripsCriteriaContainer = new TripsCriteriaContainer(
				transportCode, routeName, remSeatClass1, remSeatClass2,
				remSeatClass3, minDate, maxDate, orderByParam, orderByDirection);
		putFillElementsOptions(tripsCriteriaContainer, modelMap);
		tripsManager.validateTripsCriteria(tripsCriteriaContainer, locale);
		long count = tripsManager
				.getTripsListCriteriaCountUsingContainers(tripsCriteriaContainer);
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(CRITERIA_CONTAINER_ATTRIBUTE_NAME, tripsCriteriaContainer);
		String lang = locale.getLanguage();
		modelMap.put(TRIPSLIST_NAME, tripsManager
				.getTripsForCriteriaUsingContainers(tripsCriteriaContainer,
						container));
		modelMap.put(
				DATEFORMAT_NAME,
				new SimpleDateFormat(
						lang.equalsIgnoreCase(UKRAINIAN)
								|| lang.equalsIgnoreCase(SPANISH) ? UKRAINIAN_OR_SPANISH_DATE_FORMAT
								: DEFAULT_DATE_FORMAT));
		modelMap.put(LANGUAGE_NAME, locale.getLanguage());
	}

	/**
	 * Controller method for displaying trips list page
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined)
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined)
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = TRIPSPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printTripsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Trips.TRANSPORT_CODE_NAME, required = false) String transportCode,
			@RequestParam(value = Trips.ROUTE_NAME_NAME, required = false) String routeName,
			@RequestParam(value = Trips.REM_SEAT_CLASS_1_NAME, required = false) Integer remSeatClass1,
			@RequestParam(value = Trips.REM_SEAT_CLASS_2_NAME, required = false) Integer remSeatClass2,
			@RequestParam(value = Trips.REM_SEAT_CLASS_3_NAME, required = false) Integer remSeatClass3,
			@RequestParam(value = Trips.MIN_DATE_NAME, required = false) String minDate,
			@RequestParam(value = Trips.MAX_DATE_NAME, required = false) String maxDate,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_PARAM_NAME, required = false) String orderByParam,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_DIRECTION_NAME, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, transportCode,
				routeName, remSeatClass1, remSeatClass2, remSeatClass3,
				minDate, maxDate, orderByParam, orderByDirection, modelMap,
				locale);
		return TRIPSPAGE_SPRING_NAME;
	}

	/**
	 * Controller method for displaying AJAX-source trips list page
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined)
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined)
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = TRIPS_WEB_NAME, method = RequestMethod.GET)
	public String printTrips(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Trips.TRANSPORT_CODE_NAME, required = false) String transportCode,
			@RequestParam(value = Trips.ROUTE_NAME_NAME, required = false) String routeName,
			@RequestParam(value = Trips.REM_SEAT_CLASS_1_NAME, required = false) Integer remSeatClass1,
			@RequestParam(value = Trips.REM_SEAT_CLASS_2_NAME, required = false) Integer remSeatClass2,
			@RequestParam(value = Trips.REM_SEAT_CLASS_3_NAME, required = false) Integer remSeatClass3,
			@RequestParam(value = Trips.MIN_DATE_NAME, required = false) String minDate,
			@RequestParam(value = Trips.MAX_DATE_NAME, required = false) String maxDate,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_PARAM_NAME, required = false) String orderByParam,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_DIRECTION_NAME, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, transportCode,
				routeName, remSeatClass1, remSeatClass2, remSeatClass3,
				minDate, maxDate, orderByParam, orderByDirection, modelMap,
				locale);
		return TRIPS_SPRING_NAME;
	}

	/**
	 * Controller method for displaying trips manager page
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined)
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined)
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */
	@RequestMapping(value = MANAGETRIPSPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printManageTripsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Trips.TRANSPORT_CODE_NAME, required = false) String transportCode,
			@RequestParam(value = Trips.ROUTE_NAME_NAME, required = false) String routeName,
			@RequestParam(value = Trips.REM_SEAT_CLASS_1_NAME, required = false) Integer remSeatClass1,
			@RequestParam(value = Trips.REM_SEAT_CLASS_2_NAME, required = false) Integer remSeatClass2,
			@RequestParam(value = Trips.REM_SEAT_CLASS_3_NAME, required = false) Integer remSeatClass3,
			@RequestParam(value = Trips.MIN_DATE_NAME, required = false) String minDate,
			@RequestParam(value = Trips.MAX_DATE_NAME, required = false) String maxDate,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_PARAM_NAME, required = false) String orderByParam,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_DIRECTION_NAME, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, transportCode,
				routeName, remSeatClass1, remSeatClass2, remSeatClass3,
				minDate, maxDate, orderByParam, orderByDirection, modelMap,
				locale);
		return MANAGETRIPSPAGE_SPRING_NAME;
	}

	/**
	 * Controller method for displaying AJAX-source trips manager page
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined)
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined)
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = MANAGETRIPS_WEB_NAME, method = RequestMethod.GET)
	public String printManageTrips(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Trips.TRANSPORT_CODE_NAME, required = false) String transportCode,
			@RequestParam(value = Trips.ROUTE_NAME_NAME, required = false) String routeName,
			@RequestParam(value = Trips.REM_SEAT_CLASS_1_NAME, required = false) Integer remSeatClass1,
			@RequestParam(value = Trips.REM_SEAT_CLASS_2_NAME, required = false) Integer remSeatClass2,
			@RequestParam(value = Trips.REM_SEAT_CLASS_3_NAME, required = false) Integer remSeatClass3,
			@RequestParam(value = Trips.MIN_DATE_NAME, required = false) String minDate,
			@RequestParam(value = Trips.MAX_DATE_NAME, required = false) String maxDate,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_PARAM_NAME, required = false) String orderByParam,
			@RequestParam(value = TripsDAOImpl.ORDER_BY_DIRECTION_NAME, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, transportCode,
				routeName, remSeatClass1, remSeatClass2, remSeatClass3,
				minDate, maxDate, orderByParam, orderByDirection, modelMap,
				locale);
		return MANAGETRIPS_SPRING_NAME;
	}

	/**
	 * Controller method for displaying adding trips page
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined)
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined)
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = ADDTRIP_WEB_NAME, method = RequestMethod.GET)
	public String printAddTrips(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForAddTrip(pageNumber, resultsPerPage, modelMap, locale);
		return ADDTRIP_SPRING_NAME;
	}

	/**
	 * Controller method for displaying AJAX-source adding trips page
	 * 
	 * @param pageNumber
	 *            Number of displaying page (spring-defined)
	 * @param resultsPerPage
	 *            Amount of results per page (spring-defined)
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */

	@RequestMapping(value = ADDTRIPPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printAddTripsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForAddTrip(pageNumber, resultsPerPage, modelMap, locale);
		return ADDTRIPPAGE_SPRING_NAME;
	}

	/**
	 * Controller method for performing addition of trips
	 * 
	 * @param modelMap
	 *            Model map to fill
	 * @param locale
	 *            Used spring locale
	 * @return definition of jsp to use
	 */
	@RequestMapping(value = ADDNEWTRIPS_WEB_NAME)
	public String printAddTripsPage(Map<String, Object> modelMap,
			Locale locale, @ModelAttribute(FROM_ATTRIBUTE_NAME) String minDate,
			@ModelAttribute(TO_ATTRIBUTE_NAME) String maxDate,
			@ModelAttribute(TRANSPORTID_ATTRIBUTE_NAME) Integer transportId) {
		if (transportId == null) {
			completeMapForAddTrip(null, null, modelMap, locale);
			modelMap.put(ERRORMARK, true);
			return ADDTRIP_SPRING_NAME;
		}
		if (tripsManager.addTripsInInterval(locale, minDate, maxDate,
				transportId)) {

			completeMapForTrips(null, null, null, null, null, null, null, null,
					null, null, null, modelMap, locale);
			return MANAGETRIPS_SPRING_NAME;
		} else {
			completeMapForAddTrip(null, null, modelMap, locale);
			modelMap.put(ERRORMARK, true);
			return ADDTRIP_SPRING_NAME;
		}

	}

}

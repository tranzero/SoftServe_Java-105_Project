package com.ita.edu.softserve.web;

import java.text.SimpleDateFormat;
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
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TransportForAddTripsCriteriaContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TripsCriteriaContainerImpl;

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
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	/**
	 * Part of URL that defines trips web page
	 */
	private static final String TRIPS_WEB_NAME = "/trips";
	/**
	 * Part of URL that defines web page for trips AJAX paging
	 */
	private static final String TRIPSPAGE_WEB_NAME = "/tripspage";
	/**
	 * Part of any redirect spring jsp definition
	 */

	private static final String REDIRECT_SUBSTRING = "redirect:/";

	/**
	 * Part of any redirect spring jsp definition that is on same level with
	 * caller controller maping
	 */

	private static final String REDIRECT_SAME_LEVEL_SUBSTRING = "redirect:";
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
	 * Spring response that defines jsp page for trips manager AJAX paging
	 */
	private static final String ERRORINPUT_SPRING_NAME = "addtripserrorinput";
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
	 * name for showing route code attribute name
	 */
	private static final String IS_ROUTE_CODE_ATTRIBUTE_NAME = "isRoutesCode";

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
	 * Constant for mapping variable for REST request
	 */
	private static final String TRIPID_PATH_VARIABLE = "tripId";

	/**
	 * name for showing minimal date count attribute name
	 */
	private static final String IS_MIN_DATE_ATTRIBUTE_NAME = "isMinDate";

	/**
	 * name for showing minimal date count attribute name
	 */
	private static final String IS_MAX_DATE_ATTRIBUTE_NAME = "isMaxDate";

	/**
	 * name for showing price attribute name
	 */
	private static final String IS_PRICE_ATTRIBUTE_NAME = "isPrice";

	/**
	 * name for criteria container attribute name
	 */
	private static final String CRITERIA_CONTAINER_ATTRIBUTE_NAME = "container";

	/**
	 * name for encoder attribute name
	 */
	private static final String ENCODER_ATTRIBUTE_NAME = "encoder";

	/**
	 * Constant for mapping trips delete
	 */

	private static final String TRIP_DELETE_PATH = "/tripDelete/{tripId}";

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
	 * Container of trips search and sorting information
	 */

	/**
	 * Field for using paging-related controller-level methods (class realized
	 * using singleton)
	 */

	@Autowired
	Encoder encoder;

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
	private void putFillElementsOptions(TripsCriteriaContainer container,
			Map<String, Object> modelMap) {
		modelMap.put(IS_TRANSPORT_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getTransportCode()));
		modelMap.put(IS_ROUTE_NAME_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRouteName()));
		modelMap.put(
				IS_CLASS1_ATTRIBUTE_NAME,
				(container.getRemSeatClass1() == null)
						|| (container.getRemSeatClass1() < 0));
		modelMap.put(
				IS_CLASS2_ATTRIBUTE_NAME,
				(container.getRemSeatClass2() == null)
						|| (container.getRemSeatClass2() < 0));
		modelMap.put(
				IS_CLASS3_ATTRIBUTE_NAME,
				(container.getRemSeatClass3() == null)
						|| (container.getRemSeatClass3() < 0));
		modelMap.put(IS_MIN_DATE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getMinDate()));
		modelMap.put(IS_MAX_DATE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getMaxDate()));

	}

	private void putFillAddTripsElementsOptions(
			TransportForAddTripsCriteriaContainer container,
			Map<String, Object> modelMap) {

		modelMap.put(IS_TRANSPORT_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getTransportCode()));
		modelMap.put(IS_ROUTE_NAME_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRouteName()));
		modelMap.put(IS_ROUTE_CODE_ATTRIBUTE_NAME,
				ValidatorUtil.isEmptyString(container.getRoutesCode()));
		modelMap.put(
				IS_CLASS1_ATTRIBUTE_NAME,
				(container.getSeatClass1() == null)
						|| (container.getSeatClass1() < 0));
		modelMap.put(
				IS_CLASS2_ATTRIBUTE_NAME,
				(container.getSeatClass2() == null)
						|| (container.getSeatClass2() < 0));
		modelMap.put(
				IS_CLASS3_ATTRIBUTE_NAME,
				(container.getSeatClass3() == null)
						|| (container.getSeatClass3() < 0));

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

	private void completeMapForAddTrip(
			PageInfoContainer container,
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		putFillAddTripsElementsOptions(transportForAddTripsCriteriaContainer,
				modelMap);
		transportsManager
				.validateTransportForAddTripsCriteria(transportForAddTripsCriteriaContainer);
		modelMap.put(
				IS_PRICE_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer.getPrice().equals(
						Double.MAX_VALUE));
		long count = transportsManager
				.getTransportsListForAddTripsCountWithContainers(transportForAddTripsCriteriaContainer);
		container.setCount(count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(CRITERIA_CONTAINER_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer);
		modelMap.put(ENCODER_ATTRIBUTE_NAME, encoder);

		List<Transports> transports = transportsManager
				.getTransportsListForAddTripsWithContainers(container,
						transportForAddTripsCriteriaContainer);
		modelMap.put(TRANSPORTSLIST_NAME, transports);
		modelMap.put(LANGUAGE_NAME, locale.getLanguage());
	}

	private void completeMapForEditTrip(
			Integer transportId,
			Boolean specialPaging,
			PageInfoContainer container,
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		putFillAddTripsElementsOptions(transportForAddTripsCriteriaContainer,
				modelMap);
		transportsManager
				.validateTransportForAddTripsCriteria(transportForAddTripsCriteriaContainer);
		modelMap.put(
				IS_PRICE_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer.getPrice().equals(
						Double.MAX_VALUE));
		long count = transportsManager
				.getTransportsListForAddTripsCountWithContainers(transportForAddTripsCriteriaContainer);
		container.setCount(count);
		paginationManager.validatePaging(container);
		specialPaging = (Boolean) ValidatorUtil.defaultForNull(specialPaging, new Boolean(true));
		if (specialPaging){
			container.setPageNumber(1);
		}
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(CRITERIA_CONTAINER_ATTRIBUTE_NAME,
				transportForAddTripsCriteriaContainer);
		modelMap.put(ENCODER_ATTRIBUTE_NAME, encoder);

		List<Transports> transports = transportsManager
				.getTransportsListForAddTripsWithContainers(container,
						transportForAddTripsCriteriaContainer);
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

	private void completeMapForTrips(PageInfoContainer container,
			TripsCriteriaContainer tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		putFillElementsOptions(tripsCriteriaContainer, modelMap);
		tripsManager.validateTripsCriteria(tripsCriteriaContainer, locale);
		long count = tripsManager
				.getTripsListCriteriaCountUsingContainers(tripsCriteriaContainer);
		container.setCount(count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put(CRITERIA_CONTAINER_ATTRIBUTE_NAME, tripsCriteriaContainer);
		modelMap.put(ENCODER_ATTRIBUTE_NAME, encoder);
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
		modelMap.put(LANGUAGE_NAME, lang);
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
	public String printTripsPage(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
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
	public String printTrips(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
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
	public String printManageTripsPage(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
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
	public String printManageTrips(PageInfoContainerImpl container,
			TripsCriteriaContainerImpl tripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(container, tripsCriteriaContainer, modelMap, locale);
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
			PageInfoContainerImpl container,
			TransportForAddTripsCriteriaContainerImpl transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForAddTrip(container, transportForAddTripsCriteriaContainer,
				modelMap, locale);
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
			PageInfoContainerImpl container,
			TransportForAddTripsCriteriaContainerImpl transportForAddTripsCriteriaContainer,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForAddTrip(container, transportForAddTripsCriteriaContainer,
				modelMap, locale);
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
		if (tripsManager.addTripsInInterval(locale, minDate, maxDate,
				transportId)) {
			return REDIRECT_SAME_LEVEL_SUBSTRING + MANAGETRIPS_SPRING_NAME;
		} else {
			return REDIRECT_SAME_LEVEL_SUBSTRING + ERRORINPUT_SPRING_NAME;
		}

	}

	@RequestMapping(TRIP_DELETE_PATH)
	public String deleteTrip(@PathVariable(TRIPID_PATH_VARIABLE) Integer tripId) {
		tripsManager.removeTrip(tripId);
		return REDIRECT_SUBSTRING + MANAGETRIPS_SPRING_NAME;
	}

}

package com.ita.edu.softserve.web;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.PageInfoContainer;

@Controller
public class TripsController {

	private static final String UKRAINIAN = "ua";
	private static final String UKRAINIAN_DATE_FORMAT = "dd.MM.yyyy";
	private static final String DEFAULT_DATE_FORMAT = "yyyy.MM.dd";
	private static final String TRIPS_WEB_NAME = "/trips";
	private static final String TRIPSPAGE_WEB_NAME = "/tripspage";
	private static final String MANAGETRIPS_WEB_NAME = "/tripsmanager";
	private static final String MANAGETRIPSPAGE_WEB_NAME = "/tripsmanagerpage";
	private static final String ADDTRIP_WEB_NAME = "/addTrip";
	private static final String ADDTRIPPAGE_WEB_NAME = "/addTripPage";
	private static final String TRIPS_SPRING_NAME = "trips";
	private static final String TRIPSPAGE_SPRING_NAME = "tripspage";
	private static final String MANAGETRIPS_SPRING_NAME = "tripsmanager";
	private static final String MANAGETRIPSPAGE_SPRING_NAME = "tripsmanagerpage";
	private static final String ADDTRIP_SPRING_NAME = "addTrip";
	private static final String ADDTRIPPAGE_SPRING_NAME = "addTripPage";	
	private static final String TRIPSLIST_NAME = "tripsList";
	private static final String DATEFORMAT_NAME = "dateFormat";

	@Autowired
	private TripsManager tripsManager;
	
	@Autowired
	private TransportsManager transportsManager;

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	private void completeMapForAddTrip(Integer pageNumber,
			Integer resultsPerPage, Map<String, Object> modelMap) {
		long count = transportsManager.getTransportsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber, resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);

		List<Transports> transports = transportsManager.getTransportsForPage(
				container.getPageNumber(), container.getResultsPerPage());
		modelMap.put("transportsList", transports);
	}
	
	private void completeMapForTrips(Integer pageNumber,
			Integer resultsPerPage, Map<String, Object> modelMap, Locale locale) {
		long count = tripsManager.getTripsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber,
				resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		String lang = locale.getLanguage();
		modelMap.put(TRIPSLIST_NAME, tripsManager.getTripsForPage(
				container.getPageNumber(), container.getResultsPerPage()));
		modelMap.put(
				DATEFORMAT_NAME,
				new SimpleDateFormat(
						lang.equalsIgnoreCase(UKRAINIAN) ? UKRAINIAN_DATE_FORMAT
								: DEFAULT_DATE_FORMAT));
	}

	@RequestMapping(value = TRIPSPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printTripsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, modelMap, locale);
		return TRIPSPAGE_SPRING_NAME;
	}

	@RequestMapping(value = TRIPS_WEB_NAME, method = RequestMethod.GET)
	public String printTrips(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, modelMap, locale);
		return TRIPS_SPRING_NAME;
	}

	@RequestMapping(value = MANAGETRIPSPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printManageTripsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, modelMap, locale);
		return MANAGETRIPSPAGE_SPRING_NAME;
	}

	@RequestMapping(value = MANAGETRIPS_WEB_NAME, method = RequestMethod.GET)
	public String printManageTrips(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		completeMapForTrips(pageNumber, resultsPerPage, modelMap, locale);
		return MANAGETRIPS_SPRING_NAME;
	}
	
	

	@RequestMapping(value = ADDTRIP_WEB_NAME, method = RequestMethod.GET)
	public String printAddTrips(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		completeMapForAddTrip(pageNumber, resultsPerPage, modelMap);
		return ADDTRIP_SPRING_NAME;
	}

	@RequestMapping(value = ADDTRIPPAGE_WEB_NAME, method = RequestMethod.GET)
	public String printAddTripsPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			Map<String, Object> modelMap) {
		completeMapForAddTrip(pageNumber, resultsPerPage, modelMap);
		return ADDTRIPPAGE_SPRING_NAME;
	}
}

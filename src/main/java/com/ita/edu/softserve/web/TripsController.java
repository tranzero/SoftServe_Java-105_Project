package com.ita.edu.softserve.web;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.PageInfoContainer;

@Controller
public class TripsController {
	
	
	
	@Autowired
	private TripsManager tripsManager;
	
	private PaginationManager paginationManager=PaginationManager.getInstance(); 


	@RequestMapping(value = "/tripspage", method = RequestMethod.GET)
	public String printTripsPage(@RequestParam(value=PaginationManager.PAGE_NUMBER_NAME, required=false) Integer pageNumber,
			@RequestParam(value=PaginationManager.RESULTS_PER_PAGE_NAME, required=false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		long count = tripsManager.getTripsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber, resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		String lang = locale.getLanguage();
		modelMap.put("tripsList", tripsManager.getTripsForPage(container.getPageNumber(), 
				container.getResultsPerPage()));
		modelMap.put("dateFormat", new SimpleDateFormat(lang.equalsIgnoreCase("ua")?"dd.MM.yyyy":"yyyy.MM.dd"));
		return "tripspage";
	}
	
	
	
	@RequestMapping(value = "/trips", method = RequestMethod.GET)
	public String printTrips(@RequestParam(value=PaginationManager.PAGE_NUMBER_NAME, required=false) Integer pageNumber,
			@RequestParam(value=PaginationManager.RESULTS_PER_PAGE_NAME, required=false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		long count = tripsManager.getTripsListCount();
		PageInfoContainer container = new PageInfoContainer(pageNumber, resultsPerPage, count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		String lang = locale.getLanguage();
		modelMap.put("tripsList", tripsManager.getTripsForPage(container.getPageNumber(), 
				container.getResultsPerPage()));
		modelMap.put("dateFormat", new SimpleDateFormat(lang.equalsIgnoreCase("ua")?"dd.MM.yyyy":"yyyy.MM.dd"));		
		return "trips";
	}
}

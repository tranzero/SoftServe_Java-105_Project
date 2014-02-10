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

@Controller
public class TripsController {
	
	
	
	@Autowired
	private TripsManager tripsManager;
	
	private PaginationManager paginationManager=PaginationManager.getInstance(); 


	@RequestMapping(value = "/tripspage", method = RequestMethod.GET)
	public String printTripsPage(@RequestParam(value="pageNumber", required=false) Integer pageNumber,
			@RequestParam(value="resultsPerPage", required=false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		if (pageNumber == null){
			pageNumber = new Integer(paginationManager.getDefaultPageNumber());
		}
		if (resultsPerPage==null){
			resultsPerPage = new Integer(paginationManager.getDefaultResultPerPage());
		}
		int maxPages = paginationManager.getMaxPageCount(resultsPerPage, 
				tripsManager.getTripsListCount());
		if (pageNumber>maxPages){
			pageNumber = maxPages;
		}
		int pageAmount = paginationManager.getDefaultPageCount();
		int firstPage = pageNumber-(pageAmount/2);
		int lastPage = pageNumber+(pageAmount/2);
		if (firstPage<1){
			firstPage=1;
		}
		if (lastPage>maxPages){
			lastPage=maxPages;
		}
		String lang = locale.getLanguage();
		modelMap.put("tripsList", tripsManager.getTripsForPage(pageNumber, resultsPerPage));
		modelMap.put("dateFormat", new SimpleDateFormat(lang.equalsIgnoreCase("ua")?"dd.MM.yyyy":"yyyy.MM.dd"));
		modelMap.put("pageNumber", pageNumber);
		modelMap.put("resultsPerPage", resultsPerPage);
		modelMap.put("maxPages", maxPages);
		modelMap.put("firstPage", firstPage);
		modelMap.put("lastPage", lastPage);
		return "tripspage";
	}
	
	
	
	@RequestMapping(value = "/trips", method = RequestMethod.GET)
	public String printTrips(@RequestParam(value="pageNumber", required=false) Integer pageNumber,
			@RequestParam(value="resultsPerPage", required=false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		if (pageNumber == null){
			pageNumber = new Integer(paginationManager.getDefaultPageNumber());
		}
		if (resultsPerPage==null){
			resultsPerPage = new Integer(paginationManager.getDefaultResultPerPage());
		}
		int maxPages = paginationManager.getMaxPageCount(resultsPerPage, 
				tripsManager.getTripsListCount());
		if (pageNumber>maxPages){
			pageNumber = maxPages;
		}
		int pageAmount = paginationManager.getDefaultPageCount();
		int firstPage = pageNumber-(pageAmount/2);
		int lastPage = pageNumber+(pageAmount/2);
		if (firstPage<1){
			firstPage=1;
		}
		if (lastPage>maxPages){
			lastPage=maxPages;
		}
		String lang = locale.getLanguage();
		modelMap.put("tripsList", tripsManager.getTripsForPage(pageNumber, resultsPerPage));
		modelMap.put("dateFormat", new SimpleDateFormat(lang.equalsIgnoreCase("ua")?"dd.MM.yyyy":"yyyy.MM.dd"));
		modelMap.put("pageNumber", pageNumber);
		modelMap.put("resultsPerPage", resultsPerPage);
		modelMap.put("maxPages", maxPages);
		modelMap.put("firstPage", firstPage);
		modelMap.put("lastPage", lastPage);
		return "trips";
	}
}

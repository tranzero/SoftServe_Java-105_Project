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

@Controller
public class TripsController {
	
	
	public static int DEFAULT_PAGE= 1; 
	
	@Autowired
	private TripsManager tripsManager;

	private static void validatePages(Integer pageNumber, Integer resultsPerPage){
		if (pageNumber == null){
			pageNumber = new Integer(DEFAULT_PAGE);
		}
	}
	
	@RequestMapping(value = "/trips", method = RequestMethod.GET)
	public String printTrips(@RequestParam(value="pageNumber", required=false) Integer pageNumber,
			@RequestParam(value="resultsPerPage", required=false) Integer resultsPerPage,
			Map<String, Object> modelMap, Locale locale) {
		validatePages(pageNumber, resultsPerPage);
		String lang= locale.getLanguage();
		modelMap.put("tripsList", tripsManager.getTripsForLimit(0, 10));
		modelMap.put("dateFormat", new SimpleDateFormat(lang.equalsIgnoreCase("ua")?"dd.MM.yyyy":"yyyy.MM.dd"));
		return "trips";
	}
}

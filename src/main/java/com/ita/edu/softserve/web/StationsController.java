package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.manager.StationsManager;

@Controller
public class StationsController {
	
	@Autowired
	private StationsManager stationsManager;
	
	@RequestMapping(value = "/stations", method = RequestMethod.GET)
	public String addStations(Map<String, Object> modelMap) {
		
		modelMap.put("stationsList", stationsManager.findAllStations());
		
		return "stations";
	}
}
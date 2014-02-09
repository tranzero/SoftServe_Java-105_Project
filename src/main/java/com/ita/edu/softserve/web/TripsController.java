package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ita.edu.softserve.manager.TripsManager;

@Controller
public class TripsController {
	
	@Autowired
	private TripsManager tripsManager;

	
	@RequestMapping(value = "/trips", method = RequestMethod.GET)
	public String printTransports(Map<String, Object> modelMap) {

		modelMap.put("tripsList", tripsManager.getAllTrips());

		return "trips";
	}
}

package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.manager.TransportsManager;

@Controller
public class TransportController {
	
	@Autowired
	private TransportsManager transportsManager;
	
	@RequestMapping(value = "/transport", method = RequestMethod.GET)
	public String printTransports(Map<String, Object> modelMap) {
		
		modelMap.put("transportsList", transportsManager.getAllTransports());

		return "transport";
	}
	
	@RequestMapping(value = "/transportTravel", method = RequestMethod.GET)
	public String getLinesByTwoStations(Map<String, Object> model) {
		return "transportTravel";
	}

	@RequestMapping(value = "/transportTravelFind", method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam("stationName1") String stationName1,
			@RequestParam("stationName2") String stationName2,
			Map<String, Object> model) {

		if (stationName1.equals("") || stationName2.equals("")) {
			return "transportTravel";
		}

		model.put("TransportList",transportsManager.getTransportByTwoStations(
				stationName1, stationName2));

		return "transportTravel";
	}

}
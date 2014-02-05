package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.PostManagerExeption;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.utils.ExceptionUtil;

@Controller
public class StationsController {

	@Autowired
	private StationsManager stationsManager;

	@RequestMapping(value = "/stationsForUsers", method = RequestMethod.GET)
	public String listStations(Map<String, Object> modelMap) {

		modelMap.put("stationsList", stationsManager.findAllStations());
	
		return "stationsForUsers";
	}
	
	@RequestMapping(value = "/stations", method = RequestMethod.GET)
	public String manageStations(Map<String, Object> modelMap) {

		modelMap.put("stationsList", stationsManager.findAllStations());
		return "stations";
	}
	
	@RequestMapping("/delete/{stationId}")
	public String deleteStation(@PathVariable("stationId") Integer stationId) {
		stationsManager.removeStations(stationId);
		return "redirect:/stations";
	}

	@RequestMapping(value = "/stationEdit/{station}", method = RequestMethod.GET)
	public String editStation(@PathVariable("station") Integer stationId,
			Map<String, Object> modelMap) {
		Stations station = stationsManager.findStationsById(stationId);
		modelMap.put("station", station);
		return "stationEdit";
	}

	@RequestMapping(value = "/stationEdit/{stationToEdit}", method = RequestMethod.POST)
	public String updateStationToDB(
			@PathVariable("stationToEdit") Integer stationId,
			@ModelAttribute("stationCode") String stationCode,
			@ModelAttribute("stationName") String stationName) {

		if (stationCode.isEmpty() || stationName.isEmpty()) {
			throw new NullPointerException();
		} else {
			stationsManager.editStation(stationId, stationCode, stationName);
			return "redirect:/stations";
		}
	}

	@RequestMapping(value = "/addStation", method = RequestMethod.GET)
	public String addStations() {
		return "addStation";
	}

	@RequestMapping(value = "/addstat", method = RequestMethod.POST)
	public String addStationToBD(
			@RequestParam("stationCode") String stationCode,
			@RequestParam("stationName") String stationName,
			Map<String, Object> modelMap) {

		stationsManager.createStation(stationCode, stationName);
		return "redirect:/stations";
	}
	
	
	@RequestMapping(value="stationsoncertainline/{line}", method = RequestMethod.GET)
	public String stationsOnCertainLine(@PathVariable("line") String lineName, Map<String,Object>modelMap){
		modelMap.put("stationsList", stationsManager.getStationsOnCertainLine(lineName));
		return "stations";
	}
	
	
	

}
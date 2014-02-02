package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Users;
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
	
	@RequestMapping("/delete/{stationId}")
	public String deleteStation(@PathVariable("stationId") Integer stationId) {
		stationsManager.removeStations(stationId);
	    return "redirect:/stations";
	}     
	
	@RequestMapping(value ="/stationEdit/{station}", method =RequestMethod.GET)
	public String editStation(@PathVariable("station") Integer stationId,Map<String,Object> modelMap){
		Stations station = stationsManager.findStationsById(stationId);
		modelMap.put("station", station);
		return "stationEdit";
	}
	
	
		@RequestMapping(value="/stationEdit/{stationToEdit}",method =RequestMethod.POST)
		public String updateStationToDB(@PathVariable("stationToEdit")Integer stationId,
				@ModelAttribute("stationCode")String stationCode,
				@ModelAttribute("stationName")String stationName			
				) {
			
			stationsManager.updateStation(stationId, stationCode, stationName);
			return "redirect:/stations";
		}
	
}
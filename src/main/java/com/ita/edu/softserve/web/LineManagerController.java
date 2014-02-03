package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.StationsManager;

@Controller
public class LineManagerController {

	@Autowired
	private LinesManager linesManager;
	@Autowired
	private StationsManager stationsManager;

	@RequestMapping(value = "/removeLines", method = RequestMethod.GET)
	public String removeLine(Map<String, Object> modelMap) {
		modelMap.put("linesList", linesManager.getFullLines());
		return "removeLines";
	}

	@RequestMapping(value = "/removeLines", method = RequestMethod.POST)
	public String remove(@ModelAttribute("lineName") String lineName,
			Map<String, Object> modelMap) {
		//System.out.println(lineName);
		linesManager.deleteLine(lineName);
		modelMap.put("linesList", linesManager.getFullLines());
		return "removeLines";
	}
	
	@RequestMapping(value="/addLines", method= RequestMethod.GET)
	public String addLines(Map<String, Object> modelMap){
		modelMap.put("stationsList", stationsManager.findAllStations());
		return "addLines";
	}
	
	//Not Finished
	@RequestMapping(value="/newLine", method= RequestMethod.POST)
	public String addNewLine(){
		return "addlines";
	}

	@RequestMapping(value = "/allLines", method = RequestMethod.GET)
	public String allLines(Map<String, Object> modelMap) {

		modelMap.put("linesList", linesManager.getFullLines());

		return "allLines";
	}

	@RequestMapping(value = "/lines", method = RequestMethod.GET)
	public String getLinesByTwoStations(Map<String, Object> model) {
		return "lines";
	}

	@RequestMapping(value = "/linesFind", method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam("stationName1") String stationName1,
			@RequestParam("stationName2") String stationName2,
			Map<String, Object> model) {

		if (stationName1.equals("") || stationName2.equals("")) {
			return "lines";
		}

		model.put("LinesList", linesManager.getLinesTwoStationsCertainOrder(
				stationName1, stationName2));

		return "lines";
	}

	@RequestMapping(value = "/linesbystation", method = RequestMethod.GET)
	public String linesByStation(Map<String, Object> modelMap) {
		return "linesbystation";
	}

	@RequestMapping(value = "/findlinesbyStation", method = RequestMethod.GET)
	public String linesByStation(
			@RequestParam("stationname") String stationName,
			Map<String, Object> modelMap) {
		if (stationName.equalsIgnoreCase("")) {
			return "linesbystation";
		}
		modelMap.put("linesbystationlist",
				linesManager.getLinesByStation(stationName));
		return "linesbystation";
	}

}

package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.StationsManager;

@Controller
public class LinesController {

	@Autowired
	private LinesManager linesManager;

	@Autowired
	private StationsManager stationsManager;

	@Autowired
	private StationOnLineManager stationOnLineManager;

	@RequestMapping(value = "/addLines", method = RequestMethod.GET)
	public String addLines(Map<String, Object> modelMap) {
		modelMap.put("linesList", linesManager.getFullLines());
		return "addLines";
	}

	@RequestMapping(value = "/addnewline")
	public String addNewLine(/* Map<String, Object> modelMap */) {
		return "editLine";
	}

	@RequestMapping(value = "/addnewstations")
	public String addNewStations(Map<String, Object> modelMap) {
		modelMap.put("stationsList", stationsManager.findAllStations());
		return "allStationsEditLine";
	}

	@RequestMapping(value = "/updateline/addnewstations")
	public String addNewStation(Map<String, Object> modelMap) {
		modelMap.put("stationsList", stationsManager.findAllStations());
		return "allStationsEditLine";
	}

	@RequestMapping(value = "/removeline/{remove}")
	public String removeLine(@PathVariable("remove") String lineName,
			Map<String, Object> modelMap) {
		linesManager.deleteLine(lineName);
		return "redirect:/addLines";
	}

	@RequestMapping(value = "/updateline/{lineName}/{lineId}")
	public String updateLine(@PathVariable("lineName") String lineName,
			@PathVariable("lineId") Integer lineId, 
			Map<String, Object> modelMap) {
		modelMap.put("stationsList",
				stationsManager.getStationsOnCertainLine(lineName));
		return "editLine";
	}

	@RequestMapping(value = "/updateline/{lineName}/removestation/{removeStId}/{lineId}")
	public String removeSt(@PathVariable("removeStId") Integer stationId,
			@PathVariable("lineName") String lineName,
			@PathVariable("lineId") Integer lineId, 
			Map<String, Object> modelMap) {
		stationOnLineManager.removeStation(stationId, lineId);
		modelMap.put("stationsList", stationsManager.findAllStations());
		return "redirect:/updateline/" + lineName + "/" + lineId;
	}

	@RequestMapping(value = "/linesbytwostations", method = RequestMethod.GET)
	public String getLinesByTwoStations(Map<String, Object> model) {
		return "linesbytwostations";
	}

	@RequestMapping(value = "/linesbytwostationsFind", method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam("stationName1") String stationName1,
			@RequestParam("stationName2") String stationName2,
			Map<String, Object> model) {

		if (stationName1.equals("") || stationName2.equals("")) {
			return "linesbytwostations";
		}

		model.put("LinesList",
				linesManager.getLinesByTwoStations(stationName1, stationName2));

		return "linesbytwostations";
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

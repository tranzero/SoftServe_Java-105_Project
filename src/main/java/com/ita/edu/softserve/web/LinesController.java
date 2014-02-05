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
import com.ita.edu.softserve.manager.StationsManager;

@Controller
public class LinesController {

	@Autowired
	private LinesManager linesManager;

	@Autowired
	private StationsManager stationsManager;

	@RequestMapping(value = "/addLines", method = RequestMethod.GET)
	public String addLines(Map<String, Object> modelMap) {
		modelMap.put("linesList", linesManager.getFullLines());
		return "addLines";
	}

	@RequestMapping(value = "/addNewLine", method = RequestMethod.GET)
	public String addNewLine(Map<String, Object> modelMap) {
		return "updateLines";
	}
	
	@RequestMapping(value = "/addStationToLine", method = RequestMethod.GET)
	public String addStationToLine(Map<String, Object> modelMap) {
		modelMap.put("stationsList", stationsManager.findAllStations());
		return "addStationToLine";
	}

	@RequestMapping(value = "updateLines/{updateLines}", method = RequestMethod.GET)
	public String update(@PathVariable("updateLines") String lineName,
			Map<String, Object> modelMap) {
		modelMap.put("stationsList", stationsManager.getStationsOnCertainLine(lineName));
		return "updateLine";
	}

	@RequestMapping(value = "removeStationOnLine/{removeStationOnLine}", method = RequestMethod.GET)
	public String removeStation(
			@PathVariable("removeStationOnLine") String stationName) {
		return "redirect:/updateLine";
	}

	@RequestMapping(value = "removeLines/{removeLines}", method = RequestMethod.GET)
	public String remove(@PathVariable("removeLines") String lineName,
			Map<String, Object> modelMap) {
		linesManager.deleteLine(lineName);
		modelMap.put("linesList", linesManager.getFullLines());
		return "redirect:/addLines";
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

		model.put("LinesList", linesManager.getLinesTwoStationsCertainOrder(
				stationName1, stationName2));

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

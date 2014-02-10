package com.ita.edu.softserve.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Lines;
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

	@RequestMapping(value = "/savenewline", method = RequestMethod.POST)
	public String saveNewLine(@ModelAttribute("newLineName") String newlineName) {
		System.out.println(newlineName);
		linesManager.createLine(newlineName);
		Integer newLineId = linesManager.findByLineName(newlineName)
				.getLineId();
		System.out.println(newLineId);
		return "redirect:/updateline/" + newlineName + "/" + newLineId;
	}

	@RequestMapping(value = "/addnewstations/{lineId}")
	public String addNewStations(@PathVariable("lineId") Integer lineId,
			Map<String, Object> modelMap) {
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
			@PathVariable("lineId") Integer lineId, Map<String, Object> modelMap) {
		modelMap.put("stationsList",
				stationsManager.getStationsOnCertainLine(lineName));
		return "editLine";
	}

	@RequestMapping(value = "/updateline/{lineName}/removestation/{removeStId}/{lineId}")
	public String removeSt(@PathVariable("removeStId") Integer stationId,
			@PathVariable("lineName") String lineName,
			@PathVariable("lineId") Integer lineId, Map<String, Object> modelMap) {
		stationOnLineManager.removeStation(stationId, lineId);
		modelMap.put("stationsList", stationsManager.findAllStations());
		return "redirect:/updateline/" + lineName + "/" + lineId;
	}

	@RequestMapping(value = "/addnewstations/confirmaddstations", method = RequestMethod.POST)
	public String confirmAddStations(
			@RequestParam("checkStations") String[] stationsName,
			@ModelAttribute("lineId") Integer lineId) {
		List<String> stationName = new ArrayList<String>();
		for (String str : stationsName) {
			stationName.add(str);
		}
		stationOnLineManager.addStationsToLine(lineId, stationName);

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

		model.put("LinesList",
				linesManager.getLinesByTwoStations(stationName1, stationName2));

		return "linesbytwostations";
	}

	@RequestMapping(value = "/linesbystation", method = RequestMethod.GET)
	public String linesByStationGet() {

		return "linesbystation";

	}

	@RequestMapping(value = "/linesbystation", method = RequestMethod.POST)
	public String linesByStationPost(
			@ModelAttribute("stationname") String stationName,
			Map<String, Object> modelMap) {
		modelMap.put("stationName", stationName);
		modelMap.put("linesbystationlist",
				linesManager.getLinesByStationName(stationName));
		return "linesbystationresult";

	}
	// @RequestMapping(value = "/linesbystationresult", method =
	// RequestMethod.GET)
	// public String linesByStationGet(String stationName,Map<String, Object>
	// modelMap) {
	//
	// return "linesbystation";
	//
	// }
	//

}

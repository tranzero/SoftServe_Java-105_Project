package com.ita.edu.softserve.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;
import com.ita.edu.softserve.manager.StationsManager;

@Controller
public class LinesController {

	String pageNumberKey = "pageNumber";
	String resultsPerPageKey = "resultsPerPage";
	String sizeOfPagingKey = "sizeOfPaging";
	String maxPageCountKey = "maxPageCount";
	String maxResultCountKey = "maxResultCount";
	String newsListKey = "newsList";
	String errorListKey = "errorList";
	String errorMsgKey = "errorMsg";

	String allLines = "allLines";
	String allLinesAddLine = "newLine";
	String editLinesEditLine = "editLines";
	String deleteLines = "redirect:/allLines";
	String deleteStations = "redirect:/editline/";
	String editStations = "redirect:/editline/";
	String addStations = "addStationsToLine";
	String applyChanges = "redirect:/allLines";
	@Autowired
	private LinesManager linesManager;

	@Autowired
	private StationsManager stationsManager;

	@Autowired
	private StationOnLineManager stationOnLineManager;

	@RequestMapping(value = "/allLines", method = RequestMethod.GET)
	public String allLines(Map<String, Object> modelMap) {
		modelMap.put("lines", linesManager.getFullLines());
		return allLines;
	}

	@RequestMapping(value = "addline", method = RequestMethod.GET)
	public String addLine(Map<String, Object> modelMap) {
		try {
			modelMap.put("stations", stationsManager.findAllStations());
		} catch (StationManagerException e) {
			e.printStackTrace();
		}
		return allLinesAddLine;
	}

	@RequestMapping(value = "editline/{lineName}", method = RequestMethod.GET)
	public String editLine(Map<String, Object> modelMap,
			@PathVariable("lineName") String lineName) {
		try {
			modelMap.put("stationsOnLine",
					stationsManager.getStationsOnCertainLine(lineName));
		} catch (StationManagerException e) {
			e.printStackTrace();
		}
		return editLinesEditLine;
	}

	@RequestMapping(value = "deleteline/{lineName}", method = RequestMethod.GET)
	public String deleteLine(@PathVariable("lineName") String lineName) {
		linesManager.deleteLine(lineName);
		return deleteLines;
	}

	@RequestMapping(value = "editline/deletestation/{stationId}/{lineName}", method = RequestMethod.GET)
	public String deleteStation(@PathVariable("stationId") Integer stationId,
			@PathVariable("lineName") String lineName) {
		stationOnLineManager.removeStation(stationId, linesManager
				.findByLineName(lineName).getLineId());
		return editStations + lineName;
	}

	@RequestMapping(value = "editline/addstation/{lineName}", method = RequestMethod.GET)
	public String addStation(@PathVariable("lineName") String lineName,
			Map<String, Object> modelMap) {
		try {
			modelMap.put("allStations",
					stationsManager.getStationsNotOnCertainLine(lineName));
			modelMap.put("existStations",
					stationsManager.getStationsOnCertainLine(lineName));
		} catch (StationManagerException e) {
			e.printStackTrace();
		}

		return addStations;
	}

	@RequestMapping(value = "editline/addstation/changestations/{lineName}", method = RequestMethod.POST)
	public String changeStations(
			@RequestParam("stationsCheck") List<String> stations,
			@PathVariable("lineName") String lineName) {
		stationOnLineManager.updateStationOnLine(
				linesManager.findByLineName(lineName).getLineId(), stations);
		return deleteStations + lineName;
	}

	@RequestMapping(value = "confirmcreating", method = RequestMethod.POST)
	public String confirmCreating(
			@ModelAttribute("newLineName") String lineName,
			@RequestParam("stationsCheck") List<String> stations) {
		linesManager.createLine(lineName);
		stationOnLineManager.updateStationOnLine(
				linesManager.findByLineName(lineName).getLineId(), stations);
		return applyChanges;
	}

	@RequestMapping(value = "editline/applychanges", method = RequestMethod.GET)
	public String applyChanges() {
		return applyChanges;
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
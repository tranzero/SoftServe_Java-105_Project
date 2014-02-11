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

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;
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

	@RequestMapping(value = "/allLines", method=RequestMethod.GET)
	public String allLines(Map<String, Object> modelMap) {
		modelMap.put("lines", linesManager.getFullLines());
		return "allLines";
	}

	@RequestMapping(value = "addline", method=RequestMethod.GET)
	public String addLine(Map<String, Object> modelMap) {
		try {
			modelMap.put("stations", stationsManager.findAllStations());
		} catch (StationManagerException e) {
			e.printStackTrace();
		}
		return "newLine";
	}

	@RequestMapping(value = "editline/{lineName}", method=RequestMethod.GET)
	public String editLine(Map<String, Object> modelMap,
			@PathVariable("lineName") String lineName) {
		modelMap.put("stationsOnLine",
				stationsManager.getStationsOnCertainLine(lineName));
		return "editLines";
	}

	@RequestMapping(value = "deleteline/{lineName}", method=RequestMethod.GET)
	public String deleteLine(@PathVariable("lineName") String lineName) {
		linesManager.deleteLine(lineName);
		return "redirect:/allLines";
	}

	@RequestMapping(value = "editline/deletestation/{stationId}/{lineName}", method=RequestMethod.GET)
	public String deleteStation(@PathVariable("stationId") Integer stationId,
			@PathVariable("lineName") String lineName) {
		stationOnLineManager.removeStation(stationId, linesManager
				.findByLineName(lineName).getLineId());
		return "redirect:/editline/" + lineName;
	}

	@RequestMapping(value = "editline/addstation/{lineName}", method=RequestMethod.GET)
	public String addStation(@PathVariable("lineName") String lineName,
			Map<String, Object> modelMap) {
		List<Stations> existStations = stationsManager
				.getStationsOnCertainLine(lineName);
		try {
			List<Stations> allStations = new ArrayList<Stations>();
			for (Stations st : stationsManager.findAllStations()) {
				if (!existStations.contains(st)) {
					allStations.add(st);
				}
			}
			modelMap.put("allStations", allStations);
			modelMap.put("existStations", existStations);
		} catch (StationManagerException e) {
			e.printStackTrace();
		}
		return "addStationsToLine";
	}

	@RequestMapping(value = "editline/addstation/changestations/{lineName}", method = RequestMethod.POST)
	public String changeStations(
			@RequestParam("stationsCheck") List<String> stations,
			@PathVariable("lineName") String lineName) {
		stationOnLineManager.updateStationOnLine(
				linesManager.findByLineName(lineName).getLineId(), stations);
		return "redirect:/editline/" + lineName;
	}

	@RequestMapping(value = "confirmcreating", method=RequestMethod.POST)
	public String confirmCreating(@ModelAttribute("newLineName") String lineName,
			@RequestParam("stationsCheck") List<String> stations) {
		linesManager.createLine(lineName);		
		stationOnLineManager.updateStationOnLine(
				linesManager.findByLineName(lineName).getLineId(), stations);
		return "redirect:/allLines";
	}

	@RequestMapping(value="editline/applychanges", method=RequestMethod.GET)
	public String applyChanges(){
		return "redirect:/allLines";
	}

	/*
	 * @RequestMapping(value = "/addLines", method = RequestMethod.GET) public
	 * String addLines(Map<String, Object> modelMap) { modelMap.put("linesList",
	 * linesManager.getFullLines()); return "addLines"; }
	 * 
	 * @RequestMapping(value = "/addnewline") public String addNewLine(
	 * Map<String, Object> modelMap ) { return "editLine"; }
	 * 
	 * @RequestMapping(value = "/savenewline", method = RequestMethod.POST)
	 * public String saveNewLine(@ModelAttribute("newLineName") String
	 * newlineName) { System.out.println(newlineName);
	 * linesManager.createLine(newlineName); Integer newLineId =
	 * linesManager.findByLineName(newlineName) .getLineId();
	 * System.out.println(newLineId); return "redirect:/updateline/" +
	 * newlineName + "/" + newLineId; }
	 * 
	 * @RequestMapping(value = "/addnewstations/{lineId}") public String
	 * addNewStations(@PathVariable("lineId") Integer lineId, Map<String,
	 * Object> modelMap) { try { modelMap.put("stationsList",
	 * stationsManager.findAllStations()); } catch (StationManagerException e) {
	 * // TODO Auto-generated catch block e.printStackTrace(); } return
	 * "allStationsEditLine"; }
	 * 
	 * @RequestMapping(value = "/updateline/addnewstations") public String
	 * addNewStation(Map<String, Object> modelMap) { try {
	 * modelMap.put("stationsList", stationsManager.findAllStations()); } catch
	 * (StationManagerException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return "allStationsEditLine"; }
	 * 
	 * @RequestMapping(value = "/removeline/{remove}") public String
	 * removeLine(@PathVariable("remove") String lineName, Map<String, Object>
	 * modelMap) { linesManager.deleteLine(lineName); return
	 * "redirect:/addLines"; }
	 * 
	 * @RequestMapping(value = "/updateline/{lineName}/{lineId}") public String
	 * updateLine(@PathVariable("lineName") String lineName,
	 * 
	 * @PathVariable("lineId") Integer lineId, Map<String, Object> modelMap) {
	 * modelMap.put("stationsList",
	 * stationsManager.getStationsOnCertainLine(lineName)); return "editLine"; }
	 * 
	 * @RequestMapping(value =
	 * "/updateline/{lineName}/removestation/{removeStId}/{lineId}") public
	 * String removeSt(@PathVariable("removeStId") Integer stationId,
	 * 
	 * @PathVariable("lineName") String lineName,
	 * 
	 * @PathVariable("lineId") Integer lineId, Map<String, Object> modelMap) {
	 * stationOnLineManager.removeStation(stationId, lineId); try {
	 * modelMap.put("stationsList", stationsManager.findAllStations()); } catch
	 * (StationManagerException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return "redirect:/updateline/" + lineName + "/" +
	 * lineId; }
	 * 
	 * @RequestMapping(value = "/addnewstations/confirmaddstations", method =
	 * RequestMethod.POST) public String confirmAddStations(
	 * 
	 * @RequestParam("checkStations") String[] stationsName,
	 * 
	 * @ModelAttribute("lineId") Integer lineId) { List<String> stationName =
	 * new ArrayList<String>(); for (String str : stationsName) {
	 * stationName.add(str); } stationOnLineManager.addStationsToLine(lineId,
	 * stationName);
	 * 
	 * return "redirect:/addLines"; }
	 */
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
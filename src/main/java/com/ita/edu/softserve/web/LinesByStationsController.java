package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;

@Controller
public class LinesByStationsController {
@Autowired
private LinesManager linesManager;
@RequestMapping(value = "/linesByStation", method = RequestMethod.GET)
public String linesByStation(Map<String, Object> modelMap) {
	return "linesByStation";
}
@RequestMapping(value = "/findLinesByStation", method = RequestMethod.GET)
public String linesByStation(@RequestParam("stationName") String stationName,Map<String, Object> modelMap) {
	if(stationName.equalsIgnoreCase("")){
		return "linesByStation";
	}
	modelMap.put("linesByStationList", linesManager.getLinesByStation(stationName));
	return "linesByStation";
}
}

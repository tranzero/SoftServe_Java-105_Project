package com.ita.edu.softserve.web;

import java.sql.Time;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.manager.RoutesManager;


@Controller
public class RoutesController {
	/*public RoutesController() {
		// TODO Auto-generated constructor stub
	}*/
	@Autowired
	private RoutesManager routesManager;
	@RequestMapping(value = "/routes", method = RequestMethod.GET)
	public String findRoutersListByStationIdArriving(Map<String, Object> model) {
		return "routes";
	}
	@RequestMapping(value = "/routesSearch", method = RequestMethod.GET)
	public String findRoutersListByStationIdArriving(
			@RequestParam("idStationArriving") int idStationArriving,
			@RequestParam("timeArrivalMin") Time timeArrivalMin,
			@RequestParam("timeArrivalMax") Time timeArrivalMax,
			Map<String, Object> model) {

		//model.put("RoutesList", routesManager.findRoutersListByStationIdArriving(idStationArriving , timeArrivalMin, timeArrivalMax);
		model.put("RoutesList", routesManager.findRoutersListByStationIdArriving(5,new Time(0, 0, 0),new Time(23, 59, 0)));
		return "routes";
	}
}

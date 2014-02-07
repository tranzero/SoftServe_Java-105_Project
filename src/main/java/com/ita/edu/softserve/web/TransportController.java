package com.ita.edu.softserve.web;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;

@Controller
public class TransportController {

	private static final Logger LOGGER = Logger
			.getLogger(TransportController.class);

	@Autowired
	private TransportsManager transportsManager;

	@RequestMapping(value = "/transport", method = RequestMethod.GET)
	public String printTransports(Map<String, Object> modelMap) {

		modelMap.put("transportsList", transportsManager.getAllTransports());

		return "transport";
	}

	/*--------------------------------------for HTML FORM-----------------------------------*/

	@RequestMapping(value = "/formTransport.htm", method = RequestMethod.GET)
	public String transportForm() {
		return "addTransport";
	}

	@RequestMapping(value = "/addTransport.htm", method = RequestMethod.POST)
	public String addTransportToBD(
			@ModelAttribute("transportCode") String transportCode,
			@ModelAttribute("startTime") String startTime,
			@ModelAttribute("routes") String routesCode,
			@ModelAttribute("seatclass1") String seatclass1,
			@ModelAttribute("seatclass2") String seatclass2,
			@ModelAttribute("seatclass3") String seatclass3,
			@ModelAttribute("genprice") String genprice) {

		transportsManager.saveOrUpdateTransport(transportCode, startTime,
				routesCode, seatclass1, seatclass2, seatclass3, genprice);

		return "redirect:/transport";
	}

	/*--------------------------------------for Spring FORM-----------------------------------*/

	/*
	 * @RequestMapping("/formTransport.htm") public String
	 * transportForm(Map<String, Object> map) { map.put("transport", new
	 * Transports()); return "addTransport"; }
	 * 
	 * @RequestMapping(value = "/addTransport.htm", method = RequestMethod.POST)
	 * public String addTransportToBD(
	 * 
	 * @ModelAttribute("transport") Transports transport, BindingResult result)
	 * { try { transportsManager.saveTransports(transport); } catch (Exception
	 * e) { LOGGER.error(e.toString()); } return "redirect:/transport"; }
	 */

	@RequestMapping(value = "/editTransport/{transport}", method = RequestMethod.GET)
	public String editTransport(@PathVariable("transport") Integer transportId,
			Map<String, Object> modelMap) {
		Transports transport = transportsManager
				.findTransportsById(transportId);
		modelMap.put("transport", transport);

		return "editTransport";
	}

	@RequestMapping(value = "/editTransport/{transportId}", method = RequestMethod.POST)
	public String updateTransportToDB(
			@PathVariable("transportId") Integer transportId,
			@ModelAttribute("transportCode") String transportCode,
			@ModelAttribute("startTime") String startTime,
			@ModelAttribute("routes") String routes,
			@ModelAttribute("seatclass1") String seatclass1,
			@ModelAttribute("seatclass2") String seatclass2,
			@ModelAttribute("seatclass3") String seatclass3,
			@ModelAttribute("genprice") String genprice) {

		transportsManager.editTransport(transportId, transportCode, startTime,
				routes, seatclass1, seatclass2, seatclass3, genprice);

		return "redirect:/transport";
	}

	/**
	 * Deletes Transport from database.
	 * 
	 * @param transportId
	 * @return
	 */
	@RequestMapping(value = "/removeTransport/{transportToRemove}", method = RequestMethod.GET)
	public String removeTransport(
			@PathVariable("transportToRemove") Integer transportId) {
		transportsManager.removeTransportById(transportId);
		System.out.println("delete " + transportId);

		return "redirect:/transport";
	}

	/*------------------------------------------------------------------------------*/

	@RequestMapping(value = "/transportTravel", method = RequestMethod.GET)
	public String getLinesByTwoStations(Map<String, Object> model) {
		return "transportTravel";
	}

	@RequestMapping(value = "/transportTravelFind", method = RequestMethod.GET)
	public String getLinesByTwoStations(
			@RequestParam("stationName1") String stationName1,
			@RequestParam("stationName2") String stationName2,
			Map<String, Object> model) {

		if (stationName1.equals("") || stationName2.equals("")) {
			return "transportTravel";
		}

		model.put("TransportTravelList", transportsManager
				.getTransportByTwoStations(stationName1, stationName2));

		return "transportTravel";
	}

}

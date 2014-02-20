/**
 * 
 */
package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.PageInfoContainer;

/**
 *  * Base controller class for Tickets.
 * 
 * @author admin
 */

@Controller
public class TicketController {

	
	@Autowired
	private TransportsManager transportsManager;
	
	@Autowired 
	private TicketsManager ticketsManager;
	
	
	/**
	 * Controller method for displaying getting Transport by two stations page.
	 * 
	 * @param stationName1
	 *            Name of station 1.
	 * @param stationName2
	 *            Name of station 2.
	 * @param modelMap
	 *            Model map to fill.
	 * @return
	 */
	@RequestMapping(value = "/reservationTicket", method = RequestMethod.GET)
	public String getTransportByTwoStations(
			@RequestParam(value = "stationName1", required = false) String stationName1,
			@RequestParam(value = "stationName2", required = false) String stationName2,
			@RequestParam(value = "dateArriving", required = false) String dateArriving,
			@RequestParam(value = "orderBy", required = false) Integer orderBy,
			Map<String, Object> modelMap) {

		if (stationName1 == null || stationName2 == null
				|| stationName1.equals("") || stationName2.equals("")) {
			return "reservationTicket";
		}
		if (orderBy == null) {
			orderBy = 0;
		}

		modelMap.put("TransportTravelList", ticketsManager.getTransportByTwoStations(stationName1, stationName2, dateArriving));

		return "reservationTicket";
	}

}

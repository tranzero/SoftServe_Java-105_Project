/**
 * 
 */
package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;

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
	
	@Autowired
	private TripsManager tripsManager;
	
	
	@RequestMapping(value = "/reservationTicket/{tripId}", method = RequestMethod.GET)
	public String reservationTicket(@PathVariable("tripId") Integer tripId,
			Map<String, Object> modelMap) {
	 
		Trips trip = tripsManager.findByTripId(tripId);
		Transports transport = transportsManager.findTransportsById(trip.getTransport().getTransportId());
		modelMap.put("Trip", trip);
		modelMap.put("Transport", transport);
		return "reservationTicket";
	}

	
}

/**
 * 
 */
package com.ita.edu.softserve.web;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.components.impl.ShoppingBag;
import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.OrdersManager;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.UserNameService;


/**
 *  * Base controller class for Tickets.
 * 
 * @author admin
 */

@Controller("ticketController")
@Scope("request")
public class TicketController {
	
	private static final String LIST_OF_STATIONS_ON_LINE_URL = "/listOfStationsOnLine/{lineName}";

	private static final String LINE_NAME = "lineName";

	private static final String SEAT_TYPE = "seatType";

	private static final String TICKET_NAME = "ticketName";

	private static final String REDIRECT_TO_MAIN_PAGE = "redirect:/orders";

	private static final String RESERVATION_TICKET_PAGE = "reservationTicket";

	private static final String LIST_OF_STATIONS_ON_LINE_PAGE = "listOfStationsOnLine";

	private static final String BAG_PAY_PAGE = "bagPay";

	private static final String BAG_PAGE = "bag";

	private static final String TICKETS_LIST = "ticketsList";

	private static final String DELETE_TICKET_URL = "/delete/{ticketName}/{tripId}";

	private static final String BAG_PAY_URL = "/bagPay";

	private static final String BAG_URL = "/bag";

	private static final String ADD_TO_BAG_URL = "/addToBag/{tripId}/{seatType}";

	private static final String TRANSPORT = "transport";

	private static final String TRIP = "trip";

	private static final String TICKET = "ticket";

	private static final String RESERVATION_TICKET_URL = "/reservationTicket/{tripId}/{seatType}";

	private static final String TRIP_ID = "tripId";

	@Autowired
	private ShoppingBag shoppingBag;
	
	@Autowired
	private  UserNameService userNameService;
	
	@Autowired 
	private TicketsManager ticketsManager;
	
	@Autowired
	private TripsManager tripsManager;
	
	@Autowired
	private OrdersManager ordersManager;
	
	@Autowired
	private StationsManager stationsManager;
	
	@Autowired
	private Validator ticketValidator;

	

	
	@RequestMapping(value = RESERVATION_TICKET_URL, method = RequestMethod.GET)
	public String reservationTicket(@PathVariable(TRIP_ID) Integer tripId,
			@PathVariable(value= SEAT_TYPE) Integer seatType,
			Model model) {
	 
		if(!(seatType.equals(1)) &&  !(seatType.equals(2)) && !(seatType.equals(3))){
			return REDIRECT_TO_MAIN_PAGE;
		}
		else{

			model.addAttribute(TICKET, new Tickets());
			model.addAttribute(TRIP,tripsManager.findByTripId(tripId));
			model.addAttribute(TRANSPORT, tripsManager.findByTripId(tripId).getTransport());
			model.addAttribute(TRIP_ID, tripId);
		return RESERVATION_TICKET_PAGE;
		}
	}

	@RequestMapping(value = ADD_TO_BAG_URL, method = RequestMethod.POST)
	public String reservationTicketPost(@ModelAttribute( value = TICKET) Tickets ticket,
			@PathVariable(TRIP_ID) Integer tripId,
			@PathVariable(value= SEAT_TYPE) Integer seatType,
			BindingResult result,
			Map<String, Object> modelMap) {
		
		ticketValidator.validate(ticket , result);

		if (result.hasErrors()) {
			modelMap.put(TRIP,tripsManager.findByTripId(tripId));
			modelMap.put(TRANSPORT, tripsManager.findByTripId(tripId).getTransport());
			modelMap.put(TRIP_ID, tripId);
			modelMap.put(SEAT_TYPE, seatType);


			return RESERVATION_TICKET_PAGE;
		}
		
		shoppingBag.addTicket(ticketsManager.getTicket(tripsManager.findByTripId(tripId)
				.getTransport().getRoutes().getRouteName(), 
				 tripsManager.findByTripId(tripId), ticket.getCustomerFirstName(),ticket.getCustomerLastName(), seatType));
		modelMap.put(TICKETS_LIST, shoppingBag.getTickets());
		
		return BAG_PAGE;
		
	}
	
	
	@RequestMapping(value = LIST_OF_STATIONS_ON_LINE_URL, method = RequestMethod.GET)
	public String stationsOnLine(@PathVariable(LINE_NAME) String lineName,
			Map<String, Object> modelMap) {

		modelMap.put(LIST_OF_STATIONS_ON_LINE_PAGE, stationsManager.getStationsOnCertainLine(lineName));

		return LIST_OF_STATIONS_ON_LINE_PAGE;
	}
	
	
	@RequestMapping(value=BAG_URL,method = RequestMethod.GET)
	public String shoppingBagGET(Map<String, Object> modelMap){
		
		modelMap.put(TICKETS_LIST, shoppingBag.getTickets());
		
		return BAG_PAGE;
		
	}

	@RequestMapping(value=BAG_PAY_URL,method = RequestMethod.GET)
	public String payingGet(Map<String, Object> modelMap){
		
		
		return BAG_PAY_PAGE;
	}
	
	@RequestMapping(value=BAG_PAY_URL,method=RequestMethod.POST)
	public String payingPost(Map <String,Object> modelMap){
		
		Date orderDate = new Date();
		ordersManager.createOrder(userNameService.getLoggedUserId(),orderDate);
		
		for(Tickets ticket: shoppingBag.getTickets()){
			ticket.setOrder(ordersManager.findByUserIdAndOrderDate(userNameService.getLoggedUserId(), orderDate));
			ticketsManager.createTicket(ticket.getTicketName(), ticket.getOrder().getOrderId(), 
					ticket.getTrip().getTripId(), ticket.getCustomerFirstName(),ticket.getCustomerLastName(), ticket.getSeatType());
		}
		
		shoppingBag.clearBag();
		return REDIRECT_TO_MAIN_PAGE;
	}
	
	
	@RequestMapping(value=DELETE_TICKET_URL, method=RequestMethod.GET)
	public String deleteTciketFromBag(@PathVariable(value=TICKET_NAME) String ticketName,
			@PathVariable(value=TRIP_ID) Integer tripId,
			Map<String,Object> modelMap){
		
		shoppingBag.removeTicket(ticketName, tripId);
		
		modelMap.put(TICKETS_LIST, shoppingBag.getTickets());
		return BAG_PAGE;
	}
	
}

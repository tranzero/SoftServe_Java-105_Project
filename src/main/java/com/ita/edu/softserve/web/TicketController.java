/**
 * 
 */
package com.ita.edu.softserve.web;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.entity.ShoppingBag;
import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.OrdersManager;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.UserNameService;


/**
 *  * Base controller class for Tickets.
 * 
 * @author admin
 */

@Controller("ticketController")
@Scope("session")
public class TicketController {
	
	
	private ShoppingBag shoppingBag = new ShoppingBag();
	
	@Autowired
	private  UserNameService userNameService;
	
	@Autowired 
	private TicketsManager ticketsManager;
	
	@Autowired
	private TripsManager tripsManager;
	
	@Autowired
	private OrdersManager ordersManager;
	
	
	@RequestMapping(value = "/reservationTicket/{tripId}/{seatType}", method = RequestMethod.GET)
	public String reservationTicket(@PathVariable("tripId") Integer tripId,
			@PathVariable(value= "seatType") Integer seatType,
			Map<String, Object> modelMap
			//Model model
			) {
	 
		Trips trip = tripsManager.findByTripId(tripId);
		modelMap.put("trip", trip);
		modelMap.put("transport", trip.getTransport());
		modelMap.put("tripId", tripId);
		modelMap.put("seatType", seatType);
		return "reservationTicket";
	}

	@RequestMapping(value = "/addToBag/{tripId}/{seatType}", method = RequestMethod.POST)
	public String reservationTicketPost(@ModelAttribute( value = "customerInfo") String customerInfo,
			@PathVariable("tripId") Integer tripId,
			@PathVariable(value= "seatType") Integer seatType,
			BindingResult result,
			Map<String, Object> modelMap) {
		Trips trip = tripsManager.findByTripId(tripId);
		
		if(seatType.equals(1)){
			trip.setRemSeatClass1(trip.getRemSeatClass1()-1);
		}
		if(seatType.equals(1)){
			trip.setRemSeatClass2(trip.getRemSeatClass2()-1);
		}
		
		if(seatType.equals(1)){
			trip.setRemSeatClass3(trip.getRemSeatClass3()-1);
		}
		
		tripsManager.updateTrip(trip);
		Tickets ticket = new Tickets(trip.getTransport().getRoutes().getRouteName(),trip,customerInfo,seatType);
		shoppingBag.addTicket(ticket);
		modelMap.put("ticketsList", shoppingBag.getTickets());
		
		return "bag";
	}
	
	@RequestMapping(value="/bag",method = RequestMethod.GET)
	public String shoppingBagGET(@RequestParam(value= "customerInfo",required = false) String customerInfo,
			Map<String, Object> modelMap){
		modelMap.put("ticketsList", shoppingBag.getTickets());
		System.out.println("bag");
		return "bag";
		
	}

	@RequestMapping(value="/bag",method = RequestMethod.POST)
	public String shoppingBagPOST(
			Map<String, Object> modelMap){
		
		
//		ordersManager.createOrder(userNameService.getLoggedUserId(), order.getTripId().getTripId());
//		for(Tickets tmp: shoppingBag.getTickets()){
//			tmp.setOrder(order);
//			ticketsManager.createTicket(tmp.getTicketName(), tmp.getOrder().getOrderId(), tmp.getTrip().getTripId(), tmp.getCustomerInfo(), tmp.getSeatType());
//		}
//		
		return "";
	}
	
	@RequestMapping(value="/delete/{ticketName}/{tripId}", method=RequestMethod.GET)
	public String deleteTciketFromBag(@PathVariable(value="ticketName") String ticketName,
			@PathVariable(value="tripId") Integer tripId,
			Map<String,Object> modelMap){
		List<Tickets> ticketsList = shoppingBag.getTickets();
		for(Tickets ticket: ticketsList){
			if(ticket.getTicketName().equals(ticketName) && ticket.getTrip().getTripId().equals(tripId)){
				shoppingBag.removeTicket(ticket);
			}
		}

		modelMap.put("ticketsList", shoppingBag.getTickets());
		return "bag";
	}
	
}

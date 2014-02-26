/**
 * 
 */
package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Orders;
import com.ita.edu.softserve.entity.ShoppingBag;
import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.OrdersManager;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;

/**
 *  * Base controller class for Tickets.
 * 
 * @author admin
 */

@Controller("ticketController")
@Scope("session")
public class TicketController {
	
	
	private ShoppingBag shoppingBag = new ShoppingBag();
	
	Integer currentTripId;
	
	@Autowired
	private  UserNameService userNameService;
	
	@Autowired 
	private TicketsManager ticketsManager;
	
	@Autowired
	private TripsManager tripsManager;
	
	@Autowired
	private OrdersManager ordersManager;
	
	
	@RequestMapping(value = "/reservationTicket/{tripId}", method = RequestMethod.GET)
	public String reservationTicket(@PathVariable("tripId") Integer tripId,
			//Map<String, Object> modelMap
			Model model
			) {
	 
		Trips trip = tripsManager.findByTripId(tripId);
//		modelMap.put("trip", trip);
//		modelMap.put("transport", trip.getTransport());
//		modelMap.put("ticket", new Tickets());
//		System.out.println("get");
		currentTripId = tripId;
		model.addAttribute("trip", trip);
		model.addAttribute("transport", trip.getTransport());
		model.addAttribute("ticket", new Tickets());
		return "reservationTicket";
	}

	@RequestMapping(value = "/reservationTicket/addToBag", method = RequestMethod.POST)
	public String reservationTicketPost(@ModelAttribute( value = "ticket") Tickets ticket,
			BindingResult result,
			Map<String, Object> modelMap) {
		Trips trip = tripsManager.findByTripId(currentTripId);
		
		if(ticket.getIsSeatClass1()){
			trip.setRemSeatClass1(trip.getRemSeatClass1()-1);
		}
		if(ticket.getIsSeatClass2()){
			trip.setRemSeatClass2(trip.getRemSeatClass2()-1);
		}
		if(ticket.getIsSeatClass3()){
			trip.setRemSeatClass3(trip.getRemSeatClass3()-1);
		}
		 
		ticket.setTrip(trip);
		ticket.setTicketName(trip.getTransport().getRoutes().getRouteName());
		shoppingBag.addTicket(ticket);
		System.out.println("post");
		return "bag";
	}
	
	@RequestMapping(value="/bag",method = RequestMethod.GET)
	public String shoppingBagGET(
			Map<String, Object> modelMap){
		modelMap.put("ticketsList", shoppingBag.getTickets());
		return "bag";
	}

	@RequestMapping(value="/bag",method = RequestMethod.POST)
	public String shoppingBagPOST(
			Map<String, Object> modelMap){
		
		modelMap.put("ticketsList", shoppingBag.getTickets());
		Orders order = new Orders();
		order.setTripId(shoppingBag.getTickets().get(0).getTrip());
		ordersManager.createOrder(userNameService.getLoggedUserId(), order.getTripId().getTripId());
		for(Tickets tmp: shoppingBag.getTickets()){
			tmp.setOrder(order);
			ticketsManager.createTicket(tmp.getTicketName(), tmp.getOrder().getOrderId(), tmp.getTrip().getTripId(), tmp.getCustomerInfo(), tmp.getIsSeatClass1(), tmp.getIsSeatClass2(), tmp.getIsSeatClass3());
		}
		
		return "";
	}
	
}

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.components.impl.ShoppingBag;
import com.ita.edu.softserve.entity.Orders;
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
@Scope("request")
public class TicketController {
	
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
	
	
	@RequestMapping(value = "/reservationTicket/{tripId}/{seatType}", method = RequestMethod.GET)
	public String reservationTicket(@PathVariable("tripId") Integer tripId,
			@PathVariable(value= "seatType") Integer seatType,
			Map<String, Object> modelMap) {
	 
		if(!(seatType.equals(1)) &&  !(seatType.equals(2)) && !(seatType.equals(3))){
			return "redirect:/";
		}
		else{
		modelMap.put("trip",tripsManager.findByTripId(tripId));
		modelMap.put("transport", tripsManager.findByTripId(tripId).getTransport());
		modelMap.put("tripId", tripId);
		modelMap.put("seatType", seatType);
		return "reservationTicket";
		}
	}

	@RequestMapping(value = "/addToBag/{tripId}/{seatType}", method = RequestMethod.POST)
	public String reservationTicketPost(@ModelAttribute( value = "customerInfo") String customerInfo,
			@PathVariable("tripId") Integer tripId,
			@PathVariable(value= "seatType") Integer seatType,
			BindingResult result,
			Map<String, Object> modelMap) {
		
		
		shoppingBag.addTicket(ticketsManager.getTicket(tripsManager.findByTripId(tripId)
				.getTransport().getRoutes().getRouteName(), 
				 tripsManager.findByTripId(tripId), customerInfo, seatType));
		modelMap.put("ticketsList", shoppingBag.getTickets());
		
		return "bag";
		
	}
	
	@RequestMapping(value="/bag",method = RequestMethod.GET)
	public String shoppingBagGET(@RequestParam(value= "customerInfo",required = false) String customerInfo,
			Map<String, Object> modelMap){
		
		modelMap.put("ticketsList", shoppingBag.getTickets());
		
		return "bag";
		
	}

	@RequestMapping(value="/bagPay",method = RequestMethod.GET)
	public String payingGet(@RequestParam(value= "customerInfo",required = false) String customerInfo,
			Map<String, Object> modelMap){
		
		
		return "bagPay";
	}
	
	@RequestMapping(value="/bagPay",method=RequestMethod.POST)
	public String payingPost(Map <String,Object> modelMap){
		
		Date orderDate = new Date();
		ordersManager.createOrder(userNameService.getLoggedUserId(),orderDate);
		
		for(Tickets ticket: shoppingBag.getTickets()){
			ticket.setOrder(ordersManager.findByUserIdAndOrderDate(userNameService.getLoggedUserId(), orderDate));
			ticketsManager.createTicket(ticket.getTicketName(), ticket.getOrder().getOrderId(), 
					ticket.getTrip().getTripId(), ticket.getCustomerInfo(), ticket.getSeatType());
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/delete/{ticketName}/{tripId}", method=RequestMethod.GET)
	public String deleteTciketFromBag(@PathVariable(value="ticketName") String ticketName,
			@PathVariable(value="tripId") Integer tripId,
			Map<String,Object> modelMap){
		if(!(shoppingBag.getTickets().isEmpty())){
		List<Tickets> ticketsList = shoppingBag.getTickets();
		for(Tickets ticket: ticketsList){
			if(ticket.getTicketName().equals(ticketName) && ticket.getTrip().getTripId().equals(tripId)){
				
				shoppingBag.removeTicket(ticketsList.indexOf(ticket));
			}
		}
		}

		modelMap.put("ticketsList", shoppingBag.getTickets());
		return "bag";
	}
	
}

/**
 * 
 */
package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
	
	private Tickets tickets;
	
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
			Map<String, Object> modelMap) {
	 
		Trips trip = tripsManager.findByTripId(tripId);
		modelMap.put("trip", trip);
		modelMap.put("transport", trip.getTransport());
		System.out.println("get");
		tickets = new Tickets(trip.getTransport().getRoutes().getRouteName(),trip,"",false,false,false);
	
		return "reservationTicket";
	}
	

	@RequestMapping(value = "/reservationTicket/reservationTicket", method = RequestMethod.POST)
	public String reservationTicketPost(
			@RequestParam(value ="customerInfo") String customerInfo,
			@RequestParam(value ="seat1") boolean isSeatClass1,
			@RequestParam(value ="seat2") boolean isSeatClass2,
			@RequestParam(value ="seat3") boolean isSeatClass3,
			Map<String, Object> modelMap) {
		
		Trips trip = tripsManager.findByTripId(tickets.getTrip().getTripId());
		
		if(isSeatClass1){
			trip.setRemSeatClass1(tickets.getTrip().getRemSeatClass1()-1);
		}
		if(isSeatClass2){
			trip.setRemSeatClass2(tickets.getTrip().getRemSeatClass2()-1);
		}
		if(isSeatClass3){
			trip.setRemSeatClass3(tickets.getTrip().getRemSeatClass3()-1);
		}
		tickets.setCustomerInfo(customerInfo);
		tickets.setIsSeatClass1(isSeatClass1);
		tickets.setIsSeatClass2(isSeatClass2);
		tickets.setIsSeatClass3(isSeatClass3);
		shoppingBag.addTicket(tickets);
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

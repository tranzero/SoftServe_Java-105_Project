package com.ita.edu.softserve.components.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.TripsManagerImpl;

@Component
@Scope("session")
public class ShoppingBag implements Serializable {

	private static final long serialVersionUID = -4741144366790593201L;

	private List<Tickets> tickets;

	public ShoppingBag() {
		this.tickets = new ArrayList<Tickets>();

	}

	public List<Tickets> getTickets() {
		return tickets;
	}

	public void addTicket(Tickets ticket) {
		TripsManager tripsManager = TripsManagerImpl.getInstance();
		tripsManager.reduceFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
		
		tickets.add(ticket);
	}

	public void removeTicket(String ticketName,Integer tripId) {
		
		TripsManager tripsManager = TripsManagerImpl.getInstance();

		for(Tickets ticket:tickets){
			
			if(ticket.getTicketName().equals(ticketName) && ticket.getTrip().getTripId().equals(tripId)){
				
				tripsManager.increaseFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
				
					tickets.remove(ticket);
				
				}
		}
	}
	public void clearBag(){
		tickets.clear();
	}

	@Scheduled(fixedDelay = 5000)
	public void clear() {
		TripsManager tripsManager = TripsManagerImpl.getInstance();

		for(Tickets ticket:tickets){
			
			tripsManager.increaseFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
				
		}
		tickets.clear();
	}

	@PreDestroy
	public void preDestroy() {
		TripsManager tripsManager = TripsManagerImpl.getInstance();

		for(Tickets ticket:tickets){
			
			tripsManager.increaseFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
				
		}
		tickets.clear();
		
	}
}

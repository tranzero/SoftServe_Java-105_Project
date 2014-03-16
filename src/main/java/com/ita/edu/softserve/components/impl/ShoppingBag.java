package com.ita.edu.softserve.components.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.impl.TripsManagerImpl;

@Component
@Scope("session")
public class ShoppingBag implements Serializable {

	private static final long serialVersionUID = -4741144366790593201L;
	
	@Autowired
	TripsManager tripsManager;

	private List<Tickets> tickets;

	public ShoppingBag() {
		this.tickets = new LinkedList<Tickets>();

	}

	/**
	 * 
	 * @return list of tickets in bag
	 */
	public List<Tickets> getTickets() {
		return tickets;
	}

	/**
	 * add ticket to bag and reduce quantity of free seats in trip
	 * @param ticket
	 */
	public void addTicket(Tickets ticket) {
		TripsManager tripsManager = TripsManagerImpl.getInstance();
		tripsManager.reduceFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
		System.out.println("add");
		tickets.add(ticket);
	}

	@Transactional
	public void remove(String ticketName, Integer tripId) {
		
		for (Tickets ticket:tickets) {
			if (ticket.getTicketName().equals(ticketName) && ticket.getTrip().getTripId().equals(tripId)) {
				tripsManager.increaseFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
			}
			tickets.remove(ticket);
		}
		System.out.println("remove");
	}
	
	
	/**
	 * remove from bag ticket with certain name and trip id and increase quantity of free seat in trip
	 * @param ticketName
	 * @param tripId
	 */
	public void removeTicket(String ticketName, Integer tripId) {

		TripsManager tripsManager = TripsManagerImpl.getInstance();

		int i = 0;
		do {
			if (tickets.get(i).getTicketName().equals(ticketName) && tickets.get(i).getTrip().getTripId().equals(tripId)) {

				tripsManager.increaseFreeSeatsQuantity(tickets.get(i).getTrip().getTripId(), tickets.get(i).getSeatType());
				tickets.remove(tickets.get(i));
			}
			i++;
		} while (i < tickets.size());

		System.out.println("remove");
	}
	

	/**
	 * remove tickets from bag without increase free seats quantity
	 */
	public void clearBag(){
		tickets.clear();
	}

	/**
	 * scheduled bag cleaning
	 */
	@Scheduled(fixedDelay = 90000)
	public void clear() {
		TripsManager tripsManager = TripsManagerImpl.getInstance();

		for(Tickets ticket:tickets){
			
			tripsManager.increaseFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());			
		}
		tickets.clear();
	}

	/**
	 * bag cleaning before bean destroy
	 */
	@PreDestroy
	public void preDestroy() {
		TripsManager tripsManager = TripsManagerImpl.getInstance();

		for(Tickets ticket:tickets){
			
			tripsManager.increaseFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());		
		}
		tickets.clear();
	}
}

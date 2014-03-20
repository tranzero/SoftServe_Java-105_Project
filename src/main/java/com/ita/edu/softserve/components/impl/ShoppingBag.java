package com.ita.edu.softserve.components.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.manager.TripsManager;


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
	 * 
	 * @param ticket
	 */
	public void addTicket(Tickets ticket) {

		tripsManager.reduceFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
		tickets.add(ticket);
	}

	/**
	 * remove from bag ticket with certain name and trip id and increase
	 * quantity of free seat in trip
	 * 
	 * @param ticketName
	 * @param tripId
	 */
	public void removeTicket(String ticketName, Integer tripId) {

		for (Tickets ticket : tickets) {
			if ((ticket.getTicketName().equals(ticketName)) && (ticket.getTrip().getTripId().equals(tripId))) {
				tripsManager.increaseFreeSeatsQuantity(ticket.getTrip().getTripId(), ticket.getSeatType());
				tickets.remove(ticket);
			}
		}
	}

	/**
	 * remove tickets from bag without increase free seats quantity
	 */
	public void clearBag() {
		tickets.clear();
	}

	/**
	 * scheduled bag cleaning
	 */
	@Scheduled(fixedDelay = 90000)
	public void clear() {

		for (Tickets ticket : tickets) {

			tripsManager.increaseFreeSeatsQuantity(
					ticket.getTrip().getTripId(), ticket.getSeatType());
		}
		tickets.clear();
	}

	/**
	 * bag cleaning before bean destroy
	 */
	@PreDestroy
	public void preDestroy() {

		for (Tickets ticket : tickets) {

			tripsManager.increaseFreeSeatsQuantity(
					ticket.getTrip().getTripId(), ticket.getSeatType());
		}
		tickets.clear();
	}
}

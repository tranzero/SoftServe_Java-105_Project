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
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.TripsManager;

@Component
@Scope("session")
public class ShoppingBag implements Serializable {

	private static final long serialVersionUID = -4741144366790593201L;


	@Autowired
	TripsManager tripsManager;

	private List<Tickets> tickets;

	public ShoppingBag() {
		this.tickets = new ArrayList<Tickets>();

	}

	public List<Tickets> getTickets() {
		return tickets;
	}

	public void addTicket(Tickets ticket) {

		Trips trip = ticket.getTrip();
		if (ticket.getSeatType().equals(1)) {

			trip.setRemSeatClass1(ticket.getTrip().getRemSeatClass1() - 1);

		}

		if (ticket.getSeatType().equals(2)) {

			trip.setRemSeatClass2(ticket.getTrip().getRemSeatClass2() - 1);

		}

		if (ticket.getSeatType().equals(3)) {

			trip.setRemSeatClass3(ticket.getTrip().getRemSeatClass3() - 1);

		}

		tripsManager.updateTrip(trip);
		tickets.add(ticket);
	}

	public void removeTicket(String ticketName,Integer tripId) {

		for(Tickets ticket:tickets){
			
			if(ticket.getTicketName().equals(ticketName) && ticket.getTrip().getTripId().equals(tripId)){
				tickets.remove(ticket);
				}
		}
	}

	@Scheduled(fixedDelay = 15000)
	public void clear() {
		System.out.println("scheduler");
		tickets.clear();
	}

	@PreDestroy
	public void preDestroy() {
		for (Tickets ticket : tickets) {

			if (ticket.getSeatType().equals(1)) {

				ticket.getTrip().setRemSeatClass1(ticket.getTrip().getRemSeatClass1() + 1);

			}

			if (ticket.getSeatType().equals(2)) {

				ticket.getTrip().setRemSeatClass2(ticket.getTrip().getRemSeatClass2() + 1);

			}

			if (ticket.getSeatType().equals(3)) {

				ticket.getTrip().setRemSeatClass3(ticket.getTrip().getRemSeatClass3() + 1);

			}

			tripsManager.updateTrip(ticket.getTrip());
			
		}
		
		tickets.clear();
		System.out.println("destroy");
	}
}

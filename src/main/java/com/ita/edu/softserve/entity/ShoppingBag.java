package com.ita.edu.softserve.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="shoppingBag")
//@Scope("session")
public class ShoppingBag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4741144366790593201L;
	private List<Tickets> tickets;

	public ShoppingBag() {
		this.tickets = new ArrayList<Tickets>();
		
	}

	public List<Tickets> getTickets() {
		return tickets;
	}
	
	public void addTicket(Tickets ticket){
		
		tickets.add(ticket);
	}
	
	public void removeTicket(Tickets ticket){
		
	if(tickets.contains(ticket)){
		
		tickets.remove(tickets.indexOf(ticket));
		
	}
	}
	
	

}

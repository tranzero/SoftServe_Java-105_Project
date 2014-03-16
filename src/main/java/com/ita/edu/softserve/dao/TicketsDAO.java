package com.ita.edu.softserve.dao;


import java.util.List;

import com.ita.edu.softserve.entity.Tickets;

public interface TicketsDAO extends AbstractDAOIface<Tickets>{
	
	/**
	 * 
	 * @param id of order
	 * @return  list of tickets with certain order id
	 */
	List<Tickets> findTicketsByOrderId(Integer id);
	
	/**
	 * 
	 * @param id of trip
	 * @return  list of tickets with certain trip id
	 */
	List<Tickets> findTicketsByTripId(Integer id);

	/**
	 * Find Ticket by name.
	 * 
	 * @param ticketName
	 * @return
	 */
	Tickets findByName(String ticketName);
	
}

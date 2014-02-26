package com.ita.edu.softserve.manager;


import java.util.List;

import com.ita.edu.softserve.entity.Tickets;


public interface TicketsManager extends BaseManager {
	
	public List<Tickets> getAllTickets();
	
	public Tickets findByTicketId (Integer id);
	
	public void saveTicket (Tickets ticket);
	
	public void createTicket (String ticketName, Integer orderId, Integer tripId,
			String customerInfo, Integer seatType);
	
	public void removeTicket (Integer ticketId);
	

}

package com.ita.edu.softserve.manager;

import java.sql.Date;
import java.util.List;

import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.manager.impl.TransportTravel;

public interface TicketsManager extends BaseManager {
	
	public List<Tickets> getAllTickets();
	
	public Tickets findByTicketId (Integer id);
	
	public void createTicket (String ticketName, Integer orderId, Integer tripId,
			String customerInfo, Boolean isSeatClass1,
			Boolean isSeatClass2, Boolean isSeatClass3);
	
	public void removeTicket (Integer ticketId);
	
	public List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2, String date);

}

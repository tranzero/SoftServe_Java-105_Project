package com.ita.edu.softserve.manager.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.OrdersDAO;
import com.ita.edu.softserve.dao.TicketsDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TransportsManager;


@Service("ticketsService")
public class TicketsManagerImpl implements TicketsManager{

	private static final Logger LOGGER = Logger.getLogger(Tickets.class);
	
	@Autowired
	private TicketsDAO ticketsDao;
	
	@Autowired
	private OrdersDAO ordersDao;
	
	@Autowired
	private TripsDAO tripsDao;
	
	@Autowired
	private TransportsDao transportDao;

	
	public static TicketsManager getInstance() {
		return ManagerFactory.getManager(TicketsManager.class);
	}
	
	@Transactional
	@Override
	public List<Tickets> getAllTickets(){
		
		return ticketsDao.getAllEntities();
		
	}
	
	@Transactional
	@Override
	public Tickets findByTicketId (Integer id){
		
		return ticketsDao.findById(id);
		
	}
	

	@Transactional
	@Override
	public void saveTicket (Tickets ticket){
		ticketsDao.save(ticket);
	}
	
	@Transactional
	@Override
	public void createTicket (String ticketName, Integer orderId, Integer tripId,
			String customerInfo, Integer seatType){
		
		Tickets ticket = new Tickets(ticketName, ordersDao.findById(orderId), tripsDao.findById(tripId),
				customerInfo, seatType);
		
		ticketsDao.save(ticket);
		
		
		
}
	
	@Transactional
	@Override
	public void removeTicket (Integer ticketId){
		
		Tickets ticket = ticketsDao.findById(ticketId);
		ticketsDao.remove(ticket);
		
	}
	
	
	@Override
	public Tickets getTicket(String ticketName, Trips trip,
			String customerInfo, Integer seatType){
	
			return new Tickets(ticketName, trip,
			customerInfo, seatType);
	
	
}

	
}

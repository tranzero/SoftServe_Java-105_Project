package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.entity.Trips;

public interface TicketsManager extends BaseManager {

	/**
	 * 
	 * @return List of all tickets from database
	 */
	public List<Tickets> getAllTickets();

	/**
	 * 
	 * @param ticket
	 *            id
	 * @return Ticket with certain id
	 */
	public Tickets findByTicketId(Integer id);

	/**
	 * 
	 * @param ticket
	 *            which will be saved
	 */
	public void saveTicket(Tickets ticket);

	/**
	 * Create new ticket and save it to database
	 * 
	 * @param ticketName
	 * @param orderId
	 * @param tripId
	 * @param customerFirstName
	 * @param customerLastName
	 * @param seatType
	 */
	public void createTicket(String ticketName, Integer orderId,
			Integer tripId, String customerFirstName, String customerLastName,
			Integer seatType);

	/**
	 * Remove ticket with certain id from database
	 * 
	 * @param ticketId
	 */
	public void removeTicket(Integer ticketId);

	/**
	 * Method for bag use only
	 * 
	 * @param ticketName
	 * @param trip
	 * @param customerFirstName
	 * @param customerLastName
	 * @param seatType
	 * @return ticket with certain fields
	 */
	public Tickets getTicket(String ticketName, Trips trip,
			String customerFirstName, String customerLastName, Integer seatType);

	/**
	 * 
	 * @param id
	 * @return list of tickets with certain order id
	 */
	public List<Tickets> findTicketsByOrderId(Integer id);

	List<Tickets> ticketsForPage(Integer id);

	/**
	 * Method find Ticket by Name.
	 * 
	 * @param ticketName
	 * @return
	 */
	Tickets findByTicketName(String ticketName);

}

package com.ita.edu.softserve.dao;


import java.util.List;

import com.ita.edu.softserve.entity.Tickets;

public interface TicketsDAO extends AbstractDAOIface<Tickets>{
	List<Tickets> findTicketsByOrderId(Integer id);
}

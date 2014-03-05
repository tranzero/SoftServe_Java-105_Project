package com.ita.edu.softserve.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.manager.OrdersManager;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.UserNameService;

@Controller
public class OrdersController {
	
	final static String PageOrdersCall = "/orders";
	final static String PageOrdersGet = "orders";
	

	@Autowired
	private OrdersManager ordersManager;

	@Autowired
	private TicketsManager ticketsManager;

	@Autowired
	private UserNameService userNameService;

	@RequestMapping(value = PageOrdersCall, method = RequestMethod.GET)
	public String orders(Map<String, Object> modelMap) {
		List<Tickets> tlist = ticketsManager.ticketsForPage(userNameService.getLoggedUserId());
		modelMap.put("ticketsList", tlist);
		return PageOrdersGet;

	}

}

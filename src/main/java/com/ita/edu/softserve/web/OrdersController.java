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
	
	/**
	 * String that defines orders web page
	 */
	final static String PageOrdersCall = "/orders";
	
	/**
	 * Spring response that defines trips jsp page
	 */
	final static String PageOrdersGet = "orders";
	
	/**
	 * Get access to Orders Manager
	 */
	@Autowired
	private OrdersManager ordersManager;

	/**
	 * Get access to Tickets Manager
	 */
	@Autowired
	private TicketsManager ticketsManager;

	/**
	 * Get access to User Name Serice
	 */
	@Autowired
	private UserNameService userNameService;

	/**
	 * Displays orders page in browser
	 * @param modelMap Model map to fill
	 * @return the jsp name
	 */
	@RequestMapping(value = PageOrdersCall, method = RequestMethod.GET)
	public String orders(Map<String, Object> modelMap) {
		List<Tickets> tlist = ticketsManager.ticketsForPage(userNameService.getLoggedUserId());
		modelMap.put("ticketsList", tlist);
		return PageOrdersGet;

	}

}

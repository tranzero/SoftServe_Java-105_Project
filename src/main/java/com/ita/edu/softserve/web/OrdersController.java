package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.manager.OrdersManager;

@Controller
public class OrdersController {
	@Autowired
	private OrdersManager ordersManager;
	
	@RequestMapping(value="/orders", method = RequestMethod.GET) 
	public String listOrders (Map <String,Object> model) {
		model.put("ordersList", ordersManager.findAllOrders());
		return "orders";
	}
}

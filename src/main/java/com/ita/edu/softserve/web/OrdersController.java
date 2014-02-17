package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.manager.OrdersManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;

@Controller
public class OrdersController {
	String pageNumberKey = "pageNumber";
	String resultsPerPageKey = "resultsPerPage";
	String sizeOfPagingKey = "sizeOfPaging";
	String maxPageCountKey = "maxPageCount";
	String maxResultCountKey = "maxResultCount";
	String ordersListKey = "ordersList";


	String PageOrdersGet = "orders";
	String PageOrdersOutPost = "ordersp";

	private PaginationManager pageMan = PaginationManager.getInstance();
	
	
	@Autowired
	private OrdersManager ordersManager;

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String orders(Map<String, Object> modelMap) {

		modelMap.put(pageNumberKey, pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());

		int maxPageCount = pageMan.getMaxPageCount(
				pageMan.getDefaultResultPerPage(),
				ordersManager.getOrdersListCount());

		modelMap.put(maxPageCountKey, maxPageCount);
		return PageOrdersGet;

	}

	@RequestMapping(value = "/ordersp", method = RequestMethod.POST)
	public String ordersPage(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("resultsPerPage") int resultsPerPage,
			Map<String, Object> modelMap) {

		long resultCount = ordersManager.getOrdersListCount();
		modelMap.put(maxResultCountKey, resultCount);
		int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		modelMap.put(maxPageCountKey, maxPageCount);
		int currentPagingPosition = pageMan.getCurrentPagingPosition(
				pageNumber, resultsPerPage);
		modelMap.put(pageNumberKey, pageNumber);
		modelMap.put(resultsPerPageKey, resultsPerPage);
		modelMap.put(ordersListKey, ordersManager.getOrdersForPage(
				currentPagingPosition-1, resultsPerPage));

		return PageOrdersOutPost;

	}

}

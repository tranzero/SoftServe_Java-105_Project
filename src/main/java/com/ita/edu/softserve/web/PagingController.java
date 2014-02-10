package com.ita.edu.softserve.web;

import java.util.Map;

import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.PageInfoContainer;

public class PagingController {
	
	public static void deployPaging(Map<String, Object> modelMap, 
			PageInfoContainer container,PaginationManager paginationManager){
		int pageAmount = paginationManager.getDefaultPageCount();
		int firstPage = container.getPageNumber()-(pageAmount/2);
		int lastPage = container.getPageNumber()+(pageAmount/2);
		if (firstPage<1){
			firstPage=1;
		}
		if (lastPage>container.getMaxPages()){
			lastPage=container.getMaxPages();
		}
		modelMap.put(PaginationManager.PAGE_NUMBER_NAME, container.getPageNumber());
		modelMap.put(PaginationManager.RESULTS_PER_PAGE_NAME, container.getResultsPerPage());
		modelMap.put(PaginationManager.MAX_PAGES_NAME, container.getMaxPages());
		modelMap.put(PaginationManager.FIRST_PAGE_NAME, firstPage);
		modelMap.put(PaginationManager.LAST_PAGE_NAME, lastPage);
	}

}

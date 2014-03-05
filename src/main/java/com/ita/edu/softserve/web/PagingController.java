package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.ui.ModelMap;

import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;


public class PagingController {
	
	/**
	 * Fills given model map with info, related to paging
	 * @param modelMap Model map to fill
	 * @param container container of paging info (must be validated)
	 * @param paginationManager manager for pagination
	 */
	
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

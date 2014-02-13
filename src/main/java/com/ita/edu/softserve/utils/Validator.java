package com.ita.edu.softserve.utils;

import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.manager.impl.PaginationManager.SingletonHolder;


/**
 * 
 * Class for gathering different validate methods 
 * 
 * @author dnycktc
 *
 */

public class Validator {

	/**
	 * Validates paging info
	 * @param container
	 * @param paginationManager
	 */
	
	public static void validatePaging(PageInfoContainer container,
			PaginationManager paginationManager) {
		if (container.getPageNumber() == null) {
			container.setPageNumber(new Integer(paginationManager
					.getDefaultPageNumber()));
		} else if (container.getPageNumber() < 1) {
			container.setPageNumber(new Integer(paginationManager
					.getDefaultPageNumber()));
		}
		if (container.getResultsPerPage() == null) {
			container.setResultsPerPage(new Integer(paginationManager
					.getDefaultResultPerPage()));
		} else if (container.getResultsPerPage() < 1) {
			container.setResultsPerPage(new Integer(paginationManager
					.getDefaultResultPerPage()));
		}
		container.setMaxPages(SingletonHolder.HOLDER_INSTANCE.getMaxPageCount(
				container.getResultsPerPage(), container.getCount()));
		if (container.getPageNumber() > container.getMaxPages()) {
			container.setPageNumber(container.getMaxPages());
		}
	}

}

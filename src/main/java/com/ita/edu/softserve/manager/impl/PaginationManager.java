package com.ita.edu.softserve.manager.impl;



import org.springframework.stereotype.Service;

import com.ita.edu.softserve.utils.PageInfoContainer;
import com.ita.edu.softserve.utils.Validator;


 

@Service
public final class PaginationManager {
	private final int defaultPageCount = 10;
	private final int defaultPageNumber = 1;
	private final int defaultResultPerPage = 10;
	
	public static final String PAGE_NUMBER_NAME = "pageNumber";
	public static final String RESULTS_PER_PAGE_NAME = "resultsPerPage";
	public static final String MAX_PAGES_NAME = "maxPages";
	public static final String FIRST_PAGE_NAME = "firstPage";
	public static final String LAST_PAGE_NAME = "lastPage";
	
	public void validatePaging(PageInfoContainer container){
		Validator.validatePaging(container, SingletonHolder.HOLDER_INSTANCE);
	}
	
	
	
	 public static class SingletonHolder {
	        public static final PaginationManager HOLDER_INSTANCE = new PaginationManager();
	    }
	        
	    public static PaginationManager getInstance() {
	        return SingletonHolder.HOLDER_INSTANCE;
	    }
	
	
	public int getMaxPageCount(final int resultsPerPage, long resultCount) {
		if(resultCount <= 0) {
			resultCount = this.getDefaultPageCount();
		}
		return (int) ((resultCount -1)/resultsPerPage +1);
	}

	public int getCurrentPagingPosition(int pageNumber, int resultsPerPage) {
		if (pageNumber <= 0) {
		    return this.getDefaultPageNumber();
		}

		return pageNumber * resultsPerPage;
	}

	
	/**
	 * @return the defaultPageCount
	 */
	public int getDefaultPageCount() {
		return defaultPageCount;
	}

	/**
	 * @return the defaultPageNumber
	 */
	public int getDefaultPageNumber() {
		return defaultPageNumber;
	}

	/**
	 * @return the defaultResultPerPage
	 */
	public int getDefaultResultPerPage() {
		return defaultResultPerPage;
	}

}

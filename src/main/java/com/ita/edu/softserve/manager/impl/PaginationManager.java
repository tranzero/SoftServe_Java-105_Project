package com.ita.edu.softserve.manager.impl;



import org.springframework.stereotype.Service;

import com.ita.edu.softserve.utils.PageInfoContainer;


 

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
		if (container.getPageNumber()== null){
			container.setPageNumber(new Integer(SingletonHolder.HOLDER_INSTANCE.defaultPageNumber));
		}
		else if (container.getPageNumber()<1){
			container.setPageNumber(new Integer(SingletonHolder.HOLDER_INSTANCE.defaultPageNumber));
		}
		if (container.getResultsPerPage()==null){
			container.setResultsPerPage(new Integer(SingletonHolder.HOLDER_INSTANCE.getDefaultResultPerPage()));
		}
		else if (container.getResultsPerPage()<1){
			container.setResultsPerPage(new Integer(SingletonHolder.HOLDER_INSTANCE.getDefaultResultPerPage()));
		}
		
		container.setMaxPages(SingletonHolder.HOLDER_INSTANCE.getMaxPageCount(
				container.getResultsPerPage(), container.getCount()));
		if (container.getPageNumber()>container.getMaxPages()){
			container.setPageNumber(container.getMaxPages());
		}
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

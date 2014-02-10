package com.ita.edu.softserve.manager.impl;



import org.springframework.stereotype.Service;


 

@Service
public final class PaginationManager {
	private final int defaultPageCount = 10;
	private final int defaultPageNumber = 1;
	private final int defaultResultPerPage = 10;
	
	
	
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

package com.ita.edu.softserve.manager.impl;



import org.springframework.stereotype.Service;

import com.ita.edu.softserve.manager.PaginationManager;

 

@Service
public class PaginationManagerImpl implements PaginationManager{
	private final int defaultPageCount = 1;
	private final int defaultPageNumber = 1;
	
	@Override
	public int getMaxPageCount(int resultsPerPage, long resultCount) {
		if(resultsPerPage <= 0) {
			resultsPerPage = this.getDefaultPageCount();
		}
		return (int) Math.ceil((double)resultCount / resultsPerPage);
	}

	@Override
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

}

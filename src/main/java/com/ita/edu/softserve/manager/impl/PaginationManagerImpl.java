package com.ita.edu.softserve.manager.impl;



import org.springframework.stereotype.Service;

import com.ita.edu.softserve.manager.PaginationManager;



@Service
public class PaginationManagerImpl implements PaginationManager{
	
	
	@Override
	public int getMaxPageCount(int resultsPerPage, long resultCount) {
		if(resultsPerPage <= 0) {
			resultsPerPage = 15;
		}
		return (int) Math.ceil((double)resultCount / resultsPerPage);
	}

	@Override
	public int getCurrentPagingPosition(int pageNumber, int resultsPerPage) {
		if (pageNumber <= 0) {
		    return 1;
		}

		return pageNumber * resultsPerPage;
	}

}

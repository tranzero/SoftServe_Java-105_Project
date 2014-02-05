package com.ita.edu.softserve.manager;

import org.springframework.stereotype.Service;


@Service
public interface PaginationManager {
	
	int getMaxPageCount(int resultsPerPage, long resultCount);
	
	int getCurrentPagingPosition(int pageNumber, int resultsPerPage);

	int getDefaultPageCount();

	int getDefaultPageNumber();

	int getDefaultResultPerPage();
	
}
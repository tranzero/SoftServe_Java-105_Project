package com.ita.edu.softserve.manager;



public interface PaginationManager {
	
	int getMaxPageCount(int resultsPerPage, long resultCount);
	
	int getCurrentPagingPosition(int pageNumber, int resultsPerPage);
	
}
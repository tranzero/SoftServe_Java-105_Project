package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PaginationManagerTest {
	
	@Autowired
	private PaginationManager pageMan;

	@Test
	public void testGetMaxPageCountNormalUsage() {
		
		int resultsPerPage = 10;
		long resultCount = 30000;
		
		int expectedMaxPageCountValue = 3000;
		
		int actualMaxPageCountValue = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		
		assertEquals(expectedMaxPageCountValue, actualMaxPageCountValue);
		
	}
	
	@Test
	public void testGetMaxPageCountZeroResultsPerPage() {
		
		int resultsPerPage = 0;
		long resultCount = 30000;
		
		int expectedMaxPageCountValue = 3000;
		
		int actualMaxPageCountValue = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		
		assertEquals(expectedMaxPageCountValue, actualMaxPageCountValue);
		
	}
	
	@Test
	public void testGetMaxPageCountZeroResultsCount() {
		
		int resultsPerPage = 500;
		long resultCount = 0;
		
		int expectedMaxPageCountValue = 1;
		
		int actualMaxPageCountValue = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		
		assertEquals(expectedMaxPageCountValue, actualMaxPageCountValue);
		
	}
	
	@Test
	public void testGetMaxPageCountNegativeResultsCount() {
		
		int resultsPerPage = 500;
		long resultCount = -500;
		
		int expectedMaxPageCountValue = 1;
		
		int actualMaxPageCountValue = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		
		assertEquals(expectedMaxPageCountValue, actualMaxPageCountValue);
		
	}

	
	@Test
	public void testGetMaxPageCountNegativeResultsPerPage() {
		
		int resultsPerPage = -30;
		long resultCount = 30000;
		
		int expectedMaxPageCountValue = 3000;
		
		int actualMaxPageCountValue = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		
		assertEquals(expectedMaxPageCountValue, actualMaxPageCountValue);
		
	}

	@Test
	public void testGetCurrentPagingPositionNormalUsage() {
		
		int resultsPerPage = 10;
		int pageNumber = 2;
		
		int expectedPagingPositionValue = 20;
		
		int actualPagingPostitionValue = pageMan.getCurrentPagingPosition(pageNumber, resultsPerPage);
		
		assertEquals(expectedPagingPositionValue, actualPagingPostitionValue);
	}
	
	@Test
	public void testGetCurrentPagingPositionZeroPageNumber() {
		
		int resultsPerPage = 10;
		int pageNumber = 0;
		
		int expectedPagingPositionValue = 1;
		
		int actualPagingPostitionValue = pageMan.getCurrentPagingPosition(pageNumber, resultsPerPage);
		
		assertEquals(expectedPagingPositionValue, actualPagingPostitionValue);
	}
	
	@Test
	public void testGetCurrentPagingPositionNegativePageNumber() {
		
		int resultsPerPage = 10;
		int pageNumber = -2;
		
		int expectedPagingPositionValue = 1;
		
		int actualPagingPostitionValue = pageMan.getCurrentPagingPosition(pageNumber, resultsPerPage);
		
		assertEquals(expectedPagingPositionValue, actualPagingPostitionValue);
	}

}

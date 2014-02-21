package com.ita.edu.softserve.utils;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.manager.impl.PaginationManager.SingletonHolder;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;

/**
 * 
 * Class for gathering different validate methods
 * 
 * @author dnycktc
 * 
 */

public class Validator {

	public static final String[] ORDER_BY_SORTING_TYPES = { "ASC", "DESC" };

	/**
	 * Validates paging info
	 * 
	 * @param container
	 *            Container with info to validate
	 * @param paginationManager
	 *            instance of pagination manager that holds required functions
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

	/**
	 * Validates trips criteria info
	 * 
	 * @param tripsCriteriaContainer
	 *            Container with info to validate
	 * @param locale
	 *            Locale for date validation
	 */

	public static void validateTripsCriteria(
			TripsCriteriaContainer tripsCriteriaContainer, Locale locale) {
		Set<String> fieldsSet = new TreeSet<String>(
				Arrays.asList(TripsCriteriaContainer.TRIPS_ORDER_BY_COLUMNS));
		if (!(fieldsSet.contains(tripsCriteriaContainer.getOrderByParam()))) {
			tripsCriteriaContainer
					.setOrderByParam(TripsCriteriaContainer.TRIPS_ORDER_BY_COLUMNS[0]);
		}
		if (!(tripsCriteriaContainer.getOrderByDirection().equalsIgnoreCase(
				ORDER_BY_SORTING_TYPES[0]) || tripsCriteriaContainer
				.getOrderByDirection().equalsIgnoreCase(
						ORDER_BY_SORTING_TYPES[1]))) {
			tripsCriteriaContainer.setOrderByDirection(ORDER_BY_SORTING_TYPES[0]);

		}
		if (tripsCriteriaContainer.getTransportCode() == null) {
			tripsCriteriaContainer.setTransportCode("");
		}
		if (tripsCriteriaContainer.getRemSeatClass1() == null) {
			tripsCriteriaContainer.setRemSeatClass1(-1);
		}
		if (tripsCriteriaContainer.getRemSeatClass2() == null) {
			tripsCriteriaContainer.setRemSeatClass2(-1);
		}
		if (tripsCriteriaContainer.getRemSeatClass3() == null) {
			tripsCriteriaContainer.setRemSeatClass3(-1);
		}

	}

}

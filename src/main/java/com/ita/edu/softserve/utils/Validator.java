package com.ita.edu.softserve.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.web.TripsController;

/**
 * 
 * Class for gathering different validate methods
 * 
 * @author dnycktc
 * 
 */

public class Validator {

	/**
	 * Array contains the ways of sorting using jpql "sort by" instruction: asc
	 * and desc
	 */
	public static final String[] ORDER_BY_SORTING_TYPES = { "ASC", "DESC" };
	/**
	 * String for representation of minimal available date in default date
	 * format
	 */

	private static final String MIN_DATE_STRING = "01/01/1900";

	/**
	 * String for representation of maximal available date in default date
	 * format
	 */

	private static final String MAX_DATE_STRING = "12/31/2100";
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
		container.setPageNumber((Integer) ValidatorUtil.defaultForNull(
				container.getPageNumber(),
				new Integer(paginationManager.getDefaultPageNumber())));
		if (container.getPageNumber() < 1) {
			container.setPageNumber(new Integer(paginationManager
					.getDefaultPageNumber()));
		}

		container.setResultsPerPage((Integer) ValidatorUtil.defaultForNull(
				container.getResultsPerPage(),
				new Integer(paginationManager.getDefaultResultPerPage())));
		if (container.getResultsPerPage() < 1) {
			container.setResultsPerPage(new Integer(paginationManager
					.getDefaultResultPerPage()));
		}
		container.setMaxPages(paginationManager.getMaxPageCount(
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
			tripsCriteriaContainer
					.setOrderByDirection(ORDER_BY_SORTING_TYPES[0]);

		}
		tripsCriteriaContainer.setTransportCode((String) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getTransportCode(), ""));
		tripsCriteriaContainer.setRemSeatClass1((Integer) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getRemSeatClass1(), -1));
		tripsCriteriaContainer.setRemSeatClass2((Integer) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getRemSeatClass2(), -1));
		tripsCriteriaContainer.setRemSeatClass3((Integer) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getRemSeatClass3(), -1));
		tripsCriteriaContainer.setMinDate(ValidatorUtil.getDateWithFormat(
				tripsCriteriaContainer.getMinDateString(), locale, MIN_DATE_STRING));
		tripsCriteriaContainer.setMaxDate(ValidatorUtil.getDateWithFormat(
				tripsCriteriaContainer.getMaxDateString(), locale, MAX_DATE_STRING));
	}

}

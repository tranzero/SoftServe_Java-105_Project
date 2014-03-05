package com.ita.edu.softserve.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.UserCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.StationsCriteriaContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TransportForAddTripsCriteriaContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TripsCriteriaContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.UsersCriteriaContainerImpl;

/**
 * 
 * Class for gathering different validate methods
 * 
 * @author dnycktc
 * 
 */

public class StaticValidator {

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
		Set<String> fieldsSet = new TreeSet<String>();
		Collections.addAll(fieldsSet,
				TripsCriteriaContainerImpl.TRIPS_ORDER_BY_COLUMNS);
		if ((tripsCriteriaContainer.getOrderByParam() == null)
				|| !(fieldsSet.contains(tripsCriteriaContainer
						.getOrderByParam()))) {
			tripsCriteriaContainer
					.setOrderByParam(TripsCriteriaContainerImpl.TRIPS_ORDER_BY_COLUMNS[0]);
		}
		if ((tripsCriteriaContainer.getOrderByDirection() == null)
				|| !(tripsCriteriaContainer.getOrderByDirection()
						.equalsIgnoreCase(ORDER_BY_SORTING_TYPES[0]) || tripsCriteriaContainer
						.getOrderByDirection().equalsIgnoreCase(
								ORDER_BY_SORTING_TYPES[1]))) {
			tripsCriteriaContainer
					.setOrderByDirection(ORDER_BY_SORTING_TYPES[0]);

		}
		tripsCriteriaContainer.setTransportCode((String) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getTransportCode(), ""));
		tripsCriteriaContainer.setRouteName((String) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getRouteName(), ""));
		tripsCriteriaContainer.setRemSeatClass1((Integer) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getRemSeatClass1(), -1));
		tripsCriteriaContainer.setRemSeatClass2((Integer) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getRemSeatClass2(), -1));
		tripsCriteriaContainer.setRemSeatClass3((Integer) ValidatorUtil
				.defaultForNull(tripsCriteriaContainer.getRemSeatClass3(), -1));
		tripsCriteriaContainer.setMinDateValue(ValidatorUtil.getDateWithFormat(
				tripsCriteriaContainer.getMinDate(), locale,
				MIN_DATE_STRING));
		tripsCriteriaContainer.setMaxDateValue(ValidatorUtil.getDateWithFormat(
				tripsCriteriaContainer.getMaxDate(), locale,
				MAX_DATE_STRING));
	}

	public static void validateTransportForAddTripsCriteria(
			TransportForAddTripsCriteriaContainer container) {
		Set<String> fieldsSet = new TreeSet<String>();
		Collections.addAll(fieldsSet,
				TransportForAddTripsCriteriaContainerImpl.ORDER_BY_COLUMNS);
		if ((container.getOrderByCriteria() == null)
				|| !(fieldsSet.contains(container.getOrderByCriteria()))) {
			container
					.setOrderByCriteria(TransportForAddTripsCriteriaContainerImpl.ORDER_BY_COLUMNS[0]);
		}
		if ((container.getOrderByDirection() == null)
				|| !(container.getOrderByDirection().equalsIgnoreCase(
						ORDER_BY_SORTING_TYPES[0]) || container
						.getOrderByDirection().equalsIgnoreCase(
								ORDER_BY_SORTING_TYPES[1]))) {
			container.setOrderByDirection(ORDER_BY_SORTING_TYPES[0]);
		}
		container.setTransportCode((String) ValidatorUtil
				.defaultForNull(container.getTransportCode(), ""));
		container.setRouteName((String) ValidatorUtil
				.defaultForNull(container.getRouteName(), ""));
		container.setRoutesCode((String) ValidatorUtil
				.defaultForNull(container.getRoutesCode(), ""));
		container.setPriceName((String) ValidatorUtil
				.defaultForNull(container.getPriceName(), ""));
		container.setSeatClass1((Integer) ValidatorUtil
				.defaultForNull(container.getSeatClass1(), -1));
		container.setSeatClass2((Integer) ValidatorUtil
				.defaultForNull(container.getSeatClass2(), -1));
		container.setSeatClass3((Integer) ValidatorUtil
				.defaultForNull(container.getSeatClass3(), -1));
		try {
			container.setPrice(Double.parseDouble(container.getPriceName()));
		} catch (Exception e) {
			container.setPrice(Double.MAX_VALUE);
		}
		if (container.getPrice() <= 0) {
			container.setPrice(Double.MAX_VALUE);
		}

	}

	public static void validateUserListCriteria(
			UserCriteriaContainer userCriteriaContainer, Locale locale) {

		Set<String> fieldsSet = new TreeSet<String>();
		Collections.addAll(fieldsSet,
				UsersCriteriaContainerImpl.USERS_ORDER_BY_COLUMNS);
		if ((userCriteriaContainer.getOrderByParam() == null)
				|| !(fieldsSet
						.contains(userCriteriaContainer.getOrderByParam()))) {
			userCriteriaContainer
					.setOrderByParam(UsersCriteriaContainerImpl.USERS_ORDER_BY_COLUMNS[0]);
		}
		if ((userCriteriaContainer.getOrderByDirection() == null)
				|| !(userCriteriaContainer.getOrderByDirection()
						.equalsIgnoreCase(ORDER_BY_SORTING_TYPES[0]) || userCriteriaContainer
						.getOrderByDirection().equalsIgnoreCase(
								ORDER_BY_SORTING_TYPES[1]))) {
			userCriteriaContainer
					.setOrderByDirection(ORDER_BY_SORTING_TYPES[0]);

		}

		userCriteriaContainer.setSearchString((String) ValidatorUtil
				.defaultForNull(userCriteriaContainer.getSearchString(), ""));
		userCriteriaContainer.setIsAdmin((Boolean) ValidatorUtil
				.defaultForNull(userCriteriaContainer.getIsAdmin(),
						new Boolean(false)));
		userCriteriaContainer.setIsManager((Boolean) ValidatorUtil
				.defaultForNull(userCriteriaContainer.getIsManager(),
						new Boolean(false)));
		userCriteriaContainer.setIsRegUser((Boolean) ValidatorUtil
				.defaultForNull(userCriteriaContainer.getIsRegUser(),
						new Boolean(false)));
		userCriteriaContainer.setMinDate(ValidatorUtil.getDateWithFormat(
				userCriteriaContainer.getMinDateString(), locale,
				MIN_DATE_STRING));
		userCriteriaContainer.setMaxDate(ValidatorUtil.getDateWithFormat(
				userCriteriaContainer.getMaxDateString(), locale,
				MAX_DATE_STRING));
		List<Role> arrayOfRoles = new LinkedList<Role>();
		if (userCriteriaContainer.getIsAdmin()) {
			arrayOfRoles.add(Role.ADMIN);
		}
		if (userCriteriaContainer.getIsManager()) {
			arrayOfRoles.add(Role.MANAGER);
		}
		if (userCriteriaContainer.getIsRegUser()) {
			arrayOfRoles.add(Role.REGUSER);
		}
		if (arrayOfRoles.isEmpty()) {
			arrayOfRoles.add(Role.REGUSER);
			userCriteriaContainer.setIsRegUser(true);
		}
		userCriteriaContainer.setRoleArray(arrayOfRoles);

	}

	public static void validateStationListCriteria(
			StationsCriteriaContainer stationsCriteriaContainer, Locale locale) {

		Set<String> fieldsSet = new TreeSet<String>();
		Collections.addAll(fieldsSet,
				StationsCriteriaContainerImpl.STATIONS_ORDER_BY_COLUMNS);
		if ((stationsCriteriaContainer.getOrderByParam() == null)
				|| !(fieldsSet.contains(stationsCriteriaContainer
						.getOrderByParam()))) {
			stationsCriteriaContainer
					.setOrderByParam(StationsCriteriaContainerImpl.STATIONS_ORDER_BY_COLUMNS[0]);
		}
		if ((stationsCriteriaContainer.getOrderByDirection() == null)
				|| !(stationsCriteriaContainer.getOrderByDirection()
						.equalsIgnoreCase(ORDER_BY_SORTING_TYPES[0]) || stationsCriteriaContainer
						.getOrderByDirection().equalsIgnoreCase(
								ORDER_BY_SORTING_TYPES[1]))) {
			stationsCriteriaContainer
					.setOrderByDirection(ORDER_BY_SORTING_TYPES[0]);

		}

		stationsCriteriaContainer
				.setSearchString((String) ValidatorUtil.defaultForNull(
						stationsCriteriaContainer.getSearchString(), ""));

	}

}

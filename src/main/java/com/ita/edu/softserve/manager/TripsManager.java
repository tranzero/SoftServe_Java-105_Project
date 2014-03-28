package com.ita.edu.softserve.manager;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsInfoValidationContainer;
import com.ita.edu.softserve.validationcontainers.impl.EditTripsInfoValidationContainer;

public interface TripsManager extends BaseManager {

	/**
	 * Validates parameters, used in find with criteria queries
	 * 
	 * @param tripsCriteriaContainer
	 *            container of parameters to validate
	 * @param locale
	 *            Locale(used to validate date)
	 */
	void validateTripsCriteria(TripsCriteriaContainer tripsCriteriaContainer,
			Locale locale);

	/**
	 * Returns complete list of trips
	 * 
	 * @return Complete list of trips
	 */
	List<Trips> getAllTrips();

	/**
	 * 
	 * Returns size of list of trips according given limits and criteria
	 * 
	 * @param transportCode
	 *            transport code for matching
	 * @param routeName
	 *            route name for matching
	 * @param remSeatClass1
	 *            count of remaining seats of class 1 for matching
	 * @param remSeatClass2
	 *            count of remaining seats of class 2 for matching
	 * @param remSeatClass3
	 *            count of remaining seats of class 3 for matching
	 * @param minDate
	 *            minimum date for date range
	 * @param maxDate
	 *            maximum date for date range
	 * @return Size of list of trips according given limits and criteria
	 */

	long getTripsListCriteriaCount(String transportCode, String routeName,
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate);

	/**
	 * 
	 * Returns list of trips according given limits and criteria
	 * 
	 * @param firstElement
	 *            Starting element for result list
	 * @param count
	 *            capacity of result list
	 * @param transportCode
	 *            transport code for matching
	 * @param routeName
	 *            route name for matching
	 * @param remSeatClass1
	 *            count of remaining seats of class 1 for matching
	 * @param remSeatClass2
	 *            count of remaining seats of class 2 for matching
	 * @param remSeatClass3
	 *            count of remaining seats of class 3 for matching
	 * @param minDate
	 *            minimum date for date range
	 * @param maxDate
	 *            maximum date for date range
	 * @param orderByParam
	 *            the column, using for sorting
	 * @param orderByDirection
	 *            sorting direction
	 * @return List of trips according given limits and criteria
	 */

	List<Trips> getTripsForCriteria(int firstElement, int count,
			String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection);

	/**
	 * 
	 * Returns list of trips according given limits and criteria (using page
	 * number instead of starting element)
	 * 
	 * @param pageNumber
	 *            Starting page for result list
	 * @param count
	 *            capacity of result list
	 * @param transportCode
	 *            transport code for matching
	 * @param routeName
	 *            route name for matching
	 * @param remSeatClass1
	 *            count of remaining seats of class 1 for matching
	 * @param remSeatClass2
	 *            count of remaining seats of class 2 for matching
	 * @param remSeatClass3
	 *            count of remaining seats of class 3 for matching
	 * @param minDate
	 *            minimum date for date range
	 * @param maxDate
	 *            maximum date for date range
	 * @param orderByParam
	 *            the column, using for sorting
	 * @param orderByDirection
	 *            sorting direction
	 * @return List of trips according given limits and criteria
	 */
	List<Trips> getTripsForCriteriaWithPage(int pageNumber, int count,
			String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection);

	/**
	 * 
	 * Returns list of trips size according given limits and criteria using
	 * containers
	 * 
	 * @param tripsCriteriaContainer
	 *            container with given limits and criteria
	 * @return List of trips size according given limits and criteria
	 */
	long getTripsListCriteriaCountUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer);

	/**
	 * 
	 * Returns list of trips according given limits and criteria using
	 * containers
	 * 
	 * @param tripsCriteriaContainer
	 *            container with given limits and criteria
	 * @param container
	 *            container with information for paging
	 * @return List of trips according given limits and criteria
	 */
	List<Trips> getTripsForCriteriaUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer,
			PageInfoContainer container);

	/**
	 * Returns trip that matches given id
	 * 
	 * @param id
	 *            id of trips to search
	 * @return trip that matches given id
	 */
	Trips findByTripId(Integer id);

	/**
	 * Reduces seats quantity of given class for given trip
	 * 
	 * @param tripId
	 *            trip id to reduce seats
	 * @param seatType
	 *            seat class to reduce count
	 */
	void reduceFreeSeatsQuantity(Integer tripId, Integer seatType);

	/**
	 * Increases seats quantity of given class for given trip
	 * 
	 * @param tripId
	 *            trip id to increase seats
	 * @param seatType
	 *            seat class to increase count
	 */
	void increaseFreeSeatsQuantity(Integer tripId, Integer seatType);

	/**
	 * Updates given trip id DB
	 * 
	 * @param trip
	 *            to change
	 */
	void updateTrip(Trips trip);

	/**
	 * Deletes trip from DB
	 * 
	 * @param id
	 *            trip id to delete
	 */
	void removeTrip(Integer id);

	/**
	 * Returns page number for given element using given criteria
	 * 
	 * @param tripsCriteriaContainer
	 *            container with given limits and criteria
	 * @param elementIndex
	 *            id of trip to check
	 * @param pageSize
	 *            size of page
	 * @return page number for given element using given criteria
	 */
	long getTripsListCriteriaPageUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer,
			Integer elementIndex, Integer pageSize);

	/**
	 * Returns trip with given id using custom query
	 * 
	 * @param id
	 *            id of trip to find
	 * @return trip with given id
	 */
	Trips getTripById(int id);

	/**
	 * Edits given trip using given data
	 * 
	 * @param tripId
	 *            id of trip to change
	 * @param container
	 *            container with info for trip changing
	 * @return success operation identifier
	 */
	boolean editTrip(Integer tripId, EditTripsInfoValidationContainer container);

	/**
	 * Adds trips range using given data
	 * 
	 * @param container
	 *            container with info of data for trips range to aadd
	 * @return success operation identifier
	 */
	boolean addTripsWithContainer(AddTripsInfoValidationContainer container);

}

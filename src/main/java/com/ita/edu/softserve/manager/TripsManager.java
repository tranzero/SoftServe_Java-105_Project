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
	 * @return List of trips size according given limits and criteria
	 */

	long getTripsListCriteriaCount(String transportCode, String routeName,
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate);

	/**
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
	 * @param tripsCriteriaContainer
	 *            container with given limits and criteria
	 * @return List of trips size according given limits and criteria
	 */
	long getTripsListCriteriaCountUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer);

	/**
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

	Trips findByTripId(Integer id);
	
	 void reduceFreeSeatsQuantity(Integer tripId, Integer seatType);
	
	 void increaseFreeSeatsQuantity(Integer tripId, Integer seatType);
	
	void updateTrip(Trips trip);

	void removeTrip(Integer id);

	long getTripsListCriteriaPageUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer,
			Integer elementIndex, Integer pageSize);

	Trips getTripById(int id);

	boolean editTrip(Integer tripId,
			EditTripsInfoValidationContainer container);

	boolean addTripsWithContainer(AddTripsInfoValidationContainer container);

}

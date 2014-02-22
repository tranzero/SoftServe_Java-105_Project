package com.ita.edu.softserve.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.entity.Trips;

@Repository
public interface TripsDAO extends AbstractDAOIface<Trips> {

	/**
	 * Searches trip by transport ID
	 * 
	 * @param id
	 *            transport ID
	 * @return List of found trips
	 */
	List<Trips> findByTransportId(int id);

	/**
	 * Searches all trips with given limit of amount
	 * 
	 * @param firstElement
	 *            Starting element for result list
	 * @param count
	 *            capacity of result list
	 * @return List of trips according given limits
	 */

	List<Trips> getTripsForLimits(int firstElement, int count);

	/**
	 * Returns count of all trips
	 * 
	 * @return Count of all trips
	 */

	long getTripsListCount();

	/**
	 * 
	 * @param transportCode
	 *            transport code for matching
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
	 * @return count of matching DB records
	 */

	long getTripsListCriteriaCount(String transportCode, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate);

	/**
	 * 
	 * @param firstElement
	 *            Starting element for result list
	 * @param count
	 *            capacity of result list
	 * @param transportCode
	 *            transport code for matching
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

	List<Trips> getTripsListCriteria(int firstElement, int count,
			String transportCode, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate,
			String orderByParam, String orderByDirection);

	/**
	 * Saves a Trip to database if not exist or update existing one.
	 */
	void saveOrUpdate(Trips entity);

}

package com.ita.edu.softserve.manager;

import java.util.List;
import java.util.Locale;

import com.ita.edu.softserve.entity.Trips;

public interface TripsManager extends BaseManager {

	/**
	 * Returns complete list of trips
	 * 
	 * @return Complete list of trips
	 */
	List<Trips> getAllTrips();

	/**
	 * Returns list of trips using given limit
	 * 
	 * @param firstElement
	 *            Starting element number
	 * @param count
	 *            Maximum amount of results
	 * @return Result list
	 */

	List<Trips> getTripsForLimit(int firstElement, int count);

	/**
	 * Returns list of trips for page
	 * 
	 * @param pageNumber
	 *            number of page
	 * @param count
	 *            amount of elements for page
	 * @return Result list
	 */

	public List<Trips> getTripsForPage(int pageNumber, int count);

	/**
	 * Adds trips for transport and date interval
	 * 
	 * @param locale
	 *            locale used to format date
	 * @param minDate
	 *            low border of date interval
	 * @param maxDate
	 *            top border of date interval
	 * @param transportId
	 *            transport ID used
	 * @return confirmation of success operation
	 */

	public boolean addTripsInInterval(Locale locale, String minDate,
			String maxDate, int transportId);

	/**
	 * Returns amount of trips
	 * 
	 * @return amount of trips
	 */

	long getTripsListCount();
	
	public Trips findByTripId (Integer id);

}

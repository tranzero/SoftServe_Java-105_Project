package com.ita.edu.softserve.manager;

import java.util.List;
import java.util.Locale;

import com.ita.edu.softserve.entity.Trips;


public interface TripsManager extends BaseManager {

	/**
	 * Returns complete list of trips
	 * @return Complete list of trips
	 */
	List<Trips> getAllTrips();
	/**
	 * Returns list of trips using given limit 
	 * @param firstElement Starting element number
	 * @param count 
	 * @return
	 */
	
	List<Trips> getTripsForLimit(int firstElement, int count);
	
	/**
	 * 
	 * @param pageNumber
	 * @param count
	 * @return
	 */
	
	public List<Trips> getTripsForPage(int pageNumber, int count);
	
	/**
	 * 
	 * @param locale
	 * @param minDate
	 * @param maxDate
	 * @param transportId
	 * @return
	 */
	
	public boolean addTripsInInterval(Locale locale, String minDate, String maxDate, int transportId);
	
	/**
	 * 
	 * @return
	 */
	
	long getTripsListCount();

}

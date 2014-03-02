package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * @author iryna
 */
public interface StationsDAO extends AbstractDAOIface<Stations> {

	/**
	 * Find Stations by stationName
	 */
	public List<Stations> findByStations(String stationName);

	/**
	 * Find Stations by name of the station
	 */
	public Stations findByName(String stationName);

	public List<Stations> findByLineName(String lineName);

	/**
	 * Saves a Station to database if not exist or update existing one.
	 */
	public void saveOrUpdate(Stations entity);

	/**
	 * Method for pagging.
	 */
	long getStationsListCount();

	/**
	 * 
	 * @param firstElement
	 * @param count
	 * @return
	 */
	List<Stations> getStationsForLimits(int firstElement, int count);

	/**
	 * 
	 * @param searchString
	 * @return
	 */
	long getStationsListCriteriaCount(String searchString);

	/**
	 * 
	 * @param firstElement
	 *            - Starting element for result list
	 * @param count
	 *            - capacity of result list
	 * @param searchString
	 *            - searchString for matching
	 * @param orderByParam
	 *            - the column, using for sorting
	 * @param orderByDirection
	 *            - sorting direction
	 * @return List of Stations according given limits and criteria
	 */
	List<Stations> getStationsForOnePageWithCriteria(int firstElement,
			int count, String searchString, String orderByParam,
			String orderByDirection);

}

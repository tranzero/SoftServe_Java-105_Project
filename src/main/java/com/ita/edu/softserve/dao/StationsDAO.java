package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;

/**
 * @author admin
 */
public interface StationsDAO extends AbstractDAOIface<Stations> {


	/**
	 * Finds <code>Stations</code> by name of the station.
	 * 
	 * @param stationName
	 *            - the name to find object by
	 * @return the <code>Stations</code> object
	 */
	public Stations findByName(String stationName);

	/**
	 * Finds <code>List of Stations</code> by line name.
	 * 
	 * @param lineName
	 *            - the name of line.
	 * @return the List of <code>Stations</code> objects.
	 */
	public List<Stations> findByLineName(String lineName);

	/**
	 * If <code>Station</code> Id is <code>null</code> saves the
	 * <code>Station</code> to the database otherwise updates existing one.
	 * 
	 * @param entity
	 *            the <code>Stations</code> object.
	 */
	public void saveOrUpdate(Stations entity);

	/**
	 * Finds the number of <code>Stations</code> and return it quantity.
	 * 
	 * @return the count of <code>Stations</code> objects.
	 */
	public long getStationsListCount();

	/**
	 * Searches all stations with given limit of amount
	 * 
	 * @param firstElement
	 *            Starting element for result list
	 * @param count
	 *            capacity of result list
	 * @return List of Stations according given limits.
	 */
	public List<Stations> getStationsForLimits(int firstElement, int count);

	/**
	 * @param searchString
	 *            - string for matching.
	 * 
	 * @return number of stations elements that corresponding to searchString.
	 */
	public long getStationsListCriteriaCount(String searchString);

	/**
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
	 * @return List of Stations according given limits and criteria.
	 */
	public List<Stations> getStationsForOnePageWithCriteria(int firstElement,
			int count, String searchString, String orderByParam,
			String orderByDirection);

}

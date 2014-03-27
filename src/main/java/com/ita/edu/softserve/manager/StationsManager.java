package com.ita.edu.softserve.manager;

import java.util.List;
import java.util.Locale;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer;

/**
 * Service interface for stations.
 * 
 * @author admin
 */
public interface StationsManager extends BaseManager {

	/**
	 * Gets list of all Stations
	 * 
	 * @return complete list of Stations
	 */
	List<Stations> findAllStations();

	/**
	 * Finds the <code>Stations</code> by Id.
	 * 
	 * @param id
	 *            - the Id of station to find <code>Stations</code> object.
	 * @return the <code>Stations</code> object.
	 */
	Stations findStationsById(Integer id);

	/**
	 * Saves <code>Stations</code> to database.
	 * 
	 * @param station
	 *            - Stations object to save.
	 */
	void createStation(Stations station);

	/**
	 * Removes <code>Stations</code> from database.
	 * 
	 * @param stationId
	 *            - the Id of Station to delete.
	 */
	void removeStations(Integer stationId);

	/**
	 * Updates exact <code>Stations</code> by Id.
	 * 
	 * @param stationId
	 *            - the Id of station to delete.
	 * 
	 * @param stationCode
	 *            - the Code of station.
	 * 
	 * @param stationName
	 *            - the Name of station.
	 * 
	 * @return true if Station was updated, otherwise return false.
	 */
	boolean editStation(Integer stationId, String stationCode,
			String stationName);

	/**
	 * Finds station by name.
	 * 
	 * @param stationName
	 *            - the station name to find.
	 * @return the Stations fond by stationName.
	 */
	Stations findByStationName(String stationName);

	/**
	 * Finds list of stations on certain line by line name.
	 * 
	 * @param lineName
	 *            - the name of line to find.
	 * 
	 * @return the Stations fond by lineName.
	 */
	List<Stations> getStationsOnCertainLine(String lineName);

	/**
	 * Finds stations which not exist on certain line by line Id.
	 * 
	 * @param lineId
	 *            - the Id of line to find.
	 * 
	 * @return the Stations fond by lineId.
	 */
	List<Stations> getStationsNotOnCertainLine(Integer lineId);

	/**
	 * Finds list of stations on certain line by line Id.
	 * 
	 * @param lineId
	 *            - the Id of line to find.
	 * 
	 * @return the Stations fond by lineId.
	 */
	List<Stations> getStationsOnCertainLine(Integer lineId);

	/**
	 * Returns list of Stations using given limit
	 * 
	 * @param firstElement
	 *            - Starting element number
	 * @param count
	 *            - Maximum amount of results
	 * @return Result list
	 */
	List<Stations> getStationsForLimit(int firstElement, int count);

	/**
	 * Returns list of Stations for page
	 * 
	 * @param pageNumber
	 *            - number of page
	 * @param count
	 *            - amount of elements for page
	 * @return Result list
	 */
	List<Stations> getStationsForPage(int pageNumber, int count);

	/**
	 * Returns amount of Stations
	 * 
	 * @return amount of Stations
	 */
	long getStationsListCount();

	/**
	 * Saves the <code>Stations</code> object to database if not exist or
	 * updates existing one. <br/>
	 * <br/>
	 * Check if <code>stationId</code> is <code>null</code> than it creates new
	 * station object otherwise it finds existing one in database and updates
	 * it.
	 * 
	 * @param station
	 *            - the Stations object to save or update.
	 */
	void saveOrUpdateStation(Stations station);

	/**
	 * Validates parameters, used in find with criteria queries
	 * 
	 * @param stationsCriteriaContainer
	 *            - container of parameters to validate
	 * @param locale
	 *            - Locale(used to validate date)
	 */
	void validateStationListCriteria(
			StationsCriteriaContainer stationsCriteriaContainer, Locale locale);

	/**
	 * @param searchString
	 *            - string for matching
	 * @return count of Stations according given limits and criteria
	 */
	long getStationsListCountWithCriteria(String searchString);

	/**
	 * @param stationsCriteriaContainer
	 *            - container with given limits and criteria
	 * @return count of Stations according given limits and criteria
	 */
	long getStationsListCountUsingContainer(
			StationsCriteriaContainer stationsCriteriaContainer);

	/**
	 * @param stationsCriteriaContainer
	 *            - container with given limits and criteria
	 * @param container
	 *            - container with information for paging
	 * @return List of stations according given limits and criteria
	 */
	List<Stations> getStationsForLimitUsingContainers(
			StationsCriteriaContainer stationsCriteriaContainer,
			PageInfoContainer container);

	/**
	 * @param firstElement
	 *            - Starting element for result list
	 * @param count
	 *            - capacity of result list
	 * @param searchString
	 *            - string for matching
	 * @param orderByParam
	 *            - the column, using for sorting
	 * @param orderByDirection
	 *            - sorting direction
	 * @return List of Stations according given limits and criteria
	 */
	List<Stations> getStationsForLimitWithCriteria(int firstElement,
			long count, String searchString, String orderByParam,
			String orderByDirection);

	/**
	 * @param pageNumber
	 *            - Starting page for result list
	 * @param count
	 *            - capacity of result list
	 * @param searchString
	 *            - string for matching
	 * @param orderByParam
	 *            - the column, using for sorting
	 * @param orderByDirection
	 *            - sorting direction
	 * @return List of Stations according given limits and criteria
	 */
	List<Stations> getStationsForPageWithCriteria(int pageNumber, long count,
			String searchString, String orderByParam, String orderByDirection);

}
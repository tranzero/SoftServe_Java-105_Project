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
	 * Gets list of all Stations.
	 */
	List<Stations> findAllStations();

	/**
	 * Gets Station by ID.
	 */
	Stations findStationsById(Integer id);

	/**
	 * Saves station by parameters.
	 */
	void createStation(String stationCode, String stationName);

	/**
	 * Removes station.
	 */
	void removeStations(Integer stationId);

	/**
	 * Updates exact station by Id.
	 */
	void editStation(Integer stationId, String stationCode, String stationName);
	
	/**
	 * Finds station by Name.
	 */
	public Stations findByStationName(String stationName); 
	
	/**
	 * Finds stations on certain line by line name.
	 */
	public List<Stations> getStationsOnCertainLine(String lineName);

	/**
	 * Finds stations which not exist on certain line by line name.
	 */
	
	public List<Stations> getStationsNotOnCertainLine(Integer lineId);
	
	public List<Stations> getStationsOnCertainLine(Integer lineId);
	
	List<Stations> getStationsNotOnCertainLine(String lineName);

	List<Stations> getStationsForLimit(int firstElement, int count);

	List<Stations> getStationsForPage(int pageNumber, int count);

	long getStationsListCount();

	/**
	 * Save station if not exist or update if exist.
	 */
	public void saveOrUpdateStation(Stations station);

	void validateStationListCriteria(
			StationsCriteriaContainer stationsCriteriaContainer, Locale locale);

	long getStationsListCountWithCriteria(String searchString);

	long getStationsListCountUsingContainer(
			StationsCriteriaContainer stationsCriteriaContainer);

	List<Stations> getStationsForLimitUsingContainers(
			StationsCriteriaContainer stationsCriteriaContainer,
			PageInfoContainer container);

	List<Stations> getStationsForLimitWithCriteria(int firstElement,
			long count, String searchString, String orderByParam,
			String orderByDirection);

	List<Stations> getStationsForPageWithCriteria(int pageNumber, long count,
			String searchString, String orderByParam, String orderByDirection);
	

}
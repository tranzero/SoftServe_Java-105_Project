package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;

/**
 * 
 * @author iryna
 * 
 */

public interface LinesDAO extends AbstractDAOIface<Lines> {

	/**
	 * Find Lines by lineName
	 * 
	 * @param lineName
	 * @return Lines
	 */
	public Lines findByName(String lineName);
	
	/**
	 * Find Lines by station name
	 * 
	 * @param stationName
	 * @return List of Lines, which include certain station
	 */
	public List<Lines> getLinesByStationName(String stationName);
	
	
/**
 * Method for sorting results
 * @param stationName
 * @param firstElement
 * @param count
 * @param sortOrder
 * @return List of Lines for 1 page
 */
	public List<Lines> getLinesByStNameForLimits(String stationName, int firstElement, int count, int sortOrder);
	
	/**
	 * Method for paging
	 * 
	 * @param stationName
	 * @return quantity of result
	 */
	public long getLinesByStationNameCount(String stationName);
	
	
	
	/**
	 * Return <code>List</code> of Lines, that contain two stations in
	 * certain order
	 * 
	 * @param stationName1
	 *            - First station name
	 * @param stationName2
	 *            - Second station name
	 * @return List of Lines
	 */
	public List<Lines> findByTwoStations(String stationName1,
			String stationName2);
	
	public List<Lines> getLinesByTwoStForLimits(String stationName1,
			String stationName2, int firstElement, int count, int sortOrder);
	
	public long getLinesByTwoStListCount(String stationName1,
			String stationName2);
	
	/**
	 * @author MatyashPetro
	 * @return size of list with all lines
	 */
	public long getLinesListCount();
	
	/**
	 * @author MatyashPetro
	 * @return List of lines witch will be printed on one page
	 * @param from from what element will be start next list
	 * @param count how match elements will be in the list 
	 */
	public List<Lines> getLinesForOnePage(int from, int count);
}

/**
 * 
 */
package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;

/**
 * 
 * @author yuraloga
 * @author MPS
 * @author pankivanastasiia
 * 
 */
public interface LinesManager extends BaseManager {

	/**
	 * @author MatyashPetro
	 * @return list with all lines
	 */
	public List<Lines> getFullLines();

	/**
	 * @author MatyashPetro
	 * @return Line with selected id
	 */
	public Lines findByLineId(Integer lineId);

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be created
	 */
	public Boolean createLine(String lineName);

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be deleted
	 */
	public boolean deleteLine(Integer lineId);

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be updated
	 * @param newLineName
	 *            new name of the line witch was updated
	 */
	void updateLine(Integer lineId, String newLineName);

	/**
	 * @author MatyashPetro
	 */
	long getAllLinesCount();

	/**
	 * @author MatyashPetro
	 */
	List<Lines> getAllLinesForLimit(int firstElement, int count, int sortOrder);

	/**
	 * @author MatyashPetro
	 */
	List<Lines> getAllLinesForPage(int pageNumber, int count, int sortOrder);

	/**
	 * 
	 * @param lineName
	 *            - line name to find by
	 * 
	 * @return <code>List</code> of <code>Lines</code>
	 * 
	 */
	public Lines findByLineName(String lineName);

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * 
	 * @return <code>List</code> of <code>Lines</code>, that contains inputed
	 *         station
	 * 
	 */
	public List<Lines> getLinesByStationName(String stationName);

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * 
	 * @return number of <code>Lines</code>, that contains station
	 *         <code>stationName</code>
	 */
	public long getLinesByStationCount(String stationName);

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * @param pageNumber
	 *            - page number to return data for
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains station
	 *         <code>stationName</code>
	 */
	public List<Lines> getLinesByStNameForPage(String stationName,
			int pageNumber, int count, int sortOrder);

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * @param firstElement
	 *            - to start from
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains station
	 *         <code>stationName</code>
	 */
	public List<Lines> getLinesByStNameForLimit(String stationName,
			int firstElement, int count, int sortOrder);

	/**
	 * Returns list of lines, that contains two stations in certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @return <code>List</code> of <code>Lines</code>, that contains two
	 *         stations in certain order
	 */
	public List<Lines> getLinesByTwoStations(String stationName1,
			String stationName2);

	/**
	 * Returns number of lines, that contains two stations in certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @return number of <code>Lines</code>, that contains two stations in
	 *         certain order
	 */
	public long getLinesByTwoStListCount(String stationName1,
			String stationName2);

	/**
	 * Returns list of lines(for one page), that contains two stations in
	 * certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @param pageNumber
	 *            - page number to return data for
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains two
	 *         stations in certain order
	 */
	public List<Lines> getLinesByTwoStForPage(String stationName1,
			String stationName2, int pageNumber, int count, int sortOrder);

	/**
	 * Returns list of lines(limited by parameters), that contains two stations
	 * in certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @param firstElement
	 *            - to start from
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains two
	 *         stations in certain order
	 */
	public List<Lines> getLinesByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count, int sortOrder);

}

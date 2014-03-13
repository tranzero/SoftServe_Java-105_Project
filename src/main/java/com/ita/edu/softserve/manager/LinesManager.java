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
	public void createLine(String lineName);

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be deleted
	 */
	public void deleteLine(Integer lineId);

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

	public Lines findByLineName(String lineName);

	public long getLinesByStationCount(String stationName);
	
	public List<Lines> getLinesByStNameForPage(String stationName, int pageNumber, int count, int sortOrder);
	
	public List<Lines> getLinesByStNameForLimit(String stationName, int firstElement, int count, int sortOrder);

	public List<Lines> getLinesByTwoStations(String stationName1,
			String stationName2);
	
	public long getLinesByTwoStListCount(String stationName1,
			String stationName2);

	public List<Lines> getLinesByTwoStForPage(String stationName1,
			String stationName2, int pageNumber, int count, int sortOrder);

	public List<Lines> getLinesByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count, int sortOrder);

	

	public List<Lines> getLinesByStationName(String stationName);	
}

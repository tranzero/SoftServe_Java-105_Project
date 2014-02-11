package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.StationsOnLine;

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
	public List<Lines> getLinesByStationName(String stationName);
	public List<Lines> getLinesForLimits(int firstElement, int count);
	public long getLinesListCount();
	public int getLinesByStationNameCount(String stationName);
	public List<Lines> getLinesByStationForOnePage(int from,
			int count, String stationName);

}

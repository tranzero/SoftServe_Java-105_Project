package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
 * 
 */
public interface LinesDAO {

	/**
	 * Find Lines by lineName
	 * 
	 * @param lineName
	 * @return
	 */
	public Lines findByName(String lineName);

	public List<Lines> getLinesTwoStationsCertainOrder(
			Stations station1, Stations station2);
	
	public List<Lines> getLinesByStation(String stationName);
	
	public List<Lines> getFullLines();

}

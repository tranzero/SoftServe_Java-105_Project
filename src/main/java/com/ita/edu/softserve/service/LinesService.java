/**
 * 
 */
package com.ita.edu.softserve.service;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author yuraloga
 * @author MPS
 * 
 */
public interface LinesService {
	
	public List<Lines> getFullLines();
	
	public List<Lines> getLinesByStation(String stationName);
	
	public List<Lines> getLinesTwoStationsCertainOrder(String stationName1,
													   String stationName2);

}

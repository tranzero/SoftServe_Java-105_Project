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
	
	public List<Lines> getFullLines();
	
	public Lines findByLineName(String lineName); 
	
	public List<Lines> getLinesByStation(String stationName);
	
	public int getLinesByStationCount(String stationName);
	public List<Lines> getLinesByStationForPage(int from,int count, String stationName);
	
	public List<Lines> getLinesByTwoStations(String stationName1,
			String stationName2);
	
	public void createLine(String lineName);
	
	public void deleteLine(String lineName);

	void updateLine(String lineName, String newLineName);
}

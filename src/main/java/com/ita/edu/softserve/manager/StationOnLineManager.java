/**
 * 
 */
package com.ita.edu.softserve.manager;

import java.util.List;

/**
 * @author MatyashPetro
 *
 */
public interface StationOnLineManager extends BaseManager {
	
	public void removeStation(Integer stationId, Integer lineId);	
	
	public void addStationsToLine(Integer lineId, List<String> stationsName);
	
}

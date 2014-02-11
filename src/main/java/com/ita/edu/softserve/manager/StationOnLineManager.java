/**
 * 
 */
package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * @author MatyashPetro
 *
 */
public interface StationOnLineManager extends BaseManager {
	
	public void removeStation(Integer stationId, Integer lineId);	
	
	public void addStationsToLine(Integer lineId, List<String> stationsName);

	List<StationsOnLine> findStationsOnLine(Integer lineId);
	
	public void updateStationOnLine(Integer lineId, List<Stations> stations);
	
}

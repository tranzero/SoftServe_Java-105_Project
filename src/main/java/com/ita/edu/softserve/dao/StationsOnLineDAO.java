package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * 
 * @author iryna
 * 
 */
public interface StationsOnLineDAO extends AbstractDAOIface<StationsOnLine>{

	/**
	 * Find by Station Id
	 * 
	 * @param id
	 * @return List<StationsOnLine>
	 */
	public List<StationsOnLine> findByStationId(int id);
	
	public List<StationsOnLine> findByLineId(int id);
	

	/**
	 * Return <code>List</code> of StationOnLines, that contain two stations in
	 * certain order
	 * 
	 * @param stationName1
	 *            - First station name
	 * @param stationName2
	 *            - Second station name
	 * @return List of stations
	 */
	public List<StationsOnLine> findByTwoStations(String stationName1,
			String stationName2);
	
	public StationsOnLine findByStationIdAndLineId(Integer stationId, Integer lineId);
}

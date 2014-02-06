/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.StationOnLineManager;

/**
 * @author MatyashPetro
 * 
 */
@Service("StationOnLineService")
public class StationOnLineManagerImpl implements StationOnLineManager {

	private static final Logger LOGGER = Logger.getLogger(StationsOnLine.class);

	@Autowired
	private LinesDAO lineDao;

	@Autowired
	private StationsOnLineDAO stlDao;

	@Autowired
	private StationsDAO stationDao;

	@Transactional
	@Override
	public void removeStation(Integer stationId, Integer lineId) {
		StationsOnLine sol = stlDao.findByStationIdAndLineId(stationId, lineId);
		stlDao.remove(sol);
	}
	
	public static StationOnLineManager getInstance() {
		return ManagerFactory.getManager(StationOnLineManager.class);
	}

	@Transactional
	@Override
	public void addStationsToLine(Integer lineId, List<String> stationsName) {
		int i = 1;
		for(String station: stationsName){
			StationsOnLine sol = new StationsOnLine();
			sol.setLineId(lineDao.findById(lineId));
			sol.setStationId(stationDao.findByName(station));
			sol.setStationOrderNum(i++);
			stlDao.save(sol);
		}
		
	}
}

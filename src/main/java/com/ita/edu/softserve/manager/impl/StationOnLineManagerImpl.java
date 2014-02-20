/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.exception.StationOnLineManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.StationOnLineManager;

/**
 * @author MatyashPetro
 * 
 */
@Service("StationOnLineService")
public class StationOnLineManagerImpl implements StationOnLineManager {

	private static final Logger LOGGER = Logger
			.getLogger(LinesManagerImpl.class);
	
	private String entityName = StationsOnLine.class.getCanonicalName()
			.replace("com.ita.edu.softserve.entity.", "").concat(" with id=");
	
	private String addMsg = " was added to DB by ";
	private String removeMsg = " was removed from DB by ";
	private String changeMsg = " was changed in DB by ";
	private final String createStationsOnLineMsg = "Could not create list of stations on line";
	private final String removeStationsOnLineMsg = "Could not remove list of stations on line";
	private final String updateStationsOnLineMsg = "Could not update list of stations on line";

	@Autowired
	private LinesDAO lineDao;

	@Autowired
	private StationsOnLineDAO stlDao;

	@Autowired
	private StationsDAO stationDao;

	/**
	 * @author MatyashPetro
	 * @param stationId
	 *            station ID which must be deleted
	 * @param lineId
	 *            line ID which contains station for deleting
	 */
	@Transactional
	@Override
	public void removeStation(Integer stationId, Integer lineId) {
		try{	
			StationsOnLine stationsOnLine = stlDao.findByStationIdAndLineId(stationId, lineId);
		stlDao.remove(stationsOnLine);
		LOGGER.info(entityName + stationsOnLine.getStationOnLineId() + removeMsg);
		} catch (RuntimeException e){
			LOGGER.error(e);
			throw new StationOnLineManagerException(removeStationsOnLineMsg, e);
		}
	}

	/**
	 * @author MatyashPetro
	 * @param stationsId
	 *            List of stations id which must be included to new line
	 * @param lineId
	 *            ID of new line
	 */
	@Transactional
	@Override
	public void addStationsToLine(Integer lineId, List<Integer> stationsId) {
		List<StationsOnLine> stationOnLineList = new ArrayList<StationsOnLine>();
		try{
			for(Integer station : stationsId){
				StationsOnLine stationOnLine = new StationsOnLine();
				stationOnLine.setLineId(lineDao.findById(lineId));
				stationOnLine.setStationId(stationDao.findById(station));
				stationOnLineList.add(stationOnLine);
				stlDao.save(stationOnLine);
			}
			LOGGER.info(entityName + stationOnLineList + addMsg);
		}catch(RuntimeException e){
			LOGGER.error(e);
			throw new StationOnLineManagerException(createStationsOnLineMsg, e);
		}
	}

	/**
	 * @author MatyashPetro
	 * @param stationsId
	 *            List of stations id which must be included to existing line
	 * @param lineId
	 *            ID of new line
	 */
	@Override
	@Transactional
	public void updateStationOnLine(Integer lineId, List<Integer> stationsId) {
		List<StationsOnLine> stationsOnLineList = new ArrayList<StationsOnLine>();
		try{
			for(Integer station : stationsId){
				StationsOnLine stationOnLine = new StationsOnLine();
				stationOnLine.setLineId(lineDao.findById(lineId));
				stationOnLine.setStationId(stationDao.findById(station));
				stationsOnLineList.add(stationOnLine);
				stlDao.update(stationOnLine);
			}
			LOGGER.info(entityName + stationsOnLineList + changeMsg);
		}catch(RuntimeException e){
			LOGGER.error(e);
			throw new StationOnLineManagerException(updateStationsOnLineMsg, e);
		}
	}

	/**
	 * @author Roman
	 */
	@Transactional
	@Override
	public List<StationsOnLine> findStationsOnLine(Integer lineId) {

		return stlDao.findByLineId(lineId);
	}
	
	public static StationOnLineManager getInstance() {
		return ManagerFactory.getManager(StationOnLineManager.class);
	}
}

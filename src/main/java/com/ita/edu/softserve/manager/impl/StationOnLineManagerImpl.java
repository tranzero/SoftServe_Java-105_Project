/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
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
	
	private String entityName = Lines.class.getCanonicalName()
			.replace("com.ita.edu.softserve.entity.", "").concat(" with id=");
	
	private String addMsg = " was added to DB by ";
	private String removeMsg = " was remove from DB by ";
	private String changeMsg = " was change in DB by ";
	private final String createStationsOnLineMsg = "Could not create StationsOnLine";
	private final String findStationsOnLineMsg = "Could not find StationsOnLine List";
	private final String removeStationsOnLineMsg = "Could not remove StationsOnLine";
	private final String findByNameStationsOnLineMsg = "Could not find StationsOnLine by name";
	private final String updateStationsOnLineMsg = "Could not update StationsOnLine";
	private final String countStationsOnLineMsg = "Could not get StationsOnLine list count";
	private final String resultPerPageStationsOnLineMsg = "Could not get StationsOnLine for one page";

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
	 * @param stationsName
	 *            List of stations name which must be included to new line
	 * @param lineId
	 *            ID of new line
	 */
	@Transactional
	@Override
	public void addStationsToLine(Integer lineId, List<String> stationsName) {
		Boolean b;
		List<StationsOnLine> solList = stlDao.findByLineId(lineId);
		for (String station : stationsName) {
			b = true;
			for (StationsOnLine st : solList) {
				if (st.getStationId().getStationId() == stationDao.findByName(
						station).getStationId()) {
					b = false;
					break;
				}
			}
			if (b) {
				StationsOnLine sol = new StationsOnLine();
				sol.setLineId(lineDao.findById(lineId));
				sol.setStationId(stationDao.findByName(station));
				stlDao.save(sol);
			}
		}
	}

	@Override
	@Transactional
	public void updateStationOnLine(Integer lineId, List<String> stations) {
		List<Stations> stationList = new ArrayList<Stations>();
		for (String str : stations) {
				stationList.add(stationDao.findByName(str));
		}
		for (Stations st : stationList) {
			StationsOnLine sol = null;
			try {
				sol = stlDao
						.findByStationIdAndLineId(st.getStationId(), lineId);
				sol.setLineId(lineDao.findById(lineId));
				sol.setStationId(st);
				stlDao.update(sol);
			} catch (NoResultException e) {
				LOGGER.error(e);
			}
			if (sol == null) {
				System.out.println("!!!!!!!!");
				sol = new StationsOnLine();
				sol.setLineId(lineDao.findById(lineId));
				sol.setStationId(st);
				stlDao.save(sol);
			}
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

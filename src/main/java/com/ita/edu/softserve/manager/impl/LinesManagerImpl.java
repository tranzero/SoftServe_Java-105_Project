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
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.ManagerFactory;

/**
 * 
 * @author yuraloga
 * @author MatyashPetro
 * 
 */
@Service("linesService")
public class LinesManagerImpl implements LinesManager {

	private static final Logger LOGGER = Logger.getLogger(Lines.class);

	@Autowired
	private LinesDAO lineDao;

	@Autowired
	private StationsOnLineDAO stlDao;

	@Autowired
	private StationsDAO stationDao;

	public LinesManagerImpl() {
	}

	public LinesManagerImpl(StationsOnLineDAOImpl stlDao, LinesDAOImpl lineDao) {
		this.lineDao = lineDao;
		this.stlDao = stlDao;
	}

	public LinesManagerImpl(StationsOnLineDAOImpl stlDao) {
		this.stlDao = stlDao;
	}

	public LinesManagerImpl(LinesDAOImpl lineDao) {
		this.lineDao = lineDao;
	}

	public LinesManagerImpl(StationsDAOImpl stationDao) {
		this.stationDao = stationDao;
	}

	public Lines findByLineName(String lineName) {
		Lines line = null;
		try {
			line = lineDao.findByName(lineName);
		} catch (NoResultException e) {
			LOGGER.error("No such line!", e);
		}
		return line;
	}

	/**
	 * Return all Lines
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getFullLines() {
		return lineDao.getAllEntities();

	}

	/**
	 * 
	 * @param stationName
	 *            name of station
	 * @return <code>List&lt;Lines&gt;</code> which includes certain station
	 */
	@Override
	public List<Lines> getLinesByStation(String stationName) {
		Stations station = stationDao.findByStations(stationName).get(0);
		List<StationsOnLine> stlList = stlDao.findByStationId(station
				.getStationId());
		List<Lines> linesList = new ArrayList<Lines>();
		for (StationsOnLine stl : stlList) {
			// linesList.add(lineDao.findById(stl.getLineId().getLineId()));
			linesList.add(stl.getLineId());
		}
		return linesList;
	}

	public List<Lines> getLinesByStationName(String stationName) {
		return lineDao.getLinesByStationName(stationName);
	}

	@Override
	public int getLinesByStationCount(String stationName) {

		return lineDao.getLinesByStationNameCount(stationName);
	}

	@Override
	public List<Lines> getLinesByStationForPage(int from, int count,
			String stationName) {
		List<Lines> linesList = lineDao.getLinesByStationForOnePage(from,
				count, stationName);
		return linesList;
	}

	/**
	 * Return Lines that includes two stations in certain order
	 * 
	 * @param station1
	 *            - first station, departure
	 * @param station2
	 *            - second station, arrival
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getLinesByTwoStations(String stationName1,
			String stationName2) {
		List<Lines> lines = lineDao.findByTwoStations(stationName1,
				stationName2);

		return lines;
	}

	public static LinesManager getInstance() {
		return ManagerFactory.getManager(LinesManager.class);
	}

	@Transactional
	@Override
	public void createLine(String lineName) {
		Lines line = null;
		try {
			line = lineDao.findByName(lineName);
		} catch (NoResultException e) {
		}
		if (line == null) {
			lineDao.save(new Lines(lineName));
		}
	}

	@Transactional
	@Override
	public void updateLine(String lineName, String newLineName) {
		Lines line = null;
		try {
			line = lineDao.findByName(lineName);
			try {
				lineDao.findByName(newLineName);
				LOGGER.error("Such line \"" + newLineName
						+ "\" is already exist");
			} catch (NoResultException e) {
				line.setLineName(newLineName);
				lineDao.update(line);
			}
		} catch (NoResultException e) {
			LOGGER.error("No such line!", e);
		}
	}

	@Transactional
	@Override
	public void deleteLine(String lineName) {
		Lines line = null;
		try {
			line = lineDao.findByName(lineName);
			lineDao.remove(line);
		} catch (NoResultException e) {
			LOGGER.error("No such line!", e);
		}
	}

	

	@Transactional(readOnly = true)
	@Override 
	public long getLinesByTwoStListCount(String stationName1,
			String stationName2) {
		return lineDao.getLinesByTwoStListCount(stationName1, stationName2);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByTwoStForPage(String stationName1,
			String stationName2, int pageNumber, int count) {
		return getLinesByTwoStForLimit(stationName1, stationName2, (pageNumber - 1) * count, count);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count) {
		return lineDao.getLinesByTwoStForLimits(stationName1, stationName2, firstElement, count);
	}
	
	@Override
	public long getLinesListCount(){
		return lineDao.getLinesListCount();
	}
	
	@Override
	public List<Lines> getLinesForPage(int from, int count){
		return lineDao.getLinesForOnePage(from - 1, count);
	}
}

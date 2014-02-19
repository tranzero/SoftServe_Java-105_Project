/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.exception.LinesManagerException;
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

	private static final Logger LOGGER = Logger
			.getLogger(LinesManagerImpl.class);
	
	private String entityName = Lines.class.getCanonicalName()
			.replace("com.ita.edu.softserve.entity.", "").concat(" with id=");
	
	private String addMsg = " was added to DB by ";
	private String removeMsg = " was remove from DB by ";
	private String changeMsg = " was change in DB by ";
	private final String createLinesMsg = "Could not create Lines";
	private final String findLinesMsg = "Could not find Lines List";
	private final String removeLinesMsg = "Could not remove Lines";
	private final String findByNameLinesMsg = "Could not find Lines by name";
	private final String updateLinesMsg = "Could not update Lines";
	private final String countLinesMsg = "Could not get Lines list count";
	private final String resultPerPageLinesMsg = "Could not get Lines for one page";

	@Autowired
	private LinesDAO lineDao;

	public LinesManagerImpl() {
	}

	public LinesManagerImpl(LinesDAOImpl lineDao) {
		this.lineDao = lineDao;
	}

	public Lines findByLineName(String lineName) {
		Lines line = null;
		try {
			line = lineDao.findByName(lineName);
			return line;
		} catch (RuntimeException e) {
			LOGGER.error(e);
			throw new LinesManagerException(findByNameLinesMsg, e);
		}
	}

	/**
	 * @author MatyashPetro
	 * @return list with all Lines
	 * 
	 */
	@Override
	public List<Lines> getFullLines() {
		try {
			return lineDao.getAllEntities();
		} catch (RuntimeException e) {
			LOGGER.error(e);
			throw new LinesManagerException(findLinesMsg, e);
		}
	}

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * @return <code>List&lt;Lines&gt;</code> which includes certain station
	 */

	@Override
	public List<Lines> getLinesByStationName(String stationName) {
		return lineDao.getLinesByStationName(stationName);
	}

	@Override
	public long getLinesByStationCount(String stationName) {

		return lineDao.getLinesByStationNameCount(stationName);
	}

	@Override
	public List<Lines> getLinesByStNameForPage(String stationName,
			int pageNumber, int count, int sortOrder) {
		return this.getLinesByStNameForLimit(stationName, (pageNumber - 1)
				* count, count,sortOrder);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByStNameForLimit(String stationName,
			int firstElement, int count, int sortOrder) {

		return lineDao.getLinesByStNameForLimits(stationName, firstElement,
				count,sortOrder);

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

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be created
	 */
	@Transactional
	@Override
	public void createLine(String lineName) {
		try {
			lineDao.findByName(lineName);
		} catch (RuntimeException e) {
			LOGGER.error(e);
			new LinesManagerException(findByNameLinesMsg);
		}

		try {
			Lines newLine = new Lines(lineName);
			lineDao.save(newLine);
			LOGGER.info(entityName + newLine.getLineId() + addMsg);
		} catch (RuntimeException e) {
			LOGGER.error(e);
			throw new LinesManagerException(createLinesMsg, e);
		}
	}

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be updated
	 * @param newLineName
	 *            new name of the line witch was updated
	 */
	@Transactional
	@Override
	public void updateLine(Integer lineId, String newLineName) {
		try {
			Lines line = lineDao.findById(lineId);
			line.setLineName(newLineName);
			lineDao.update(line);
			LOGGER.info(entityName + line.getLineId() + changeMsg);
		} catch (RuntimeException e) {
			LOGGER.error(e);
			throw new LinesManagerException(updateLinesMsg, e);
		}
	}

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be deleted
	 */
	@Transactional
	@Override
	public void deleteLine(Integer lineId) {
		try {
			lineDao.findById(lineId);
		} catch (RuntimeException e) {
			LOGGER.error(e);
			new LinesManagerException(findByNameLinesMsg);
		}

		try {
			Lines line = lineDao.findById(lineId);
			lineDao.remove(line);
			LOGGER.info(entityName + line.getLineId() + removeMsg);
		} catch (RuntimeException e) {
			LOGGER.error(e);
			throw new LinesManagerException(removeLinesMsg, e);
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
			String stationName2, int pageNumber, int count, int sortOrder) {
		return getLinesByTwoStForLimit(stationName1, stationName2,
				(pageNumber - 1) * count, count, sortOrder);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count, int sortOrder) {
		return lineDao.getLinesByTwoStForLimits(stationName1, stationName2,
				firstElement, count, sortOrder);
	}

	/**
	 * @author MatyashPetro
	 * @return size of list with all lines
	 */
	@Override
	public long getLinesListCount() {
		try{
		return lineDao.getLinesListCount();
		} catch (RuntimeException e){
			LOGGER.error(e);
			throw new LinesManagerException(countLinesMsg, e);
		}
	}

	/**
	 * @author MatyashPetro
	 * @param from
	 *            from what element will be start next list
	 * @param count
	 *            how match elements will be in the list
	 * @return List of lines
	 */
	@Override
	public List<Lines> getLinesForPage(int from, int count) {
		try{
			return lineDao.getLinesForOnePage(from - 1, count);
		} catch (RuntimeException e) {
			LOGGER.error(e);
			throw new LinesManagerException(resultPerPageLinesMsg, e);
		}
	}
}

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

	public static LinesManager getInstance() {
		return ManagerFactory.getManager(LinesManager.class);
	}

	/**
	 * 
	 * @param lineName
	 *            - line name to find by
	 * 
	 * @return <code>List</code> of <code>Lines</code>
	 * 
	 */
	@Override
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
	 * @return Line with selected id
	 */
	@Override
	public Lines findByLineId(Integer lineId) {
		try {
			Lines line = lineDao.findById(lineId);
			return line;
		} catch (RuntimeException e) {
			throw e;
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
	 * @author MatyashPetro
	 * @return size of list with all lines
	 */
	@Transactional(readOnly = true)
	@Override
	public long getAllLinesCount() {
		try {
			return lineDao.getAllLinesCount();
		} catch (RuntimeException e) {
			LOGGER.error(e);
			throw new LinesManagerException(countLinesMsg, e);
		}
	}

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be created
	 */
	@Transactional
	@Override
	public boolean createLine(String lineName) {
		Lines line = null;
		line = lineDao.findByName(lineName);
		if (line == null) {
			try {
				Lines newLine = new Lines(lineName);
				lineDao.save(newLine);
				LOGGER.info(entityName + newLine.getLineId() + addMsg);
				return true;
			} catch (RuntimeException e) {
				LOGGER.error(e);
				throw e;
			}
		}
		return false;
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
	public boolean updateLine(Integer lineId, String newLineName) {
		Lines line = null;
		line = lineDao.findById(lineId);
		if (line != null)
			try {
				line.setLineName(newLineName);
				lineDao.update(line);
				LOGGER.info(entityName + line.getLineId() + changeMsg);
				return true;
			} catch (RuntimeException e) {
				LOGGER.error(updateLinesMsg, e);
				throw e;
			}
		return false;
	}

	/**
	 * @author MatyashPetro
	 * @param lineName
	 *            name of the line witch must be deleted
	 */
	@Transactional
	@Override
	public boolean deleteLine(Integer lineId) {
		Lines line = null;
		line = lineDao.findById(lineId);
		if (line != null) {
			try {
				lineDao.remove(line);
				LOGGER.info(entityName + line.getLineId() + removeMsg);
				return true;
			} catch (RuntimeException e) {
				LOGGER.error(removeLinesMsg, e);
				throw e;
			}
		}
		return false;
	}

	/**
	 * @author MatyashPetro
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Lines> getAllLinesForPage(int pageNumber, int count,
			int sortOrder) {
		return getAllLinesForLimit((pageNumber - 1) * count, count, sortOrder);
	}

	/**
	 * @author MatyashPetro
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Lines> getAllLinesForLimit(int firstElement, int count,
			int sortOrder) {
		return lineDao.getAllLinesForLimits(firstElement, count, sortOrder);
	}

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * @return <code>List&lt;Lines&gt;</code> which includes certain station
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByStationName(String stationName) {
		return lineDao.getLinesByStationName(stationName);
	}

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * 
	 * @return number of <code>Lines</code>, that contains station
	 *         <code>stationName</code>
	 */
	@Transactional(readOnly = true)
	@Override
	public long getLinesByStationCount(String stationName) {

		try {
			return lineDao.getLinesByStationNameCount(stationName);
		} catch (RuntimeException e) {
			LOGGER.error(countLinesMsg, e);
			throw new LinesManagerException(countLinesMsg, e);
		}
	}

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * @param pageNumber
	 *            - page number to return data for
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains station
	 *         <code>stationName</code>
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByStNameForPage(String stationName,
			int pageNumber, int count, int sortOrder) {
		return this.getLinesByStNameForLimit(stationName, (pageNumber - 1)
				* count, count, sortOrder);
	}

	/**
	 * 
	 * @param stationName
	 *            - name of station
	 * @param firstElement
	 *            - to start from
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains station
	 *         <code>stationName</code>
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByStNameForLimit(String stationName,
			int firstElement, int count, int sortOrder) {

		return lineDao.getLinesByStNameForLimits(stationName, firstElement,
				count, sortOrder);

	}

	/**
	 * Returns list of lines, that contains two stations in certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @return <code>List</code> of <code>Lines</code>, that contains two
	 *         stations in certain order
	 */

	@Override
	public List<Lines> getLinesByTwoStations(String stationName1,
			String stationName2) {
		List<Lines> lines = lineDao.findByTwoStations(stationName1,
				stationName2);

		return lines;
	}

	/**
	 * Returns number of lines, that contains two stations in certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @return number of <code>Lines</code>, that contains two stations in
	 *         certain order
	 */
	@Transactional(readOnly = true)
	@Override
	public long getLinesByTwoStListCount(String stationName1,
			String stationName2) {
		return lineDao.getLinesByTwoStListCount(stationName1, stationName2);
	}

	/**
	 * Returns list of lines(for one page), that contains two stations in
	 * certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @param pageNumber
	 *            - page number to return data for
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains two
	 *         stations in certain order
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByTwoStForPage(String stationName1,
			String stationName2, int pageNumber, int count, int sortOrder) {
		return getLinesByTwoStForLimit(stationName1, stationName2,
				(pageNumber - 1) * count, count, sortOrder);
	}

	/**
	 * Returns list of lines(limited by parameters), that contains two stations
	 * in certain order
	 * 
	 * @param stationName1
	 *            - first station name
	 * @param stationName2
	 *            - second station name
	 * @param firstElement
	 *            - to start from
	 * @param count
	 *            - number of elements
	 * @param sortOrder
	 *            - sort order, 0 - asc, 1 - desc
	 * @return <code>List</code> of <code>Lines</code>, that contains two
	 *         stations in certain order
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Lines> getLinesByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count, int sortOrder) {
		return lineDao.getLinesByTwoStForLimits(stationName1, stationName2,
				firstElement, count, sortOrder);
	}
}

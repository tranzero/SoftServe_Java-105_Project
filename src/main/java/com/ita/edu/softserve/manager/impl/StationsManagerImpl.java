package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer;

/**
 * This is station service class.
 * 
 * @author admin
 * 
 */
@Service("stationsService")
public class StationsManagerImpl implements StationsManager {

	private final String FIND_STATIONS_MSG = "Could not find Stations List";
	private final String FIND_STATION_BY_ID_MSG = "Could not find Station by this Id";
	private final String FIND_STATION_BY_NAME_MSG = "Could not find Station by name";
	private final String FIND_STATIONS_ONCERTAIN_LINE_MSG = "Could not get Stations On Certain Line";
	private final String CREATE_STATION_MSG = "Could not create Station";
	private final String REMOVE_STATION_MSG = "Could not remove Station";
	private final String UPDATE_STATION_MSG = "Could not update Station";
	private final String SAVE_OR_UPDATE_STATION_MSG = "Could not save or update Station";
	private final String STATIONS_FOR_LIMITS_MSG = "Could not get Stations for limits";
	private final String STATIONS_FOR_ONE_PAGE_MSG = "Could not get Stations for one page";
	private final String STATIONS_LIST_COUNT_MSG = "Could not get Stations List count";
	
	private String addMsg = " was added to DB by ";
	private String removeMsg = " was remove from DB by ";
	private String changeMsg = " was change in DB by ";

	private static final Logger LOGGER = Logger
			.getLogger(StationsManagerImpl.class);

	private String entityName = Stations.class.getSimpleName().concat(
			" with id=");

	/**
	 * Class to get access to DAO layer.
	 */
	@Autowired
	private StationsDAO stationDao;

	@Autowired
	private LinesDAO lineDao;

	@Autowired
	private StationsOnLineDAO stlDao;

	@Autowired
	UserNameServiceImpl userName;

	/**
	 * Constructor without arguments.
	 */
	public StationsManagerImpl() {
	}
	
	
	public static StationsManager getInstance() {
		return ManagerFactory.getManager(StationsManager.class);
	}

	/**
	 * @return list of all stations.
	 */
	@Transactional
	@Override
	public List<Stations> findAllStations() {
		try {
			return stationDao.getAllEntities();
		} catch (RuntimeException e) {
			RuntimeException statMangExc = new StationManagerException(
					FIND_STATIONS_MSG, e);
			LOGGER.error(e);
			LOGGER.error(statMangExc);
			throw statMangExc;
		}
	}

	/**
	 * @return Station found by ID.
	 */
	@Transactional(readOnly = true)
	@Override
	public Stations findStationsById(Integer id) {
		try {
			return stationDao.findById(id);
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(
					FIND_STATION_BY_ID_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Saves Station in database using parameters.
	 * 
	 * @param stationCode
	 * 
	 * @param stationName
	 */
	@Transactional
	@Override
	public void createStation(String stationCode, String stationName) {

		try {
			Stations station = new Stations(stationCode, stationName);
			stationDao.save(station);
			LOGGER.info(entityName + station.getStationId() + addMsg
					+ userName.getLoggedUsername());
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(CREATE_STATION_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateStation(Stations station) {

		try {
			stationDao.saveOrUpdate(station);
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(SAVE_OR_UPDATE_STATION_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Removes Stations by Id from database
	 */
	@Transactional
	@Override
	public void removeStations(Integer stationId) {

		Stations station = null;
		try {
			station = (Stations) stationDao.findById(stationId);
			stationDao.remove(station);

			LOGGER.info(entityName + stationId + removeMsg
					+ userName.getLoggedUsername());
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(REMOVE_STATION_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Updates exact station by Id.
	 */
	@Override
	@Transactional
	public void editStation(Integer stationId, String stationCode,
			String stationName) {

		try {
			Stations station = stationDao.findById(stationId);

			station.setStationCode(stationCode);
			station.setStationName(stationName);
			stationDao.update(station);

			LOGGER.info(entityName + station.getStationId() + changeMsg
					+ userName.getLoggedUsername());
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(UPDATE_STATION_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

	}

	/**
	 * Finds station by Name.
	 */
	@Transactional(readOnly = true)
	@Override
	public Stations findByStationName(String stationName) {

		try {
			return stationDao.findByName(stationName);
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(
					FIND_STATION_BY_NAME_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	public List<Stations> getStationsOnCertainLine(String lineName) {

		try {
			return stationDao.findByLineName(lineName);
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(
					FIND_STATIONS_ONCERTAIN_LINE_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

	}
	
	public List<Stations> getStationsOnCertainLine(Integer lineId) {
		return stationDao.findByLineName(lineDao.findById(lineId).getLineName());
	}
	
	@Override
	public List<Stations> getStationsNotOnCertainLine(Integer lineId) {
		List<Stations> existStations = stationDao.findByLineName(lineDao.findById(lineId).getLineName());
		List<Stations> allStations = new ArrayList<Stations>();
		for (Stations st : stationDao.getAllEntities()) {
			if (!existStations.contains(st)) {
				allStations.add(st);
			}
		}
		return allStations;
		
	}

	@Override
	public List<Stations> getStationsNotOnCertainLine(String lineName) {
		List<Stations> existStations = stationDao.findByLineName(lineName);
		List<Stations> allStations = new ArrayList<Stations>();
		for (Stations st : stationDao.getAllEntities()) {
			if (!existStations.contains(st)) {
				allStations.add(st);
			}
		}
		return allStations;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsForLimit(int firstElement, int count) {
		try {
			return stationDao.getStationsForLimits(firstElement, count);
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(
					STATIONS_FOR_LIMITS_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsForPage(int pageNumber, int count) {
		try {
			return getStationsForLimit((pageNumber - 1) * count, count);
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(
					STATIONS_FOR_ONE_PAGE_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public long getStationsListCount() {
		try {
			return stationDao.getStationsListCount();
		} catch (RuntimeException e) {
			RuntimeException ex = new StationManagerException(
					STATIONS_LIST_COUNT_MSG, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}
	
	
	@Override
	public void validateStationListCriteria(
			StationsCriteriaContainer stationsCriteriaContainer, Locale locale) {
		StaticValidator.validateStationListCriteria(stationsCriteriaContainer, locale);
	}
	
	@Transactional(readOnly = true)
	@Override
	public long getStationsListCountWithCriteria(String searchString) {

		return stationDao.getStationsListCriteriaCount(searchString);
	}

	@Transactional(readOnly = true)
	@Override
	public long getStationsListCountUsingContainer(
			StationsCriteriaContainer stationsCriteriaContainer) {

		return getStationsListCountWithCriteria("%"
				+ stationsCriteriaContainer.getSearchString() + "%");
	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsForLimitUsingContainers(
			StationsCriteriaContainer stationsCriteriaContainer,
			PageInfoContainer container) {
		return getStationsForPageWithCriteria(container.getPageNumber(),
				container.getResultsPerPage(),
				"%" + stationsCriteriaContainer.getSearchString() + "%",
				stationsCriteriaContainer.getOrderByParam(),
				stationsCriteriaContainer.getOrderByDirection());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsForLimitWithCriteria(int firstElement,
			long count, String searchString, String orderByParam,
			String orderByDirection) {
		return stationDao.getStationsForOnePageWithCriteria(firstElement,
				(int) count, searchString, orderByParam, orderByDirection);

	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsForPageWithCriteria(int pageNumber, long count,
			String searchString, String orderByParam, String orderByDirection) {
		return getStationsForLimitWithCriteria((int) ((pageNumber - 1) * count),
				count, searchString, orderByParam,
				orderByDirection);
	}


}
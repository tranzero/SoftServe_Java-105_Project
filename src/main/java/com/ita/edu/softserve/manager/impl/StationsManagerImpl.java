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
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.UserNameService;
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

	private final String FOUND_BY_NAME_MSG = " was found by name by ";
	private final String FIND_STATIONS_MSG = "Could not find Stations List";
	private final String FIND_STATION_BY_ID_MSG = "Could not find Station by this Id = ";
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
	UserNameService userName;

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
			LOGGER.error(FIND_STATIONS_MSG, e);
			throw e;
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
			LOGGER.error(FIND_STATION_BY_ID_MSG + id, e);
			throw e;
		}
	}

	/**
	 * Saves Station in database using parameters.
	 * 
	 * @param stationCode
	 * 
	 * @param stationName
	 */
	@Transactional(readOnly = false)
	@Override
	public void createStation(Stations station) {

		try {
			stationDao.save(station);
			
			LOGGER.info(entityName + station.getStationId() + addMsg
					+ userName.getLoggedUsername());
		} catch (RuntimeException e) {
			LOGGER.error(CREATE_STATION_MSG, e);
			throw e;
		}
	}

	
	/**
	 * Save <code>Stations</code> object to database if not exist, else
	 * update it. <br/>
	 * <br/>
	 * If <code>stationId</code> = <code>null</code> than it creates new
	 * station otherwise it finds existing one in database and updates
	 * it.
	 * 
	 * @param station
	 *            the Stations to create or update.
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateStation(Stations station) {
		Integer stationId = station.getStationId();

		try {
			stationDao.saveOrUpdate(station);
			if (stationId == null) {
				LOGGER.info(entityName + stationId + addMsg
						+ userName.getLoggedUsername());

			} else {
				LOGGER.info(entityName + stationId + changeMsg
						+ userName.getLoggedUsername());
			}
		} catch (RuntimeException e) {
			LOGGER.error(SAVE_OR_UPDATE_STATION_MSG, e);
			throw e;
		}
	}

	/**
	 * Removes Stations by Id from database
	 * 
	 * @param stationId - the Id of station to delete.
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
			LOGGER.error(REMOVE_STATION_MSG, e);
			throw e;
		}
	}

	/**
	 * Updates exact station by Id.
	 */
	@Override
	@Transactional
	public boolean editStation(Integer stationId, String stationCode,
			String stationName) {
		Stations station = null;
		station = stationDao.findById(stationId);
		
		if (station != null) {
			try {
				station.setStationCode(stationCode);
				station.setStationName(stationName);
				stationDao.update(station);

				LOGGER.info(entityName + station.getStationId() + changeMsg
						+ userName.getLoggedUsername());

				return true;
			} catch (RuntimeException e) {
				LOGGER.error(UPDATE_STATION_MSG, e);
				throw e;
			}
		}
		return false;
	}

	/**
	 * Finds station by Name.
	 */
	@Transactional(readOnly = true)
	@Override
	public Stations findByStationName(String stationName) {

		try {
			Stations station = stationDao.findByName(stationName);
			
			LOGGER.info(entityName + station.getStationId()
					 + FOUND_BY_NAME_MSG + userName.getLoggedUsername());
			
			return station;
		} catch (RuntimeException e) {
			LOGGER.error(FIND_STATION_BY_NAME_MSG, e);
			throw e;
		}
	}

	/**
	 * Finds List of stations by lineName.
	 * 
	 * @param lineName - name of certain line.
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsOnCertainLine(String lineName) {

		try {
			return stationDao.findByLineName(lineName);
		} catch (RuntimeException e) {
			LOGGER.error(FIND_STATIONS_ONCERTAIN_LINE_MSG, e);
			throw e;
		}

	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsOnCertainLine(Integer lineId) {
		
		try {
			return stationDao.findByLineName(lineDao.findById(lineId).getLineName());
		} catch (RuntimeException e) {
			LOGGER.error(FIND_STATIONS_ONCERTAIN_LINE_MSG, e);
			throw e;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsNotOnCertainLine(Integer lineId) {
		List<Stations> existStations = stationDao.findByLineName(lineDao
				.findById(lineId).getLineName());
		List<Stations> allStations = new ArrayList<Stations>();
		for (Stations st : stationDao.getAllEntities()) {
			if (!existStations.contains(st)) {
				allStations.add(st);
			}
		}
		return allStations;

	}

	
	/*---------------------------Methods for paging, sorting, filtering Stations------------------------------------------*/

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsForLimit(int firstElement, int count) {
		try {
			return stationDao.getStationsForLimits(firstElement, count);
		} catch (RuntimeException e) {
			LOGGER.error(STATIONS_FOR_LIMITS_MSG, e);
			throw e;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Stations> getStationsForPage(int pageNumber, int count) {
		try {
			return getStationsForLimit((pageNumber - 1) * count, count);
		} catch (RuntimeException e) {
			LOGGER.error(STATIONS_FOR_ONE_PAGE_MSG, e);
			throw e;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public long getStationsListCount() {
		try {
			return stationDao.getStationsListCount();
		} catch (RuntimeException e) {
			LOGGER.error(STATIONS_LIST_COUNT_MSG, e);
			throw e;
		}
	}

	@Override
	public void validateStationListCriteria(
			StationsCriteriaContainer stationsCriteriaContainer, Locale locale) {
		StaticValidator.validateStationListCriteria(stationsCriteriaContainer,
				locale);
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
	public List<Stations> getStationsForPageWithCriteria(int pageNumber,
			long count, String searchString, String orderByParam,
			String orderByDirection) {
		return getStationsForLimitWithCriteria(
				(int) ((pageNumber - 1) * count), count, searchString,
				orderByParam, orderByDirection);
	}

}

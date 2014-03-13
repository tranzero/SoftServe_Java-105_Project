package com.ita.edu.softserve.manager.impl;


import java.sql.Time;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.exception.RoutesManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.RoutesManager;

/**
 * RoutesManagerImpl
 * 
 * @author Lyubomyr
 * 
 */
@Service("routesManager")
public class RoutesManagerImpl implements RoutesManager {

	private static final Logger LOGGER = Logger
			.getLogger(RoutesManagerImpl.class);
	
	private String entityName = Routes.class.getSimpleName();
	private final String saveRouteMessage = "Could not save Route";
	private final String updateRouteMessage = "Could not update Route";
	private String removeMessage = " was remove from DB by ";
	private final String removeRouteByIdMessage = "Could not remove Route by id ";
	
	/**
	 * Gets access to Routes DAO.
	 */
	@Autowired
	private RoutesDAO routeDao;

	/**
	 * Gets access to Lines DAO.
	 */
	@Autowired
	private LinesDAO lineDao;
	
	/**
	 * Gets access to Station DAO.
	 */
	@Autowired
	private StationsDAO stationDao;

	/**
	 * The constructor without arguments.
	 */
	public RoutesManagerImpl() {
	}

	/**
	 * Returns list with routes for current page
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Routes> getRoutesForPage(int currentPage, int count, String orderByParam, String orderByDirection) {
		return routeDao.getRoutesForLimits((currentPage - 1) * count, count, orderByParam, orderByDirection);
	}

	/**
	 * Returns count for all Routes
	 */
	@Transactional(readOnly = true)
	@Override
	public long getRoutesListCount() {
		return routeDao.getRoutesListCount();
	}
	
	/**
	 * Returns list with station name, which name start as input stationName
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> getStationNameListCriteria(String stationName) {
		return routeDao.getStationNameListCriteria(stationName + "%");
	}
	
	/**
	 * Returns list with station name, which are in current line
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> getStationNameByLineListCriteria(String stationName,
			String lineName) {
		return routeDao.getStationNameByLineListCriteria(stationName + "%",
				lineDao.findByName(lineName).getLineId());
	}
	
	/**
	 * Returns list with line name, which name start as input lineName
	 */
	@Transactional(readOnly = true)
	@Override
	public List<String> getLineNameListCriteria(String lineName) {
		return routeDao.getLineNameListCriteria(lineName + "%");
	}

	/**
	 * Returns list with routes, which arriving from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public List<RouteTrip> getRoutersListByStNameArrivingForPage(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax,
			int currentPaget, int count){
		return routeDao.getRoutersListByStNameArrivingForLimits(stationNameArrival, timeArrivalMin, timeArrivalMax,
				(currentPaget - 1) * count, count);
	}
	
	/**
	 * Returns count for list with routes, which arriving from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public long getRoutersListByStationNameArrivingCount(String stationNameArrival,
			Time timeArrivalMin, Time timeArrivalMax){
		return routeDao.getRoutersListByStationNameArrivingCount(stationNameArrival, timeArrivalMin, timeArrivalMax);
	}
	
	/**
	 * Returns list with routes, which departing from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public List<RouteTrip> getRoutersListByStNameDepartingForPage(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax, int currentPaget, int count){
		return routeDao.getRoutersListByStNameDepartingForLimits(stationNameDeparture, timeDepartureMin, timeDepartureMax,
				(currentPaget - 1) * count, count);
	}
	
	/**
	 * Returns count for list with routes, which departing from current station
	 */
	@Transactional(readOnly = true)
	@Override
	public long getRoutersListByStationNameDepartingCount(String stationNameDeparture,
			Time timeDepartureMin, Time timeDepartureMax){
		return routeDao.getRoutersListByStationNameDepartingCount(stationNameDeparture, timeDepartureMin, timeDepartureMax);
	}
	
	
	
	/**
	 * Saves the Route object to database
	 */
	@Transactional(readOnly = false)
	@Override
	public void createRoute(Routes route) {
		try {
			routeDao.save(route);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					saveRouteMessage , e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}
	/**
	 * Updates the Route object in database
	 */
	@Transactional(readOnly = false)
	@Override
	public void updateRoute(Routes route) {
		try {
			routeDao.update(route);
		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					updateRouteMessage , e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Removes Route by Id from database.
	 */
	@Transactional
	@Override
	public void removeRouteById(Integer routeId) {
		try {
			routeDao.remove(routeDao.findById(routeId));
			LOGGER.info(entityName + routeId + "was fond");
			LOGGER.info(entityName + removeMessage);

		} catch (RuntimeException e) {
			RuntimeException ex = new RoutesManagerException(
					removeRouteByIdMessage + routeId, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}
	
	/**
	 * Saves the Route object to database
	 */
	@Transactional
	@Override
	public void createRoute(String lines, String routeCode,
			String stationStart, String stationEnd) {
		Routes route = new Routes();
		route.setRouteCode(routeCode);
		route.setLineId(lineDao.findByName(lines));
		route.setRouteName(stationStart + "-" + stationEnd);
		route.setStationStartId(stationDao.findByName(stationStart));
		route.setStationEndId(stationDao.findByName(stationEnd));
		routeDao.save(route);
	}
	
	/**
	 * Updates the Route object in database
	 */
	@Override
	@Transactional
	public void updateRoute(Integer routeId, String lines, String routeCode,
			String stationStart, String stationEnd) {
		Routes route = routeDao.findById(routeId);
		route.setRouteCode(routeCode);
		route.setLineId(lineDao.findByName(lines));
		route.setRouteName(stationStart + "-" + stationEnd);
		route.setStationStartId(stationDao.findByName(stationStart));
		route.setStationEndId(stationDao.findByName(stationEnd));
		routeDao.update(route);
	}

	/**
	 * Return Routes of transports that are arriving to certain station during
	 * certain times
	 * 
	 * @param stationNameArrival
	 *            - name arriving station
	 * @param timeArrivalMin
	 *            - minimum time arrival
	 * @param timeArrivalMax
	 *            - maximum time arrival
	 * 
	 */
	@Override
	public List<RouteTrip> findRoutersListByStationNameArriving(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax)
			throws IllegalArgumentException {
		checkInvalidDataByStationArriving(stationNameArrival, timeArrivalMin,
				timeArrivalMax);
		List<RouteTrip> routesArrivingList = routeDao
				.findRoutersListByStationNameArriving(stationNameArrival,
						timeArrivalMin, timeArrivalMax);
		return routesArrivingList;
	}

	private void checkInvalidDataByStationArriving(String stationNameArrival,
			Time timeArrivalMin, Time timeArrivalMax) {
		if (stationNameArrival == null) {
			throw new IllegalArgumentException(
					"stationNameArrival should be not null");
		}
		if (timeArrivalMin.after(timeArrivalMax)) {
			throw new IllegalArgumentException(
					"timeArrivalMax should be greater or equals than timeArrivalMin");
		}
		if (timeArrivalMin.after(new Time(24,0,0))||timeArrivalMax.after(new Time(24,0,0))) {
			throw new IllegalArgumentException(
					"time can not be greater than 24h");
		}
		if (timeArrivalMin.before(new Time(0,0,0))||timeArrivalMax.before(new Time(0,0,0))) {
			throw new IllegalArgumentException(
					"time can not be less than 0h");
		}
		
	}

	/**
	 * Return Routes of transports that are departing from certain station
	 * during certain times
	 * 
	 * @param stationNameDeparture
	 *            - name departing station
	 * @param timeDepartureMin
	 *            - minimum time departure
	 * @param timeDepartureMax
	 *            - maximum time departure
	 * 
	 */
	@Override
	public List<RouteTrip> findRoutersListByStationNameDeparting(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax) throws IllegalArgumentException {
		checkInvalidDataByStationDeparting(stationNameDeparture,
				timeDepartureMin, timeDepartureMax);
		List<RouteTrip> routesDepartingList = routeDao
				.findRoutersListByStationNameDeparting(stationNameDeparture,
						timeDepartureMin, timeDepartureMax);
		return routesDepartingList;
	}

	private void checkInvalidDataByStationDeparting(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax) {
		if (stationNameDeparture == null) {
			throw new IllegalArgumentException(
					"stationNameDeparture should be not null");
		}
		if (timeDepartureMin.after(timeDepartureMax)) {
			throw new IllegalArgumentException(
					"timeDepartureMax should be greater or equals than timeDepartureMin");
		}
		if (timeDepartureMin.after(new Time(24,0,0))||timeDepartureMax.after(new Time(24,0,0))) {
			throw new IllegalArgumentException(
					"time can not be greater than 24h");
		}
		if (timeDepartureMin.before(new Time(0,0,0))||timeDepartureMax.before(new Time(0,0,0))) {
			throw new IllegalArgumentException(
					"time can not be less than 0h");
		}
	}
	

	@Transactional
	@Override
	public List<Routes> getAllRoutes() {
		return routeDao.getAllEntities();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Routes findRoutesById(int id) {
		return routeDao.findById(id);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Routes findByCode(String routeCode) {
		return routeDao.findByCode(routeCode);
	}
	
	public static RoutesManager getInstance() {
		return ManagerFactory.getManager(RoutesManager.class);
	}
}
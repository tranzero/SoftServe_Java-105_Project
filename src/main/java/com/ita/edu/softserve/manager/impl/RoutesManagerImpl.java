package com.ita.edu.softserve.manager.impl;

import static com.ita.edu.softserve.utils.ParseUtil.timeParse;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Stops;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.RoutesManager;

/**
 * @author Lyubomyr
 * 
 */
@Service("routesManager")
public class RoutesManagerImpl implements RoutesManager {

	private static final Logger LOGGER = Logger
			.getLogger(RoutesManagerImpl.class);

	@Autowired
	private RoutesDAO routeDao;

	@Autowired
	private LinesDAO lineDao;

	public RoutesManagerImpl() {
	}

	@Transactional(readOnly = true)
	@Override
	public Routes findRoutesById(int id) {
		return routeDao.findById(id);
	}

	@Transactional
	@Override
	public void createRoute(String lines, String routeCode) {
		Routes route = new Routes();
		route.setRouteCode(routeCode);
		route.setLineId(lineDao.findByName(lines));
		routeDao.save(route);
	}

	@Override
	@Transactional
	public void updateRoute(Integer routeId, String lines, String routeCode) {
		Routes route = routeDao.findById(routeId);
		route.setRouteCode(routeCode);
		route.setLineId(lineDao.findByName(lines));
		routeDao.update(route);
	}

	@Transactional
	@Override
	public void removeRouteById(Integer routeId) {
		try {
			routeDao.remove(routeDao.findById(routeId));
		} catch (NoResultException e) {
			LOGGER.error("No routes", e);
		}
	}

	@Transactional
	@Override
	public List<Routes> getAllRoutes() {
		return routeDao.getAllEntities();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Routes> getRoutesForPage(int currentPage, int count) {
		return routeDao.getRoutesForLimits((currentPage - 1) * count, count);
	}

	@Transactional(readOnly = true)
	@Override
	public long getRoutesListCount() {
		return routeDao.getRoutesListCount();
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
		if (routesArrivingList.get(0) == null) {
			return null;
		}
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
		if (routesDepartingList.get(0) == null) {
			return null;
		}
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
	}

	public static RoutesManager getInstance() {
		return ManagerFactory.getManager(RoutesManager.class);
	}
}
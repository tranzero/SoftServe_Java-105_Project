package com.ita.edu.softserve.manager.impl;

import java.util.List;
import java.sql.Time;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.StopsDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stops;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * @author Lyubomyr
 * 
 */
@Service("routesService")
public class RoutesManagerImpl implements RoutesManager {

	private static final Logger LOGGER = Logger.getLogger(Routes.class);

	@Autowired
	private RoutesDAO routeDao;

	@Autowired
	private StopsDAO stopDao;

	@Autowired
	private StationsOnLineDAO stationOnLineDao;

	public RoutesManagerImpl() {
	}

	public RoutesManagerImpl(RoutesDAOImpl routeDao) {
		this.routeDao = routeDao;
	}

	public RoutesManagerImpl(StopsDAO stopDao) {
		this.stopDao = stopDao;
	}

	public RoutesManagerImpl(StationsOnLineDAO stationOnLineDao) {
		this.stationOnLineDao = stationOnLineDao;
	}

	/**
	 * Return Routes of transports that are arriving to certain station during
	 * certain times
	 * 
	 * @param idStationArriving
	 *            - id arriving station
	 * @param timeArrivalMin
	 *            - minimum time arrival
	 * @param timeArrivalMax
	 *            - maximum time arrival
	 * 
	 */
	@Override
	public List<Routes> findRoutersListByStationIdArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax)
			throws IllegalArgumentException {
		checkInvalidDataByStationArriving(idStationArriving, timeArrivalMin,
				timeArrivalMax);

		List<Routes> routersListByStationArriving = new ArrayList<Routes>();

		for (StationsOnLine stationOnLine : stationOnLineDao.getAllEntities()) {
			if (stationOnLine.getStationId().getStationId() == idStationArriving)
				for (Stops stop : stopDao.getAllEntities()) {
					if (stationOnLine.getStationOnLineId() == stop
							.getStationOnLineID().getStationOnLineId())
						for (Routes route : routeDao.getAllEntities()) {
							if (stop.getRouteId().getRouteId() == route
									.getRouteId()) {
								@SuppressWarnings("deprecation")
								Time timeForComparison = new Time(route
										.getStartTime().getHours()
										+ stop.getArrival().getHours(), route
										.getStartTime().getMinutes()
										+ stop.getArrival().getMinutes(), route
										.getStartTime().getSeconds()
										+ stop.getArrival().getSeconds());
								if ((timeForComparison.equals(timeArrivalMin) || timeForComparison
										.after(timeArrivalMin))
										&& (timeForComparison
												.equals(timeArrivalMax) || timeForComparison
												.before(timeArrivalMax))) {
									routersListByStationArriving.add(route);
								}
							}
						}
				}
		}
		return routersListByStationArriving;
	}

	private void checkInvalidDataByStationArriving(int idStationArriving,
			Time timeArrivalMin, Time timeArrivalMax) {
		if (idStationArriving < 1) {
			throw new IllegalArgumentException(
					"idStationArriving should be greater than zero");
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
	 * @param idStationArriving
	 *            - id departing station
	 * @param timeDepartureMin
	 *            - minimum time departure
	 * @param timeDepartureMax
	 *            - maximum time departure
	 * 
	 */
	@Override
	public List<Routes> findRoutersListByStationIdDeparting(
			int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax)
			throws IllegalArgumentException {

		checkInvalidDataByStationDeparting(idStationDeparting,
				timeDepartureMin, timeDepartureMax);

		List<Routes> routersListByStationDeparting = new ArrayList<Routes>();

		for (StationsOnLine stationOnLine : stationOnLineDao.getAllEntities()) {
			if (stationOnLine.getStationId().getStationId() == idStationDeparting)
				for (Stops stop : stopDao.getAllEntities()) {
					if (stationOnLine.getStationOnLineId() == stop
							.getStationOnLineID().getStationOnLineId())
						for (Routes route : routeDao.getAllEntities()) {
							if (stop.getRouteId().getRouteId() == route
									.getRouteId()) {
								@SuppressWarnings("deprecation")
								Time timeForComparison = new Time(route
										.getStartTime().getHours()
										+ stop.getDeparture().getHours(), route
										.getStartTime().getMinutes()
										+ stop.getDeparture().getMinutes(),
										route.getStartTime().getSeconds()
												+ stop.getDeparture()
														.getSeconds());
								if ((timeForComparison.equals(timeDepartureMin) || timeForComparison
										.after(timeDepartureMin))
										&& (timeForComparison
												.equals(timeDepartureMax) || timeForComparison
												.before(timeDepartureMax))) {
									routersListByStationDeparting.add(route);
								}
							}
						}
				}
		}
		return routersListByStationDeparting;
	}

	private void checkInvalidDataByStationDeparting(int idStationDeparting,
			Time timeDepartureMin, Time timeDepartureMax) {
		if (idStationDeparting < 1) {
			throw new IllegalArgumentException(
					"idStationDeparting should be greater than zero");
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
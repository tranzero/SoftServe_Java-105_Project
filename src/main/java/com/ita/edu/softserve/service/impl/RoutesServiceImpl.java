package com.ita.edu.softserve.service.impl;

import java.util.List;
import java.sql.Time;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.service.RoutesService;

/**
 * @author Lyubomyr
 * 
 */
@Service
public class RoutesServiceImpl implements RoutesService {

	private RoutesDAOImpl routesDao = new RoutesDAOImpl();

	public RoutesServiceImpl() {
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
		if (idStationArriving < 1) {
			throw new IllegalArgumentException(
					"idStationArriving should be greater than zero");
		}
		return routesDao.findRoutersListByStationIdArriving(idStationArriving,
				timeArrivalMin, timeArrivalMax);
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
		if (idStationDeparting < 1) {
			throw new IllegalArgumentException(
					"idStationDeparting should be greater than zero");
		}
		return routesDao.findRoutersListByStationIdDeparting(
				idStationDeparting, timeDepartureMin, timeDepartureMax);
	}
}
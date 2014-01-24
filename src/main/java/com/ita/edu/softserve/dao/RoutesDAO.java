package com.ita.edu.softserve.dao;

import java.sql.Time;
import java.util.List;

import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 * 
 */
public interface RoutesDAO extends AbstractDAOIface<Routes>{

	/**
	 * Find Routes by routeCode
	 * 
	 * @param routeCode
	 * @return
	 */
	Routes findByCode(String routeCode);

	/**
	 * Find Routes list by StationId arriving
	 * 
	 * @param idStationArriving
	 * @param timeArrivalMin
	 * @param timeArrivalMax
	 * @return List<Routes>
	 */
	public List<Routes> findRoutersListByStationIdArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax);

	/**
	 * Find Routes list by StationId departing
	 * 
	 * @param idStationDeparting
	 * @param timeDepartureMin
	 * @param timeDepartureMax
	 * @return List<Routes>
	 */
	public List<Routes> findRoutersListByStationIdDeparting(
			int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax);
}

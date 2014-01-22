package com.ita.edu.softserve.dao;

import java.util.List;
import java.sql.Time;

import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 * 
 */
public interface RoutesDAO {
	/**
	 * Find Routes by routeCode
	 * 
	 * @param routeCode
	 * @return
	 */
	Routes findByCode(String routeCode);

	public List<Routes> findRoutersListByStationIdArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax);

	public List<Routes> findRoutersListByStationIdDeparting(
			int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax);
}

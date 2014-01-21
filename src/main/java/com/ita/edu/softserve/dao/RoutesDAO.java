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

	public List<Routes> findRoutersListByStationIDArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax);

	public List<Routes> findRoutersListByStationIDDeparting(
			int idStationDeparting, Time timeDepartureMin,
			Time timeDepartureMax);
}

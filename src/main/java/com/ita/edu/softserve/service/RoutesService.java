/**
 * 
 */
package com.ita.edu.softserve.service;

import java.util.List;
import java.sql.Time;

import com.ita.edu.softserve.entity.Routes;

/**
 * @author Lyubomyr
 * 
 */
public interface RoutesService {
	public List<Routes> findRoutersListByStationIdArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax);

	public List<Routes> findRoutersListByStationIdDeparting(
			int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax);
}
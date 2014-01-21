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
	public List<Routes> findRoutersListByStationIDArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax);

	public List<Routes> findRoutersListByStationIDDeparting(
			int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax);
}
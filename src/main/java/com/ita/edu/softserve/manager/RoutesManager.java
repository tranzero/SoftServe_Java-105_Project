/**
 * 
 */
package com.ita.edu.softserve.manager;

import java.util.List;
import java.sql.Time;

import com.ita.edu.softserve.entity.Routes;

/**
 * @author Lyubomyr
 * 
 */
public interface RoutesManager extends BaseManager  {
	public List<Routes> findRoutersListByStationIdArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax);

	public List<Routes> findRoutersListByStationIdDeparting(
			int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax);
}
/**
 * 
 */
package com.ita.edu.softserve.manager;

import java.sql.Time;
import java.util.List;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.impl.RouteTrip;

/**
 * @author Lyubomyr
 * 
 */
public interface RoutesManager extends BaseManager {

	Routes findRoutesById(int id);

	List<Routes> getAllRoutes();

	void createRoute(String lines, String routeCode);

	void updateRoute(Integer routeId, String lines, String routeCode);

	void removeRouteById(Integer routeId);

	public List<Routes> getRoutesForPage(int currentPage, int count);

	long getRoutesListCount();

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
	public List<RouteTrip> findRoutersListByStationNameArriving(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax);

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
	public List<RouteTrip> findRoutersListByStationNameDeparting(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax);
}
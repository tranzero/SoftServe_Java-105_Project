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
	
	Routes findByCode(String routeCode);
	
	public List<Routes> getRoutesForPage(int currentPage, int count,
			String orderByParam, String orderByDirection);

	long getRoutesListCount();
	
	public List<String> getStationNameListCriteria(String stationName);
	
	List<String> getStationNameByLineListCriteria(String stationName,
			String lineName);
	
	public List<String> getLineNameListCriteria(String lineName);
	
	public List<RouteTrip> getRoutersListByStNameArrivingForPage(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax,
			int currentPaget, int count);
	
	public long getRoutersListByStationNameArrivingCount(String stationNameArrival,
			Time timeArrivalMin, Time timeArrivalMax);

	public List<RouteTrip> getRoutersListByStNameDepartingForPage(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax, int currentPaget, int count);
	
	public long getRoutersListByStationNameDepartingCount(String stationNameDeparture,
			Time timeDepartureMin, Time timeDepartureMax);

	
	void createRoute(Routes route);

	void updateRoute(Routes route);
	
	void removeRouteById(Integer routeId);
	
	void createRoute(String lines, String routeCode, String stationStart,
			String stationEnd);
	
	void updateRoute(Integer routeId, String lines, String routeCode, String stationStart,
			String stationEnd);
	

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
	
	List<Routes> getAllRoutes();

}
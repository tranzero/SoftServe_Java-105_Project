package com.ita.edu.softserve.dao;

import java.sql.Time;
import java.util.List;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.impl.RouteTrip;

/*
 *  @author Lyubomyr
 */
public interface RoutesDAO extends AbstractDAOIface<Routes> {

	/**
	 * Find Routes by routeCode
	 * 
	 * @param routeCode
	 * @return
	 */
	Routes findByCode(String routeCode);

	Routes findByLineId(int id);

	List<Routes> getRoutesForLimits(int currentPaget, int count,
			String orderByParam, String orderByDirection);

	long getRoutesListCount();
	
	public List<String> getStationNameListCriteria(String stationName);
	
	public List<String> getStationNameByLineListCriteria(String stationName, int lineId);
	
	public List<String> getLineNameListCriteria(String lineName);
	
	public List<RouteTrip> getRoutersListByStNameArrivingForLimits(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax,
			int currentPaget, int count);
	
	public long getRoutersListByStationNameArrivingCount(String stationNameArrival,
			Time timeArrivalMin, Time timeArrivalMax);

	
	
	public List<RouteTrip> getRoutersListByStNameDepartingForLimits(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax, int currentPaget, int count);
	
	public long getRoutersListByStationNameDepartingCount(String stationNameDeparture,
			Time timeDepartureMin, Time timeDepartureMax);
	
	
	
	
	
	public List<RouteTrip> findRoutersListByStationNameArriving(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax);

	public List<RouteTrip> findRoutersListByStationNameDeparting(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax);

}
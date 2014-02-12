package com.ita.edu.softserve.dao;

import java.sql.Time;
import java.util.List;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
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
	
	List<Routes> getRoutesForLimits(int currentPaget, int count);
	
	long getRoutesListCount();

	public List<RouteTrip> findRoutersListByStationNameArriving(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax);

	public List<RouteTrip> findRoutersListByStationNameDeparting(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax);
}
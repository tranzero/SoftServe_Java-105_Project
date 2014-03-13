package com.ita.edu.softserve.dao.impl;

import java.sql.Time;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.impl.RouteTrip;

/**
 * 
 * class RoutesDAOImpl
 * 
 */
@Repository("routesDao")
public class RoutesDAOImpl extends AbstractDAO<Routes> implements RoutesDAO {
	/**
	 * Defines the name of order by column parameter
	 */
	public static final String ORDER_BY_PARAM_NAME = "orderByParam";
	/**
	 * Defines the name of order by column direction parameter
	 */
	public static final String ORDER_BY_DIRECTION_NAME = "orderByDirection";
	
	@Override
	public Class<Routes> getEntityClass() {
		return Routes.class;
	}
	
	/**
	 * Returns list with routes for current page
	 */
	@Override
	public List<Routes> getRoutesForLimits(int currentPaget, int count,
			String orderByParam, String orderByDirection) {
		Query query = entityManager
				.createNamedQuery(
						Routes.ROUTES_ALL_ORDER_BY)
				.setFirstResult(currentPaget).setMaxResults(count);
		return (List<Routes>) query.getResultList();
	}
	
	/**
	 * Returns count for all Routes
	 */
	@Override
	public long getRoutesListCount() {
		return (long) find((Query)entityManager
				.createNamedQuery(Routes.ROUTES_FIND_COUNT));
	}
	
	/**
	 * Returns list with station name, which name start as input stationName
	 */
	public List<String> getStationNameListCriteria(String stationName) {
		Query query = entityManager
				.createQuery(Routes.STATIONS_NAME_FIND_BY_CRITERIA_QUERY)
				.setParameter(Routes.STATION_NAME, stationName)
				.setMaxResults(10);
		return (List<String>) query.getResultList();
	}
	
	/**
	 * Returns list with station name, which are in current line
	 */
	public List<String> getStationNameByLineListCriteria(String stationName, int lineId) {
		Query query = entityManager
				.createQuery(Routes.STATIONS_NAME_ON_LINE_FIND_BY_CRITERIA_QUERY)
				.setParameter(Routes.LINE_ID, lineId)
				.setParameter(Routes.STATION_NAME, stationName)
				.setMaxResults(10);
		return (List<String>) query.getResultList();
	}
	
	/**
	 * Returns list with line name, which name start as input lineName
	 */
	public List<String> getLineNameListCriteria(String lineName) {
		Query query = entityManager
				.createQuery(Routes.LINE_NAME_FIND_BY_CRITERIA_QUERY)
				.setParameter(Routes.LINE_NAME, lineName)
				.setMaxResults(10);
		return (List<String>) query.getResultList();
	}

	/**
	 * Returns list with routes, which arriving from current station
	 */
	@Override
	public List<RouteTrip> getRoutersListByStNameArrivingForLimits(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax,
			int currentPaget, int count){
		Query query = entityManager
				.createNamedQuery(Routes.FIND_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL)
				.setParameter(1, stationNameArrival)
				.setParameter(2, timeArrivalMin)
				.setParameter(3, timeArrivalMax)
				.setFirstResult(currentPaget).setMaxResults(count);
		return (List<RouteTrip>) query.getResultList();
	}
	
	/**
	 * Returns count for list with routes, which arriving from current station
	 */
	@Override
	public long getRoutersListByStationNameArrivingCount(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax) {
		return findRoutersListByStationNameArriving(stationNameArrival,
				timeArrivalMin, timeArrivalMax).size();
	}

	/**
	 * Returns list with routes, which departing from current station
	 */
	@Override
	public List<RouteTrip> getRoutersListByStNameDepartingForLimits(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax, int currentPaget, int count){
		Query query = entityManager
				.createNamedQuery(Routes.FIND_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL)
				.setParameter(1, stationNameDeparture)
				.setParameter(2, timeDepartureMin)
				.setParameter(3, timeDepartureMax)
				.setFirstResult(currentPaget).setMaxResults(count);
		return (List<RouteTrip>) query.getResultList();
	}
	
	/**
	 * Returns count for list with routes, which departing from current station
	 */
	@Override
	public long getRoutersListByStationNameDepartingCount(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax) {
		return findRoutersListByStationNameDeparting(stationNameDeparture,
				timeDepartureMin, timeDepartureMin).size();
	}
	
	@Override
	public List<RouteTrip> findRoutersListByStationNameArriving(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax) {
		Query query = entityManager
				.createNamedQuery(
						Routes.FIND_BY_ARRIVING_STATION_NAME_AND_TIME_INTERVAL)
				.setParameter(1, stationNameArrival)
				.setParameter(2, timeArrivalMin)
				.setParameter(3, timeArrivalMax);
		return query.getResultList();
	}

	@Override
	public List<RouteTrip> findRoutersListByStationNameDeparting(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax) {
		Query query = entityManager
				.createNamedQuery(
						Routes.FIND_BY_DEPARTING_STATION_NAME_AND_TIME_INTERVAL)
				.setParameter(1, stationNameDeparture)
				.setParameter(2, timeDepartureMin)
				.setParameter(3, timeDepartureMax);
		return query.getResultList();
	}
	
	@Override
	public Routes findByCode(String routeCode) {
		Query query = entityManager.createNamedQuery(Routes.FIND_BY_CODE)
				.setParameter(1, routeCode);
		return (Routes) query.getSingleResult();
	}
	
	@Override
	public Routes findByLineId(int id) {
		Query query = entityManager
				.createNamedQuery(Routes.FIND_BY_LINEID).setParameter(1,
						id);
		return (Routes) query.getSingleResult();
	}
	
	
}
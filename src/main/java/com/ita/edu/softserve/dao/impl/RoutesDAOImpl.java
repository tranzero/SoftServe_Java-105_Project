package com.ita.edu.softserve.dao.impl;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
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
	
	@Override
	public List<Routes> getRoutesForLimits(int currentPaget, int count,
			String orderByParam, String orderByDirection) {
		Query query = entityManager
				.createNamedQuery(
						Routes.ROUTES_ALL_ORDER_BY)
				.setFirstResult(currentPaget).setMaxResults(count);
		return (List<Routes>) query.getResultList();
	}
	
	@Override
	public long getRoutesListCount() {
		return (long) find((Query)entityManager
				.createNamedQuery(Routes.ROUTES_FIND_COUNT));
	}
	
	public List<String> getStationNameListCriteria(String stationName) {
		Query query = entityManager
				.createQuery(Routes.STATIONS_NAME_FIND_BY_CRITERIA_QUERY)
				.setParameter(Routes.STATION_NAME, stationName)
				.setMaxResults(10);
		return (List<String>) query.getResultList();
	}
	
	public List<String> getStationNameByLineListCriteria(String stationName, int lineId) {
		System.out.println(lineId);
		Query query = entityManager
				.createQuery(Routes.STATIONS_NAME_FIND_BY_CRITERIA_QUERY)
				//.setParameter(Routes.STATION_NAME, stationName)
				//.setParameter(Routes.LINE_ID, lineId)
				.setMaxResults(10);
		System.out.println(((List<String>) query.getResultList()).size());
		return (List<String>) query.getResultList();
	}
	
	public List<String> getLineNameListCriteria(String lineName) {
		Query query = entityManager
				.createQuery(Routes.STATIONS_NAME_FIND_BY_CRITERIA_QUERY)
				.setParameter(Routes.STATION_NAME, lineName)
				.setMaxResults(10);
		return (List<String>) query.getResultList();
	}

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
	
	@Override
	public long getRoutersListByStationNameArrivingCount(
			String stationNameArrival, Time timeArrivalMin, Time timeArrivalMax) {
		return findRoutersListByStationNameArriving(stationNameArrival,
				timeArrivalMin, timeArrivalMax).size();
	}

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
	
	@Override
	public long getRoutersListByStationNameDepartingCount(
			String stationNameDeparture, Time timeDepartureMin,
			Time timeDepartureMax) {
		return findRoutersListByStationNameDeparting(stationNameDeparture,
				timeDepartureMin, timeDepartureMin).size();
	}
	
	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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
package com.ita.edu.softserve.dao.impl;

import java.sql.Time;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.RouteTrip;

/**
 * 
 * class RoutesDAOImpl
 * 
 */
@Repository("routesDao")
public class RoutesDAOImpl extends AbstractDAO<Routes> implements RoutesDAO {

	@Override
	public Routes findByCode(String routeCode) {
		Query query = entityManager.createNamedQuery(Routes.FIND_BY_CODE)
				.setParameter(1, routeCode);
		return (Routes) query.getSingleResult();
	}

	@Override
	public Class<Routes> getEntityClass() {

		return Routes.class;
	}

	@Override
	public Routes findByLineId(int id) {
		Query query = entityManager
				.createNamedQuery(Routes.FIND_BY_LINEID).setParameter(1,
						id);
		return (Routes) query.getSingleResult();
	}

	@Override
	public List<Routes> getRoutesForLimits(int currentPaget, int count) {
		Query query = entityManager
				.createNamedQuery(Routes.ROUTES_FIND_ALL)
				.setFirstResult(currentPaget)
				.setMaxResults(count);
		return (List<Routes>)query.getResultList();
	}

	@Override
	public long getRoutesListCount() {
		return (long) find((Query)entityManager
				.createNamedQuery(Routes.ROUTES_FIND_COUNT));
	}
	
	@SuppressWarnings("unchecked")
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
}
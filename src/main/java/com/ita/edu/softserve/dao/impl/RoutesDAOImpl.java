package com.ita.edu.softserve.dao.impl;

import java.sql.Time;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;

/**
 * 
 * @author iryna
 * 
 * class RoutesDAOImpl
 * 
 */
@Repository
public class RoutesDAOImpl extends AbstractDAO<Routes> implements RoutesDAO {

	@Override
	public Routes findByCode(String routeCode) {
		Query query = entityManager.createNamedQuery(Lines.FIND_BY_NAME)
				.setParameter(1, routeCode);
		return (Routes) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Routes> findRoutersListByStationIdArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax) {
		Query query = entityManager
				.createNamedQuery(
						Routes.FIND_ROUTERS_LIST_BY_STATION_ID_ARRIVING)
				.setParameter("idStationArriving", idStationArriving)
				.setParameter("timeArrivalMin", timeArrivalMin.toString())
				.setParameter("timeArrivalMax", timeArrivalMax.toString());
		return (List<Routes>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Routes> findRoutersListByStationIdDeparting(
			int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax) {
		Query query = entityManager
				.createNamedQuery(
						Routes.FIND_ROUTERS_LIST_BY_STATION_ID_DEPARTING)
				.setParameter("idStationDeparting", idStationDeparting)
				.setParameter("timeDepartureMin", timeDepartureMin.toString())
				.setParameter("timeDepartureMax", timeDepartureMax.toString());
		return (List<Routes>) query.getResultList();
	}

	@Override
	public Class<Routes> getEntityClass() {

		return Routes.class;
	}
}
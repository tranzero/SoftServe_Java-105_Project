package com.ita.edu.softserve.dao.impl;

import java.util.List;
import java.sql.Time;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author iryna
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
	public List<Routes> findRoutersListByStationIDArriving(
			int idStationArriving, Time timeArrivalMin, Time timeArrivalMax) {
		Query query = entityManager
				.createNamedQuery(Routes.FIND_ROUTERS_LIST_BY_STATION_ID_ARRIVING)
				.setParameter("idStationArriving", idStationArriving)
				.setParameter("timeArrivalMin", timeArrivalMin.toString())
				.setParameter("timeArrivalMax", timeArrivalMax.toString());
		return (List<Routes>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Routes> findRoutersListByStationIDDeparting(
			int idStationDeparting, Time timeDepartureMin,
			Time timeDepartureMax) {
		Query query = entityManager
				.createNamedQuery(Routes.FIND_ROUTERS_LIST_BY_STATION_ID_DEPARTING)
				.setParameter("idStationDeparting", idStationDeparting)
				.setParameter("timeDepartureMin", timeDepartureMin.toString())
				.setParameter("timeDepartureMax", timeDepartureMax.toString());
		return (List<Routes>)query.getResultList();
	}

	@Override
	protected Class<Routes> getEntityClass() {
		
		return Routes.class;
	}

	

}

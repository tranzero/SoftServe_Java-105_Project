package com.ita.edu.softserve.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;

/**
 * @author dnycktc
 */
@Repository("tripsDao")
public class TripsDAOImpl extends AbstractDAO<Trips> implements TripsDAO {

	@Override
	public Class<Trips> getEntityClass() {
		return Trips.class;
	}

	@Override
	public long getTripsListCount() {
		return (long) find((Query) entityManager
				.createNamedQuery(Trips.TRIPS_FIND_COUNT));
	}

	@Override
	public long getTripsListCriteriaCount(String transportCode,
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate) {
		return (long) find((Query) entityManager
				.createNamedQuery(Trips.TRIPS_FIND_CRITERIA_COUNT)
				.setParameter("transportcode", transportCode)
				.setParameter("remseatclass1", remSeatClass1)
				.setParameter("remseatclass2", remSeatClass2)
				.setParameter("remseatclass3", remSeatClass3)
				.setParameter("mindate", minDate, TemporalType.DATE)
				.setParameter("maxdate", maxDate, TemporalType.DATE));
	}

	public List<Trips> getTripsListCriteria(int firstElement, int count,
			String transportCode, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate,
			String orderByParam, String orderByDirection) {

		Query query = entityManager
				.createQuery(Trips.TRIPS_FIND_BY_CRITERIA_QUERY + orderByParam
						+ " " + orderByDirection)
				.setParameter("transportcode", transportCode)
				.setParameter("remseatclass1", remSeatClass1)
				.setParameter("remseatclass2", remSeatClass2)
				.setParameter("remseatclass3", remSeatClass3)
				.setParameter("mindate", minDate, TemporalType.DATE)
				.setParameter("maxdate", maxDate, TemporalType.DATE)
				.setFirstResult(firstElement)
				.setMaxResults(count);
		return (List<Trips>) query.getResultList();

	}

	@Override
	public List<Trips> findByTransportId(int id) {
		Query query = entityManager.createNamedQuery(Trips.FIND_BY_TRANSPORTID)
				.setParameter(1, id);
		List<Trips> result = (List<Trips>) query.getResultList();
		return result;
	}

	@Override
	public void saveOrUpdate(Trips entity) {
		if (entity.getTripId() == null) {
			entityManager.persist(entity);
			entityManager.refresh(entity);
		} else {
			entityManager.merge(entity);
		}

	}

	@Override
	public List<Trips> getTripsForLimits(int firstElement, int count) {
		Query query = entityManager.createNamedQuery(Trips.TRIPS_FIND_ALL)
				.setFirstResult(firstElement).setMaxResults(count);
		return (List<Trips>) query.getResultList();
	}

}

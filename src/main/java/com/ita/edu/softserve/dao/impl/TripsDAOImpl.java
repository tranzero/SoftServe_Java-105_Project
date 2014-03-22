package com.ita.edu.softserve.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.entity.Trips;

/**
 * @author dnycktc
 */
@Repository
public class TripsDAOImpl extends AbstractDAO<Trips> implements TripsDAO {

	/**
	 * Defines the name of order by column parameter
	 */
	public static final String ORDER_BY_PARAM_NAME = "orderByParam";
	/**
	 * Defines the name of order by column direction parameter
	 */
	public static final String ORDER_BY_DIRECTION_NAME = "orderByDirection";

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
			String routeName, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate) {
		return (long) find((Query) entityManager
				.createNamedQuery(Trips.TRIPS_FIND_CRITERIA_COUNT)
				.setParameter(Trips.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Trips.ROUTE_NAME_NAME, routeName)
				.setParameter(Trips.REM_SEAT_CLASS_1_NAME, remSeatClass1)
				.setParameter(Trips.REM_SEAT_CLASS_2_NAME, remSeatClass2)
				.setParameter(Trips.REM_SEAT_CLASS_3_NAME, remSeatClass3)
				.setParameter(Trips.MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(Trips.MAX_DATE_NAME, maxDate, TemporalType.DATE));

	}

	@Override
	public long getTripsListCriteriaIndex(String transportCode,
			String routeName, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate,
			String orderByParam, String orderByDirection, Trips knownElement) {
		return (long) find((Query) entityManager
				.createQuery(Trips.TRIPS_FIND_CRITERIA_INDEX_QUERY + orderByParam + " "
						+ orderByDirection)
				.setParameter(Trips.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Trips.ROUTE_NAME_NAME, routeName)
				.setParameter(Trips.REM_SEAT_CLASS_1_NAME, remSeatClass1)
				.setParameter(Trips.REM_SEAT_CLASS_2_NAME, remSeatClass2)
				.setParameter(Trips.REM_SEAT_CLASS_3_NAME, remSeatClass3)
				.setParameter(Trips.MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(Trips.MAX_DATE_NAME, maxDate, TemporalType.DATE)
				.setParameter(Trips.KNOWN_TRIP_NAME, knownElement)
				);

	}

	public List<Trips> getTripsListCriteria(int firstElement, int count,
			String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection) {

		Query query = entityManager
				.createQuery(
						Trips.TRIPS_FIND_BY_CRITERIA_QUERY + orderByParam + " "
								+ orderByDirection)
				.setParameter(Trips.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Trips.ROUTE_NAME_NAME, routeName)
				.setParameter(Trips.REM_SEAT_CLASS_1_NAME, remSeatClass1)
				.setParameter(Trips.REM_SEAT_CLASS_2_NAME, remSeatClass2)
				.setParameter(Trips.REM_SEAT_CLASS_3_NAME, remSeatClass3)
				.setParameter(Trips.MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(Trips.MAX_DATE_NAME, maxDate, TemporalType.DATE)
				.setFirstResult(firstElement).setMaxResults(count);
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

	@Override
	public void reduceRemSeaatClass1(Integer tripId) {
		Query query = entityManager
				.createQuery("UPDATE Trips t SET t.remSeatClass1 = t.remSeatClass1-1 WHERE t.tripId = :p");
		int updateCount = query.setParameter("p", tripId).executeUpdate();
	}

	@Override
	public void reduceRemSeaatClass2(Integer tripId) {
		Query query = entityManager
				.createQuery("UPDATE Trips t SET t.remSeatClass2 = t.remSeatClass2-1 WHERE t.tripId = :p");
		int updateCount = query.setParameter("p", tripId).executeUpdate();
	}

	@Override
	public void reduceRemSeaatClass3(Integer tripId) {
		Query query = entityManager
				.createQuery("UPDATE Trips t SET t.remSeatClass3 = t.remSeatClass3-1 WHERE t.tripId = :p");
		int updateCount = query.setParameter("p", tripId).executeUpdate();
	}

	@Override
	public void increaseRemSeaatClass1(Integer tripId) {
		Query query = entityManager
				.createQuery("UPDATE Trips t SET t.remSeatClass1 = t.remSeatClass1+1 WHERE t.tripId = :p");
		int updateCount = query.setParameter("p", tripId).executeUpdate();
	}

	@Override
	public void increaseRemSeaatClass2(Integer tripId) {
		Query query = entityManager
				.createQuery("UPDATE Trips t SET t.remSeatClass2 = t.remSeatClass2+1 WHERE t.tripId = :p");
		int updateCount = query.setParameter("p", tripId).executeUpdate();

	}

	@Override
	public void increaseRemSeaatClass3(Integer tripId) {
		Query query = entityManager
				.createQuery("UPDATE Trips t SET t.remSeatClass3 = t.remSeatClass3+1 WHERE t.tripId = :p");
		int updateCount = query.setParameter("p", tripId).executeUpdate();
	}

}

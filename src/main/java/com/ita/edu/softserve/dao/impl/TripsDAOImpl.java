package com.ita.edu.softserve.dao.impl;

import java.util.Arrays;
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
import com.ita.edu.softserve.validationcontainers.impl.TripsCriteriaContainerImpl;

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

	/**
	 * Map for defining sorting order direction in search page for element query
	 */
	private static Map<String, String> SORT_DIRECTION_MAP;

	/**
	 * Map for defining sorting order in search page for element query
	 */
	private static Map<String, Integer> SORT_ORDER_MAP;

	static {
		String[] orderByColumns = Arrays.copyOf(
				TripsCriteriaContainerImpl.TRIPS_ORDER_BY_COLUMNS,
				TripsCriteriaContainerImpl.TRIPS_ORDER_BY_COLUMNS.length);
		SORT_DIRECTION_MAP = new HashMap<String, String>();
		SORT_DIRECTION_MAP.put("ASC", " < ");
		SORT_DIRECTION_MAP.put("DESC", " > ");
		SORT_ORDER_MAP = new HashMap<String, Integer>();
		for (int i = 0; i < orderByColumns.length; i++) {
			SORT_ORDER_MAP.put(orderByColumns[i], i);
		}
	}

	private void setPassValue(Query q, String orderByParam, Trips knownElement) {
		Map<Integer, Object> sortElementMap = new HashMap<Integer, Object>();
		int i = 0;
		int dateElement;
		sortElementMap.put(i++, knownElement.getTransport().getTransportCode());
		sortElementMap.put(i++, knownElement.getTransport().getRoutes()
				.getRouteName());
		sortElementMap.put(i++, knownElement.getRemSeatClass1());
		sortElementMap.put(i++, knownElement.getRemSeatClass2());
		sortElementMap.put(i++, knownElement.getRemSeatClass3());
		dateElement = i;
		sortElementMap.put(i++, knownElement.getStartDate());
		sortElementMap.put(i++, knownElement.getTransport().getStartTime());
		if (SORT_ORDER_MAP.get(orderByParam) == dateElement) {
			q.setParameter(
					Trips.PASSED_VALUE_NAME,
					(Date) sortElementMap.get(SORT_ORDER_MAP.get(orderByParam)),
					TemporalType.DATE);
		} else {
			q.setParameter(Trips.PASSED_VALUE_NAME,
					sortElementMap.get(SORT_ORDER_MAP.get(orderByParam)));
		}

	}

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
		orderByDirection = orderByDirection.trim().toUpperCase();
		Query q = (Query) entityManager
				.createQuery(
						Trips.TRIPS_FIND_CRITERIA_INDEX_QUERY_PART1
								+ orderByParam
								+ SORT_DIRECTION_MAP.get(orderByDirection)
								+ Trips.TRIPS_FIND_CRITERIA_INDEX_QUERY_PART2
								+ orderByParam
								+ Trips.TRIPS_FIND_CRITERIA_INDEX_QUERY_PART3
								+ SORT_DIRECTION_MAP.get(orderByDirection)
								+ Trips.TRIPS_FIND_CRITERIA_INDEX_QUERY_PART4)
				.setParameter(Trips.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Trips.ROUTE_NAME_NAME, routeName)
				.setParameter(Trips.REM_SEAT_CLASS_1_NAME, remSeatClass1)
				.setParameter(Trips.REM_SEAT_CLASS_2_NAME, remSeatClass2)
				.setParameter(Trips.REM_SEAT_CLASS_3_NAME, remSeatClass3)
				.setParameter(Trips.MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(Trips.MAX_DATE_NAME, maxDate, TemporalType.DATE)
				.setParameter(Trips.PASSED_ID_NAME, knownElement.getTripId());
		setPassValue(q, orderByParam, knownElement);
		return (long) find(q);

	}

	public List<Trips> getTripsListCriteria(int firstElement, int count,
			String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection) {
		Query query = entityManager
				.createQuery(
						Trips.TRIPS_FIND_BY_CRITERIA_QUERY + orderByParam + " "
								+ orderByDirection + Trips.GENERAL_ORDER_PART
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
	public Trips getTripById(int id) {
		Query query = entityManager.createNamedQuery(Trips.TRIPS_FIND_BY_ID)
				.setParameter(1, id);
		return (Trips) query.getSingleResult();
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

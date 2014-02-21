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

	/**
	 * Defines the name of transport code parameter
	 */
	public static final String TRANSPORT_CODE_NAME = "transportCode";
	/**
	 * Defines the name of remaining seat class 1 parameter
	 */
	public static final String REM_SEAT_CLASS_1_NAME = "remSeatClass1";
	/**
	 * Defines the name of remaining seat class 2 parameter
	 */
	public static final String REM_SEAT_CLASS_2_NAME = "remSeatClass2";
	/**
	 * Defines the name of remaining seat class 3 parameter
	 */
	public static final String REM_SEAT_CLASS_3_NAME = "remSeatClass3";
	/**
	 * Defines the name of minimum date parameter
	 */
	public static final String MIN_DATE_NAME = "minDate";
	/**
	 * Defines the name of maximum date parameter
	 */
	public static final String MAX_DATE_NAME = "maxDate";
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
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate) {
		return (long) find((Query) entityManager
				.createNamedQuery(Trips.TRIPS_FIND_CRITERIA_COUNT)
				.setParameter(TRANSPORT_CODE_NAME, transportCode)
				.setParameter(REM_SEAT_CLASS_1_NAME, remSeatClass1)
				.setParameter(REM_SEAT_CLASS_2_NAME, remSeatClass2)
				.setParameter(REM_SEAT_CLASS_3_NAME, remSeatClass3)
				.setParameter(MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(MAX_DATE_NAME, maxDate, TemporalType.DATE));
	}

	public List<Trips> getTripsListCriteria(int firstElement, int count,
			String transportCode, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate,
			String orderByParam, String orderByDirection) {

		Query query = entityManager
				.createQuery(
						Trips.TRIPS_FIND_BY_CRITERIA_QUERY + orderByParam + " "
								+ orderByDirection)
				.setParameter(TRANSPORT_CODE_NAME, transportCode)
				.setParameter(REM_SEAT_CLASS_1_NAME, remSeatClass1)
				.setParameter(REM_SEAT_CLASS_2_NAME, remSeatClass2)
				.setParameter(REM_SEAT_CLASS_3_NAME, remSeatClass3)
				.setParameter(MIN_DATE_NAME, minDate, TemporalType.DATE)
				.setParameter(MAX_DATE_NAME, maxDate, TemporalType.DATE)
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

}

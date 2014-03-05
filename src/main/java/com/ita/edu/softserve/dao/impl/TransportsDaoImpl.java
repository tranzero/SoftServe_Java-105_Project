package com.ita.edu.softserve.dao.impl;

import java.sql.Time;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;

/**
 * @author Roman
 */
@Repository("transportsDao")
public class TransportsDaoImpl extends AbstractDAO<Transports> implements
		TransportsDao {

	@Override
	public Class<Transports> getEntityClass() {
		return Transports.class;
	}

	/**
	 * Finds Transport by route id.
	 * 
	 * @param id
	 *            the Id to find object.
	 */
	@Override
	public Transports findByRouteId(int id) {
		Query query = entityManager
				.createNamedQuery(Transports.FIND_BY_ROUTEID).setParameter(1,
						id);

		return (Transports) query.getSingleResult();
	}

	@Override
	public Transports findByCode(String code) {
		Query query = entityManager.createNamedQuery(
				Transports.FIND_BY_TRANSPORTCODE).setParameter(1, code);

		return (Transports) query.getSingleResult();
	}

	/**
	 * Saves a Transport into the Transports table if not exist or updates
	 * existing one.
	 * 
	 * @param entity
	 *            the transport to save or update into Transports table.
	 */
	@Override
	public void saveOrUpdate(final Transports entity) {
		if (entity.getTransportId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	/**
	 * Finds Transport by criteria.
	 * 
	 * @see com.ita.edu.softserve.dao.TransportsDao#getTransportsListByCriteria(int,
	 *      int, java.lang.String, java.sql.Time, java.lang.String,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Double)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsListByCriteria(int firstElement,
			int count, String transportCode, Time time, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price) {

		Query query = entityManager
				.createQuery(Transports.FIND_TRANSPORTS_LIST_BY_CRITERIA_QUERY)
				.setParameter("transportCode", "%" + transportCode + "%")
				.setParameter("startTime", time, TemporalType.TIME)
				.setParameter("routeCode", "%" + routeCode + "%")
				.setParameter("seatclass1", seatClass1)
				.setParameter("seatclass2", seatClass2)
				.setParameter("seatclass3", seatClass3)
				.setParameter("genPrice", price).setFirstResult(firstElement)
				.setMaxResults(count);

		return (List<Transports>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsListForAddTrips(int firstElement,
			int count, String transportCode, String routeName, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String OrderByCriteria, String OrderByDirection) {

		Query query = entityManager
				.createQuery(Transports.FIND_TRANSPORTS_LIST_BY_CRITERIA_QUERY+OrderByCriteria+" "+OrderByDirection)
				.setParameter(Transports.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Transports.ROUTE_NAME_NAME, routeName)
				.setParameter(Transports.ROUTE_CODE_NAME, routeCode)
				.setParameter(Transports.SEAT_CLASS1_NAME , seatClass1)
				.setParameter(Transports.SEAT_CLASS2_NAME, seatClass2)
				.setParameter(Transports.SEAT_CLASS3_NAME, seatClass3)
				.setParameter(Transports.GEN_PRICE_NAME , price).setFirstResult(firstElement)
				.setMaxResults(count);

		return (List<Transports>) query.getResultList();
	}
	
	@Override
	public long getTransportsListForAddTripsCount(String transportCode,
			String routeName, String routeCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price) {

		return (long) find((Query) entityManager
				.createNamedQuery(
						Transports.FIND_TRANSPORTS_LIST_BY_CRITERIA_COUNT)
				.setParameter(Transports.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Transports.ROUTE_NAME_NAME, routeName)
				.setParameter(Transports.ROUTE_CODE_NAME, routeCode)
				.setParameter(Transports.SEAT_CLASS1_NAME , seatClass1)
				.setParameter(Transports.SEAT_CLASS2_NAME, seatClass2)
				.setParameter(Transports.SEAT_CLASS3_NAME, seatClass3)
				.setParameter(Transports.GEN_PRICE_NAME , price));
	}
	
	
	@Override
	public long getTransportsListByCriteriaCount(String transportCode,
			Time time, String routeCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price) {

		return (long) find((Query) entityManager
				.createNamedQuery(
						Transports.FIND_TRANSPORTS_LIST_BY_CRITERIA_COUNT)
				.setParameter("transportCode", "%" + transportCode + "%")
				.setParameter("startTime", time, TemporalType.TIME)
				.setParameter("routeCode", "%" + routeCode + "%")
				.setParameter("seatclass1", seatClass1)
				.setParameter("seatclass2", seatClass2)
				.setParameter("seatclass3", seatClass3)
				.setParameter("genPrice", price));
	}

	/**
	 * @see com.ita.edu.softserve.dao.TransportsDao#findByDate(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> findByDate(String date) {
		Query query = entityManager.createNamedQuery(Transports.FIND_BY_DATE)
				.setParameter(1, date);

		return (List<Transports>) query.getResultList();
	}

	/**
	 * @see com.ita.edu.softserve.dao.TransportsDao#findByTwoStations(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TransportTravel> findByTwoStations(String stationName1,
			String stationName2) {
		Query query = entityManager
				.createNamedQuery(Transports.FIND_BY_TWO_STATIONS)
				.setParameter(1, "%" + stationName1 + "%")
				.setParameter(2, "%" + stationName2 + "%");

		return (List<TransportTravel>) query.getResultList();
	}

	/**
	 * 
	 * @see com.ita.edu.softserve.dao.TransportsDao#getTransportByTwoStForLimits(java.lang.String,
	 *      java.lang.String, int, int, java.lang.String, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TransportTravel> getTransportByTwoStForLimits(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate, int orderBy) {
		Query query = null;

		if (sDate == null || sDate.equals("")) {
			query = entityManager
					.createNamedQuery(Transports.FIND_BY_TWO_STATIONS)
					.setParameter(1, "%" + stationName1 + "%")
					.setParameter(2, "%" + stationName2 + "%")
					.setFirstResult(firstElement).setMaxResults(count);
		} else {
			query = entityManager
					.createNamedQuery(Transports.FIND_BY_TWO_STATIONS_AND_DATE)
					.setParameter(1, "%" + stationName1 + "%")
					.setParameter(2, "%" + stationName2 + "%")
					.setParameter(3, java.sql.Date.valueOf(sDate))
					.setFirstResult(firstElement).setMaxResults(count);
		}
		/*
		 * if (orderBy == 1) { query = entityManager
		 * .createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_LNAME)
		 * .setParameter(1, stationName1).setParameter(2, stationName2)
		 * .setFirstResult(firstElement).setMaxResults(count); } else if
		 * (orderBy == 2) { query = entityManager
		 * .createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_TCODE)
		 * .setParameter(1, stationName1).setParameter(2, stationName2)
		 * .setFirstResult(firstElement).setMaxResults(count); } else if
		 * (orderBy == 3) { query = entityManager
		 * .createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_DEP)
		 * .setParameter(1, stationName1).setParameter(2, stationName2)
		 * .setFirstResult(firstElement).setMaxResults(count); } else if
		 * (orderBy == 4) { query = entityManager
		 * .createNamedQuery(Transports.FIND_BY_TS_ORDER_BY_DURATION)
		 * .setParameter(1, stationName1).setParameter(2, stationName2)
		 * .setFirstResult(firstElement).setMaxResults(count); } else {
		 */
		// }

		return (List<TransportTravel>) query.getResultList();
	}

	/**
	 * @see com.ita.edu.softserve.dao.TransportsDao#getTransportByTwoStListCount(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2) {
		// this will be changed soon
		return findByTwoStations(stationName1, stationName2).size();
	}

	/**
	 * @see com.ita.edu.softserve.dao.TransportsDao#getTransportsForLimits(int,
	 *      int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsForLimits(int firstElement, int count) {
		Query query = entityManager
				.createNamedQuery(Transports.TRANSPORTS_FIND_ALL)
				.setFirstResult(firstElement).setMaxResults(count);

		return (List<Transports>) query.getResultList();
	}

	/**
	 * @see com.ita.edu.softserve.dao.TransportsDao#getTransportsListCount()
	 */
	@Override
	public long getTransportsListCount() {
		return (long) find((Query) entityManager
				.createNamedQuery(Transports.TRANSPORTS_FIND_COUNT));
	}

}

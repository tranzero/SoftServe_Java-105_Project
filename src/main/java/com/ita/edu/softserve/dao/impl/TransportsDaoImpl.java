package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

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

	/**
	 * @return
	 */
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

	/**
	 * @param code
	 * @return
	 */
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
	public void saveOrUpdate(Transports entity) {
		if (entity.getTransportId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	/*---------------------------for transport paging sorting filtering------------------------------------------*/

	/**
	 * Finds Transport by criteria.
	 */
	/**
	 * @param firstElement
	 * @param count
	 * @param transportCode
	 * @param routeName
	 * @param routeCode
	 * @param seatClass1
	 * @param seatClass2
	 * @param seatClass3
	 * @param price
	 * @param OrderByCriteria
	 * @param OrderByDirection
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsList(int firstElement, int count,
			String transportCode, String routeName, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String OrderByCriteria, String OrderByDirection) {

		Query query = entityManager
				.createQuery(
						Transports.FIND_TRANSPORTS_QUERY + OrderByCriteria
								+ " " + OrderByDirection)
				.setParameter(Transports.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Transports.ROUTE_NAME_NAME, routeName)
				.setParameter(Transports.ROUTE_CODE_NAME, routeCode)
				.setParameter(Transports.SEAT_CLASS1_NAME, seatClass1)
				.setParameter(Transports.SEAT_CLASS2_NAME, seatClass2)
				.setParameter(Transports.SEAT_CLASS3_NAME, seatClass3)
				.setParameter(Transports.GEN_PRICE_NAME, price)
				.setFirstResult(firstElement).setMaxResults(count);

		return (List<Transports>) query.getResultList();
	}

	/**
	 * @param transportCode
	 * @param routeName
	 * @param routeCode
	 * @param seatClass1
	 * @param seatClass2
	 * @param seatClass3
	 * @param price
	 * @return
	 */
	@Override
	public long getTransportsListCount(String transportCode, String routeName,
			String routeCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price) {

		return (long) find((Query) entityManager
				.createNamedQuery(Transports.FIND_TRANSPORTS_COUNT)
				.setParameter(Transports.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Transports.ROUTE_NAME_NAME, routeName)
				.setParameter(Transports.ROUTE_CODE_NAME, routeCode)
				.setParameter(Transports.SEAT_CLASS1_NAME, seatClass1)
				.setParameter(Transports.SEAT_CLASS2_NAME, seatClass2)
				.setParameter(Transports.SEAT_CLASS3_NAME, seatClass3)
				.setParameter(Transports.GEN_PRICE_NAME, price));
	}

	/*--------------------------END-for transport paging sorting filtering------------------------------------------*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsListForAddTrips(int firstElement,
			int count, String transportCode, String routeName,
			String routeCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String OrderByCriteria,
			String OrderByDirection) {

		Query query = entityManager
				.createQuery(
						Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_QUERY
								+ OrderByCriteria + " " + OrderByDirection)
				.setParameter(Transports.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Transports.ROUTE_NAME_NAME, routeName)
				.setParameter(Transports.ROUTE_CODE_NAME, routeCode)
				.setParameter(Transports.SEAT_CLASS1_NAME, seatClass1)
				.setParameter(Transports.SEAT_CLASS2_NAME, seatClass2)
				.setParameter(Transports.SEAT_CLASS3_NAME, seatClass3)
				.setParameter(Transports.GEN_PRICE_NAME, price)
				.setFirstResult(firstElement).setMaxResults(count);

		return (List<Transports>) query.getResultList();
	}

	@Override
	public long getTransportsListForAddTripsCount(String transportCode,
			String routeName, String routeCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price) {

		return (long) find((Query) entityManager
				.createNamedQuery(
						Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_COUNT)
				.setParameter(Transports.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Transports.ROUTE_NAME_NAME, routeName)
				.setParameter(Transports.ROUTE_CODE_NAME, routeCode)
				.setParameter(Transports.SEAT_CLASS1_NAME, seatClass1)
				.setParameter(Transports.SEAT_CLASS2_NAME, seatClass2)
				.setParameter(Transports.SEAT_CLASS3_NAME, seatClass3)
				.setParameter(Transports.GEN_PRICE_NAME, price));
	}

	/**
	 * @param date
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> findByDate(String date) {
		Query query = entityManager.createNamedQuery(Transports.FIND_BY_DATE)
				.setParameter(1, date);

		return (List<Transports>) query.getResultList();
	}

	/**
	 * Returns transport by two stations including stops
	 * at these stations.
	 * 
	 * @param stationName1 - name of the first station
	 * @param stationName2 - name of the second station
	 * @return <code>List</code> of <code>transportTravel</code>
	 * 		   which contains transport and some info about trip
	 * 		   duration, arrival time, departure time
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
	 * Returns transport by two stations (limited number of records)
	 * including stops at these stations.
	 * 
	 * @param stationName1 - name of the first station
	 * @param stationName2 - name of the second station
	 * @param firstElement - element from what to start 
	 * @param count - number of elements to return
	 * @param sDate - date of trip
	 * 
	 * @return <code>List</code> of <code>transportTravel</code>
	 * 		   which contains transport and some info about trip
	 * 		   duration, arrival time, departure time
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TransportTravel> getTransportByTwoStForLimits(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate) {
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

		return (List<TransportTravel>) query.getResultList();
	}

	/**
	 * Returns number of transport elements that go through
	 * two stations including stops 
	 * 
	 * @param stationName1 - name of the first station
	 * @param stationName2 - name of the second station
	 * 
	 * @return number of transport elements that go through
	 * 		   two stations including stops
	 */
	@Override
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2) {
		return findByTwoStations(stationName1, stationName2).size();
	}

}

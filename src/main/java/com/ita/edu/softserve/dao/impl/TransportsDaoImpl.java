package com.ita.edu.softserve.dao.impl;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.impl.TransportTravel;
import com.ita.edu.softserve.validationcontainers.impl.TransportForAddTripsCriteriaContainerImpl;


/**
 * @author Roman
 */
@Repository("transportsDao")
public class TransportsDaoImpl extends AbstractDAO<Transports> implements
		TransportsDao {

	/**
	 * Map for defining sorting order direction in search page for element query
	 */
	private static Map<String, String> SORT_DIRECTION_MAP;

	/**
	 * Map for defining sorting order in search page for element query
	 */
	private static Map<String, Integer> SORT_ORDER_MAP;

	static {
		String[] orderByColumns = Arrays
				.copyOf(TransportForAddTripsCriteriaContainerImpl.ORDER_BY_COLUMNS,
						TransportForAddTripsCriteriaContainerImpl.ORDER_BY_COLUMNS.length);
		SORT_DIRECTION_MAP = new HashMap<String, String>();
		SORT_DIRECTION_MAP.put("ASC", " < ");
		SORT_DIRECTION_MAP.put("DESC", " > ");
		SORT_ORDER_MAP = new HashMap<String, Integer>();
		for (int i = 0; i < orderByColumns.length; i++) {
			SORT_ORDER_MAP.put(orderByColumns[i], i);
		}
	}

	
	private void setPassValue(Query q, String orderByCriteria, Transports knownElement){
		Map<Integer, Object> sortElementMap = new HashMap<Integer, Object>();
		int i = 0;
		int timeElement;
		sortElementMap.put(i++, knownElement.getTransportCode());
		sortElementMap.put(i++, knownElement.getRoutes().getRouteCode());
		sortElementMap.put(i++, knownElement.getRoutes().getRouteName());
		sortElementMap.put(i++, knownElement.getSeatclass1());
		sortElementMap.put(i++, knownElement.getSeatclass2());
		sortElementMap.put(i++, knownElement.getSeatclass3());
		sortElementMap.put(i++, knownElement.getGenPrice());
		timeElement=i;
		sortElementMap.put(i++, knownElement.getStartTime());
		if (SORT_ORDER_MAP.get(orderByCriteria) == timeElement) {
			q.setParameter(
					Transports.PASSED_VALUE_NAME,
					(Time) sortElementMap.get(SORT_ORDER_MAP.get(orderByCriteria)),
					TemporalType.TIME);
		} else {
			q.setParameter(
					Transports.PASSED_VALUE_NAME,
					sortElementMap.get(SORT_ORDER_MAP.get(orderByCriteria)));
		}
	}
	/**
	 * @return the type Class of <code>Transports</code> object class.
	 */
	@Override
	public Class<Transports> getEntityClass() {
		return Transports.class;
	}

	/**
	 * Finds <code>Transports</code> by route id.
	 * 
	 * @param id
	 *            the Id to find object by
	 * @return the <code>Transports</code> object
	 */
	@Override
	public Transports findByRouteId(int id) {
		Query query = entityManager
				.createNamedQuery(Transports.FIND_BY_ROUTEID).setParameter(1,
						id);

		return (Transports) query.getSingleResult();
	}

	/**
	 * Finds <code>Transports</code> by route code.
	 * 
	 * @param code
	 *            the code to find object by
	 * @return the <code>Transports</code> object if exist
	 */
	@Override
	public Transports findByCode(String code) {
		Query query = entityManager.createNamedQuery(
				Transports.FIND_BY_TRANSPORTCODE).setParameter(1, code);

		return (Transports) query.getSingleResult();
	}

	/**
	 * If <code>Transports</code> ID is<code>null</code> saves the
	 * <code>Transports</code> into the Transports table otherwise updates
	 * existing one.
	 * 
	 * @param transports
	 *            the <code>Transports</code> object
	 */
	@Override
	public void saveOrUpdate(Transports transports) {
		if (transports.getTransportId() == null) {
			entityManager.persist(transports);
		} else {
			entityManager.merge(transports);
		}
	}

	/*---------------------------for transport paging sorting filtering------------------------------------------*/

	/**
	 * Finds <code>Transports</code> by parameter.
	 * 
	 * @param firstElement
	 *            the first element
	 * @param count
	 *            the capacity of result list
	 * @param transportCode
	 *            the transports code for matching
	 * @param routeName
	 *            the route name to set for matching
	 * @param routeCode
	 *            the route code to set for matching
	 * @param seatClass1
	 *            the seat class 1 to set for matching
	 * @param seatClass2
	 *            the seat class 2 to set for matching
	 * @param seatClass3
	 *            the seat class 3 to set for matching
	 * @param price
	 *            the price to set for matching
	 * @param orderByCriteria
	 *            the filter criteria
	 * @param orderByDirection
	 *            the sorting direction
	 * @return the List of <code>Transports</code> objects
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsList(int firstElement, int count,
			String transportCode, String routeName, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String orderByCriteria, String orderByDirection) {

		Query query = entityManager
				.createQuery(Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_QUERY
						+ orderByCriteria + " "	+ orderByDirection								
						+ Transports.GENERAL_ORDER_PART	+ orderByDirection)
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
	 * Finds the number of <code>Transports</code> by parameters and return it quantity.
	 * @param transportCode
	 *            the transports code for matching
	 * @param routeName
	 *            the route name to set for matching
	 * @param routeCode
	 *            the route code to set for matching
	 * @param seatClass1
	 *            the seat class 1 to set for matching
	 * @param seatClass2
	 *            the seat class 2 to set for matching
	 * @param seatClass3
	 *            the seat class 3 to set for matching
	 * @param price
	 *            the price to set for matching
	 * @return the quantity of <code>Transports</code> objects
	 */
	@Override
	public long getTransportsListCount(String transportCode, String routeName,
			String routeCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price) {

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

	/*--------------------------END-for transport paging sorting filtering------------------------------------------*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Transports> getTransportsListForAddTrips(int firstElement,
			int count, String transportCode, String routeName,
			String routeCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection) {

		Query query = entityManager
				.createQuery(
						Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_QUERY
								+ orderByCriteria
								+ " "
								+ orderByDirection
								+ Transports.GENERAL_ORDER_PART
								+ orderByDirection)
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

	@Override
	public long getTransportsListForAddTripsIndex(String transportCode,
			String routeName, String routeCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price,
			String orderByCriteria, String orderByDirection,
			Transports knownElement) {
		Query q = (Query) entityManager
				.createNamedQuery(
						Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART1
								+ orderByCriteria
								+ SORT_DIRECTION_MAP.get(orderByCriteria)
								+ Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART2
								+ orderByCriteria
								+ Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART3
								+ SORT_DIRECTION_MAP.get(orderByCriteria)
								+ Transports.FIND_TRANSPORTS_FOR_ADD_TRIPS_INDEX_QUERY_PART4)
				.setParameter(Transports.TRANSPORT_CODE_NAME, transportCode)
				.setParameter(Transports.ROUTE_NAME_NAME, routeName)
				.setParameter(Transports.ROUTE_CODE_NAME, routeCode)
				.setParameter(Transports.SEAT_CLASS1_NAME, seatClass1)
				.setParameter(Transports.SEAT_CLASS2_NAME, seatClass2)
				.setParameter(Transports.SEAT_CLASS3_NAME, seatClass3)
				.setParameter(Transports.GEN_PRICE_NAME, price);
		setPassValue(q, orderByCriteria, knownElement);
		return (long) find(q);
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
	 * Returns transport by two stations including stops at these stations.
	 * 
	 * @param stationName1
	 *            - name of the first station
	 * @param stationName2
	 *            - name of the second station
	 * @return <code>List</code> of <code>transportTravel</code> which contains
	 *         transport and some info about trip duration, arrival time,
	 *         departure time
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
	 * Returns transport by two stations (limited number of records) including
	 * stops at these stations.
	 * 
	 * @param stationName1
	 *            - name of the first station
	 * @param stationName2
	 *            - name of the second station
	 * @param firstElement
	 *            - element from what to start
	 * @param count
	 *            - number of elements to return
	 * @param sDate
	 *            - date of trip
	 * 
	 * @return <code>List</code> of <code>transportTravel</code> which contains
	 *         transport and some info about trip duration, arrival time,
	 *         departure time
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
	 * Returns number of transport elements that go through two stations
	 * including stops
	 * 
	 * @param stationName1
	 *            - name of the first station
	 * @param stationName2
	 *            - name of the second station
	 * 
	 * @return number of transport elements that go through two stations
	 *         including stops
	 */
	@Override
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2) {
		return findByTwoStations(stationName1, stationName2).size();
	}

}

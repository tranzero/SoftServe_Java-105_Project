package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;

/**
 * @author Roman
 */
public interface TransportsDao extends AbstractDAOIface<Transports> {

	/**
	 * Finds <code>Transports</code> by route id.
	 * 
	 * @param id
	 *            the Id to find object by
	 * @return the <code>Transports</code> object
	 */
	Transports findByRouteId(int id);

	/**
	 * Finds <code>Transports</code> by route code.
	 * 
	 * @param code
	 *            the code to find object by
	 * @return the <code>Transports</code> object if exist
	 */
	Transports findByCode(String code);

	/**
	 * If <code>Transports</code> ID is<code>null</code> saves the
	 * <code>Transports</code> into the Transports table otherwise updates
	 * existing one.
	 * 
	 * @param transports
	 *            the <code>Transports</code> object
	 */
	void saveOrUpdate(Transports transports);

	/**
	 * Removes <code>Transports</code> by Id from database.
	 * 
	 * @param id the Transports Id
	 */
	void removeById(Integer id);

	/*------------------------------------------------------------------------------------------*/

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
	List<Transports> getTransportsList(int firstElement, int count,
			String transportCode, String routeName, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String OrderByCriteria, String OrderByDirection);

	/**
	 * 
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
	long getTransportsListCount(String transportCode, String routeName,
			String routeCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price);

	/*------------------------------------------------------------------------------------------*/

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
	public List<TransportTravel> findByTwoStations(String stationName1,
			String stationName2);

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
	public List<TransportTravel> getTransportByTwoStForLimits(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate);

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
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2);

	/**
	 * @param date
	 * @return
	 */
	List<Transports> findByDate(String date);

	List<Transports> getTransportsListForAddTrips(int firstElement, int count,
			String transportCode, String routeName, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String OrderByCriteria, String OrderByDirection);

	long getTransportsListForAddTripsCount(String transportCode,
			String routeName, String routeCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price);

	long getTransportsListForAddTripsIndex(String transportCode,
			String routeName, String routeCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price,
			String OrderByCriteria, String OrderByDirection,
			Transports knownElement);
}

package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;

/**
 * @author Roman
 */
public interface TransportsDao extends AbstractDAOIface<Transports> {

	/**
	 * Finds Transport by route id.
	 * 
	 * @param id
	 *            the Id to find route by.
	 * @return the route with Id from argument.
	 */
	Transports findByRouteId(int id);

	/**
	 * Finds Transport by transport code.
	 * 
	 * @param code
	 *            the code to find transport by.
	 * @return the transport if exist.
	 */
	Transports findByCode(String code);

	/**
	 * Saves a Transport into the Transports table if not exist or updates
	 * existing one.
	 * 
	 * @param entity
	 *            the transport to save or update.
	 */
	void saveOrUpdate(Transports entity);
	/*------------------------------------------------------------------------------------------*/
	
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
	List<Transports> getTransportsList(int firstElement, int count,
			String transportCode, String routeName, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String OrderByCriteria, String OrderByDirection);

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
	long getTransportsListCount(String transportCode, String routeName,
			String routeCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price);
	/*------------------------------------------------------------------------------------------*/

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
	public List<TransportTravel> findByTwoStations(String stationName1,
			String stationName2);

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
	public List<TransportTravel> getTransportByTwoStForLimits(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate);

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
	
}

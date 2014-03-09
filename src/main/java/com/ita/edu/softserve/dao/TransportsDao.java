package com.ita.edu.softserve.dao;

import java.sql.Time;
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

	/**
	 * @param firstElement
	 * @param count
	 * @return
	 */
	List<Transports> getTransportsForLimits(int firstElement, int count);

	/**
	 * @return
	 */
	long getTransportsListCount();

	/**
	 * Get transport by two stations.
	 * 
	 * @param stationName1
	 * @param stationName2
	 * @return
	 */
	public List<TransportTravel> findByTwoStations(String stationName1,
			String stationName2);

	/**
	 * @param stationName1
	 * @param stationName2
	 * @param firstElement
	 * @param count
	 * @param sDate
	 * @param orderBy
	 * @return
	 */
	public List<TransportTravel> getTransportByTwoStForLimits(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate, int orderBy);

	/**
	 * @param stationName1
	 * @param stationName2
	 * @return
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
	/*------------------------------------------------------------------------------------------*/

	List<Transports> getTransportsList(int firstElement, int count,
			String transportCode, String routeName, String routeCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String OrderByCriteria, String OrderByDirection);

	long getTransportsListCount(String transportCode, String routeName,
			String routeCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price);
	
}

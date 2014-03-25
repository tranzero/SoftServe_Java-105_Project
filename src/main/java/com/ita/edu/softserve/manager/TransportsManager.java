package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.validation.Errors;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;

/**
 * @author Roman
 */
public interface TransportsManager extends BaseManager {

	/**
	 * Finds the <code>Transports</code> by Id.
	 * 
	 * @param id
	 *            the Id to find <code>Transports</code>.
	 * @return the <code>Transports</code> fond by Id.
	 */
	Transports findTransportsById(int id);

	/**
	 * Finds Transports by transport code.
	 * 
	 * @param code
	 *            the transport code to find.
	 * @return the Transports fond by transport code.
	 */
	Transports findTransportsByCode(String code);

	/**
	 * Saves <code>Transports</code> in database.
	 * 
	 * @param transports
	 *            the array of Transports to save.
	 */
	void saveTransports(Transports transports);

	/**
	 * Removes <code>Transports</code> from database.
	 * 
	 * @param transports
	 *            the array of Transports to delete.
	 */
	void removeTransports(Transports transports);

	/**
	 * Removes <code>Transports</code> by Id from database.
	 * 
	 * @param transportId
	 *            the Transports to delete by Id.
	 */
	void removeTransportById(Integer transportId);

	/**
	 * Updates <code>Transports</code> table and get list of all Transports.
	 * 
	 * @param transports
	 *            the array of Transports to update.
	 * @return the List of Transports.
	 */
	List<Transports> updateTransports(Transports transports);

	/**
	 * Gets the list of all <code>Transports</code>.
	 * 
	 * @return the List of Transports.
	 */
	List<Transports> getAllTransports();

	/**
	 * Saves the <code>Transports</code> object to database if not exist or
	 * updates it. <br/>
	 * <br/>
	 * If <code>transportId</code> is <code>null</code> than it creates new
	 * transport object otherwise it finds existing one in database and updates
	 * it.
	 * 
	 * @param transports
	 *            the Transports to add or update.
	 */
	void saveOrUpdateTransport(Transports transports);
	
	/**
	 * Finds out if Transports object exist in database with such transport
	 * code.
	 * 
	 * @param transportId
	 *            the transport ID to check.
	 * @param transportCode
	 *            the transport code to check.
	 * @param error
	 *            the error to register message.
	 */
	public void validateIfTransportExist(Transports transports, Errors error);

	/*---------------------------for transport paging sorting filtering------------------------------------------*/

	/**
	 * @param container
	 * @param transportCriteriaContainer
	 * @return
	 */
	List<Transports> getTransportsListWithContainers(
			PageInfoContainer container,
			TransportsCriteriaContainer transportCriteriaContainer);

	/**
	 * @param transportCriteriaContainer
	 * @return
	 */
	long getTransportsListCountWithContainers(
			TransportsCriteriaContainer transportCriteriaContainer);

	/**
	 * @param transportCode
	 * @param routeName
	 * @param routesCode
	 * @param seatClass1
	 * @param seatClass2
	 * @param seatClass3
	 * @param price
	 * @return
	 */
	long getTransportsListCount(String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price);

	/**
	 * @param firstElement
	 * @param count
	 * @param transportCode
	 * @param routeName
	 * @param routesCode
	 * @param seatClass1
	 * @param seatClass2
	 * @param seatClass3
	 * @param price
	 * @param orderByCriteria
	 * @param orderByDirection
	 * @return
	 */
//	List<Transports> getTransportsList(int firstElement, int count,
//			String transportCode, String routeName, String routesCode,
//			Integer seatClass1, Integer seatClass2, Integer seatClass3,
//			Double price, String orderByCriteria, String orderByDirection);

	/**
	 * @param pageNumber
	 * @param count
	 * @param transportCode
	 * @param routeName
	 * @param routesCode
	 * @param seatClass1
	 * @param seatClass2
	 * @param seatClass3
	 * @param price
	 * @param orderByCriteria
	 * @param orderByDirection
	 * @return
	 */
	List<Transports> getTransportsListWithPaging(int pageNumber, int count,
			String transportCode, String routeName, String routesCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String orderByCriteria, String orderByDirection);

	/**
	 * @param transportCriteriaContainer
	 */
	void validateTransportCriteria(
			TransportsCriteriaContainer transportCriteriaContainer);

	/*--------------------------END-for transport paging sorting filtering------------------------------------------*/

	/**
	 * Returns <code>TransportTravel</code> object, that contains all transport
	 * that goes through two stations
	 * 
	 * @param stationName1
	 *            - name of the first station
	 * @param stationName2
	 *            - name of the second station
	 * 
	 * @return <code>TransportTravel</code>, that contains transport code,
	 *         departure and arrival times, duration
	 */
	List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2);

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
	long getTransportByTwoStListCount(String stationName1, String stationName2);

	/**
	 * Returns transport by two stations (limited number of records) including
	 * stops at these stations.
	 * 
	 * @param stationName1
	 *            - name of the first station
	 * @param stationName2
	 *            - name of the second station
	 * @param pageNumber
	 *            - number of page to get results for
	 * @param count
	 *            - number of elements to return
	 * @param sDate
	 *            - date of trip
	 * 
	 * @return <code>List</code> of <code>transportTravel</code> which contains
	 *         transport and some info about trip duration, arrival time,
	 *         departure time
	 */
	List<TransportTravel> getTransportByTwoStForPage(String stationName1,
			String stationName2, int pageNumber, int count, String sDate);

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
	List<TransportTravel> getTransportByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count, String sDate);

	long getTransportsListForAddTripsCount(String transportCode,
			String routeName, String routesCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price);

	List<Transports> getTransportsListForAddTrips(int firstElement, int count,
			String transportCode, String routeName, String routesCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String orderByCriteria, String orderByDirection);

	List<Transports> getTransportsListForAddTripsWithPaging(int pageNumber,
			int count, String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection);

	void validateTransportForAddTripsCriteria(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer);

	List<Transports> getTransportsListForAddTripsWithContainers(
			PageInfoContainer container,
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer);

	long getTransportsListForAddTripsCountWithContainers(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer);

	long getTransportIndexForAddTrips(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer,
			Transports knownElement, Integer pageSize);

}

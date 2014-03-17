package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportTravel;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;

/**
 * @author Roman
 */
public interface TransportsManager extends BaseManager {

	/**
	 * Finds transports by ID.
	 * 
	 * @param id
	 *            the Id to find transport.
	 * @return the transport fond by Id.
	 */
	Transports findTransportsById(int id);

	/**
	 * Finds transports by transport code.
	 * 
	 * @param code
	 *            the transport code to find.
	 * @return the transport fond by code.
	 */
	Transports findTransportsByCode(String code);

	/**
	 * Saves transports.
	 * 
	 * @param entities
	 *            the array of transports to save.
	 */
	void saveTransports(Transports... entities);

	/**
	 * Removes transports.
	 * 
	 * @param entities
	 *            the array of transports to delete.
	 */
	void removeTransports(Transports... entities);

	/**
	 * Removes transports by ID.
	 * 
	 * @param transportId
	 *            the transport to delete by Id.
	 */
	void removeTransportById(Integer transportId);

	/**
	 * Updates transports.
	 * 
	 * @param entities
	 *            the array of transports to update.
	 * @return the List of Transports.
	 */
	List<Transports> updateTransports(Transports... entities);

	/**
	 * Gets all transports.
	 * 
	 * @return the List of Transports.
	 */
	List<Transports> getAllTransports();

	/**
	 * Saves new a transport into Transports table if not exist otherwise
	 * updates it.
	 * 
	 * @param transport
	 *            the transport to save or update.
	 */
	void saveOrUpdateTransport(Transports transport);

	/*---------------------------for transport paging sorting filtering------------------------------------------*/
	
	/**
	 * @param container
	 * @param transportCriteriaContainer
	 * @return
	 */
	List<Transports> getTransportsListWithContainers(
			PageInfoContainer container,
			TransportCriteriaContainer transportCriteriaContainer);

	/**
	 * @param transportCriteriaContainer
	 * @return
	 */
	long getTransportsListCountWithContainers(
			TransportCriteriaContainer transportCriteriaContainer);
	
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
	List<Transports> getTransportsList(int firstElement, int count,
			String transportCode, String routeName, String routesCode,
			Integer seatClass1, Integer seatClass2, Integer seatClass3,
			Double price, String orderByCriteria, String orderByDirection);

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
			TransportCriteriaContainer transportCriteriaContainer);
	/*--------------------------END-for transport paging sorting filtering------------------------------------------*/
	
	/**
	 * Gets transport by two stations in certain order
	 * 
	 * @param stationName1
	 * @param stationName2
	 * @return
	 */
	List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2);

	/**
	 * @param stationName1
	 * @param stationName2
	 * @return
	 */
	long getTransportByTwoStListCount(String stationName1, String stationName2);

	/**
	 * @param stationName1
	 * @param stationName2
	 * @param pageNumber
	 * @param count
	 * @param sDate
	 * @param orderBy
	 * @return
	 */
	List<TransportTravel> getTransportByTwoStForPage(String stationName1,
			String stationName2, int pageNumber, int count, String sDate,
			int orderBy);

	/**
	 * @param stationName1
	 * @param stationName2
	 * @param firstElement
	 * @param count
	 * @param sDate
	 * @param orderBy
	 * @return
	 */
	List<TransportTravel> getTransportByTwoStForLimit(String stationName1,
			String stationName2, int firstElement, int count, String sDate,
			int orderBy);

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

}

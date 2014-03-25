package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportForAddTripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer;

/**
 * This is transports manager class.
 * 
 * @author Roman
 */
@Service("transportsManager")
public class TransportsManagerImpl implements TransportsManager {

	private static final Logger LOGGER = Logger.getLogger(TransportsManagerImpl.class);

	/**
	 * The simple name of the <code>Transports</code> class as given in
	 * the source code. Returns an empty string if the underlying class is
	 * anonymous.
	 */
	private String entityName = Transports.class.getSimpleName() + " with Id=";

	private final String addMessage = " was added to DB by ";
	private final String removeMessage = " was remove from DB by ";
	private final String wasFoundMessage = " was fond by ";
	private final String wasFoundByCodeMessage = " was fond by code by ";
	private final String wasUpdatedMessage = " was updated by ";
	private final String getAllTransportsMessage = "Seccesfuly get list of Transports";

	private final String findTransportsMessage = "Could not find Transports by ID=";
	private final String findTransportsCodeMessage = "Could not find Transports by code=";
	private final String saveTransportsMessage = "Could not save Transports";
	private final String removeTransportsMessage = "Could not remove Transports";
	private final String removeTransportsByIdMessage = "Could not remove Transport by id=";
	private final String updateTransportsMessage = "Could not update Transports ";
	private final String saveOrUpdateTransportsMessage = "Could not save or update Transports";
	private final String getAllTransportsMessageError = "Could not get list of Transports";
	private final String getTransportsByTwoStationsMessage = "Could not get Transports by two stations";
	
	private static final String TRANSPORT_CODE = "transportCode";
	private static final String TRANSPORT_CODE_EXIST = "transportCode.exist";

	/**
	 * Gets access to Transports DAO.
	 */
	@Autowired
	private TransportsDao transportsDao;

	@Autowired
	private UserNameService userName;

	/**
	 * The constructor without arguments.
	 */
	public TransportsManagerImpl() {
		super();
	}

	/**
	 * Finds the <code>Transports</code> by Id.
	 * 
	 * @param id
	 *            the Id to find <code>Transports</code>.
	 * @return the <code>Transports</code> fond by Id.
	 * @see com.ita.edu.softserve.manager.TransportsManager#findTransportsById(int)
	 */
	// /**
	// * {@inheritDoc}
	// */
	@Transactional(readOnly = true)
	@Override
	public Transports findTransportsById(int id) {
		try {
			Transports transports = transportsDao.findById(id);

			if (transports != null) {
				LOGGER.info(entityName + transports.getTransportId()
						+ wasFoundMessage + userName.getLoggedUsername());
			}

			return transports;

		} catch (RuntimeException e) {
			LOGGER.error(findTransportsMessage + id, e);
			throw e;
		}
	}

	/**
	 * Finds Transports by transport code.
	 * 
	 * @param code
	 *            the transport code to find.
	 * @return the Transports fond by transport code.
	 */
	@Transactional(readOnly = true)
	@Override
	public Transports findTransportsByCode(String code) {
		try {
			Transports transports = transportsDao.findByCode(code);
			LOGGER.info(entityName + transports.getTransportId()
					 + wasFoundByCodeMessage + userName.getLoggedUsername());

			return transports;

		} catch (RuntimeException e) {
			LOGGER.error(findTransportsCodeMessage + code, e);
			
			return null;
		}
	}

	/**
	 * Saves <code>Transports</code> in database.
	 * 
	 * @param transports
	 *            the array of Transports to save.
	 * @see com.ita.edu.softserve.manager.TransportsManager#saveTransports(com.ita.edu.softserve.entity.Transports[])
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveTransports(Transports transports) {
		try {
			transportsDao.save(transports);
			LOGGER.info(entityName + transports.getTransportId() + addMessage
					+ userName.getLoggedUsername());
		} catch (RuntimeException e) {
			LOGGER.error(saveTransportsMessage + transports.getTransportId(), e);
			throw e;
		}
	}

	/**
	 * Removes <code>Transports</code> from database.
	 * 
	 * @param transports
	 *            the array of Transports to delete.
	 */
	@Transactional(readOnly = false)
	@Override
	public void removeTransports(Transports transports) {
		try {
			transportsDao.remove(transports);
			LOGGER.info(entityName + transports.getTransportId()
					+ removeMessage + userName.getLoggedUsername());
		} catch (RuntimeException e) {
			LOGGER.error(removeTransportsMessage + transports.getTransportId(), e);
			throw e;
		}
	}

	/**
	 * Removes <code>Transports</code> by Id from database.
	 * 
	 * @param transportId
	 *            the Transports to delete by Id.
	 */
	@Transactional
	@Override
	public void removeTransportById(Integer transportId) {
		Transports transports = null;

		try {
			transports = (Transports) transportsDao.findById(transportId);
			LOGGER.info(entityName + transports.getTransportId()
					+ wasFoundMessage + userName.getLoggedUsername());

			transportsDao.remove(transports);
			LOGGER.info(entityName + transports.getTransportId()
					+ removeMessage + userName.getLoggedUsername());

		} catch (RuntimeException e) {
			LOGGER.error(
					removeTransportsByIdMessage + transports.getTransportId(),
					e);
			throw e;
		}
	}

	/**
	 * Updates <code>Transports</code> table and get list of all Transports.
	 * 
	 * @param transports
	 *            the array of Transports to update.
	 * @return the List of Transports.
	 */
	@Transactional
	@Override
	public List<Transports> updateTransports(Transports transports) {
		try {
			List<Transports> update = transportsDao.update(transports);
			LOGGER.info(entityName + transports.getTransportId()
					+ wasUpdatedMessage + userName.getLoggedUsername());

			return update;
		} catch (RuntimeException e) {
			LOGGER.error(updateTransportsMessage, e);
			throw e;
		}
	}

	/**
	 * Gets the list of all <code>Transports</code>.
	 * 
	 * @return the List of Transports.
	 */
	@Transactional
	@Override
	public List<Transports> getAllTransports() {
		try {
			List<Transports> allEntities = transportsDao.getAllEntities();
			LOGGER.info(getAllTransportsMessage);

			return allEntities;
		} catch (RuntimeException e) {
			LOGGER.error(getAllTransportsMessageError, e);
			throw e;
		}
	}

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
	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateTransport(Transports transports) {
		Integer id = transports.getTransportId();

		try {
			transportsDao.saveOrUpdate(transports);

			if (id == null) {
				LOGGER.info(entityName + id + addMessage
						+ userName.getLoggedUsername());

			} else {
				LOGGER.info(entityName + id + wasUpdatedMessage
						+ userName.getLoggedUsername());
			}
		} catch (RuntimeException e) {
			LOGGER.error(saveOrUpdateTransportsMessage, e);
			throw e;
		}
	}

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
	@Override
	public void validateIfTransportExist(Transports transports, Errors error) {

		if ((transports.getTransportCode() != null) && (!transports.getTransportCode().isEmpty())) {
			Transports transport = null;

			try {
				transport = transportsDao.findByCode(transports.getTransportCode());

				if ((transport.getTransportId()).equals(transports.getTransportId())) {
					return;

				} else {
					error.rejectValue(TRANSPORT_CODE, TRANSPORT_CODE_EXIST);
				}

			} catch (RuntimeException e) {
			}
		}
	}

	/*---------------------------for transport paging, sorting, filtering------------------------------------------*/
	/**
	 * 
	 */
	@Override
	public void validateTransportCriteria(
			TransportsCriteriaContainer transportCriteriaContainer) {

		StaticValidator.validateTransportCriteria(transportCriteriaContainer);
	}

	@Override
	public List<Transports> getTransportsListWithContainers(
			PageInfoContainer container,
			TransportsCriteriaContainer transportCriteriaContainer) {

		return getTransportsListWithPaging(container.getPageNumber(),
				container.getResultsPerPage(),
				transportCriteriaContainer.getTransportCode(),
				transportCriteriaContainer.getRouteName(),
				transportCriteriaContainer.getRoutesCode(),
				transportCriteriaContainer.getSeatClass1(),
				transportCriteriaContainer.getSeatClass2(),
				transportCriteriaContainer.getSeatClass3(),
				transportCriteriaContainer.getPrice(),
				transportCriteriaContainer.getOrderByCriteria(),
				transportCriteriaContainer.getOrderByDirection());
	}

	@Override
	public long getTransportsListCountWithContainers(
			TransportsCriteriaContainer transportCriteriaContainer) {

		return getTransportsListCount(
				transportCriteriaContainer.getTransportCode(),
				transportCriteriaContainer.getRouteName(),
				transportCriteriaContainer.getRoutesCode(),
				transportCriteriaContainer.getSeatClass1(),
				transportCriteriaContainer.getSeatClass2(),
				transportCriteriaContainer.getSeatClass3(),
				transportCriteriaContainer.getPrice());
	}

	@Transactional(readOnly = true)
	@Override
	public long getTransportsListCount(String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price) {

		return transportsDao.getTransportsListCount("%" + transportCode + "%",
				"%" + routeName + "%", "%" + routesCode + "%", seatClass1,
				seatClass2, seatClass3, price);
	}

//	@Transactional(readOnly = true)
//	@Override
//	public List<Transports> getTransportsList(int firstElement, int count,
//			String transportCode, String routeName, String routesCode,
//			Integer seatClass1, Integer seatClass2, Integer seatClass3,
//			Double price, String orderByCriteria, String orderByDirection) {
//
//		return transportsDao.getTransportsList(firstElement, count, "%"
//				+ transportCode + "%", "%" + routeName + "%", "%" + routesCode
//				+ "%", seatClass1, seatClass2, seatClass3, price,
//				orderByCriteria, orderByDirection);
//	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsListWithPaging(int pageNumber,
			int count, String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection) {

		return transportsDao.getTransportsList((pageNumber - 1) * count, count,
				"%" + transportCode + "%", "%" + routeName + "%", "%"
						+ routesCode + "%", seatClass1, seatClass2, seatClass3,
				price, orderByCriteria, orderByDirection);
	}

	/*--------------------------END-for transport paging, sorting, filtering------------------------------------------*/

	@Override
	public void validateTransportForAddTripsCriteria(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer) {
		StaticValidator
				.validateTransportForAddTripsCriteria(transportForAddTripsCriteriaContainer);
	}

	@Override
	public List<Transports> getTransportsListForAddTripsWithContainers(
			PageInfoContainer container,
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer) {

		return getTransportsListForAddTripsWithPaging(
				container.getPageNumber(), container.getResultsPerPage(),
				transportForAddTripsCriteriaContainer.getTransportCode(),
				transportForAddTripsCriteriaContainer.getRouteName(),
				transportForAddTripsCriteriaContainer.getRoutesCode(),
				transportForAddTripsCriteriaContainer.getSeatClass1(),
				transportForAddTripsCriteriaContainer.getSeatClass2(),
				transportForAddTripsCriteriaContainer.getSeatClass3(),
				transportForAddTripsCriteriaContainer.getPrice(),
				transportForAddTripsCriteriaContainer.getOrderByCriteria(),
				transportForAddTripsCriteriaContainer.getOrderByDirection());
	}

	@Override
	public long getTransportIndexForAddTrips(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer,
			Transports knownElement, Integer pageSize) {
		long result;

		result = transportsDao.getTransportsListForAddTripsIndex("%"+transportForAddTripsCriteriaContainer.getTransportCode()+"%",
				"%"+transportForAddTripsCriteriaContainer.getRouteName()+"%",
				"%"+transportForAddTripsCriteriaContainer.getRoutesCode()+"%",
				transportForAddTripsCriteriaContainer.getSeatClass1(),
				transportForAddTripsCriteriaContainer.getSeatClass2(),
				transportForAddTripsCriteriaContainer.getSeatClass3(),
				transportForAddTripsCriteriaContainer.getPrice(),
				transportForAddTripsCriteriaContainer.getOrderByCriteria(),
				transportForAddTripsCriteriaContainer.getOrderByDirection(),
				knownElement);
		return (result / pageSize) + 1;
	}

	@Override
	public long getTransportsListForAddTripsCountWithContainers(
			TransportForAddTripsCriteriaContainer transportForAddTripsCriteriaContainer) {
		return getTransportsListForAddTripsCount(
				transportForAddTripsCriteriaContainer.getTransportCode(),
				transportForAddTripsCriteriaContainer.getRouteName(),
				transportForAddTripsCriteriaContainer.getRoutesCode(),
				transportForAddTripsCriteriaContainer.getSeatClass1(),
				transportForAddTripsCriteriaContainer.getSeatClass2(),
				transportForAddTripsCriteriaContainer.getSeatClass3(),
				transportForAddTripsCriteriaContainer.getPrice());
	}

	@Transactional(readOnly = true)
	@Override
	public long getTransportsListForAddTripsCount(String transportCode,
			String routeName, String routesCode, Integer seatClass1,
			Integer seatClass2, Integer seatClass3, Double price) {

		return transportsDao.getTransportsListForAddTripsCount("%"
				+ transportCode + "%", "%" + routeName + "%", "%" + routesCode
				+ "%", seatClass1, seatClass2, seatClass3, price);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsListForAddTrips(int firstElement,
			int count, String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection) {

		return transportsDao.getTransportsListForAddTrips(firstElement, count,
				"%" + transportCode + "%", "%" + routeName + "%", "%"
						+ routesCode + "%", seatClass1, seatClass2, seatClass3,
				price, orderByCriteria, orderByDirection);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsListForAddTripsWithPaging(
			int pageNumber, int count, String transportCode, String routeName,
			String routesCode, Integer seatClass1, Integer seatClass2,
			Integer seatClass3, Double price, String orderByCriteria,
			String orderByDirection) {

		return transportsDao.getTransportsListForAddTrips((pageNumber - 1)
				* count, count, "%" + transportCode + "%", "%" + routeName
				+ "%", "%" + routesCode + "%", seatClass1, seatClass2,
				seatClass3, price, orderByCriteria, orderByDirection);
	}

	/**
	 * @return the instance of TransportsManager.
	 */
	public static TransportsManager getInstance() {
		return ManagerFactory.getManager(TransportsManager.class);
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
	@Transactional(readOnly = true)
	@Override
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2) {

		return transportsDao.getTransportByTwoStListCount(stationName1,
				stationName2);
	}

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
	@Transactional(readOnly = true)
	@Override
	public List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2) {
		// Results are stored here
		List<TransportTravel> transportTravel = null;

		try {
			transportTravel = transportsDao.findByTwoStations(stationName1,
					stationName2);
		} catch (RuntimeException e) {
			LOGGER.error(getTransportsByTwoStationsMessage, e);
			throw e;
		}

		return transportTravel;
	}

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
	@Transactional(readOnly = true)
	@Override
	public List<TransportTravel> getTransportByTwoStForPage(
			String stationName1, String stationName2, int pageNumber,
			int count, String sDate) {

		return getTransportByTwoStForLimit(stationName1, stationName2,
				(pageNumber - 1) * count, count, sDate);
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
	@Transactional(readOnly = true)
	@Override
	public List<TransportTravel> getTransportByTwoStForLimit(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate) {

		return transportsDao.getTransportByTwoStForLimits(stationName1,
				stationName2, firstElement, count, sDate);
	}

}

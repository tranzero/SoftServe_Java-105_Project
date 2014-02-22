package com.ita.edu.softserve.manager.impl;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.exception.TransprtsManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.TransportsManager;

/**
 * This is transports manager class.
 * 
 * @author Roman
 * 
 */
@Service("transportsManager")
public class TransportsManagerImpl implements TransportsManager {

	private static final Logger LOGGER = Logger
			.getLogger(TransportsManagerImpl.class);

	private String entityName = Transports.class.getSimpleName().concat(
			" with id=");

	private String addMessage = " was added to DB";
	private String removeMessage = " was remove from DB by ";
	private String changeMsg = " was change in DB by ";

	private final String findTransportsMessage = "Could not find Transport List";
	private final String saveTransportMessage = "Could not save Transports";
	private final String removeTransportMessage = "Could not remove Transport";
	private final String removeTransportByIdMessage = "Could not remove Transport by id ";
	private final String updateTransportMessage = "Could not update Transport";
	private final String saveOrUpdateTransportMessage = "Could not save or update Transports";
	private final String getAllTransportsMessage = "Could not get list of Transport";
	private final String getTransportByTwoStationsMessage = "Could not get Transport by two stations";

	/**
	 * Gets access to Transports DAO.
	 */
	@Autowired
	public TransportsDao transportsDao;

	/**
	 * Gets access to Routes DAO.
	 */
	@Autowired
	private RoutesDAO routesDao;

	/**
	 * Constructor without arguments.
	 */
	public TransportsManagerImpl() {
		super();
	}

	/**
	 * @return transport found by Id.
	 * 
	 */
	@Transactional(readOnly = true)
	@Override
	public Transports findTransportsById(int id) {
		try {
			return transportsDao.findById(id);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					findTransportsMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Saves Transports in database.
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveTransports(Transports... entities) {
		try {
			transportsDao.save(entities);
			LOGGER.info(entityName + addMessage);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					saveTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Removes Transports from database.
	 */
	@Transactional(readOnly = false)
	@Override
	public void removeTransports(Transports... entities) {
		try {
			transportsDao.remove(entities);
			LOGGER.info(entityName + removeMessage);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					removeTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Removes Transport by Id from database.
	 */
	@Transactional
	@Override
	public void removeTransportById(Integer transportId) {
		Transports transport = null;

		try {
			transport = (Transports) transportsDao.findById(transportId);
			LOGGER.info(entityName + transport.getTransportId() + "was fond");

			transportsDao.remove(transport);
			LOGGER.info(entityName + removeMessage);

		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					removeTransportByIdMessage + transport.getTransportId(), e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Updates database and get list of all Transports.
	 * 
	 * @return list of all stations.
	 */
	@Transactional
	@Override
	public List<Transports> updateTransports(Transports... entities) {
		try {
			return transportsDao.update(entities);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					updateTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Gets list of all transports.
	 * 
	 * @return list of all transports.
	 */
	@Transactional
	@Override
	public List<Transports> getAllTransports() {
		try {
			return transportsDao.getAllEntities();
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					getAllTransportsMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Saves Transport object to database if not exist or updates it. <br/>
	 * <br/>
	 * If <code>transportId</code> is <code>null</code> than it creates new
	 * transport object otherwise it finds existing one in database and updates
	 * it.
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateTransport(Integer transportId,
			String transportCode, Time startTime, Routes routes,
			Integer seatclass1, Integer seatclass2, Integer seatclass3,
			Double genprice) {

		Transports transport = null;

		if (transportId == null) {
			transport = new Transports();
			LOGGER.info(entityName + "was created");
		} else {
			try {
				transport = transportsDao.findById(transportId);
				LOGGER.info(entityName + transport.getTransportId() + "was fond");
			} catch (RuntimeException e) {
				RuntimeException ex = new TransprtsManagerException(
						"Could not find transport by ID =  "
								+ transport.getTransportId(), e);
				LOGGER.error(e);
				LOGGER.error(ex);
				throw ex;
			}
		}

		transport.setTransportCode(transportCode);
		transport.setStartTime(startTime);
		transport.setRoutes(routes);
		transport.setSeatclass1(seatclass1);
		transport.setSeatclass2(seatclass2);
		transport.setSeatclass3(seatclass3);
		transport.setGenPrice(genprice);

		try {
			transportsDao.saveOrUpdate(transport);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					saveOrUpdateTransportMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	/**
	 * Returns <code>TransportTravel</code> object, that contains all transport
	 * that goes through two stations
	 * 
	 * @param stationName1
	 * @param stationName2
	 * 
	 *            return <code>TransportTravel</code>, that contains transport
	 *            code, departure and arrival times, duration
	 */
	@Override
	public List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2) {
		// Results are stored here
		List<TransportTravel> transportTravel = null;

		try {
			transportTravel = transportsDao.findByTwoStations(stationName1,
					stationName2);
		} catch (RuntimeException e) {
			RuntimeException ex = new TransprtsManagerException(
					getTransportByTwoStationsMessage, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

		return transportTravel;
	}

	public static TransportsManager getInstance() {
		return ManagerFactory.getManager(TransportsManager.class);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsForLimit(int firstElement, int count) {
		return transportsDao.getTransportsForLimits(firstElement, count);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transports> getTransportsForPage(int pageNumber, int count) {
		return getTransportsForLimit((pageNumber - 1) * count, count);
	}

	@Transactional(readOnly = true)
	@Override
	public long getTransportsListCount() {
		return transportsDao.getTransportsListCount();
	}

	@Override
	public long getTransportByTwoStListCount(String stationName1,
			String stationName2) {

		return transportsDao.getTransportByTwoStListCount(stationName1,
				stationName2);
	}

	@Override
	public List<TransportTravel> getTransportByTwoStForPage(
			String stationName1, String stationName2, int pageNumber,
			int count, String sDate, int orderBy) {

		return getTransportByTwoStForLimit(stationName1, stationName2,
				(pageNumber - 1) * count, count, sDate, orderBy);
	}

	@Override
	public List<TransportTravel> getTransportByTwoStForLimit(
			String stationName1, String stationName2, int firstElement,
			int count, String sDate, int orderBy) {

		return transportsDao.getTransportByTwoStForLimits(stationName1,
				stationName2, firstElement, count, sDate, orderBy);
	}
}

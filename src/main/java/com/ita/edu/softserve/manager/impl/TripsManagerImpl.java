package com.ita.edu.softserve.manager.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.exception.TripsManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.TripsManager;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.utils.DateUtil;
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsInfoValidationContainer;
import com.ita.edu.softserve.validationcontainers.impl.EditTripsInfoValidationContainer;

@Service
public class TripsManagerImpl implements TripsManager {
	/**
	 * Logger for manager
	 */

	private static final Logger LOGGER = Logger
			.getLogger(TripsManagerImpl.class);

	/**
	 * String for ukrainian language representation in locale format (used in
	 * formatting date)
	 */

	public static final String UKRAINIAN = "ua";

	/**
	 * String for spain language representation in locale format (used in
	 * formatting date)
	 */

	public static final String SPANISH = "es";

	/**
	 * Error message for trip delete action
	 */

	private static final String COULD_NOT_REMOVE_TRIP = "Could not remove trip. ";

	/**
	 * Error message for trip edit action
	 */

	private static final String COULD_NOT_EDIT_TRIP = "Could not edit trip. ";

	/**
	 * Error message for trip add action
	 */

	private static final String COULD_NOT_ADD_TRIP = "Could not add trip. ";

	/**
	 * Common logging text
	 */

	private static final String USER_ACTION_TEXT = "Caused by actions of user ";

	/**
	 * Success trip removal logging text
	 */

	private static final String REMOVE_TRIP = "th trip removed. ";

	/**
	 * Success trip editing logging text
	 */

	private static final String EDIT_TRIP = "th trip edited. ";

	private static final String DATE_FORMAT_EXCEPTION = "Wrong date format. ";

	private static final String SUCCESS_ADD_TRIP = "Trip for transport %d and date %s added. ";

	private static final String TRIP_ALREADY_EXIST = "Trip for transport %d and date %s already exists.";

	private static final String DB_CONNECT_EXCEPTION = "There is a problem with DB connection (or something unexpected). ";

	@Autowired
	private TripsDAO tripsDao;

	@Autowired
	private UserNameService userNameService;

	@Autowired
	private TransportsDao transportsDao;

	public TripsManagerImpl() {
	}

	private RuntimeException logTripsException(Exception e, String message) {
		RuntimeException ex = new TripsManagerException(message
				+ USER_ACTION_TEXT + userNameService.getLoggedUsername(), e);
		LOGGER.error(ex);
		return ex;
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getAllTrips() 
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Trips> getAllTrips() {
		try {
			return tripsDao.getAllEntities();
		} catch (RuntimeException e) {
			throw logTripsException(e, DB_CONNECT_EXCEPTION);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#findByTripId(Integer)
	 */
	@Transactional
	@Override
	public Trips findByTripId(Integer id) {
		try {
			return tripsDao.findById(id);
		} catch (RuntimeException e) {
			throw logTripsException(e, DB_CONNECT_EXCEPTION);
		}

	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getTripsListCriteriaCount(String,
			String, Integer, Integer,
			Integer, Date, Date)
	 */
	@Transactional(readOnly = true)
	@Override
	public long getTripsListCriteriaCount(String transportCode,
			String routeName, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate) {
		try {
			return tripsDao.getTripsListCriteriaCount(transportCode, routeName,
					remSeatClass1, remSeatClass2, remSeatClass3, minDate,
					maxDate);
		} catch (RuntimeException e) {
			throw logTripsException(e, DB_CONNECT_EXCEPTION);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getTripsListCriteriaCountUsingContainers(
			TripsCriteriaContainer)
	 */
	@Transactional(readOnly = true)
	@Override
	public long getTripsListCriteriaCountUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer) {
		return getTripsListCriteriaCount(
				"%" + tripsCriteriaContainer.getTransportCode() + "%", "%"
						+ tripsCriteriaContainer.getRouteName() + "%",
				tripsCriteriaContainer.getRemSeatClass1(),
				tripsCriteriaContainer.getRemSeatClass2(),
				tripsCriteriaContainer.getRemSeatClass3(),
				tripsCriteriaContainer.getMinDateValue(),
				tripsCriteriaContainer.getMaxDateValue());
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getTripsListCriteriaPageUsingContainers(
			TripsCriteriaContainer,
			Integer, Integer)
	 */
	@Transactional(readOnly = true)
	@Override
	public long getTripsListCriteriaPageUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer,
			Integer elementIndex, Integer pageSize) {
		try {
			Trips knownElement = null;
			knownElement = tripsDao.findById(elementIndex);
			long number = tripsDao.getTripsListCriteriaIndex("%"
					+ tripsCriteriaContainer.getTransportCode() + "%", "%"
					+ tripsCriteriaContainer.getRouteName() + "%",
					tripsCriteriaContainer.getRemSeatClass1(),
					tripsCriteriaContainer.getRemSeatClass2(),
					tripsCriteriaContainer.getRemSeatClass3(),
					tripsCriteriaContainer.getMinDateValue(),
					tripsCriteriaContainer.getMaxDateValue(),
					tripsCriteriaContainer.getOrderByParam(),
					tripsCriteriaContainer.getOrderByDirection(), knownElement);
			return (number / pageSize) + 1;
		} catch (RuntimeException e) {
			throw logTripsException(e, DB_CONNECT_EXCEPTION);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getTripsForCriteriaWithPage(int, int,
			String, String, Integer,
			Integer, Integer, Date,
			Date, String, String)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForCriteriaWithPage(int pageNumber, int count,
			String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection) {
		return getTripsForCriteria((pageNumber - 1) * count, count,
				transportCode, routeName, remSeatClass1, remSeatClass2,
				remSeatClass3, minDate, maxDate, orderByParam, orderByDirection);
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getTripsForCriteriaUsingContainers(
			TripsCriteriaContainer,
			PageInfoContainer)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForCriteriaUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer,
			PageInfoContainer container) {
		return getTripsForCriteriaWithPage(container.getPageNumber(),
				container.getResultsPerPage(),
				"%" + tripsCriteriaContainer.getTransportCode() + "%", "%"
						+ tripsCriteriaContainer.getRouteName() + "%",
				tripsCriteriaContainer.getRemSeatClass1(),
				tripsCriteriaContainer.getRemSeatClass2(),
				tripsCriteriaContainer.getRemSeatClass3(),
				tripsCriteriaContainer.getMinDateValue(),
				tripsCriteriaContainer.getMaxDateValue(),
				tripsCriteriaContainer.getOrderByParam(),
				tripsCriteriaContainer.getOrderByDirection());
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getTripsForCriteria(int , int ,
			String , String , Integer ,
			Integer , Integer , Date ,
			Date , String , String )
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForCriteria(int firstElement, int count,
			String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection) {
		try {
			return tripsDao.getTripsListCriteria(firstElement, count,
					transportCode, routeName, remSeatClass1, remSeatClass2,
					remSeatClass3, minDate, maxDate, orderByParam,
					orderByDirection);
		} catch (RuntimeException e) {
			throw logTripsException(e, DB_CONNECT_EXCEPTION);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#getTripById(int) 
	 */
	@Transactional(readOnly = true)
	@Override
	public Trips getTripById(int id) {
		try {
			return tripsDao.getTripById(id);
		} catch (RuntimeException e) {
			throw logTripsException(e, DB_CONNECT_EXCEPTION);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#validateTripsCriteria(
			TripsCriteriaContainer, Locale) 
	 */
	@Override
	public void validateTripsCriteria(
			TripsCriteriaContainer tripsCriteriaContainer, Locale locale) {
		StaticValidator.validateTripsCriteria(tripsCriteriaContainer, locale);
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#addTripsWithContainer(
			AddTripsInfoValidationContainer)
	 */
	@Transactional
	@Override
	public boolean addTripsWithContainer(
			AddTripsInfoValidationContainer container) {
		Date startDate;
		Date endDate;
		try {
			Transports transport = null;
			Locale locale = container.getLocaleParam();
			transport = transportsDao.findById(Integer.parseInt(container
					.getTransportId()));
			startDate = DateUtil.parseLocalDate(container.getFrom(), locale);
			endDate = DateUtil.parseLocalDate(container.getTo(), locale);
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			for (Date date = start.getTime(); !start.after(end); start.add(
					Calendar.DATE, 1), date = start.getTime()) {
				if (tripsDao.checkTripExistance(transport.getTransportId(),
						date)) {
					LOGGER.info(String.format(TRIP_ALREADY_EXIST,
							transport.getTransportId(), date.toString())
							+ USER_ACTION_TEXT
							+ userNameService.getLoggedUsername());
				} else {
					Trips element = new Trips(transport, date);
					tripsDao.saveOrUpdate(element);
					LOGGER.info(String.format(SUCCESS_ADD_TRIP,
							transport.getTransportId(), date.toString())
							+ USER_ACTION_TEXT
							+ userNameService.getLoggedUsername());
				}
			}
			return true;
			
		} catch (ParseException e) {
			logTripsException(e, DATE_FORMAT_EXCEPTION);
			return false;
			
		} catch (RuntimeException e) {
			logTripsException(e, COULD_NOT_ADD_TRIP);
			return false;
		}

	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#editTrip(Integer,
			EditTripsInfoValidationContainer) 
	 */
	@Transactional
	@Override
	public boolean editTrip(Integer tripId,
			EditTripsInfoValidationContainer container) {
		Date startDate;
		Locale locale = container.getLocaleParam();
		Trips trip;
		try {
			startDate = DateUtil.parseLocalDate(container.getStartDate(),
					locale);
			trip = Objects.requireNonNull(tripsDao.findById(tripId));
			trip.setTransport(Objects.requireNonNull(transportsDao
					.findById(Integer.parseInt(container.getTransportId()))));
			trip.setRemSeatClass1(Integer.parseInt(container.getRemSeatClass1()));
			trip.setRemSeatClass2(Integer.parseInt(container.getRemSeatClass2()));
			trip.setRemSeatClass3(Integer.parseInt(container.getRemSeatClass3()));
			trip.setStartDate(startDate);
			updateTrip(trip);
			LOGGER.info(tripId + EDIT_TRIP + USER_ACTION_TEXT
					+ userNameService.getLoggedUsername());
			return true;
		} catch (ParseException e) {
			logTripsException(e, DATE_FORMAT_EXCEPTION);
			return false;
		} catch (RuntimeException e) {
			logTripsException(e, COULD_NOT_EDIT_TRIP);
			return false;
		}

	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#updateTrip(Trips)
	 */
	@Override
	public void updateTrip(Trips trip) {
		try {
			tripsDao.saveOrUpdate(trip);
		} catch (RuntimeException e) {
			throw logTripsException(e, DB_CONNECT_EXCEPTION);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#reduceFreeSeatsQuantity(Integer, Integer)
	 */
	@Transactional
	@Override
	public void reduceFreeSeatsQuantity(Integer tripId, Integer seatType) {

		if (seatType.equals(1)) {
			tripsDao.reduceRemSeaatClass1(tripId);
		}

		if (seatType.equals(2)) {
			tripsDao.reduceRemSeaatClass2(tripId);
		}

		if (seatType.equals(3)) {
			tripsDao.reduceRemSeaatClass3(tripId);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#increaseFreeSeatsQuantity(Integer, Integer) 
	 */
	@Transactional
	@Override
	public void increaseFreeSeatsQuantity(Integer tripId, Integer seatType) {

		if (seatType.equals(1)) {
			tripsDao.increaseRemSeaatClass1(tripId);
		}

		if (seatType.equals(2)) {
			tripsDao.increaseRemSeaatClass2(tripId);
		}

		if (seatType.equals(3)) {
			tripsDao.increaseRemSeaatClass3(tripId);
		}
	}

	/**
	 * @see com.ita.edu.softserve.manager.TripsManager#removeTrip(Integer) 
	 */

	@Override
	@Transactional(readOnly = false)
	public void removeTrip(Integer id) {
		try {
			tripsDao.remove(tripsDao.findById(id));
			LOGGER.info(id + REMOVE_TRIP + USER_ACTION_TEXT
					+ userNameService.getLoggedUsername());
		} catch (RuntimeException e) {
			throw logTripsException(e, COULD_NOT_REMOVE_TRIP);
		}

	}
}

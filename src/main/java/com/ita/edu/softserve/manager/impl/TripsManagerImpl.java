package com.ita.edu.softserve.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;

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

	private static final String UKRAINIAN = "ua";

	/**
	 * String for spain language representation in locale format (used in
	 * formatting date)
	 */

	private static final String SPANISH = "es";
	/**
	 * 
	 */

	private static final String COULD_NOT_REMOVE_TRIP = "Could not remove trip. Caused by actions of user ";

	@Autowired
	private TripsDAO tripsDao;

	@Autowired
	private UserNameService userNameService;

	@Autowired
	private TransportsDao transportsDao;

	public TripsManagerImpl() {
	}

	@Transactional(readOnly = true)
	@Override
	public List<Trips> getAllTrips() {
		return tripsDao.getAllEntities();
	}

	@Transactional
	@Override
	public Trips findByTripId(Integer id) {

		return tripsDao.findById(id);

	}

	@Transactional(readOnly = true)
	@Override
	public long getTripsListCriteriaCount(String transportCode,
			String routeName, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate) {
		return tripsDao.getTripsListCriteriaCount(transportCode, routeName,
				remSeatClass1, remSeatClass2, remSeatClass3, minDate, maxDate);
	}

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

	@Transactional(readOnly = true)
	@Override
	public long getTripsListCriteriaPageUsingContainers(
			TripsCriteriaContainer tripsCriteriaContainer,
			Integer elementIndex, Integer pageSize) {
		Trips knownElement=null;
		knownElement=tripsDao.findById(elementIndex);
		long number = tripsDao.getTripsListCriteriaIndex("%"
				+ tripsCriteriaContainer.getTransportCode() + "%", "%"
				+ tripsCriteriaContainer.getRouteName() + "%",
				tripsCriteriaContainer.getRemSeatClass1(),
				tripsCriteriaContainer.getRemSeatClass2(),
				tripsCriteriaContainer.getRemSeatClass3(),
				tripsCriteriaContainer.getMinDateValue(),
				tripsCriteriaContainer.getMaxDateValue(),
				tripsCriteriaContainer.getOrderByParam(),
				tripsCriteriaContainer.getOrderByDirection(),
				knownElement);
		return (number/pageSize)+1;
	}

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

	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForCriteria(int firstElement, int count,
			String transportCode, String routeName, Integer remSeatClass1,
			Integer remSeatClass2, Integer remSeatClass3, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection) {
		return tripsDao
				.getTripsListCriteria(firstElement, count, transportCode,
						routeName, remSeatClass1, remSeatClass2, remSeatClass3,
						minDate, maxDate, orderByParam, orderByDirection);
	}

	@Transactional(readOnly = true)
	@Override
	public long getTripsListCount() {
		return tripsDao.getTripsListCount();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForPage(int pageNumber, int count) {
		return getTripsForLimit((pageNumber - 1) * count, count);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Trips> getTripsForLimit(int firstElement, int count) {
		return tripsDao.getTripsForLimits(firstElement, count);
	}

	@Override
	public void validateTripsCriteria(
			TripsCriteriaContainer tripsCriteriaContainer, Locale locale) {
		StaticValidator.validateTripsCriteria(tripsCriteriaContainer, locale);
	}

	@Transactional
	@Override
	public boolean addTripsInInterval(Locale locale, String minDate,
			String maxDate, int transportId) {
		Date startDate;
		Date endDate;
		try {
			Transports transport = null;

			transport = transportsDao.findById(transportId);
			if (locale.getLanguage().trim().equalsIgnoreCase(UKRAINIAN)
					|| locale.getLanguage().trim().equalsIgnoreCase(SPANISH)) {
				startDate = ValidatorUtil.UKRAINIAN_AND_SPANISH_FORMATTER.parse(minDate);
				endDate = ValidatorUtil.UKRAINIAN_AND_SPANISH_FORMATTER.parse(maxDate);
			} else {
				startDate = ValidatorUtil.DEFAULT_DATE_FORMATTER.parse(minDate);
				endDate = ValidatorUtil.DEFAULT_DATE_FORMATTER.parse(maxDate);
			}
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);
			for (Date date = start.getTime(); !start.after(end); start.add(
					Calendar.DATE, 1), date = start.getTime()) {
				Trips element = new Trips(transport, date);
				tripsDao.saveOrUpdate(element);
			}
			;
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void updateTrip(Trips trip) {
		tripsDao.saveOrUpdate(trip);
	}

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
		System.out.println("reduceManager");
	}

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

		System.out.println("increaseManager");
	}

	public static TripsManager getInstance() {
		return ManagerFactory.getManager(TripsManager.class);
	}

	/**
	 * Delete trip from DB
	 */

	@Override
	@Transactional(readOnly = false)
	public void removeTrip(Integer id) {
		try {
			tripsDao.remove(tripsDao.findById(id));
		} catch (RuntimeException e) {
			RuntimeException ex = new TripsManagerException(
					COULD_NOT_REMOVE_TRIP + userNameService.getLoggedUsername(),
					e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

	}
}

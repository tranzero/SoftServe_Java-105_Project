package com.ita.edu.softserve.manager.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.dao.TripsDAO;
import com.ita.edu.softserve.entity.Tickets;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.TicketsManager;
import com.ita.edu.softserve.manager.TripsManager;

@Service("tripsManager")
public class TripsManagerImpl implements TripsManager {

	/**
	 * String for ukrainian language representation in locale format (used in
	 * formatting date)
	 */

	private static final String UKRAINIAN = "ua";

	/**
	 * String for spain language representation in locale format (used in
	 * formatting date)
	 */

	private static final String SPAIN = "es";

	@Autowired
	private TripsDAO tripsDao;

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
			Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date minDate, Date maxDate) {
		return tripsDao.getTripsListCriteriaCount(transportCode, remSeatClass1,
				remSeatClass2, remSeatClass3, minDate, maxDate);
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

	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public boolean addTripsInInterval(Locale locale, String minDate,
			String maxDate, int transportId) {
		int year;
		int day;
		int month;
		Date startDate;
		Date endDate;
		try {
			Transports transport = null;

			transport = transportsDao.findById(transportId);
			if (locale.getLanguage().trim().equalsIgnoreCase(UKRAINIAN)
					|| locale.getLanguage().trim().equalsIgnoreCase(SPAIN)) {
				String datesplit1[] = minDate.split("\\.");
				year = Integer.parseInt(datesplit1[2]);
				day = Integer.parseInt(datesplit1[0]);
				month = Integer.parseInt(datesplit1[1]);
				startDate = new Date(year, month, day);
				String datesplit2[] = maxDate.split("\\.");
				year = Integer.parseInt(datesplit2[2]);
				day = Integer.parseInt(datesplit2[0]);
				month = Integer.parseInt(datesplit2[1]);
				endDate = new Date(year, month, day);
			} else {
				String datesplit1[] = minDate.split("/");
				year = Integer.parseInt(datesplit1[2]);
				day = Integer.parseInt(datesplit1[1]);
				month = Integer.parseInt(datesplit1[0]);
				startDate = new Date(year, month, day);
				String datesplit2[] = maxDate.split("/");
				year = Integer.parseInt(datesplit2[2]);
				day = Integer.parseInt(datesplit2[1]);
				month = Integer.parseInt(datesplit2[0]);
				endDate = new Date(year, month, day);
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

	public static TripsManager getInstance() {
		return ManagerFactory.getManager(TripsManager.class);
	}
}

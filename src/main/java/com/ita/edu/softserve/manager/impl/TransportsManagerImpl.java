package com.ita.edu.softserve.manager.impl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.dao.TransportsDao;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Stops;
import com.ita.edu.softserve.entity.Transports;
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

	/**
	 * Object to get access to DAO layer.
	 */
	@Autowired
	public TransportsDao transportsDao;

	@Autowired
	private StationsOnLineDAO stlDao;

	@Autowired
	private StationsDAO stationDao;

	@Autowired
	private StopsDAO stopsDao;

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
		return transportsDao.findById(id);
	}

	/**
	 * Saves Transports in database.
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveTransports(Transports... entities) {
		transportsDao.save(entities);
	}

	/**
	 * Removes Transports from database.
	 */
	@Transactional
	@Override
	public void removeTransports(Transports... entities) {
		transportsDao.remove(entities);
	}

	/**
	 * Updates database and get list of all Transports.
	 * 
	 * @return list of all stations.
	 */
	@Transactional
	@Override
	public List<Transports> updateTransports(Transports... entities) {
		return transportsDao.update(entities);
	}

	/**
	 * Gets list of all transports.
	 * 
	 * @return list of all transports.
	 */
	@Transactional
	@Override
	public List<Transports> getAllTransports() {
		return transportsDao.getAllEntities();
	}

	/**
	 * Saves Transport object and save it to database.
	 */
	@Transactional
	@Override
	public Transports createTransport(String transportCode, String startTime,
			String route, String seatclass1, String seatclass2,
			String seatclass3, String genprice) {

		Transports transport = new Transports(transportCode,
				timeParse(startTime), routesDao.findById(new Integer(route)));

		transport.setSeatclass1(new Integer(seatclass1));
		transport.setSeatclass2(new Integer(seatclass2));
		transport.setSeatclass3(new Integer(seatclass3));
		transport.setGenPrice(new Double(genprice));

		transportsDao.save(transport);

		return transport;
	}

	@Transactional
	@Override
	public void saveOrUpdateTransport(Transports entity) {
		transportsDao.saveOrUpdate(entity);
	}

	@Override
	public List<Transports> getTransportByTwoStations(String stationName1,
			String stationName2) {
		Stations station1;
		Stations station2;

		/* Results are stored here */
		List<Transports> transport = new ArrayList<Transports>();

		List<StationsOnLine> StationsOnLine1 = new ArrayList<StationsOnLine>();
		List<StationsOnLine> StationsOnLine2 = new ArrayList<StationsOnLine>();

		Stops stop1 = null;
		Stops stop2 = null;

		int index = 0;

		try {
			station1 = (Stations) stationDao.findByStations(stationName1)
					.get(0);
			station2 = (Stations) stationDao.findByStations(stationName2)
					.get(0);
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
			return null;
		}

		StationsOnLine1.addAll(stlDao.findByStationId(station1.getStationId()));
		StationsOnLine2.addAll(stlDao.findByStationId(station2.getStationId()));

		for (int i = 0; i < StationsOnLine2.size(); i++) {
			for (int j = 0; j < StationsOnLine1.size(); j++) {
				if (StationsOnLine2.get(i).getLineId().getLineId() == StationsOnLine1
						.get(j).getLineId().getLineId()) {
					if (StationsOnLine2.get(i).getStationOrderNum() < StationsOnLine1
							.get(j).getStationOrderNum()) {
						try {
							stop1 = stopsDao
									.findByStationsOnLineId(StationsOnLine1
											.get(i).getStationOnLineId());
							stop2 = stopsDao
									.findByStationsOnLineId(StationsOnLine2
											.get(i).getStationOnLineId());
						} catch (Exception e) {
							System.out.println("" + e.getMessage());
						}
						if ((stop1 != null) && (stop2 != null)) {
							transport.add(transportsDao.findByRouteId(stop1
									.getRouteId().getRouteId()));
							transport.get(index).setStartTime(
									TransportsManagerImpl.sumDates(transport
											.get(index).getStartTime(), stop1
											.getDeparture()));
							index++;
						} else {
							return null;
						}
					}
				}
			}
		}

		return transport;
	}

	private static Time sumDates(Time... time) {
		int secs = 0;
		int mins = 0;
		int hrs = 0;

		Calendar calendar = Calendar.getInstance();

		for (int i = 0; i < time.length; i++) {
			Date date = new Date(time[i].getTime());
			calendar.setTime(date);
			secs += calendar.get(Calendar.SECOND);
			mins += calendar.get(Calendar.MINUTE);
			hrs += calendar.get(Calendar.HOUR_OF_DAY);
		}
		calendar.set(0, 0, 0, hrs, mins, secs);

		return new Time(calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
	}

	public static TransportsManager getInstance() {
		return ManagerFactory.getManager(TransportsManager.class);
	}

	/**
	 * Parses time representing in string into sql time.
	 */
	private static Time timeParse(String time) {

		DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		Date date = null;

		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			LOGGER.error(e.toString());
		}

		return new Time(date.getTime());
	}
}

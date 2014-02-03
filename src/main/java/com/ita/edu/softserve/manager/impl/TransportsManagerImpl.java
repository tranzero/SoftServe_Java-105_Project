package com.ita.edu.softserve.manager.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.StationsDAO;
import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.dao.StopsDAO;
import com.ita.edu.softserve.dao.TransportsDao;
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
	@Transactional
	@Override
	public Transports findTransportsById(int id) {
		return transportsDao.findById(id);
	}

	/**
	 * Saves Transports in database.
	 */
	@Transactional
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

	@Override
	public List<Transports> getTransportByTwoStations(String stationName1,
			String stationName2) {
		Stations station1;
		Stations station2;

		/* Results are stored here */
		List<Transports> transport = new ArrayList<Transports>();

		List<StationsOnLine> StationsOnLine1 = new ArrayList<StationsOnLine>();
		List<StationsOnLine> StationsOnLine2 = new ArrayList<StationsOnLine>();

		Stops stop1;
		Stops stop2;

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
						stop1 = stopsDao.findById(StationsOnLine2.get(i)
								.getStationOnLineId());
						stop2 = stopsDao.findById(StationsOnLine2.get(i)
								.getStationOnLineId());
						if ((stop1 != null) && (stop2 != null)) {
							transport.add(transportsDao.findByRouteId(stop1
									.getRouteId().getRouteId()));
							transport.get(index).setStartTime(
									TransportsManagerImpl.sumDates(transport
											.get(index).getStartTime(), stop1
											.getDeparture()));
							index++;
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
			hrs += calendar.get(Calendar.HOUR);
		}
		calendar.set(0, 0, 0, hrs, mins, secs);
		
		return new Time(calendar.get(Calendar.HOUR),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
	}

	public static TransportsManager getInstance() {
		return ManagerFactory.getManager(TransportsManager.class);
	}

}

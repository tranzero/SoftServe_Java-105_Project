package com.ita.edu.softserve.manager.impl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.RoutesDAO;
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
import static com.ita.edu.softserve.utils.ParseUtil.*;
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
	@Transactional(readOnly = false)
	@Override
	public void removeTransports(Transports... entities) {
		transportsDao.remove(entities);
	}

	/**
	 * Removes Transport by Id from database.
	 */
	@Transactional(readOnly = false)
	@Override
	public void removeTransportById(Integer transportId) {
		Transports transport = null;

		try {
			transport = (Transports) transportsDao.findById(transportId);
			transportsDao.remove(transport);
		} catch (NoResultException e) {
			LOGGER.error("No such transport!", e);
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
	 * Saves Transport object or updates it to database.
	 */
	@Transactional
	@Override
	public void saveOrUpdateTransport(String transportCode, String startTime,
			String routes, String seatclass1, String seatclass2,
			String seatclass3, String genprice) {

		Transports transport = new Transports(transportCode,
				timeParse(startTime), routesDao.findByCode(routes),
				new Integer(seatclass1), new Integer(seatclass2), new Integer(
						seatclass3), new Double(genprice));

		transportsDao.saveOrUpdate(transport);
	}

	/**
	 * Edits Transport object.
	 */
	@Transactional
	@Override
	public void editTransport(Integer transportId, String transportCode, String startTime,
			String routes, String seatclass1, String seatclass2,
			String seatclass3, String genprice) {
		
		Transports transport = transportsDao.findById(transportId);

		transport.setTransportCode(transportCode);
		transport.setStartTime(timeParse(startTime));
		transport.setRoutes(routesDao.findByCode(routes));
		transport.setSeatclass1(new Integer(seatclass1));
		transport.setSeatclass2(new Integer(seatclass2));
		transport.setSeatclass3(new Integer(seatclass3));
		transport.setGenPrice(new Double(genprice));

		transportsDao.update(transport);
	}

	@Override
	public List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2) {
		Stations station1;
		Stations station2;

		/* Results are stored here */
		List<TransportTravel> transportTravel = new ArrayList<TransportTravel>();

		List<StationsOnLine> StationsOnLine1 = new ArrayList<StationsOnLine>();
		List<StationsOnLine> StationsOnLine2 = new ArrayList<StationsOnLine>();

		Stops stop1 = null;
		Stops stop2 = null;

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
					if (StationsOnLine2.get(i).getStationOrderNum() > StationsOnLine1
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
							Transports transport = transportsDao
									.findByRouteId(stop1.getRouteId()
											.getRouteId());
							TransportTravel trTravel = new TransportTravel(
									transport);
							trTravel.setDepartureTime(TransportTravel.sumTimes(
									transport.getStartTime(),
									stop1.getDeparture()));
							trTravel.setArrivalTime(TransportTravel.sumTimes(
									transport.getStartTime(),
									stop2.getArrival()));

							trTravel.setDuration(TransportTravel.subtractTimes(
									stop2.getArrival(), stop1.getDeparture()));
							trTravel.setDuration(TransportTravel.subtractTimes(
									trTravel.getArrivalTime(),
									trTravel.getDepartureTime()));
							transportTravel.add(trTravel);
						} else {
							return null;
						}
					}
				}
			}
		}

		return transportTravel;
	}

	public static TransportsManager getInstance() {
		return ManagerFactory.getManager(TransportsManager.class);
	}
}

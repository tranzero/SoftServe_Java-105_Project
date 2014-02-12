package com.ita.edu.softserve.manager.impl;

import static com.ita.edu.softserve.utils.ParseUtil.timeParse;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.TransportsDao;
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
	@Transactional
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
	 * Saves Transport object to database if not exist or updates it. <br/>
	 * <br/>
	 * If <code>transportId</code> is <code>null</code> than it creates new
	 * transport object otherwise it finds existing one in database and updates
	 * it.
	 */
	@Transactional(readOnly = false)
	@Override
	public void saveOrUpdateTransport(Integer transportId,
			String transportCode, String startTime, String routes,
			String seatclass1, String seatclass2, String seatclass3,
			String genprice) {

		Transports transport = null;

		if (transportId == null) {
			transport = new Transports();
		} else {
			transport = transportsDao.findById(transportId);
		}

		transport.setTransportCode(transportCode);
		transport.setStartTime(timeParse(startTime));
		transport.setRoutes(routesDao.findByCode(routes));
		transport.setSeatclass1(new Integer(seatclass1));
		transport.setSeatclass2(new Integer(seatclass2));
		transport.setSeatclass3(new Integer(seatclass3));
		transport.setGenPrice(new Double(genprice));

		transportsDao.saveOrUpdate(transport);
	}

	@Override
	public List<TransportTravel> getTransportByTwoStations(String stationName1,
			String stationName2) {
		// Results are stored here
		List<TransportTravel> transportTravel;

		transportTravel = transportsDao.findByTwoStations(stationName1,
				stationName2);

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
		return getTransportsForLimit((pageNumber-1)*count, count);
	}
	
	@Transactional(readOnly = true)
	@Override
	public long getTransportsListCount() {
		return transportsDao.getTransportsListCount();
	}
}

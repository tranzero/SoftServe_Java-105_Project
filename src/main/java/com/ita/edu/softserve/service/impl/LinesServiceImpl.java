/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.StationsOnLineDAO;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.service.LinesService;

/**
 * 
 * @author yuraloga
 * @author MPS
 * 
 */
@Service
public class LinesServiceImpl implements LinesService {

	private static final Logger LOGGER = Logger.getLogger(Lines.class);
	private LinesDAOImpl lineDao;
	private StationsOnLineDAOImpl stlDao;

	public LinesServiceImpl() {
		this(new LinesDAOImpl()); 
	}

	public LinesServiceImpl(LinesDAOImpl lineDao) {
		this.lineDao = lineDao;
	}

	/**
	 * Return all Lines
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getFullLines() {
		List<Lines> linesList = lineDao.getAllEntities();
		return linesList;

	}

	/**
	 * 
	 * @param stationName
	 *            name of station
	 * @return <code>List&lt;Lines&gt;</code> which includes certain station
	 */
	@Override
	public List<Lines> getLinesByStation(Stations station) {

		List<StationsOnLine> stlList= stlDao.findByStationId(station.getStationId());
		List<Lines> linesList = new ArrayList<Lines>();
		for(StationsOnLine stl: stlList){
			linesList.add(lineDao.findById(stl.getLineId().getLineId()));
		}
		return linesList;
	}

	/**
	 * Return Lines that includes two stations in certain order
	 * 
	 * @param station1
	 *            - first station, departure
	 * @param station2
	 *            - second station, arrival
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getLinesTwoStationsCertainOrder(String station1,
			String station2) {
		return null;
//		return lineDao.getLinesTwoStationsCertainOrder(station1, station2);
	}

}

/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
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
	private LinesDAOImpl lineDao = new LinesDAOImpl();

	/**
	 * Return all Lines
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getFullLines() {
		// TODO Auto-generated method stub
		return lineDao.getFullLines();
	}

	/**
	 * 
	 * @param stationName name of station
	 * @return <code>List&lt;Lines&gt;</code> which includes certain station
	 */
	@Override
	public List<Lines> getLinesByStation(String stationName) {
		
		return lineDao.getLinesByStation(stationName);
	}

	/**
	 * Return Lines that includes two stations in certain order
	 * 
	 * @param station1 - first station, departure
	 * @param station2 - second station, arrival
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getLinesTwoStationsCertainOrder(Stations station1,
													   Stations station2) {
		return lineDao.getLinesTwoStationsCertainOrder(station1, station2);
	}

}

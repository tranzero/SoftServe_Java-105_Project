/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("linesService")
public class LinesServiceImpl implements LinesService {

	private static final Logger LOGGER = Logger.getLogger(Lines.class);
	
	@Autowired
	private LinesDAOImpl lineDao;
	
	@Autowired
	private StationsOnLineDAOImpl stlDao;
	


	public LinesServiceImpl() {
	}



	public LinesServiceImpl(StationsOnLineDAOImpl stlDao, LinesDAOImpl lineDao) {
		this.lineDao = lineDao;
		this.stlDao = stlDao;
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

		List<StationsOnLine> stlList = stlDao.findByStationId(station
				.getStationId());
		List<Lines> linesList = new ArrayList<Lines>();
		for (StationsOnLine stl : stlList) {
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
	public List<Lines> getLinesTwoStationsCertainOrder(Stations station1,
			Stations station2) {
		/* here is stored station that we need to get from */
//		List<Stations> stations1 = stationsDao.findByStations(station1
//				.getStationName());
		/* here is stored station that we need to get to */
//		List<Stations> stations2 = stationsDao.findByStations(station2
//				.getStationName());

		List<StationsOnLine> StationsOnLine1 = new ArrayList<StationsOnLine>();
		List<StationsOnLine> StationsOnLine2 = new ArrayList<StationsOnLine>();

		/* Pre-results are stored here */
		List<StationsOnLine> StationsOnLine = new ArrayList<StationsOnLine>();
		/* Results are stored here */
		List<Lines> lines = new ArrayList<Lines>();
/*
		for (int i = 0; i < stations1.size(); i++) {
			StationsOnLine1.addAll(stlDao.findByStationId(stations1.get(i)
					.getStationId()));
		}
		for (int i = 0; i < stations2.size(); i++) {
			StationsOnLine2.addAll(stlDao.findByStationId(stations2.get(i)
					.getStationId()));
		}
*/		
		StationsOnLine1.addAll(stlDao.findByStationId(station1.getStationId()));
		StationsOnLine2.addAll(stlDao.findByStationId(station2.getStationId()));
		
		for (int i = 0; i < StationsOnLine2.size(); i++) {
			for (int j = 0; j < StationsOnLine1.size(); j++) {
				if (StationsOnLine2.get(i).getLineId() == StationsOnLine1
						.get(j).getLineId()) {
					if (StationsOnLine2.get(i).getStationOrderNum() < StationsOnLine1
							.get(j).getStationOrderNum()) {
						StationsOnLine.add(StationsOnLine2.get(i));
					}
				}
			}
		}

		for (int i = 0; i < StationsOnLine.size(); i++) {
			lines.add(lineDao.findById(StationsOnLine.get(i).getLineId()
					.getLineId()));
		}

		return lines;
	}

}

/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

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
public class LinesServiceImpl implements LinesService {

    private static final Logger LOGGER = Logger.getLogger(Lines.class);

    /**
	 * Return all Lines
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getFullLines() {
		// TODO Auto-generated method stub
		return LinesDAOImpl.getFullLines();
	}

	/**
	 * Return Lines that includes two stations in certain order
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	@Override
	public List<Lines> getLinesByStation() {
		// TODO Auto-generated method stub
		return null;
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
        return LinesDAOImpl.getLinesTwoStationsCertainOrder(station1, station2);
	}

}

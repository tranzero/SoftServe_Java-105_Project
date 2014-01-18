/**
 * 
 */
package com.ita.edu.softserve.service;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author yuraloga
 * @author MPS
 * 
 */
public interface LinesService {
	
	public List<Lines> getAllLines();
	
	public List<Lines> getLinesByStation();
	
	public List<Lines> getLinesTwoStationsCertainOrder(Stations station1,
													   Stations station2);

}

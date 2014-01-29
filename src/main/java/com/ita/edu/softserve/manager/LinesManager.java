/**
 * 
 */
package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

/**
 * 
 * @author yuraloga
 * @author MPS
 * 
 */
public interface LinesManager extends BaseManager {
	
	public List<Lines> getFullLines();
	
	public List<Lines> getLinesByStation(Stations station);
	
	public List<Lines> getLinesTwoStationsCertainOrder(Stations station1,
													   Stations station2);

}

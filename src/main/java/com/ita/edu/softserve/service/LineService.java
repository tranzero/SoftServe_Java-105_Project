/**
 * 
 */
package com.ita.edu.softserve.serviceiface;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;

/**
 * @author MPS
 *
 */
public interface LineService {
	
	public List<Lines> getAllLines();
	
	public List<Lines> getLinesByStation();
	
	public List<Lines> getLinesTwoStationsCertainOrder();

}

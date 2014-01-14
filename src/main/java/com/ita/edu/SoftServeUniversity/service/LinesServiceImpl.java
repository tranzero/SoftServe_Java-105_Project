package com.ita.edu.SoftServeUniversity.service;  

import java.util.List;
import com.ita.edu.SoftServeUniversity.entity.Lines;   

/**  
 * @author yuraloga   
 * 
 */
public class LinesServiceImpl implements LinesService {

	/**
	 * Return Lines that includes two stations
	 * in certain order
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	public List<Lines> getLinesTwoStationsCertainOrder() { 		
		return DaoFactory.getInstance().getLinesDAO().getLinesTwoStationsCertainOrder(); 
	}  	

	/**
	 * Return full Lines
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	public List<Lines> getFullLines() { 
		return DaoFactory.getInstance().getLinesDAO().getFullLines(); 
	}  	
	
	/**
	 * Return all Lines that includes certain
	 * station
	 *  
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	public List<Lines> getLinesByStation() { 	
		return DaoFactory.getInstance().getLinesDAO().getLinesByStation(); 
	} 
}
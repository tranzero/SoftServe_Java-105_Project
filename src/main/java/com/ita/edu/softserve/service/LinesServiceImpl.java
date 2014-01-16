package com.ita.edu.softserve.service;  

import java.util.List;

import com.ita.edu.softserve.dao.DaoFactory;
import com.ita.edu.softserve.entity.Lines;

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
		return null;
//		return DaoFactory.getInstance().getLinesDAO().getLinesTwoStationsCertainOrder(); 
	}  	

	/**
	 * Return full Lines
	 * 
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	public List<Lines> getFullLines() { 
		return DaoFactory.getInstance().getLinesDAO().getAllElements(); 
	}  	
	
	/**
	 * Return all Lines that includes certain
	 * station
	 *  
	 * @return <code>List&lt;Lines&gt;</code>
	 */
	public List<Lines> getLinesByStation() {
		return null;
	} 
}
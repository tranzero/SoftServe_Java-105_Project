package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * 
 * @author iryna
 * 
 */
public interface StationsOnLineDAO extends AbstractDAOIface<StationsOnLine>{

	/**
	 * Find by Station Id
	 * 
	 * @param id
	 * @return List<StationsOnLine>
	 */
	public List<StationsOnLine> findByStationId(int id);
	public List<StationsOnLine> findByLineId(int id);
	public int getLinesByStationCount(int id);
}

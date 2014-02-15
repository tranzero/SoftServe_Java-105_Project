package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Trips;

/**
 * 
 * @author iryna
 * 
 */
@Repository("linesDaoImpl")
public class LinesDAOImpl extends AbstractDAO<Lines> implements LinesDAO {

	@Override
	public Lines findByName(String lineName) {
		Query query = entityManager.createNamedQuery(Lines.FIND_BY_NAME)
				.setParameter(1, lineName);
		return (Lines) query.getSingleResult();
	}

	
	@Override
	public Class<Lines> getEntityClass() {

		return Lines.class;
	}

	public List<Lines> getLinesByStationName(String stationName) {
		
		Query query = entityManager.createNamedQuery(Lines.FIND_BY_STATION_NAME)
				.setParameter( 1, stationName);
	
		return (List<Lines>)query.getResultList();
		
	}

	@Override
	public long getLinesByStationNameCount(String stationName) {

		return (long) find((Query) entityManager.createNamedQuery(
				Lines.FIND_BY_STATION_NAME_COUNT).setParameter(1, stationName));
	}

	private List<Lines> getLinesByStationForPaging(int from, int count,
			String stationName) {
		
		Query query = entityManager
				.createNamedQuery(Lines.FIND_BY_STATION_NAME_FOR_PAGING)
				.setParameter(1, stationName).setFirstResult(from)
				.setMaxResults(count);
		
		return (List<Lines>) getRange(from, count, query);
		
	}

	@Override
	public List<Lines> getLinesByStationForOnePage(int from, int count,
			String stationName) {
		
		return this.getLinesByStationForPaging(from, count, stationName);
		
	}
	
	@Override
	public List<Lines> getLinesByStNameForLimits(String stationName, int firstElement, int count) {
		
		Query query = entityManager
				.createNamedQuery(Lines.FIND_BY_STATION_NAME).setParameter(1, stationName).setFirstResult(firstElement).setMaxResults(count);
		
		return (List<Lines>) query.getResultList();
		
	}

	/**
	 * 
	 * @param stationName1
	 * @param stationName2
	 * 
	 * @return List of lines, that contains two stations
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Lines> findByTwoStations(String stationName1,
			String stationName2) {
		
		Query query = entityManager
				.createNamedQuery(Lines.FIND_BY_TWO_STATIONS)
				.setParameter(1, stationName1).setParameter(2, stationName2);

		return query.getResultList();
	}

	/**
	 * 
	 * @param stationName1
	 * @param stationName2
	 * @param firstElement to start with
	 * @param count 	   how math to fetch
	 * 
	 * @return Lines limited
	 */
	@Override
	public List<Lines> getLinesByTwoStForLimits(String stationName1,
			String stationName2, int firstElement, int count) {
		
		Query query = entityManager
				.createNamedQuery(Lines.FIND_BY_TWO_STATIONS)
				.setParameter(1, stationName1).setParameter(2, stationName2)
				.setFirstResult(firstElement).setMaxResults(count);
		
		return (List<Lines>) query.getResultList();
	}

	/**
	 * @param stationName1
	 * @param stationName2
	 * 
	 * @return size of list with lines
	 */
	@Override
	public long getLinesByTwoStListCount(String stationName1,
			String stationName2) {
		
		return (long) find((Query) entityManager
				.createNamedQuery(Lines.FIND_BY_TWO_STATIONS_COUNT)
				.setParameter(1, stationName1).setParameter(2, stationName2));
	}

	/**
	 * @author MatyashPetro
	 * @return size of list with all lines
	 */
	@Override
	public long getLinesListCount() {
		
		return (long) find((Query) entityManager
				.createNamedQuery(Lines.GET_LINES_LIST_COUNT));
	}

	/**
	 * @author MatyashPetro
	 * @return List of lines witch will be printed on one page
	 * @param from from what element will be start next list
	 * @param count how match elements will be in the list 
	 */
	@Override
	public List<Lines> getLinesForOnePage(int from, int count) {
		
		return this.getLinesForPaging(from, count);

	}

	/**
	 * @author MatyashPetro
	 * @return List of lines witch will be printed on one page
	 * @param from from what element will be start next list
	 * @param count how match elements will be in the list 
	 */
	private List<Lines> getLinesForPaging(int from, int count) {
		
		Query query = entityManager
				.createNamedQuery(Lines.GET_LINES_LIST_FOR_PAGING)
				.setFirstResult(from).setMaxResults(count);
		
		return (List<Lines>) getRange(from, count, query);
	}
}
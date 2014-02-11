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
@Repository ("linesDaoImpl")
public class LinesDAOImpl extends AbstractDAO<Lines> implements LinesDAO {

        @Override
        public Lines findByName(String lineName) {
                Query query = entityManager.createNamedQuery(Lines.FIND_BY_NAME)
                                .setParameter(1, lineName);
                return (Lines) query.getSingleResult();
        }

         
        /**
         * @param stationName
         * @return List of lines which has certain station
         */
        
    	@Override
    	public long getLinesListCount() {
    		return (long) find((Query)entityManager
    					.createNamedQuery(Lines.GET_FULL_LINES_COUNT));
    	}
        
    	@Override
    	public List<Lines> getLinesForLimits(int firstElement, int count) {
    		Query query = entityManager
    				.createNamedQuery(Lines.GET_FULL_LINES)
    				.setFirstResult(firstElement)
    				.setMaxResults(count);
    		return (List<Lines>)query.getResultList();
    	}
        
        @Override
		public Class<Lines> getEntityClass() {
                
                return Lines.class;
        }
        
        public List<Lines> getLinesByStationName(String stationName){
        	Query query = entityManager.createNamedQuery(Lines.FIND_BY_STATION_NAME).setParameter(1, stationName);
        	List<Lines > list = query.getResultList();
        	return list;
        }
        @Override
    	public int getLinesByStationNameCount(String stationName) {

    		return (int) find((Query) entityManager.createNamedQuery(
    				Lines.FIND_BY_STATION_NAME_COUNT).setParameter(1, stationName));
    	}
        
        private List<Lines> getLinesByStationForPaging(int from,
    			int count, String stationName) {
    		Query query = entityManager
    				.createNamedQuery(
    						Lines.FIND_BY_STATION_NAME_FOR_PAGING)
    				.setParameter(1, stationName).setFirstResult(from).setMaxResults(count);
    		return (List<Lines>) getRange(from, count, query);
    	}
        @Override
    	public List<Lines> getLinesByStationForOnePage(int from,
    			int count, String stationName) {
    		return this.getLinesByStationForPaging(from, count, stationName);
    	}
        
    	@SuppressWarnings("unchecked")
    	@Override
    	public List<Lines> findByTwoStations(String stationName1,
    			String stationName2) {
    		Query query = entityManager
    				.createNamedQuery(Lines.FIND_BY_TWO_STATIONS)
    				.setParameter(1, stationName1).setParameter(2, stationName2);

    		return query.getResultList();
    	}

		
}
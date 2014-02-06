package com.ita.edu.softserve.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.entity.Lines;

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
		public Class<Lines> getEntityClass() {
                
                return Lines.class;
        }
        
        public List<Lines> getLinesByStationName(String stationName){
        	Query query = entityManager.createNamedQuery(Lines.FIND_BY_STATION_NAME).setParameter(1, stationName);
        	List<Lines > list = query.getResultList();
        	return list;
        }
		
}
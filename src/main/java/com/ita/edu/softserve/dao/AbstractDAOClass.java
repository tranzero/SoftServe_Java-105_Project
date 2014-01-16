package com.ita.edu.softserve.daoiface;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AbstractDAOClass {
	public static final String PERSISTENCE_UNIT_NAME = "Java105";
	
	 public Object find(Query query) {
	        try {
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

}

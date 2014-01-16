package com.ita.edu.softserve.daoiface;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AbstractDAOClass {
	
	 public Object find(Query query) {
	        try {
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

}

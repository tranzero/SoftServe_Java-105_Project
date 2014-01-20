package com.ita.edu.softserve.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ita.edu.softserve.entity.BaseEntity;

public abstract class AbstractDAO <E extends BaseEntity> {
	public static final String PERSISTENCE_UNIT_NAME = "Java105";
	@PersistenceContext(name = PERSISTENCE_UNIT_NAME)
    protected EntityManager entityManager;
	 
	
	    public E findById(int id) {
	        return entityManager.find(getEntityClass(), id);
	    }

	   
	    public void save(E... entities) {
	        
	        for(E entity: entities){
	        entityManager.persist(entity);
	        }
	    }

	    
	    public void remove(E... entities) {
	        for(E entity: entities){
	        entityManager.remove(entity);
	        }
	    }

	 
	    public List<E> update(E... entities) {
	        List<E> entitiesResult =  new ArrayList<E>();
	        for(E entity: entities){
	         entitiesResult.add( entityManager.merge(entity));
	        }
	        return entitiesResult;
	
	    }

	   
	    public List<E> getAllEntities() {

	      return   entityManager.createQuery("From " + getEntityClass()
	                              .getCanonicalName()).getResultList();
	      
	    }
	    protected abstract Class<E> getEntityClass();
}

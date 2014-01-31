package com.ita.edu.softserve.dao;

import java.util.List;

public interface AbstractDAOIface<E> {
	public E findById(int id);
	
	@SuppressWarnings("unchecked")
	public void save(E... entities);
	
	@SuppressWarnings("unchecked")
	public void remove(E... entities);
	
	@SuppressWarnings("unchecked")
	public List<E> update(E... entities);
	
	public List<E> getAllEntities();
	
	public abstract Class<E> getEntityClass();

}

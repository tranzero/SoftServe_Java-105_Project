package com.ita.edu.softserve.manager;

import java.util.List;

import com.ita.edu.softserve.entity.Trips;


public interface TripsManager extends BaseManager {

	List<Trips> getAllTrips();
	
	List<Trips> getTripsForLimit(int firstElement, int count);
	
	public List<Trips> getTripsForPage(int pageNumber, int count);
	
	long getTripsListCount();

}

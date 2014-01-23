package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;

/**
 * Class under test
 * {@link com.ita.edu.softserve.service.impl.StationsServiceImpl}
 * 
 * @author Роман
 */
public class TestStationServiceImpl {
	
	/**
	 * Mock object.
	 */
	private StationsDAOImpl stationsDaoImpl;
	
	/**
	 * StationsService.
	 */
	private StationsServiceImpl stationServiceImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public final void setUp() {
		stationsDaoImpl = mock(StationsDAOImpl.class);
		stationServiceImpl = new StationsServiceImpl(stationsDaoImpl);
	}

	/**
	 * Test whether method do not return empty list. Method under test
	 * {@link com.ita.edu.softserve.service.impl.StationsServiceImpl#findAllStations()}
	 */
	@Test
	public final void testFindAllStationsIsEmpty() {
		List<Stations> listOfStations = new ArrayList<Stations>();
		Stations station = mock(Stations.class);
		listOfStations.add(station);
		when(stationsDaoImpl.getAllEntities()).thenReturn(listOfStations);
		List<Stations> stationList = stationServiceImpl.findAllStations();

		assertFalse(stationList.isEmpty());
	}

	/**
	 * Test if two list is equals. Method under test
	 * {@link com.ita.edu.softserve.service.impl.StationsServiceImpl#findAllStations()}
	 */
	@Test
	public final void testFindAllStationsEquals() {
		List<Stations> listOfStations = new ArrayList<Stations>();

		Stations station1 = mock(Stations.class);
		Stations station2 = mock(Stations.class);

		listOfStations.add(station1);
		listOfStations.add(station2);

		when(stationsDaoImpl.getAllEntities()).thenReturn(listOfStations);
		List<Stations> stationList = stationServiceImpl.findAllStations();

		assertTrue((listOfStations.size() == stationList.size())
				&& (listOfStations.containsAll(stationList)));
	}

	/**
	 * Method under test.
	 * {@link com.ita.edu.softserve.service.impl.StationsServiceImpl#findAllStations()}
	 */
	@Test
	public final void testFindAllStationsEmptyList() {
		List<Stations> listOfStations = new ArrayList<Stations>();
		when(stationsDaoImpl.getAllEntities()).thenReturn(listOfStations);
		List<Stations> stationList = stationServiceImpl.findAllStations();

		assertTrue((listOfStations.size() == stationList.size())
				&& (listOfStations.containsAll(stationList)));
	}

	/**
	 * Test method for Null.<br/>
	 * Method under test
	 * {@link com.ita.edu.softserve.service.impl.StationsServiceImpl#findAllStations()}
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public final void testFindAllStationsShouldThrowNullPointerException() {
		when(stationsDaoImpl.getAllEntities()).thenThrow(new NullPointerException());
		stationServiceImpl.findAllStations();
	}
}

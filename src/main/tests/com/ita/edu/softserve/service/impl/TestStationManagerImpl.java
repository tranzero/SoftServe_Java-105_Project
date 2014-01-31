package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.impl.StationsManagerImpl;

/**
 * Class under test
 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl}
 * 
 * @author Roman
 */
public class TestStationManagerImpl {

	/**
	 * Mock object.
	 */
	private StationsDAOImpl mockStationsDAOImpl;

	/**
	 * StationsManagerImpl.
	 */
	private StationsManagerImpl stationsManagerImpl;

	/**
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Before
	public final void setUp() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		mockStationsDAOImpl = mock(StationsDAOImpl.class);

		stationsManagerImpl = new StationsManagerImpl();
		Field fild = stationsManagerImpl.getClass().getDeclaredField(
				"stationDao");

		fild.setAccessible(true);
		fild.set(stationsManagerImpl, mockStationsDAOImpl);

	}

	/**
	 * Test whether method do not return empty list. Method under test
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Test
	public final void testFindAllStationsIsEmpty() throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		List<Stations> listOfStations = new ArrayList<Stations>();

		Stations station = mock(Stations.class);
		listOfStations.add(station);

		when(mockStationsDAOImpl.getAllEntities()).thenReturn(listOfStations);

		List<Stations> stationList = stationsManagerImpl.findAllStations();

		assertFalse(stationList.isEmpty());
	}

	/**
	 * Test if two list is equals. Method under test
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
	 */
	@Test
	public final void testFindAllStationsEquals() {
		List<Stations> listOfStations = new ArrayList<Stations>();

		Stations station1 = mock(Stations.class);
		Stations station2 = mock(Stations.class);

		listOfStations.add(station1);
		listOfStations.add(station2);

		when(mockStationsDAOImpl.getAllEntities()).thenReturn(listOfStations);
		List<Stations> stationList = stationsManagerImpl.findAllStations();

		assertTrue((listOfStations.size() == stationList.size())
				&& (listOfStations.containsAll(stationList)));
	}

	/**
	 * Method under test.
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
	 */
	@Test
	public final void testFindAllStationsEmptyList() {
		List<Stations> listOfStations = new ArrayList<Stations>();
		when(mockStationsDAOImpl.getAllEntities()).thenReturn(listOfStations);
		List<Stations> stationList = stationsManagerImpl.findAllStations();

		assertTrue((listOfStations.size() == stationList.size())
				&& (listOfStations.containsAll(stationList)));
	}

	/**
	 * Test method for Null.<br/>
	 * Method under test
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public final void testFindAllStationsShouldThrowNullPointerException() {
		when(mockStationsDAOImpl.getAllEntities()).thenThrow(
				new NullPointerException());
		stationsManagerImpl.findAllStations();
	}
}

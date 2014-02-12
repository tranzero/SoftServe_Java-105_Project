/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;
import com.ita.edu.softserve.manager.impl.TransportTravel;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;

/**
 * Class under test {@link com.ita.edu.softserve.manager.impl.LinesManagerImpl}
 * 
 * @author MPS
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class LineServiceImplTests {

	/**
	 * Mock object.
	 */
	private LinesDAOImpl mockLinesDaoImpl;

	/**
	 * LinesManagerImpl.
	 */
	private LinesManagerImpl linesManagerImpl;
	
	@Before
	public final void setUp() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		mockLinesDaoImpl = mock(LinesDAOImpl.class);

		linesManagerImpl = new LinesManagerImpl();
		Field fild = linesManagerImpl.getClass().getDeclaredField(
				"lineDao");

		fild.setAccessible(true);
		fild.set(linesManagerImpl, mockLinesDaoImpl);
	}

	/**
	 * Test for method which returns all lines.
	 * @author MatyashPetro 
	 */
	@Test
	public final void getFullLinesTest() {
		List<Lines> expectedLinesList = new ArrayList<Lines>();
		List<Lines> actualLinesList = null;
		Lines line1 = mock(Lines.class);
		Lines line2 = mock(Lines.class);
		expectedLinesList.add(line1);
		expectedLinesList.add(line2);
		when(mockLinesDaoImpl.getAllEntities()).thenReturn(expectedLinesList);
		actualLinesList = linesManagerImpl.getFullLines();
		assertTrue((expectedLinesList.containsAll(actualLinesList))
				&& (expectedLinesList.size() == actualLinesList.size()));
	}
	
	/**
	 * Test for method which returns all lines when table is empty.
	 * @author MatyashPetro 
	 */
	@Test
	public final void getFullLinesEmptyTest() {
		List<Lines> expectedLinesList = new ArrayList<Lines>();
		List<Lines> actualLinesList = null;
		when(mockLinesDaoImpl.getAllEntities()).thenReturn(expectedLinesList);
		actualLinesList = linesManagerImpl.getFullLines();
		assertTrue((expectedLinesList.containsAll(actualLinesList))
				&& (expectedLinesList.size() == actualLinesList.size()));
	}
	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.LinesManagerImpl#
	 * getLinesByTwoStations(Stations, Stations)}
	 */
	@Test
	public final void getLinesByTwoStationsTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";
		
		List<Lines> listOfLines = new ArrayList<Lines>();
		Lines line = mock(Lines.class);
		listOfLines.add(line);		
		
		List<Lines> expectedLines = Collections.singletonList(line);
		

		when(mockLinesDaoImpl.findByTwoStations(stationName1, stationName2)).thenReturn(
				listOfLines);
		List<Lines> actualLines = linesManagerImpl
				.getLinesByTwoStations(stationName1, stationName2);
		
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));		
	}

	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.LinesManagerImpl#
	 * getLinesByTwoStations(Stations, Stations)}
	 */
	@Test
	public final void getLinesByTwoStationsIfEmptyListTest() {
		String stationName1 = "Lviv";
		String stationName2 = "Kyiv";
		
		List<Lines> linesList = new ArrayList<Lines>();
		List<Lines> expectedLines = new ArrayList<Lines>();
		
		when(mockLinesDaoImpl.findByTwoStations(stationName1, stationName2)).thenReturn(linesList);
		List<Lines> actualLines = linesManagerImpl
				.getLinesByTwoStations(stationName1, stationName2);
		
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));		
	}

}
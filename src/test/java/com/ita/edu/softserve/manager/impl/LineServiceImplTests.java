package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;

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
	@Mock
	private LinesDAOImpl mockLinesDaoImpl;

	/**
	 * LinesManagerImpl.
	 */
	@InjectMocks
	private LinesManagerImpl linesManagerImpl = new LinesManagerImpl();

	// @Before
	// public final void setUp() throws NoSuchFieldException, SecurityException,
	// IllegalArgumentException, IllegalAccessException {
	//
	// mockLinesDaoImpl = mock(LinesDAOImpl.class);
	//
	// linesManagerImpl = new LinesManagerImpl();
	// Field fild = linesManagerImpl.getClass().getDeclaredField("lineDao");
	//
	// fild.setAccessible(true);
	// fild.set(linesManagerImpl, mockLinesDaoImpl);
	// }

	@Test
	public final void testFindAllLinesList() {
		List<Lines> expected = new ArrayList<Lines>();
		when(mockLinesDaoImpl.getAllEntities()).thenReturn(expected);
		List<Lines> actual = linesManagerImpl.getFullLines();
		assertEquals(expected, actual);
	}
	
	@Test (expected = RuntimeException.class)
	public final void testFindAllLinesListException(){
		when(mockLinesDaoImpl.getAllEntities()).thenThrow(new RuntimeException());
		linesManagerImpl.getFullLines();
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.LinesManagerImpl# getLinesByTwoStations(Stations, Stations)}
	 */
	@Test
	public final void getLinesByTwoStationsTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		List<Lines> listOfLines = new ArrayList<Lines>();
		Lines line = mock(Lines.class);
		listOfLines.add(line);

		List<Lines> expectedLines = Collections.singletonList(line);

		when(mockLinesDaoImpl.findByTwoStations(stationName1, stationName2))
				.thenReturn(listOfLines);
		List<Lines> actualLines = linesManagerImpl.getLinesByTwoStations(
				stationName1, stationName2);

		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.LinesManagerImpl# getLinesByTwoStations(Stations, Stations)}
	 */
	@Test
	public final void getLinesByTwoStationsIfEmptyListTest() {
		String stationName1 = "Lviv";
		String stationName2 = "Kyiv";

		List<Lines> linesList = new ArrayList<Lines>();
		List<Lines> expectedLines = new ArrayList<Lines>();

		when(mockLinesDaoImpl.findByTwoStations(stationName1, stationName2))
				.thenReturn(linesList);
		List<Lines> actualLines = linesManagerImpl.getLinesByTwoStations(
				stationName1, stationName2);

		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.LinesManagerImpl#getLinesByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, int sortOrder)}
	 */
	@Test
	public final void getLinesByTwoStForLimitTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		int firstElement = 0;
		int count = 10;
		int sortOrder = 0;

		Lines line = mock(Lines.class);
		List<Lines> expectedLines = Collections.singletonList(line);

		when(
				mockLinesDaoImpl.getLinesByTwoStForLimits(stationName1,
						stationName2, firstElement, count, sortOrder))
				.thenReturn(expectedLines);
		List<Lines> actualLines = linesManagerImpl.getLinesByTwoStForLimit(
				stationName1, stationName2, firstElement, count, sortOrder);

		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.LinesManagerImpl#getLinesByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, int sortOrder)}
	 */
	@Test
	public final void getLinesByTwoStForPageIfEmptyListTest() {
		String stationName1 = "Lviv";
		String stationName2 = "Kyiv";

		int firstElement = 0;
		int count = 10;
		int sortOrder = 0;

		List<Lines> expectedLines = new ArrayList<Lines>();

		when(
				mockLinesDaoImpl.getLinesByTwoStForLimits(stationName1,
						stationName2, firstElement, count, sortOrder))
				.thenReturn(expectedLines);
		List<Lines> actualLines = linesManagerImpl.getLinesByTwoStForLimit(
				stationName1, stationName2, firstElement, count, sortOrder);

		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.LinesManagerImpl#getLinesByTwoStCount(String stationName1, String stationName2)}
	 */
	@Test
	public final void getLinesByTwoStCountTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		long expectedLinesCount = 15;

		when(
				mockLinesDaoImpl.getLinesByTwoStListCount(stationName1,
						stationName2)).thenReturn(expectedLinesCount);
		long actualLinesCount = linesManagerImpl.getLinesByTwoStListCount(
				stationName1, stationName2);

		assertEquals(expectedLinesCount, actualLinesCount);
	}
}
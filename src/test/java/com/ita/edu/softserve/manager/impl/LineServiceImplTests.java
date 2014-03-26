package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.LinesManagerException;

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

	private String lineName = "NEW LINE TEST";
	private String stationName = "NEW STATION TEST";
	private Integer lineId = 100500;

	@Spy
	private Lines line = new Lines(lineName);

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testFindAllLinesList() {
		List<Lines> expected = new ArrayList<Lines>();
		when(mockLinesDaoImpl.getAllEntities()).thenReturn(expected);
		List<Lines> actual = linesManagerImpl.getFullLines();
		assertEquals(expected, actual);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test(expected = RuntimeException.class)
	public final void testFindAllLinesListException() {
		when(mockLinesDaoImpl.getAllEntities()).thenThrow(
				new RuntimeException());
		linesManagerImpl.getFullLines();
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testFindLineById() {
		Lines expected = new Lines("WTF");
		when(mockLinesDaoImpl.findById(lineId)).thenReturn(expected);
		Lines actual = linesManagerImpl.findByLineId(lineId);
		assertEquals(expected, actual);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindLineBuIdException() {
		when(mockLinesDaoImpl.findById(lineId)).thenThrow(
				new IllegalArgumentException());
		linesManagerImpl.findByLineId(lineId);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testFindByLineName() {
		Lines expected = new Lines("WTF");
		when(mockLinesDaoImpl.findByName(lineName)).thenReturn(expected);
		Lines actual = linesManagerImpl.findByLineName(lineName);
		assertEquals(expected, actual);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test(expected = LinesManagerException.class)
	public final void testFindByLineNameException() {
		when(mockLinesDaoImpl.findByName(lineName)).thenThrow(
				new LinesManagerException());
		linesManagerImpl.findByLineName(lineName);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testCreateLine() {
		boolean wasCreated = false;
		when(mockLinesDaoImpl.findByName(lineName)).thenReturn(null);
		wasCreated = linesManagerImpl.createLine(lineName);

		assertTrue(wasCreated);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateLineException() {
		doThrow(new IllegalArgumentException()).when(line).setLineName("");
		linesManagerImpl.createLine("");
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testCreateLineExisted() {
		boolean wasCreated = true;
		when(mockLinesDaoImpl.findByName(lineName)).thenReturn(new Lines());
		wasCreated = linesManagerImpl.createLine(lineName);

		assertFalse(wasCreated);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testDeleteLine() {
		boolean wasDeleted = false;
		when(mockLinesDaoImpl.findById(lineId)).thenReturn(new Lines());
		wasDeleted = linesManagerImpl.deleteLine(lineId);
		assertTrue(wasDeleted);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testDeleteLineWhenNull() {
		boolean wasDeleted = true;
		wasDeleted = linesManagerImpl.deleteLine(lineId);
		assertFalse(wasDeleted);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test(expected = NullPointerException.class)
	public final void testDeleteLineException() {
		when(mockLinesDaoImpl.findById(lineId)).thenReturn(line);
		doThrow(new NullPointerException()).when(mockLinesDaoImpl).remove(line);
		linesManagerImpl.deleteLine(lineId);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testUpdeteLine() {
		boolean wasUpdated = false;
		when(mockLinesDaoImpl.findById(lineId)).thenReturn(line);
		wasUpdated = linesManagerImpl.updateLine(lineId, lineName);
		assertTrue(wasUpdated);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testUpdateLineWhenNull() {
		boolean wasUpdated = true;
		when(mockLinesDaoImpl.findById(lineId)).thenReturn(null);
		wasUpdated = linesManagerImpl.updateLine(lineId, lineName);
		assertFalse(wasUpdated);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testUpdateLineException() {
		when(mockLinesDaoImpl.findById(lineId)).thenReturn(line);
		doThrow(new IllegalArgumentException()).when(line).setLineName("");
		linesManagerImpl.updateLine(lineId, "");
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testGetAllLinesCount() {
		long expected = 5;
		when(mockLinesDaoImpl.getAllLinesCount()).thenReturn(expected);
		long actual = linesManagerImpl.getAllLinesCount();
		assertEquals(expected, actual);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetAllLinesCountException() {
		when(mockLinesDaoImpl.getAllLinesCount()).thenThrow(
				new RuntimeException());
		linesManagerImpl.getAllLinesCount();
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void testGetAllLinesForLimits() {
		int firstElement = 0;
		int count = 10;
		int sortOrder = 0;

		List<Lines> expected = new ArrayList<Lines>();
		when(
				mockLinesDaoImpl.getAllLinesForLimits(firstElement, count,
						sortOrder)).thenReturn(expected);
		List<Lines> actual = linesManagerImpl.getAllLinesForLimit(firstElement,
				count, sortOrder);
		assertEquals(expected, actual);
	}

	/**
	 * @author MatyashPetro
	 */
	@Test
	public final void tesgGetAllLinesForPage() {
		int firstElement = 0;
		int count = 10;
		int sortOrder = 0;

		List<Lines> expected = new ArrayList<Lines>();
		when(
				mockLinesDaoImpl.getAllLinesForLimits(firstElement, count,
						sortOrder)).thenReturn(expected);
		List<Lines> actual = linesManagerImpl.getAllLinesForPage(firstElement,
				count, sortOrder);
		assertEquals(expected, actual);

	}

	@Test
	public final void testGetlinesByStationName() {
		List<Lines> expected = new ArrayList<Lines>();
		when(mockLinesDaoImpl.getLinesByStationName(stationName)).thenReturn(
				expected);
		List<Lines> actual = linesManagerImpl
				.getLinesByStationName(stationName);
		assertEquals(expected, actual);
	}

	@Test
	public final void testGetLinesByStationCount() {
		long expected = 5;
		when(mockLinesDaoImpl.getLinesByStationNameCount(stationName))
				.thenReturn(expected);
		long actual = linesManagerImpl.getLinesByStationCount(stationName);
		assertEquals(expected, actual);
	}

	@Test(expected = LinesManagerException.class)
	public final void testGetLinesByStationCountException() {
		when(mockLinesDaoImpl.getLinesByStationNameCount(stationName))
				.thenThrow(new RuntimeException());
		linesManagerImpl.getLinesByStationCount(stationName);
	}

	@Test
	public final void testGetLinesByStNameForLimit() {
		int firstElement = 0;
		int count = 10;
		int sortOrder = 0;

		List<Lines> expected = new ArrayList<Lines>();
		when(
				mockLinesDaoImpl.getLinesByStNameForLimits(stationName,
						firstElement, count, sortOrder)).thenReturn(expected);
		List<Lines> actual = linesManagerImpl.getLinesByStNameForLimit(stationName, firstElement, count, sortOrder);
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testGetLinesByStNameForPage(){
		int firstElement = 0;
		int count = 10;
		int sortOrder = 0;

		List<Lines> expected = new ArrayList<Lines>();
		when(
				mockLinesDaoImpl.getLinesByStNameForLimits(stationName,
						firstElement, count, sortOrder)).thenReturn(expected);
		List<Lines> actual = linesManagerImpl.getLinesByStNameForPage(stationName, firstElement, count, sortOrder);
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testGetLinesByTwoStForPage(){
		int firstElement = 0;
		int count = 10;
		int sortOrder = 0;

		List<Lines> expected = new ArrayList<Lines>();

		when(
				mockLinesDaoImpl.getLinesByTwoStForLimits(stationName,
						stationName, firstElement, count, sortOrder))
				.thenReturn(expected);
		List<Lines> actual = linesManagerImpl.getLinesByTwoStForPage(
				stationName, stationName, firstElement, count, sortOrder);

		assertEquals(expected, actual);
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
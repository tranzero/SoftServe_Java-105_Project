/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.entity.Lines;

/**
 * @author MPS
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class LineServiceImplTests {

	// @Mock
	// private LinesDAOImpl linesDaoImpl;

	// @InjectMocks
	// private LinesServiceImpl linesServiceImpl = new LinesServiceImpl();

	@Test
	public void getFullLinesTest() {
		List<Lines> testList = new ArrayList<Lines>();
		Lines lines = mock(Lines.class);
		testList.add(lines);
		testList.add(lines);
		LinesDAOImpl linesDaoImpl = mock(LinesDAOImpl.class);
		when(linesDaoImpl.getAllEntities()).thenReturn(testList);
		LinesServiceImpl lsi = new LinesServiceImpl(linesDaoImpl);
		assertTrue(Iterables.elementsEqual(testList, lsi.getFullLines()));
	}

	@Test
	public void getFullLinesWithEmptyListTest() {
		List<Lines> testList = new ArrayList<Lines>();
		LinesDAOImpl linesDaoImpl = mock(LinesDAOImpl.class);
		when(linesDaoImpl.getAllEntities()).thenReturn(testList);
		LinesServiceImpl lsi = new LinesServiceImpl(linesDaoImpl);
		assertTrue(Iterables.elementsEqual(testList, lsi.getFullLines()));
	}

	/*
	 * @Test public void getLinesByStationTest() {
	 * 
	 * List<StationsOnLine> stationsOnLines = new ArrayList<StationsOnLine>();
	 * StationsOnLine son = mock(StationsOnLine.class);
	 * 
	 * Stations st = mock(Stations.class);
	 * st.setStationsOnLines(stationsOnLines); st.setStationName("Struy");
	 * 
	 * Stations st1 = mock(Stations.class);
	 * st.setStationsOnLines(stationsOnLines); st.setStationName("Lviv");
	 * 
	 * 
	 * ///////////////LINES/////////////// List<Lines> testList = new
	 * ArrayList<Lines>();
	 * 
	 * Lines lines = mock(Lines.class);
	 * lines.setStationsOnLines(stationsOnLines); lines.setLineId(11);
	 * 
	 * Lines lines1 = mock(Lines.class);
	 * lines.setStationsOnLines(stationsOnLines); lines.setLineId(12);
	 * 
	 * testList.add(lines); testList.add(lines1); //testList.add(lines);
	 * //testList.add(lines); //LinesDAOImpl linesDaoImpl =
	 * mock(LinesDAOImpl.class);
	 * //when(linesDaoImpl.getLinesByStation("Struy")).thenReturn(testList);
	 * //assertTrue(testList.containsAll(linesDaoImpl.getFullLines()));
	 * //assertTrue(linesDaoImpl.getFullLines().containsAll(testList)); }
	 */
	
	@Test 
	public void getLinesByStationTest(){
		List<Lines> lines = new ArrayList<Lines>();
		LinesDAOImpl linesDAO = mock(LinesDAOImpl.class);
		when(linesDAO.getLinesByStation("stationName")).thenReturn(lines);
		LinesServiceImpl LSImpl = new LinesServiceImpl(linesDAO);
		assertTrue(Iterables.elementsEqual(LSImpl.getLinesByStation("stationName"),lines));
		
		
	}
}

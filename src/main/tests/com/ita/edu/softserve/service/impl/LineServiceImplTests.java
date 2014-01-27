/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sound.sampled.Line;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * Class under test {@link com.ita.edu.softserve.service.impl.LinesServiceImpl}
 * 
 * @author MPS
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class LineServiceImplTests {

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
	public void getLinesByStationTest() {

		Lines line = Mockito.mock(Lines.class);
		when(line.getLineId()).thenReturn(1);
		
		StationsOnLine stationOnLine = mock(StationsOnLine.class);
		when(stationOnLine.getLineId()).thenReturn(line);
		
		List<StationsOnLine> stlList = new ArrayList<StationsOnLine>();
		stlList.add(stationOnLine);
		
		StationsOnLineDAOImpl stl = mock(StationsOnLineDAOImpl.class);
		when(stl.findByStationId(Mockito.anyInt())).thenReturn(stlList);
		
		LinesServiceImpl lineService = new LinesServiceImpl(stl);
		List<Lines> actualLines = lineService.getLinesByStation(new Stations());
		
		Assert.notEmpty(actualLines);
		
		List<Lines> expectedLines = Collections.singletonList(line);
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	@Test
	public final void LinesTwoStationsCertainOrderTest() {

		List<Stations> stations = new ArrayList<Stations>();
		StationsDAOImpl stationsDAO = mock(StationsDAOImpl.class);
		LinesServiceImpl lsImpl = new LinesServiceImpl(stationsDAO);

		when(stationsDAO.findByStations("Pisochne")).thenReturn(stations);

		assertTrue(Iterables.elementsEqual(
				lsImpl.getLinesTwoStationsCertainOrder(new Stations(),
						new Stations()), stations));
	}
	
	@Test(expected = java.lang.NullPointerException.class)
	public final void LinesTwoStationsCertainOrderIfNullTest() {

		List<Stations> stations = new ArrayList<Stations>();
		StationsDAOImpl stationsDAO = mock(StationsDAOImpl.class);
		LinesServiceImpl lsImpl = new LinesServiceImpl(stationsDAO);

		when(stationsDAO.findByStations(null)).thenReturn(null);

		assertTrue(Iterables.elementsEqual(
				lsImpl.getLinesTwoStationsCertainOrder(new Stations(),
						new Stations()), stations));
	}

}
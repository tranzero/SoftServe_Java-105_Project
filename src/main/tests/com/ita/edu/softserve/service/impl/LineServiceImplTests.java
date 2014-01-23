/**
 * 
 */
package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.service.LinesService;

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

		List<StationsOnLine> stlList = new ArrayList<StationsOnLine>();
		StationsOnLineDAOImpl stl = mock(StationsOnLineDAOImpl.class);
		when(stl.findByStationId(1)).thenReturn(stlList);
		LinesServiceImpl LSImpl = new LinesServiceImpl(stl);
		assertTrue(Iterables.elementsEqual(
				LSImpl.getLinesByStation(new Stations()), stlList));

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
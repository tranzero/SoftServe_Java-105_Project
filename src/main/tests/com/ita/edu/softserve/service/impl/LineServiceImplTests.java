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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.StationsOnLine;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;

/**
 * Class under test {@link com.ita.edu.softserve.manager.impl.LinesManagerImpl}
 * 
 * @author MPS
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class LineServiceImplTests {

/*	@Test
	public void getFullLinesTest() {
		List<Lines> testList = new ArrayList<Lines>();
		Lines lines = mock(Lines.class);
		testList.add(lines);
		testList.add(lines);
		LinesDAOImpl linesDaoImpl = mock(LinesDAOImpl.class);
		when(linesDaoImpl.getAllEntities()).thenReturn(testList);
		LinesManagerImpl lsi = new LinesManagerImpl(linesDaoImpl);
		assertTrue(Iterables.elementsEqual(testList, lsi.getFullLines()));
	}

	@Test
	public void getFullLinesWithEmptyListTest() {
		List<Lines> testList = new ArrayList<Lines>();
		LinesDAOImpl linesDaoImpl = mock(LinesDAOImpl.class);
		when(linesDaoImpl.getAllEntities()).thenReturn(testList);
		LinesManagerImpl lsi = new LinesManagerImpl(linesDaoImpl);
		assertTrue(Iterables.elementsEqual(testList, lsi.getFullLines()));
	}

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
		
		LinesManagerImpl lineService = new LinesManagerImpl(stl);
		List<Lines> actualLines = lineService.getLinesByStation("");
		
		Assert.notEmpty(actualLines);
		
		List<Lines> expectedLines = Collections.singletonList(line);
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	
	*//***************************************//*
	
	 * THIS SECTION WILL BE CHANGED SOON
	 
	*//***************************************//*
	
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.LineServiceImpl#
	 * getLinesTwoStationsCertainOrder(Stations, Stations)}
	 
	@Test
	public final void LinesTwoStationsCertainOrderTest() {

		Lines line = Mockito.mock(Lines.class);
		when(line.getLineId()).thenReturn(1);

		StationsOnLine stationOnLine1 = mock(StationsOnLine.class);
		when(stationOnLine1.getLineId()).thenReturn(line);

		StationsOnLine stationOnLine2 = mock(StationsOnLine.class);
		when(stationOnLine2.getLineId()).thenReturn(line);

		List<StationsOnLine> stlList1 = new ArrayList<StationsOnLine>();
		stlList1.add(stationOnLine1);
		// gt int
		when(stlList1.get(0).getStationOrderNum()).thenReturn(30);

		List<StationsOnLine> stlList2 = new ArrayList<StationsOnLine>();
		stlList2.add(stationOnLine2);
		// lt int
		when(stlList2.get(0).getStationOrderNum()).thenReturn(20);

		StationsOnLineDAOImpl stl = mock(StationsOnLineDAOImpl.class);
		when(stl.findByStationId(Mockito.anyInt())).thenReturn(stlList1)
				.thenReturn(stlList2);

		LinesManagerImpl lineService = new LinesManagerImpl(stl);
		List<Lines> actualLines = lineService.getLinesTwoStationsCertainOrder("","");
//				new Stations(), new Stations());

		Assert.notEmpty(actualLines);

		List<Lines> expectedLines = Collections.singletonList(line);
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	
	 * Test for method: empty List {@link
	 * com.ita.edu.softserve.service.impl.LineServiceImpl#
	 * getLinesTwoStationsCertainOrder(Stations, Stations)}
	 
	@Test
	public final void LinesTwoStationsCertainOrderEmptyListTest() {

		Lines line = Mockito.mock(Lines.class);
		when(line.getLineId()).thenReturn(1);

		StationsOnLine stationOnLine1 = mock(StationsOnLine.class);
		when(stationOnLine1.getLineId()).thenReturn(line);

		StationsOnLine stationOnLine2 = mock(StationsOnLine.class);
		when(stationOnLine2.getLineId()).thenReturn(line);

		List<StationsOnLine> stlList1 = new ArrayList<StationsOnLine>();
		stlList1.add(stationOnLine1);
		// lt int
		when(stlList1.get(0).getStationOrderNum()).thenReturn(10);

		List<StationsOnLine> stlList2 = new ArrayList<StationsOnLine>();
		stlList2.add(stationOnLine2);
		// gt int
		when(stlList2.get(0).getStationOrderNum()).thenReturn(20);

		StationsOnLineDAOImpl stl = mock(StationsOnLineDAOImpl.class);
		when(stl.findByStationId(Mockito.anyInt())).thenReturn(stlList1)
				.thenReturn(stlList2);

		LinesManagerImpl lineService = new LinesManagerImpl(stl);
		List<Lines> actualLines = lineService.getLinesTwoStationsCertainOrder("","");
//				new Stations(), new Stations());

		List<Lines> expectedLines = new ArrayList<Lines>();
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	
	 * Test for method: Test if different lines in two Lists {@link
	 * com.ita.edu.softserve.service.impl.LineServiceImpl#
	 * getLinesTwoStationsCertainOrder(Stations, Stations)}
	 
	@Test
	public final void LinesTwoStationsCertainOrderIfDifferentLinesTest() {

		Lines line1 = Mockito.mock(Lines.class);
		when(line1.getLineId()).thenReturn(1);

		Lines line2 = Mockito.mock(Lines.class);
		when(line2.getLineId()).thenReturn(2);

		StationsOnLine stationOnLine1 = mock(StationsOnLine.class);
		when(stationOnLine1.getLineId()).thenReturn(line1);

		StationsOnLine stationOnLine2 = mock(StationsOnLine.class);
		when(stationOnLine2.getLineId()).thenReturn(line2);

		List<StationsOnLine> stlList1 = new ArrayList<StationsOnLine>();
		stlList1.add(stationOnLine1);

		List<StationsOnLine> stlList2 = new ArrayList<StationsOnLine>();
		stlList2.add(stationOnLine2);

		StationsOnLineDAOImpl stl = mock(StationsOnLineDAOImpl.class);
		when(stl.findByStationId(Mockito.anyInt())).thenReturn(stlList1)
				.thenReturn(stlList2);

		LinesManagerImpl lineService = new LinesManagerImpl(stl);
		List<Lines> actualLines = lineService.getLinesTwoStationsCertainOrder("","");
//				new Stations(), new Stations());

		List<Lines> expectedLines = new ArrayList<Lines>();
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}

	
	 * Test for method: Test if two Stations are the same {@link
	 * com.ita.edu.softserve.service.impl.LineServiceImpl#
	 * getLinesTwoStationsCertainOrder(Stations, Stations)}
	 
	@Test
	public final void LinesTwoStationsCertainOrderEqualsListsTest() {

		Lines line = Mockito.mock(Lines.class);
		when(line.getLineId()).thenReturn(1);

		StationsOnLine stationOnLine = mock(StationsOnLine.class);
		when(stationOnLine.getLineId()).thenReturn(line);

		List<StationsOnLine> stlList = new ArrayList<StationsOnLine>();
		stlList.add(stationOnLine);
		when(stlList.get(0).getStationOrderNum()).thenReturn(30);

		StationsOnLineDAOImpl stl = mock(StationsOnLineDAOImpl.class);
		when(stl.findByStationId(Mockito.anyInt())).thenReturn(stlList);

		LinesManagerImpl lineService = new LinesManagerImpl(stl);
		List<Lines> actualLines = lineService.getLinesTwoStationsCertainOrder("stationName1","stationName2");
//				new Stations(), new Stations());

		List<Lines> expectedLines = new ArrayList<Lines>();
		assertTrue(Iterables.elementsEqual(expectedLines, actualLines));
	}*/

}
package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TransportsCriteriaContainerImpl;

/**
 * Class under test
 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl}
 * 
 * @author Roman
 */
@RunWith(MockitoJUnitRunner.class)
public class TestTransportsManagerImpl {

	@Mock
	private TransportsDaoImpl mockTransportsDaoImpl;

	@Mock
	private UserNameService userName;

	@InjectMocks
	private TransportsManagerImpl transportsManagerImpl = new TransportsManagerImpl();

	private Transports transports;

	int transportsIdMock = 20;
	int illegalId = -1;
	private static final String mockTransportsCode = "T000000001";
	private static final String illegalTransportsCode = "T00000@@@1";

	@Before
	public final void setUp() {
		when(userName.getLoggedUsername()).thenReturn("roman");
		transports = mock(Transports.class);
	}

	/**
	 * Test the methods for Equals. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}.
	 */
	@Test()
	public void testFindTransportsById() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);
		
		Transports actualTransport = transportsManagerImpl
				.findTransportsById(transportsIdMock);
		
		verify(mockTransportsDaoImpl, times(1)).findById(transportsIdMock);
		assertEquals(transports, actualTransport);
	}

	/**
	 * Test the methods for <code>null</code>. the found entity instance or null
	 * if the entity does not exist Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}.
	 */
	@Test
	public final void testFindTransportsByIdForNull() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(null);

		Transports expectedTransport = transportsManagerImpl
				.findTransportsById(transportsIdMock);
		
		verify(mockTransportsDaoImpl, times(1)).findById(Mockito.anyInt());
		assertNull(expectedTransport);
	}

	/**
	 * IllegalArgumentException - if the argument is not a valid type for that
	 * Entity's primary key or is null.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindTransportsByIdException() {
		when(mockTransportsDaoImpl.findById(illegalId)).thenThrow(
				new IllegalArgumentException());

		transportsManagerImpl.findTransportsById(illegalId);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}.
	 */
	@Test()
	public void testFindTransportsByCode() {
		when(mockTransportsDaoImpl.findByCode(mockTransportsCode)).thenReturn(
				transports);
		Transports actual = transportsManagerImpl
				.findTransportsByCode(mockTransportsCode);
		
		verify(mockTransportsDaoImpl, times(1)).findByCode(mockTransportsCode);
		assertEquals(transports, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}.
	 */
	@Test
	public final void testFindTransportsByCodeForNull() {
		when(mockTransportsDaoImpl.findByCode(illegalTransportsCode))
				.thenReturn(null);

		Transports expectedTransport = transportsManagerImpl
				.findTransportsByCode(illegalTransportsCode);
		
		verify(mockTransportsDaoImpl, times(1)).findByCode(illegalTransportsCode);
		assertNull(expectedTransport);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}.
	 */
	@Test()
	public void testSaveTransports() {
		doNothing().when(mockTransportsDaoImpl).save(transports);

		transportsManagerImpl.saveTransports(transports);

		verify(mockTransportsDaoImpl, times(1)).save(transports);
	}

	/**
	 * IllegalArgumentException - if the instance is not an entity.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSaveTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.save(transports);

		transportsManagerImpl.saveTransports(transports);
	}

	/**
	 * EntityExistsException - if the entity already exists. (If the entity
	 * already exists, the EntityExistsException may be thrown when the persist
	 * operation is invoked, or the EntityExistsException or another
	 * PersistenceException may be thrown at flush or commit time.) Test method
	 * for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}.
	 */
	@Test(expected = EntityExistsException.class)
	public void testSaveTransportsException2() {
		transports.setTransportId(Mockito.anyInt());
		doThrow(new EntityExistsException()).when(mockTransportsDaoImpl).save(
				transports);

		transportsManagerImpl.saveTransports(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransports(com.ita.edu.softserve.entity.Transports[])}.
	 */
	@Test()
	public void testRemoveTransports() {
		doNothing().when(mockTransportsDaoImpl).remove(transports);

		transportsManagerImpl.removeTransports(transports);
		
		verify(mockTransportsDaoImpl, times(1)).remove(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransports(com.ita.edu.softserve.entity.Transports[])}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.remove(transports);

		transportsManagerImpl.removeTransports(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransportById(java.lang.Integer)}.
	 */
	@Test()
	public void testRemoveTransportById() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);
		doNothing().when(mockTransportsDaoImpl).remove(transports);

		transportsManagerImpl.removeTransportById(transportsIdMock);

		verify(mockTransportsDaoImpl, times(1)).findById(transportsIdMock);
		verify(mockTransportsDaoImpl, times(1)).remove(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransportById(java.lang.Integer)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportByIdException() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);

		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.remove(transports);

		transportsManagerImpl.removeTransportById(transportsIdMock);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}.
	 * .
	 */
	@Test()
	public void testUpdateTransports() {
		transportsManagerImpl.updateTransports(transports);

		verify(mockTransportsDaoImpl, times(1)).update(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}.
	 */
	@Test()
	public void testUpdateTransportsEquals() {
		List<Transports> expectedListOfTransports = new ArrayList<Transports>();
		Transports transport1 = mock(Transports.class);
		Transports transport2 = mock(Transports.class);

		expectedListOfTransports.add(transport1);
		expectedListOfTransports.add(transport2);
		expectedListOfTransports.add(transports);

		when(mockTransportsDaoImpl.update(transports)).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.updateTransports(transports);
		
		verify(mockTransportsDaoImpl, times(1)).update(transports);
		assertEquals(expectedListOfTransports, actualListOfTransports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}.
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.update(transports);

		transportsManagerImpl.updateTransports(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}.
	 */
	@Test()
	public void testSaveOrUpdateTransport() {
		doNothing().when(mockTransportsDaoImpl).saveOrUpdate(transports);
		
		transportsManagerImpl.saveOrUpdateTransport(transports);

		verify(mockTransportsDaoImpl, times(1)).saveOrUpdate(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}.
	 */
	@Test()
	public void testSaveOrUpdateTransportForNullId() {
//		Transports transports = new Transports();
		transports.setTransportId(null);
		
		doNothing().when(mockTransportsDaoImpl).saveOrUpdate(transports);

		transportsManagerImpl.saveOrUpdateTransport(transports);

		verify(mockTransportsDaoImpl, times(1)).saveOrUpdate(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSaveOrUpdateTransportException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.saveOrUpdate(transports);

		transportsManagerImpl.saveOrUpdateTransport(transports);
	}

	/**
	 * Method under test. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}.
	 */
	@Test
	public final void testGetAllTransportsEmptyList() {
		List<Transports> expected = new ArrayList<Transports>();

		when(mockTransportsDaoImpl.getAllEntities()).thenReturn(
				expected);
		List<Transports> actual = transportsManagerImpl
				.getAllTransports();
		
		verify(mockTransportsDaoImpl, times(1)).getAllEntities();
		assertEquals(expected, actual);

//		assertTrue((expected.size() == actual.size())
//				&& (expected.containsAll(actual)));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}.
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetAllTransportsShouldThrowNullPointerException() {

		when(mockTransportsDaoImpl.getAllEntities()).thenThrow(
				new RuntimeException());

		transportsManagerImpl.getAllTransports();
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}.
	 */
	@Test
	public final void getTransportByTwoStationsTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		List<TransportTravel> listOfTTravel = new ArrayList<TransportTravel>();
		TransportTravel ttravel = mock(TransportTravel.class);
		listOfTTravel.add(ttravel);

		List<TransportTravel> expectedTTravel = Collections
				.singletonList(ttravel);

		when(
				mockTransportsDaoImpl.findByTwoStations(stationName1,
						stationName2)).thenReturn(listOfTTravel);
		List<TransportTravel> actualTTravel = transportsManagerImpl
				.getTransportByTwoStations(stationName1, stationName2);

		assertTrue(Iterables.elementsEqual(expectedTTravel, actualTTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}.
	 * If empty list
	 */
	@Test
	public final void getTransportByTwoStationsIfEmptyListTest() {
		String stationName1 = "Rahiv";
		String stationName2 = "Lviv";

		List<TransportTravel> listOfTTravel = new ArrayList<TransportTravel>();
		List<TransportTravel> expectedTTravel = new ArrayList<TransportTravel>();

		when(
				mockTransportsDaoImpl.findByTwoStations(stationName1,
						stationName2)).thenReturn(listOfTTravel);
		List<TransportTravel> actualTTravel = transportsManagerImpl
				.getTransportByTwoStations(stationName1, stationName2);

		assertTrue(Iterables.elementsEqual(expectedTTravel, actualTTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}.
	 */
	@Test(expected = RuntimeException.class)
	public final void getTransportByTwoStationsIfExceptionTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		List<TransportTravel> listOfTTravel = new ArrayList<TransportTravel>();
		TransportTravel ttravel = mock(TransportTravel.class);
		listOfTTravel.add(ttravel);

		when(
				mockTransportsDaoImpl.findByTwoStations(stationName1,
						stationName2)).thenThrow(new RuntimeException());

		transportsManagerImpl.getTransportByTwoStations(stationName1,
				stationName2);
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, String sDate)}.
	 */
	@Test
	public final void getTransportByTwoStForLimitTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		int firstElement = 0;
		int count = 10;

		TransportTravel transportTravel = mock(TransportTravel.class);
		List<TransportTravel> expectedTransportTravel = Collections
				.singletonList(transportTravel);

		when(
				mockTransportsDaoImpl.getTransportByTwoStForLimits(
						stationName1, stationName2, firstElement, count, null))
				.thenReturn(expectedTransportTravel);
		List<TransportTravel> actualTransportTravel = transportsManagerImpl
				.getTransportByTwoStForLimit(stationName1, stationName2,
						firstElement, count, null);

		assertTrue(Iterables.elementsEqual(expectedTransportTravel,
				actualTransportTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, String sDate)}.
	 */
	@Test
	public final void getTransportByTwoStForLimitIfEmptyListTest() {
		String stationName1 = "Lviv";
		String stationName2 = "Kyiv";

		int firstElement = 0;
		int count = 10;

		List<TransportTravel> expectedTransportTravel = new ArrayList<TransportTravel>();

		when(
				mockTransportsDaoImpl.getTransportByTwoStForLimits(
						stationName1, stationName2, firstElement, count, null))
				.thenReturn(expectedTransportTravel);

		List<TransportTravel> actualTransportTravel = transportsManagerImpl
				.getTransportByTwoStForLimit(stationName1, stationName2,
						firstElement, count, null);

		assertTrue(Iterables.elementsEqual(expectedTransportTravel,
				actualTransportTravel));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStListCount(String stationName1, String stationName2)}.
	 */
	@Test
	public final void getLinesByTwoStCountTest() {
		String stationName1 = "Pisochne";
		String stationName2 = "Sknyliv";

		long expectedTransportTravelCount = 12;

		when(
				mockTransportsDaoImpl.getTransportByTwoStListCount(
						stationName1, stationName2)).thenReturn(
				expectedTransportTravelCount);

		long actualTransportTravelCount = transportsManagerImpl
				.getTransportByTwoStListCount(stationName1, stationName2);

		assertEquals(expectedTransportTravelCount, actualTransportTravelCount);
	}

	/*--------------------------*/

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getTransportsListWithContainers(com.ita.edu.softserve.validationcontainers.PageInfoContainer, com.ita.edu.softserve.validationcontainers.TransportsCriteriaContainer)}.
	 */
	@Test
	public void testGetTransportsListWithContainers() {
		// fail("Not yet implemented"); // TODO
		PageInfoContainer container = mock(PageInfoContainerImpl.class);
		TransportsCriteriaContainer transportCriteriaContainer = mock(TransportsCriteriaContainerImpl.class);

		List<Transports> expectedListOfTransports = new ArrayList<Transports>();

		when(
				transportsManagerImpl.getTransportsListWithPaging(1, 10,
						"T000000001", "Stryy-Pyatnychany", "1000000000003",
						150, 150, 150, 24.0, "routeCode", "ASC")).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.getTransportsListWithContainers(container,
						transportCriteriaContainer);

		assertEquals(expectedListOfTransports, actualListOfTransports);
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getTransportsListWithPaging(int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Double, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetTransportsListWithPaging() {

		List<Transports> expectedListOfTransports = new ArrayList<Transports>();

		when(
				mockTransportsDaoImpl.getTransportsList(1, 10, "T000000001",
						"Stryy-Pyatnychany", "1000000000003", 150, 150, 150,
						24.0, "routeCode", "ASC")).thenReturn(
				expectedListOfTransports);

		List<Transports> actualListOfTransports = transportsManagerImpl
				.getTransportsListWithPaging(1, 10, "T000000001",
						"Stryy-Pyatnychany", "1000000000003", 150, 150, 150,
						24.0, "routeCode", "ASC");

		assertEquals(expectedListOfTransports, actualListOfTransports);
	}
}

package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}
	 */
	@Test()
	public void testFindTransportsById() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);
		Transports actualTransport = transportsManagerImpl
				.findTransportsById(transportsIdMock);

		assertEquals(transports, actualTransport);
	}

	/**
	 * Test the methods for <code>null</code>. the found entity instance or null
	 * if the entity does not exist Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}
	 */
	@Test
	public final void testFindTransportsByIdForNull() {
		when(mockTransportsDaoImpl.findById(Mockito.anyInt())).thenReturn(null);

		Transports expectedTransport = transportsManagerImpl
				.findTransportsById(transportsIdMock);

		assertNull(expectedTransport);
	}

	/**
	 * IllegalArgumentException - if the argument is not a valid type for that
	 * Entity's primary key or is null.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsById(int)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindTransportsByIdException() {
		when(mockTransportsDaoImpl.findById(illegalId)).thenThrow(
				new IllegalArgumentException());

		transportsManagerImpl.findTransportsById(illegalId);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}
	 */
	@Test()
	public void testFindTransportsByCode() {
		when(mockTransportsDaoImpl.findByCode(mockTransportsCode)).thenReturn(
				transports);
		Transports actual = transportsManagerImpl
				.findTransportsByCode(mockTransportsCode);

		assertEquals(transports, actual);
	}

	/**
	 * IllegalArgumentException - if position does not correspond to a
	 * positional parameter of the query or if the argument is of incorrect type
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}
	 */
	// @Test(expected = IllegalArgumentException.class)
	public final void testFindTransportsByCodeException() {
		when(mockTransportsDaoImpl.findByCode(illegalTransportsCode))
				.thenThrow(new IllegalArgumentException());

		transportsManagerImpl.findTransportsByCode(illegalTransportsCode);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#findTransportsByCode(java.lang.String)}
	 */
	@Test
	public final void testFindTransportsByCodeForNull() {
		when(mockTransportsDaoImpl.findByCode(illegalTransportsCode))
				.thenReturn(null);

		Transports expectedTransport = transportsManagerImpl
				.findTransportsByCode(illegalTransportsCode);

		assertNull(expectedTransport);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}
	 */
	@Test()
	public void testSaveTransports() {
		transportsManagerImpl.saveTransports(transports);

		verify(mockTransportsDaoImpl).save(transports);
	}

	/**
	 * IllegalArgumentException - if the instance is not an entity.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}
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
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
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
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test()
	public void testRemoveTransports() {
		transportsManagerImpl.removeTransports(transports);

		verify(mockTransportsDaoImpl).remove(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.remove(eq(transports));

		transportsManagerImpl.removeTransports(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransportById(java.lang.Integer)}
	 * .
	 */
	@Test()
	public void testRemoveTransportById() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);

		transportsManagerImpl.removeTransportById(transportsIdMock);

		verify(mockTransportsDaoImpl).findById(transportsIdMock);
		verify(mockTransportsDaoImpl).remove(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#removeTransportById(java.lang.Integer)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportByIdException() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);

		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.remove(eq(transports));

		transportsManagerImpl.removeTransportById(transportsIdMock);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}
	 * .
	 */
	@Test()
	public void testUpdateTransports() {
		transportsManagerImpl.updateTransports(transports);

		verify(mockTransportsDaoImpl).update(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#updateTransports(com.ita.edu.softserve.entity.Transports[])}
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
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}
	 * .
	 */
	@Test()
	public void testSaveOrUpdateTransport() {
		transportsManagerImpl.saveOrUpdateTransport(transports);

		verify(mockTransportsDaoImpl).saveOrUpdate(transports);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#saveOrUpdateTransport(com.ita.edu.softserve.entity.Transports)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSaveOrUpdateTransportException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.saveOrUpdate(transports);

		transportsManagerImpl.saveOrUpdateTransport(transports);
	}

	/**
	 * Test whether method do not return empty list. Method under test Test
	 * method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 * .
	 */
	@Test
	public final void testGetAllTransportsIsEmpty() {
		List<Transports> listOfTransports = new ArrayList<Transports>();
		Transports transport = mock(Transports.class);
		listOfTransports.add(transport);

		when(mockTransportsDaoImpl.getAllEntities()).thenReturn(
				listOfTransports);
		List<Transports> transportList = transportsManagerImpl
				.getAllTransports();

		assertFalse(transportList.isEmpty());
	}

	/**
	 * Test if two list is equals. Method under test Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 * .
	 */
	@Test
	public final void testGetAllTransportsEquals() {
		List<Transports> listOfTransports = new ArrayList<Transports>();

		Transports transport1 = mock(Transports.class);
		Transports transport2 = mock(Transports.class);

		listOfTransports.add(transport1);
		listOfTransports.add(transport2);

		when(mockTransportsDaoImpl.getAllEntities()).thenReturn(
				listOfTransports);
		List<Transports> transportList = transportsManagerImpl
				.getAllTransports();

		assertTrue((listOfTransports.size() == transportList.size())
				&& (listOfTransports.containsAll(transportList)));
	}

	/**
	 * Method under test. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 * .
	 */
	@Test
	public final void testGetAllTransportsEmptyList() {
		List<Transports> listOfTransports = new ArrayList<Transports>();

		when(mockTransportsDaoImpl.getAllEntities()).thenReturn(
				listOfTransports);
		List<Transports> transportList = transportsManagerImpl
				.getAllTransports();

		assertTrue((listOfTransports.size() == transportList.size())
				&& (listOfTransports.containsAll(transportList)));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testGetAllTransportsShouldThrowNullPointerException() {

		when(mockTransportsDaoImpl.getAllEntities()).thenThrow(
				new IllegalArgumentException());

		transportsManagerImpl.getAllTransports();
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}
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
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}
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
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStations(Stations, Stations)}
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
		
		transportsManagerImpl.getTransportByTwoStations(stationName1, stationName2);
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, String sDate)}
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
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStForLimit(String stationName1, String stationName2, int firstElement, int count, String sDate)}
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
	 * {@link com.ita.edu.softserve.service.impl.TransportManagerImpl# getTransportByTwoStListCount(String stationName1, String stationName2)}
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
}

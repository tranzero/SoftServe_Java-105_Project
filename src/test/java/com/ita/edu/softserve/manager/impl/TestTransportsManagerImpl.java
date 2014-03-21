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

	@InjectMocks
	private TransportsManagerImpl transportsManagerImpl = new TransportsManagerImpl();

	int transportsIdMock = 20;
	int illegalId = -1;

	private Transports transports;

	@Before
	public final void setUp() {
		transports = mock(Transports.class);
	}

	/**
	 * Test the methods for Equals.
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
	 * if the entity does not exist
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
	 * Entity's primary key or is null
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindTransportsByIdException() {
		when(mockTransportsDaoImpl.findById(illegalId)).thenThrow(
				new IllegalArgumentException());

		transportsManagerImpl.findTransportsById(illegalId);
	}

	/**
	 * 
	 */
	@Test()
	public void testFindTransportsByCode() {
		when(mockTransportsDaoImpl.findByCode("T000000001")).thenReturn(
				transports);
		Transports actual = transportsManagerImpl
				.findTransportsByCode("T000000001");

		assertEquals(transports, actual);
	}

	/**
	 * IllegalArgumentException - if position does not correspond to a
	 * positional parameter of the query or if the argument is of incorrect type
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindTransportsByCodeException() {
		when(mockTransportsDaoImpl.findByCode("T0000000@1")).thenThrow(
				new IllegalArgumentException());

		transportsManagerImpl.findTransportsByCode("T0000000@1");
	}

	/**
	 * 
	 */
	@Test()
	public void testSaveTransports() {
		transportsManagerImpl.saveTransports(transports);

		verify(mockTransportsDaoImpl).save(transports);
	}

	/**
	 * IllegalArgumentException - if the instance is not an entity
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
	 * PersistenceException may be thrown at flush or commit time.)
	 */
	@Test(expected = EntityExistsException.class)
	public void testSaveTransportsException2() {
		transports.setTransportId(Mockito.anyInt());
		doThrow(new EntityExistsException()).when(mockTransportsDaoImpl).save(
				transports);

		transportsManagerImpl.saveTransports(transports);
	}

	@Test()
	public void testRemoveTransports() {
		transportsManagerImpl.removeTransports(transports);

		verify(mockTransportsDaoImpl).remove(transports);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.remove(eq(transports));

		transportsManagerImpl.removeTransports(transports);
	}

	@Test()
	public void testRemoveTransportById() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);

		transportsManagerImpl.removeTransportById(transportsIdMock);

		verify(mockTransportsDaoImpl).findById(transportsIdMock);
		verify(mockTransportsDaoImpl).remove(transports);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRemoveTransportByIdException() {
		when(mockTransportsDaoImpl.findById(transportsIdMock)).thenReturn(
				transports);

		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.remove(eq(transports));

		transportsManagerImpl.removeTransportById(transportsIdMock);
	}

	@Test()
	public void testUpdateTransports() {
		transportsManagerImpl.updateTransports(transports);

		verify(mockTransportsDaoImpl).update(transports);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUpdateTransportsException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.update(transports);

		transportsManagerImpl.updateTransports(transports);
	}

	@Test()
	public void testSaveOrUpdateTransport() {
		transportsManagerImpl.saveOrUpdateTransport(transports);

		verify(mockTransportsDaoImpl).saveOrUpdate(transports);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveOrUpdateTransportException() {
		doThrow(new IllegalArgumentException()).when(mockTransportsDaoImpl)
				.saveOrUpdate(transports);

		transportsManagerImpl.saveOrUpdateTransport(transports);
	}

	/**
	 * Test whether method do not return empty list. Method under test
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 */
	@Test
	public final void testFindAllTransportsIsEmpty() {
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
	 * Test if two list is equals. Method under test
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 */
	@Test
	public final void testFindAllTransportsEquals() {
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
	 * Method under test.
	 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl#getAllTransports()}
	 */
	@Test
	public final void testFindAllTransportsEmptyList() {
		List<Transports> listOfTransports = new ArrayList<Transports>();

		when(mockTransportsDaoImpl.getAllEntities()).thenReturn(
				listOfTransports);
		List<Transports> transportList = transportsManagerImpl
				.getAllTransports();

		assertTrue((listOfTransports.size() == transportList.size())
				&& (listOfTransports.containsAll(transportList)));
	}

	/**
	 * Test method for Null.<br/>
	 * Method under test
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
	 */
	@Test(expected = IllegalStateException.class)
	public final void testFindAllTransportsShouldThrowNullPointerException() {

		when(mockTransportsDaoImpl.getAllEntities()).thenThrow(
				new IllegalStateException());

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
}

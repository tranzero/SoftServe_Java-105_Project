package com.ita.edu.softserve.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;

/**
 * Class under test
 * {@link com.ita.edu.softserve.manager.impl.TransportsManagerImpl}
 * @author Roman
 */
public class TestTransportsManagerImpl {

	/**
	 * Mock object.
	 */
	private TransportsDaoImpl mockTransportsDaoImpl;

	/**
	 * TransportsManagerImpl.
	 */
	private TransportsManagerImpl transportsManagerImpl;

	/**
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws java.lang.Exception
	 */
	@Before
	public final void setUp() throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException {

		mockTransportsDaoImpl = mock(TransportsDaoImpl.class);

		transportsManagerImpl = new TransportsManagerImpl();
		Field fild = transportsManagerImpl.getClass().getDeclaredField(
				"transportsDao");

		fild.setAccessible(true);
		fild.set(transportsManagerImpl, mockTransportsDaoImpl);
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
	@Test(expected = java.lang.NullPointerException.class)
	public final void testFindAllTransportsShouldThrowNullPointerException() {
		when(mockTransportsDaoImpl.getAllEntities()).thenThrow(
				new NullPointerException());

		transportsManagerImpl.getAllTransports();
	}
	
	/**
	 * Test the methods for Equals.
	 */
	@Test
	public final void testFindTransportsByIdForEquals() {
		Transports actualTransport = mock(Transports.class);

		when(mockTransportsDaoImpl.findById(Mockito.anyInt())).thenReturn(
				actualTransport);
		Transports expectedTransport = transportsManagerImpl.findTransportsById(7);

		assertEquals(expectedTransport, actualTransport);
	}

	/**
	 * Test the methods for <code>null</code>.
	 */
	@Test
	public final void testFindTransportsByIdForNull() {
		when(mockTransportsDaoImpl.findById(Mockito.anyInt())).thenReturn(null);

		Transports expectedTransport = transportsManagerImpl.findTransportsById(3);

		assertNull(expectedTransport);
	}
}

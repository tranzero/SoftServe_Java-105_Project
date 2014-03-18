package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.ResponsesDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Responses;

/**
 * Class under test
 * {@link com.ita.edu.softserve.manager.impl.ResponsesManagerImpl}
 * 
 * @author yuraloga
 */
public class ResponsesManagerImplTest {

	/**
	 * Mock object.
	 */
	private ResponsesDAOImpl mockResponsesDaoImpl;

	/**
	 * ResponsesManagerImpl
	 */
	private ResponsesManagerImpl responsesManagerImpl;

	private Integer routeId = 10;
	
	private Integer tripId = 11;
	
	private Integer transportId = 12;

	/**
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Before
	public final void setUp() throws NoSuchFieldException, 
			IllegalArgumentException, IllegalAccessException {

		mockResponsesDaoImpl = mock(ResponsesDAOImpl.class);

		responsesManagerImpl = new ResponsesManagerImpl();
		Field field = responsesManagerImpl.getClass().getDeclaredField(
				"responsesDao");

		field.setAccessible(true);
		field.set(responsesManagerImpl, mockResponsesDaoImpl);
	}

	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getResponsesByRouteId(routeId)}
	 */
	@Test
	public final void getResponsesByRouteIdTest() {
		List<Responses> listOfResponses = new ArrayList<Responses>();
		Responses response = mock(Responses.class);
		listOfResponses.add(response);		
		
		List<Responses> expectedResponses = Collections.singletonList(response);
		

		when(mockResponsesDaoImpl.findResponsesByRouteId(routeId)).thenReturn(
				listOfResponses);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByRouteId(routeId);
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}

	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getResponsesByRouteId(routeId)}
	 * If empty list
	 */
	@Test
	public final void getResponsesByRouteIdIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();
		
		when(mockResponsesDaoImpl.findResponsesByRouteId(routeId)).thenReturn(responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByRouteId(routeId);
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}
	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getResponsesByTripId(tripId)}
	 */
	@Test
	public final void getResponsesByTripIdTest() {
		List<Responses> listOfResponses = new ArrayList<Responses>();
		Responses response = mock(Responses.class);
		listOfResponses.add(response);		
		
		List<Responses> expectedResponses = Collections.singletonList(response);
		

		when(mockResponsesDaoImpl.findResponsesByTripId(tripId)).thenReturn(
				listOfResponses);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTripId(tripId);
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}
	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getResponsesByTripId(tripId)}
	 * If empty list
	 */
	@Test
	public final void getResponsesByTripIdIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();
		
		when(mockResponsesDaoImpl.findResponsesByTripId(tripId)).thenReturn(responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTripId(tripId);
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}
	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getResponsesByTransportId(transportId)}
	 */
	@Test
	public final void getResponsesByTransportIdTest() {
		List<Responses> listOfResponses = new ArrayList<Responses>();
		Responses response = mock(Responses.class);
		listOfResponses.add(response);		
		
		List<Responses> expectedResponses = Collections.singletonList(response);
		

		when(mockResponsesDaoImpl.findResponsesByTranportId(transportId)).thenReturn(
				listOfResponses);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTransportId(transportId);
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}

	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getResponsesByTransportId(transportId)}
	 * If empty list
	 */
	@Test
	public final void getResponsesByTransportIdIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();
		
		when(mockResponsesDaoImpl.findResponsesByTranportId(transportId)).thenReturn(responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTransportId(transportId);
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}
	
	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getUncheckedResponses()}
	 */
	@Test
	public final void getUncheckedResponsesTest() {
		List<Responses> listOfResponses = new ArrayList<Responses>();
		Responses response = mock(Responses.class);
		listOfResponses.add(response);		
		
		List<Responses> expectedResponses = Collections.singletonList(response);
		

		when(mockResponsesDaoImpl.findUncheckedResponses()).thenReturn(
				listOfResponses);
		List<Responses> actualResponses = responsesManagerImpl
				.getUncheckedResponses();
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}

	/**
	 * Test for method {@link
	 * com.ita.edu.softserve.service.impl.ResponsesManagerImpl#
	 * getUncheckedResponses()}
	 * If empty list
	 */
	@Test
	public final void getUncheckedResponsesIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();
		
		when(mockResponsesDaoImpl.findUncheckedResponses()).thenReturn(responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getUncheckedResponses();
		
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));		
	}

}

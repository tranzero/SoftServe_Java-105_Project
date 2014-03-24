package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.ResponsesDAOImpl;
import com.ita.edu.softserve.dao.impl.TripsDAOImpl;
import com.ita.edu.softserve.dao.impl.UsersDAOImpl;
import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.exception.ResponsesManagerException;
import com.ita.edu.softserve.manager.ResponsesManager;

/**
 * Class under test
 * {@link com.ita.edu.softserve.manager.impl.ResponsesManagerImpl}
 * 
 * @author yuraloga
 */
@RunWith(MockitoJUnitRunner.class)
public class ResponsesManagerImplTest {

	/**
	 * Mock object.
	 */
	@Mock
	private ResponsesDAOImpl mockResponsesDaoImpl;

	/**
	 * Mock object.
	 */
	@Mock
	private UsersDAOImpl usersDaoImpl;

	/**
	 * Mock object.
	 */
	@Mock
	private TripsDAOImpl tripsDAOImpl;

	/**
	 * ResponsesManagerImpl
	 */
	@InjectMocks
	private ResponsesManagerImpl responsesManagerImpl = new ResponsesManagerImpl();

	private Integer routeId = 10;

	private Integer tripId = 11;

	private Integer transportId = 12;

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByRouteId(routeId)}
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

		verify(mockResponsesDaoImpl, times(1)).findResponsesByRouteId(routeId);
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByRouteId(routeId)}
	 * If empty list
	 */
	@Test
	public final void getResponsesByRouteIdIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();

		when(mockResponsesDaoImpl.findResponsesByRouteId(routeId)).thenReturn(
				responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByRouteId(routeId);

		verify(mockResponsesDaoImpl, times(1)).findResponsesByRouteId(routeId);
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByRouteId(routeId)}
	 * If exception
	 */
	@Test
	public final void getResponsesByRouteIdIfExceptionTest() {

		RuntimeException runtimeEx = new RuntimeException();
		ResponsesManagerException actualException = null;

		when(mockResponsesDaoImpl.findResponsesByRouteId(routeId)).thenThrow(
				runtimeEx);
		try {

			responsesManagerImpl.getResponsesByRouteId(routeId);

		} catch (ResponsesManagerException responsesManagerEx) {
			actualException = responsesManagerEx;
		}

		assertNotNull(actualException);
		assertEquals(runtimeEx, actualException.getCause());
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTripId(tripId)}
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

		verify(mockResponsesDaoImpl, times(1)).findResponsesByTripId(tripId);
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTripId(tripId)}
	 * If empty list
	 */
	@Test
	public final void getResponsesByTripIdIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();

		when(mockResponsesDaoImpl.findResponsesByTripId(tripId)).thenReturn(
				responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTripId(tripId);

		verify(mockResponsesDaoImpl, times(1)).findResponsesByTripId(tripId);
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTripId(routeId)}
	 * If exception
	 */
	@Test
	public final void getResponsesByTripIdIfExceptionTest() {
		RuntimeException runtimeEx = new RuntimeException();
		ResponsesManagerException actualException = null;

		when(mockResponsesDaoImpl.findResponsesByTripId(tripId)).thenThrow(
				runtimeEx);
		try {

			responsesManagerImpl.getResponsesByTripId(tripId);

		} catch (ResponsesManagerException responsesManagerEx) {
			actualException = responsesManagerEx;
		}

		assertNotNull(actualException);
		assertEquals(runtimeEx, actualException.getCause());
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTransportId(transportId)}
	 */
	@Test
	public final void getResponsesByTransportIdTest() {
		List<Responses> listOfResponses = new ArrayList<Responses>();
		Responses response = mock(Responses.class);
		listOfResponses.add(response);

		List<Responses> expectedResponses = Collections.singletonList(response);

		when(mockResponsesDaoImpl.findResponsesByTransportId(transportId))
				.thenReturn(listOfResponses);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTransportId(transportId);

		verify(mockResponsesDaoImpl, times(1)).findResponsesByTransportId(
				transportId);
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTransportId(transportId)}
	 * If empty list
	 */
	@Test
	public final void getResponsesByTransportIdIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();

		when(mockResponsesDaoImpl.findResponsesByTransportId(transportId))
				.thenReturn(responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTransportId(transportId);

		verify(mockResponsesDaoImpl, times(1)).findResponsesByTransportId(
				transportId);
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTransportId(routeId)}
	 * If exception
	 */
	@Test
	public final void getResponsesByTransportIdIfExceptionTest() {
		RuntimeException runtimeEx = new RuntimeException();
		ResponsesManagerException actualException = null;

		when(mockResponsesDaoImpl.findResponsesByTransportId(transportId)).thenThrow(
				runtimeEx);
		try {

			responsesManagerImpl.getResponsesByTransportId(transportId);

		} catch (ResponsesManagerException responsesManagerEx) {
			actualException = responsesManagerEx;
		}

		assertNotNull(actualException);
		assertEquals(runtimeEx, actualException.getCause());
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getUncheckedResponses()}
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

		verify(mockResponsesDaoImpl, times(1)).findUncheckedResponses();
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getUncheckedResponses()}
	 * If empty list
	 */
	@Test
	public final void getUncheckedResponsesIfEmptyListTest() {
		List<Responses> responsesList = new ArrayList<Responses>();
		List<Responses> expectedResponses = new ArrayList<Responses>();

		when(mockResponsesDaoImpl.findUncheckedResponses()).thenReturn(
				responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getUncheckedResponses();

		verify(mockResponsesDaoImpl, times(1)).findUncheckedResponses();
		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getUncheckedResponses()}
	 * If exception
	 */
	@Test(expected = RuntimeException.class)
	public final void getUncheckedResponsesIfExceptionTest() {
		when(mockResponsesDaoImpl.findUncheckedResponses()).thenThrow(
				new RuntimeException());

		responsesManagerImpl.getUncheckedResponses();
	}

	/*	*//**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#addResponse(userId, tripId, responseText)}
	 */
	/*
	 * @Test public final void addResponseTest() { String responseText =
	 * "Good trip"; Users user = mock(Users.class); Trips trip =
	 * mock(Trips.class); Responses toTest = spy(new Responses());
	 * 
	 * when(usersDaoImpl.findById(Mockito.anyInt())).thenReturn(user);
	 * when(tripsDAOImpl.findById(Mockito.anyInt())).thenReturn(trip);
	 * 
	 * 
	 * responsesManagerImpl.addResponse(1, 2, responseText);
	 * 
	 * verify(mockResponsesDaoImpl).save(toTest); }
	 */
	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#addResponse(userId, tripId, responseText)}
	 * If Exception
	 */
	/*
	 * @Test (expected = IllegalArgumentException.class) public final void
	 * addResponseIfExceptionTest() { String responseText = "Good trip"; Users
	 * user = mock(Users.class); Trips trip = mock(Trips.class);
	 * 
	 * when(usersDaoImpl.findById(Mockito.anyInt())).thenReturn(user);
	 * when(tripsDAOImpl.findById(Mockito.anyInt())).thenReturn(trip); Responses
	 * spyResponse = spy(new Responses(user, trip, responseText, null));
	 * doThrow(new RuntimeException()).when(spyResponse).setDate(null);
	 * 
	 * responsesManagerImpl.addResponse(1, 2, responseText);
	 * 
	 * }
	 */

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#delResponse(responseId)}
	 */
	@Test
	public final void delResponseTest() {
		Integer responseId = 1;
		Responses response = mock(Responses.class);

		when(mockResponsesDaoImpl.findById(responseId)).thenReturn(response);
		doNothing().when(mockResponsesDaoImpl).remove(response);

		responsesManagerImpl.delResponse(responseId);

		verify(mockResponsesDaoImpl, times(1)).findById(responseId);
		verify(mockResponsesDaoImpl, times(1)).remove(response);
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#delResponse(responseId)}
	 * If exception
	 */
	@Test(expected = RuntimeException.class)
	public final void delResponseIfExceptionTest() {
		Integer responseId = 1;

		when(mockResponsesDaoImpl.findById(responseId)).thenThrow(
				new RuntimeException());

		responsesManagerImpl.delResponse(responseId);
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#markAsChecked(responseId)}
	 */
	@Test
	public final void markAsCheckedTest() {
		Integer responseId = 1;
		Responses response = mock(Responses.class);
		Responses updatedResponse = mock(Responses.class);
		List<Responses> responsesList = new ArrayList<Responses>();
		responsesList.add(updatedResponse);

		when(mockResponsesDaoImpl.findById(responseId)).thenReturn(response);
		when(mockResponsesDaoImpl.update(response)).thenReturn(responsesList);

		responsesManagerImpl.markAsChecked(responseId);

		verify(mockResponsesDaoImpl, times(1)).findById(responseId);
		verify(response, times(1)).setChecked(true);
		verify(mockResponsesDaoImpl, times(1)).update(response);
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#markAsChecked(responseId)}
	 * If exception
	 */
	@Test(expected = RuntimeException.class)
	public final void markAsCheckedIfExceptionTest() {
		Integer responseId = 1;
		Responses response = mock(Responses.class);
		Responses updatedResponse = mock(Responses.class);
		List<Responses> responsesList = new ArrayList<Responses>();
		responsesList.add(updatedResponse);

		when(mockResponsesDaoImpl.findById(responseId)).thenReturn(response);
		when(mockResponsesDaoImpl.update(response)).thenThrow(
				new RuntimeException());

		responsesManagerImpl.markAsChecked(responseId);

		verify(mockResponsesDaoImpl, times(1)).findById(responseId);
		verify(response, times(1)).setChecked(true);
		verify(mockResponsesDaoImpl, times(1)).update(response);
	}
	
	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#markAsChecked(responseId)}
	 * If exception
	 */
	@Test(expected = RuntimeException.class)
	public final void markAsCheckedIfExceptionBeforeUpdateTest() {
		Integer responseId = 1;
		Responses response = mock(Responses.class);
		Responses updatedResponse = mock(Responses.class);
		List<Responses> responsesList = new ArrayList<Responses>();
		responsesList.add(updatedResponse);

		when(mockResponsesDaoImpl.findById(responseId)).thenThrow(
				new RuntimeException());

		responsesManagerImpl.markAsChecked(responseId);

		verify(mockResponsesDaoImpl, times(1)).findById(responseId);
		verify(response, times(0)).setChecked(true);
		verify(mockResponsesDaoImpl, times(0)).update(response);
	}
	
	
}

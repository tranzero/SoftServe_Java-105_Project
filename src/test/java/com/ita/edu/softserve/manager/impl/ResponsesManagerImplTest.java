package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Iterables;
import com.ita.edu.softserve.dao.impl.ResponsesDAOImpl;
import com.ita.edu.softserve.dao.impl.TripsDAOImpl;
import com.ita.edu.softserve.dao.impl.UsersDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.entity.Responses;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.entity.Users;

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
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@Before
	public final void setUp() throws NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		/*
		 * mockResponsesDaoImpl = mock(ResponsesDAOImpl.class);
		 * responsesManagerImpl = new ResponsesManagerImpl(); Field field =
		 * responsesManagerImpl.getClass().getDeclaredField( "responsesDao");
		 * 
		 * field.setAccessible(true); field.set(responsesManagerImpl,
		 * mockResponsesDaoImpl);
		 */
	}

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

		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByRouteId(routeId)}
	 * If exception
	 */
	@Test(expected = RuntimeException.class)
	public final void getResponsesByRouteIdIfExceptionTest() {
		when(mockResponsesDaoImpl.findResponsesByRouteId(routeId)).thenThrow(
				new RuntimeException());

		responsesManagerImpl.getResponsesByRouteId(routeId);
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

		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTripId(routeId)}
	 * If exception
	 */
	@Test(expected = RuntimeException.class)
	public final void getResponsesByTripIdIfExceptionTest() {
		when(mockResponsesDaoImpl.findResponsesByTripId(tripId)).thenThrow(
				new RuntimeException());

		responsesManagerImpl.getResponsesByTripId(tripId);
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

		when(mockResponsesDaoImpl.findResponsesByTranportId(transportId))
				.thenReturn(listOfResponses);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTransportId(transportId);

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

		when(mockResponsesDaoImpl.findResponsesByTranportId(transportId))
				.thenReturn(responsesList);
		List<Responses> actualResponses = responsesManagerImpl
				.getResponsesByTransportId(transportId);

		assertTrue(Iterables.elementsEqual(expectedResponses, actualResponses));
	}

	/**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#getResponsesByTransportId(routeId)}
	 * If exception
	 */
	@Test(expected = RuntimeException.class)
	public final void getResponsesByTransportIdIfExceptionTest() {
		when(mockResponsesDaoImpl.findResponsesByTranportId(transportId))
				.thenThrow(new RuntimeException());

		responsesManagerImpl.getResponsesByTransportId(transportId);
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
	 *//*
	@Test
	public final void addResponseTest() {
		String responseText = "Good trip";
		Users user = mock(Users.class);
		Trips trip = mock(Trips.class);
		Responses toTest = spy(new Responses(user, trip, responseText, null));

		when(usersDaoImpl.findById(Mockito.anyInt())).thenReturn(user);
		when(tripsDAOImpl.findById(Mockito.anyInt())).thenReturn(trip);
		when(new Responses(user, trip, responseText, null))
			.thenReturn(toTest);

		
		responsesManagerImpl.addResponse(1, 2, responseText);

		verify(mockResponsesDaoImpl).save(toTest);
	}

	*//**
	 * Test for method
	 * {@link com.ita.edu.softserve.service.impl.ResponsesManagerImpl#addResponse(userId, tripId, responseText)}
	 * If Exception
	 *//*
	@Test (expected = IllegalArgumentException.class)
	public final void addResponseIfExceptionTest() {
		String responseText = "Good trip";
		Users user = mock(Users.class);
		Trips trip = mock(Trips.class);
		
		when(usersDaoImpl.findById(Mockito.anyInt())).thenReturn(user);
		when(tripsDAOImpl.findById(Mockito.anyInt())).thenReturn(trip);
		Responses spyResponse = spy(new Responses(user, trip, responseText, null));
		doThrow(new RuntimeException()).when(spyResponse).setDate(null);

		responsesManagerImpl.addResponse(1, 2, responseText);

	}
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

		responsesManagerImpl.delResponse(responseId);
		verify(mockResponsesDaoImpl).remove(response);
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
		verify(mockResponsesDaoImpl).update(response);
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
	}
}

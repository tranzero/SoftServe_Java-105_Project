package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.dao.impl.TripsDAOImpl;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.exception.TripsManagerException;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.TripsCriteriaContainerImpl;

/**
 * @author dnyckct
 * 
 */

@RunWith(MockitoJUnitRunner.class)
public class TripsManagerImplTest {

	@Mock
	private TripsDAOImpl tripsDao;

	@Mock
	private UserNameService userNameService;

	@Mock
	private TransportsDaoImpl transportsDao;

	@InjectMocks
	private TripsManagerImpl tripsManager = new TripsManagerImpl();

	private final int correctTripIdForRemove = 1;
	private final int incorrectTripIdForRemove = 0;
	private final int correctTripIdForSearch = 1;
	private final int incorrectTripIdForSearch = 0;
	private final int correctTripIdForList = 1;
	private final int correctPageSizeForList = 1;
	private final int incorrectTripIdForList = 0;
	private final int incorrectPageSizeForList = 0;
	private final int correctTripIdForEdit = 1;
	private final int incorrectTripIdForEdit = 0;
	
	private final TripsCriteriaContainer correctTripsCriteriaContainer = new TripsCriteriaContainerImpl(
			"correct", "correct", 1, 2, 3, StaticValidator.MIN_DATE_STRING,
			StaticValidator.MAX_DATE_STRING, "tr.transport.transportCode",
			"ASC");
	private final TripsCriteriaContainer incorrectTripsCriteriaContainer = new TripsCriteriaContainerImpl(
			"incorrect", "incorrect", 3, 2, 1, "", "", "tr.startDate", "DESC");
	PageInfoContainer correctPageInfoContainer = new PageInfoContainerImpl(1,
			10, 100);
	PageInfoContainer incorrectPageInfoContainer = new PageInfoContainerImpl(3,
			2, 1);

	@Before
	public final void setUp() {
		when(userNameService.getLoggedUsername()).thenReturn("dnycktc");
		correctTripsCriteriaContainer.setMaxDateValue(new Date(Long.MAX_VALUE));
		correctTripsCriteriaContainer.setMinDateValue(new Date(Long.MIN_VALUE));
	}

	@Test
	public final void correctTripRemovalTest() {
		Trips expected = new Trips();
		when(tripsDao.findById(correctTripIdForRemove)).thenReturn(expected);
		tripsManager.removeTrip(correctTripIdForRemove);
		verify(tripsDao, times(1)).remove(expected);
		verify(userNameService, times(1)).getLoggedUsername();
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInTripRemovalTest() {
		Trips expected = new Trips();
		when(tripsDao.findById(incorrectTripIdForRemove)).thenReturn(expected);
		doThrow(new RuntimeException()).when(tripsDao).remove(expected);
		tripsManager.removeTrip(incorrectTripIdForRemove);
	}

	@Test
	public final void correctGetAllEntitiesTest() {
		List<Trips> expected = new ArrayList<Trips>();
		List<Trips> actual;
		when(tripsDao.getAllEntities()).thenReturn(expected);
		actual = tripsManager.getAllTrips();
		verify(tripsDao, times(1)).getAllEntities();
		assertTrue(expected == actual);
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInGetAllEntitiesTest() {
		when(tripsDao.getAllEntities()).thenThrow(new RuntimeException());
		tripsManager.getAllTrips();
	}

	@Test
	public final void correctFindByTripIdTest() {
		Trips expected = new Trips();
		Trips actual;
		when(tripsDao.findById(correctTripIdForSearch)).thenReturn(expected);
		actual = tripsManager.findByTripId(correctTripIdForSearch);
		verify(tripsDao, times(1)).findById(correctTripIdForSearch);
		assertTrue(expected == actual);
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInFindByTripIdTest() {
		when(tripsDao.findById(incorrectTripIdForSearch)).thenThrow(
				new RuntimeException());
		tripsManager.findByTripId(incorrectTripIdForSearch);

	}

	@Test
	public final void correctGetTripsListCriteriaCountUsingContainersTest() {
		long expected = 0;
		long actual;
		when(
				tripsDao.getTripsListCriteriaCount("%"
						+ correctTripsCriteriaContainer.getTransportCode()
						+ "%",
						"%" + correctTripsCriteriaContainer.getRouteName()
								+ "%",
						correctTripsCriteriaContainer.getRemSeatClass1(),
						correctTripsCriteriaContainer.getRemSeatClass2(),
						correctTripsCriteriaContainer.getRemSeatClass3(),
						correctTripsCriteriaContainer.getMinDateValue(),
						correctTripsCriteriaContainer.getMaxDateValue()))
				.thenReturn(expected);
		actual = tripsManager
				.getTripsListCriteriaCountUsingContainers(correctTripsCriteriaContainer);
		verify(tripsDao, times(1)).getTripsListCriteriaCount(
				"%" + correctTripsCriteriaContainer.getTransportCode() + "%",
				"%" + correctTripsCriteriaContainer.getRouteName() + "%",
				correctTripsCriteriaContainer.getRemSeatClass1(),
				correctTripsCriteriaContainer.getRemSeatClass2(),
				correctTripsCriteriaContainer.getRemSeatClass3(),
				correctTripsCriteriaContainer.getMinDateValue(),
				correctTripsCriteriaContainer.getMaxDateValue());
		assertEquals(expected, actual);
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInGetTripsListCriteriaCountUsingContainersTest() {
		when(
				tripsDao.getTripsListCriteriaCount("%"
						+ incorrectTripsCriteriaContainer.getTransportCode()
						+ "%",
						"%" + incorrectTripsCriteriaContainer.getRouteName()
								+ "%",
						incorrectTripsCriteriaContainer.getRemSeatClass1(),
						incorrectTripsCriteriaContainer.getRemSeatClass2(),
						incorrectTripsCriteriaContainer.getRemSeatClass3(),
						incorrectTripsCriteriaContainer.getMinDateValue(),
						incorrectTripsCriteriaContainer.getMaxDateValue()))
				.thenThrow(new RuntimeException());
		tripsManager
				.getTripsListCriteriaCountUsingContainers(incorrectTripsCriteriaContainer);
	}

	@Test
	public final void correctGetTripsListCriteriaPageUsingContainersTest() {
		Trips correctTrip = new Trips();
		long preExpected = 0;
		long actual;
		when(tripsDao.findById(correctTripIdForList)).thenReturn(correctTrip);
		when(
				tripsDao.getTripsListCriteriaIndex("%"
						+ correctTripsCriteriaContainer.getTransportCode()
						+ "%",
						"%" + correctTripsCriteriaContainer.getRouteName()
								+ "%",
						correctTripsCriteriaContainer.getRemSeatClass1(),
						correctTripsCriteriaContainer.getRemSeatClass2(),
						correctTripsCriteriaContainer.getRemSeatClass3(),
						correctTripsCriteriaContainer.getMinDateValue(),
						correctTripsCriteriaContainer.getMaxDateValue(),
						correctTripsCriteriaContainer.getOrderByParam(),
						correctTripsCriteriaContainer.getOrderByDirection(),
						correctTrip)).thenReturn(preExpected);
		actual = tripsManager.getTripsListCriteriaPageUsingContainers(
				correctTripsCriteriaContainer, correctTripIdForList,
				correctPageSizeForList);
		assertTrue(preExpected / correctPageSizeForList + 1 == actual);
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInGetTripsListCriteriaPageUsingContainersTest() {
		when(tripsDao.findById(incorrectTripIdForList)).thenThrow(
				new RuntimeException());
		tripsManager.getTripsListCriteriaPageUsingContainers(
				incorrectTripsCriteriaContainer, incorrectTripIdForList,
				incorrectPageSizeForList);
	}

	@Test
	public final void correctGetTripsForCriteriaUsingContainersTest() {
		List<Trips> expected = new ArrayList<Trips>();
		List<Trips> actual;
		when(
				tripsDao.getTripsListCriteria(
						(correctPageInfoContainer.getPageNumber() - 1)
								* correctPageInfoContainer.getResultsPerPage(),
						correctPageInfoContainer.getResultsPerPage(),
						"%" + correctTripsCriteriaContainer.getTransportCode()
								+ "%",
						"%" + correctTripsCriteriaContainer.getRouteName()
								+ "%",
						correctTripsCriteriaContainer.getRemSeatClass1(),
						correctTripsCriteriaContainer.getRemSeatClass2(),
						correctTripsCriteriaContainer.getRemSeatClass3(),
						correctTripsCriteriaContainer.getMinDateValue(),
						correctTripsCriteriaContainer.getMaxDateValue(),
						correctTripsCriteriaContainer.getOrderByParam(),
						correctTripsCriteriaContainer.getOrderByDirection()))
				.thenReturn(expected);
		actual = tripsManager.getTripsForCriteriaUsingContainers(
				correctTripsCriteriaContainer, correctPageInfoContainer);
		assertTrue(expected == actual);
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInGetTripsForCriteriaUsingContainersTest() {
		when(
				tripsDao.getTripsListCriteria(
						(incorrectPageInfoContainer.getPageNumber() - 1)
								* incorrectPageInfoContainer
										.getResultsPerPage(),
						incorrectPageInfoContainer.getResultsPerPage(),
						"%"
								+ incorrectTripsCriteriaContainer
										.getTransportCode() + "%",
						"%" + incorrectTripsCriteriaContainer.getRouteName()
								+ "%",
						incorrectTripsCriteriaContainer.getRemSeatClass1(),
						incorrectTripsCriteriaContainer.getRemSeatClass2(),
						incorrectTripsCriteriaContainer.getRemSeatClass3(),
						incorrectTripsCriteriaContainer.getMinDateValue(),
						incorrectTripsCriteriaContainer.getMaxDateValue(),
						incorrectTripsCriteriaContainer.getOrderByParam(),
						incorrectTripsCriteriaContainer.getOrderByDirection()))
				.thenThrow(new RuntimeException());
		tripsManager.getTripsForCriteriaUsingContainers(
				incorrectTripsCriteriaContainer, incorrectPageInfoContainer);
	}
	
	
	public final void correctEditTripTest(){
		
	}
	

}

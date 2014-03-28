package com.ita.edu.softserve.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.entity.Trips;
import com.ita.edu.softserve.exception.TripsManagerException;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.utils.DateUtil;
import com.ita.edu.softserve.utils.StaticValidator;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.AddTripsInfoValidationContainer;
import com.ita.edu.softserve.validationcontainers.impl.EditTripsInfoValidationContainer;
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
	private final int correctTripIdForSeatsManipulations = 1;

	private final TripsCriteriaContainer correctTripsCriteriaContainer = new TripsCriteriaContainerImpl(
			"correct", "correct", 1, 2, 3, StaticValidator.MIN_DATE_STRING,
			StaticValidator.MAX_DATE_STRING, "tr.transport.transportCode",
			"ASC");
	private final TripsCriteriaContainer correctTripsCriteriaContainerCopy = new TripsCriteriaContainerImpl(
			"correct", "correct", 1, 2, 3, StaticValidator.MIN_DATE_STRING,
			StaticValidator.MAX_DATE_STRING, "tr.transport.transportCode",
			"ASC");
	private TripsCriteriaContainer emptyTripsCriteriaContainer;
	private final TripsCriteriaContainer incorrectTripsCriteriaContainer = new TripsCriteriaContainerImpl(
			"incorrect", "incorrect", 3, 2, 1, "", "", "tr.startDate", "DESC");
	private final PageInfoContainer correctPageInfoContainer = new PageInfoContainerImpl(
			1, 10, 100);
	private final PageInfoContainer incorrectPageInfoContainer = new PageInfoContainerImpl(
			3, 2, 1);
	private final EditTripsInfoValidationContainer correctEditTripsInfoValidationContainer = new EditTripsInfoValidationContainer();
	private final EditTripsInfoValidationContainer incorrectEditTripsInfoValidationContainer = new EditTripsInfoValidationContainer();
	private final AddTripsInfoValidationContainer correctAddTripsInfoValidationContainer = new AddTripsInfoValidationContainer();
	private final AddTripsInfoValidationContainer incorrectAddTripsInfoValidationContainer = new AddTripsInfoValidationContainer();
	private Date minDateForAddTrips;
	private Date maxDateForAddTrips;

	@Before
	public final void setUp() {
		when(userNameService.getLoggedUsername()).thenReturn("dnycktc");
		correctTripsCriteriaContainer.setMaxDateValue(new Date(Long.MAX_VALUE));
		correctTripsCriteriaContainer.setMinDateValue(new Date(Long.MIN_VALUE));
		correctEditTripsInfoValidationContainer.setLocaleParam(Locale.ENGLISH);
		correctEditTripsInfoValidationContainer.setRemSeatClass1("10");
		correctEditTripsInfoValidationContainer.setRemSeatClass2("20");
		correctEditTripsInfoValidationContainer.setRemSeatClass3("30");
		correctEditTripsInfoValidationContainer
				.setStartDate(StaticValidator.MIN_DATE_STRING);
		correctEditTripsInfoValidationContainer.setTransportId("1");
		incorrectEditTripsInfoValidationContainer
				.setLocaleParam(Locale.ENGLISH);
		incorrectEditTripsInfoValidationContainer.setRemSeatClass1("30");
		incorrectEditTripsInfoValidationContainer.setRemSeatClass2("20");
		incorrectEditTripsInfoValidationContainer.setRemSeatClass3("10");
		incorrectEditTripsInfoValidationContainer.setStartDate("");
		incorrectEditTripsInfoValidationContainer.setTransportId("0");
		emptyTripsCriteriaContainer = new TripsCriteriaContainerImpl(null,
				null, null, null, null, null, null, null, null);
		correctAddTripsInfoValidationContainer.setLocaleParam(Locale.ENGLISH);
		incorrectAddTripsInfoValidationContainer.setLocaleParam(Locale.ENGLISH);
		correctAddTripsInfoValidationContainer.setFrom("12/30/2100");
		correctAddTripsInfoValidationContainer.setTo("12/31/2100");
		incorrectAddTripsInfoValidationContainer.setFrom("");
		incorrectAddTripsInfoValidationContainer.setTo("");
		correctAddTripsInfoValidationContainer.setTransportId("1");
		incorrectAddTripsInfoValidationContainer.setTransportId("0");
		try {
			correctTripsCriteriaContainerCopy.setMaxDateValue(DateUtil
					.parseLocalDate(StaticValidator.MAX_DATE_STRING,
							Locale.ENGLISH));
			correctTripsCriteriaContainerCopy.setMinDateValue(DateUtil
					.parseLocalDate(StaticValidator.MIN_DATE_STRING,
							Locale.ENGLISH));
			minDateForAddTrips = DateUtil.parseLocalDate("12/30/2100",
					Locale.ENGLISH);
			maxDateForAddTrips = DateUtil.parseLocalDate("12/31/2100",
					Locale.ENGLISH);
		} catch (Exception e) {
		}
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

	@Test
	public final void correctEditTripTest() {
		Trips tripToEdit = new Trips();
		Transports expectedTransport = new Transports();
		boolean actual;
		when(tripsDao.findById(correctTripIdForEdit)).thenReturn(tripToEdit);
		when(
				transportsDao.findById(Integer
						.parseInt(correctEditTripsInfoValidationContainer
								.getTransportId()))).thenReturn(
				expectedTransport);
		actual = tripsManager.editTrip(correctTripIdForEdit,
				correctEditTripsInfoValidationContainer);
		verify(tripsDao, times(1)).saveOrUpdate(tripToEdit);
		assertTrue(actual);
	}

	@Test
	public final void catchedDateExceptionInEditTripTest() {
		boolean actual;
		actual = tripsManager.editTrip(incorrectTripIdForEdit,
				incorrectEditTripsInfoValidationContainer);
		assertFalse(actual);
	}

	@Test
	public final void catchedRuntimeExceptionInEditTripTest() {
		boolean actual;
		when(tripsDao.findById(incorrectTripIdForEdit)).thenThrow(
				new RuntimeException());
		actual = tripsManager.editTrip(incorrectTripIdForEdit,
				correctEditTripsInfoValidationContainer);
		assertFalse(actual);
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInUpdateTripTest() {
		Trips tripToUpdate = new Trips();
		doThrow(new RuntimeException()).when(tripsDao).saveOrUpdate(
				tripToUpdate);
		tripsManager.updateTrip(tripToUpdate);
	}

	@Test
	public final void correctReduceFreeSeatsQuantityClass1Test() {
		tripsManager.reduceFreeSeatsQuantity(
				correctTripIdForSeatsManipulations, 1);
		verify(tripsDao, times(1)).reduceRemSeaatClass1(
				correctTripIdForSeatsManipulations);
	}

	@Test
	public final void correctReduceFreeSeatsQuantityClass2Test() {
		tripsManager.reduceFreeSeatsQuantity(
				correctTripIdForSeatsManipulations, 2);
		verify(tripsDao, times(1)).reduceRemSeaatClass2(
				correctTripIdForSeatsManipulations);
	}

	@Test
	public final void correctReduceFreeSeatsQuantityClass3Test() {
		tripsManager.reduceFreeSeatsQuantity(
				correctTripIdForSeatsManipulations, 3);
		verify(tripsDao, times(1)).reduceRemSeaatClass3(
				correctTripIdForSeatsManipulations);
	}

	@Test
	public final void correctIncreaseFreeSeatsQuantityClass1Test() {
		tripsManager.increaseFreeSeatsQuantity(
				correctTripIdForSeatsManipulations, 1);
		verify(tripsDao, times(1)).increaseRemSeaatClass1(
				correctTripIdForSeatsManipulations);
	}

	@Test
	public final void correctIncreaseFreeSeatsQuantityClass2Test() {
		tripsManager.increaseFreeSeatsQuantity(
				correctTripIdForSeatsManipulations, 2);
		verify(tripsDao, times(1)).increaseRemSeaatClass2(
				correctTripIdForSeatsManipulations);
	}

	@Test
	public final void correctIncreaseFreeSeatsQuantityClass3Test() {
		tripsManager.increaseFreeSeatsQuantity(
				correctTripIdForSeatsManipulations, 3);
		verify(tripsDao, times(1)).increaseRemSeaatClass3(
				correctTripIdForSeatsManipulations);
	}

	@Test
	public final void correctGetByTripIdTest() {
		Trips expected = new Trips();
		Trips actual;
		when(tripsDao.getTripById(correctTripIdForSearch)).thenReturn(expected);
		actual = tripsManager.getTripById(correctTripIdForSearch);
		verify(tripsDao, times(1)).getTripById(correctTripIdForSearch);
		assertTrue(expected == actual);
	}

	@Test(expected = TripsManagerException.class)
	public final void exceptionInGetByTripIdTest() {
		when(tripsDao.getTripById(incorrectTripIdForSearch)).thenThrow(
				new RuntimeException());
		tripsManager.getTripById(incorrectTripIdForSearch);

	}

	@Test
	public final void validateNullTripsCriteriaTest() {
		tripsManager.validateTripsCriteria(emptyTripsCriteriaContainer,
				Locale.ENGLISH);
		assertFalse(emptyTripsCriteriaContainer.getMaxDateValue() == null);
		assertFalse(emptyTripsCriteriaContainer.getMinDateValue() == null);
		assertFalse(emptyTripsCriteriaContainer.getOrderByDirection() == null);
		assertFalse(emptyTripsCriteriaContainer.getOrderByParam() == null);
		assertFalse(emptyTripsCriteriaContainer.getRemSeatClass1() == null);
		assertFalse(emptyTripsCriteriaContainer.getRemSeatClass2() == null);
		assertFalse(emptyTripsCriteriaContainer.getRemSeatClass3() == null);
		assertFalse(emptyTripsCriteriaContainer.getRouteName() == null);
		assertFalse(emptyTripsCriteriaContainer.getTransportCode() == null);
	}

	@Test
	public final void validateCorrectTripsCriteriaTest() {
		tripsManager.validateTripsCriteria(correctTripsCriteriaContainer,
				Locale.ENGLISH);
		assertEquals(correctTripsCriteriaContainer.getMaxDateValue(),
				correctTripsCriteriaContainerCopy.getMaxDateValue());
		assertEquals(correctTripsCriteriaContainer.getMinDateValue(),
				correctTripsCriteriaContainerCopy.getMinDateValue());
		assertEquals(correctTripsCriteriaContainer.getOrderByDirection(),
				correctTripsCriteriaContainerCopy.getOrderByDirection());
		assertEquals(correctTripsCriteriaContainer.getOrderByParam(),
				correctTripsCriteriaContainerCopy.getOrderByParam());
		assertEquals(correctTripsCriteriaContainer.getRemSeatClass1(),
				correctTripsCriteriaContainerCopy.getRemSeatClass1());
		assertEquals(correctTripsCriteriaContainer.getRemSeatClass2(),
				correctTripsCriteriaContainerCopy.getRemSeatClass2());
		assertEquals(correctTripsCriteriaContainer.getRemSeatClass3(),
				correctTripsCriteriaContainerCopy.getRemSeatClass3());
		assertEquals(correctTripsCriteriaContainer.getRouteName(),
				correctTripsCriteriaContainerCopy.getRouteName());
		assertEquals(correctTripsCriteriaContainer.getTransportCode(),
				correctTripsCriteriaContainerCopy.getTransportCode());
	}

	@Test
	public final void correctAddTripsWithContainerTest() {
		Transports foundTransport = new Transports();
		Trips writtenTrip = new Trips(foundTransport, maxDateForAddTrips);
		boolean actual;
		when(
				transportsDao.findById(Integer
						.parseInt(correctAddTripsInfoValidationContainer
								.getTransportId()))).thenReturn(foundTransport);
		when(
				tripsDao.checkTripExistance(foundTransport.getTransportId(),
						minDateForAddTrips)).thenReturn(true);
		when(
				tripsDao.checkTripExistance(foundTransport.getTransportId(),
						maxDateForAddTrips)).thenReturn(false);
		actual = tripsManager
				.addTripsWithContainer(correctAddTripsInfoValidationContainer);
		verify(tripsDao, times(1)).saveOrUpdate(writtenTrip);
		assertTrue(actual);
	}
	
	@Test
	public final void cachedDateExceptionInAddTripsWithContainerTest() {
		Transports foundTransport = new Transports();
		boolean actual;
		when(
				transportsDao.findById(Integer
						.parseInt(incorrectAddTripsInfoValidationContainer
								.getTransportId()))).thenReturn(foundTransport);
		actual = tripsManager
				.addTripsWithContainer(incorrectAddTripsInfoValidationContainer);
		assertFalse(actual);
	}
	
	@Test
	public final void cachedRuntimeExceptionInAddTripsWithContainerTest() {
		boolean actual;
		when(
				transportsDao.findById(Integer
						.parseInt(incorrectAddTripsInfoValidationContainer
								.getTransportId()))).thenThrow(new RuntimeException());
		actual = tripsManager
				.addTripsWithContainer(incorrectAddTripsInfoValidationContainer);
		assertFalse(actual);
	}
}

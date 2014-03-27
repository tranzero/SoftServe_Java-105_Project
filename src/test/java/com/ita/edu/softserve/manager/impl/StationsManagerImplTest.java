package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.UserNameService;
import com.ita.edu.softserve.manager.impl.StationsManagerImpl;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.impl.PageInfoContainerImpl;
import com.ita.edu.softserve.validationcontainers.impl.StationsCriteriaContainerImpl;

/**
 * Class under test
 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl}
 * 
 * @author admin
 */
@RunWith(MockitoJUnitRunner.class)
public class StationsManagerImplTest {

	/**
	 * Mock object.
	 */
	@Mock
	private StationsDAOImpl stationsDaoMock;

	/**
	 * Mock object.
	 */
	@Mock
	private UserNameService userName;

	/**
	 * StationsManagerImpl.
	 */
	@InjectMocks
	private StationsManager stationsManagerMock = new StationsManagerImpl();

	int stationIdMock = 100;
	int illegalStationId = -1;
	private static final String stationCodeMock = "stationCode mock";
	private static final String stationNameMock = "stationName mock";
	private static final String illegalStationName = "@Stryy@";

	@Spy
	private Stations station = new Stations(stationCodeMock, stationNameMock);

	@Before
	public final void setUp() {

		when(userName.getLoggedUsername()).thenReturn("taras");
		doReturn(stationIdMock).when(station).getStationId();
		when(stationsDaoMock.findByName(stationNameMock)).thenReturn(station);
		when(stationsDaoMock.findById(stationIdMock)).thenReturn(station);
	}

	/**
	 * Test if two list is equals. Method under test. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
	 */
	@Test
	public final void testFindAllStationsEquals() {
		List<Stations> expected = new ArrayList<Stations>();

		when(stationsDaoMock.getAllEntities()).thenReturn(expected);
		List<Stations> actual = stationsManagerMock.findAllStations();

		verify(stationsDaoMock, times(1)).getAllEntities();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
	 */
	@Test(expected = RuntimeException.class)
	public final void testFindAllStationsException() {

		when(stationsDaoMock.getAllEntities())
				.thenThrow(new RuntimeException());

		stationsManagerMock.findAllStations();
		verify(stationsDaoMock, times(1)).getAllEntities();
	}

	/**
	 * Test the methods for Equals. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findStationsById(Integer)}
	 */
	@Test()
	public void testFindStationsById() {
		when(stationsDaoMock.findById(stationIdMock)).thenReturn(station);

		Stations actualStation = stationsManagerMock
				.findStationsById(stationIdMock);

		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		assertEquals(station, actualStation);
	}

	/**
	 * Test the method for <code>null</code>. Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findStationsById(Integer)}
	 */
	@Test
	public final void testFindStationsByIdForNull() {
		when(stationsDaoMock.findById(stationIdMock)).thenReturn(null);

		Stations expectedStation = stationsManagerMock
				.findStationsById(stationIdMock);

		verify(stationsDaoMock, times(1)).findById(Mockito.anyInt());
		assertNull(expectedStation);
	}

	/**
	 * IllegalArgumentException - if the argument is not valid type or null for
	 * that Entity's primary key.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findStationsById(Integer)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testFindStationsByIdException() {
		when(stationsDaoMock.findById(illegalStationId)).thenThrow(
				new IllegalArgumentException());

		stationsManagerMock.findStationsById(illegalStationId);
		verify(stationsDaoMock, times(1)).findById(illegalStationId);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findByStationName(java.lang.String)}
	 */
	@Test()
	public void testFindByStationName() {
		when(stationsDaoMock.findByName(stationNameMock)).thenReturn(station);
		Stations actualStation = stationsManagerMock
				.findByStationName(stationNameMock);

		verify(stationsDaoMock, times(1)).findByName(stationNameMock);
		assertEquals(station, actualStation);
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findByStationName(java.lang.String)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFindByStationNameException() {
		when(stationsDaoMock.findByName(illegalStationName)).thenThrow(
				new IllegalArgumentException());

		stationsManagerMock.findByStationName(illegalStationName);
		verify(stationsDaoMock, times(1)).findByName(illegalStationName);
	}


	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#createStation(com.ita.edu.softserve.entity.Stations)}
	 */
	@Test()
	public void testCreateStation() {
		doNothing().when(stationsDaoMock).save(station);

		stationsManagerMock.createStation(station);
		verify(stationsDaoMock, times(1)).save(station);
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#createStation(com.ita.edu.softserve.entity.Stations)}
	 */
	@Test(expected = EntityExistsException.class)
	public void testCreateStationEntityExistsException() {
		
		station.setStationId(stationIdMock);
		doThrow(new EntityExistsException()).when(stationsDaoMock).save(
				station);

		stationsManagerMock.createStation(station);
		verify(stationsDaoMock, times(1)).save(station);
	}

	/**
	 * IllegalArgumentException - if the instance is not an entity.<br>
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#createStation(com.ita.edu.softserve.entity.Stations)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateStationIllegalArgumentException() {
		doThrow(new IllegalArgumentException()).when(stationsDaoMock).save(
				station);

		stationsManagerMock.createStation(station);
		verify(stationsDaoMock, times(1)).save(station);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#saveOrUpdateStation(com.ita.edu.softserve.entity.Stations)}
	 */
	@Test()
	public void testSaveOrUpdateStations() {
		doNothing().when(stationsDaoMock).saveOrUpdate(station);

		stationsManagerMock.saveOrUpdateStation(station);
		verify(stationsDaoMock, times(1)).saveOrUpdate(station);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#saveOrUpdateStation(com.ita.edu.softserve.entity.Stations)}
	 */
	@Test(expected = RuntimeException.class)
	public void testSaveOrUpdateStationsRuntimeException() {
		doThrow(new RuntimeException()).when(stationsDaoMock).saveOrUpdate(
				station);

		stationsManagerMock.saveOrUpdateStation(station);
		verify(stationsDaoMock, times(1)).saveOrUpdate(station);
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#saveOrUpdateStation(com.ita.edu.softserve.entity.Stations)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSaveOrUpdateStationsIllegalArgumentException() {
		doThrow(new IllegalArgumentException()).when(stationsDaoMock).saveOrUpdate(
				station);

		stationsManagerMock.saveOrUpdateStation(station);
		verify(stationsDaoMock, times(1)).saveOrUpdate(station);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#saveOrUpdateStation(com.ita.edu.softserve.entity.Stations)}
	 */
	@Test()
	public void testSaveOrUpdateStationsWithIdNull() {
		station.setStationId(null);
		doNothing().when(stationsDaoMock).saveOrUpdate(station);

		stationsManagerMock.saveOrUpdateStation(station);
		verify(stationsDaoMock, times(1)).saveOrUpdate(station);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#removeStations(java.lang.Integer)}
	 */
	@Test()
	public void testRemoveStations() {
		when(stationsDaoMock.findById(stationIdMock)).thenReturn(station);
		doNothing().when(stationsDaoMock).remove(station);
		
		stationsManagerMock.removeStations(stationIdMock);

		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(stationsDaoMock, times(1)).remove(station);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#removeStations(java.lang.Integer)}
	 */
	@Test
	public void testRemoveStationsForNull() {
		when(stationsDaoMock.findById(stationIdMock)).thenReturn(null);

		stationsManagerMock.removeStations(stationIdMock);
		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(stationsDaoMock, times(0)).remove(station);
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#removeStations(java.lang.Integer)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRemoveStationsException() {
		when(stationsDaoMock.findById(stationIdMock)).thenReturn(station);

		doThrow(new IllegalArgumentException()).when(stationsDaoMock).remove(
				station);

		stationsManagerMock.removeStations(stationIdMock);
		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(stationsDaoMock, times(1)).remove(station);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#editStation(java.lang.Integer, java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testEditStation() {
		boolean isStationUpdated = false;
		doCallRealMethod().when(station).setStationCode(stationCodeMock);
		doCallRealMethod().when(station).setStationName(stationNameMock);
		isStationUpdated = stationsManagerMock.editStation(stationIdMock,
				stationCodeMock, stationNameMock);

		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(station, times(1)).setStationCode(stationCodeMock);
		verify(station, times(1)).setStationName(stationNameMock);
		verify(stationsDaoMock, times(1)).update(station);

		assertTrue(isStationUpdated);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#editStation(java.lang.Integer, java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testEditStationWhenStationNull() {
		boolean isStationUpdated = true;
		when(stationsDaoMock.findById(stationIdMock)).thenReturn(null);
		isStationUpdated = stationsManagerMock.editStation(stationIdMock,
				stationCodeMock, stationNameMock);

		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(station, times(0)).setStationCode(stationCodeMock);
		verify(station, times(0)).setStationName(stationNameMock);
		verify(stationsDaoMock, times(0)).update(station);

		assertFalse(isStationUpdated);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#editStation(java.lang.Integer, java.lang.String, java.lang.String)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testEditStationFirstValueException() {
		doThrow(new IllegalArgumentException()).when(station)
				.setStationCode(null);
		stationsManagerMock.editStation(stationIdMock, null, stationNameMock);
		
		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(station, times(1)).setStationCode(stationCodeMock);
		verify(station, times(0)).setStationName(stationNameMock);
		verify(stationsDaoMock, times(0)).update(station);
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#editStation(java.lang.Integer, java.lang.String, java.lang.String)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testEditStationSecondValueException() {
		doThrow(new IllegalArgumentException()).when(station)
				.setStationName(null);
		stationsManagerMock.editStation(stationIdMock, stationCodeMock, null);
		
		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(station, times(1)).setStationCode(stationCodeMock);
		verify(station, times(1)).setStationName(stationNameMock);
		verify(stationsDaoMock, times(0)).update(station);
	}
	
	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#editStation(java.lang.Integer, java.lang.String, java.lang.String)}
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testEditStationBothValuesException() {
		doThrow(new IllegalArgumentException()).when(station)
		.setStationCode(null);
		doThrow(new IllegalArgumentException()).when(station)
				.setStationName(null);
		stationsManagerMock.editStation(stationIdMock, null, null);
		
		verify(stationsDaoMock, times(1)).findById(stationIdMock);
		verify(station, times(1)).setStationCode(stationCodeMock);
		verify(station, times(1)).setStationName(stationNameMock);
		verify(stationsDaoMock, times(0)).update(station);
	}

	/*---------------------------Tests for paging, sorting, filtering methods--------------------------------------------*/

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#getStationsListCount()}
	 */
	@Test
	public final void testGetAllStationsListCount() {
		long expected = 10;
		when(stationsDaoMock.getStationsListCount()).thenReturn(expected);
		long actual = stationsManagerMock.getStationsListCount();
		verify(stationsDaoMock, times(1)).getStationsListCount();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#getStationsListCount()}
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetAllStationsListCountException() {
		when(stationsDaoMock.getStationsListCount()).thenThrow(
				new RuntimeException());
		stationsManagerMock.getStationsListCount();
		verify(stationsDaoMock, times(1)).getStationsListCount();
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#getStationsForLimit(java.lang.Integer, java.lang.Integer)}
	 */
	@Test
	public final void testGetStationsForLimit() {

		int firstElement = 0;
		int count = 10;

		List<Stations> expected = new ArrayList<Stations>();
		when(stationsDaoMock.getStationsForLimits(firstElement, count))
				.thenReturn(expected);
		List<Stations> actual = stationsManagerMock.getStationsForLimit(
				firstElement, count);
		verify(stationsDaoMock, times(1)).getStationsForLimits(firstElement, count);

		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#getStationsForPage(java.lang.Integer, java.lang.Integer)}
	 */
	@Test
	public final void testGetStationsForPage() {
		int firstElement = 0;
		int count = 10;

		List<Stations> expected = new ArrayList<Stations>();
		when(stationsDaoMock.getStationsForLimits(firstElement, count))
				.thenReturn(expected);
		List<Stations> actual = stationsManagerMock.getStationsForPage(
				firstElement, count);
		verify(stationsDaoMock, times(1)).getStationsForLimits(Mockito.anyInt(), Mockito.anyInt());
		
		assertEquals(expected, actual);

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#getStationsForLimitUsingContainers(com.ita.edu.softserve.validationcontainers.PageInfoContainer, com.ita.edu.softserve.validationcontainers.StationsCriteriaContainer)}
	 */
	@Test
	public final void testGetStationsForLimitUsingContainers() {

		PageInfoContainer container = mock(PageInfoContainerImpl.class);
		StationsCriteriaContainer stationsCriteriaContainer = mock(StationsCriteriaContainerImpl.class);

		List<Stations> expectedList = new ArrayList<Stations>();

		when(
				stationsDaoMock.getStationsForOnePageWithCriteria(1, 10, "L'viv", "stationCode", "ASC"))
				.thenReturn(expectedList);

		List<Stations> actualList = stationsManagerMock
				.getStationsForLimitUsingContainers(stationsCriteriaContainer, container);
		
		assertEquals(expectedList, actualList);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#getStationsForLimitWithCriteria(java.lang.Integer, java.lang.Double, java.lang.String, java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testGetStationsForLimitWithCriteria() {

		List<Stations> expectedList = new ArrayList<Stations>();

		when(
				stationsDaoMock.getStationsForOnePageWithCriteria(1, 10, "L'viv", "stationName", "ASC"))
				.thenReturn(expectedList);

		List<Stations> actualList = stationsManagerMock
				.getStationsForLimitWithCriteria(1, 10, "L'viv", "stationName", "ASC");

		assertEquals(expectedList, actualList);
	}

}
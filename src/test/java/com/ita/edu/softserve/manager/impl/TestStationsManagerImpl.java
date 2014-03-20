package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
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

import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.impl.StationsManagerImpl;

/**
* Class under test
* {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl}
*
* @author Roman
*/
public class TestStationsManagerImpl {

/**
* Mock object.
*/
private StationsDAOImpl mockStationsDAOImpl;

/**
* StationsManagerImpl.
*/
private StationsManagerImpl stationsManagerImpl;

/**
* @throws SecurityException
* @throws NoSuchFieldException
* @throws IllegalAccessException
* @throws IllegalArgumentException
*/
@Before
public final void setUp() throws NoSuchFieldException, SecurityException,
IllegalArgumentException, IllegalAccessException {

mockStationsDAOImpl = mock(StationsDAOImpl.class);

stationsManagerImpl = new StationsManagerImpl();
Field fild = stationsManagerImpl.getClass().getDeclaredField(
"stationDao");

fild.setAccessible(true);
fild.set(stationsManagerImpl, mockStationsDAOImpl);

}

/**
* Test if two list is equals. Method under test
* {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
*/
@Test
public final void testFindAllStationsEquals() {
List<Stations> listOfStations = new ArrayList<Stations>();

Stations station1 = mock(Stations.class);
Stations station2 = mock(Stations.class);

listOfStations.add(station1);
listOfStations.add(station2);

when(mockStationsDAOImpl.getAllEntities()).thenReturn(listOfStations);
List<Stations> stationList = null;
try {
stationList = stationsManagerImpl.findAllStations();
} catch (StationManagerException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

assertTrue((listOfStations.size() == stationList.size())
&& (listOfStations.containsAll(stationList)));
}

/**
* Method under test.
* {@link com.ita.edu.softserve.manager.impl.StationsManagerImpl#findAllStations()}
*/
@Test
public final void testFindAllStationsEmptyList() {
List<Stations> listOfStations = new ArrayList<Stations>();
when(mockStationsDAOImpl.getAllEntities()).thenReturn(listOfStations);
List<Stations> stationList = null;
try {
stationList = stationsManagerImpl.findAllStations();
} catch (StationManagerException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

assertTrue((listOfStations.size() == stationList.size())
&& (listOfStations.containsAll(stationList)));
}

/**
* Test the methods for Equals.
* @throws StationManagerException
*/
@Test
public final void testFindStationsByIdForEquals() throws StationManagerException {
Stations actualStation = mock(Stations.class);

when(mockStationsDAOImpl.findById(Mockito.anyInt())).thenReturn(
actualStation);
Stations expectedStation = stationsManagerImpl.findStationsById(5);

assertEquals(expectedStation, actualStation);
}

/**
* Test the methods for <code>null</code>.
* @throws StationManagerException
*/
@Test
public final void testFindStationsByIdForNull() throws StationManagerException {
when(mockStationsDAOImpl.findById(Mockito.anyInt())).thenReturn(null);

Stations expectedStation = stationsManagerImpl.findStationsById(9);

assertNull(expectedStation);
}
}
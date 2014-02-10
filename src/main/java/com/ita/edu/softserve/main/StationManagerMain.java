package com.ita.edu.softserve.main;

import java.util.List;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.impl.StationsManagerImpl;


public class StationManagerMain {

	public static void main(String[] args) {

		StationsManager stationsManager = (StationsManager) StationsManagerImpl.getInstance();
		List<Stations> stationList=null;
		try {
			stationList = stationsManager.findAllStations();
		} catch (StationManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("STATION CODE " + " STATION NAME");
		for (Stations station : stationList) {
			System.out.println(station.getStationCode() + " "
					+ station.getStationName());
		}
	}
}

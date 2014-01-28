package com.ita.edu.softserve.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.StationsManager;
import com.ita.edu.softserve.service.impl.StationsManagerImpl;

public class StationServiceMain {

	public static void main(String[] args) {
		
		StationsManager stationsDAOImpl = (StationsManager) StationsManagerImpl.getInstance();

		List<Stations> stationList = stationsDAOImpl.findAllStations();
		System.out.println("CTATION CODE " + " CTATION NAME");
		for (Stations station : stationList) {
			System.out.println(station.getStationCode() + " "
					+ station.getStationName());
		}
	}
}

package com.ita.edu.softserve.main;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.exception.StationManagerException;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.StationsManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;
import com.ita.edu.softserve.manager.impl.StationsManagerImpl;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;

public class OrderStationMain {

	public static void main(String[] args) {

		LinesManager lsImpl = (LinesManager) LinesManagerImpl.getInstance();
		TransportsManager trImpl = (TransportsManager) TransportsManagerImpl.getInstance();
		StationsManager stationsDAOImpl = (StationsManager) StationsManagerImpl
				.getInstance();

		List<Stations> stationsList=null;
		try {
			stationsList = stationsDAOImpl.findAllStations();
		} catch (StationManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stations station1 = stationsList.get(4);
		Stations station2 = stationsList.get(15);
		System.out.println("***********************************");
		System.out.println("FROM: " + station1.getStationName());
		System.out.println("TO:   " + station2.getStationName());
		// List<Lines> lines = lsImpl.getFullLines();
		// List<Lines> lines = lsImpl.getLinesByStation(station1);
		List<Lines> lines = lsImpl.getLinesByTwoStations(
				station1.getStationName(), station2.getStationName());
		for (Lines line : lines) {
			System.out.println(line.getLineId() + " " + line.getLineName());
		}

		station1 = stationsList.get(15);
		station2 = stationsList.get(4);
		System.out.println("***********************************");
		System.out.println("FROM: " + station1.getStationName());
		System.out.println("TO:   " + station2.getStationName());
		lines = lsImpl.getLinesByTwoStations(
				station1.getStationName(), station2.getStationName());
		for (Lines line : lines) {
			System.out.println(line.getLineId() + " " + line.getLineName());
		}
	}

}

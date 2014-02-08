package com.ita.edu.softserve.main;

import java.util.List;

import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;


public class TransportManagerMain {

	public static void main(String[] args) {

		TransportsManager stationsManager = (TransportsManager) TransportsManagerImpl.getInstance();
		List<Transports> transportsList = stationsManager.getAllTransports();
		
		System.out.println("Line ID " + " Line NAME");
		for (Transports transport : transportsList) {
			System.out.println(transport.getRoutes().getLineId().getClass().getSimpleName() + ":	"
					+ transport.getRoutes().getLineId().getLineName());
		}
	}
}

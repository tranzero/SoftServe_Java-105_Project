package com.ita.edu.softserve.main;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsDAOImpl;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.service.LinesService;
import com.ita.edu.softserve.service.impl.LinesServiceImpl;

public class OrderStationMain {

	public static void main(String[] args) {
		ApplicationContext appContext = 
		    	  new ClassPathXmlApplicationContext("/META-INF/spring/root-context.xml");		 
		
		LinesService lsImpl = (LinesServiceImpl) appContext.getBean("linesService");
		LinesDAO lineDaoImpl = (LinesDAOImpl) appContext.getBean("linesDaoImpl");
		StationsDAOImpl stationsDao = (StationsDAOImpl) appContext.getBean("stationsDao");

		List<Stations> stationsList = stationsDao.getAllEntities();
		Stations station1 = stationsList.get(5);
		Stations station2 = stationsList.get(12);
		List<Lines> lines = lsImpl.getLinesTwoStationsCertainOrder(station1, station2);
		Iterator it = lines.iterator();
		while (it.hasNext()) {
			Object element = it.next();
	        System.out.print(element + " ");	
	    }
	}

}

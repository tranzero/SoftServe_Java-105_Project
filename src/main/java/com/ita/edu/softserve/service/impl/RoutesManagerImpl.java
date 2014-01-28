package com.ita.edu.softserve.service.impl;

import java.util.List;
import java.sql.Time;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.service.ManagerFactory;
import com.ita.edu.softserve.service.RoutesManager;
import com.ita.edu.softserve.service.StationsManager;
import com.ita.edu.softserve.dao.impl.StopsDAOImpl;
import com.ita.edu.softserve.dao.impl.StationsOnLineDAOImpl;
import com.ita.edu.softserve.entity.Stops;
import com.ita.edu.softserve.entity.StationsOnLine;

/**
 * @author Lyubomyr
 * 
 */
@Service
public class RoutesManagerImpl implements RoutesManager {

        private RoutesDAOImpl routesDao = new RoutesDAOImpl();

        public RoutesManagerImpl() {
        }

        /**
         * Return Routes of transports that are arriving to certain station during
         * certain times
         * 
         * @param idStationArriving
         *            - id arriving station
         * @param timeArrivalMin
         *            - minimum time arrival
         * @param timeArrivalMax
         *            - maximum time arrival
         * 
         */
        @Override
        public List<Routes> findRoutersListByStationIdArriving(
                        int idStationArriving, Time timeArrivalMin, Time timeArrivalMax)
                        throws IllegalArgumentException {
                if (idStationArriving < 1) {
                        throw new IllegalArgumentException(
                                        "idStationArriving should be greater than zero");
                }
                if (timeArrivalMin.after(timeArrivalMax)) {
                        throw new IllegalArgumentException(
                                        "timeArrivalMax should be greater or equals than timeArrivalMin");
                }
                List<Routes> routersListByStationArriving = new ArrayList<Routes>();
                StopsDAOImpl stopsDao = new StopsDAOImpl();
                StationsOnLineDAOImpl stationsOnLineDao = new StationsOnLineDAOImpl();
                for (Routes route : routesDao.getAllEntities()) {
                        for (Stops stop : stopsDao.getAllEntities()) {
                                if (route.getLineId().getLineId() == stop.getRouteId()
                                                .getRouteId()) {
                                        if ((stop.getArrival().equals(timeArrivalMin) || stop
                                                        .getArrival().after(timeArrivalMin))
                                                        && (stop.getArrival().equals(timeArrivalMax) || stop
                                                                        .getArrival().before(timeArrivalMax))) {
                                                StationsOnLine stationOnLine = stationsOnLineDao
                                                                .findById(stop.getStationOnLineID()
                                                                                .getStationOnLineId());
                                                if (stationOnLine != null) {
                                                        if (idStationArriving == stationOnLine
                                                                        .getStationId().getStationId()) {
                                                                routersListByStationArriving.add(route);
                                                        }
                                                }
                                        }
                                }
                        }
                }
                return routersListByStationArriving;

                // return
                // routesDao.findRoutersListByStationIdArriving(idStationArriving,
                // timeArrivalMin, timeArrivalMax);
        }

        /**
         * Return Routes of transports that are departing from certain station
         * during certain times
         * 
         * @param idStationArriving
         *            - id departing station
         * @param timeDepartureMin
         *            - minimum time departure
         * @param timeDepartureMax
         *            - maximum time departure
         * 
         */
        @Override
        public List<Routes> findRoutersListByStationIdDeparting(
                        int idStationDeparting, Time timeDepartureMin, Time timeDepartureMax)
                        throws IllegalArgumentException {
                if (idStationDeparting < 1) {
                        throw new IllegalArgumentException(
                                        "idStationDeparting should be greater than zero");
                }
                if (timeDepartureMin.after(timeDepartureMax)) {
                        throw new IllegalArgumentException(
                                        "timeDepartureMax should be greater or equals than timeDepartureMin");
                }
                List<Routes> routersListByStationDeparting = new ArrayList<Routes>();
                StopsDAOImpl stopsDao = new StopsDAOImpl();
                StationsOnLineDAOImpl stationsOnLineDao = new StationsOnLineDAOImpl();
                for (Routes route : routesDao.getAllEntities()) {
                        for (Stops stop : stopsDao.getAllEntities()) {
                                if (route.getLineId().getLineId() == stop.getRouteId()
                                                .getRouteId()) {
                                        if ((stop.getDeparture().equals(timeDepartureMin) || stop
                                                        .getDeparture().after(timeDepartureMin))
                                                        && (stop.getDeparture().equals(timeDepartureMax) || stop
                                                                        .getDeparture().before(timeDepartureMax))) {
                                                StationsOnLine stationOnLine = stationsOnLineDao
                                                                .findById(stop.getStationOnLineID()
                                                                                .getStationOnLineId());
                                                if (stationOnLine != null) {
                                                        if (idStationDeparting == stationOnLine
                                                                        .getStationId().getStationId()) {
                                                                routersListByStationDeparting.add(route);
                                                        }
                                                }
                                        }
                                }
                        }
                }
                return routersListByStationDeparting;

                // return
                // routesDao.findRoutersListByStationIdDeparting(idStationDeparting,
                // timeDepartureMin, timeDepartureMax);
        }
        public static RoutesManager getInstance() {
    		return ManagerFactory.getManager(RoutesManager.class); 
    	}
}
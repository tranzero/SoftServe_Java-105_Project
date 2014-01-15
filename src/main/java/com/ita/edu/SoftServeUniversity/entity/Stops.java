package com.ita.edu.SoftServeUniversity.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * @author admin
 *
 */

@Entity
@Table(name = "STOPS")
public class Stops {
        @Id
        @Column(name = "STOPID")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int stopId;
        
        @ManyToOne
    	@JoinColumn(name = "ROUTEID", nullable = false)
        private Routes routeId;
        
        @ManyToOne
    	@JoinColumn(name = "STATIONONLINEID", nullable = false)
        private StationsOnLine stationOnLineID;
        
        @Column(name = "ARRIVAL")
        private Date arrival;
        
        @Column(name = "DEPARTURE")
        private Date departure;

        /**
         * @return the stopId
         */
        public int getStopId() {
                return stopId;
        }

        /**
         * @param stopId the stopId to set
         */
        public void setStopId(int stopId) {
                this.stopId = stopId;
        }

        /**
         * @return the routeId
         */
        public Routes getRouteId() {
                return routeId;
        }

        /**
         * @param routeId the routeId to set
         */
        public void setRouteId(Routes routeId) {
                this.routeId = routeId;
        }

        /**
         * @return the stationOnLineID
         */
        public StationsOnLine getStationOnLineID() {
                return stationOnLineID;
        }

        /**
         * @param stationOnLineID the stationOnLineID to set
         */
        public void setStationOnLineID(StationsOnLine stationOnLineID) {
                this.stationOnLineID = stationOnLineID;
        }

        /**
         * @return the arrival
         */
        public Date getArrival() {
                return arrival;
        }

        /**
         * @param arrival the arrival to set
         */
        public void setArrival(Date arrival) {
                this.arrival = arrival;
        }

        /**
         * @return the departure
         */
        public Date getDeparture() {
                return departure;
        }

        /**
         * @param departure the departure to set
         */
        public void setDeparture(Date departure) {
                this.departure = departure;
        }
        

}
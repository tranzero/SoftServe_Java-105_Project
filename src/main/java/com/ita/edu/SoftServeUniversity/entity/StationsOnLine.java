package com.ita.edu.SoftServeUniversity.entity;

import javax.persistence.*;

/**
 * @author admin
 *
 */

@Entity
@Table(name = "STATIONSONLINE")
public class StationsOnLine {
        @Id
        @Column(name = "STATIONONLINEID", nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int stationOnLineID;
        
        @ManyToOne
    	@JoinColumn(name = "LINEID", nullable = false)
        private Lines lineId;
        
        @ManyToOne
    	@JoinColumn(name = "STATIONID", nullable = false)
        private Stations stationId;
        
        @Column(name = "STATIONORDERNUM")
        private int stationOrderNum;

        /**
         * @return the stationOnLineID
         */
        public int getStationOnLineID() {
                return stationOnLineID;
        }

        /**
         * @param stationOnLineID the stationOnLineID to set
         */
        public void setStationOnLineID(int stationOnLineID) {
                this.stationOnLineID = stationOnLineID;
        }

        /**
         * @return the lineId
         */
        public Lines getLineId() {
                return lineId;
        }

        /**
         * @param lineId the lineId to set
         */
        public void setLineId(Lines lineId) {
                this.lineId = lineId;
        }

        /**
         * @return the stationId
         */
        public Stations getStationId() {
                return stationId;
        }

        /**
         * @param stationId the stationId to set
         */
        public void setStationId(Stations stationId) {
                this.stationId = stationId;
        }

        /**
         * @return the stationOrderNum
         */
        public int getStationOrderNum() {
                return stationOrderNum;
        }

        /**
         * @param stationOrderNum the stationOrderNum to set
         */
        public void setStationOrderNum(int stationOrderNum) {
                this.stationOrderNum = stationOrderNum;
        }
        

}

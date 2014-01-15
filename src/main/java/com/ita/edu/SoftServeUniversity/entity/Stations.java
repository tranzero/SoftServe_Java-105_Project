package com.ita.edu.SoftServeUniversity.entity;

import javax.persistence.*;

/**
 * @author admin
 *
 */

@Entity
@Table(name = "STATIONS")
public class Stations {

	 @Id
     @Column(name = "STATIONID", nullable = false)
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int stationId;
     
     @Column(name = "STATIONCODE", unique = true, nullable = false)
     private String stationCode;
     
     @Column(name = "STATIONNAME", nullable = false, length = 100)
     private String stationName;

     /**
      * @return the stationId
      */
     public int getStationId() {
             return stationId;
     }

     /**
      * @param stationId the stationId to set
      */
     public void setStationId(int stationId) {
             this.stationId = stationId;
     }

     /**
      * @return the stationCode
      */
     public String getStationCode() {
             return stationCode;
     }

     /**
      * @param stationCode the stationCode to set
      */
     public void setStationCode(String stationCode) {
             this.stationCode = stationCode;
     }

     /**
      * @return the stationName
      */
     public String getStationName() {
             return stationName;
     }

     /**
      * @param stationName the stationName to set
      */
     public void setStationName(String stationName) {
             this.stationName = stationName;
     }
}

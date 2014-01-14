package com.ita.edu.SoftServeUniversity.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * @author admin
 *
 */

@Entity
@Table(name = "ROUTES")
public class Routes {
        @Id
        @Column(name = "ROUTEID")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int routeID;
        
        @ManyToOne
    	@JoinColumn(name = "LINEID", nullable = false)
        private int lineID;
                
        @Column(name = "ROUTECODE", nullable = false, length = 20)
        private String routeCode;
        
        @Column(name = "STARTTIME")
        private Date startTime;
        
        
        public Routes(){
                
        }

        /**
         * @return the routeID
         */
        public int getRouteID() {
                return routeID;
        }

        /**
         * @param routeID the routeID to set
         */
        public void setRouteID(int routeID) {
                this.routeID = routeID;
        }

        /**
         * @return the lineID
         */
        public int getLineID() {
                return lineID;
        }

        /**
         * @param lineID the lineID to set
         */
        public void setLineID(int lineID) {
                this.lineID = lineID;
        }

        

        /**
         * @return the routeCode
         */
        public String getRouteCode() {
                return routeCode;
        }

        /**
         * @param routeCode the routeCode to set
         */
        public void setRouteCode(String routeCode) {
                this.routeCode = routeCode;
        }

        /**
         * @return the startTime
         */
        public Date getStartTime() {
                return startTime;
        }

        /**
         * @param startTime the startTime to set
         */
        public void setStartTime(Date startTime) {
                this.startTime = startTime;
        }

}

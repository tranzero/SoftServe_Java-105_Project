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
        @Column(name = "ROUTEID", nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int routeID;
        
        @ManyToOne
    	@JoinColumn(name = "LINEID", nullable = false)
        private Lines lineID;
                
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
        public Lines getLineID() {
                return lineID;
        }

        /**
         * @param lineID the lineID to set
         */
        public void setLineID(Lines lineID) {
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

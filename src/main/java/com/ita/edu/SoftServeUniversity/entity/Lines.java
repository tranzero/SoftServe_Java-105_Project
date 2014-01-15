/**
 * 
 */
package com.ita.edu.SoftServeUniversity.entity;

import javax.persistence.*;

/**
 * @author admin
 *
 */

@Entity
@Table(name = "LINES")
public class Lines {
	
	 @Id
     @Column(name = "LINEID", nullable = false)
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int lineID;
     
     @Column(name = "LINENAME", nullable = false, length = 100)
     private String lineName;

	public int getLineID() {
		return lineID;
	}

	/**
	 * @param lineID
	 */
	public void setLineID(int lineID) {
		this.lineID = lineID;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

}

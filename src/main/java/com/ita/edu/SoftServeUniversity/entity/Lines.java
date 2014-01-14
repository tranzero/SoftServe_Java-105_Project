/**
 * 
 */
package com.ita.edu.SoftServeUniversity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tranzero
 *
 */

@Entity
@Table(name = "LINES")
public class Lines {
	@Id
	@Column(name = "LINEID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int lineID;
	
	@Column(name = "LINENAME", length = 100)
	private String lineName;

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
	 * @return the lineName
	 */
	public String getLineName() {
		return lineName;
	}

	/**
	 * @param lineName the lineName to set
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	

}

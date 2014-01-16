/**
 * 
 */
package com.ita.edu.softserve.entity;

import javax.persistence.*;

/**
 * 
 * @author admin
 * 
 */

@Entity
@Table(name = "LINES")
@NamedQueries({
	@NamedQuery(name = Lines.GET_LINES_TWO_STATIONS_CERTAIN_ORDER, 
			    query = Lines.GET_LINES_TWO_STATIONS_CERTAIN_ORDER_QUERY)
})
public class Lines {

	public static final String GET_LINES_TWO_STATIONS_CERTAIN_ORDER = "Lines.getLinesTwoStationsCertainOrder";
	/* Here must be a real query?! ||| in progress%%% */
	static final String GET_LINES_TWO_STATIONS_CERTAIN_ORDER_QUERY = "SELECT ln FROM Lines ln";

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

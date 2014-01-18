/**
 * 
 */
package com.ita.edu.softserve.entity;

import java.util.List;

import javax.persistence.*;


/**
 * @author admin
 * 
 * The persistent class for the lines database table.
 */
@Entity
@Table(name = "LINES")
@NamedQueries({
	@NamedQuery(name = Lines.GET_LINES_TWO_STATIONS_CERTAIN_ORDER, 
			    query = Lines.GET_LINES_TWO_STATIONS_CERTAIN_ORDER_QUERY)
})
public class Lines {

	public static final String GET_LINES_TWO_STATIONS_CERTAIN_ORDER = "Lines.getLinesTwoStationsCertainOrder";
	/* Here must be a real query?! ||| in progress%%% - Answer - YES*/
	static final String GET_LINES_TWO_STATIONS_CERTAIN_ORDER_QUERY = "SELECT ln FROM Lines ln";

	@Id
	@Column(name = "LINEID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int lineId;

	@Column(name = "LINENAME", length = 100)
	private String lineName;
	
	/* Bi-directional one-to-many association to Routes */
	@OneToMany(mappedBy="lineId", fetch = FetchType.LAZY)
	private List<Routes> routes;
	
	/* Bi-directional one-to-many association to StationsOnLine */
	@OneToMany(mappedBy="lineId", fetch = FetchType.LAZY)
	private List<StationsOnLine> stationsOnLines;

	
	/**
	 * Default constructor
	 */
	public Lines() {
	}
	
	
	/**
	 * @param lineName
	 * Constructor with parametr lineName
	 */
	public Lines(String lineName) {
		this.lineName = lineName;
	}


	/**
     * @return the lineId
     */
	public int getLineId() {
		return lineId;
	}

	/**
	 * @param lineId to set
	 */
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	
	/**
     * @return the lineName
     */
	public String getLineName() {
		return lineName;
	}

	/**
	 * @param lineName to set
	 */
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
     * @return the List of routes
     */
	public List<Routes> getRoutes() {
		return routes;
	}

	/**
	 * @param routes to set
	 */
	public void setRoutes(List<Routes> routes) {
		this.routes = routes;
	}

	/**
     * @return the List of stationsOnLines
     */
	public List<StationsOnLine> getStationsOnLines() {
		return stationsOnLines;
	}

	/**
	 * @param stationsOnLines to set
	 */
	public void setStationsOnLines(List<StationsOnLine> stationsOnLines) {
		this.stationsOnLines = stationsOnLines;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lineName == null) ? 0 : lineName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lines other = (Lines) obj;
		if (lineName == null) {
			if (other.lineName != null)
				return false;
		} else if (!lineName.equals(other.lineName))
			return false;
		return true;
	}

}

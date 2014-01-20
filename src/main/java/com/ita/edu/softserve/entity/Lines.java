/**
 * 
 */
package com.ita.edu.softserve.entity;

import java.util.List;

import javax.persistence.*;

/**
 * @author admin
 * 
 *         The persistent class for the lines database table.
 */
@Entity
@Table(name = "LINES")
@NamedQuery(name = Lines.FIND_BY_NAME, query = Lines.FIND_BY_NAME_QUERY)
@NamedQueries({
		@NamedQuery(name = Lines.GET_LINES_TWO_STATIONS_CERTAIN_ORDER, query = Lines.GET_LINES_TWO_STATIONS_CERTAIN_ORDER_QUERY),
		@NamedQuery(name = Lines.GET_FULL_LINES, query = Lines.GET_FULL_LINES_QUERY),
		@NamedQuery(name = Lines.GET_LINES_BY_STATION, query= Lines.GET_LINES_BY_STATION_QUERY)})

public class Lines {

	public static final String FIND_BY_NAME = "Lines.findByName";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Lines u WHERE u.lineName = ?1";
	//Query to find all lines
	public static final String GET_FULL_LINES = "Lines.findFullLines";
	public static final String GET_FULL_LINES_QUERY = "SELECT ln FROM Lines ln";

	public static final String GET_LINES_TWO_STATIONS_CERTAIN_ORDER = "Lines.getLinesTwoStationsCertainOrder";
	/* Here must be a real query?! ||| in progress%%% - Answer - YES */
	static final String GET_LINES_TWO_STATIONS_CERTAIN_ORDER_QUERY = "SELECT ln FROM Lines ln";
	public static final String GET_LINES_BY_STATION ="Lines.getLinesByStation";
	/* Here must be HQL query*/
	public static final String GET_LINES_BY_STATION_QUERY = "SELECT `LINES`.LINEID, `LINES`.LINENAME FROM ( STATIONS JOIN STATIONSONLINES ON STATIONS.STATIONID = STATIONSONLINES.STATIONID JOIN `LINES` ON STATIONSONLINES.LINEID = `LINES`.LINEID ) WHERE STATIONS.STATIONNAME = @CERTAINSTATIONNAME";

	@Id
	@Column(name = "LINEID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int lineId;

	@Column(name = "LINENAME", length = 100)
	private String lineName;

	/* Bi-directional one-to-many association to Routes */
	@OneToMany(mappedBy = "lineId", fetch = FetchType.LAZY)
	private List<Routes> routes;

	/* Bi-directional one-to-many association to StationsOnLine */
	@OneToMany(mappedBy = "lineId", fetch = FetchType.LAZY)
	private List<StationsOnLine> stationsOnLines;

	/**
	 * Default constructor
	 */
	public Lines() {
	}

	/**
	 * @param lineName
	 *            Constructor with parametr lineName
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
	 * @param lineId
	 *            to set
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
	 * @param lineName
	 *            to set
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
	 * @param routes
	 *            to set
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
	 * @param stationsOnLines
	 *            to set
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

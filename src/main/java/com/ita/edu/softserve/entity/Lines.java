/**
 * 
 */
package com.ita.edu.softserve.entity;

import java.util.Set;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author admin
 * 
 *         The persistent class for the lines database table.
 */
@Entity
@Table(name = "`lines`")
@NamedQueries({
		@NamedQuery(name = Lines.GET_FULL_LINES, query = Lines.GET_FULL_LINES_QUERY),
		@NamedQuery(name = Lines.GET_FULL_LINES_COUNT, query = Lines.GET_FULL_LINES_QUERY_COUNT),
		@NamedQuery(name = Lines.FIND_BY_NAME, query = Lines.FIND_BY_NAME_QUERY),
		@NamedQuery(name = Lines.FIND_BY_STATION_NAME, query = Lines.FIND_BY_STATION_NAME_QUERY),
		@NamedQuery(name = Lines.FIND_BY_STATION_NAME_COUNT, query = Lines.FIND_BY_STATION_NAME_COUNT_QUERY),
		@NamedQuery(name = Lines.FIND_BY_STATION_NAME_FOR_PAGING, query = Lines.FIND_BY_STATION_NAME_FOR_PAGING_QUERY)})
public class Lines extends BaseEntity {

	public static final String FIND_BY_NAME = "Lines.findByName";
	public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Lines u WHERE u.lineName = ?1";
	// Query to find all lines
	public static final String GET_FULL_LINES = "Lines.findFullLines";
	public static final String GET_FULL_LINES_QUERY = "SELECT ln FROM Lines ln";
	
	public static final String GET_FULL_LINES_COUNT = "Lines.findFullLinesCount";
	public static final String GET_FULL_LINES_QUERY_COUNT = "SELECT COUNT(ln.lineId) FROM Lines ln";
	
	public static final String FIND_BY_STATION_NAME = "Lines.findByStationName";
	public static final String FIND_BY_STATION_NAME_QUERY = "select l from StationsOnLine stln inner join stln.lineId as l inner join stln.stationId as s where s.stationName = ?1";

	public static final String FIND_BY_STATION_NAME_COUNT = "Lines.findByStationNameCount";
	public static final String FIND_BY_STATION_NAME_COUNT_QUERY = "select count(l.lineId) from StationsOnLine stln inner join stln.lineId as l inner join stln.stationId as s where s.stationName = ?1";
	
	public static final String FIND_BY_STATION_NAME_FOR_PAGING = "Lines.findByStationNameForPaging";
	public static final String FIND_BY_STATION_NAME_FOR_PAGING_QUERY = "select l from StationsOnLine stln inner join stln.lineId as l inner join stln.stationId as s where s.stationName = ?1 ORDER BY l.lineId";
	
	

	@Id
	@Column(name = "LINEID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer lineId;

	@Column(name = "LINENAME", nullable = false, length = 100)
	private String lineName;

	/* Bi-directional one-to-many association to Routes */
	@OneToMany(mappedBy = "lineId", fetch = FetchType.LAZY)
	private Set<Routes> routes;

	/* Bi-directional one-to-many association to StationsOnLine */
	@OneToMany(mappedBy = "lineId", fetch = FetchType.LAZY)
	private Set<StationsOnLine> stationsOnLines;

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
		if (trueEnterValueOfLineName(lineName)) {
			this.lineName = lineName;
		}
	}

	/**
	 * @return the lineId
	 */
	public Integer getLineId() {
		return lineId;
	}

	/**
	 * @param lineId
	 *            to set
	 */
	public void setLineId(Integer lineId) {
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
	public Set<Routes> getRoutes() {
		return routes;
	}

	/**
	 * @param routes
	 *            to set
	 */
	public void setRoutes(Set<Routes> routes) {
		this.routes = routes;
	}

	/**
	 * @return the List of stationsOnLines
	 */
	public Set<StationsOnLine> getStationsOnLines() {
		return stationsOnLines;
	}

	/**
	 * @param stationsOnLines
	 *            to set
	 */
	public void setStationsOnLines(Set<StationsOnLine> stationsOnLines) {
		this.stationsOnLines = stationsOnLines;
	}

	/**
	 * Check lineName is not null
	 * 
	 * @param lineNameValue
	 * @return true or false
	 */
	private boolean trueEnterValueOfLineName(String lineNameValue) {
		if (lineNameValue != "" && lineNameValue != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(lineName).hashCode();
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
		return new EqualsBuilder().append(lineName, other.lineName).isEquals();
	}

}

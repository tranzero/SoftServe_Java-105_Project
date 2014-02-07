package com.ita.edu.softserve.entity;


import java.util.Set;

import javax.persistence.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author admin
 * 
 *         The persistent class for the STATIONSONLINE database table.
 */

@Entity
@Table(name = "stationsonlines")
@NamedQueries({
@NamedQuery(name = StationsOnLine.FIND_BY_STATIONS_ID, query = StationsOnLine.FIND_BY_STATIONS_ID_QUERY),
@NamedQuery(name = StationsOnLine.FIND_BY_TWO_STATIONS, query = StationsOnLine.FIND_BY_TWO_STATIONS_QUERY),
@NamedQuery(name = StationsOnLine.FIND_BY_LINE_ID, query = StationsOnLine.FIND_BY_LINE_ID_QUERY),
@NamedQuery(name =StationsOnLine.GET_LINES_BY_STATION_COUNT,query = StationsOnLine.GET_LINES_BY_STATION_COUNT_QUERY),
@NamedQuery(name =StationsOnLine.GET_LINES_BY_STATION_FOR_PAGING,query = StationsOnLine. GET_LINES_BY_STATION_FOR_PAGING_QUERY),
@NamedQuery(name = StationsOnLine.FIND_BY_LINE_ID_AND_STATION_ID, query = StationsOnLine.FIND_BY_LINE_ID_AND_STATION_ID_QUERY)})
public class StationsOnLine extends BaseEntity {


	public static final String FIND_BY_STATIONS_ID = "StationsOnLine.findByStationID";
//	public static final String FIND_BY_STATIONS_ID_QUERY ="SELECT u FROM StationsOnLine u WHERE u.stationId = ?1";
//	public static final String FIND_BY_STATIONS_ID_QUERY ="SELECT stl FROM StationsOnLine stl INNER JOIN stl.stationId s WHERE s.stationId = ?1";
	public static final String FIND_BY_STATIONS_ID_QUERY ="SELECT stl FROM StationsOnLine stl WHERE stl.stationId.stationId = ?1";

	public static final String FIND_BY_TWO_STATIONS = "StationsOnLine.findByTwoStations";
	public static final String FIND_BY_TWO_STATIONS_QUERY ="select stl from StationsOnLine stl, StationsOnLine stlt where stl.stationId.stationName = ?1 and stlt.stationId.stationName = ?2 and stl.lineId.lineId = stlt.lineId.lineId and stlt.stationOrderNum > stl.stationOrderNum";

	public static final String FIND_BY_LINE_ID_AND_STATION_ID = "StationsOnLine.findByStationIdAndLineId";
	public static final String FIND_BY_LINE_ID_AND_STATION_ID_QUERY ="select stl from StationsOnLine stl where stl.stationId.stationId = ?1 and stl.lineId.lineId = ?2";

	
	
	public static final String FIND_BY_LINE_ID = "StationsOnLine.findByLineID";
	public static final String FIND_BY_LINE_ID_QUERY="SELECT stl FROM StationsOnLine stl WHERE stl.lineId.lineId = ?1";
	public static final String GET_LINES_BY_STATION_COUNT="StationsOnLine.getLinesByStationCount";
	public static final String GET_LINES_BY_STATION_COUNT_QUERY="SELECT COUNT ( stl.stationOnLineId) FROM StationsOnLine stl WHERE stl.lineId.lineId = ?1";
	public static final String GET_LINES_BY_STATION_FOR_PAGING = "StationsOnLine.getLinesByStationForPaging";
	public static final String GET_LINES_BY_STATION_FOR_PAGING_QUERY = "SELECT stl from StationsOnLine stl WHERE stl.lineId.lineId = ?1 ORDER BY stl.stationOnLineId";
	
	@Id
	@Column(name = "STATIONONLINEID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stationOnLineId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LINEID")
	private Lines lineId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATIONID")
	private Stations stationId;

	@OneToMany(mappedBy = "stationOnLineId", fetch = FetchType.LAZY)
	private Set<Stops> stops;

	@Column(name = "STATIONORDERNUM")
	private int stationOrderNum;

	/**
	 * Default Constructor
	 */
	public StationsOnLine() {
	}

	/**
	 * @return the stationOnLineId
	 */
	public int getStationOnLineId() {
		return stationOnLineId;
	}

	/**
	 * @param stationOnLineId
	 *            the stationOnLineId to set
	 */
	public void setStationOnLineId(int stationOnLineId) {
		this.stationOnLineId = stationOnLineId;
	}

	/**
	 * @return the lineId
	 */
	public Lines getLineId() {
		return lineId;
	}

	/**
	 * @param lineId
	 *            the lineId to set
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
	 * @param stationId
	 *            the stationId to set
	 */
	public void setStationId(Stations stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the stops
	 */
	public Set<Stops> getStops() {
		return stops;
	}

	/**
	 * @param stops
	 *            the stops to set
	 */
	public void setStops(Set<Stops> stops) {
		this.stops = stops;
	}

	/**
	 * @return the stationOrderNum
	 */
	public int getStationOrderNum() {
		return stationOrderNum;
	}

	/**
	 * @param stationOrderNum
	 *            the stationOrderNum to set
	 */
	public void setStationOrderNum(int stationOrderNum) {
		this.stationOrderNum = stationOrderNum;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(stationOnLineId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StationsOnLine other = (StationsOnLine) obj;
		return new EqualsBuilder().append(stationOnLineId, other.stationOnLineId).isEquals();
	}

}

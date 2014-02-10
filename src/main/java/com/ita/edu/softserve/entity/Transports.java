package com.ita.edu.softserve.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The persistent class for the transports database table.
 * 
 * @author Roman
 */
@Entity
@Table(name = "transports")
@NamedQueries({
		@NamedQuery(name = Transports.TRANSPORTS_FIND_ALL, query = Transports.TRANSPORTS_FIND_ALL_QUERY),
		@NamedQuery(name = Transports.FIND_BY_ROUTEID, query = Transports.FIND_BY_ROUTEID_QUERY),
		@NamedQuery(name = Transports.FIND_BY_TWO_STATIONS, query = Transports.FIND_BY_TWO_STATIONS_QUERY)
})
public class Transports extends BaseEntity {

	static final String TRANSPORTS_FIND_ALL = "Transports.findAll";
	static final String TRANSPORTS_FIND_ALL_QUERY = "SELECT t FROM Transports t";
 
	public static final String FIND_BY_ROUTEID = "Transport.findByRouteId";
	public static final String FIND_BY_ROUTEID_QUERY = "SELECT t FROM Transports t WHERE t.routes.routeId = ?1";

	public static final String FIND_BY_TWO_STATIONS = "Transports.findByTwoStations";
	public static final String FIND_BY_TWO_STATIONS_QUERY ="SELECT "
			+ "NEW com.ita.edu.softserve.manager.impl.TransportTravel(t, s.departure, TIME(MAX(s.arrival))) "
			+ "FROM Transports t "
			+ "JOIN t.routes r "
			+ "JOIN r.stops s "
			+ "JOIN s.stationOnLineId sol "
			+ "JOIN sol.stationId st "
			+ "WHERE t.routes.routeId in "
			+ "(SELECT s1.routeId.routeId "
			+ "FROM Stops s1, Stops s2 "
			+ "WHERE s1.stationOnLineId.stationId.stationName = ?1 "
			+ "and s2.stationOnLineId.stationId.stationName = ?2 "
			+ "and s1.stationOnLineId.lineId.lineId = s2.stationOnLineId.lineId.lineId "
			+ "and s2.stationOnLineId.stationOrderNum > s1.stationOnLineId.stationOrderNum"
			+ ") and (sol.stationId.stationName=?1 or sol.stationId.stationName=?2) "
			+ "GROUP BY t.transportCode";

	@Id
	@Column(name = "TRANSPORTID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transportId;

	@Column(name = "TRANSPORTCODE", nullable = false, length = 20)
	private String transportCode;

	@Column(name = "STARTTIME", nullable = false)
	private Time startTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROUTEID")
	private Routes routes;

	@Column(name = "SEATCLASS1", nullable = false)
	private int seatclass1;

	@Column(name = "SEATCLASS2", nullable = false)
	private int seatclass2;

	@Column(name = "SEATCLASS3", nullable = false)
	private int seatclass3;

	@Column(name = "GENPRICE", nullable = false)
	private double genPrice;

	public Transports() {
		super();
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public String getTransportCode() {
		return transportCode;
	}

	public void setTransportCode(String transportCode) {
		this.transportCode = transportCode;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Routes getRoutes() {
		return routes;
	}

	public void setRoutes(Routes routes) {
		this.routes = routes;
	}

	public int getSeatclass1() {
		return seatclass1;
	}

	public void setSeatclass1(int seatclass1) {
		this.seatclass1 = seatclass1;
	}

	public int getSeatclass2() {
		return seatclass2;
	}

	public void setSeatclass2(int seatclass2) {
		this.seatclass2 = seatclass2;
	}

	public int getSeatclass3() {
		return seatclass3;
	}

	public void setSeatclass3(int seatclass3) {
		this.seatclass3 = seatclass3;
	}

	public double getGenPrice() {
		return genPrice;
	}

	public void setGenPrice(double genprice) {
		this.genPrice = genprice;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transportCode).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transports other = (Transports) obj;

		return new EqualsBuilder().append(transportCode, other.transportCode)
				.isEquals();
	}
}
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
	@NamedQuery(name = Transports.FIND_BY_ROUTEID, query = Transports.FIND_BY_ROUTEID_QUERY)
})
public class Transports extends BaseEntity {

	static final String TRANSPORTS_FIND_ALL = "Transports.findAll";
	static final String TRANSPORTS_FIND_ALL_QUERY = "SELECT t FROM Transports t";

	public static final String FIND_BY_ROUTEID = "Transport.findByRouteId";
	public static final String FIND_BY_ROUTEID_QUERY = "SELECT t FROM Transports t WHERE t.routes.routeId = ?1";

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

	public Transports() {
		super();
	}

	public Transports(String transportCode, Time startTime, Routes routes) {
		this();
		this.transportCode = transportCode;
		this.startTime = startTime;
		this.routes = routes;
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transportCode)
				.hashCode();
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
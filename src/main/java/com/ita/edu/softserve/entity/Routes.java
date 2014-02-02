package com.ita.edu.softserve.entity;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author admin _ The persistent class for the ROUTES database table.
 */
@Entity
@Table(name = "routes")
@NamedQuery(name = Routes.FIND_BY_CODE, query = Routes.FIND_BY_CODE_QUERY)

public class Routes extends BaseEntity {

	public static final String FIND_BY_CODE = "Routes.findByCode";
	public static final String FIND_BY_CODE_QUERY = "SELECT u FROM Routes u WHERE u.routeCode = ?1";

	@Id
	@Column(name = "ROUTEID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int routeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LINEID")
	private Lines lineId;

	@OneToMany(mappedBy = "routeId", fetch = FetchType.LAZY)
	private Set<Stops> stops;

	@Column(name = "ROUTECODE", length = 20)
	private String routeCode;

	@Column(name = "STARTTIME")
	private Time startTime;
	
	@OneToMany(mappedBy = "routes", fetch = FetchType.LAZY)
	private List<Transports> transports;
	
	/**
	 * Default Constructor
	 */
	public Routes() {
	}

	/**
	 * @return the routeId
	 */
	public int getRouteId() {
		return routeId;
	}

	/**
	 * @param routeId
	 *            the routeId to set
	 */
	public void setRouteId(int routeId) {
		this.routeId = routeId;
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
	 * @return the routeCode
	 */
	public String getRouteCode() {
		return routeCode;
	}

	/**
	 * @param routeCode
	 *            the routeCode to set
	 */
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	/**
	 * @return the startTime
	 */
	public Time getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(routeId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Routes other = (Routes) obj;
		return new EqualsBuilder().append(routeId, other.routeId).isEquals();
	}
	
	
}
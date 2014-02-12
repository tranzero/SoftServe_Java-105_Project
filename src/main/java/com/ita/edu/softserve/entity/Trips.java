package com.ita.edu.softserve.entity;

import java.util.Date;

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


@Entity
@Table(name = "trips")
@NamedQueries({
	@NamedQuery(name = Trips.TRIPS_FIND_ALL, query = Trips.TRIPS_FIND_ALL_QUERY),
	@NamedQuery(name = Trips.TRIPS_FIND_COUNT, query = Trips.TRIPS_FIND_COUNT_QUERY),
	@NamedQuery(name = Trips.FIND_BY_TRANSPORTID, query = Trips.FIND_BY_TRANSPORTID_QUERY)
})
public class Trips extends BaseEntity{
	
	public static final String TRIPS_FIND_ALL = "Trips.findAll";
	public static final String TRIPS_FIND_ALL_QUERY = "SELECT tr FROM Trips tr";

	public static final String TRIPS_FIND_COUNT = "Trips.findCount";
	public static final String TRIPS_FIND_COUNT_QUERY = "SELECT COUNT(tr.tripId) FROM Trips tr";
	
	public static final String FIND_BY_TRANSPORTID = "Trips.findByTransportId";
	public static final String FIND_BY_TRANSPORTID_QUERY = "SELECT tr FROM Trips tr WHERE tr.transport.transportId = ?1";

	
	
	@Id
	@Column(name = "TRIPID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tripId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRANSPORTID")
	private Transports transport;
	
	
	@Column(name = "REMSEATCLASS1", nullable = false)
	private Integer remSeatClass1;
	
	
	@Column(name = "REMSEATCLASS2", nullable = false)
	private Integer remSeatClass2;
	
	
	@Column(name = "REMSEATCLASS3", nullable = false)
	private Integer remSeatClass3;
	
	
	@Column(name = "STARTDATE", nullable = false)
	private Date startDate;
	
	
	
	public Trips() {
		super();
	}

	
	
	public Trips(Transports transport, Integer remSeatClass1, Integer remSeatClass2,
			Integer remSeatClass3, Date startDate) {
		super();
		this.transport = transport;
		this.remSeatClass1 = remSeatClass1;
		this.remSeatClass2 = remSeatClass2;
		this.remSeatClass3 = remSeatClass3;
		this.startDate = startDate;
	}

	public Trips(Transports transport, Date startDate) {
		super();
		this.transport = transport;
		this.remSeatClass1 = transport.getSeatclass1();
		this.remSeatClass2 = transport.getSeatclass2();
		this.remSeatClass3 = transport.getSeatclass3();
		this.startDate = startDate;
	}


	/**
	 * @return the tripId
	 */
	public Integer getTripId() {
		return tripId;
	}
//	/**
//	 * @param tripId the tripId to set
//	 */
//	public void setTripId(Integer tripId) {
//		this.tripId = tripId;
//	}
	/**
	 * @return the transportId
	 */
	public Transports getTransport() {
		return transport;
	}
	/**
	 * @param transportId the transportId to set
	 */
	public void setTransport(Transports transport) {
		this.transport = transport;
	}
	/**
	 * @return the remSeatClass1
	 */
	public Integer getRemSeatClass1() {
		return remSeatClass1;
	}
	/**
	 * @param remSeatClass1 the remSeatClass1 to set
	 */
	public void setRemSeatClass1(Integer remSeatClass1) {
		this.remSeatClass1 = remSeatClass1;
	}
	/**
	 * @return the remSeatClass2
	 */
	public Integer getRemSeatClass2() {
		return remSeatClass2;
	}
	/**
	 * @param remSeatClass2 the remSeatClass2 to set
	 */
	public void setRemSeatClass2(Integer remSeatClass2) {
		this.remSeatClass2 = remSeatClass2;
	}
	/**
	 * @return the remSeatClass3
	 */
	public Integer getRemSeatClass3() {
		return remSeatClass3;
	}
	/**
	 * @param remSeatClass3 the remSeatClass3 to set
	 */
	public void setRemSeatClass3(Integer remSeatClass3) {
		this.remSeatClass3 = remSeatClass3;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transport).append(startDate)
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
		Trips other = (Trips) obj;

		return new EqualsBuilder().append(transport, other.transport).append(startDate, other.startDate)
				.isEquals();
	}



	@Override
	public String toString() {
		return "" + tripId;
	}
}

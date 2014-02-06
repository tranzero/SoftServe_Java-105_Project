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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


@Entity
@Table(name = "trips")
public class Trips extends BaseEntity{
	
	
	@Id
	@Column(name = "TRIPID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tripId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRANSPORTID")
	private Transports transport;
	
	
	@Column(name = "REMSEATCLASS1", nullable = false)
	private int remSeatClass1;
	
	
	@Column(name = "REMSEATCLASS2", nullable = false)
	private int remSeatClass2;
	
	
	@Column(name = "REMSEATCLASS3", nullable = false)
	private int remSeatClass3;
	
	
	@Column(name = "STARTDATE", nullable = false)
	private Date startDate;
	
	
	
	public Trips() {
		super();
	}

	
	
	public Trips(Transports transport, int remSeatClass1, int remSeatClass2,
			int remSeatClass3, Date startDate) {
		super();
		this.transport = transport;
		this.remSeatClass1 = remSeatClass1;
		this.remSeatClass2 = remSeatClass2;
		this.remSeatClass3 = remSeatClass3;
		this.startDate = startDate;
	}




	/**
	 * @return the tripId
	 */
	public int getTripId() {
		return tripId;
	}
	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
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
	public int getRemSeatClass1() {
		return remSeatClass1;
	}
	/**
	 * @param remSeatClass1 the remSeatClass1 to set
	 */
	public void setRemSeatClass1(int remSeatClass1) {
		this.remSeatClass1 = remSeatClass1;
	}
	/**
	 * @return the remSeatClass2
	 */
	public int getRemSeatClass2() {
		return remSeatClass2;
	}
	/**
	 * @param remSeatClass2 the remSeatClass2 to set
	 */
	public void setRemSeatClass2(int remSeatClass2) {
		this.remSeatClass2 = remSeatClass2;
	}
	/**
	 * @return the remSeatClass3
	 */
	public int getRemSeatClass3() {
		return remSeatClass3;
	}
	/**
	 * @param remSeatClass3 the remSeatClass3 to set
	 */
	public void setRemSeatClass3(int remSeatClass3) {
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

}

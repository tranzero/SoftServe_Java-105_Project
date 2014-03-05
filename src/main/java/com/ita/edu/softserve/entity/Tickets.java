package com.ita.edu.softserve.entity;

import java.io.Serializable;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;





import com.google.common.base.Objects;

@Entity
@Table(name = "tickets")
@NamedQueries(
		@NamedQuery(name = Tickets.FIND_BY_ORDERID, query = Tickets.FIND_BY_ORDERID_QUERY)
)
public class Tickets extends BaseEntity implements Serializable {

	/**
	 * 
	 */

	
	private static final long serialVersionUID = -5039457734811028773L;
	
	public static final String FIND_BY_ORDERID = "Tickets.findByOrderId";
	public static final String FIND_BY_ORDERID_QUERY = "SELECT t FROM Tickets t WHERE t.order.orderId = ?1";

	@Id
	@Column(name = "TICKETID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;

	@Column(name = "TICKETNAME")
	private String ticketName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDERID")
	private Orders order;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TRIPID")
	private Trips trip;

	@Column(name = "CUSTOMERFIRSTNAME")
	private String customerFirstName;

	@Column(name = "CUSTOMERLASTNAME")
	private String customerLastName;

	@Column(name = "SEATTYPE")
	private Integer seatType;

	public Tickets() {

	}

	public Tickets(String ticketName, Trips trip, String customerFirstName,
			String customerLastName, Integer seatType) {
		super();
		this.ticketName = ticketName;
		this.trip = trip;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.seatType = seatType;
	}

	public Tickets(String ticketName, Orders order, Trips trip,
			String customerFirstName, String customerLastName, Integer seatType) {
		super();
		this.ticketName = ticketName;
		this.order = order;
		this.trip = trip;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.seatType = seatType;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Trips getTrip() {
		return trip;
	}

	public void setTrip(Trips trip) {
		this.trip = trip;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public Integer getSeatType() {
		return seatType;
	}

	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}

	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(ticketId).append(ticketName).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tickets other = (Tickets) obj;
		return new EqualsBuilder().append(ticketId, other.ticketId).isEquals();
	}

}


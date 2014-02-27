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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.Objects;




@Entity
@Table(name = "tickets")
public class Tickets extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -5039457734811028773L;

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

	@Column(name = "CUSTOMERINFO")
	private String customerInfo;
	
	@Column (name = "SETATYPE")
	private Integer seatType;
	
	public Tickets(){
		
	}
	
	public Tickets(String ticketName, Orders order, Trips trip,
			String customerInfo, Integer seatType) {
		super();
		this.ticketName = ticketName;
		this.order = order;
		this.trip = trip;
		this.customerInfo = customerInfo;
		this.seatType = seatType;
	}
	

	public Tickets( String ticketName, Trips trip,
			String customerInfo, Integer seatType) {
		super();
		
		this.ticketName = ticketName;
		this.trip = trip;
		this.customerInfo = customerInfo;
		this.seatType = seatType;
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


	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Integer getSeatType() {
		return seatType;
	}


	public void setSeatType(Integer seatType) {
		this.seatType = seatType;
	}


	public Integer getTicketId() {
		return ticketId;
	}


	@Override
	public String toString() {
		 return Objects.toStringHelper(this)  
	                .addValue(this.getTicketName())  
	                .addValue(this.getTrip().getTripId())  
	                .addValue(this.getOrder().getOrderId())  
	                .toString();  
	}
	

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
	    return obj instanceof Tickets &&
	        Objects.equal(this.getTicketId(), ((Tickets) obj).getTicketId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.getTicketId(), this.getTicketName());
	}

}

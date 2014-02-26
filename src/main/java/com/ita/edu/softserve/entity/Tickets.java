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
	
	@Column (name = "ISSEATCLASS1")
	private Boolean isSeatClass1;
	
	@Column (name = "ISSEATCLASS2")
	private Boolean isSeatClass2;
	
	@Column (name = "ISSEATCLASS3")
	private Boolean isSeatClass3;

	public Tickets(){
		
	}
	
	public Tickets( String customerInfo, Boolean isSeatClass1, Boolean isSeatClass2,
			Boolean isSeatClass3) {
		super();
		this.customerInfo = customerInfo;
		this.isSeatClass1 = isSeatClass1;
		this.isSeatClass2 = isSeatClass2;
		this.isSeatClass3 = isSeatClass3;
	}

	public Tickets(String ticketName, Orders order, Trips trip,
			String customerInfo, Boolean isSeatClass1, Boolean isSeatClass2,
			Boolean isSeatClass3) {
		super();
		this.ticketName = ticketName;
		this.order = order;
		this.trip = trip;
		this.customerInfo = customerInfo;
		this.isSeatClass1 = isSeatClass1;
		this.isSeatClass2 = isSeatClass2;
		this.isSeatClass3 = isSeatClass3;
	}
	
	public Tickets(String ticketName, Trips trip,
			String customerInfo, Boolean isSeatClass1, Boolean isSeatClass2,
			Boolean isSeatClass3) {
		super();
		this.ticketName = ticketName;
		this.trip = trip;
		this.customerInfo = customerInfo;
		this.isSeatClass1 = isSeatClass1;
		this.isSeatClass2 = isSeatClass2;
		this.isSeatClass3 = isSeatClass3;
	}



	@Override
	public int hashCode() {
		return Objects.hashCode(this.getTicketId(), this.getTicketName());
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

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}
	
	public Trips getTrip() {
		return trip;
	}

	public void setTrip(Trips trip) {
		this.trip = trip;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}

	public Boolean getIsSeatClass1() {
		return isSeatClass1;
	}

	public void setIsSeatClass1(Boolean isSeatClass1) {
		this.isSeatClass1 = isSeatClass1;
	}

	public Boolean getIsSeatClass2() {
		return isSeatClass2;
	}

	public void setIsSeatClass2(Boolean isSeatClass2) {
		this.isSeatClass2 = isSeatClass2;
	}

	public Boolean getIsSeatClass3() {
		return isSeatClass3;
	}

	public void setIsSeatClass3(Boolean isSeatClass3) {
		this.isSeatClass3 = isSeatClass3;
	}

	public Integer getTicketId() {
		return ticketId;
	}
}

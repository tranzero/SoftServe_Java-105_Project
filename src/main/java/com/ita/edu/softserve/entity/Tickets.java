package com.ita.edu.softserve.entity;

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
@Table(name = "tickets")
public class Tickets extends BaseEntity{
	
	@Id
	@Column(name = "TICKETID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	
	@Column(name = "TICKETNAME")
	private String ticketName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDERID")
	private Orders order;
	
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
	
	public Tickets(String ticketName, Orders order, String customerInfo,
			Boolean isSeatClass1, Boolean isSeatClass2, Boolean isSeatClass3) {
		super();
		this.ticketName = ticketName;
		this.order = order;
		this.customerInfo = customerInfo;
		this.isSeatClass1 = isSeatClass1;
		this.isSeatClass2 = isSeatClass2;
		this.isSeatClass3 = isSeatClass3;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(ticketId).append(ticketName)
				.hashCode();
	}

	@Override
	public String toString() {
		return "" + ticketName + " orderId=" + order.getOrderId()+ " tripId="+ order.getTripId().getTripId();
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
package com.ita.edu.softserve.manager.impl;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.mail.Transport;

import com.ita.edu.softserve.entity.Transports;

public class TransportTravel {
		
	private Transports transport;
	
	private Time departureTime;
	
	private Time arrivalTime;
	
	private Time duration;
	
	public TransportTravel() {
		this.transport = null;
		this.departureTime = null;
		this.arrivalTime = null;
		this.duration = null;
	}
	
	public TransportTravel(Transports transport) {
		this.transport = transport;
	}
	
	public Transports getTransport() {
		return transport;
	}

	public void setTransport(Transports transport) {
		this.transport = transport;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}
	
	/*
	 * Sum two Times
	 */
	public static Time sumTimes(Time... time) {
		int secs = 0;
		int mins = 0;
		int hrs = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		for (int i = 0; i < time.length; i++) {
			Date date = new Date(time[i].getTime());
			calendar.setTime(date);
			secs += calendar.get(Calendar.SECOND);
			mins += calendar.get(Calendar.MINUTE);
			hrs += calendar.get(Calendar.HOUR_OF_DAY);
		}
		calendar.set(0, 0, 0, hrs, mins, secs);
		
		return new Time(calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
	}

}

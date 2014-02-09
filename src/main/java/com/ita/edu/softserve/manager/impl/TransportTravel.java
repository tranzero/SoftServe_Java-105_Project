package com.ita.edu.softserve.manager.impl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import com.ita.edu.softserve.entity.Transports;

public class TransportTravel {
		
	private Transports transport;
	
	private Object departureTime;
	
	private Object arrivalTime;
	
	private Object duration;
	
	public TransportTravel() {
		this.transport = null;
		this.departureTime = null;
		this.arrivalTime = null;
		this.duration = null;
	}

	public TransportTravel(Transports transport, Object departureTime, Object arrivalTime, Object duration) {
		this.transport = transport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
	}
	
	public Transports getTransport() {
		return transport;
	}

	public void setTransport(Transports transport) {
		this.transport = transport;
	}
	
	public Object getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Object getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Object getDuration() {
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

	/*
	 * Subtract two Times
	 */
	public static Time subtractTimes(Time time1, Time time2) {
		int secs = 0;
		int mins = 0;
		int hrs = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		Date date1 = new Date(time1.getTime());
		Date date2 = new Date(time2.getTime());
		calendar.setTime(date1);

		secs = calendar.get(Calendar.SECOND);
		mins = calendar.get(Calendar.MINUTE);
		hrs = calendar.get(Calendar.HOUR_OF_DAY);

		calendar.setTime(date2);
		
		secs -= calendar.get(Calendar.SECOND);
		mins -= calendar.get(Calendar.MINUTE);
		hrs -= calendar.get(Calendar.HOUR_OF_DAY);
		
		calendar.set(0, 0, 0, hrs, mins, secs);
		
		return new Time(calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
	}

}

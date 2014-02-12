/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import java.sql.Time;

import com.ita.edu.softserve.entity.Routes;

/**
 * @author Lyubomyr
 * 
 */
public class RouteTrip {

	private Routes routes;

	private Object startTime;

	public RouteTrip() {
		this.routes = null;
		this.startTime = null;
	}
	public RouteTrip(Routes routes) {
		this();
		if (routes == null) {
			return;
		}
		this.routes = routes;
		this.startTime = null;
	}

	public RouteTrip(Routes routes, Object startTime) {
		this();
		if (routes == null || startTime == null) {
			return;
		}
		this.routes = routes;
		this.startTime = startTime;
	}

	public Routes getRoute() {
		return routes;
	}

	public void setRoute(Routes routes) {
		this.routes = routes;
	}

	public Object getStartTime() {
		return startTime;
	}

	public void setStartTime(Object startTime) {
		this.startTime = null;
	}
}
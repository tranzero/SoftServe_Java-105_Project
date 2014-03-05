package com.ita.edu.softserve.validation;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.TransportsManager;
import com.ita.edu.softserve.utils.ParseUtil;

@Component("routesValidator")
public class RoutesValidator1  implements Validator  {
	public static final String ROUTE_CODE_PATERN = "^[a-zA-Z0-9]{10,15}$";

	private static final String ROUTE_CODE = "routeCode";
	private static final String LINE_NAME = "lineName";
	private static final String STATION_START_NAME = "stationStartName";
	private static final String STATION_END_NAME = "stationEndName";
	
	private static final String LINES = "lines";
	private static final String STATION_START = "stationStart";
	private static final String STATION_END = "stationEnd";

	private static final String ROUTE_CODE_MATCHER = "routeCode.matcher";
	private static final String ROUTE_CODE_EXIST = "routeCode.exist";
	private static final String LINE_NAME_MATCHER = "lineName.matcher";
	private static final String LINE_NAME_EXIST = "lineName.exist";
	private static final String STATION_START_MATCHER = "stationStartName.matcher";
	private static final String STATION_START_EXIST = "stationStartName.exist";
	private static final String STATION_END_MATCHER = "stationEndName.matcher";
	private static final String STATION_END_EXIST = "stationEndName.exist";
	
	private static final String LINES_NOT_EXIST = "lines.exist";
	private static final String STATION_START_NOT_EXIST = "stationStart.exist";
	private static final String STATION_END_NOT_EXIST = "stationEnd.exist";
	
	/**
	 * Field for using routes-related methods.
	 */
	@Autowired
	private RoutesManager routesManager;

	/**
	 * This Validator validates *just* Routes instance
	 */
	public boolean supports(Class<?> clazz) {
		return Routes.class.equals(clazz);
	}

	/**
	 * This method provide validation for Routes objects.
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors error) {
		Routes route = (Routes) obj;

		validateRouteCode(route.getRouteCode(), error);

		validateIfRouteExist(route.getRouteId(), route.getRouteCode(), error);

		validateLines(route, route.getLineId(), error);
	}
	
	/**
	 * String must contain only letter and digits more then 5 and less then 15.
	 * 
	 * @param routeCode
	 *            The route code to match.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateRouteCode(String routeCode, Errors error) {

		if (routeCode.matches(ROUTE_CODE_PATERN) == false) {
			error.rejectValue(ROUTE_CODE, ROUTE_CODE_MATCHER);
		}
	}

	/**
	 * Finds out if Routes object exist in database with such route
	 * code. 
	 * 
	 * @param routeCode
	 *            the route code to check.
	 * @param error
	 *            the error to register message.
	 */
	private void validateIfRouteExist(Integer routeId, String routeCode, Errors error) {

		if ((routeCode != null) && (routeCode != "")) {
			try {
				Routes route = routesManager.findByCode(routeCode);

				if ((route .getRouteId()).equals(routeId)) {
					return;
				}
				error.rejectValue(ROUTE_CODE, ROUTE_CODE_EXIST);
			} catch (RuntimeException e) {
			}
		}
	}
	
	/**
	 * If lines is <code>null</code> that mean it is not in database.
	 * 
	 * @param lines
	 *            the Lines to verify.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateLines(Routes route, Lines line, Errors error) {
		if (line == null) {
			error.rejectValue(LINES, LINES_NOT_EXIST);
		}
		else{
			//validateStationStart(route.getStationStartId(),error);
			//validateStationEnd(route.getStationEndId(),error);
		}
	}
	/**
	 * If stationStart is <code>null</code> that mean it is not in database.
	 * 
	 * @param stationStart
	 *            the Stations to verify.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateStationStart(Stations stationStart, Errors error) {
		if (stationStart == null) {
			error.rejectValue(STATION_START, STATION_START_NOT_EXIST);
		}
	}
	/**
	 * If lines is <code>null</code> that mean it is not in database.
	 * 
	 * @param stationEnd
	 *            the Stations to verify.
	 * @param error
	 *            the Errors object that stores and exposes information about
	 *            data-binding and validation errors for a specific object.
	 */
	private void validateStationEnd(Stations stationEnd, Errors error) {
		if (stationEnd == null) {
			error.rejectValue(STATION_END, STATION_END_NOT_EXIST);
		}
	}
}

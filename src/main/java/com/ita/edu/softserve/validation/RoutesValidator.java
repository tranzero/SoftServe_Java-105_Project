package com.ita.edu.softserve.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.manager.StationOnLineManager;

@Component("routesValidator")
public class RoutesValidator implements Validator {
	/**
	 * ResponsesManager Field
	 */
	@Autowired
	private RoutesManager routesManager;
	/**
	 * ResponsesManager Field
	 */
	@Autowired
	private LinesManager linesManager;
	/**
	 * ResponsesManager Field
	 */
	@Autowired
	private StationOnLineManager stationOnLineManager;

	public static final String ROUTE_CODE_PATERN = "^[0-9]{5,15}$";
	public static final String NAME_CODE_PATERN = ".{3,20}";
	public static final String IS_NO_ERROR = "0";
	public static final String IS_ERROR = "1";
	public static final String NO_ERROR_TEXT = "";

	private static final String ROUTE_CODE = "routeCode";
	private static final String LINE_NAME = "lineId";
	private static final String STATION_START = "stationStartId";
	private static final String STATION_END = "stationEndId";

	private static final String ROUTE_CODE_MATCHER = "routeCode.matcher";
	private static final String LINE_NAME_MATCHER = "lineName.matcher";
	private static final String STATION_START_MATCHER = "stationStart.matcher";
	private static final String STATION_END_MATCHER = "stationEnd.matcher";
	private static final String LINE_NOT_EXIST = "line.notExist";
	private static final String STATION_START_NOT_EXIST = "stationStart.notExist";
	private static final String STATION_END_NOT_EXIST = "stationEnd.notExist";
	private static final String ROUTE_CODE_EXIST = "routeCode.exist";
	private static final String SAME_STATIONS = "stations.Same";

	/**
	 * This Validator validates *just* Routes instance
	 */
	public boolean supports(Class<?> clazz) {
		return Routes.class.equals(clazz);
	}

	/**
	 * This method set RoutesManager
	 */
	public void setRoutesManager(RoutesManager routesManager) {
		this.routesManager = routesManager;
	}

	/**
	 * This method set LinesManager
	 */
	public void setLinesManager(LinesManager linesManager) {
		this.linesManager = linesManager;
	}

	/**
	 * This method set StationOnLineManager
	 */
	public void setStationOnLineManager(
			StationOnLineManager stationOnLineManager) {
		this.stationOnLineManager = stationOnLineManager;
	}

	/**
	 * This method provide validation for Routes objects.
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */

	public void validate(Object obj, Errors error) {
		Routes route = (Routes) obj;

		validateRouteByCode(route.getRouteId(), route.getRouteCode(), error);

		validateLine(route.getLineId(), error);

		validateStations(route.getStationStartId(), route.getStationEndId(),
				route.getLineId(), error);
	}

	/**
	 * Validate routeCode name, using regexp pattern
	 */
	private void validateRouteCode(String routeCode, Errors error) {

		if (!routeCode.matches(ROUTE_CODE_PATERN)) {
			error.rejectValue(ROUTE_CODE, ROUTE_CODE_MATCHER);
		}
	}

	/**
	 * Validate lineName, using regexp pattern
	 */
	private void validateLineName(String lineName, Errors error) {
		if (!lineName.matches(NAME_CODE_PATERN)) {
			error.rejectValue(LINE_NAME, LINE_NAME_MATCHER);
		}
	}

	/**
	 * Validate stationStartName, using regexp pattern
	 */
	private void validateStationStartName(String stationStartName, Errors error) {
		if (!stationStartName.matches(NAME_CODE_PATERN)) {
			error.rejectValue(STATION_START, STATION_START_MATCHER);
		}
	}

	/**
	 * Validate stationEndName, using regexp pattern
	 */
	private void validateStationEndName(String stationEndName, Errors error) {
		if (!stationEndName.matches(NAME_CODE_PATERN)) {
			error.rejectValue(STATION_END, STATION_END_MATCHER);
		}
	}

	/**
	 * Validate route by null
	 */
	private void validateRouteCodeByNull(Integer routeId, String routeCode,
			Errors error) {

		if (routeId == null) {
			error.rejectValue(ROUTE_CODE, ROUTE_CODE_MATCHER);
		} else {
			if (routeCode == null) {
				error.rejectValue(ROUTE_CODE, ROUTE_CODE_MATCHER);
			}
		}
	}

	/**
	 * Validate start and end Station by same Name
	 */
	private void validateStationBySameName(String stationStartName,
			String stationEndName, Errors error) {

		if (stationStartName.equals(stationEndName)) {
			error.rejectValue(STATION_START, SAME_STATIONS);
			error.rejectValue(STATION_END, SAME_STATIONS);
		}
	}

	/**
	 * Validate Route by code
	 */
	private void validateRouteByCode(Integer routeId, String routeCode,
			Errors error) {
		validateRouteCodeByNull(routeId, routeCode, error);

		if (!error.hasFieldErrors(ROUTE_CODE)) {
			validateRouteCode(routeCode, error);

			if (!error.hasFieldErrors(ROUTE_CODE)) {
				validateRouteExist(routeId, routeCode, error);
			}
		}
	}

	/**
	 * Validate Line by name
	 */
	private void validateLine(Lines line, Errors error) {

		if (line == null) {
			error.rejectValue(LINE_NAME, LINE_NOT_EXIST);
		} else {

			validateLineName(line.getLineName(), error);
			if (!error.hasFieldErrors(LINE_NAME)) {
				validateLineNoExist(line.getLineName(), error);
			}
		}
	}

	/**
	 * Validate start Station by lineName
	 */
	public void validateStartStationByLine(String stationStartName,
			String lineName, Errors error) {

		if (!error.hasFieldErrors(LINE_NAME)
				&& !error.hasFieldErrors(STATION_START)) {
			validateStationStartName(stationStartName, error);
			validateStartStationByLineNoExist(stationStartName, lineName, error);
		}
	}

	/**
	 * Validate end Station by lineName
	 */
	void validateEndStationByLine(String stationEndName, String lineName,
			Errors error) {

		if (!error.hasFieldErrors(LINE_NAME)
				&& !error.hasFieldErrors(STATION_END)) {
			validateStationEndName(stationEndName, error);
			validateEndStationByLineNoExist(stationEndName, lineName, error);
		}
	}

	/**
	 * Validate routeCode name, using regexp pattern
	 */
	public boolean validateRouteCode(String routeCode) {
		return routeCode.matches(ROUTE_CODE_PATERN);
	}

	/**
	 * Validate lineName, using regexp pattern
	 */
	public boolean validateLineName(String lineName) {
		return lineName.matches(NAME_CODE_PATERN);
	}

	/**
	 * Validate stationStartName, using regexp pattern
	 */
	public boolean validateStationStartName(String stationStartName) {
		return stationStartName.matches(NAME_CODE_PATERN);
	}

	/**
	 * Validate stationEndName, using regexp pattern
	 */
	public boolean validateStationEndName(String stationEndName) {
		return stationEndName.matches(NAME_CODE_PATERN);
	}

	/**
	 * Validate Stations by null
	 */
	private void validateStationsByNull(Stations stationStart,
			Stations stationEnd, Errors error) {
		if (!error.hasFieldErrors(LINE_NAME)) {
			if (stationStart == null) {
				error.rejectValue(STATION_START, STATION_START_NOT_EXIST);
			}
			if (stationEnd == null) {
				error.rejectValue(STATION_END, STATION_END_NOT_EXIST);
			}
		}
	}

	/**
	 * Validate Stations
	 */
	private void validateStations(Stations stationStart, Stations stationEnd,
			Lines line, Errors error) {
		validateStationsByNull(stationStart, stationEnd, error);

		if (!error.hasFieldErrors(STATION_START)
				&& !error.hasFieldErrors(STATION_END)
				&& !error.hasFieldErrors(LINE_NAME)) {
			validateStartStationByLine(stationStart.getStationName(),
					line.getLineName(), error);

			validateEndStationByLine(stationEnd.getStationName(),
					line.getLineName(), error);

			if (!error.hasFieldErrors(STATION_START)
					&& !error.hasFieldErrors(STATION_END)
					&& !error.hasFieldErrors(LINE_NAME)) {
				validateStationBySameName(stationStart.getStationName(),
						stationEnd.getStationName(), error);
			}
		}
	}

	/**
	 * Check if Route object exist in database with such stationName.
	 * 
	 * @param routeId
	 * @param routeCode
	 * @param error
	 */
	public boolean validateRouteExist(Integer routeId, String routeCode,
			Errors error) {

		try {
			Routes route = routesManager.findByCode(routeCode);

			if (!(route.getRouteId()).equals(routeId)) {
				error.rejectValue(ROUTE_CODE, ROUTE_CODE_EXIST);
				return false;
			}
			return true;
		} catch (RuntimeException e) {
			return false;
		}
	}

	/**
	 * Check if Line object not exist in database with such lineName.
	 * 
	 * @param lineName
	 * @param error
	 */
	private void validateLineNoExist(String lineName, Errors error) {
		try {
			routesManager.findByLineName(lineName);
		} catch (RuntimeException e) {
			error.rejectValue(LINE_NAME, LINE_NOT_EXIST);
		}
	}

	/**
	 * Check if start Station object not exist by Line.
	 * 
	 * @param stationStartName
	 * @param lineName
	 * @param error
	 */
	private void validateStartStationByLineNoExist(String stationStartName,
			String lineName, Errors error) {

		try {
			routesManager.getStationNameByLineNameCriteria(stationStartName,
					lineName);
		} catch (RuntimeException e) {
			error.rejectValue(STATION_START, STATION_START_NOT_EXIST);
		}
	}

	/**
	 * Check if end Station object not exist by Line.
	 * 
	 * @param stationEndName
	 * @param lineName
	 * @param error
	 */
	private void validateEndStationByLineNoExist(String stationEndName,
			String lineName, Errors error) {

		try {
			routesManager.getStationNameByLineNameCriteria(stationEndName,
					lineName);
		} catch (RuntimeException e) {
			error.rejectValue(STATION_END, STATION_END_NOT_EXIST);
		}
	}

	public static final String ROUTE_ERROR_IN_NAME = "*Must contain only digits more then 5 and less then 15";
	public static final String ERROR_IN_NAME = "*Must contain more then 3 and less then 20";
	public static final String ROUTE_EXISTS = "*This route code already exists";
	public static final String ROUTE_NO_EXISTS = "*You can use this route code";
	public static final String NO_LINE_EXISTS = "*Incorect line name";
	public static final String NO_STATION_EXISTS = "*Incorect station name";
	public static final String DIFFERENT_STATION = "*Station should be different";
	public static final String ERROR_IN_LINE_NAME = "*Must contain more then 3 and less then 20";
	public static final String ERROR_IN_STATION_START_NAME = "*Must contain more then 3 and less then 20";
	public static final String ERROR_IN_STATION_END_NAME = "*Must contain more then 3 and less then 20";
}
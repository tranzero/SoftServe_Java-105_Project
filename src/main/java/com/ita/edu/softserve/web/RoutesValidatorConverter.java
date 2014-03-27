package com.ita.edu.softserve.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.RoutesManager;
import com.ita.edu.softserve.validation.RoutesValidator;

public class RoutesValidatorConverter {

	private RoutesManager routesManager;

	public RoutesValidatorConverter() {
	}

	/**
	 * Get route errors in string array
	 */
	public String[] getValidateRouteErrors(Routes route,
			RoutesValidator routesValidator, RoutesManager routesManager) {

		this.routesManager = routesManager;
		Errors error = new BeanPropertyBindingResult(route, "route");

		routesValidator.validate(route, error);

		return convertErrorToStringArray(error);
	}

	/**
	 * Convert errors to array
	 */
	public String[] convertErrorToStringArray(Errors error) {
		String[] checkResult;
		if (error.getErrorCount() == 0) {
			checkResult = new String[] { IS_NO_ERROR };
		} else {
			checkResult = new String[9];
			checkResult[0] = IS_ERROR;

			checkRouteCode(error, checkResult);

			checkLineName(error, checkResult);

			checkStartStation(error, checkResult);

			checkEndStation(error, checkResult);
		}
		return checkResult;
	}

	/**
	 * Set routeCode errors in string array
	 */
	private void checkRouteCode(Errors error, String[] checkResult) {
		if (error.hasFieldErrors(ROUTE_CODE)) {

			checkResult[1] = IS_ERROR;

			if (error.getFieldError(ROUTE_CODE).getCode()
					.equals(ROUTE_CODE_NOT_EXIST)) {
				checkResult[2] = ROUTE_NO_EXISTS;
			}
			if (error.getFieldError(ROUTE_CODE).getCode()
					.equals(ROUTE_CODE_EXIST)) {
				checkResult[2] = ROUTE_EXISTS;
			}
			if (error.getFieldError(ROUTE_CODE).getCode()
					.equals(ROUTE_CODE_MATCHER)) {
				checkResult[2] = ROUTE_ERROR_IN_NAME;
			}
		} else {
			checkResult[1] = IS_NO_ERROR;
			checkResult[2] = NO_ERROR_TEXT;
		}
	}

	/**
	 * Set lineName errors in string array
	 */
	private void checkLineName(Errors error, String[] checkResult) {
		if (error.hasFieldErrors(LINE_NAME)) {

			checkResult[3] = IS_ERROR;

			if (error.getFieldError(LINE_NAME).getCode().equals(LINE_NOT_EXIST)) {
				checkResult[4] = NO_LINE_EXISTS;
			}
			if (error.getFieldError(LINE_NAME).getCode()
					.equals(LINE_NAME_MATCHER)) {
				checkResult[4] = ERROR_IN_LINE_NAME;
			}
		} else {
			checkResult[3] = IS_NO_ERROR;
			checkResult[4] = NO_ERROR_TEXT;
		}
	}

	/**
	 * Set startStation errors in string array
	 */
	private void checkStartStation(Errors error, String[] checkResult) {
		if (error.hasFieldErrors(STATION_START)) {

			checkResult[5] = IS_ERROR;

			if (error.getFieldError(STATION_START).getCode()
					.equals(STATION_START_NOT_EXIST)) {
				checkResult[6] = NO_STATION_EXISTS;
			}
			if (error.getFieldError(STATION_START).getCode()
					.equals(STATION_START_MATCHER)) {
				checkResult[6] = ERROR_IN_STATION_START_NAME;
			}
			if (error.getFieldError(STATION_START).getCode()
					.equals(SAME_STATIONS)) {
				checkResult[6] = DIFFERENT_STATION;
			}
		} else {
			checkResult[5] = IS_NO_ERROR;
			checkResult[6] = NO_ERROR_TEXT;
		}
	}

	/**
	 * Set endStation errors in string array
	 */
	private void checkEndStation(Errors error, String[] checkResult) {
		if (error.hasFieldErrors(STATION_END)) {

			checkResult[7] = IS_ERROR;

			if (error.getFieldError(STATION_END).getCode()
					.equals(STATION_END_NOT_EXIST)) {
				checkResult[8] = NO_STATION_EXISTS;
			}
			if (error.getFieldError(STATION_END).getCode()
					.equals(STATION_END_MATCHER)) {
				checkResult[8] = ERROR_IN_STATION_END_NAME;
			}
			if (error.getFieldError(STATION_END).getCode()
					.equals(SAME_STATIONS)) {
				checkResult[8] = DIFFERENT_STATION;
			}
		} else {
			checkResult[7] = IS_NO_ERROR;
			checkResult[8] = NO_ERROR_TEXT;
		}
	}

	/**
	 * Validate RouteCode for auto validate form
	 */
	public String[] validateRouteCodeToStringArray(String routeCode) {
		String[] checkResult;
		try {
			if (!validateRouteCode(routeCode)) {
				checkResult = new String[] { IS_ERROR, ROUTE_ERROR_IN_NAME };
			} else {
				routesManager.findByCode(routeCode);
				checkResult = new String[] { IS_ERROR, ROUTE_EXISTS };
			}
		} catch (RuntimeException e) {
			checkResult = new String[] { IS_NO_ERROR, ROUTE_NO_EXISTS };
		}
		return checkResult;
	}

	/**
	 * Validate routeCode name, using regexp pattern
	 */
	public boolean validateRouteCode(String routeCode) {
		return routeCode.matches(ROUTE_CODE_PATERN);
	}

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
	private static final String ROUTE_CODE_NOT_EXIST = "routeCode.notExist";
	private static final String SAME_STATIONS = "stations.Same";

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

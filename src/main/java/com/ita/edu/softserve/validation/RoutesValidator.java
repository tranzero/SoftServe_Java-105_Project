package com.ita.edu.softserve.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.dao.LinesDAO;
import com.ita.edu.softserve.dao.RoutesDAO;
import com.ita.edu.softserve.dao.impl.LinesDAOImpl;
import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.RoutesManager;

@Component("routesValidator")
public class RoutesValidator {
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

	public static final String ROUTE_CODE_PATERN = "^[0-9]{5,15}$";
	public static final String NAME_CODE_PATERN = ".{3,20}";
	public static final String IS_NO_ERROR = "0";
	public static final String IS_ERROR = "1";
	public static final String NO_ERROR_TEXT = "";

	/**
	 * Validate routeCode name, using regexp pattern
	 */
	private boolean validateRouteCodeName(String routeCode) {
		if (routeCode.matches(ROUTE_CODE_PATERN) == false) {
			return false;
		}
		return true;
	}
	
	/**
	 * Validate lineName, using regexp pattern
	 */
	private boolean validateLine(String lineName) {
		if (lineName.matches(NAME_CODE_PATERN) == false) {
			return false;
		}
		return true;
	}

	/**
	 * Validate stationName, using regexp pattern
	 */
	private boolean validateStation(String stationName) {
		if (stationName.matches(NAME_CODE_PATERN) == false) {
			return false;
		}
		return true;
	}

	/**
	 * Validate RouteCode
	 */
	public String[] validateRouteCode(String routeCode) {
		String[] checkResult;
		try {
			if (!validateRouteCodeName(routeCode)) {
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
	 * Validate new Route
	 */
	public String[] validateAddRoute(String routeCode, String lineName,
			String stationStart, String stationEnd) {
		List<String[]> checkArrayResult = new ArrayList<String[]>();
		checkArrayResult.add(validateRouteCode(routeCode));
		checkArrayResult.add(validateLineName(lineName));
		if (checkArrayResult.get(1)[0].equals(IS_ERROR)) {
			checkArrayResult.add(validateStringNameOnlyRule(stationStart));
			checkArrayResult.add(validateStringNameOnlyRule(stationEnd));
		} else {
			checkArrayResult.add(validateStringName(stationStart, lineName));
			checkArrayResult.add(validateStringName(stationEnd, lineName));
			if (checkArrayResult.get(2)[0].equals(IS_NO_ERROR)
					&& checkArrayResult.get(3)[0].equals(IS_NO_ERROR)
					&& stationStart.equals(stationEnd)) {
				checkArrayResult.get(2)[0] = IS_ERROR;
				checkArrayResult.get(2)[1] = DIFFERENT_STATION;
				checkArrayResult.get(3)[0] = IS_ERROR;
				checkArrayResult.get(3)[1] = DIFFERENT_STATION;
			}
		}
		return convertToArray(checkArrayResult);
	}

	/**
	 * Validate lineName
	 */
	public String[] validateLineName(String lineName) {
		String[] checkResult;
		try {
			if (!validateLine(lineName)) {
				checkResult = new String[] { IS_ERROR, ERROR_IN_NAME };
			} else {
				linesManager.findByLineName(lineName);
				checkResult = new String[] { IS_NO_ERROR, NO_ERROR_TEXT };
			}
		} catch (RuntimeException e) {
			checkResult = new String[] { IS_ERROR, NO_LINE_EXISTS };
		}
		return checkResult;
	}

	/**
	 * Validate stationName
	 */
	public String[] validateStringNameOnlyRule(String stationName) {
		String[] checkResult;
		if (!validateStation(stationName)) {
			checkResult = new String[] { IS_ERROR, ERROR_IN_NAME };
		} else {
			checkResult = new String[] { IS_NO_ERROR, NO_ERROR_TEXT };
		}
		return checkResult;
	}

	/**
	 * Validate stationName by lineName
	 */
	public String[] validateStringName(String stationName, String lineName) {
		String[] checkResult;
		try {
			if (!validateStation(stationName)) {
				checkResult = new String[] { IS_ERROR, ERROR_IN_NAME };
			} else {
				List<String> stations = routesManager
						.getStationNameByLineListCriteria(stationName, lineName);
				if (stations.contains(stationName)) {
					checkResult = new String[] { IS_NO_ERROR, NO_ERROR_TEXT };
				} else {
					checkResult = new String[] { IS_ERROR, NO_STATION_EXISTS };
				}
			}
		} catch (RuntimeException e) {
			checkResult = new String[] { IS_ERROR, NO_STATION_EXISTS };
		}
		return checkResult;
	}

	/**
	 * convert result to array
	 */
	public String[] convertToArray(List<String[]> checkArrayResult) {
		String[] checkResult = null;
		boolean isError = false;
		for (int i = 0; i < 4; i++) {
			if (checkArrayResult.get(i)[0].equals(IS_ERROR)) {
				isError = true;
			}
		}
		if (isError) {
			checkResult = new String[9];
			checkResult[0] = IS_ERROR;
			int index = 1;
			for (int i = 0; i < 4; i++) {
				checkResult[index] = checkArrayResult.get(i)[0];
				index++;
				checkResult[index] = checkArrayResult.get(i)[1];
				index++;
			}
		} else {
			checkResult = new String[1];
			checkResult[0] = IS_NO_ERROR;
		}
		return checkResult;
	}

	public static final String ROUTE_ERROR_IN_NAME = "*Must contain only digits more then 5 and less then 15";
	public static final String ERROR_IN_NAME = "*Must contain more then 3 and less then 20";
	public static final String RUNTIME_ERROR = "RuntimeException";
	public static final String ROUTE_EXISTS = "*This route code already exists";
	public static final String ROUTE_NO_EXISTS = "*You can use this route code";
	public static final String NO_LINE_EXISTS = "*Incorect line name";
	public static final String NO_STATION_EXISTS = "*Incorect station name";
	public static final String DIFFERENT_STATION = "*Station should be different";

}
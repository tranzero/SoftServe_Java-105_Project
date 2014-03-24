package com.ita.edu.softserve.validation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ita.edu.softserve.dao.impl.RoutesDAOImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.entity.Stations;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.RoutesManagerImpl;
import com.ita.edu.softserve.utils.ParseUtil;

/**
 * 
 * @author Lyubomyr
 *
 */
public class TestRoutesValidation {

	private Validator routesValidator;

	Routes route;

	Errors errors;

	@Before
	public void setUp() {
		routesValidator = new RoutesValidator();
		route = new Routes();
	}

	@Test
	public void needsRouteCode() {
		errors = new BeanPropertyBindingResult(route, "route");
		routesValidator.validate(route, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("routeCode"));
	}
	
	@Test
	public void needsLine() {
		errors = new BeanPropertyBindingResult(route, "route");
		routesValidator.validate(route, errors);

		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("lineId"));
	}
	
	@Test
	public void needsstationStart() {
		errors = new BeanPropertyBindingResult(route, "route");
		routesValidator.validate(route, errors);

		assertTrue(errors.hasErrors());
		//assertNotNull(errors.getFieldError("stationStartId"));
	}
}
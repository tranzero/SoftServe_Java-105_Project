//package com.ita.edu.softserve.validation;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.mock;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import com.ita.edu.softserve.entity.Transports;
//
//public class TestTransportsValidation {
//	
////	@Mock
//	private Validator transportsValidator = new TransportsValidator();
//
//	@Before 
//	public void setUp() {
//		transportsValidator = new TransportsValidator();
////		transportsValidator = mock(TransportsValidator.class);
//
//    }
//
//	@Test
//	public void needsTransportCode() {
//		Transports transport = new Transports(); // no transportCode set
////		transport.setTransportCode("T0000000");
//		Errors errors = new BeanPropertyBindingResult(transport, "transport");
//		transportsValidator.validate(transport, errors); // 'validator' under test
//
//		assertTrue(errors.hasErrors());
//		assertNotNull(errors.getFieldError("transportCode"));
//
//	}
//	
//	@Test
//	public void needsSeatclass1() {
//		Transports transport = new Transports(); // no transportCode set
//		transport.setSeatclass1(100);
//		Errors errors = new BeanPropertyBindingResult(transport, "transport");
//		transportsValidator.validate(transport, errors); // 'validator' under test
//
//		assertTrue(errors.hasErrors());
//		assertNotNull(errors.getFieldError("seatclass1"));
//
//	}
//}

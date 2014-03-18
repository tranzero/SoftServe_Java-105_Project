package com.ita.edu.softserve.validation;

//import static org.mockito.Mockito.mock;
import static com.ita.edu.softserve.utils.ParseUtil.parseStringToTime;
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

import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Transports;
import com.ita.edu.softserve.manager.impl.TransportsManagerImpl;
import com.ita.edu.softserve.utils.ParseUtil;

public class TestTransportsValidation {

private Validator transportsValidator;

Transports transport;

Errors errors;

@Before
public void setUp() {
transportsValidator = new TransportsValidator();
transport = new Transports();
}

/* Black box tests */
@Test
public void needsTransportCode() {
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("transportCode"));

}

@Test
public void blankTransportCode() {
transport.setTransportCode("");
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("transportCode"));

}

@Test
public void needsStartTime() {
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("startTime"));

}

/* @Test
public void needsREStartTime() {
transport.setStartTime(ParseUtil.parseStringToTime("16:15:%0"));
// String time = ParseUtil.parseTimeToString(transport.getStartTime());

errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("startTime"));

}*/

@Test
public void needsRoutes() {
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("routes"));

}

@Test
public void needsSeatclass1() {
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("seatclass1"));

}

@Test
public void needsSeatclass2() {
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("seatclass2"));

}

@Test
public void needsSeatclass3() {
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("seatclass3"));

}

@Test
public void needsGeneralPrice() {
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("genPrice"));
}

/* Dark box tests */
@Test
public void wrongTransportCode() {
transport.setTransportCode("T0000000@1");
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("transportCode"));

}

@Test
public void negativeSeatclass1() {
transport.setSeatclass1(-1);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("seatclass1"));

}

@Test
public void negativeSeatclass2() {
transport.setSeatclass2(-1);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("seatclass2"));

}

@Test
public void negativeSeatclass3() {
transport.setSeatclass3(-1);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("seatclass3"));

}

@Test
public void negativeGeneralPrice() {
transport.setGenPrice(-1);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("genPrice"));
}

/* White box tests */
@Test
public void hasTransportCode() {
transport.setTransportCode("T000000001");
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNull(errors.getFieldError("transportCode"));

}

@Test
public void hasStartTime() {
transport.setStartTime(parseStringToTime("10:10:00"));
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNull(errors.getFieldError("startTime"));

}

@Test
public void hasRoutes() {
transport.setRoutes(new Routes());
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNull(errors.getFieldError("routes"));

}

@Test
public void hasSeatclass1() {
transport.setSeatclass1(100);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNull(errors.getFieldError("seatclass1"));

}

@Test
public void hasSeatclass2() {
transport.setSeatclass2(100);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNull(errors.getFieldError("seatclass2"));

}

@Test
public void hasSeatclass3() {
transport.setSeatclass3(100);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNull(errors.getFieldError("seatclass3"));

}

@Test
public void hasGeneralPrice() {
transport.setGenPrice(25);
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNull(errors.getFieldError("genPrice"));
}

@Test
public void testValidateIfTransportExist() throws IllegalArgumentException,
IllegalAccessException, NoSuchFieldException, SecurityException {

TransportsManagerImpl mockTransportsManagerImpl = mock(TransportsManagerImpl.class);
Field fild = transportsValidator.getClass().getDeclaredField(
"transportsManager");

fild.setAccessible(true);
fild.set(transportsValidator, mockTransportsManagerImpl);

Transports transports = new Transports();
transports.setTransportId(1);
when(mockTransportsManagerImpl.findTransportsByCode("T000000001"))
.thenReturn(transports);

transport.setTransportId(1);
transport.setTransportCode("T000000001");
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
// assertNull(errors.getFieldError("transportId"));
assertNull(errors.getFieldError("transportCode"));
}

@Test
public void testBlackValidateIfTransportExist()
throws IllegalArgumentException, IllegalAccessException,
NoSuchFieldException, SecurityException {

TransportsManagerImpl mockTransportsManagerImpl = mock(TransportsManagerImpl.class);
Field fild = transportsValidator.getClass().getDeclaredField(
"transportsManager");

fild.setAccessible(true);
fild.set(transportsValidator, mockTransportsManagerImpl);

Transports transports = new Transports();
transports.setTransportId(2);

when(mockTransportsManagerImpl.findTransportsByCode("T000000001"))
.thenReturn(transports);

transport.setTransportId(1);
transport.setTransportCode("T000000001");
errors = new BeanPropertyBindingResult(transport, "transport");
transportsValidator.validate(transport, errors);

assertTrue(errors.hasErrors());
assertNotNull(errors.getFieldError("transportCode"));
}
}
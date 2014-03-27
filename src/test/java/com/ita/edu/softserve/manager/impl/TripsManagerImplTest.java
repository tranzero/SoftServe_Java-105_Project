package com.ita.edu.softserve.manager.impl;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.ita.edu.softserve.dao.impl.TransportsDaoImpl;
import com.ita.edu.softserve.dao.impl.TripsDAOImpl;
import com.ita.edu.softserve.manager.UserNameService;

/**
 * @author dnyckct
 * 
 */

@RunWith(MockitoJUnitRunner.class)
public class TripsManagerImplTest {
	
	@Mock
	private TripsDAOImpl tripsDaoMock;

	@Mock
	private UserNameService userNameServiceMock;

	@Mock
	private TransportsDaoImpl transportsDaoMock;
	
	
}

package com.ita.edu.softserve.service.impl;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Class under test
 * {@link com.ita.edu.softserve.service.impl.AdminServiceImpl}
 * 
 * @author nvrubl
 */

import com.ita.edu.softserve.dao.impl.UsersDAOImpl;
import com.ita.edu.softserve.service.AdminService;

/**
 * Class under test {@link com.ita.edu.softserve.service.impl.AdminServiceImpl}
 * 
 * @author nvrubl
 */
public class AdminServiceImplTests {

	/**
	 * Mock object.
	 */
	private UsersDAOImpl usersDaoImpl;

	/**
	 * Admin Service.
	 */
	private AdminService adminServiceImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public final void setUp() {
		usersDaoImpl = mock(UsersDAOImpl.class);
		adminServiceImpl = new AdminServiceImpl(usersDaoImpl);
	}

	/**
	 * Test method for Null.<br/>
	 * Method under test
	 * {@link com.ita.edu.softserve.service.impl.AdminServiceImpl#printAllUsers()}
	 */
	@Test(expected = java.lang.NullPointerException.class)
	public final void testFindAllStationsShouldThrowNullPointerException() {
		when(usersDaoImpl.getAllEntities()).thenReturn(null);
		adminServiceImpl.printAllUsers();
	}

}

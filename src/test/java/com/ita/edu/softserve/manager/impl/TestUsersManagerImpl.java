package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.ita.edu.softserve.dao.impl.UsersDAOImpl;
import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.UserNameService;

/**
 * Class under test 
 * {@link com.ita.edu.softserve.manager.impl.UserManagerImpl
 * 
 * @author iryna
 */

@RunWith(MockitoJUnitRunner.class)
public class TestUsersManagerImpl {

	@Mock
	private UsersDAOImpl mockUsersDao;
	@Mock
	private UserNameService mockUserName;
	@InjectMocks
	private UserManager userManagerImpl = new UserManagerImpl();

	int userIdMock = 30;
	private static final String userUserName = "user1";
	private static final String userFirstName = "Anna";
	private static final String userLastName = "Krachkovska";
	private static final String userEmail = "user45@mail.com";
	private static final String userPassword = "12345";
	private static final Role userRole = Role.MANAGER;

	@Spy
	private Users user = new Users(userUserName, userFirstName, userLastName,
			userEmail, userPassword, userRole);

	@Before
	public final void setUp() {
		when(mockUsersDao.findById(userIdMock)).thenReturn(user);
	}

	/**
	 * Test for method findAllUsers().
	 */
	@Test(expected = RuntimeException.class)
	public final void testFindAllUsersException() {
		when(mockUsersDao.getAllEntities()).thenThrow(new RuntimeException());

		userManagerImpl.findAllUsers();
	}

	/**
	 * Test for method findAllUsers() verified if the list is not empty
	 */
	@Test
	public final void testFindAllUsersEmptyList() {
		List<Users> expectedList = new ArrayList<Users>();

		when(mockUsersDao.getAllEntities()).thenReturn(expectedList);

		List<Users> actualList = userManagerImpl.findAllUsers();

		verify(mockUsersDao, times(1)).getAllEntities();
		assertEquals(expectedList, actualList);
	}

	// --------------------------------------
	/**
	 * Test for method removeUser
	 */
	@Test
	public final void testRemoveUser1() {
		int testID = 5;

		when(mockUsersDao.findById(testID)).thenReturn(user);

		doNothing().when(mockUsersDao).remove(user);

		userManagerImpl.removeUser(testID);

		verify(mockUsersDao, times(1)).findById(5);
		verify(mockUsersDao, times(1)).remove(user);
	}

	/**
	 * Test for method removeUser
	 */
	@Test
	public final void testRemoveUser2() {

		int testID = 5;
		RuntimeException ex = new RuntimeException();

		when(mockUsersDao.findById(testID)).thenThrow(ex);

		RuntimeException actualException = null;
		try {
			userManagerImpl.removeUser(testID);
		} catch (RuntimeException e) {
			actualException = e;
		}
		assertNotNull(actualException);
	}

	/**
	 * Test for method removeUser
	 */
	@Test(expected = RuntimeException.class)
	public final void testRemoveUser3() {

		int testID = 5;
		RuntimeException ex = new RuntimeException();

		when(mockUsersDao.findById(testID)).thenThrow(ex);

		userManagerImpl.removeUser(testID);
	}

	/**
	 * Test for method updateTheUserData
	 */
	@Test
	public final void testUpdateUserToDB() {

		RuntimeException ex = new RuntimeException();
		doThrow(ex).when(mockUsersDao).updateUserData(user);

		RuntimeException actualException = null;
		try {

			userManagerImpl.updateTheUserData(user);

		} catch (RuntimeException e) {
			actualException = e;
		}
		assertNotNull(actualException); // ok
	}

	// --------------------------------------
	/**
	 * Test for method createUser
	 */
	@Test
	public final void testCreateNewUser() {
		boolean isCreatedUser = false;
		when(mockUsersDao.findByUsername(userUserName)).thenReturn(null);

		isCreatedUser = userManagerImpl.createUser(userUserName, userFirstName,
				userLastName, userEmail, userPassword, userRole);

		verify(mockUsersDao, times(1)).save(any(Users.class));

		assertTrue(isCreatedUser);
	}

	/**
	 * Test for method createUser when user exists
	 */
	@Test
	public final void testNotCreateUserWhenUserExists() {
		boolean isCreatedUser = false;

		when(mockUsersDao.findByUsername(userUserName)).thenReturn(user);

		isCreatedUser = userManagerImpl.createUser(userUserName, userFirstName,
				userLastName, userEmail, userPassword, userRole);

		verify(mockUsersDao, times(0)).save(any(Users.class));

		assertFalse(isCreatedUser);
	}

}

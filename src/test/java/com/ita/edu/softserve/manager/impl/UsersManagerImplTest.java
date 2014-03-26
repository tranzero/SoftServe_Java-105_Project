package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.ita.edu.softserve.exception.UsersManagerExeption;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.UserNameService;

/**
 * Class under test 
 * {@link com.ita.edu.softserve.manager.impl.UserManagerImpl
 * @author iryna
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class UsersManagerImplTest {

	@Mock
	private UsersDAOImpl userDao;
	@Mock
	private UserNameService userName;
	@InjectMocks
	private UserManager userManagerImpl = new UserManagerImpl();

	int userIdMock = 30;
	private static final String userUsername = "user1";
	private static final String userFirstName = "Anna";
	private static final String userLastName = "Gud";
	private static final String userEmail = "qi@mail.com";
	private static final String userPassword = "12345";
	private static final Role userRole = Role.MANAGER;
	@Spy
	private Users user = new Users(userUsername, userFirstName, userLastName,
			userEmail, userPassword, userRole);

	/*
	 * private Users user = new Users("user1", "ana", "Gud", "qi@mail.com",
	 * "12345", Role.MANAGER);
	 */

	@Before
	public final void setUp() {
		when(userName.getLoggedUsername()).thenReturn("yes");
		doReturn(userIdMock).when(user).getUserId();
		when(userDao.findByUsername(userUsername)).thenReturn(user);
		when(userDao.findById(userIdMock)).thenReturn(user);

	}

	// ---------------------

	@Test
	public final void test1() {
		int testID = 5;

		when(userDao.findById(testID)).thenReturn(user);
		// doReturn(user).when(userDao).findById(testID);
		doNothing().when(userDao).remove(user);

		userManagerImpl.removeUser(testID);

		verify(userDao, times(1)).findById(5);
		verify(userDao, times(1)).remove(user);

	}

	@Test
	public final void test2() {
		int testID = 5;
		RuntimeException ex = new RuntimeException();
		
		when(userDao.findById(testID)).thenThrow(ex);
		
		UsersManagerExeption actualException = null;
		try{
		userManagerImpl.removeUser(testID);
		}catch(UsersManagerExeption e){
			actualException = e;
	
		}
		assertNotNull(actualException);
		assertEquals(ex, actualException.getCause());
	}

	@Test
	public final void testCreateUser1() {
		boolean isCreatedUser = false;
		when(userDao.findByUsername(userUsername)).thenReturn(null);
		isCreatedUser = userManagerImpl.createUser(userUsername, userFirstName,
				userLastName, userEmail, userPassword, userRole);

		assertTrue(isCreatedUser);
	}

	@Test
	public final void testCreateUser2() {
		final String userUsername2 = "login2";
		final String userFirstName2 = "Roxa";
		final String userLastName2 = "Strochik";
		final String userEmail2 = "roxa5@mail.com";
		final String userPassword2 = "12345";
		final Role userRole2 = Role.REGUSER;

		boolean isCreatedUser = false;
		when(userDao.findByUsername(userUsername2)).thenReturn(null);
		isCreatedUser = userManagerImpl.createUser(userUsername2,
				userFirstName2, userLastName2, userEmail2, userPassword2,
				userRole2);

		assertTrue(isCreatedUser);
	}

	/*
	 * @Test public final void testRemoveUser0(int userIdMock) { boolean
	 * isDeletedUser = false;
	 * 
	 * if (userIdMock != 0) {
	 * 
	 * userManagerImpl.removeUser(userIdMock);
	 * 
	 * isDeletedUser = true; } else { isDeletedUser = false; }
	 * assertTrue(isDeletedUser); }
	 */

	@Test
	public final void testRemoveUser1() {
		boolean isDeletedUser = false; // when(userDao.findById(userIdMock)).thenReturn(user);

		if (user.getUserName() != "") {
			userManagerImpl.removeUser(userIdMock);
			isDeletedUser = true;
		} else {
			isDeletedUser = false;
			/* assertTrue(isDeletedUser); */
		}
		assertTrue(isDeletedUser);
	}

	/*
	 * @Test public final void testRemoveUser() { boolean isDeletedUser = false;
	 * // when(userDao.findById(userIdMock)).thenReturn(user); try { Users user
	 * = userDao.findById(userIdMock); int id = user.getUserId(); //
	 * userDao.remove(user); userManagerImpl.removeUser(userIdMock);
	 * 
	 * isDeletedUser = true; } catch (RuntimeException e) { isDeletedUser =
	 * false; } finally { }
	 * 
	 * 
	 * if (user.getUserName()!=""){ Users user = userDao.findById(userIdMock);
	 * int id = user.getUserId(); //userDao.remove(user);
	 * userManagerImpl.removeUser(userIdMock);
	 * 
	 * isDeletedUser= true; } else{ isDeletedUser=false;
	 * 
	 * 
	 * // isDeletedUser = userManagerImpl.removeUser(2);
	 * 
	 * assertTrue(isDeletedUser);
	 * 
	 * }
	 */

	@Test
	public final void testUpdateUser() {
		boolean isUpdatedUser = false;
		if (user.getUserId() != null) {
			doCallRealMethod().when(user).setUserName(userUsername);
			doCallRealMethod().when(user).setFirstName(userFirstName);
			doCallRealMethod().when(user).setLastName(userLastName);
			doCallRealMethod().when(user).setEmail(userEmail);
			doCallRealMethod().when(user).setPassword(userPassword);
			doCallRealMethod().when(user).setRole(userRole);

			userManagerImpl.updateTheUserData(user);
			isUpdatedUser = true;
		} else {
		}

		assertTrue(isUpdatedUser);
	}
}

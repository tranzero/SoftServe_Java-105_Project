package com.ita.edu.softserve.manager;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.exception.UsersManagerExeption;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.UserCriteriaContainer;

/**
 * Interface UserManager
 * 
 * @author iryna
 * 
 */
@Service
public interface UserManager extends BaseManager {

	/**
	 * Create new user
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 * @param role
	 * @return
	 */
	public boolean createUser(String username, String firstname,
			String lastname, String email, String password, Role role);

	/**
	 * Find All users
	 * 
	 * @return List of Users
	 */
	public List<Users> findAllUsers();

	/**
	 * Find a user by id
	 * 
	 * @param id
	 * @return user
	 */
	public Users findUser(Integer id);

	/**
	 * Update The User Data (for userEdit.jsp)
	 * 
	 * @param user
	 */
	void updateTheUserData(Users user);

	/**
	 * Update user (for profile.jsp)
	 */
	public void updateUser2(Integer userId, String firstName, String lastName,
			String email, String passwd);

	/**
	 * Delete user of DB
	 * 
	 * @param id
	 */
	public void removeUser(Integer id);

	/**
	 * Find user by username
	 * 
	 * @param id
	 * @return user
	 */
	public Users findByUsername(String username);

	/**
	 * For pagging1
	 */
	long getUsersListCountWithCriteria(String searchString,
			List<Role> roleArray, Date minDate, Date maxDate);

	List<Users> getUsersForLimitWithCriteria(int firstElement, long count,
			String searchString, List<Role> roleArray, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection);

	void validateUserListCriteria(UserCriteriaContainer userCriteriaContainer,
			Locale locale);

	long getUsersListCountUsingContainer(
			UserCriteriaContainer userCriteriaContainer);

	List<Users> getUsersForPageWithCriteria(int pageNumber, long count,
			String searchString, List<Role> roleArray, Date minDate,
			Date maxDate, String orderByParam, String orderByDirection);

	List<Users> getUsersForLimitUsingContainers(
			UserCriteriaContainer userCriteriaContainer,
			PageInfoContainer container);

	/**
	 * For pagging2- get userlist Count
	 * 
	 * @return
	 * @throws UsersManagerExeption
	 */
	public long getUsersListCount() throws UsersManagerExeption;

	/**
	 * For pagging2- get all users
	 * 
	 * @param from
	 * @param count
	 * @return
	 * @throws UsersManagerExeption
	 */
	public List<Users> getUsersForPage(int from, int count)
			throws UsersManagerExeption;

	/**
	 * Update user -(for userEditData1.jsp)-
	 */
	/*public void updateUser(Integer userId, String firstName, String lastName,
			String email, String passwd, Role role);*/

}

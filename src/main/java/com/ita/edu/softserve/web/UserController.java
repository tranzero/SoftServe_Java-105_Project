package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;

/**
 * Controller - UserController
 * 
 * @author iryna
 * 
 */
@Controller
public class UserController {

	@Autowired
	private UserManager usersmanage;

	/**
	 * Shows userlist
	 * 
	 * @param modelMap
	 * @return userlist
	 */
	@RequestMapping(value = "userlist", method = RequestMethod.GET)
	public String getAllUser(Map<String, Object> modelMap) {
		modelMap.put("userList", usersmanage.findAllUsers());
		return "userlist";
	}

	/**
	 * Update user to DB - RequestMethod.GET
	 * 
	 * @param usId
	 * @param modelMap
	 * @return userEdit
	 */
	@RequestMapping(value = "/userEdit/{user}", method = RequestMethod.GET)
	public String editUser(@PathVariable("user") Integer usId,
			Map<String, Object> modelMap) {
		Users user = usersmanage.findUser(usId);
		modelMap.put("user", user);
		return "userEdit";
	}

	/**
	 * Update user to DB - RequestMethod.POST
	 * 
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param parole
	 * @param role
	 * @return userEdit
	 */
	@RequestMapping(value = "/userEdit/{userToEdit}", method = RequestMethod.POST)
	public String updateUserToDB(@PathVariable("userToEdit") Integer userId,
			@ModelAttribute("userFirstName") String firstName,
			@ModelAttribute("lastName") String lastName,
			@ModelAttribute("email") String email,
			@ModelAttribute("password") String password,
			@ModelAttribute("role") Role role

	) {
		usersmanage
				.updateUser(userId, firstName, lastName, email, password, role);
		return "redirect:/userlist";
	}

	/**
	 * updateUserToDB2 - RequestMethod.GET
	 */
	@RequestMapping(value = "/userEdit2/{user}", method = RequestMethod.GET)
	public String editUser2(@PathVariable("user") Integer usId,
			Map<String, Object> modelMap) {
		Users user = usersmanage.findUser(usId);
		modelMap.put("user", user);
		return "userEdit2";
	}

	/**
	 * updateUserToDB2 - RequestMethod.POST
	 */
	@RequestMapping(value = "/userEdit2/{userToEdit}", method = RequestMethod.POST)
	public String updateUserToDB2(@PathVariable("userToEdit") Integer userId,
			@ModelAttribute("userFirstName") String firstName,
			@ModelAttribute("lastName") String lastName,
			@ModelAttribute("eMail") String eMail,
			@ModelAttribute("password") String password

	) {
		usersmanage.updateUser2(userId, firstName, lastName, eMail, password);
		return "redirect:/userlist";
	}

	/**
	 * Delete user
	 * 
	 * @param userId
	 * @return userlist
	 */
	@RequestMapping("/userdel/{user}")
	public String deleteUser(@PathVariable("user") Integer userId) {
		usersmanage.removeUser(userId);
		return "redirect:/userlist";
	}

	/**
	 * Edit user profile
	 * 
	 * @param usId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/editProfile/{userId}", method = RequestMethod.GET)
	public String editProfile(@PathVariable("userId") Integer usId,
			Map<String, Object> modelMap) {
		Users user = usersmanage.findUser(usId);
		modelMap.put("user", user);
		return "editProfile";
	}

	@RequestMapping(value = "/editProfile/{userId}", method = RequestMethod.POST)
	public String updateProfile(@PathVariable("userId") Integer userId,
			@ModelAttribute("firstName") String firstName,
			@ModelAttribute("lastName") String lastName,
			@ModelAttribute("email") String email,
			@ModelAttribute("password") String password

	) {
		usersmanage.updateUser2(userId, firstName, lastName, email, password);
		return "redirect:/mainpage";
	}

}

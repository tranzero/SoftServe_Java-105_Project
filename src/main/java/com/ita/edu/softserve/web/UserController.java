package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.UserNameService;

/**
 * Controller for Users
 * 
 * @author iryna
 * 
 */
@Controller
public class UserController {

	@Autowired
	private UserManager usersmanage;

	@Autowired
	private  ProfileEditValidator profileEditValidator;
	
	@Autowired
	Validator userEditValidator;

	@Autowired
	private UserNameService userService;

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
	@RequestMapping(value = "/userEdit/userEdit.htm", method = RequestMethod.POST)
	public String updateUserToDB(@ModelAttribute("user") Users user,
			BindingResult bindingResult, ModelMap modelMap) {

		userEditValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			modelMap.put("user", user);
			return "userEdit";
		}
		usersmanage.updateTheUserData(user);
		return "redirect:/userlist2";
	}

	/**
	 * for Validation - userEdit
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(userEditValidator);
		binder.registerCustomEditor(Role.class, new UserRoleEditor());

	}

	/**
	 * Delete user
	 * 
	 * @param userId
	 * @return userlist
	 */
	@RequestMapping("/userDelete/{user}")
	public String deleteUser(@PathVariable("user") Integer userId) {
		usersmanage.removeUser(userId);
		return "redirect:/userlist";
	}

	/**
	 * Edit Profile
	 * 
	 * @param usId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/profileEdit", method = RequestMethod.GET)
	public String editProfile(Map<String, Object> modelMap) {
		Users user = usersmanage.findUser(userService.getLoggedUserId());
		modelMap.put("user", user);
		return "profileEdit";
	}

	@RequestMapping(value = "/profileEdit", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute("user") Users user, BindingResult result, ModelMap model){
		profileEditValidator.validate(user, result);
		if (result.hasErrors()) {
			return "profileEdit";
		}
		usersmanage.updateUser2(user.getUserId(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPassword());
		return "redirect:/mainpage";
	}

}

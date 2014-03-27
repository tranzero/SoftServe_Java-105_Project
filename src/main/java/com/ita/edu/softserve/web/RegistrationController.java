package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.components.CustomPasswordEncoder;
import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.validation.RegistrationValidator;

/**
 * 
 * Controller for Registration of Users
 * 
 */
@Controller
public class RegistrationController {

	@Autowired
	private UserManager usersManager;

	@Autowired
	private RegistrationValidator regValid;

	@Autowired
	private CustomPasswordEncoder customPasswordEncoder;
	
	/**
	 * Registration
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistration(ModelMap model) {
		Users user = new Users();
		model.addAttribute("user", user);
		return "registration";
	}

	/**
	 * Register User
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") Users user,
			BindingResult result, ModelMap model) {
		regValid.validate(user, result);
		if (result.hasErrors()) {
			return "registration";
		}
		customPasswordEncoder.encodePassword(user);
		if (usersManager.createUser(user.getUserName(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPassword(),
				Role.REGUSER) == false) {
			return "redirect:/regError";
		}
		return "redirect:/successfulReg";
	}

	/**
	 * Show msg of registration error
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regError", method = RequestMethod.GET)
	public String regError(Map<String, Object> model) {
		return "regError";
	}

	/**
	 * Show msg of successfull registration
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/successfulReg", method = RequestMethod.GET)
	public String regSuc(Map<String, Object> model) {
		return "successfulReg";
	}

}

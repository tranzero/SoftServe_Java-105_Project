package com.ita.edu.softserve.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.validation.RegistrationValidator;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserManager usersManager;
	
	@Autowired
	private RegistrationValidator regValid;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistration(ModelMap model) {
		Users user = new Users();
		model.addAttribute("user", user);
		return "registration";
	}

	@RequestMapping(value = "/regUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") Users user,
			BindingResult result, ModelMap model) {
		regValid.validate(user, result);
		if (result.hasErrors()) {
			return "registration";
		}
		if (usersManager.createUser(user.getUserName(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPassword(),
				Role.REGUSER) == false) {
			return "redirect:/regError";
		}
		return "redirect:/successfulReg";
	}

	@RequestMapping(value = "/regError", method = RequestMethod.GET)
	public String regError(Map<String, Object> model) {
		return "regError";
	}
	@RequestMapping(value = "/successfulReg", method = RequestMethod.GET)
	public String regSuc(Map<String, Object> model) {
		return "successfulReg";
	}

}

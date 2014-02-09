package com.ita.edu.softserve.web;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.manager.UserManager;

@Controller
public class RegistrationController {
	@Autowired
	private UserManager usersManager;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String register() {
		return "registration";
	}
	
	@RequestMapping(value = "/regUser", method = RequestMethod.POST)
	public String registerUser(@RequestParam("userName") String userName,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Map<String, Object> model) {
		try {
			usersManager.createUser(userName, firstName, lastName, email, password, Role.REGUSER);
		}
		catch (IllegalArgumentException iae) {
			return "redirect:/regError";
		}
		return "redirect:/mainpage";

	}
	@RequestMapping(value="/regError", method = RequestMethod.GET)
	public String regError( Map<String, Object> model) {
		return "regError";
	}
	
}

	
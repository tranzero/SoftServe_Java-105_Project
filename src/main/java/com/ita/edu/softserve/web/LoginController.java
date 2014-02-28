package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.userdetails.User;

@Controller
public class LoginController {	
	

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Map<String, Object> modelMap) {			
		return "login"; 
	}
	
	@RequestMapping(value="/accessDenied")
	public String accesserror(Map<String, Object> modelMap) {		
		return "accessDenied";
	}
	
	@RequestMapping(value="/loginsuccess")
	public String loginsuccess(Map<String, Object> modelMap) {      
		return "redirect:mainpage";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Map<String, Object> modelMap) {		
		return "redirect:/j_spring_security_logout";
 	}	
}

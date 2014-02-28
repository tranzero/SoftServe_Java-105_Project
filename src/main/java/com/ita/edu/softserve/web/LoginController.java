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
		//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String name = user.getUsername();
		//modelMap.put("username", name);		
		return "login"; 
	}
	
	@RequestMapping(value="/loginfailed")
	public String loginerror(Map<String, Object> modelMap) {
		modelMap.put("error", "true");		
		return "mainpage";
	}
	
	@RequestMapping(value="/loginsuccess")
	public String loginsuccess(Map<String, Object> modelMap) {
        System.out.println("loginsuccess");
		return "redirect:mainpage";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Map<String, Object> modelMap) {		
		return "mainpage";
 	}	
}

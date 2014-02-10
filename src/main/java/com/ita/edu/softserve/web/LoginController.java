package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;

import org.springframework.security.core.userdetails.User;

@Controller
public class LoginController {
	
	@Autowired
	private UserManager usersmanager;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Map<String, Object> modelMap) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
		modelMap.put("username", name);		
		System.out.println("SIGNED IN");
		return "orders"; 
	}
	
	@RequestMapping(value="/loginfailed")
	public String loginerror(Map<String, Object> modelMap) {
		modelMap.put("error", "true");		
		return "orders";
 	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(Map<String, Object> modelMap) {
		return "orders";
 	}	
}

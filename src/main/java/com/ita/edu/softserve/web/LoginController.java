package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;

@Controller
public class LoginController {
	
	@Autowired
	private UserManager usersmanager;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String signInUser(@RequestParam("username") String username,
			@RequestParam("password") String password, Map<String, Object> modelMap){
	    Users user = usersmanager.findByUsername(username);	    
	    if (user == null || user.getPasswd() != password) {
	    	return "errors";
	    }
	    modelMap.put("username", username);
	    return "index"; 
	}
}

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
 * UserController
 * 
 * @author iryna
 * 
 */
@Controller
public class UserController {

	@Autowired
	private UserManager usersmanage;

	@RequestMapping(value = "userlist", method = RequestMethod.GET)
	public String getAllUser(Map<String, Object> modelMap) {
		modelMap.put("userList", usersmanage.findAllUsers());
		return "userlist";
	}

	
	@RequestMapping(value ="/userEdit/{user}", method =RequestMethod.GET)
	public String editUser(@PathVariable("user") Integer usId,Map<String,Object> modelMap){
		Users user = usersmanage.findUser(usId);
		modelMap.put("user", user);
		return "userEdit";
	}
	
	
	
	//update user
	@RequestMapping(value="/userEdit/{userToEdit}",method =RequestMethod.POST)
	public String updateUserToDB(@PathVariable("userToEdit")Integer userId,
			@ModelAttribute("firstName")String firstName,
			@ModelAttribute("lastName")String lastName,
			@ModelAttribute("eMail")String eMail,
			@ModelAttribute("passwd")String passwd,
			@ModelAttribute("role")Role role			
						
			){
		
		usersmanage.updateUser(userId,  firstName, lastName, eMail, passwd,  role);
		return "redirect:/userEdit";
	}

	

	// delete user
	@RequestMapping("/userdel/{userr}")
	public String deleteUser(@PathVariable("userr") Integer userId) {
		usersmanage.removeUser(userId);
		return "redirect:/userlist";
	}

}

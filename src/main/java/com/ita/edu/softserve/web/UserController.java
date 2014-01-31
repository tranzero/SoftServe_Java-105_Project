package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	// no updateUsers
	@RequestMapping(value = "userEdit", method = RequestMethod.GET)
	public String updateUser(Map<String, Object> modelMap) {
		// @requestParam
		modelMap.put("userList", usersmanage.updateUsers());
		return "userEdit";
	}

	@RequestMapping(value = "userEdit1", method = RequestMethod.GET)
	public String editUser(Map<String, Object> modelMap) {

		modelMap.put("userList", usersmanage.findAllUsers());
		return "userEdit";
	}

	// delete user
	@RequestMapping("/userdel/{userr}")
	public String deleteUser(@PathVariable("userr") Integer userId) {
		usersmanage.removeUser(userId);
		return "redirect:/userlist";
	}

}

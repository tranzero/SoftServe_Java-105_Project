package com.ita.edu.softserve.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ita.edu.softserve.dao.impl.TripsDAOImpl;
import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Routes;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.TripsCriteriaContainer;
import com.ita.edu.softserve.validationcontainers.UserCriteriaContainer;

/**
 * Controller - UserController
 * 
 * @author iryna
 * 
 */
@Controller
public class UserController {

	@Autowired
	private UserManager usersmanage;

	@Autowired
	Validator userEditValidator;

	@Autowired
	UserCriteriaContainer userCriteriaContainer;

	private void putFillElementsOptions(
			UserCriteriaContainer usersCriteriaContainer,
			Map<String, Object> modelMap) {
		modelMap.put("isSearchString", ValidatorUtil
				.isEmptyString(userCriteriaContainer.getSearchString()));
		modelMap.put("isMinDate", ValidatorUtil
				.isEmptyString(userCriteriaContainer.getMaxDateString()));
		modelMap.put("isMaxDate", ValidatorUtil
				.isEmptyString(userCriteriaContainer.getMinDateString()));
	}

	private void deployUsersParameters(Integer pageNumber,
			Integer resultsPerPage, String searchString, String minDateString,
			String maxDateString, Boolean isRegUser, Boolean isManager,
			Boolean isAdmin, String orderByParam, String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		userCriteriaContainer.setValuableInfo(searchString, minDateString,
				maxDateString, isRegUser, isManager, isAdmin, orderByParam,
				orderByDirection);
		putFillElementsOptions(userCriteriaContainer, modelMap);
		usersmanage.validateUserListCriteria(userCriteriaContainer, locale);

	}

	/**
	 * Shows userlist
	 * 
	 * @param modelMap
	 * @return userlist
	 */
	@RequestMapping(value = "userlist", method = RequestMethod.GET)
	public String getAllUser(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Users.SEARCH_STRING_NAME, required = false) String searchString,
			@RequestParam(value = Users.MIN_DATE_NAME, required = false) String minDateString,
			@RequestParam(value = Users.MAX_DATE_NAME, required = false) String maxDateString,
			@RequestParam(value = "isRegUser", required = false) Boolean isRegUser,
			@RequestParam(value = "isManager", required = false) Boolean isManager,
			@RequestParam(value = "isAdmin", required = false) Boolean isAdmin,
			@RequestParam(value = "orderByParam", required = false) String orderByParam,
			@RequestParam(value = "orderByDirection", required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		deployUsersParameters(pageNumber, resultsPerPage, searchString,
				minDateString, maxDateString, isRegUser, isManager, isAdmin,
				orderByParam, orderByDirection, modelMap, locale);
		modelMap.put("userList", usersmanage.findAllUsers());
		return "userlist";
	}

	@RequestMapping(value = "userListPage", method = RequestMethod.GET)
	public String getAllUserPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Users.SEARCH_STRING_NAME, required = false) String searchString,
			@RequestParam(value = Users.MIN_DATE_NAME, required = false) String minDateString,
			@RequestParam(value = Users.MAX_DATE_NAME, required = false) String maxDateString,
			@RequestParam(value = "isRegUser", required = false) Boolean isRegUser,
			@RequestParam(value = "isManager", required = false) Boolean isManager,
			@RequestParam(value = "isAdmin", required = false) Boolean isAdmin,
			@RequestParam(value = "orderByParam", required = false) String orderByParam,
			@RequestParam(value = "orderByDirection", required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		deployUsersParameters(pageNumber, resultsPerPage, searchString,
				minDateString, maxDateString, isRegUser, isManager, isAdmin,
				orderByParam, orderByDirection, modelMap, locale);
		modelMap.put("userList", usersmanage.findAllUsers());
		return "userListPage";
	}

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
	 * // * Update user to DB - RequestMethod.POST
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
		user.setRole(Role.REGUSER);
		userEditValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			modelMap.put("user", user);
			return "userEdit";
		}
		usersmanage.saveOrUpdateUser(user);
		return "redirect:/userlist";
	}

	/**
	 * updateUserToDB2 - RequestMethod.GET
	 */
	@RequestMapping(value = "/userEdit2/{user}", method = RequestMethod.GET)
	public String editUser2(@PathVariable("user") Integer usId,
			Map<String, Object> modelMap) {
		Users user = usersmanage.findUser(usId);
		modelMap.put("user", user);
		return "userEdit2";
	}

	/**
	 * updateUserToDB2 - RequestMethod.POST
	 */
	@RequestMapping(value = "/userEdit2/{userToEdit}", method = RequestMethod.POST)
	public String updateUserToDB2(@PathVariable("userToEdit") Integer userId,
			@ModelAttribute("userFirstName") String firstName,
			@ModelAttribute("lastName") String lastName,
			@ModelAttribute("eMail") String eMail,
			@ModelAttribute("password") String password

	) {
		usersmanage.updateUser2(userId, firstName, lastName, eMail, password);
		return "redirect:/userlist";
	}

	/**
	 * Delete user
	 * 
	 * @param userId
	 * @return userlist
	 */
	@RequestMapping("/userdel/{user}")
	public String deleteUser(@PathVariable("user") Integer userId) {
		usersmanage.removeUser(userId);
		return "redirect:/userlist";
	}

	/**
	 * Edit user profile
	 * 
	 * @param usId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/editProfile/{userId}", method = RequestMethod.GET)
	public String editProfile(@PathVariable("userId") Integer usId,
			Map<String, Object> modelMap) {
		Users user = usersmanage.findUser(usId);
		modelMap.put("user", user);
		return "editProfile";
	}

	@RequestMapping(value = "/editProfile/{userId}", method = RequestMethod.POST)
	public String updateProfile(@PathVariable("userId") Integer userId,
			@ModelAttribute("firstName") String firstName,
			@ModelAttribute("lastName") String lastName,
			@ModelAttribute("email") String email,
			@ModelAttribute("password") String password

	) {
		usersmanage.updateUser2(userId, firstName, lastName, email, password);
		return "redirect:/mainpage";
	}

	// -------------------------------------------------------
	// for Validator

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(userEditValidator);
		binder.registerCustomEditor(Role.class, new RoleEditor());

	}

}

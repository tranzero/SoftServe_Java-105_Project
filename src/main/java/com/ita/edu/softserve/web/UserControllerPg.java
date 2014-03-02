package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Role;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.exception.UsersManagerExeption;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ExceptionUtil;

/**
 * Controller - UserController Page
 * 
 * @author iryna
 * 
 */
@Controller
public class UserControllerPg {

	String pageNumberKey = "pageNumber";
	String resultsPerPageKey = "resultsPerPage";
	String sizeOfPagingKey = "sizeOfPaging";
	String maxPageCountKey = "maxPageCount";
	String maxResultCountKey = "maxResultCount";

	String usersListKey = "userList";

	String errorListKey = "errorList";
	String errorMsgKey = "errorMsg";

	String mainPageOutGet = "userlist2";
	String resultOut = "result";
	String mainPageOutPost = "userlist2page";

	@Autowired
	private UserManager usersmanage;

	@Autowired
	public PaginationManager pageMan = PaginationManager.getInstance();

	@Autowired
	Validator userEditValidator;

	// ----userEdit Validator---------------------------------
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
	 * Update user to DB - RequestMethod.POST
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
		// user.setRole(Role.REGUSER);
		userEditValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {
			modelMap.put("user", user);
			return "userEdit";
		}
		usersmanage.saveOrUpdateUser(user);
		return "redirect:/userlist";
	}

	/**
	 * Delete user
	 * 
	 * @param userId
	 * @return userlist
	 */
	@RequestMapping("/userdelpg/{user}")
	public String deleteUser(@PathVariable("user") Integer userId) {
		usersmanage.removeUser(userId);
		return "redirect:/userlist2";
	}

	// ---------------

	// -------------------------------------------------------
	// for Validator- userEdit

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(userEditValidator);
		binder.registerCustomEditor(Role.class, new RoleEditor());

	}

	// ----for paging userlist 2---------------------------------
	/**
	 * For paging userList 2
	 * 
	 * @param modelMap
	 * @return userlist2
	 */
	@RequestMapping(value = "/userlist2", method = RequestMethod.GET)
	public String mainPage(Map<String, Object> modelMap) {

		modelMap.put(pageNumberKey, pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());
		try {
			int maxPageCount = pageMan.getMaxPageCount(
					pageMan.getDefaultResultPerPage(),
					usersmanage.getUsersListCount());

			modelMap.put(maxPageCountKey, maxPageCount);
			return mainPageOutGet;

		} catch (UsersManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}

	}

	/**
	 * For paging userList 2
	 * 
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/userlist2page", method = RequestMethod.POST)
	public String mainPagePost(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("resultsPerPage") int resultsPerPage,
			Map<String, Object> modelMap) {

		try {
			long resultCount = usersmanage.getUsersListCount();

			modelMap.put(maxResultCountKey, resultCount);
			int maxPageCount = pageMan.getMaxPageCount(resultsPerPage,
					resultCount);
			modelMap.put(maxPageCountKey, maxPageCount);
			int currentPagingPosition = pageMan.getCurrentPagingPosition(
					pageNumber, resultsPerPage);

			modelMap.put(pageNumberKey, pageNumber);
			modelMap.put(resultsPerPageKey, resultsPerPage);

			modelMap.put(usersListKey, usersmanage.getUsersForPage(
					currentPagingPosition, resultsPerPage)

			);

			return mainPageOutPost;

		} catch (UsersManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}

	}

}

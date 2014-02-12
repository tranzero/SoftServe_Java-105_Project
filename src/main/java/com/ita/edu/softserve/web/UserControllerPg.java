package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	// String managePageNewsGet = "managenews";
	// String managePageNewsOutPost = "managenewspost";
	// String addNewsOutGet = "addnews";
	// String addNewsOutPost = "redirect:/managenews";
	// String delNewsGet = "redirect:/managenews";
	// String editNewsGet = "editnews";
	// String editNewsPost = "redirect:/managenews";
	// String detailsNewsGet = "detailsnews";

	@Autowired
	private UserManager usersmanage;

	@Autowired
	public PaginationManager pageMan = PaginationManager.getInstance();

	// for paging
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

	// -----
	/**
	 * Shows userlist2
	 * 
	 * @param modelMap
	 * @return userlist
	 *//*
	@RequestMapping(value = "userlist2", method = RequestMethod.GET)
	public String getAllUser(Map<String, Object> modelMap) {
		modelMap.put("userList2", usersmanage.findAllUsers());
		return "userlist2";
	}*/

	

	

	

}
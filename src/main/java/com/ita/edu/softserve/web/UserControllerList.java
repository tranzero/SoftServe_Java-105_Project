package com.ita.edu.softserve.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.components.Encoder;
import com.ita.edu.softserve.entity.Users;
import com.ita.edu.softserve.manager.UserManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ValidatorUtil;
import com.ita.edu.softserve.validationcontainers.PageInfoContainer;
import com.ita.edu.softserve.validationcontainers.UserCriteriaContainer;

/**
 * UserController for List
 * 
 * @author iryna
 * 
 */
@Controller
public class UserControllerList {

	private static final String ORDER_BY_DIRECTION = "orderByDirection";

	private static final String ORDER_BY_PARAM = "orderByParam";

	private static final String IS_ADMIN = "isAdmin";

	private static final String IS_MANAGER = "isManager";

	private static final String IS_REG_USER = "isRegUser";

	private PaginationManager paginationManager = PaginationManager
			.getInstance();

	@Autowired
	PageInfoContainer container;

	@Autowired
	UserCriteriaContainer userCriteriaContainer;

	@Autowired
	private UserManager usersmanage;

	@Autowired
	Encoder encoder;

	/**
	 * Put Fill Elements Options
	 */
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

	/**
	 * Deploy Users Parameters
	 * 
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param searchString
	 * @param minDateString
	 * @param maxDateString
	 * @param isRegUser
	 * @param isManager
	 * @param isAdmin
	 * @param orderByParam
	 * @param orderByDirection
	 * @param modelMap
	 * @param locale
	 */
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

		long count = usersmanage
				.getUsersListCountUsingContainer(userCriteriaContainer);

		container.setPageNumber(pageNumber);
		container.setResultsPerPage(resultsPerPage);
		container.setCount(count);
		paginationManager.validatePaging(container);
		PagingController.deployPaging(modelMap, container, paginationManager);
		modelMap.put("container", userCriteriaContainer);
		modelMap.put("encoder", encoder);
		modelMap.put("userList", usersmanage.getUsersForLimitUsingContainers(
				userCriteriaContainer, container));
		modelMap.put("language", locale.getLanguage());
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
			@RequestParam(value = IS_REG_USER, required = false) Boolean isRegUser,
			@RequestParam(value = IS_MANAGER, required = false) Boolean isManager,
			@RequestParam(value = IS_ADMIN, required = false) Boolean isAdmin,
			@RequestParam(value = ORDER_BY_PARAM, required = false) String orderByParam,
			@RequestParam(value = ORDER_BY_DIRECTION, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		deployUsersParameters(pageNumber, resultsPerPage, searchString,
				minDateString, maxDateString, isRegUser, isManager, isAdmin,
				orderByParam, orderByDirection, modelMap, locale);

		return "userlist";
	}

	/**
	 * Get all users
	 * 
	 * @param pageNumber
	 * @param resultsPerPage
	 * @param searchString
	 * @param minDateString
	 * @param maxDateString
	 * @param isRegUser
	 * @param isManager
	 * @param isAdmin
	 * @param orderByParam
	 * @param orderByDirection
	 * @param modelMap
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "userListPage", method = RequestMethod.GET)
	public String getAllUserPage(
			@RequestParam(value = PaginationManager.PAGE_NUMBER_NAME, required = false) Integer pageNumber,
			@RequestParam(value = PaginationManager.RESULTS_PER_PAGE_NAME, required = false) Integer resultsPerPage,
			@RequestParam(value = Users.SEARCH_STRING_NAME, required = false) String searchString,
			@RequestParam(value = Users.MIN_DATE_NAME, required = false) String minDateString,
			@RequestParam(value = Users.MAX_DATE_NAME, required = false) String maxDateString,
			@RequestParam(value = IS_REG_USER, required = false) Boolean isRegUser,
			@RequestParam(value = IS_MANAGER, required = false) Boolean isManager,
			@RequestParam(value = IS_ADMIN, required = false) Boolean isAdmin,
			@RequestParam(value = ORDER_BY_PARAM, required = false) String orderByParam,
			@RequestParam(value = ORDER_BY_DIRECTION, required = false) String orderByDirection,
			Map<String, Object> modelMap, Locale locale) {
		deployUsersParameters(pageNumber, resultsPerPage, searchString,
				minDateString, maxDateString, isRegUser, isManager, isAdmin,
				orderByParam, orderByDirection, modelMap, locale);

		return "userListPage";
	}

}

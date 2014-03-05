package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.manager.PropertiesManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;

@Controller
public class IndexPageController {

	@Autowired
	private PaginationManager pageMan = PaginationManager.getInstance();

	@Autowired
	private PostForMainPageManager posts;

	@Autowired
	private PropertiesManager propertyManager;

	// modelMap String Keys
	private final static String PAGE_NUMBER_KEY = "pageNumber";
	private final static String SIZE_OF_PAGING_KEY = "sizeOfPaging";
	private final static String MAX_PAGE_COUNT_KEY = "maxPageCount";
	private final static String MAX_RESULT_COUNT_KEY = "maxResultCount";
	private final static String NEWS_LIST_KEY = "newsList";
	private final static String NEWS_NAME_LIST_KEY = "post";
	private final static String POST_LIST_KEY = "News";
	private final static String IMG_PATH_KEY = "mainImgPath";

	// RequesMapping input request value
	private final static String MAIN_PAGE_NEWS_IN_GET = "/mainpage";
	private final static String MAIN_PAGE_NEWS_IN_POST = "/mainpagepost";
	private final static String MANAGE_PAGE_NEWS_IN_GET = "/managenews";
	private final static String MANAGE_PAGE_NEWS_IN_POST = "/managenewspost";
	private final static String ADD_NEWS_IN_GET = "/addnews";
	private final static String ADD_NEWS_IN_POST = "/addnews";
	private final static String DEL_NEWS_IN = "delnews";
	private final static String EDIT_NEWS_IN = "editnews";
	private final static String UPDATE_NEWS = "updatenews";
	private final static String DETAILS_NEWS_IN_GET = "/detailsnews";
	private final static String RESULT_PER_PAGE_KEY = "resultsPerPage";

	// Request params Keys
	private final static String REQUEST_PARAM_PAGE_NUMBER = "pageNumber";
	private final static String REQUEST_PARAM_RESULTS_PER_PAGE = "resultsPerPage";
	private final static String MODEL_ATTRIBYTE_NEWS_TITLE = "newsTitle";
	private final static String MODEL_ATTRIBYTE_NEWS_ID = "idTitle";
	private final static String MODEL_ATTRIBYTE_NEWS_DESCRIPTION = "newsDescription";
	private final static String MODEL_ATTRIBYTE_EDIT_NEWS = "newsId";
	private final static String MODEL_ATTRIBYTE_FILE_NAME = "fileName";
	private final static String MODEL_ATTRIBYTE_DETAILS_ID = "detailsId";

	// Response output JSP name
	private String mainPageOutGet = "mainpage";
	private String mainPageOutPost = "mainpagepost";
	private String managePageNewsGet = "managenews";
	private String managePageNewsOutPost = "managenewspost";
	private String addNewsOutGet = "addnews";
	private String addNewsOutPost = "redirect:/managenews";
	private String delNewsGet = "redirect:/managenews";
	private String editNewsPostOut = "redirect:/managenews";
	private String detailsNewsGet = "detailsnews";
	private String editNewsOut = "editnews";

	@RequestMapping(value = MAIN_PAGE_NEWS_IN_GET, method = RequestMethod.GET)
	public String mainPage(Map<String, Object> modelMap) {

		modelMap.put(PAGE_NUMBER_KEY, pageMan.getDefaultPageNumber());
		modelMap.put(RESULT_PER_PAGE_KEY, pageMan.getDefaultResultPerPage());
		modelMap.put(SIZE_OF_PAGING_KEY, pageMan.getDefaultPageCount());
		int maxPageCount = pageMan.getMaxPageCount(
				pageMan.getDefaultResultPerPage(), posts.getPostListCount());
		modelMap.put(MAX_PAGE_COUNT_KEY, maxPageCount);
		
		return mainPageOutGet;

	}

	@RequestMapping(value = MAIN_PAGE_NEWS_IN_POST, method = RequestMethod.POST)
	public String mainPagePost(
			@RequestParam(REQUEST_PARAM_PAGE_NUMBER) int pageNumber,
			@RequestParam(REQUEST_PARAM_RESULTS_PER_PAGE) int resultsPerPage,
			Map<String, Object> modelMap) {

		long resultCount = posts.getPostListCount();
		modelMap.put(MAX_RESULT_COUNT_KEY, resultCount);
		int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		modelMap.put(MAX_PAGE_COUNT_KEY, maxPageCount);
		int currentPagingPosition = pageMan.getCurrentPagingPosition(
				pageNumber, resultsPerPage);
		modelMap.put(PAGE_NUMBER_KEY, pageNumber);
		modelMap.put(RESULT_PER_PAGE_KEY, resultsPerPage);
		modelMap.put(NEWS_LIST_KEY,
				posts.getPostForPage(currentPagingPosition - 1, resultsPerPage));
		modelMap.put(IMG_PATH_KEY, propertyManager.getImgPath());
		
		return mainPageOutPost;

	}

	@RequestMapping(value = MANAGE_PAGE_NEWS_IN_GET, method = RequestMethod.GET)
	public String index(Map<String, Object> modelMap) {

		modelMap.put(PAGE_NUMBER_KEY, pageMan.getDefaultPageNumber());
		modelMap.put(RESULT_PER_PAGE_KEY, pageMan.getDefaultResultPerPage());
		modelMap.put(SIZE_OF_PAGING_KEY, pageMan.getDefaultPageCount());
		int maxPageCount = pageMan.getMaxPageCount(
				pageMan.getDefaultResultPerPage(), posts.getPostListCount());
		modelMap.put(MAX_PAGE_COUNT_KEY, maxPageCount);
		
		return managePageNewsGet;

	}

	@RequestMapping(value = MANAGE_PAGE_NEWS_IN_POST, method = RequestMethod.POST)
	public String mainNewsPost(
			@RequestParam(REQUEST_PARAM_PAGE_NUMBER) int pageNumber,
			@RequestParam(REQUEST_PARAM_RESULTS_PER_PAGE) int resultsPerPage,
			Map<String, Object> modelMap) {

		long resultCount = posts.getPostListCount();
		modelMap.put(MAX_RESULT_COUNT_KEY, resultCount);
		int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		modelMap.put(MAX_PAGE_COUNT_KEY, maxPageCount);
		int currentPagingPosition = pageMan.getCurrentPagingPosition(
				pageNumber, resultsPerPage);
		modelMap.put(PAGE_NUMBER_KEY, pageNumber);
		modelMap.put(RESULT_PER_PAGE_KEY, resultsPerPage);
		modelMap.put(NEWS_LIST_KEY,
				posts.getPostForPage(currentPagingPosition - 1, resultsPerPage));
		modelMap.put(IMG_PATH_KEY, propertyManager.getImgPath());
		
		return managePageNewsOutPost;

	}

	@RequestMapping(value = ADD_NEWS_IN_GET, method = RequestMethod.GET)
	public String addNews() {
		
		return addNewsOutGet;
	}

	@RequestMapping(value = ADD_NEWS_IN_POST, method = RequestMethod.POST)
	public String addNewsToBD(
			@ModelAttribute(MODEL_ATTRIBYTE_NEWS_TITLE) String newsTitle,
			@ModelAttribute(MODEL_ATTRIBYTE_NEWS_DESCRIPTION) String newsDescription,
			@ModelAttribute(MODEL_ATTRIBYTE_FILE_NAME) String fileName,
			Map<String, Object> modelMap) {

		posts.createNews(newsTitle, newsDescription, fileName);
		
		return addNewsOutPost;
	}

	@RequestMapping(value = DEL_NEWS_IN, method = RequestMethod.POST)
	public String delNews(
			@ModelAttribute(MODEL_ATTRIBYTE_EDIT_NEWS) Integer postId) {

		posts.removeNews(postId);
		
		return delNewsGet;

	}

	@RequestMapping(value = EDIT_NEWS_IN, method = RequestMethod.POST)
	public String editNewsPost(
			@ModelAttribute(MODEL_ATTRIBYTE_EDIT_NEWS) Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		post = posts.findNews(postId);
		modelMap.put(NEWS_NAME_LIST_KEY, post);
		
		return editNewsOut;
	}

	@RequestMapping(value = UPDATE_NEWS, method = RequestMethod.POST)
	public String updateNewsToBD(
			@ModelAttribute(MODEL_ATTRIBYTE_FILE_NAME) String filename,
			@ModelAttribute(MODEL_ATTRIBYTE_NEWS_ID) Integer newsId,
			@ModelAttribute(MODEL_ATTRIBYTE_NEWS_TITLE) String newsTitle,
			@ModelAttribute(MODEL_ATTRIBYTE_NEWS_DESCRIPTION) String newsDescription,
			Map<String, Object> modelMap) {

		posts.updateNews(newsId, newsTitle, newsDescription, filename);

		return editNewsPostOut;
	}

	@RequestMapping(value = DETAILS_NEWS_IN_GET, method = RequestMethod.POST)
	public String detailsNews(
			@ModelAttribute(MODEL_ATTRIBYTE_DETAILS_ID) Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		post = posts.findNews(postId);
		modelMap.put(POST_LIST_KEY, post);
		modelMap.put(IMG_PATH_KEY, propertyManager.getImgPath());
		
		return detailsNewsGet;
	}
}
package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerExeption;
import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;
import com.ita.edu.softserve.utils.ExceptionUtil;

@Controller
public class IndexPageController {
	String pageNumberKey = "pageNumber";
	String resultsPerPageKey = "resultsPerPage";
	String sizeOfPagingKey = "sizeOfPaging";
	String maxPageCountKey = "maxPageCount";
	String maxResultCountKey = "maxResultCount";
	String newsListKey = "newsList";
	String errorListKey = "errorList";
	String errorMsgKey = "errorMsg";
	
	String mainPageOutGet = "mainpage";
	String resultOut = "result";
	String mainPageOutPost = "mainpagepost";
	String managePageNewsGet = "managenews";
	String managePageNewsOutPost = "managenewspost";
	String addNewsOutGet = "addnews";
	String addNewsOutPost = "redirect:/managenews";
	String delNewsGet = "redirect:/managenews";
	String editNewsGet = "editnews";
	String editNewsPost = "redirect:/managenews";
	String detailsNewsGet = "detailsnews";
	
	
	@Autowired
	public PaginationManager pageMan = PaginationManager.getInstance();

	@Autowired
	private PostForMainPageManager posts;

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String mainPage(Map<String, Object> modelMap) {

		modelMap.put(pageNumberKey , pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());
		try {
			int maxPageCount = pageMan
					.getMaxPageCount(pageMan.getDefaultResultPerPage(),
							posts.getPostListCount());

			modelMap.put(maxPageCountKey, maxPageCount);
			return mainPageOutGet;

		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return  resultOut;
		}

	}

	@RequestMapping(value = "/mainpagepost", method = RequestMethod.POST)
	public String mainPagePost(
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("resultsPerPage") int resultsPerPage,
			Map<String, Object> modelMap) {

		try {

			long resultCount = posts.getPostListCount();
			modelMap.put(maxResultCountKey, resultCount);
			int maxPageCount = pageMan.getMaxPageCount(resultsPerPage,
					resultCount);
			modelMap.put(maxPageCountKey, maxPageCount);
			int currentPagingPosition = pageMan.getCurrentPagingPosition(
					pageNumber, resultsPerPage);
			modelMap.put(pageNumberKey, pageNumber);
			modelMap.put(resultsPerPageKey, resultsPerPage);
			modelMap.put(newsListKey,
					posts.getPostForPage(currentPagingPosition, resultsPerPage));

			return mainPageOutPost;

		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}

	}

	@RequestMapping(value = "/managenews", method = RequestMethod.GET)
	public String index(Map<String, Object> modelMap) {

		modelMap.put(pageNumberKey, pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());
		try {
			int maxPageCount = pageMan
					.getMaxPageCount(pageMan.getDefaultResultPerPage(),
							posts.getPostListCount());
			modelMap.put(maxPageCountKey, maxPageCount);
			return managePageNewsGet;
		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}

	}

	@RequestMapping(value = "managenewspost", method = RequestMethod.POST)
	public String mainNewsPost(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("resultsPerPage") int resultsPerPage,
			Map<String, Object> modelMap) {

		try {

			long resultCount = posts.getPostListCount();
			modelMap.put(maxResultCountKey, resultCount);
			int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
			modelMap.put(maxPageCountKey, maxPageCount);
			int currentPagingPosition = pageMan.getCurrentPagingPosition(
					pageNumber, resultsPerPage);
			modelMap.put(pageNumberKey, pageNumber);
			modelMap.put(resultsPerPageKey, resultsPerPage);
			modelMap.put(newsListKey,
					posts.getPostForPage(currentPagingPosition, resultsPerPage));

			return managePageNewsOutPost;

		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}

	}

	@RequestMapping(value = "/addnews", method = RequestMethod.GET)
	public String addNews() {
		return addNewsOutGet;
	}

	@RequestMapping(value = "addnews", method = RequestMethod.POST)
	public String addNewsToBD(
			@ModelAttribute("newsTitle") String newsTitle,
			@ModelAttribute("newsDescription") String newsDescription,
			Map<String, Object> modelMap) {
		try {
			posts.createNews(newsTitle, newsDescription);
		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}

		return addNewsOutPost;
	}

	@RequestMapping(value = "/delnews/{delnews}", method = RequestMethod.GET)
	public String delNews(
			@PathVariable("delnews") Integer postId,
			Map<String, Object> map) {
		try {
			posts.removeNews(postId);
		} catch (PostManagerExeption e) {
			map.put("incorrectMsg", "Incorrect News!");
			return resultOut;
		}
		return delNewsGet;

	}

	@RequestMapping(value = "/editnews/managenews", method = RequestMethod.GET)
	public String cancelAction() {

		return "redirect:/managenews";
	}

	@RequestMapping(value = "editnews/{editnews}", method = RequestMethod.GET)
	public String editNews(@PathVariable("editnews") Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		try {
			post = posts.findNews(postId);

			modelMap.put("post", post);
		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}
		return editNewsGet;
	}

	@RequestMapping(value = "editnews/{editnews}", method = RequestMethod.POST)
	public String updateNewsToBD(
			@ModelAttribute("idTitle") Integer newsId,
			@ModelAttribute("newsTitle") String newsTitle,
			@ModelAttribute("newsDescription") String newsDescription,
			Map<String, Object> modelMap) {
		try {
			posts.updateNews(newsId, newsTitle, newsDescription);
		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}

		return editNewsPost;
	}

	@RequestMapping(value = "/detailsnews/{detailsId}", method = RequestMethod.GET)
	public String detailsNews(@PathVariable("detailsId") Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		try {
			post = posts.findNews(postId);

			modelMap.put("News", post);
		} catch (PostManagerExeption e) {
			modelMap.put(errorListKey, ExceptionUtil.createErrorList(e));
			modelMap.put(errorMsgKey, e.getMessage());
			return resultOut;
		}
		return detailsNewsGet;
	}

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(Map<String, Object> modelMap) {
		return resultOut;
	}

}
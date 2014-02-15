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


@Controller
public class IndexPageController {
	
	//modelMap String Keys
	final String pageNumberKey = "pageNumber";
	
	final String sizeOfPagingKey = "sizeOfPaging";
	final String maxPageCountKey = "maxPageCount";
	final String maxResultCountKey = "maxResultCount";
	final String newsListKey = "newsList";
	final String newsNameListKey = "post";
	final String postNameListKey = "News";
	
	//RequesMapping input & output String value
	final String mainPageInGet = "/mainpage";
	final String mainPageOutGet = "mainpage";
	final String resultOut = "result";
	final String mainPageInPost = "/mainpagepost";
	final String mainPageOutPost = "mainpagepost";
	final String managePageNewsInGet = "/managenews";
	final String managePageNewsGet = "managenews";
	final String managePageNewsInPost = "/managenewspost";
	final String managePageNewsOutPost = "managenewspost";
	final String addNewsInGet = "/addnews";
	final String addNewsOutGet = "addnews";
	final String addNewsInPost = "/addnews";
	final String addNewsOutPost = "redirect:/managenews";
	final String delNewsInGet ="/delnews/{delnews}";
	final String delNewsGet = "redirect:/managenews";
	final String editNewsRedirectGet = "/editnews/managenews";
	final String editNewsRedirectOutGet = "redirect:/managenews";
	final String editNewsInGet ="editnews/{editnews}";
	final String editNewsGet = "editnews";
	final String editNewsPost = "redirect:/managenews";
	final String detailsNewsInGet = "/detailsnews/{detailsId}";
	final String detailsNewsGet = "detailsnews";
	final String resultsPerPageKey = "/errorinput";
	
	//Request params Keys
	final String requestParamPageNumber = "pageNumber";
	final String requestParamResultsPerPage = "resultsPerPage";
	final String modelAttributeNewsTitle = "newsTitle";
	final String modelAttributeIdTitle = "idTitle";
	final String modelAttributeNewsDescription = "newsDescription";
	final String pathVariableDelnews = "delnews";
	final String pathVariableEditnews = "editnews";
	final String pathVariableDetailsId = "detailsId";
	
	@Autowired
	public PaginationManager pageMan = PaginationManager.getInstance();

	@Autowired
	private PostForMainPageManager posts;

	@RequestMapping(value = mainPageInGet, method = RequestMethod.GET)
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
			return mainPageOutGet;
		}

	}

	@RequestMapping(value = mainPageInPost, method = RequestMethod.POST)
	public String mainPagePost(
			@RequestParam(requestParamPageNumber) int pageNumber,
			@RequestParam(requestParamResultsPerPage) int resultsPerPage,
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
					posts.getPostForPage(currentPagingPosition-1, resultsPerPage));

			return mainPageOutPost;

		} catch (PostManagerExeption e) {
			
			return mainPageOutPost;
		}

	}

	@RequestMapping(value = managePageNewsInGet, method = RequestMethod.GET)
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
			
			return managePageNewsGet;
		}

	}

	@RequestMapping(value = managePageNewsInPost, method = RequestMethod.POST)
	public String mainNewsPost(@RequestParam(requestParamPageNumber) int pageNumber,
			@RequestParam(requestParamResultsPerPage) int resultsPerPage,
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
					posts.getPostForPage(currentPagingPosition-1, resultsPerPage));

			return managePageNewsOutPost;

		} catch (PostManagerExeption e) {
			
			return managePageNewsOutPost;
		}

	}

	@RequestMapping(value = addNewsInGet, method = RequestMethod.GET)
	public String addNews() {
		return addNewsOutGet;
	}

	@RequestMapping(value = addNewsInPost, method = RequestMethod.POST)
	public String addNewsToBD(
			@ModelAttribute(modelAttributeNewsTitle) String newsTitle,
			@ModelAttribute(modelAttributeNewsDescription) String newsDescription,
			Map<String, Object> modelMap) {
		try {
			posts.createNews(newsTitle, newsDescription);
		} catch (PostManagerExeption e) {
			
			return addNewsOutPost;
		}

		return addNewsOutPost;
	}

	@RequestMapping(value = delNewsInGet, method = RequestMethod.GET)
	public String delNews(
			@PathVariable(pathVariableDelnews) Integer postId,
			Map<String, Object> map) {
		try {
			posts.removeNews(postId);
		} catch (PostManagerExeption e) {
			
			return delNewsGet;
		}
		return delNewsGet;

	}

	@RequestMapping(value = editNewsRedirectGet, method = RequestMethod.GET)
	public String cancelAction() {

		return editNewsRedirectOutGet;
	}

	@RequestMapping(value = editNewsInGet, method = RequestMethod.GET)
	public String editNews(@PathVariable(pathVariableEditnews) Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		try {
			post = posts.findNews(postId);

			modelMap.put(newsNameListKey, post);
		} catch (PostManagerExeption e) {
			
			return editNewsGet;
		}
		return editNewsGet;
	}

	@RequestMapping(value = editNewsInGet, method = RequestMethod.POST)
	public String updateNewsToBD(
			@ModelAttribute(modelAttributeIdTitle) Integer newsId,
			@ModelAttribute(modelAttributeNewsTitle) String newsTitle,
			@ModelAttribute(modelAttributeNewsDescription) String newsDescription,
			Map<String, Object> modelMap) {
		try {
			posts.updateNews(newsId, newsTitle, newsDescription);
		} catch (PostManagerExeption e) {
			
			return editNewsPost;
		}

		return editNewsPost;
	}

	@RequestMapping(value = detailsNewsInGet, method = RequestMethod.GET)
	public String detailsNews(@PathVariable(pathVariableDetailsId) Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		try {
			post = posts.findNews(postId);

			modelMap.put(postNameListKey, post);
		} catch (PostManagerExeption e) {
			
			return detailsNewsGet;
		}
		return detailsNewsGet;
	}
	

}
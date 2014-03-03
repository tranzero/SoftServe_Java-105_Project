package com.ita.edu.softserve.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.manager.PropertiesManager;
import com.ita.edu.softserve.manager.impl.PaginationManager;

@Controller
public class IndexPageController {

	// modelMap String Keys
	final static String pageNumberKey = "pageNumber";

	final static String sizeOfPagingKey = "sizeOfPaging";
	final static String maxPageCountKey = "maxPageCount";
	final static String maxResultCountKey = "maxResultCount";
	final static String newsListKey = "newsList";
	final static String newsNameListKey = "post";
	final static String postNameListKey = "News";
	final static String mainImgPathKey = "mainImgPath";
	final static String hostNameKey = "hostName";

	// RequesMapping input & output String value
	final static String mainPageInGet = "/mainpage";
	final static String mainPageOutGet = "mainpage";
	final static String resultOut = "result";
	final static String mainPageInPost = "/mainpagepost";
	final static String mainPageOutPost = "mainpagepost";
	final static String managePageNewsInGet = "/managenews";
	final static String managePageNewsGet = "managenews";
	final static String managePageNewsInPost = "/managenewspost";
	final static String managePageNewsOutPost = "managenewspost";
	final static String addNewsInGet = "/addnews";
	final static String addNewsOutGet = "addnews";
	final static String addNewsInPost = "/addnews";
	final static String addNewsOutPost = "redirect:/managenews";
	final static String delNewsIn = "delnews";
	final static String delNewsGet = "redirect:/managenews";
	final static String editNewsRedirectOutGet = "redirect:/managenews";
	final static String editNewsIn = "editnews";
	final static String updateNews = "updatenews";
	final static String editNewsPostOut = "redirect:/managenews";
	final static String detailsNewsInGet = "/detailsnews";
	final static String detailsNewsGet = "detailsnews";
	final static String resultsPerPageKey = "/errorinput";

	// Request params Keys
	final static String requestParamPageNumber = "pageNumber";
	final static String requestParamResultsPerPage = "resultsPerPage";
	final static String modelAttributeNewsTitle = "newsTitle";
	final static String modelAttributeIdTitle = "idTitle";
	final static String modelAttributeNewsDescription = "newsDescription";
	final static String modelAttributeEditnews = "newsId";
	final static String modelAttributefileName = "fileName";
	final static String pathVariableDelnews = "delnews";
	final static String pathVariableDetailsId = "detailsId";

	@Autowired
	public PaginationManager pageMan = PaginationManager.getInstance();

	@Autowired
	private PostForMainPageManager posts;
	
	@Autowired
	private PropertiesManager propertyManager;

	@RequestMapping(value = mainPageInGet, method = RequestMethod.GET)
	public String mainPage(Map<String, Object> modelMap) {

		modelMap.put(pageNumberKey, pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());
		int maxPageCount = pageMan.getMaxPageCount(
				pageMan.getDefaultResultPerPage(), posts.getPostListCount());
		modelMap.put(maxPageCountKey, maxPageCount);
		return mainPageOutGet;

	}
		
	@RequestMapping(value = mainPageInPost, method = RequestMethod.POST)
	public String mainPagePost(
			@RequestParam(requestParamPageNumber) int pageNumber,
			@RequestParam(requestParamResultsPerPage) int resultsPerPage,
			Map<String, Object> modelMap) {

		long resultCount = posts.getPostListCount();
		modelMap.put(maxResultCountKey, resultCount);
		int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		modelMap.put(maxPageCountKey, maxPageCount);
		int currentPagingPosition = pageMan.getCurrentPagingPosition(
				pageNumber, resultsPerPage);
		modelMap.put(pageNumberKey, pageNumber);
		modelMap.put(resultsPerPageKey, resultsPerPage);
		modelMap.put(newsListKey,
				posts.getPostForPage(currentPagingPosition - 1, resultsPerPage));
		modelMap.put(mainImgPathKey, propertyManager.getImgPath());
		modelMap.put(hostNameKey, propertyManager.getHostPath());
		return mainPageOutPost;

	}

	@RequestMapping(value = managePageNewsInGet, method = RequestMethod.GET)
	public String index(Map<String, Object> modelMap) {

		modelMap.put(pageNumberKey, pageMan.getDefaultPageNumber());
		modelMap.put(resultsPerPageKey, pageMan.getDefaultResultPerPage());
		modelMap.put(sizeOfPagingKey, pageMan.getDefaultPageCount());
		int maxPageCount = pageMan.getMaxPageCount(
				pageMan.getDefaultResultPerPage(), posts.getPostListCount());
		modelMap.put(maxPageCountKey, maxPageCount);
		return managePageNewsGet;

	}

	@RequestMapping(value = managePageNewsInPost, method = RequestMethod.POST)
	public String mainNewsPost(
			@RequestParam(requestParamPageNumber) int pageNumber,
			@RequestParam(requestParamResultsPerPage) int resultsPerPage,
			Map<String, Object> modelMap) {

		long resultCount = posts.getPostListCount();
		modelMap.put(maxResultCountKey, resultCount);
		int maxPageCount = pageMan.getMaxPageCount(resultsPerPage, resultCount);
		modelMap.put(maxPageCountKey, maxPageCount);
		int currentPagingPosition = pageMan.getCurrentPagingPosition(
				pageNumber, resultsPerPage);
		modelMap.put(pageNumberKey, pageNumber);
		modelMap.put(resultsPerPageKey, resultsPerPage);
		modelMap.put(newsListKey,
				posts.getPostForPage(currentPagingPosition - 1, resultsPerPage));
		modelMap.put(mainImgPathKey, propertyManager.getImgPath());
		return managePageNewsOutPost;

	}

	@RequestMapping(value = addNewsInGet, method = RequestMethod.GET)
	public String addNews() {
		return addNewsOutGet;
	}

	@RequestMapping(value = addNewsInPost, method = RequestMethod.POST)
	public String addNewsToBD(
			@ModelAttribute(modelAttributeNewsTitle) String newsTitle,
			@ModelAttribute(modelAttributeNewsDescription) String newsDescription,
			@ModelAttribute(modelAttributefileName) String fileName,
			Map<String, Object> modelMap) {

		posts.createNews(newsTitle, newsDescription, fileName);
		return addNewsOutPost;
	}

	@RequestMapping(value = delNewsIn, method = RequestMethod.POST)
	public String delNews(@ModelAttribute(modelAttributeEditnews) Integer postId) {

		posts.removeNews(postId);
		return delNewsGet;

	}

		@RequestMapping(value = editNewsIn, method = RequestMethod.POST)
	public String editNewsPost(@ModelAttribute(modelAttributeEditnews) Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		post = posts.findNews(postId);
		modelMap.put(newsNameListKey, post);
		return editNewsIn;
	}
		
	@RequestMapping(value = updateNews, method = RequestMethod.POST)
	public String updateNewsToBD(
			@ModelAttribute(modelAttributefileName) String filename,
			@ModelAttribute(modelAttributeIdTitle) Integer newsId,
			@ModelAttribute(modelAttributeNewsTitle) String newsTitle,
			@ModelAttribute(modelAttributeNewsDescription) String newsDescription,
			Map<String, Object> modelMap) {

		posts.updateNews(newsId, newsTitle, newsDescription, filename);

		return editNewsPostOut;
	}

	@RequestMapping(value = detailsNewsInGet, method = RequestMethod.POST)
	public String detailsNews(
			@ModelAttribute(pathVariableDetailsId) Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		post = posts.findNews(postId);
		modelMap.put(postNameListKey, post);
		modelMap.put(mainImgPathKey, propertyManager.getImgPath());
		modelMap.put(hostNameKey, propertyManager.getHostPath());
		return detailsNewsGet;
	}
	
	@RequestMapping(value = "news/images/{img}.{res}", method = RequestMethod.GET)
	public void showImg(HttpServletResponse response,
			@PathVariable("img") String img,
			@PathVariable("res") String res) throws IOException {
		
		File file = new File(propertyManager.getImgPath()+img+"."+res);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() +"\""); 
        InputStream inputStream = null; 
        inputStream = new FileInputStream(file); 
        FileCopyUtils.copy(inputStream, response.getOutputStream()); 
		
	}

}
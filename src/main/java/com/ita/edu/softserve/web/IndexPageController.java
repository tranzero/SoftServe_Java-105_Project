package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerExeption;
import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.utils.ExceptionUtil;

@Controller
public class IndexPageController {

	@Autowired
	private PostForMainPageManager posts;

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String mainPage(Map<String, Object> modelMap) {

		try {
			modelMap.put("newsList", posts.findPostList());
		} catch (PostManagerExeption e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}
		return "mainpage";
	}

	@RequestMapping(value = "/managenews", method = RequestMethod.GET)
	public String index(Map<String, Object> modelMap) {

		try {
			modelMap.put("newsList", posts.findPostList());
		} catch (PostManagerExeption e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}
		return "managenews";
	}

	@RequestMapping(value = "/addnews", method = RequestMethod.GET)
	public String addNews() {
		return "addnews";
	}

	@RequestMapping(value = "addnews", method = RequestMethod.POST)
	public String addNewsToBD(@ModelAttribute("newsTitle") String newsTitle,
			@ModelAttribute("newsDescription") String newsDescription,
			Map<String, Object> modelMap) {
		try {
			posts.createNews(newsTitle, newsDescription);
		} catch (PostManagerExeption e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}

		return "redirect:/managenews";
	}

	@RequestMapping(value = "/delnews/{delnews}", method = RequestMethod.GET)
	public String delNews(@PathVariable("delnews") Integer postId, Map<String, Object> map) {
		try {
			posts.removeNews(postId);
		} catch (PostManagerExeption e) {
			map.put("incorrectMsg", "Incorrect News!");
			return "result";
		}
		return "redirect:/managenews";

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
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}
		return "editnews";
	}

	@RequestMapping(value = "editnews/{editnews}", method = RequestMethod.POST)
	public String updateNewsToBD(@PathVariable("editnews") Integer newsId,
			@ModelAttribute("newsTitle") String newsTitle,
			@ModelAttribute("newsDescription") String newsDescription, Map<String, Object> modelMap) {
		try {
			posts.updateNews(newsId, newsTitle, newsDescription);
		} catch (PostManagerExeption e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}

		return "redirect:/managenews";
	}

	@RequestMapping(value = "/detailsnews/{detailsId}", method = RequestMethod.GET)
	public String detailsNews(@PathVariable("detailsId") Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		try {
			post = posts.findNews(postId);

			modelMap.put("News", post);
		} catch (PostManagerExeption e) {
			modelMap.put("errorList", ExceptionUtil.createErrorList(e));
			modelMap.put("errorMsg", e.getMessage());
			return "result";
		}
		return "detailsnews";
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result( Map<String, Object> modelMap) {
		return "result";
	}
	
	
}
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

@Controller
public class IndexPageController {

	@Autowired
	private PostForMainPageManager posts;
	
	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String mainPage(Map<String, Object> modelMap) {

		try {
			modelMap.put("newsList", posts.findPostList());
		} catch (PostManagerExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "mainpage";
	}


	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Map<String, Object> modelMap) {

		try {
			modelMap.put("newsList", posts.findPostList());
		} catch (PostManagerExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping(value = "/indexwithdiv", method = RequestMethod.GET)
	public String indexwithdiv(Map<String, Object> modelMap) {

		try {
			modelMap.put("newsList", posts.findPostList());
		} catch (PostManagerExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "indexwithdiv";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/index";
	}

	@RequestMapping(value = "/delnews/{delnews}", method = RequestMethod.GET)
	public String delNews(@PathVariable("delnews") Integer postId) {
		try {
			posts.removeNews(postId);
		} catch (PostManagerExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/index";

	}

	@RequestMapping(value = "editnews/{editnews}", method = RequestMethod.GET)
	public String editNews(@PathVariable("editnews") Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		try {
			post = posts.findNews(postId);
		
		modelMap.put("post", post);
		} catch (PostManagerExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "editnews";
	}

	@RequestMapping(value = "editnews/{editnews}", method = RequestMethod.POST)
	public String updateNewsToBD(@PathVariable("editnews") Integer newsId,
			@ModelAttribute("newsTitle") String newsTitle,
			@ModelAttribute("newsDescription") String newsDescription) {
		try {
			posts.updateNews(newsId, newsTitle, newsDescription);
		} catch (PostManagerExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/index";
	}

	@RequestMapping(value = "/detailsnews/{detailsId}", method = RequestMethod.GET)
	public String detailsNews(@PathVariable("detailsId") Integer postId,
			Map<String, Object> modelMap) {

		Post post;
		try {
			post = posts.findNews(postId);
		
		modelMap.put("News", post);
		} catch (PostManagerExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "detailsnews";
	}

}
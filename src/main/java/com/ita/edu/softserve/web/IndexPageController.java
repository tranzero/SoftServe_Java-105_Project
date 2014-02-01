package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.manager.PostForMainPageManager;

@Controller
public class IndexPageController {

	@Autowired
	private PostForMainPageManager posts;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Map<String, Object> modelMap) {

		modelMap.put("newsList", posts.findPostList());
		return "index";
	}
	
	@RequestMapping(value = "/indexwithdiv", method = RequestMethod.GET)
	public String indexwithdiv(Map<String, Object> modelMap) {

		modelMap.put("newsList", posts.findPostList());
		return "indexwithdiv";
	}
	
	@RequestMapping(value = "/addnews", method = RequestMethod.GET)
	public String addNews(){
		return "addnews";
	}
	
	@RequestMapping(value = "/addnews", method = RequestMethod.POST)
	public String addNewsToBD( 
			@ModelAttribute("newsTitle") String newsTitle,
			@ModelAttribute("newsDescription") String newsDescription,
			Map<String, Object> modelMap){
			posts.createNews(newsTitle, newsDescription);
		
		return "redirect:/index";
	}
	@RequestMapping(value = "delnews/{delnews}")
		public String delNews(
			@PathVariable("delnews") Integer postId){
			posts.removeNews(postId);
			return "redirect:/index";
			
		}
	

}
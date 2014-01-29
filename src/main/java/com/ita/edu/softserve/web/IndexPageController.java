package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl;

@Controller
public class IndexPageController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Map<String, Object> modelMap){
		PostForMainPageManager posts = (PostForMainPageManager) PostForMainPageManagerImpl.getInstance();
		
		modelMap.put("newsList", posts.findPostList());
		return "index";
	}

}

package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.ita.edu.softserve.service.PostForMainPageManager;
import com.ita.edu.softserve.service.impl.PostForMainPageManagerImpl;

@Controller
public class IndexPageController {
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Map<String, Object> modelMap){
		PostForMainPageManager posts = (PostForMainPageManager) PostForMainPageManagerImpl.getInstance();
		
		modelMap.put("newsList", posts.findPostList());
		return "index";
	}

}

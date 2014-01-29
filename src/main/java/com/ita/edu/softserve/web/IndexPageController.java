package com.ita.edu.softserve.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}

package com.ita.edu.softserve.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPageController {
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}

}

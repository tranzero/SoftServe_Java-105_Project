package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.service.PostForMainPageService;
import com.ita.edu.softserve.service.impl.PostForMainPageServiceImpl;

public class PostMain implements MainIface {
	
	public static void main(String... args) {
		
		PostForMainPageService posts = (PostForMainPageService) appContext.getBean("postForMainPageService");
		for (Post post: posts.findPostList() ){
			
			System.out.println(post);
			
		}
		
	}

}

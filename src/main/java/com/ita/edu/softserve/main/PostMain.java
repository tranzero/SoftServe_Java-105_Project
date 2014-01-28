package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.service.PostForMainPageManager;
import com.ita.edu.softserve.service.impl.PostForMainPageManagerImpl;

public class PostMain {
	
	public static void main(String... args) {
		
		PostForMainPageManager posts = (PostForMainPageManager) PostForMainPageManagerImpl.getInstance();
		for (Post post: posts.findPostList() ){
			
			System.out.println(post.getDescription() + post.getDate() + post.getTitle());
			
		}
		
	}

}

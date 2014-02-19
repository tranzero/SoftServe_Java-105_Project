package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerException;
import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl;

public class PostMain {
	
	public static void main(String... args) throws PostManagerException {
		
		PostForMainPageManager posts = (PostForMainPageManager) PostForMainPageManagerImpl.getInstance();
//		for (Post post: posts.findPostList() ){
//			
//			System.out.println(post.getDescription() + post.getDate() + post.getTitle());
//			
//		}
	 System.out.println(posts.getPostListCount());
		
	 for (Post post: posts.getPostForPage(1, 5000)){
		 System.out.println(post.getDescription());
	 }
	}

}

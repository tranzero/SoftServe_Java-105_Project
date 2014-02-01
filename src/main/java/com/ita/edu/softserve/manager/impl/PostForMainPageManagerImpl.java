package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.PostForMainPageManager;

@Service("postForMainPageService")
public class PostForMainPageManagerImpl implements PostForMainPageManager {
	
	@Autowired
	PostDAOImpl postDao;

	@Override
	public List<Post> findPostList() {
		return postDao.getAllEntities();
	}
	public static PostForMainPageManager getInstance() {
		return ManagerFactory.getManager(PostForMainPageManager.class); 
	}
	
	@Transactional
	@Override
	public void createNews(String newsTitle, String newsDescription) {
		Post post = new Post(newsTitle, newsDescription);
		
		postDao.save(post);
		
		
	}
	
	@Transactional
	@Override
	public void removeNews(Integer id) {
		
		Post temp = postDao.findById(id);
		postDao.remove(temp);
	}
	@Override
	public Post findNews(Integer postId) {
		
		return postDao.findById(postId);
	}
	
	
}

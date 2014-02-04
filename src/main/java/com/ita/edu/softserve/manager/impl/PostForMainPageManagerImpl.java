package com.ita.edu.softserve.manager.impl;



import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerExeption;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.PostForMainPageManager;

@Service("postForMainPageService")
public class PostForMainPageManagerImpl implements PostForMainPageManager  {
	private static final Logger LOGGER = Logger.getLogger(PostManagerExeption.class);
	
	@Autowired
	PostDAOImpl postDao;

	@Transactional(readOnly = true)
	@Override
	public List<Post> findPostList()  throws PostManagerExeption{
		try{
		return postDao.getAllEntities();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerExeption(
					"Could not find Post List", e);
		}
	}
	
	
	@Transactional
	@Override
	public void createNews(String newsTitle, String newsDescription) throws PostManagerExeption {
		
		try{
		Post post = new Post(newsTitle, newsDescription);
		postDao.save(post);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerExeption(
					"Could not create Post", e);
		}
		
		
	}
	
	@Transactional
	@Override
	public void removeNews(Integer id) throws PostManagerExeption {
		try {
		Post temp = postDao.findById(id);
		postDao.remove(temp);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerExeption(
					"Could not remove Post", e);
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public Post findNews(Integer postId) throws PostManagerExeption {
		try{
		return postDao.findById(postId);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerExeption(
					"Could not find news by id", e);
		}
	}
	
	@Transactional
	@Override
	public void updateNews(Integer newsId, String newsTitle,
			String newsDescription) throws PostManagerExeption {
		
		try {
		Post post = postDao.findById(newsId);
		
		post.setTitle(newsTitle);
		post.setDescription(newsDescription);
		postDao.update(post);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerExeption(
					"Could not update Post", e);
		}
		
		
	}
	
	@Override
	public long getPostListCount() throws PostManagerExeption{
		try {
			return postDao.getPostListCount();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerExeption(
					"Could not get post list count", e);
		}
	}
	
	public static PostForMainPageManager getInstance() {
		return ManagerFactory.getManager(PostForMainPageManager.class); 
	}
	
}

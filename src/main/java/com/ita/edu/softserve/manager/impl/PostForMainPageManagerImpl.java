package com.ita.edu.softserve.manager.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerException;
import com.ita.edu.softserve.manager.ManagerFactory;
import com.ita.edu.softserve.manager.PostForMainPageManager;

@Service("postForMainPageService")
public class PostForMainPageManagerImpl implements PostForMainPageManager  {
	private static final Logger LOGGER = Logger.getLogger(PostForMainPageManagerImpl.class);
	private String entityName = Post.class.getCanonicalName().replace("com.ita.edu.softserve.entity.", "").concat(" with id=");
	private String addMsg = " was added to DB by ";
	private String removeMsg = " was remove from DB by ";
	private String changeMsg = " was change in DB by ";
	@Autowired
	PostDAOImpl postDao;

	@Transactional(readOnly = true)
	@Override
	public List<Post> findPostList()  throws PostManagerException{
		try{
		return postDao.getAllEntities();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerException(
					"Could not find Post List", e);
		}
	}
	
	
	@Transactional
	@Override
	public void createNews(String newsTitle, String newsDescription) throws PostManagerException {
		
		try{
		Post post = new Post(newsTitle, newsDescription);
		postDao.save(post);
		LOGGER.info(entityName + post.getPostId() + addMsg);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerException(
					"Could not create Post", e);
		}
		
		
	}
	
	@Transactional
	@Override
	public void removeNews(Integer id) throws PostManagerException {
		try {
		Post temp = postDao.findById(id);
		postDao.remove(temp);
		LOGGER.info(entityName + temp.getPostId() + removeMsg);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerException(
					"Could not remove Post", e);
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public Post findNews(Integer postId) throws PostManagerException {
		try{
		return postDao.findById(postId);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerException(
					"Could not find news by id", e);
		}
	}
	
	@Transactional
	@Override
	public void updateNews(Integer newsId, String newsTitle,
			String newsDescription) throws PostManagerException {
		
		try {
		Post post = postDao.findById(newsId);
		
		post.setTitle(newsTitle);
		post.setDescription(newsDescription);
		postDao.update(post);
		LOGGER.info(entityName + post.getPostId() + changeMsg);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerException(
					"Could not update Post", e);
		}
		
		
	}
	
	@Override
	public long getPostListCount() throws PostManagerException{
		try {
			return postDao.getPostListCount();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerException(
					"Could not get post list count", e);
		}
	}
	
	@Override
	public List<Post> getPostForPage(int from, int count)
			throws PostManagerException {
		try {
			return postDao.getPostForOnePage(from, count);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PostManagerException(
					"Could not get post for one page",
					e);
		}
	}

	
	public static PostForMainPageManager getInstance() {
		return ManagerFactory.getManager(PostForMainPageManager.class); 
	}
	
}

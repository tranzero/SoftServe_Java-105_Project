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
import com.ita.edu.softserve.manager.UserNameService;

@Service("postForMainPageService")
public class PostForMainPageManagerImpl implements PostForMainPageManager {
	private static final Logger LOGGER = Logger
			.getLogger(PostForMainPageManagerImpl.class);

	private String entityName = Post.class.getSimpleName().concat(" with id=");
	private String addMsg = " was added to DB by ";
	private String removeMsg = " was remove from DB by ";
	private String changeMsg = " was change in DB by ";
	private final String createPostMsg = "Could not create Post";
	private final String findPostMsg = "Could not find Post List";
	private final String removePostMsg = "Could not remove Post";
	private final String findByIdPostMsg = "Could not find news by id";
	private final String updatePostMsg = "Could not update Post";
	private final String countPostMsg = "Could not get post list count";
	private final String resultPerPagePostMsg = "Could not get post for one page";

	@Autowired
	private PostDAOImpl postDao;
	
	@Autowired
	private UserNameService userName;

	@Transactional(readOnly = true)
	@Override
	public List<Post> findPostList() {
		
		try {
			
			return postDao.getAllEntities();
		} catch (RuntimeException e) {
			RuntimeException ex = new PostManagerException(findPostMsg, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	
	}

	@Transactional
	@Override
	public void createNews(String newsTitle, String newsDescription, String imageSrc) {

		try {
			Post post = new Post(newsTitle, newsDescription, imageSrc);
			postDao.save(post);
			LOGGER.info(entityName + post.getPostId() + addMsg + userName.getLoggedUsername());
		} catch (RuntimeException e) {
			RuntimeException ex = new PostManagerException(createPostMsg, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

	}

	@Transactional
	@Override
	public void removeNews(Integer id) {
		try {
			Post post = postDao.findById(id);
			postDao.remove(post);
			LOGGER.info(entityName + id + removeMsg + userName.getLoggedUsername());
		} catch (RuntimeException e) {
			RuntimeException ex = new PostManagerException(removePostMsg, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Post findNews(Integer postId) {
		
		try {
			return postDao.findById(postId);
		} catch (RuntimeException e) {
			RuntimeException ex= new PostManagerException(findByIdPostMsg, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Transactional
	@Override
	public void updateNews(Integer newsId, String newsTitle,
			String newsDescription, String imageSrc) {

		try {
			Post post = postDao.findById(newsId);
			post.setTitle(newsTitle);
			post.setDescription(newsDescription);
			post.setDate();
			post.setImgSrc(imageSrc);
			postDao.update(post);
			LOGGER.info(entityName + post.getPostId() + changeMsg + userName.getLoggedUsername());
		} catch (RuntimeException e) {
			RuntimeException ex = new PostManagerException(updatePostMsg, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}

	}

	@Override
	public long getPostListCount() {
		try {
			return postDao.getPostListCount();
		} catch (RuntimeException e) {
			RuntimeException ex = new PostManagerException(countPostMsg, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	@Override
	public List<Post> getPostForPage(int from, int count) {
		try {
			return postDao.getPostForOnePage(from, count);
		} catch (RuntimeException e) {
			RuntimeException ex = new PostManagerException(resultPerPagePostMsg, e);
			LOGGER.error(e);
			LOGGER.error(ex);
			throw ex;
		}
	}

	public static PostForMainPageManager getInstance() {
		return ManagerFactory.getManager(PostForMainPageManager.class);
	}

}

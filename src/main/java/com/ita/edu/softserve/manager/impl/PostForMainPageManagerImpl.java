package com.ita.edu.softserve.manager.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
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
	private String createPostMsg = "Could not create Post";
	private String findPostMsg = "Could not find Post List";
	private String removePostMsg = "Could not remove Post";
	private String findByIdPostMsg = "Could not find news by id"; 
	private String updatePostMsg = "Could not update Post";
	private String countPostMsg = "Could not get post list count";
	private String resultPerPagePostMsg = "Could not get post for one page";

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
			LOGGER.error(findPostMsg, e);
			throw e;
		}

	}

	@Transactional
	@Override
	public boolean createNews(String newsTitle, String newsDescription,
			String imageSrc) {
		Post post = null;
		post = postDao.findByTitle(newsTitle);

		if (post == null) {
			try {

				post = new Post(newsTitle, newsDescription, imageSrc);
				postDao.save(post);

				LOGGER.info(entityName + post.getPostId() + addMsg
						+ userName.getLoggedUsername());

				return true;

			} catch (RuntimeException e) {
				LOGGER.error(createPostMsg, e);
				throw e;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public boolean removeNews(Integer id) {
		Post post = null;
		post = postDao.findById(id);
		if (post != null) {
			try {
				postDao.remove(post);
				LOGGER.info(entityName + id + removeMsg
						+ userName.getLoggedUsername());
				return true;
			} catch (RuntimeException e) {
				LOGGER.error(removePostMsg, e);
				throw e;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public boolean removePost(String title) {
		Post post = null;
		post = postDao.findByTitle(title);

		if (post != null) {
			try {
				int id = post.getPostId();
				postDao.remove(post);
				LOGGER.info(entityName + id + removeMsg
						+ userName.getLoggedUsername());
				return true;
			} catch (RuntimeException e) {
				LOGGER.error(removePostMsg, e);
				throw e;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	@Override
	public Post findNews(Integer postId) {

		try {
			Post post = postDao.findById(postId);
			return post;
		} catch (RuntimeException e) {
			LOGGER.error(findByIdPostMsg, e);
			throw e;
		}
	}

	@Transactional
	@Override
	public boolean updateNews(Integer newsId, String newsTitle,
			String newsDescription, String imageSrc) {
		Post post = null;
		post = postDao.findById(newsId);

		if (post != null) {
			try {
				post.setTitle(newsTitle);
				post.setDescription(newsDescription);
				post.setDate();
				post.setImgSrc(imageSrc);
				postDao.update(post);
				LOGGER.info(entityName + post.getPostId() + changeMsg
						+ userName.getLoggedUsername());

				return true;
			} catch (RuntimeException e) {
				LOGGER.error(updatePostMsg, e);
				throw e;
			}
		}
		return false;
	}

	@Override
	public long getPostListCount() {
		try {
			return postDao.getPostListCount();
		} catch (RuntimeException e) {
			LOGGER.error(countPostMsg, e);
			throw e;
		}
	}

	@Override
	public List<Post> getPostForPage(int from, int count) {
		try {
			return postDao.getPostForOnePage(from, count);
		} catch (RuntimeException e) {
			LOGGER.error(resultPerPagePostMsg, e);
			throw e;
		}
	}

}

package com.ita.edu.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.PostDAO;
import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.service.ManagerFactory;
import com.ita.edu.softserve.service.PostForMainPageManager;
import com.ita.edu.softserve.service.UserManager;

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

}

package com.ita.edu.softserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ita.edu.softserve.dao.PostDAO;
import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.service.PostForMainPageService;

@Service("postForMainPageService")
public class PostForMainPageServiceImpl implements PostForMainPageService {
	
	@Autowired
	PostDAOImpl postDao;

	@Override
	public List<Post> findPostList() {
		return postDao.getAllEntities();
	}

}

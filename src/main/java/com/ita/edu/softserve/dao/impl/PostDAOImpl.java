package com.ita.edu.softserve.dao.impl;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.PostDAO;
import com.ita.edu.softserve.entity.Post;

@Repository
public class PostDAOImpl extends AbstractDAO<Post> implements PostDAO{

	@Override
	public Class<Post> getEntityClass() {
		
		return Post.class;
	}

}

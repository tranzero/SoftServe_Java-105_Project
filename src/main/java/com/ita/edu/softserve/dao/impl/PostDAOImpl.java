package com.ita.edu.softserve.dao.impl;

import com.ita.edu.softserve.dao.AbstractDAO;
import com.ita.edu.softserve.dao.PostDAO;
import com.ita.edu.softserve.entity.Post;

public class PostDAOImpl extends AbstractDAO<Post> implements PostDAO{

	@Override
	public Class<Post> getEntityClass() {
		
		return Post.class;
	}

}

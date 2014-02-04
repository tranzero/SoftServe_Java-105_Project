package com.ita.edu.softserve.dao.impl;



import javax.persistence.Query;

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

	public long getPostListCount() {
		return (long) find((Query)entityManager
					.createNamedQuery(Post.FIND_POST_LIST_COUNT));
	}

	
}

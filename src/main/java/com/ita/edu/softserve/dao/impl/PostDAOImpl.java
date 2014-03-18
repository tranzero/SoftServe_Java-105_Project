package com.ita.edu.softserve.dao.impl;





import java.util.List;

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
	
	@Override
	public long getPostListCount() {
		return (long) find((Query)entityManager
					.createNamedQuery(Post.FIND_POST_LIST_COUNT));
	}
	
	@Override
    public List<Post> getPostForOnePage (int from, int count) {
	return this.getPostForPaging(from, count);
    }
	
	private List<Post> getPostForPaging(int from, int count) {
		Query query = entityManager
			.createNamedQuery(
					Post.FIND_POST_LIST_FOR_PAGING)
			.setFirstResult(from).setMaxResults(count);
		return (List<Post>)getRange(from, count, query);
	    }
	
	@Override
    public Post findByTitle(String title) {
	Query query = entityManager.createNamedQuery(
		Post.FIND_BY_TITLE).setParameter(1, title);
	return (Post) find(query);
    }
}

package com.ita.edu.softserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ita.edu.softserve.entity.Post;

@Repository
public interface PostDAO extends AbstractDAOIface<Post>{
	public long getPostListCount();

	public List<Post> getPostForOnePage(int from, int count);

	Post findByTitle(String title);
	
}

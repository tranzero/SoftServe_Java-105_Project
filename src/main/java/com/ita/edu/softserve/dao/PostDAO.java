package com.ita.edu.softserve.dao;

import java.util.List;

import com.ita.edu.softserve.entity.Post;

public interface PostDAO extends AbstractDAOIface<Post>{
	public long getPostListCount();

	public List<Post> getPostForOnePage(int from, int count);
	
}

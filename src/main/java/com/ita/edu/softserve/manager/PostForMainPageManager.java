package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Post;

@Service
public interface PostForMainPageManager extends BaseManager {
	
	public List<Post> findPostList();

}

package com.ita.edu.softserve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Post;

@Service
public interface PostForMainPageService {
	
	public List<Post> findPostList();

}
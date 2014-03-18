package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Post;

@Service
public interface PostForMainPageManager extends BaseManager {
	
	public List<Post> findPostList();

	public boolean removeNews(Integer id);

	public Post findNews(Integer postId);

	public long getPostListCount();

	public List<Post> getPostForPage(int from, int count);

	boolean createNews(String newsTitle, String newsDescription, String imageSrc);

	boolean updateNews(Integer newsId, String newsTitle, String newsDescription,
			String imageSrc);

	boolean removePost(String title);

}

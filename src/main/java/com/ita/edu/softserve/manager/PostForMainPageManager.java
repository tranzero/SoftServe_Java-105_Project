package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerExeption;

@Service
public interface PostForMainPageManager extends BaseManager {
	
	public List<Post> findPostList();

	public void createNews(String newsTitle, String newsDescription);

	public void removeNews(Integer id);

	public Post findNews(Integer postId);

	public void updateNews(Integer newsId, String newsTitle,
			String newsDescription);
	public long getPostListCount() throws PostManagerExeption;

}

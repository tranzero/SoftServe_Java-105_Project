package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerExeption;

@Service
public interface PostForMainPageManager extends BaseManager {
	
	public List<Post> findPostList() throws PostManagerExeption;

	public void createNews(String newsTitle, String newsDescription) throws PostManagerExeption;

	public void removeNews(Integer id) throws PostManagerExeption;

	public Post findNews(Integer postId) throws PostManagerExeption;

	public void updateNews(Integer newsId, String newsTitle,
			String newsDescription) throws PostManagerExeption;
	public long getPostListCount() throws PostManagerExeption;

}

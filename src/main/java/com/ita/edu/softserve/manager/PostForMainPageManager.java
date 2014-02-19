package com.ita.edu.softserve.manager;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.exception.PostManagerException;

@Service
public interface PostForMainPageManager extends BaseManager {
	
	public List<Post> findPostList() throws PostManagerException;

	public void createNews(String newsTitle, String newsDescription) throws PostManagerException;

	public void removeNews(Integer id) throws PostManagerException;

	public Post findNews(Integer postId) throws PostManagerException;

	public void updateNews(Integer newsId, String newsTitle,
			String newsDescription) throws PostManagerException;
	public long getPostListCount() throws PostManagerException;

	public List<Post> getPostForPage(int from, int count) throws PostManagerException;

}

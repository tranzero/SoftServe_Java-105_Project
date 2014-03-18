/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ita.edu.softserve.dao.PostDAO;
import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.manager.PostForMainPageManager;

/**
 * @author tranzero
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class PostForMainPageManagerImplTest {
	
	
	@Mock
	private PostDAOImpl postDao;
	
	@InjectMocks
	private PostForMainPageManager postManager = new PostForMainPageManagerImpl();
	
	String postTitleMock = "test Title";
	String postDescriptionMock = "test Description";
	String postImgSrcMock = "test.png";

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#findPostList()}.
	 */
	@Test
	public final void testFindPostList() {
		
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#createNews(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testCreateNews() {
		
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removeNews(java.lang.Integer)}.
	 */
	@Test
	public final void testRemoveNews() {
		Post post = new Post(postTitleMock, postDescriptionMock, postImgSrcMock);
		when(postDao.findByTitle(postTitleMock)).thenReturn(post);
		
		boolean isDeletedPost = false;
		
		
		isDeletedPost = postManager.removePost(postTitleMock);
		
		assertTrue(isDeletedPost);
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#findNews(java.lang.Integer)}.
	 */
	@Test
	public final void testFindNews() {
		
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#updateNews(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUpdateNews() {
		
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostListCount()}.
	 */
	@Test
	public final void testGetPostListCount() {
		
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostForPage(int, int)}.
	 */
	@Test
	public final void testGetPostForPage() {
		
	}

}

/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.ita.edu.softserve.dao.PostDAO;
import com.ita.edu.softserve.dao.impl.PostDAOImpl;
import com.ita.edu.softserve.entity.Post;
import com.ita.edu.softserve.manager.PostForMainPageManager;
import com.ita.edu.softserve.manager.UserNameService;

/**
 * @author tranzero
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class PostForMainPageManagerImplTest {
	
	
	@Mock
	private PostDAOImpl postDao;
	
	@Mock
	private UserNameService userName;
	
	
	
	@InjectMocks
	private PostForMainPageManager postManager = new PostForMainPageManagerImpl();
	
	int postIdMock = 20;
	String postTitleMock = "test Title";
	String postDescriptionMock = "test Description";
	String postImgSrcMock = "test.png";
	
	@Spy
	private Post post = new Post(postTitleMock, postDescriptionMock, postImgSrcMock);
	
	@Before
	public final void setUp() {
		when(userName.getLoggedUsername()).thenReturn("pukan");
		doReturn(postIdMock).when(post).getPostId();
		when(postDao.findByTitle(postTitleMock)).thenReturn(post);
		when(postDao.findById(postIdMock)).thenReturn(post);
	}
	
	
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
		
		boolean isCreatedPost = false;
		isCreatedPost = postManager.createNews(postTitleMock, postDescriptionMock, postImgSrcMock);
		
		assertTrue(isCreatedPost);
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removeNews(java.lang.Integer)}.
	 */
	@Test
	public final void testRemovePost() {

		boolean isDeletedPost = false;
		isDeletedPost = postManager.removePost(postTitleMock);
		
		assertTrue(isDeletedPost);
	}
	
	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removeNews(java.lang.Integer)}.
	 */
	@Test
	public final void testRemoveNews() {

		boolean isDeletedPost = false;
		isDeletedPost = postManager.removeNews(postIdMock);
		
		assertTrue(isDeletedPost);
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#findNews(java.lang.Integer)}.
	 */
	@Test
	public final void testFindNews() {
		Post actual = postDao.findById(postIdMock);
		assertEquals(post, actual);
		
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#updateNews(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testUpdateNews() {
		boolean isUpdatedPost = false;
		doCallRealMethod().when(post).setDescription(postDescriptionMock);
		doCallRealMethod().when(post).setTitle(postTitleMock);
		doCallRealMethod().when(post).setDate();
		doCallRealMethod().when(post).setImgSrc(postImgSrcMock);
		isUpdatedPost = postManager.updateNews(postIdMock ,postTitleMock, postDescriptionMock, postImgSrcMock);
		
		assertTrue(isUpdatedPost);
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostListCount()}.
	 */
	@Test
	public final void testGetPostListCount() {
		long expected = 25;
		when(postDao.getPostListCount()).thenReturn(expected);
		
		long actual = postDao.getPostListCount();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostForPage(int, int)}.
	 */
	@Test
	public final void testGetPostForPage() {
		
	}

}

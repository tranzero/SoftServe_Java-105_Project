/**
 * 
 */
package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

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

	private int postIdMock = 20;
	private String postTitleMock = "test Title";
	private String postDescriptionMock = "test Description";
	private String postImgSrcMock = "test.png";
	private String emptyArgString = null;

	@Spy
	private Post post = new Post(postTitleMock, postDescriptionMock,
			postImgSrcMock);
	
	
	@Before
	public final void setUp() {
		when(userName.getLoggedUsername()).thenReturn("pukan");
		doReturn(postIdMock).when(post).getPostId();
		when(postDao.findByTitle(postTitleMock)).thenReturn(post);
		when(postDao.findById(postIdMock)).thenReturn(post);

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#findPostList()}
	 * .
	 */
	@Test
	public final void testFindPostList() {
		List<Post> expected = new ArrayList<Post>();
		when(postDao.getAllEntities()).thenReturn(expected);
		List<Post> actual = postManager.findPostList();
		verify(postDao, times(1)).getAllEntities();
		assertEquals(expected, actual);

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#findPostList()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testFindPostListException() {
		when(postDao.getAllEntities()).thenThrow(new RuntimeException());
		postManager.findPostList();
		verify(postDao, times(1)).getAllEntities();
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#createNews(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testCreateNews() {

		boolean isCreatedPost = false;
		when(postDao.findByTitle(postTitleMock)).thenReturn(null);
		isCreatedPost = postManager.createNews(postTitleMock,
				postDescriptionMock, postImgSrcMock);
		verify(postDao, times(1)).findByTitle(postTitleMock);
		verify(postDao, times(1)).save(new Post(postTitleMock, postDescriptionMock,
				postImgSrcMock));

		assertTrue(isCreatedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#createNews(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testCreateNewsWhenNewsIsset() {

		boolean isCreatedPost = true;
		isCreatedPost = postManager.createNews(postTitleMock,
				postDescriptionMock, postImgSrcMock);
		
		verify(postDao, times(1)).findByTitle(postTitleMock);
		verify(postDao, times(0)).save(new Post(postTitleMock, postDescriptionMock,
				postImgSrcMock));
		assertFalse(isCreatedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#createNews(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateNewsExeptionFirthValue() {

		doThrow(new IllegalArgumentException()).when(post).setTitle(
				emptyArgString);
		postManager.createNews(emptyArgString, postDescriptionMock,
				postImgSrcMock);
		verify(postDao, times(1)).findByTitle(emptyArgString);
		verify(postDao, times(0)).save(new Post(emptyArgString, postDescriptionMock,
				postImgSrcMock));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#createNews(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateNewsExeptionSecondValue() {

		when(new Post(postTitleMock, emptyArgString, postImgSrcMock))
				.thenThrow(new IllegalArgumentException());
		postManager.createNews(postTitleMock, emptyArgString, postImgSrcMock);
		verify(postDao, times(1)).findByTitle(postTitleMock);
		verify(postDao, times(0)).save(new Post(postTitleMock, emptyArgString, postImgSrcMock));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#createNews(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateNewsExeptionBothValues() {
		doThrow(new IllegalArgumentException()).when(post).setDescription(
				emptyArgString);
		doThrow(new IllegalArgumentException()).when(post).setTitle(
				emptyArgString);
		postManager.createNews(emptyArgString, emptyArgString, postImgSrcMock);
		verify(postDao, times(1)).findByTitle(emptyArgString);
		verify(postDao, times(0)).save(new Post(emptyArgString, emptyArgString, postImgSrcMock));
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removePost(java.lang.Integer)}
	 * .
	 */
	@Test
	public final void testRemovePost() {

		boolean isDeletedPost = false;
		isDeletedPost = postManager.removePost(postTitleMock);
		verify(postDao, times(1)).findByTitle(postTitleMock);
		verify(postDao, times(1)).remove(post);
		assertTrue(isDeletedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removePost(java.lang.Integer)}
	 * .
	 */
	@Test
	public final void testRemovePostFalse() {
		boolean isDeletedPost = true;
		when(postDao.findByTitle(postTitleMock)).thenReturn(null);
		isDeletedPost = postManager.removePost(postTitleMock);
		verify(postDao, times(1)).findByTitle(postTitleMock);
		verify(postDao, times(0)).remove(post);
		assertFalse(isDeletedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removePost(java.lang.Integer)}
	 * .
	 */
	@Test(expected = NullPointerException.class)
	public final void testRemovePostExeption() {

		doThrow(new NullPointerException()).when(postDao).remove(post);
		postManager.removePost(postTitleMock);
		verify(postDao, times(1)).findByTitle(postTitleMock);
		verify(postDao, times(1)).remove(post);

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removeNews(java.lang.Integer)}
	 * .
	 */
	@Test
	public final void testRemoveNews() {

		boolean isDeletedPost = false;
		isDeletedPost = postManager.removeNews(postIdMock);
		verify(postDao, times(1)).findById(postIdMock);
		verify(postDao, times(1)).remove(post);

		assertTrue(isDeletedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removeNews(java.lang.Integer)}
	 * .
	 */
	@Test
	public final void testRemoveNewsFalse() {

		boolean isDeletedPost = true;
		when(postDao.findById(postIdMock)).thenReturn(null);
		isDeletedPost = postManager.removeNews(postIdMock);
		verify(postDao, times(1)).findById(postIdMock);
		verify(postDao, times(0)).remove(post);
		assertFalse(isDeletedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#removeNews(java.lang.Integer)}
	 * .
	 */
	@Test(expected = NullPointerException.class)
	public final void testRemoveNewsExeption() {

		doThrow(new NullPointerException()).when(postDao).remove(post);
		postManager.removeNews(postIdMock);
		verify(postDao, times(1)).findById(postIdMock);
		verify(postDao, times(1)).remove(post);

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#findNews(java.lang.Integer)}
	 * .
	 */
	@Test
	public final void testFindNews() {
		Post actual = postManager.findNews(postIdMock);
		verify(postDao, times(1)).findById(postIdMock);
		
		assertEquals(post, actual);

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#findNews(java.lang.Integer)}
	 * .
	 */
	@Test(expected = NullPointerException.class)
	public final void testFindNewsException() {
		when(postDao.findById(postIdMock))
				.thenThrow(new NullPointerException());
		postManager.findNews(postIdMock);
		verify(postDao, times(1)).findById(postIdMock);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#updateNews(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testUpdateNews() {
		boolean isUpdatedPost = false;
		doCallRealMethod().when(post).setDescription(postDescriptionMock);
		doCallRealMethod().when(post).setTitle(postTitleMock);
		doCallRealMethod().when(post).setDate();
		doCallRealMethod().when(post).setImgSrc(postImgSrcMock);
		isUpdatedPost = postManager.updateNews(postIdMock, postTitleMock,
				postDescriptionMock, postImgSrcMock);
		
		verify(postDao, times(1)).findById(postIdMock);
		verify(post, times(1)).setDescription(postDescriptionMock);
		verify(post, times(1)).setTitle(postTitleMock);
		verify(post, times(1)).setDate();
		verify(post, times(1)).setImgSrc(postImgSrcMock);
		verify(postDao, times(1)).update(post);
		assertTrue(isUpdatedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#updateNews(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public final void testUpdateNewsWhenPostNull() {
		boolean isUpdatedPost = true;
		when(postDao.findById(postIdMock)).thenReturn(null);
		isUpdatedPost = postManager.updateNews(postIdMock, postTitleMock,
				postDescriptionMock, postImgSrcMock);
		verify(postDao, times(1)).findById(postIdMock);
		verify(post, times(0)).setDescription(postDescriptionMock);
		verify(post, times(0)).setTitle(postTitleMock);
		verify(post, times(0)).setDate();
		verify(post, times(0)).setImgSrc(postImgSrcMock);
		verify(postDao, times(0)).update(post);
		assertFalse(isUpdatedPost);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#updateNews(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testUpdateNewsExceptionSecondValue() {
		doThrow(new IllegalArgumentException()).when(post).setTitle(
				emptyArgString);
		postManager.updateNews(postIdMock, emptyArgString, postDescriptionMock,
				postImgSrcMock);
		verify(postDao, times(1)).findById(postIdMock);
		verify(post, times(0)).setDescription(postDescriptionMock);
		verify(post, times(1)).setTitle(emptyArgString);
		verify(post, times(1)).setDate();
		verify(post, times(0)).setImgSrc(postImgSrcMock);
		verify(postDao, times(0)).update(post);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#updateNews(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testUpdateNewsExceptionThirdValue() {
		doThrow(new IllegalArgumentException()).when(post).setDescription(
				emptyArgString);
		postManager.updateNews(postIdMock, postTitleMock, emptyArgString,
				postImgSrcMock);
		verify(postDao, times(1)).findById(postIdMock);
		verify(post, times(1)).setDescription(emptyArgString);
		verify(post, times(1)).setTitle(postTitleMock);
		verify(post, times(1)).setDate();
		verify(post, times(0)).setImgSrc(postImgSrcMock);
		verify(postDao, times(0)).update(post);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#updateNews(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testUpdateNewsExceptionBothValue() {

		doThrow(new IllegalArgumentException()).when(post).setTitle(
				emptyArgString);
		doThrow(new IllegalArgumentException()).when(post).setDescription(
				emptyArgString);
		postManager.updateNews(postIdMock, emptyArgString, emptyArgString,
				postImgSrcMock);
		verify(postDao, times(1)).findById(postIdMock);
		verify(post, times(0)).setDescription(emptyArgString);
		verify(post, times(1)).setTitle(emptyArgString);
		verify(post, times(1)).setDate();
		verify(post, times(0)).setImgSrc(postImgSrcMock);
		verify(postDao, times(0)).update(post);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostListCount()}
	 * .
	 */
	@Test
	public final void testGetPostListCount() {
		long expected = 25;
		when(postDao.getPostListCount()).thenReturn(expected);

		long actual = postManager.getPostListCount();
		verify(postDao, times(1)).getPostListCount();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostListCount()}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetPostListCountException() {
		when(postDao.getPostListCount()).thenThrow(new RuntimeException());
		postManager.getPostListCount();
		verify(postDao, times(1)).getPostListCount();
	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostForPage(int, int)}
	 * .
	 */
	@Test
	public final void testGetPostForPage() {
		int from = 10;
		int count = 10;
		List<Post> expected = new ArrayList<Post>();
		when(postDao.getPostForOnePage(from, count)).thenReturn(expected);
		List<Post> actual = postManager.getPostForPage(from, count);
		verify(postDao, times(1)).getPostForOnePage(from, count);
		assertEquals(expected, actual);

	}

	/**
	 * Test method for
	 * {@link com.ita.edu.softserve.manager.impl.PostForMainPageManagerImpl#getPostForPage(int, int)}
	 * .
	 */
	@Test(expected = RuntimeException.class)
	public final void testGetPostForPageException() {
		int from = 10;
		int count = 10;
		when(postDao.getPostForOnePage(from, count)).thenThrow(
				new RuntimeException());
		postManager.getPostForPage(from, count);
		verify(postDao, times(1)).getPostForOnePage(from, count);

	}
}

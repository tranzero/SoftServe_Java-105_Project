package com.ita.edu.softserve.entity;

import java.util.Date;

import static org.apache.commons.lang.Validate.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ibm.icu.text.SimpleDateFormat;

@Entity
@Table(name = "posts")
@NamedQueries(value = {
		@NamedQuery(name = Post.FIND_POST_LIST_COUNT, query = Post.FIND_POST_LIST_COUNT_QUERY),
		@NamedQuery(name = Post.FIND_POST_LIST_FOR_PAGING, query = Post.FIND_POST_LIST_FOR_PAGING_QUERY) })
	public class Post extends BaseEntity {
	public static final String FIND_POST_LIST_COUNT = "Post.findPostListCount";
	public static final String FIND_POST_LIST_COUNT_QUERY = "SELECT COUNT (news.postId) from Post news";

	public static final String FIND_POST_LIST_FOR_PAGING = "Post.findPostListForPaging";
	public static final String FIND_POST_LIST_FOR_PAGING_QUERY = "SELECT news from Post news ORDER BY news.date DESC";
	

	@Id
	@Column(name = "POSTID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;

	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	@Column(name = "DESCRIPTION", nullable = false, length = 1000)
	private String description;

	@Column(name = "DATE", nullable = true, length = 60)
	@GeneratedValue
	private Date date;
	
	@Column(name = "IMGSRC", nullable = true, length = 60)
	private String imgSrc;

	

	public Post() {
		this.setDate();
	}

	public Post(String title, String description, String imageSrc)
			throws IllegalArgumentException {
		this();
		this.setTitle(title);
		this.setDescription(description);
		this.setImgSrc(imageSrc);

	}

	/**
	 * @return the postId
	 */
	public Integer getPostId() {
		return postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		notEmpty(title);
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		notEmpty(description);
		this.description = description;
	}

	public String getDate() {
		String formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
		.format(this.date);
		return formatDate;
	}

	public void setDate() {
		this.date = new Date();
	}
	
	/**
	 * @return the imgSrc
	 */
	public String getImgSrc() {
		return imgSrc;
	}

	/**
	 * @param imgSrc the imgSrc to set
	 */
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

}

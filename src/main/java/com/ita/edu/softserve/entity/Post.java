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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.ibm.icu.text.SimpleDateFormat;

@Entity
@Table(name = "posts")
@NamedQueries(value = {
		@NamedQuery(name = Post.FIND_POST_LIST_COUNT, query = Post.FIND_POST_LIST_COUNT_QUERY),
		@NamedQuery(name = Post.FIND_BY_TITLE, query = Post.FIND_BY_TITLE_QUERY),
		@NamedQuery(name = Post.FIND_POST_LIST_FOR_PAGING, query = Post.FIND_POST_LIST_FOR_PAGING_QUERY) })
	public class Post extends BaseEntity {
	public static final String FIND_POST_LIST_COUNT = "Post.findPostListCount";
	public static final String FIND_POST_LIST_COUNT_QUERY = "SELECT COUNT (news.postId) from Post news";

	public static final String FIND_POST_LIST_FOR_PAGING = "Post.findPostListForPaging";
	public static final String FIND_POST_LIST_FOR_PAGING_QUERY = "SELECT news from Post news ORDER BY news.date DESC";
	
	public static final String FIND_BY_TITLE = "Post.findByTitle";
    public static final String FIND_BY_TITLE_QUERY = "SELECT news from Post news WHERE news.title= ?1";
	

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
		String pattern = "dd-MM-yyyy HH:mm:ss";
		
		String formatDate = new SimpleDateFormat(pattern)
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
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(postId).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;

		return new EqualsBuilder().append(postId, other.postId).isEquals();
	}

}

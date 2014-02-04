package com.ita.edu.softserve.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity{
	@Id
	@Column(name = "POSTID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 1000)
	private String description;
	
	@Column(name = "DATE", nullable = true)
	private Date date;
	
	public Post(){
		this.setDate();
	}
	
	public Post(String title, String description) throws IllegalArgumentException
	{
		this();
		org.apache.commons.lang.Validate.notEmpty(title);
		org.apache.commons.lang.Validate.notEmpty(description);
		this.setTitle(title);
		this.setDescription(description);

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
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate() {
		this.date = new Date();
	}
	


}

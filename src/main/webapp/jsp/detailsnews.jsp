<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
		<div>
		<p>News Title</p>
		<p>${News.getTitle()}</p>	
		<p>News Description</p>
		<p>${News.getDescription()}</p>	
		<p>News Date update</p>
		<p>${News.getDate()}</p>		
		</div>
		<br>
	
</section>	
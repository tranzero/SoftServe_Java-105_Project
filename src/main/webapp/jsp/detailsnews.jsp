<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section id="content">
	<div id="details_news">
		<div class="news_title_details">${News.getTitle()}</div>
		<div class="news_date_details">${News.getDate()}</div>
		<div class="news_description_details">${News.getDescription()}</div>
	</div>
</section>

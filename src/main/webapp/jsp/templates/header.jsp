<%@page contentType = "text/html" pageEncoding = "UTF-8" %>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<header id = "header">
	<div id="logo">
		<h1><spring:message code="label.header.title"/>[WIP]</h1>
	</div>
	<div id="login">
		<ul>
			<li><a href="">Login</a></li>
			<li>|</li>
			<li><a href="">Register</a></li>
		</ul>
	</div>
	<div id="language">
		<ul>
			<li><span class="flag flag-ua"></span><a href="?lang=ua">ua</a></li>
			<li><span class="flag flag-en"></span><a href="?lang=en">en</a></li>
		</ul>
	</div>
		<div id="social">
		<ul>
			<li><a href="#"><span class="icon fb_icon"></span></a></li>
			<li><a href="#"><span class="icon vk_icon"></span></a></li>
			<li><a href="#"><span class="icon rss_icon"></span></a></li>
		</ul>
	</div>
</header>
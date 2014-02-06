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
			<li><img class="flag flag-ua" /><a href="?lang=ua">ua</a></li>
			<li><img class="flag flag-en" /><a href="?lang=en">en</a></li>
		</ul>
<%--
		<div class="lang_ua"><a href="?lang=ua">ua</a></div>
		<div class="lang_en"><a href="?lang=en">en</a></div>
--%>
	</div>
</header>
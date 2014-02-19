<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<header id="header">
	<div id="container">
		<div id="logo">
			<h2>Just go. To travel is to live...</h2>
		</div>
		<div id="login">
			<ul>
				<sec:authorize access="isAuthenticated()">
					<li><p>
							Hello,
							<sec:authentication property="principal.username" />
						</p></li>
					<li><a href="<c:url value="j_spring_security_logout" />"><spring:message
								code="label.header.logout" /></a></li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li><a href="login"><spring:message
								code="label.header.login" /></a></li>
					<li>|</li>
					<li><a href="registration"><spring:message
								code="label.header.register" /></a></li>
				</sec:authorize>
			</ul>
		</div>
		<div id="language">
			<ul>
				<li><span class="flag flag-ua"></span><a href="?lang=ua">ua</a></li>
				<li><span class="flag flag-en"></span><a href="?lang=en">en</a></li>
				<li><span class="flag flag-es"></span><a href="?lang=es">es</a></li>
			</ul>
		</div>
		<div id="social">
			<ul>
				<li><a href="#"><span class="icon fb_icon"></span></a></li>
				<li><a href="#"><span class="icon twitter_icon"></span></a></li>
				<li><a href="#"><span class="icon rss_icon"></span></a></li>
			</ul>
		</div>
	</div>
</header>
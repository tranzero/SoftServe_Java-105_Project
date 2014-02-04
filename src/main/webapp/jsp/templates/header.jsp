<%@page contentType = "text/html" pageEncoding = "UTF-8" %>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div id = "header"><h1><spring:message code="label.header.title"/>[WIP]</h1>
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
</div>
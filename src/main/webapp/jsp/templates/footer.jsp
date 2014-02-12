<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<footer id="footer">
	<div id="footer_primary">
		<div id="footer_col">
			<ul>
				<li class="footer_col_title">SoftServe</li>
				<li></li>
				<li><a href="http://softserve.ua/university/it-academy/"><span class="ss_icon"></span></a>SoftServe IT Acatemy</li>
			</ul>
		</div>
		<div id="footer_col">
			<ul>
				<li class="footer_col_title">GitHub</li>
				<li></li>
				<li><a href="https://github.com/tranzero/SoftServe_Java-105_Project/"><span class="github_icon"></span></a>Source Code</li>
			</ul>
		</div>
		<div id="footer_col">
			<ul>
				<li class="footer_col_title">Help and Support</li>
				<li></li>
				<li>Contact Support</li>
				<li>About</li>
			</ul>
		</div>
		<div id="footer_col">
			<ul>
				<li class="footer_col_title">Contact Us</li>
				<li></li>
				<li>1234 Fictional Road Suite #5432</li>
				<li>Nashville, Tennessee 00000-0000</li>
				<li>(800) 555-0000</li>
			</ul>
		</div>
	</div>
	<div id="footer_secondary">
		<div id="footer_first_col">&copy; Copyright LV-105 Java. All rights reserved. </div>
		<div id="footer_second_col">
			<spring:message code="label.footer.title" />
		</div>
	</div>
</footer>
<%--
<div id = "footer" >
<h2><spring:message code="label.footer.title"/>[WIP]</h2>
</div>
--%>
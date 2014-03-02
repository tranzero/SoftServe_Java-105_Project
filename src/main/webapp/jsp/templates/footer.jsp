<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<footer id="footer">
	<div id="footer_primary">
		<div id="container">
			<div id="footer_col">
				<ul>
					<li class="footer_col_title">SoftServe</li>
					<li></li>
					<li class="ss_block"><a
						href="http://softserve.ua/university/it-academy/"><span
							class="ss_icon"></span><spring:message code="label.ssItAcademy" /></a></li>
				</ul>
			</div>
			<div id="footer_col">
				<ul>
					<li class="footer_col_title">GitHub</li>
					<li></li>
					<li class="github_block"><a
						href="https://github.com/tranzero/SoftServe_Java-105_Project/"><span
							class="github_icon"></span> <spring:message
								code="label.sourceCode" /></a></li>
				</ul>
			</div>
			<div id="footer_col">
				<ul>
					<li class="footer_col_title"><spring:message
							code="label.helpAndSupport" /></li>
					<li></li>
					<li><a href="#"><spring:message code="label.contactSupport" /></a></li>
					<li><a href="#"><spring:message code="label.about" /></a></li>
				</ul>
			</div>
			<div id="footer_col">
				<ul>
					<li class="footer_col_title"><spring:message
							code="label.contactUs" /></li>
					<li></li>
					<li>1234 Fictional Road Suite #5432</li>
					<li>Nashville, Tennessee 00000-0000</li>
					<li>(800) 555-0000</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="footer_secondary">
		<div id="container">
			<div id="footer_first_col">
				&copy; Copyright LV-105 Java.
				<spring:message code="label.copyright" />
			</div>
			<div id="footer_second_col">
				<a href="#"><span class="icon_f tw_icon_f"></span></a>
				<a href="#"><span class="icon_f fb_icon_f"></span></a>
				<a href="#"><span class="icon_f rss_icon_f"></span></a>
			</div>
		</div>
	</div>
</footer>
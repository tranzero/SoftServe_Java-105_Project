<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<aside id="sidebar">
	<div class="widget">
		<link rel="stylesheet" type="text/css"
			href="http://www.gismeteo.ua/static/css/informer2/gs_informerClient.min.css">
		<div id="gsInformerID-J4oIPmP4S2h7b1" class="gsInformer"
			style="width: 200px; height: 241px">
			<div class="gsIContent">
				<div id="cityLink">
					<a href="http://www.gismeteo.com/city/daily/4949/" target="_blank">Weather
						in Lviv</a>
				</div>
				<div class="gsLinks">
					<table>
						<tr>
							<td>
								<div class="leftCol">
									<a href="http://www.gismeteo.com" target="_blank"> <img
										alt="Gismeteo" title="Gismeteo"
										src="http://www.gismeteo.ua/static/images/informer2/logo-mini2.png"
										align="absmiddle" border="0" /> <span>Gismeteo</span>
									</a>
								</div>
								<div class="rightCol">
									<a href="http://www.gismeteo.com/city/weekly/4949/"
										target="_blank">2-weeks forecast</a>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<script
			src="http://www.gismeteo.ua/ajax/getInformer/?hash=J4oIPmP4S2h7b1"
			type="text/javascript"></script>
	</div>
	<div class="widget">
		<div id='kurs-com-ua-informer-main-ukraine-280x130-blue-container'>
			<a href='http://ua.kurs.com.ua/'
				id='kurs-com-ua-informer-main-ukraine-280x130-blue' target='_blank'>ua.kurs.com.ua</a>
		</div>
		<script type='text/javascript'>
			(function() {
				var rnd = +new Date;
				var iframe = '<ifr'
						+ 'ame src="http://ua.kurs.com.ua/informer/inf2?color=blue&rnd='
						+ rnd
						+ '" width="200" height="130" frameborder="0" vspace="0" scrolling="no" hspace="0"></ifr'+'ame>';
				var container = document
						.getElementById('kurs-com-ua-informer-main-ukraine-280x130-blue');
				container.parentNode.innerHTML = iframe;
			})();
		</script>
		<noscript>
			<img src='http://ua.kurs.com.ua/static/images/informer/kurs.png'
				width='52' height='26' border='0' />
		</noscript>
	</div>
</aside>

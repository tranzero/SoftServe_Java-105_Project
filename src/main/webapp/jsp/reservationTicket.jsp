<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="content">

	<form:form id="addtobag"
		action="/SoftServe_Java-105/addToBag/${tripId}/${seatType}"
		modelAttribute="ticket" method="post">

		<table class="form">
			<tr>
				<td align=center><label> Enter first name: </label>
				<td><form:input path="customerFirstName" /></td>
				<td><form:errors path="customerFirstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label> Enter last name: </label></td>
				<td><form:input path="customerLastName" /></td>
				<td><form:errors path="customerLastName" cssClass="error" /></td>
			</tr>
		</table>


		<div id="div1" class="list" style="display: none;">
			<p>

				<label><strong>${trip.getRemSeatClass1()}</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<div id="div2" class="list" style="display: none;">
			<p>
				<label><strong>${trip.getRemSeatClass2()}</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<div id="div3" class="list" style="display: none;">
			<p>
				<label><strong>${trip.getRemSeatClass3()}</strong> - free
					spaces</label><br /> Price : <strong>${transport.getGenPrice()}</strong>
		</div>
		<br>

		<table>
			<tr align=center>
				<td align=center><br /> <a
					href="/SoftServe_Java-105/listOfStations/${trip.getTransport().getRoutes().getLineId().getLineName()}">
						<label style="color: #0000FF; font-size: 12pt">Show
							stations on Route </label>
				</a>
		</table>

		<table>
			<tr align=center>
				<td align=center>
					<p>
						<label> Route name <strong>${trip.getTransport().getRoutes().getRouteName()}</strong></label>
					</p> <br>
					<p>
						<label> Trip date <strong>${trip.getStartDate().toString().substring(0, 10)}</strong></label>
					</p> <br>
					<p>
						<label> Start time <strong>${trip.getTransport().getStartTime().toString()}</strong></label>
					</p>
					<br>
					<p>
						<label> Price <strong>${trip.getTransport().getGenPrice()}</strong></label>

					</p>
		</table>
		<div class="pad_top_bottom">
			<input type="submit" value="Add to bag">
			<!-- 	<input type="reset" value="Cancel"> -->
		</div>
	</form:form>

</section>

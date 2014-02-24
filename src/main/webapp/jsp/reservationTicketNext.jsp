<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h2 align="center">Input data</h2>
	<hr />

	<form id="reservationInputData" method="get"
		name="/reservationTicketNext" action="reservTicket"
		style="text-align: center">
		<table>
			<tr align=center>
				<td align=center>
					<p>
						FirstName: <input id="firstName" type="text"
							name="firstName"><br> 
							<p>
						LastName: <input id="lastName" type="text" name="lastName"><br>
						<p>
						Email: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="email"><br>
						<p>
						Phone: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="phone"><br>
						<p>
						Credit Cart Number: <input type="text" name="credit_card_number">
						<p>
					<p>
						<input type="submit" value="Buy"> <input type="button"
							value="<spring:message code="label.stations.cancel"/>"
							onclick="window.location='reservationTicket';">
					</p>
				</td>
			</tr>
		</table>
	</form>
</section>
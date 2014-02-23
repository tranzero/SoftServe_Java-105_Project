<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
div.container {
	width: 100%;
	margin: 0px;
	border: 1px solid gray;
	line-height: 150%;
}

div.header,div.footer {
	padding: 0.5em;
	color: white;
	background-color: gray;
	clear: left;
}

h1.header {
	padding: 0;
	margin: 0;
}

div.left {
	float: left;
	width: 160px;
	margin: 0;
	padding: 1em;
}

div.content {
	margin-left: 190px;
	border-left: 1px solid gray;
	padding: 1em;
}
</style>
<section id="content">
	<h2>Ticket Reservation</h2>
	<form:form action="reservationTicket" method="get">
		<div class="container">
					<div class="header">
						<h1 class="header">Ticket Booking</h1>
					</div>
					<div class="left">
						<p>
							<strong>SeatClass №1 :</strong> 
							Amount of free space : <strong>${trip.getRemSeatClass1()}</strong><br />
							Price : <strong>${transport.getGenPrice()}</strong>
							
						</p>
					</div>

					<div class="left">
						<p>
							<strong>SeatClass №2 :</strong> 
							Amount of free space : <strong>${trip.getRemSeatClass2()}</strong><br />
							Price : <strong>${transport.getGenPrice()}</strong>
						</p>
					</div>
					<div class="left">
						<p>
							<strong>SeatClass №3 :</strong> 
							Amount of free space : <strong>${trip.getRemSeatClass3()}</strong><br />
							Price : <strong>${transport.getGenPrice()}</strong>
						</p>
					</div>
				</div>
	</form:form>

</section>

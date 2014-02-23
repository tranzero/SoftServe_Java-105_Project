<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="content">
	<h2>Choose Class-Seat</h2>
	<form:form action="reservationTicket" method="get">
		<tr>
			<td valign=bottom><label><input type="radio"
					name="group1"> <strong>SeatClass №1 :</strong> <br /> </label>Amount
				of free space : <strong>${trip.getRemSeatClass1()}</strong><br />
				Price : <strong>${transport.getGenPrice()}</strong></td>
		</tr>
		<br />
		<tr>
			<td valign=bottom><label><input type="radio"
					name="group1" /> <strong>SeatClass №2 :</strong> <br /></label>Amount of
				free space : <strong>${trip.getRemSeatClass2()}</strong><br />
				Price : <strong>${transport.getGenPrice()}</strong></td>
		</tr>
		<br />
		<tr>
			<td valign=bottom><label><input type="radio"
					name="group1" /> <strong>SeatClass №3 :</strong> <br /> </label> Amount
				of free space : <strong>${trip.getRemSeatClass3()}</strong><br />
				Price : <strong>${transport.getGenPrice()}</strong></td>
		</tr>
		<br />

		<table cellspacing=0 cellpadding=5 bgcolor="tan">
			<tr>
				<td align=center>FirstName: <input type="text"
					name="shopper_name" size=44><br> LastName: <input
					type="text" name="shopper_name" size=44><br>
					Email:&nbsp;&nbsp;&nbsp; <input type="text" size=44 name="email"><br>
					Phone-Number:&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" size="20"
					name="phone"><br> Credit card number: <input
					type="text" size="20" name="credit_card_number"> <br>
				</td>
			</tr>

		</table>
		<br>
		<input type="submit" name="submit" value="Make a booking">&nbsp
 		<input type="reset" value="Cancel">
		<br>
	</form:form>

</section>

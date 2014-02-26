<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script>
	function toggle_visibility(id) {
		var e = document.getElementById(id);
		if (e.style.display == 'block')
			e.style.display = 'none';
		else
			e.style.display = 'block';

		hideAllBut(id);
	}

	function hideAllBut(id) {
		var lists = document.querySelectorAll('.list');
		for (var i = lists.length; i--;) {
			if (lists[i].id != id) {
				lists[i].style.display = 'none';
			}
		}
	}
</script>
<section id="content">

		
	<h2>Choose Class-Seat</h2>
	<form:form id = "add to bag" action= "addToBag" method="post" modelAttribute = "ticket">
	
	<table>
			<tr align=center>
				<td align=center>
					<p>
						Enter passenger first and last name: <form:input path="customerInfo"></form:input> <br> 
					
		</table>
		
		< <table>
			<tr>
				<td><b>Choose Seat-Class you want : </b> 
				<Br> <form:radiobutton path="isSeatClass1" value="true"
				 onchange="toggle_visibility('div1');"/> 
				<strong>SeatClass№1</strong> 
				<Br> <form:radiobutton path="isSeatClass2" value="true"
				 onchange="toggle_visibility('div2');"/> 
				<strong>SeatClass№1</strong> 
				<Br> <form:radiobutton path="isSeatClass3" value="true"
				 onchange="toggle_visibility('div3');"/> 
				<strong>SeatClass№1</strong> 
					
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
				<td align=center>
					<p>
						<label> Route name <strong>${trip.getTransport().getRoutes().getRouteName()}</strong></label></p>
						<br>
							<p>
						<label> Trip date <strong>${trip.getStartDate()}</strong></label></p>
						<br>
						<p>
						<label> Price <strong>${trip.getTransport().getGenPrice()}</strong></label></p>
						
		</table>
						<!--  <a href="/SoftServe_Java-105/bag"> <input type="BUTTON" value ="Add to bag"></a>&nbsp  -->
 		<input type="submit" value="Add to bag">
 	<!-- 	<input type="reset" value="Cancel"> -->
		<br>
	</form:form>

</section>

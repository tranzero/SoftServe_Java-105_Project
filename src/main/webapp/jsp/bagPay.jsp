<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title>Checkout</title>
<section id="content">

	<form:form id="paying" action="/SoftServe_Java-105/bagPay" method="post">
		<table>
			<tbody>
				<tr>
					<td align="center">Credit Cart Number:</td>
					<td align="center"><input id="firstName" name="firstName" ></td>
					<td align="center"></td>
				</tr>
				<tr>
					<td align="center">Card holder last Name:</td>
					<td align="center"><input id="lastName" name="lastName" ></td>
					<td align="center"></td>
				</tr>
				<tr>
					<td align="center">Card holder billing adress:</td>
					<td align="center"><input name="adress" ></td>
					<td align="center"></td>
				</tr>
				<tr>
					<td align="center">Security code:</td>
					<td align="center"><input name="securityCode" ></td>
					<td align="center"></td>
				</tr>
				<tr>
					<td></td>
					<td align="center" colspan="4"><input type="submit"
						value="Pay"></td>
				</tr>
			</tbody>
		</table>
	</form:form>

</section>
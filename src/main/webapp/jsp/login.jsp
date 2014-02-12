<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div>	
	<c:if test='${not empty param.error}'> 
	  <font color='red'> 
	    <spring:message code="label.Login.loginerror" /><br /> 
	    <spring:message code="label.login.reason" />${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message} 
	  </font> 
	</c:if>  
	<form name='loginForm' action="<c:url value="/j_spring_security_check"/>" method='POST'>	
		<table>
          <tr>
              <td align="right"><spring:message code="label.login.username" /></td>
              <td width="50%"><input type="text" id="username" placeholder="Username" name='j_username' value='' /></td>
          </tr>
          <tr>
              <td align="right"><spring:message code="label.login.password" /></td>
              <td width="50%"><input type="text" id="password" placeholder="Password" name='j_password' /></td>
          </tr>
          <tr>
              <td align="right">
                  <input type="submit" name="submit" value="Login" class="btn" />                                  
              </td>
              <td align="left">
                  <a href='mainpage'><input type="button" name="cancel" value="Cancel"></a>                                  
              </td>
         <tr>
			<td align="right"><spring:message code="label.login.remember" /></td>
			<td width="45%">
			 	<input type="checkbox" name="_spring_security_remember_me"/>
			</td>
		</tr>          
      </table>	
	</form>	
</div>
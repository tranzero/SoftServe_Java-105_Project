<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div>	
	<c:if test='${not empty param.error}'> 
	  <font color='red'> 
	    Login error. <br /> 
	    Reason : ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message} 
	  </font> 
	</c:if>  
	<form name='loginForm' action="<c:url value="/j_spring_security_check"/>" method='POST'>	
		<table>
          <tr>
              <td>Username:</td>
              <td><input type="text" id="username" placeholder="Username" name='j_username' value='' /></td>
          </tr>
          <tr>
              <td>Password:</td>
              <td><input type="text" id="password" placeholder="Password" name='j_password' /></td>
          </tr>
          <tr>
              <td colspan="2">
                  <input type="submit" name="submit" value="Login" class="btn" />                
              </td>
         <tr>
			<td>Remember Me</td>
			<td>
			 	<input type="checkbox" name="_spring_security_remember_me"/>
			</td>
		</tr>          
      </table>	
	</form>	
</div>
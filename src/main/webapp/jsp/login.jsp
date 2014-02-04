<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>	
	<form name='loginForm' action="login" method='POST'>	
		<table>
          <tr>
              <td>Username:</td>
              <td><input type="text" id="username" placeholder="Username" name='username' value='' /></td>
          </tr>
          <tr>
              <td>Password:</td>
              <td><input type="text" id="password" placeholder="Password" name='password' /></td>
          </tr>
          <tr>
              <td colspan="2">
                  <input type="submit" name="submit" value="Login" class="btn" />
               <!--    <button type="submit" class="btn">Cancel</button>--> 
              </td>
         <!-- <tr>
			<td>Remember Me</td>
			<td>
			 	<input type="checkbox" name="_spring_security_remember_me"/>
			</td>
		</tr>    -->
          
      </table>	
	</form>
	
	
</div>
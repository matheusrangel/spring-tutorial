<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	<h4>Login</h4>
	
	<c:if test="${param.error != null}">
		<p class="error">Login failed. Check that your username and password are correct.</p>	
	
	</c:if>
	
	<form name='f' action='${pageContext.request.contextPath}/login'
		method='POST' class="col s12">
		
		<div class="row">
			<div class="input-field col s12">
				<input type='text' id='username' name='username' value='' class="validate">
				<label for="username">Username</label>	
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<input type='password' id='password' name='password' class="validate">
				<label for="password">Password</label>		
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<input type='checkbox' id='remember-me' name='remember-me' class="validate">
				<label for="remember-me">Remember Me</label>		
			</div>
		</div>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<button class="btn waves-effect waves-light" type="submit" name="action">Login
   			 <i class="material-icons right">send</i>
  		</button>
	</form>
	
	<p><a href='<c:url value="/newaccount" />'>Create new account</a>


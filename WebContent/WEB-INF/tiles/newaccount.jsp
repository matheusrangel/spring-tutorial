<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<h4>Create New Account</h4>

	<form:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user" class="col s12">
		
		
		<div class="row">
			<div class="input-field col s12">
				<form:input type='text' id='username' name='username' path="username" class="validate" />
				<label for="username">Username</label>
				<div class="error"><form:errors path="username" /></div>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input type='text' id='name' name='name' path="name" class="validate" />
				<label for="name">Name</label>
				<div class="error"><form:errors path="name" /></div>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input type='text' id='email' name='email' path="email" class="validate" />
				<label for="email">Email</label>
				<div class="error"><form:errors path="email" /></div>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input type='password' id='password' name='password' path="password" class="validate" />
				<label for="password">Password</label>
				<div class="error"><form:errors path="password" /></div>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<input type='password' id='confirmpass' name='confirmpass' class="validate" />
				<label for="confirmpass">Confirm Password</label>
				<div id="matchpass"></div>
			</div>
		</div>
		
		<button class="btn waves-effect waves-light" type="submit" name="action">Create account
   			 <i class="material-icons right">send</i>
  		</button>
		
	</form:form>

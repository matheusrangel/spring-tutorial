<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<h4>Send Message</h4>
	
	<form:form method="post" commandName="message" class="col s12">
	
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
		<input type="hidden" name="_eventId" value="send" />
		
		<div class="row">
			<div class="input-field col s12">
				<form:input type='text' id='name' name='name' path="name" class="validate" value="${fromName}" />
				<label for="name">Your name</label>
				<div class="error"><form:errors path="name" /></div>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input type='text' id='email' name='email' path="email" class="validate" value="${fromEmail}" />
				<label for="email">Your email</label>
				<div class="error"><form:errors path="email" /></div>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:input type='text' id='subject' name='subject' path="subject" class="validate" />
				<label for="subject">Subject</label>
				<div class="error"><form:errors path="subject" /></div>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:textarea type='text' id='content' name='content' path="content" class="validate materialize-textarea" />
				<label for="content">Content</label>
				<div class="error"><form:errors path="content" /></div>
			</div>
		</div>
	
		<button class="btn waves-effect waves-light" type="submit" name="action">Send
   			 <i class="material-icons right">send</i>
  		</button>
	
	
	</form:form>

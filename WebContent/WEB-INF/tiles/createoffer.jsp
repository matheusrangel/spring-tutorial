<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	
	function deleteConfirm(event) {
		if (!confirm("Are you sure you want to delete this offer?")) {
			event.preventDefault();
		}
	}
	
	function onReady() {
		$("#delete").click(deleteConfirm);
	}
	
	$(document).ready(onReady);
</script>

<h4>Offer</h4>

<form:form method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="offer" class="col s12">
		<form:input type="hidden" name="id" path="id" />
		
		<div class="row">
			<div class="input-field col s12">
				<form:textarea type='text' id='text' name='text' path="text" class="validate materialize-textarea" />
				<label for="text">Text</label>
				<div class="error"><form:errors path="text" /></div>
			</div>
		</div>
		
		<button class="btn waves-effect waves-light" type="submit" name="action">Save
   			 <i class="material-icons right">send</i>
  		</button>
  		
  		<c:if test="${offer.id != 0}">
			<button class="btn waves-effect waves-light red" id="delete" type="submit" name="delete">
   			 	<i class="material-icons">delete</i>
  			</button>
		</c:if>
		
	</form:form>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


			
	<!------------- OPTION 1 ------------>
	<sec:authorize access="hasRole('ADMIN')">
		<li><a href="<c:url value='/admin' />">Admin</a></li>
	</sec:authorize>
			
	<!------------- OPTION 2 ------------>
	<sec:authorize access="isAuthenticated()">
		<li><a href="<c:url value='/messages' />"><i class="material-icons left">message</i> (<span id="numberMessages">0</span>)</a></li>
	</sec:authorize>
			
	<!------------- OPTION 3 ------------>
	<sec:authorize access="!isAuthenticated()">
		<li>
			<a href="<c:url value='/login' />">Login</a>
		</li>
	</sec:authorize>
			
	<!------------- OPTION 4 ------------>
	<sec:authorize access="isAuthenticated()">
		<li>
			<c:url var="logoutUrl" value="/logout"/>
			<form action="${logoutUrl}" id="formLogout" method="post">
    			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<a href="#" onclick="document.getElementById('formLogout').submit();"><i class="material-icons left">exit_to_app</i>Logout</a>
		</li>
	</sec:authorize>

			
	<script type="text/javascript">
			
		function updateMessageLink(data) {
			$("#numberMessages").text(data.number);
		}	
	
		function startScript() {
			updateCountMsg();
			window.setInterval(updateCountMsg, 3000);
		}
		
		function updateCountMsg() {
			$.getJSON("<c:url value="/getmessages" />", updateMessageLink);
		}
		
		$(document).ready(startScript);
		
	
	</script>
	
	
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	
	<table class="bordered highlight responsive-table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Offer</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="offer" items="${offers}">
				<tr>
					<td>
						<a href="<c:url value='/message?uid=${offer.username}' />">
							<c:out value="${offer.user.name}" />
						</a> 
					</td>
					<td><c:out value="${offer.text}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	

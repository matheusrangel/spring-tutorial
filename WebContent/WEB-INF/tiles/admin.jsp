<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Authorised Users Only</h3>

<table class="formtable">
	<tr>
		<th>Username</th>
		<th>Email</th>
		<th>Role</th>
		<th>Enabled</th>
	</tr>
	<c:forEach items="${users}" var="user">
		<tr>
			<td><c:out value="${user.username}" /></td>
			<td><c:out value="${user.email}" /></td>
			<td><c:out value="${user.authority}" /></td>
			<td><c:out value="${user.enabled}" /></td>
		</tr>
	</c:forEach>

</table>

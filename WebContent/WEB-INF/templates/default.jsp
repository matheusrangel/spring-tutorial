<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<title>
	<tiles:insertAttribute name="title" />
</title>

<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" text="text/css">
<link href="${pageContext.request.contextPath}/static/css/materialize.min.css" rel="stylesheet" text="text/css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/materialize.min.js"></script>

<tiles:insertAttribute name="includes" />


</head>
<body>
	
		<header>
			<tiles:insertAttribute name="toolbar" />
		</header>
		
		<main>
			<div class="container">
				<tiles:insertAttribute name="content" />
			</div>
			
			<div class="fixed-action-btn">
				<c:choose>
					<c:when test="${hasOffer}">
						<a class="btn-floating btn-large waves-effect waves-light red"
							href="${pageContext.request.contextPath}/create"><i
							class="large material-icons">mode_edit</i></a>
					</c:when>
					<c:otherwise>
						<a class="btn-floating btn-large waves-effect waves-light red"
							href="${pageContext.request.contextPath}/create"><i
							class="material-icons">add</i></a>
					</c:otherwise>
				</c:choose>
			</div>
			
		</main>
		
		<footer class="page-footer teal lighten-2">
			<tiles:insertAttribute name="footer" />
		</footer>
</body>
</html>
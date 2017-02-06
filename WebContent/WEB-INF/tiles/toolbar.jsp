<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<nav class="teal lighten-2"> <!------------- NAVBAR ------------>
	<div class="nav-wrapper">
		<a class="brand-logo center" href="<c:url value='/' />" >Offers</a>
		 <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
		<ul class="right hide-on-med-and-down">
			<tiles:insertAttribute name="menu_options" />
		</ul>
		<ul class="side-nav" id="mobile-demo">
			<tiles:insertAttribute name="menu_options" />
		</ul>
	</div>
</nav>


<script type="text/javascript">
$(document).ready(function(){
	$(".button-collapse").sideNav();
}); 
</script>

	
	
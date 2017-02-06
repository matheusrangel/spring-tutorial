<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

function onLoad() {
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpass").keyup(checkPasswordsMatch);
	
	$("#details").submit(canSubmit);
}

function canSubmit() {
	if ($("#password").val() != $("#confirmpass").val()) {
		alert("<fmt:message key='UnmatchedPasswords.user.password' />");
		return false;
	} else {
		return true;
	}
}

function checkPasswordsMatch() {
	var password = $("#password").val();
	var confirmpass = $("#confirmpass").val();
	
	if (password.length > 3 || confirmpass.length > 3) {
		if (password == confirmpass) {
			$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password' />");
			$("#matchpass").addClass("valid");
			$("#matchpass").removeClass("error");
		} else {
			$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password' />");
			$("#matchpass").addClass("error");
			$("#matchpass").removeClass("valid");
		}
	}
	
}

$(document).ready(onLoad);



</script>
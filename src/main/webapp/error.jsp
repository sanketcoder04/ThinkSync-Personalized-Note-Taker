<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>
</head>
<body style="background-color: #ECF39E">

	<div class="container text-center" 
		style="background-color: #ECF39E">
		<div class="row d-flex justify-content-center align-items-center"
			style="margin: 15px">
			<img class="img-fluid" 
				alt="error" 
				src="images/error.png"
				style="height: 400px; width: 450px">
		</div>
		<h2 style="color: #132A13">Some Unexpected Error Occurred!!!</h2>
		
		<%
		String error = (String) request.getAttribute("error");
		%>
		
		<c:choose>
			<c:when test="${error == null}">
				<h4 style="color: #31572C">Sorry! Something went wrong.</h4>
			</c:when>
			<c:otherwise>
				<h4 style="color: #31572C">
					<%= error %>
				</h4>
			</c:otherwise>
		</c:choose>


		<a class="btn btn-md" 
			href="index.jsp"
			style="background-color: #132A13;">
			<b style="color: #ECF39E">Home</b>
		</a>
		<a class="btn btn-md" 
			href="signup.jsp"
			style="background-color: #132A13;">
			<b style="color: #ECF39E">Sign Up</b>
		</a> 
		<a class="btn btn-md" 
			href="login.jsp"
			style="background-color: #132A13;">
			<b style="color: #ECF39E">Login</b>
		</a>
	</div>
</body>
</html>
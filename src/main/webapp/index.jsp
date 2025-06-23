<%@page import="thinksync.entities.User"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>
</head>
<%
User user = (User)session.getAttribute("user");
%>
<body>

	<!-- Including navigation bar page -->

	<%@ include file="navbar.html"%>

	<!-- Home Page Body -->
	
	<div class="container-fluid back" style="color: #132A13">
		<div class="container padding-top padding-bottom" style="color: #132A13">

			<!-- Application Introduction -->

			<div class="row padding-top" style="color: #132A13">
				<h1>
					Welcome to 
					<b style="color: #132A13">ThinkSync..</b>
				</h1>
				<h3 class="text-end" 
					style="color: #31572C">
					- Your Ultimate and Personalized Digital NoteBook
				</h3>
			</div>
			
			<br>
			
			<div class="row" style="color: #132A13">
				<p style="color: #132A13">
					We're thrilled to have you here! <b style="color: #132A13">ThinkSync</b>
					is not just another note-taking application. It's your personal,
					smart, and seam-less companion to organize thoughts, capture ideas,
					and manage your daily life with ease. Whether you're a student,
					professional, or someone who loves to jot down creative sparks, <b
						style="color: #132A13">ThinkSync</b> is tailored just for you.
				</p>
			</div>
			
			<br>

			<!-- User SignUp and Login Buttons -->
			
			<% if(user == null) { %>
				<a class="btn btn-lg" 
					href="signup.jsp" 
					role="button"
					style="background-color: #31572C; color: #ECF39E; margin-right: 3px;">
					Create Account
					<span class="icon-margin-left">
						<i class="fa-solid fa-user-plus"></i>
					</span>
				</a>
				<a class="btn btn-lg" 
					href="login.jsp" 
					role="button"
					style="background-color: #31572C; color: #ECF39E">
					Login
					<span class="icon-margin-left">
						<i class="fa-solid fa-arrow-right-to-bracket"></i>
					</span>
				</a>
			<% } else { %>
				<a class="btn btn-lg" 
					href="notes.jsp" 
					role="button"
					style="background-color: #31572C; color: #ECF39E; margin-right: 3px;">
					Create Note
					<span class="icon-margin-left">
						<i class="fa-solid fa-file-pen"></i>
					</span>
					</a>
					
				<a class="btn btn-lg" 
					href="profile.jsp" 
					role="button"
					style="background-color: #31572C; color: #ECF39E">
					View Profile
					<span class="icon-margin-left">
						<i class="fa-solid fa-user"></i>
					</span>
				</a>
			<% } %>
		</div>
		
		<br>
		
	</div>

	<!-- Including About and Social Page -->

	<%@ include file="about_part.html"%>
	
	<br>
	
	<%@ include file="social_part.html"%>
</body>
</html>
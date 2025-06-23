<%@ page import="java.util.List"%>
<%@ page import="thinksync.helper.SessionProvider"%>
<%@ page import="thinksync.database.FeedbackDatabase"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="thinksync.entities.Feedback"%>
<%@ page import="thinksync.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FeedBack</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>
</head>
<body>

	<%
		User user = (User) session.getAttribute("user");
		List<Feedback> feedbacks = new FeedbackDatabase(SessionProvider.getSession()).getFeedbacks();
	%>

	<!-- including navigation bar -->

	<%@ include file="navbar.html"%>

	<!-- including feedback part -->

	<div class="container">

		<!-- Button trigger modal -->
		
		<% if (user != null) { %>
			<div class="d-grid gap-2">
				<button type="button" 
					class="btn btn-lg margin-top"
					data-bs-toggle="modal" 
					data-bs-target="#exampleModal"
					style="color: #ECF39E; background-color: #132A13">
					Add Your FeedBack
					<span class="icon-margin-left">
						<i class="fa-solid fa-comment-dots"></i>
					</span>
				</button>
			</div>
		<% } %>

		<!-- Modal -->

		<div class="modal fade" 
			id="exampleModal" 
			tabindex="-1"
			aria-labelledby="exampleModalLabel" 
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" 
							id="exampleModalLabel"
							style="color: #132A13">
							Feedback Form :
						</h1>
						<button type="button" 
							class="btn-close" 
							data-bs-dismiss="modal"
							aria-label="Close">
						</button>
					</div>
					<div class="modal-body">
						<form action="feedback" 
							method="post">
							<div class="mb-3">
								<textarea class="form-control" 
									id="exampleFormControlTextarea1"
									rows="3" 
									name="feedback"
									placeholder="Enter Your Feedback"
									required="required"></textarea>
							</div>
							<p style="color: #132A13">
								After submitting the feedback you
								can't able to delete it. If we find something irrelevant or
								inappropriate then some decisions will be taken.
							</p>

							<button type="button" 
								class="btn btn-danger"
								data-bs-dismiss="modal">
								Discard
							</button>
							<button type="submit" 
								class="btn"
								style="color: white; background-color: #132A13">
								Submit
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">

		<!-- Feedback posts -->

		<% if (feedbacks == null) { %>
			<div class="container padding-top">
				<div class="card w-100 mb-3 padding-top margin-bottom back"
					style="border: none;">
					<h2 style="color: #132A13; text-align: center">No Feedbacks</h2>
				</div>
			</div>
		<% } else { %>
			<% for(Feedback feedback : feedbacks) { %>
				<div class="container padding-top">
					<div class="card w-100 mb-3 padding-top margin-bottom back"
						style="border: none;">
						<div class="card-body margin-top">
							<b class="card-title" 
								style="font-size: 25px; color: #132A13">
								<%=feedback.getUser().getName()%>
								<span class="fa fa-check-circle icon-margin-left" 
								style="color: green">
								</span>
							</b>
							<span style="color: #31572C"> 
								- <%=feedback.getUser().getOccupation()%>
							</span>
							
							<br>
							
							<b class="card-text" 
								style="color: #4F772D">
								<%=feedback.getFeedback()%>
							</b>
						
							<hr>
							
							<b style="color: #31572C">Date Posted : 
							<fmt:formatDate 
								value="<%= feedback.getPostedDate() %>" 
								pattern="dd/MM/yyyy" />
							</b>
						</div>
					</div>
				</div>
			<% } %>
		<% } %>
	</div> 
</body>
</html>
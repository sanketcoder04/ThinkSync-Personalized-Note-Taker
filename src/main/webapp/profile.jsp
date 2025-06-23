<%@ page import="thinksync.entities.Category"%>
<%@ page import="thinksync.entities.Note"%>
<%@ page import="java.util.List"%>
<%@ page import="thinksync.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	User user = (User) session.getAttribute("user");
	if(user == null) {
		response.sendRedirect("signup.jsp");
		return;
	}
	String facebook = user.getFacebookURL();
	String linkedin = user.getLinkedinURL();
	String x = user.getxURL();
	List<Note> notes = user.getNotes();
	List<Category> categories = user.getCategories();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile - <%= user.getName() %></title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>
</head>
<body>

	<!-- Including navigation bar page -->

	<%@ include file="navbar.html"%>

	<!-- Body Part -->

	<div class="container-fluid back">
		<div class="container">

			<!-- Profile Info -->

			<br>
			
			<div class="row">
			
				<!-- Image Column to show Image -->
				<div class="col-4 d-flex justify-content-center align-items-center"
					style="height: 300px; width: 300px;">
					<img src="images/image-user-removebg-preview.png">
				</div>
				
				<!-- Profile Info Column -->
				<div class="col-md-8">
					<div class="row">
						<div class="col-md-8">
							<h1 style="color: #132A13; text-align: center;">
								<%= user.getName() %>
								<span class="icon-margin-left"> 
									<i class="fa-solid fa-circle-check fa-2xs" style="color: green;"></i>
								</span>
							</h1>
						</div>
						<div class="col-4 text-center d-flex justify-content-start align-items-end">
							<h6 style="color: #31572C"> 
								- <%= user.getOccupation() %>
							</h6>
						</div>
					</div>

					<br>

					<div class="row">
						<h3 style="color: #132A13">About :</h3>
					</div>
					<div class="row">
						<h6 style="color: #31572C">
							<%= user.getAbout().replace("\n", "<br>") %>
						</h6>
					</div>

					<br>

					<div class="row" 
						style="margin-bottom: 5px">
						<b style="color: #132A13">
							Company/Organization : 
							<span style="color: #31572C">
								<%= user.getOrganization() %>
							</span>
						</b>
					</div>

					<div class="row">
						<b style="color: #132A13">
							Account Created On : 
							<span style="color: #31572C">
								<fmt:formatDate value="<%= user.getCreationDate() %>" pattern="dd/MM/yyyy" />
							</span>
						</b>
					</div>

					<br>

					<div class="row">
						<b style="color: #132A13">
							<% if(!facebook.equals("")) { %>
								<a href="<%= facebook %>" style="text-decoration: none">
									<span class="icon-margin-right icon-margin-left">
										<i class="fa-brands fa-square-facebook"
											style="color: #132A13; font-size: 30px">
										</i>
									</span>
								</a>
							<% } %>
							<% if(!linkedin.equals("")) { %>
								<a href="<%= linkedin %>" style="text-decoration: none">
									<span class="icon-margin-right icon-margin-left">
										<i class="fa-brands fa-linkedin"
											style="color: #132A13; font-size: 30px">
										</i>
									</span>
								</a>
							<% } %>
							<% if(!x.equals("")) { %>
								<a href="<%= x %>" style="text-decoration: none">
									<span class="icon-margin-right icon-margin-left">
										<i class="fa-brands fa-x-twitter"
											style="color: #132A13; font-size: 30px">
										</i>
									</span>
								</a>
							<% } %>
						</b>
					</div>
				</div>
			</div>

			<br>

			<!-- Buttons for Personal Use and Info -->

			<div class="row">
				<div class="col text-center d-grid gap-2">
					<a class="btn btn-lg"
						style="background-color: #31572C; color: #ECF39E; margin-right: 3px;"
						href="notes.jsp">
						MyNotes
						<span class="icon-margin-left">
							<i class="fa-solid fa-note-sticky"></i>
						</span>
					</a>
				</div>
				<div class="col text-center d-grid gap-2">
					<a class="btn btn-lg"
						style="background-color: #31572C; color: #ECF39E; margin-right: 3px;"
						href="category.jsp">
						MyCategories
						<span class="icon-margin-left">
							<i class="fa-solid fa-list-ul"></i>
						</span>
					</a>
				</div>
			</div>

			<hr style="color: #31572C">

			<!-- Buttons for Modifications -->

			<div class="row">
				<div class="col d-flex justify-content-center align-items-center">

					<!-- Logout Profile -->

					<form action="logout" method="post">
						<button class="btn btn-lg"
							style="background-color: #31572C; color: #ECF39E; margin-right: 3px;"
							type="submit">
							<span class="icon-margin-right">
								<i class="fa-solid fa-arrow-right-from-bracket"></i>
							</span>
							LogOut Profile
						</button>
					</form>
				</div>

				<div class="col d-flex justify-content-center align-items-center">

					<!-- Edit Profile -->

					<button type="button" 
						class="btn btn-lg margin-top"
						data-bs-toggle="modal" 
						data-bs-target="#exampleModal"
						style="background-color: #31572C; color: #ECF39E; margin-right: 3px;">
						<span class="icon-margin-right">
							<i class="fa-solid fa-gear"></i>
						</span>
						Edit Profile
					</button>
				</div>

				<div class="col d-flex justify-content-center align-items-center">

					<!-- Delete Profile -->

					<form action="deleteaccount" method="post">
						<button class="btn btn-lg"
							style="background-color: #31572C; color: #ECF39E; margin-right: 3px;"
							type="submit">
							<span class="icon-margin-right">
								<i class="fa-solid fa-trash-can"></i>
							</span>
							Delete Profile
						</button>
					</form>
				</div>
			</div>

			<hr style="color: #31572C">
			<br>

			<!-- Notes and Categories created -->

			<div class="row">
				<div class="col text-center">
					<b style="color: #4F772D">Total Notes Created</b>
					<% if(notes != null) { %>
						<h4 style="color: #31572C; margin-top: 5px"><%= notes.size() %></h4>
					<% } else { %>
						<h4 style="color: #31572C; margin-top: 5px">0</h4>
					<% } %>
				</div>
				<div class="col text-center">
					<b style="color: #4F772D">Total Categories Created</b>
					<% if(categories != null) { %>
						<h4 style="color: #31572C; margin-top: 5px"><%= categories.size() %></h4>
					<% } else { %>
						<h4 style="color: #31572C; margin-top: 5px">0</h4>
					<% } %>
				</div>
			</div>
		</div>
		<br>
	</div>

	<!-- Modal for Edit -->

	<div class="modal fade" 
		id="exampleModal" 
		tabindex="-1"
		aria-labelledby="exampleModalLabel" 
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header back">
					<h1 class="modal-title fs-5" 
						id="exampleModalLabel"
						style="color: #132A13">
						Edit Your Information :
					</h1>
					<button type="button" 
						class="btn-close" 
						data-bs-dismiss="modal"
						aria-label="Close">
					</button>
				</div>
				<div class="modal-body">
					<form action="editaccount" method="post">
						<div class="mb-3">
							<label for="name" 
								class="form-label" 
								style="color: #31572C">
								Name
							</label> 
							<input type="text" 
								class="form-control"
								id="exampleFormControlInput1"
								name="name" 
								value="<%=user.getName()%>">
						</div>
						<div class="mb-3">
							<label for="email" 
								class="form-label" 
								style="color: #31572C">
								Email
							</label> 
							<input type="email" 
								class="form-control"
								id="exampleFormControlInput1"
								name="email" 
								value="<%=user.getEmail()%>">
						</div>
						<div class="mb-3">
							<label for="password" 
								class="form-label" 
								style="color: #31572C">
								Password
							</label> 
							<input type="password" 
								class="form-control"
								id="exampleFormControlInput1" 
								name="password"
								value="<%=user.getPassword()%>">
						</div>
						<div class="mb-3">
							<label for="occupation" 
								class="form-label" 
								style="color: #31572C">
								Occupation
							</label> 
							<input type="text" 
								class="form-control"
								id="exampleFormControlInput1" 
								name="occupation"
								value="<%=user.getOccupation()%>">
						</div>
						<div class="mb-3">
							<label for="organization" 
								class="form-label"
								style="color: #31572C">
								Company/Organization 
							</label> 
							<input type="text" 
								class="form-control" 
								id="exampleFormControlInput1" 
								name="organization"
								value="<%=user.getOrganization()%>">
						</div>
						<div class="mb-3">
							<label for="facebook" 
								class="form-label" 
								style="color: #31572C">
								FaceBook URL 
							</label> 
							<input type="text" 
								class="form-control"
								id="exampleFormControlInput1" 
								name="facebookurl"
								value="<%=user.getFacebookURL()%>">
						</div>
						<div class="mb-3">
							<label for="linkedin" 
								class="form-label" 
								style="color: #31572C">
								LinkedIn URL 
							</label> 
							<input type="text" 
								class="form-control"
								id="exampleFormControlInput1" 
								name="linkedinurl"
								value="<%=user.getLinkedinURL()%>">
						</div>
						<div class="mb-3">
							<label for="twitter" 
								class="form-label" 
								style="color: #31572C">
								Twitter/X URL 
							</label> 
							<input type="text" 
								class="form-control"
								id="exampleFormControlInput1"
								name="twitterurl" 
								value="<%=user.getxURL()%>">
						</div>
						<div class="mb-3">
							<label for="about" 
								class="form-label" 
								style="color: #31572C">
								About
							</label>
							<textarea class="form-control" 
								id="exampleFormControlTextarea1"
								rows="3" 
								name="about"
							><%=user.getAbout()%></textarea>
						</div>

						<button type="button" 
							class="btn btn-danger"
							data-bs-dismiss="modal">
							Discard
						</button>
						<button type="submit" 
							class="btn"
							style="color: white; background-color: #132A13">
							Edit
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
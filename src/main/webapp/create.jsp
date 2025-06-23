<%@page import="thinksync.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="thinksync.entities.User"%>
<%@page import="thinksync.helper.SessionProvider"%>
<%@page import="thinksync.database.CategoryDatabase"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Note</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>

<style>
	input[type="text"] {
    	padding: 5px;
    	font-size: 25px;
    	border: 2px solid #31572C;
    	border-radius: 8px;
  	}
  	textarea {
  		padding: 5px;
    	font-size: 15px;
    	border: 2px solid #31572C;
    	border-radius: 8px;
  	}
  	.form-check-input {
  		border: 2px solid #4F772D;
    	accent-color: #4F772D;
    	transform: scale(1.1); 
  	}
  	.form-check-input:checked {
    	background-color: #4F772D;
    	border-color: #4F772D; 
  	}
  	.form-check-input:focus {
    	box-shadow: 0 0 0 0.25rem rgba(40, 167, 69, 0.25); /* green glow on focus */
  	}
</style>

</head>

<% 
	User user = (User) session.getAttribute("user"); 
	List<Category> categories = new CategoryDatabase(SessionProvider.getSession()).getCategories(user);
%>

<body>

<% if(user != null) { %>

	<%@ include file="navbar.html"%>
	
	<div class="container-fluid back">
		<form action="savenote" method="post">
		
			<!-- Note Creation Part -->
		
			<div class="row" 
				style="padding-top: 10px">
		
				<!-- Enter Note Title and Content-->
		
				<div class="col-md-9">
					<input type="text" 
						placeholder="Enter Title" 
						style="width: 100%; color: #132A13;"
						required="required"
						name="title">
					
					<textarea rows="20" cols="136"
						placeholder="Enter Your Note"
						style="margin-top: 10px; width: 100%; color: #132A13;"
						required="required"
						name="content"></textarea>
				</div>
			
				<!-- Enter filename and Select Category -->
			
				<div class="col-md-3">
				
					<!-- Enter File name -->
				
					<input type="text" 
						placeholder="Enter File Name" 
						style="width: 100%; color: #132A13;"
						required="required"
						name="filename">
					
					<h4 style="color: #31572C; margin-top: 15px">Select Associated Categories</h4>
					<p style="color: red; font-size: 15px; font-weight: bold;">* You cannot update these selected category later</p>
					
					<!-- Category Check boxes -->
				<% for(Category category : categories) { %>
					<div class="form-check form-switch">
  						<input class="form-check-input" 
  							type="checkbox" 
  							role="switch" 
  							name="categories" 
  							value="<%= category.getTitle() %>">
  						<label class="form-check-label" 
  							for="categories" 
  							style="color: #132A13; font-weight: bold">
  							<%= category.getTitle() %>
  						</label>
					</div>
				<% } %>
				</div>
			</div>
			
			<!-- Note Submit or Discard Button -->
			
			<div class="row" style="padding: 5px">
				<div class="col-md-6">
					<button class="btn btn-lg text-center" 
						role="submit"
						style="background-color: #31572C; color: #ECF39E; height: auto; margin-bottom: 5px; width: 100%">
						<b>
							Save Note
							<span class="icon-margin-left">
								<i class="fa-solid fa-file-arrow-down"></i>
							</span>
						</b>
					</button>
				</div>
				
				<div class="col-md-6">
					<a class="btn btn-lg btn-danger text-center" 
						role="button"
						href="notes.jsp"
						style="width: 100%">
						<b>
							Discard Note
							<span class="icon-margin-left">
								<i class="fa-solid fa-file-circle-xmark"></i>
							</span>
						</b>
					</a>
				</div>
			</div>
			
		</form>
	</div>
<%
} 
else {
	response.sendRedirect("signup.jsp");
}
%>
</body>
</html>
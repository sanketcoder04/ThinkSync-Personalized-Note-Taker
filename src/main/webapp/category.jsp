<%@page import="thinksync.entities.CategoryFilter"%>
<%@page import="thinksync.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="thinksync.database.CategoryDatabase"%>
<%@page import="thinksync.helper.SessionProvider"%>
<%@page import="thinksync.database.FeedbackDatabase"%>
<%@page import="thinksync.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categories</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>

<style>
	input[type="text"] {
    	padding: 5px;
    	font-size: 20px;
    	border: 2px solid #31572C;
    	border-radius: 8px;
  	}
  	input[type="radio"] {
    	transform: scale(1.5);        
    	accent-color: #4F772D;        
    	margin: 10px;
  	}
  	input[type="date"] {
    	padding: 5px;
    	font-size: 16px;
    	border: 2px solid #4caf50;
    	border-radius: 8px;
    	background-color: #f9f9f9;
    	color: #333;
  	}
</style>
</head>

<%
	User user = (User) session.getAttribute("user");
	CategoryFilter categoryFilter = (CategoryFilter) session.getAttribute("category_filter");
	List<Category> categories;
	if(categoryFilter == null) {
		categories = new CategoryDatabase(SessionProvider.getSession()).getCategories(user);
	}
	else {
		categories = new CategoryDatabase(SessionProvider.getSession()).getCategoriesOnRequirements(user, categoryFilter);
	}
%>

<body>

<% 
if(user != null) {
%>
	<%@ include file="navbar.html"%>
	
	<!-- Header of Category Page -->

	<div class="container-fluid back">
		<div class="container"
			style="height: 100%; width: 100%; padding: 10px">
			
			<!-- Create Category Page -->
			
			<div class="row">
				<div class="col-md-8">
					<h3 style="color: #31572C">
						Create Categories and associate to your notes for Personalization
					</h3>
				</div>
				<div class="col-md-4 flex">
					<a class="btn btn-lg text-center"
						role="button"
						style="background-color: #31572C; color: #ECF39E; height: auto; width: 100%"
						data-bs-toggle="modal" 
						data-bs-target="#addCategoryModal">
						<b style="font-size: 20px">
							Create Category
							<i class="fa-solid fa-paperclip"></i>
						</b>
					</a>
				</div>
			</div>

			<!-- Filtering Part  -->
			
			<form action="filtercategory">
				<div class="row" 
					style="height: auto; margin-top: 5px;">
					
					<p style="color: red; font-size: 15px; font-weight: bold;">
						* You cannot delete a category which is associated with an existing note
					</p>
					<div class=col-md-4>
						<input type="radio" 
							name="sort" 
							value="createdDate">
						<label for="sort" 
							style="color: #31572C; font-weight: bold">
							Sort by Date
						</label>
					
						<br>
					
						<input type="radio" 
							name="sort" 
							value="title">
						<label for="sort" 
							style="color: #31572C; font-weight: bold">
							Sort by Name
						</label>
					
						<br>
					
						<input type="date" 
							name="createdDate">
						<label for="date" 
							style="color: #31572C; font-weight: bold">
							Get Category by Date
						</label>
					</div>
					<div class="col-md-8">
						<input class="form-control" 
							type="text" 
							name="name" 
							placeholder="Search by Name"
							style="border: 2px solid #4F772D; margin-bottom: 15px; margin-top: 10px; height: 50px">
						
						<button type="submit" 
							class="btn" 
							style="background-color: #31572C; color: #ECF39E; font-weight: bold">
							Show Results
						</button>
						<a href="resetcategoryfilter" 
							class="btn" 
							style="background-color: #4F772D; color: #ECF39E; font-weight: bold">
							Reset
						</a>
					</div>
				</div>
			</form>
			
		</div>
	</div>
	
	<!-- All Categories -->

	<div class="container-fluid">
		<div class="container padding-top">
		
		<%
		for(Category category : categories) {
		%>		
		
			<!-- Category Cards -->
		
			<div class="card w-100 mb-3 padding-top margin-bottom back"
				style="border: none;">
				<div class="card-body margin-top">
					<b class="card-title" 
						style="font-size: 25px; color: #132A13">
						<%= category.getTitle() %>
					</b>
					<span style="color: #31572C"> 
						- <%= category.getUser().getName() %>
					</span>
							
					<br>
							
					<b class="card-text" 
						style="color: #4F772D">
						Number of Notes Associated - <%= category.getNotes().size() %>
					</b>
						
					<hr>
					
					<div class="row">
						<div class="col-9" 
							style="margin-bottom: 5px">
							<b style="color: #31572C;">Date Posted :
							<span style="color: #4F772D">
								<fmt:formatDate value="<%= category.getCreatedDate() %>" pattern="dd/MM/yyyy"/>
							</span>
							</b>
						</div>
						<div class="col-md-3">
							<div class="d-flex justify-content-start gap-2">
								<a class="btn text-center" 
									role="button"
									style="background-color: #31572C; color: #ECF39E; height: auto; margin-bottom: 5px;"
									data-bs-toggle="modal" 
									data-bs-target="#editCategoryModal-<%= category.getId() %>">
									<b>
										<span class="icon-margin-right">
											<i class="fa-solid fa-gear"></i>
										</span>
										Edit
									</b>
								</a>
								<form action="deletecategory" method="post">
									<input name="id" 
										value="<%= category.getId() %>" 
										hidden="hidden">
									<button class="btn btn-danger text-center" 
										role="submit"
									<% if(category.getNotes().size() != 0) { %>
										disabled="disabled"
										style="cursor: not-allowed; pointer-events: auto;"
									<% } %>>
										<b>
											<span class="icon-margin-right">
												<i class="fa-solid fa-trash-can"></i>
											</span>
											Delete
										</b>
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!--Edit Category Modal -->
	
			<div class="modal fade" 
				id="editCategoryModal-<%= category.getId() %>" 
				tabindex="-1" 
				aria-labelledby="exampleModalLabel" 
				aria-hidden="true">
  				<div class="modal-dialog">
    				<div class="modal-content">
      					<div class="modal-header back">
        					<h1 class="modal-title fs-5" 
        						id="exampleModalLabel" 
        						style="color: #132A13;">
        						Edit Category
        					</h1>
        					<button type="button" 
        						class="btn-close" 
        						data-bs-dismiss="modal" 
        						aria-label="Close">
        					</button>
      					</div>
      					<form action="editcategory" method="post">
      						<div class="modal-body">
      							<input name="id"
      								value="<%= category.getId() %>"
      								hidden="hidden">
        						<input name="title" 
        							type="text"
        							placeholder="Enter new Category Name"
    	   							style="color: #31572C; width: 100%; font-weight: bold; height: 40px; font-size: 20px;"
        							required="required">
      						</div>
      						<div class="modal-footer">
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
      						</div>
      					</form>
    				</div>
  				</div>
			</div>
		<%
		}
		%>	
		</div>
	</div>
	
	<!-- Add Category Modal -->
	
	<div class="modal fade" 
		id="addCategoryModal" 
		tabindex="-1" 
		aria-labelledby="exampleModalLabel" 
		aria-hidden="true">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header back">
        			<h1 class="modal-title fs-5" 
        				id="exampleModalLabel" 
        				style="color: #132A13">
        				Add Category
        			</h1>
        			<button type="button" 
        				class="btn-close" 
        				data-bs-dismiss="modal" 
        				aria-label="Close">
        			</button>
      			</div>
      			<form action="addcategory" method="post">
      				<div class="modal-body">
        				<input name="category" 
        					type="text"
        					placeholder="Enter a Category" 
        					style="color: #31572C; width: 100%; font-weight: bold; height: 40px; font-size: 20px; border: 2px solid #4F772D;"
        					required="required">
      				</div>
      				<div class="modal-footer">
        				<button type="button" 
        					class="btn btn-danger" 
        					data-bs-dismiss="modal">
        					Discard
        				</button>
        				<button type="submit" 
        					class="btn" 
        					style="color: white; background-color: #132A13">
        					Add
        				</button>
      				</div>
      			</form>
    		</div>
  		</div>
	</div>	
<%	
} 
else { 
	response.sendRedirect("signup.jsp"); 
}
%>
</body>
</html>
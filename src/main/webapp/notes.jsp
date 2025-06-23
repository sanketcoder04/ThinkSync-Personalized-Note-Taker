<%@page import="thinksync.entities.NoteFilter"%>
<%@page import="thinksync.entities.Category"%>
<%@page import="thinksync.entities.Note"%>
<%@page import="java.util.List"%>
<%@page import="thinksync.helper.SessionProvider"%>
<%@page import="thinksync.database.NoteDatabase"%>
<%@page import="thinksync.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notes</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>

<style>
	input[type="text"] {
    	padding: 5px;
    	font-size: 20px;
    	border: 2px solid #31572C;
    	border-radius: 8px;
  	}
  	input[type="email"] {
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
  	textarea {
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
	NoteFilter noteFilter = (NoteFilter) session.getAttribute("note_filter");
	List<Note> notes;
	if(noteFilter == null) {
		notes = new NoteDatabase(SessionProvider.getSession()).getNotes(user);
	}
	else {
		notes = new NoteDatabase(SessionProvider.getSession()).getNotesOnRequirements(user, noteFilter);
	}
%>

<body>

<% 
if(user != null) {
%>
	<%@ include file="navbar.html"%>
	
	<!-- Header of Note Page -->

	<div class="container-fluid back">
		<div class="container"
			style="height: 100%; width: 100%; padding: 10px">
			
			<!-- Create Note Page Link -->
			
			<div class="row">
				<div class="col-md-9">
					<h3 style="color: #31572C">
						Create Notes and associate your categories for Personalization
					</h3>
				</div>
				<div class="col-md-3 flex">
					<a class="btn btn-lg text-center" 
						href="create.jsp"
						role="button"
						style="background-color: #31572C; color: #ECF39E; height: auto; width: 100%">
						<b style="font-size: 20px">
							Create Note
							<span class="icon-margin-left">
								<i class="fa-solid fa-file-pen"></i>
							</span>
						</b>
					</a>
				</div>
			</div>
			
			<!-- Filtering Part -->

			<form action="filternote">
				<div class="row" 
					style="height: auto; margin-top: 5px;">
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
							Sort by Title
						</label>
					
						<br>
					
						<input type="date" 
							name="createdDate">
						<label for="createdDate" 
							style="color: #31572C; font-weight: bold">
							Get Category by Date
						</label>
					</div>
					<div class="col-md-8">
						<input class="form-control" 
							type="text" 
							name="title" 
							placeholder="Search by Title"
							style="border: 2px solid #4F772D; margin-bottom: 15px; margin-top: 10px; height: 50px">
						<input class="form-control" 
							type="text" 
							name="category" 
							placeholder="Search by Category"
							style="border: 2px solid #4F772D; margin-bottom: 15px; margin-top: 10px; height: 50px">
						<button type="submit" 
							class="btn" 
							style="background-color: #31572C; color: #ECF39E; font-weight: bold">
							Show Results
						</button>
						<a href="resetnotefilter" 
							class="btn" 
							style="background-color: #4F772D; color: #ECF39E; font-weight: bold">
							Reset
						</a>
					</div>
				</div>
			</form>
			
		</div>
	</div>
	
	<!-- All Notes -->
	
	<div class="container-fluid">
		<div class="container padding-top">
		
		<% 
		for(Note note : notes) { 
		%>	
		
			<!-- Note Cards -->
		
			<div class="card w-100 mb-3 padding-top margin-bottom back"
				style="border: none;">
				<div class="card-body margin-top">
					<div>
						<form action="qrcode" method="post">
							<input name="title" 
								value="<%= note.getTitle() %>" 
								hidden="hidden">
							<textarea name="content" 
								hidden="hidden"><%= note.getContent() %></textarea>
							<input name="filename" 
								value="<%= note.getFilename() %>"
							    hidden="hidden">
							<b class="card-title" 
								style="font-size: 23px; color: #132A13;">
								<%= note.getTitle() %>
							</b>
							<span style="color: #31572C;"> 
								<button class="btn">
									<i style="color: #31572C;font-size: 30px;" class="fa-solid fa-qrcode"></i>
								</button>
							</span>
						</form>
					</div>
				<% 
				for(Category category : note.getCategories()) { 
				%>		
					<b class="card-text" 
						style="color: #ECF39E; background-color: #31572C; border-radius: 5px; padding: 3px; margin-right: 5px">
							<%= category.getTitle() %>
					</b>
				<% 
				} 
				%>	
					<hr>
					
					<div class="row">
						<div class="col-8" style="margin-bottom: 5px">
							<b style="color: #31572C;">
								Date Posted :
								<span style="color: #4F772D">
									<fmt:formatDate value="<%= note.getCreatedDate() %>" pattern="dd/MM/yyyy"/> 
								</span>
							</b>
							
							
							<b style="color: #31572C; display: block;">
								File Name : 
								<span style="color: #4F772D">
									<%= note.getFilename() %>
								</span>
							</b>
						</div>
						
						<!-- View Edit Delete options -->
						
						<div class="col-md-4">
							<div class="d-flex justify-content-start gap-2">
								<a class="btn text-center" 
									role="button"
									style="background-color: #4F772D; color: #ECF39E; height: auto;"
									data-bs-toggle="modal" 
									data-bs-target="#viewNoteModal-<%= note.getId() %>">
									<b>
										<span class="icon-margin-right">
											<i class="fa-solid fa-note-sticky"></i>
										</span>
										View
									</b>
								</a>
								<a class="btn text-center" 
									role="button"
									style="background-color: #31572C; color: #ECF39E; height: auto;"
									data-bs-toggle="modal" 
									data-bs-target="#editNoteModal-<%= note.getId() %>">
									<b>
										<span class="icon-margin-right">
											<i class="fa-solid fa-gear"></i>
										</span>
										Edit
									</b>
								</a>
								<form action="deletenote" method="post">
									<input name="id" 
										value="<%= note.getId() %>" 
										hidden="hidden">
									<button class="btn btn-danger text-center" 
										role="submit">
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
					
					<hr>
					
					<!-- Features of Notes -->
					
					<div class="row">
						
						<!-- Download File Format -->
						
						<div class="col-md-9">
							<div class="d-flex justify-content-start gap-2">
								<form action="downloadnotetext" method="post">
									<input name="id" 
										value="<%= note.getId() %>" 
										hidden="hidden">
									<input name="title" 
										value="<%= note.getTitle() %>"
										hidden="hidden">
									<textarea name="content"
										hidden="hidden"><%= note.getContent() %></textarea>
									<input name="filename" 
										value="<%= note.getFilename() %>" 
										hidden="hidden">
									<button class="btn btn-dark text-center" 
										role="submit">
										<b>
											<span class="icon-margin-right">
												<i class="fa-solid fa-file-lines"></i>
											</span>
											Download .txt File
										</b>
									</button>
								</form>
								
								<form action="downloadnoteword" method="post">
									<input name="id" 
										value="<%= note.getId() %>" 
										hidden="hidden">
									<input name="title" 
										value="<%= note.getTitle() %>"
										hidden="hidden">
									<textarea name="content"
										hidden="hidden"><%= note.getContent() %></textarea>
									<input name="filename" 
										value="<%= note.getFilename() %>" 
										hidden="hidden">
									<button class="btn text-center" 
										role="submit"
										style="background-color: #023E8A;">
										<b>
											<span class="icon-margin-right">
												<i class="fa-solid fa-file-word"></i>
											</span>
											Download .docx File
										</b>
									</button>
								</form>
								
								<form action="downloadnotepdf" method="post">
									<input name="id" 
										value="<%= note.getId() %>" 
										hidden="hidden">
									<input name="title" 
										value="<%= note.getTitle() %>"
										hidden="hidden">
									<textarea name="content"
										hidden="hidden"><%= note.getContent() %></textarea>
									<input name="filename" 
										value="<%= note.getFilename() %>" 
										hidden="hidden">
									<button class="btn btn-danger text-center" 
										role="submit">
										<b>
											<span class="icon-margin-right">
												<i class="fa-solid fa-file-pdf"></i>
											</span>
											Download .pdf File
										</b>
									</button>
								</form>
							</div>
							<br>
						</div>
						
						<!-- Mail File -->
						
						<div class="col-md-3">
							<a class="btn text-center" 
								role="submit"
								style="width: 100%; background: linear-gradient(90deg, #EA4335, #023E8A) no-repeat; border-radius: 10px; font-weight: bold; color: #ECF39E"
								data-bs-toggle="modal" 
								data-bs-target="#mailNoteModal-<%= note.getId() %>">
								<span class="icon-margin-right">
									<i class="fa-solid fa-envelope"></i>
								</span>
								Mail
							</a>
						</div>
					</div>
				</div>
			</div>
			
			<!-- View Note Modal -->
			
			<div class="modal fade modal-xl" 
				id="viewNoteModal-<%= note.getId() %>" 
				tabindex="-1" 
				aria-labelledby="exampleModalLabel" 
				aria-hidden="true">
  				<div class="modal-dialog">
    				<div class="modal-content">
      					<div class="modal-header back">
        					<h1 class="modal-title fs-5" 
        						id="exampleModalLabel"
        						style="color: #31572C; font-weight: bold">
        						<%= note.getTitle() %>
        					</h1>
      					</div>
      					<div class="modal-body">
        					<pre style="color: #31572C; white-space: pre-wrap; word-wrap: break-word;"><%= note.getContent() %></pre>
      					</div>
      					<div class="modal-footer">
        					<button type="button" 
        						class="btn" 
        						data-bs-dismiss="modal"
        						style="background-color: #31572C; color: #ECF39E; height: auto">
        						Close
        					</button>
      					</div>
    				</div>
  				</div>
			</div>
			
			<!-- Edit Note Modal -->
			
			<div class="modal fade modal-xl" 
				id="editNoteModal-<%= note.getId() %>" 
				tabindex="-1" 
				aria-labelledby="exampleModalLabel" 
				aria-hidden="true">
				<form action="editnote" method="post">
  					<div class="modal-dialog">
    					<div class="modal-content">
      						<div class="modal-header back">
        						<div class="col-md-8">
        							<input type="text" 
										value="<%= note.getTitle() %>"
										style="width: 100%; color: #132A13;"
										required="required"
										name="title">
									<input name="id"
										value="<%= note.getId() %>"
										hidden="hidden">
        						</div>
        						<div class="col-md-4"
        							style="margin-left: 8px" >
        							<input type="text" 
										value="<%= note.getFilename() %>"
										style="width: 100%; color: #132A13;"
										required="required"
										name="filename">
        						</div>
      						</div>
      						<div class="modal-body">
      							<textarea rows="20"
									placeholder="Enter Your Note"
									style="margin-top: 10px; width: 100%; color: #132A13;"
									required="required"
									name="content"><%= note.getContent() %></textarea>
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
    					</div>
  					</div>
  				</form>
			</div>
			
			<!-- Mail Note Modal -->
			
			<div class="modal fade modal-xl" 
				id="mailNoteModal-<%= note.getId() %>" 
				tabindex="-1" 
				aria-labelledby="exampleModalLabel" 
				aria-hidden="true">
				<form action="mailnote" method="post">
  					<div class="modal-dialog">
    					<div class="modal-content">
      						<div class="modal-header back">
      							<input name="id" 
      								value="<%= note.getId() %>" 
      								hidden="hidden">
      							<input name="title" 
      								value="<%= note.getTitle() %>" 
      								hidden="hidden">
      							<input name="filename" 
      								value="<%= note.getFilename() %>" 
      								hidden="hidden">
      							<textarea name="content"
      								hidden="hidden"><%= note.getContent() %></textarea>	
      								
        						<div class="col-md-8"
        							style="color: #31572C; font-weight: bold; font-size: 20px">
        							<%= note.getTitle() %>
        						</div>
      						</div>
      						<div class="row modal-body">
      							<div class="col-md-6">
      								<input type="email"
      									style="width: 100%; color: #132A13;"
										required="required"
										name="reciepent"
										placeholder="Enter Reciepent Email Address">
									
									<input type="radio" 
										name="format" 
										value="text">
									<label for="format" 
										style="color: #31572C; font-weight: bold">
										.txt Format
									</label>
									<br>
									<input type="radio" 
										name="format" 
										value="pdf">
									<label for="format" 
										style="color: #31572C; font-weight: bold">
										.pdf Format
									</label>
									<br>
									<input type="radio" 
										name="format" 
										value="docx">
									<label for="format" 
										style="color: #31572C; font-weight: bold">
										.docx Format
									</label>
									<br>
									<p style="color: #4F772D; font-weight: bold">File Name : <%= note.getFilename() %></p>
      							</div>
      							
      							<div class="col-md-6">
      								<input type="text"
      									style="width: 100%; color: #132A13;"
										required="required"
										name="subject"
										placeholder="Enter Email Subject">
									<textarea rows="5" 
										style="width: 100%; margin-top: 5px; color: #132A13"
										placeholder="Enter Email Body"
										required="required"
										name="body"></textarea>
      							</div>
      						</div>
      						<div class="modal-footer">
        						<button type="button" 
        							class="btn btn-danger" 
        							data-bs-dismiss="modal"
        							onclick="location.reload()">
        							Discard
        						</button>
        						<button type="submit" 
        							class="btn" 
        							style="color: #ECF39E; background-color: #132A13">
        							Send
        							<i class="fa-solid fa-paper-plane"></i>
        						</button>
      						</div>
    					</div>
  					</div>
  				</form>
			</div>
		<% 
		} 
		%>	
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
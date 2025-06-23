<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - ThinkSync</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>
</head>
<body style="background-color: #132A13">

	<%@ include file="navbar.html"%>

	<div class="container-fluid"
		style="background-color: #132A13; padding-top: 20px">
		<div class="container align-items-center"
			style="background-color: #132A13;">
			
			<br>
			
			<div class="row" 
				style="padding-bottom: 19px">
				<div class="row" 
					style="padding-bottom: 50px">

					<!-- Icon and Design -->

					<div
						class="col-md d-flex flex-column justify-content-center align-items-center">
						<span class="icon-margin-right icon-margin-left text-center">
							<i class="fa-solid fa-hexagon-nodes text-center"
								style="color: #90A955; font-size: 150px">
							</i>
						</span>
						<h1 style="color: #90A955">
							ThinkSync - 
							<span style="color: #ECF39E">
								Login
							</span>
						</h1>
					</div>

					<!-- Form -->

					<div class="col-md">
					
						<br>
						
						<form action="login" 
							method="POST">
							<div class="mb-3">
								<input type="text" 
									class="form-control back"
									placeholder="Enter Your Name" 
									name="name" 
									required="required"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="email" 
									class="form-control back"
									placeholder="Enter Your Email" 
									name="email" 
									required="required"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="password" 
									class="form-control back"
									placeholder="Enter Your Password" 
									name="password"
									required="required" 
									style="font-weight: bold; color: #132A13"
									autocomplete="off">
							</div>
							<div class="row" 
								style="margin-left: 1px; margin-right: 1px">
								<button type="submit" 
									class="btn btn-lg"
									style="background-color: #ECF39E;">
									<b style="color: #132A13">
										Login
										<span class="icon-margin-left">
											<i class="fa-solid fa-arrow-right-to-bracket"
												style="color: #132A13">
											</i>
										</span>
									</b>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
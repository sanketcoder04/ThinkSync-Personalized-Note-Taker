<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Account - ThinkSync</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>
</head>
<body>

	<%@ include file="navbar.html"%>

	<div class="container-fluid"
		style="background-color: #132A13; padding-top: 20px">
		<div class="container" 
			style="background-color: #132A13">
			<div class="row" 
				style="padding-bottom: 19px">
				<div class="row" 
					style="padding-bottom: 50px">

					<!-- Icon and Design -->

					<div class="col-md d-flex flex-column justify-content-center align-items-center">
						<span class="icon-margin-right icon-margin-left text-center">
							<i class="fa-solid fa-hexagon-nodes text-center"
								style="color: #90A955; font-size: 150px">
							</i>
						</span>
						<h1 style="color: #90A955">
							ThinkSync - 
							<span style="color: #ECF39E">
								Create Account
							</span>
						</h1>
					</div>

					<!-- Form -->

					<div class="col-md">
						<br>
						<form action="signup" 
							method="POST">
							<div class="mb-3">
								<input type="text"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your Name" 
									name="name" 
									required="required"
									aria-describedby="nameHelp"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="email"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your Email" 
									name="email" 
									required="required"
									aria-describedby="emailHelp"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="password"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your Password" 
									name="password"
									required="required" 
									aria-describedby="passwordHelp"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off">
							</div>
							<div class="mb-3">
								<textarea class="form-control form-group font-weight-bold back" 
									rows="3" 
									placeholder="Enter About Yourself" 
									name="about"
									required="required" 
									aria-describedby="aboutHelp"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off"></textarea>
							</div>
							<div class="mb-3">
								<input type="text"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your Occupation" 
									name="occupation"
									required="required" 
									style="font-weight: bold; color: #132A13"
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="text"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your Organization" 
									name="organization"
									required="required" 
									style="font-weight: bold; color: #132A13"
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="url"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your FaceBook URL" 
									name="facebookurl"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="url"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your Twitter URL" 
									name="twitterurl"
									style="font-weight: bold; color: #132A13"
									autocomplete="off">
							</div>
							<div class="mb-3">
								<input type="url"
									class="form-control form-group font-weight-bold back"
									placeholder="Enter Your LinkedIn URL" 
									name="linkedinurl"
									style="font-weight: bold; color: #132A13" 
									autocomplete="off">
							</div>
							<div class="row" 
								style="margin-left: 1px; margin-right: 1px">
								<button type="submit" 
									class="btn btn-lg"
									style="background-color: #ECF39E;">
									<b style="color: #132A13">
										Sign Up
										<span class="icon-margin-left">
											<i class="fa-solid fa-user-plus" style="color: #132A13"></i>
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
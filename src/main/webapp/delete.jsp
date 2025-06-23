<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" http-equiv="refresh" content="5; URL=index.jsp">
<title>Delete profile</title>
<%@ include file="bootstrap_css_icon_dependencies.html"%>
<script type="text/javascript">
	let seconds = 6;
	function countdown() {
		if (seconds > 0) {
			document.getElementById("timer").textContent = seconds;
			seconds--;
			setTimeout(countdown, 1000);
		}
	}
	window.onload = countdown;
</script>
</head>
<body style="background-color: #ECF39E">

	<div class="container text-center" 
		style="background-color: #ECF39E">
		<div class="row d-flex justify-content-center align-items-center"
			style="margin: 30px; margin-bottom: 30px">
			<img class="img-fluid" 
				alt="error" 
				src="images/deletepic.png"
				style="height: 300px; width: 350px">
		</div>
		<h2 style="color: #31572C; margin-top: 50px">Account Successfully Deleted</h2>
		<p style="color: #132A13">
			Redirecting to Home page in 
			<span id="timer" style="color: #132A13">6</span> 
			seconds......
		</p>
	</div>
</body>
</html>
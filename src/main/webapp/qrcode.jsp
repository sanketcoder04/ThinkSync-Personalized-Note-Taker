<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Generated QR Code</title>
<%@include file="bootstrap_css_icon_dependencies.html" %>
</head>
<body>
	<%
		String downloadURL = (String) request.getAttribute("downloadLink");
		String qrImage = (String) request.getAttribute("qrImage");
	%>
	
	<%@include file="navbar.html" %>
	
	<div class="container-fluid d-flex justify-content-center align-items-center back" 
		style="height: 85vh">
		<div class="row d-flex justify-content-center align-items-center" 
			style="width: 30%; background-color: #31572C; border-radius: 10px; padding: 10px">
			<h2 style="color: #ECF39E">
				Generated QR Code
			</h2>
			
			<p style="color: #90A955; font-weight: bold">
				Scan the QR Code to download the file in PDF format on other devices
			</p>
			<div class="row d-flex justify-content-center align-items-center">
				<img src="data:image/png;base64,<%= qrImage %>" 
					alt="QR Code" 
					class="img-fluid"/>
			</div>
			
		</div>
	</div>
</body>
</html>
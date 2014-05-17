<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="rentalStyle.css" />
<title>Register Account</title>
</head>
<body>

	<h2>User Registration</h2>

	<div class="error">
		Cannot proceed!<br> Please fill in all the fields!<br>
		Possible issues below:
		<%
		if (request.getParameter("registerSubmit") != null)
		{
			if (request.getAttribute("firstName") == null || request.getAttribute("firstName") == "")
			{
				out.println("<div class='errorHints'>First name field cannot be empty.</div>");
			}
			if (request.getAttribute("lastName") == null || request.getAttribute("lastName") == "")
			{
				out.println("<div class='errorHints'>Last name field cannot be empty.</div>");
			}
			if (request.getAttribute("phone") == null || request.getAttribute("phone") == "")
			{
				out.println("<div class='errorHints'>Phone field cannot be empty.</div>");
			}
			if (request.getAttribute("email") == null || request.getAttribute("email") == "")
			{
				out.println("<div class='errorHints'>Email field cannot be empty.</div>");
			}
			if (request.getAttribute("password") == null || request.getAttribute("password") == "")
			{
				out.println("<div class='errorHints'>Password field cannot be empty.</div>");
			}
			if (request.getAttribute("password") != request.getAttribute("rePassword"))
			{
				out.println("<div class='errorHints'>Password must be the same with the confirmation password.</div>");
			}
		}
	%>
	</div>

	<form name="register" action="Controller" method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" value="${firstName}" size="30"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" value="${lastName}" size="30"></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone" value="${phone}" size="30"></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" value="${email}" size="30"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" value="${password}" size="30"></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><input type="password" name="rePassword" value="${rePassword}" size="30"></td>
			</tr>
		</table>
		<input name="registerSubmit" type="submit" value="Register" /> <input name="reset" type="reset" value="Reset" />
	</form>

</body>
</html>
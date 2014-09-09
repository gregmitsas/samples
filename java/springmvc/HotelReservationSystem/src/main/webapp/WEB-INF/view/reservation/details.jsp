<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Reservation Details</title>
	</head>
	<body>
		<h1>Reservation Details</h1>
		<table>
				<tr>
					<td>First Name:</td>
					<td>${reservation.customer.firstName}</td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td>${reservation.customer.lastName}</td>
				</tr>
				<tr>
					<td>Room Type:</td>
					<td>${reservation.roomType}</td>
				</tr>
				<tr>
					<td>Payment Method:</td>
					<td>${reservation.paymentMethod}</td>
				</tr>
				<tr>
					<td>Meal Plan:</td>
					<td>${reservation.mealPlan}</td>
				</tr>
				<tr>
					<td>Check-In Date:</td>
					<td>${reservation.checkInDate}</td>
				</tr>
				<tr>
					<td>Check-Out Date:</td>
					<td>${reservation.checkOutDate}</td>
				</tr>
			</table>
			<h2>Total Cost: ${reservation.totalCost}</h2>
	</body>
</html>
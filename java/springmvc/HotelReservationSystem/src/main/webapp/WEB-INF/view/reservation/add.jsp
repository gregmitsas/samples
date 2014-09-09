<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>New Reservation</title>
		<link href="styling.css" rel="stylesheet"/>
		<link href="bootstrap.min.css" rel="stylesheet"/>
		<script type="text/javascript" src="jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="script.js"></script>
	</head>
	<body>
		<form:form commandName="reservation" class="form-horizontal">
		<fieldset>
			<legend>Make your reservation</legend>
			<form:errors path="*" cssClass="errorBlock" element="div"/>
			<div class="form-group">
				<label for="firstName" class="col-sm-1">First Name</label>
				<div class="col-sm-11">
					<form:input id="firstName" name="firstName" path="customer.firstName" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="lastName" class="col-sm-1">Last Name</label>
				<div class="col-sm-11">
					<form:input id="lastName" name="lastName" path="customer.lastName" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="roomType" class="col-sm-1">Room</label>
				<div class="col-sm-11">
					<form:select id="roomType" name="roomType" path="roomType" class="form-control col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="paymentMethod" class="col-sm-1">Payment</label>
				<div class="col-sm-11">
					<form:select id="paymentMethod" name="paymentMethod" path="paymentMethod" class="form-control col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="mealPlan" class="col-sm-1">Meal Plan</label>
				<div class="col-sm-11">
					<form:select id="mealPlan" name="mealPlan" path="mealPlan" class="form-control col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="checkInDate" class="col-sm-1">Check-In Date</label>
				<div class="col-sm-11">
					<form:input id="checkInDate" name="checkInDate" path="checkInDate" placeholder="DD/MM/YYYY" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="checkOutDate" class="col-sm-1">Check-Out Date</label>
				<div class="col-sm-11">
					<form:input id="checkOutDate" name="checkOutDate" path="checkOutDate" placeholder="DD/MM/YYYY" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-11">
					<div class="checkbox">
						<label><form:checkbox path="refund"/>Refund in case of cancellation</label>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-11">
					<input type="submit" class="btn btn-primary" value="Submit"/>
				</div>
			</div>
		</fieldset>			
		</form:form>
	</body>
</html>
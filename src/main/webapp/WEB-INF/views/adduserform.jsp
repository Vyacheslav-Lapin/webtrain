<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<!-- TODO: Try to use tag "spring:message" instead of direct message below-->
	<title>User :: AddForm</title>
</head>
<body>

<h3><spring:message code="form.adduser.name" /></h3>
<form:form ><!--commandName="userFormBean"-->
	<table>
		<tr>
			<!-- TODO: Try to use tag "spring:message" instead of direct message below-->
			<td>First Name:</td> 
			<td><form:input path="firstName"/></td>
		</tr>
		<tr>
			<td colspan="2" style="color: red; font-size: small;">
				<form:errors path="firstName" />
			</td>
		</tr>
		<tr>
			<td><spring:message code="name.last" /></td>
			<td><form:input path="lastName" /></td>
		</tr>
		<tr>
			<td colspan="2" style="color: red; font-size: small;">
				<form:errors path="lastName" />
			</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Save Changes" /></td>
		</tr>
	</table>
</form:form>

</body>
</html>
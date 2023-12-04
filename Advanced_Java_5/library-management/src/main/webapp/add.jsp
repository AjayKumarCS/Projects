<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library-Management</title>
</head>
<body>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}
	%>
	<div align="right">
		<form action="logout" method="post">
			<label>hello ${username}</label> <input type="submit" value="logout" />
		</form>
	</div>
	<form action="addDetails" method="post">
		<table>
			<tr>
				<td>Code:</td>
				<td><input type="text" name="code" /></td>
			</tr>
			<tr>
				<td>Book Name:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><select name="author">
						<c:forEach items="${authors}" var="item">
							<option value="<c:out value="${item}" />"><c:out
									value="${item}" /></option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Date:</td>
				<td><input type="text" name="date"
					value="<c:out value="${date}" />" readonly /></td>
			</tr>
		</table>
		<input type="submit" value="Add" />
	</form>
	<form action="back" method="post">
		<input type="submit" value="Cancel" />
	</form>
</body>
</html>
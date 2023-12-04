<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>search result</title>
</head>
<body>
<div align="right">
	<form method="Post" action="logout">
		<label>hello ${userName}</label><input type="submit" value="Log Out" />
	</form>
	</div>
<div>
	<form method="Post" action="back">
		<input type="submit" value="Search again" />
	</form>
	</div>
	<table>
		<thead>
			<tr>
				<th>PID</th>
				<th>Name</th>
				<th>Colour</th>
				<th>Gender</th>
				<th>Size</th>
				<th>Available</th>
				<th>Price</th>
				<th>Rating</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${result}" var="list">
			<tr>
				<c:forEach items="${list}" var="item">
				<td><c:out value="${item}" /></td>
				</c:forEach>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("index.jsp");
	}
	%>
	<div align="right">
		<form action="logout" method="post">
			<label>hello ${userName}</label> <input type="submit" value="logout" />
		</form>
	</div>
	<form action="search" method="post">
		<table>
			<tr>
				<td><label>Select Colour</label></td>
				<td><select name="color">
						<c:forEach items="${colors}" var="item">
							<option value="<c:out value="${item}" />"><c:out
									value="${item}" /></option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><label>Select Size</label></td>
				<td><select name="size">
						<option value="S">S</option>
						<option value="M">M</option>
						<option value="L">L</option>
						<option value="XL">XL</option>
						<option value="XXL">XXL</option>
				</select></td>
			</tr>
			<tr>
				<td><label>Select Gender</label></td>
				<td><select name="gender">
						<option value="M">Male</option>
						<option value="F">Female</option>
				</select></td>
			</tr>
			<tr>
				<td><label>Select Output Preference</label></td>
				<td><select name="sortBy">
						<option value="ORDER BY price">Price</option>
						<option value="ORDER BY rating DESC">Rating</option>
						<option value="ORDER BY price, rating DESC">Both</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="Search" />
	</form>
</body>
</html>
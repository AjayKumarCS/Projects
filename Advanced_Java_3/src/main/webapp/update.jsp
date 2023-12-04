<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.pms.dao.ProductDAO"%>
<%@page import="com.pms.model.Product"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>PMS</title>
<style>
.center {
	display: flex;
	justify-content: center;
	align-items: center;
}

.user {
	position: absolute;
	top: 10px;
	right: 10px;
}
</style>
</head>
<body>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("login.jsp");
	}
	%>

	<h1 class="center">Product Management Tool</h1>
	<div align="right" class="user">
		<form action="LogoutServlet" method="post">
			<label>hello ${userName}</label> <input type="submit" value="logout" />
		</form>
	</div>
	
	<form method="POST" action="EditServlet" enctype="multipart/form-data">
		<b>Please enter product details you want to edit</b>
		<%
		ProductDAO productDAO = new ProductDAO();
		Product p = productDAO.selectProduct(Integer.parseInt(request.getParameter("id")));
		%>
		<table>
			<tr>
				<td><label>Title</label></td>
				<td><input type="text" name="title" value="<%=p.getTitle()%>"
					required /></td>
			</tr>
			<tr>
				<td><label>Quantity</label></td>
				<td><input type="text" name="quantity"
					value="<%=p.getQuantity()%>" required /></td>
			</tr>
			<tr>
				<td><label>Size</label></td>
				<td><input type="text" name="size" value="<%=p.getSize()%>"
					required /></td>
			</tr>
			<tr>
				<td><label>Image</label></td>
				<td><input type="file" accept="image/*" name="image" size="50" /></td>
				<td><img
					src="http://localhost:8080/uploaded_files/<%=p.getImg()%>"
					style="width: 100px; height: 100px; background-size: cover;" /></td>
			</tr>
			<tr style="display: none;">
				<td><input type="text" name="id" value="<%=p.getId()%>" /></td>
			</tr>
		</table>
		<input type="submit" value="Edit" />
	</form>
	<div align="right">
		<p>
			<a href="index.jsp">Click Back</a>
		</p>
	</div>
</body>
</html>
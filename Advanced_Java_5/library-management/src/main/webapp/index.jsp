<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Library-Management</title>
<script type="text/javascript">
	function del(link) {
		console.log(link);
		var xmlhttp = new window.XMLHttpRequest();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				location.reload();
			}
		};
		var params = "link=" + link;

		xmlhttp.open('POST', "delete", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(params);
	}
</script>
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
	<form method="post" action="add">
		<input type="submit" value="Add Book" />
	</form>
	<table>
		<thead>
			<tr>
				<th>Book Code</th>
				<th>Book Name</th>
				<th>Author</th>
				<th>Date Added</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${books}" var="item">
				<tr>
					<td><c:out value="${item[0]}" /></td>
					<td><c:out value="${item[1]}" /></td>
					<td><c:out value="${item[2]}" /></td>
					<td><c:out value="${item[3]}" /></td>
					<td>
						<form action="edit" method="post">
							<input type="text" value='<c:out value="${item[4]}" />'
								style="display: none" name="link" /> <input type="submit"
								value="edit" />
						</form> <input type="button"
						onclick="del('<c:out value="${item[4]}" />')" value="delete" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
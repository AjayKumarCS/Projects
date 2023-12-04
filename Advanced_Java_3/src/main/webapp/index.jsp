<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="com.pms.dao.ProductDAO"%>
<%@page import="com.pms.model.Product"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>PMS</title>
<style type="text/css">
.center {
	display: flex;
	justify-content: center;
	align-items: center;
}

.outputTable {
	width: 90vw;
}

.margin {
	margin-left: auto;
	margin-right: auto;
}

.border {
	border: 1px solid black;
	border-collapse: collapse;
}

.user {
	position: absolute;
	top: 10px;
	right: 10px;
}
</style>
<script type="text/javascript">

	function del(id) {
		
		var xmlhttp = new window.XMLHttpRequest();		
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				location.reload();
			}
		};
		
		var params = "id=" + id;

		xmlhttp.open('POST', "DeleteServlet", true);
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
	<form action="InsertServlet" method="post"
		enctype="multipart/form-data">
		<b>Please enter product details to add to stock</b>
		<table>
			<tr>
				<td><label>Title</label></td>
				<td><input type="text" name="title" required /></td>
				<td></td>
			</tr>
			<tr>
				<td><label>Quantity</label></td>
				<td><input type="text" name="quantity" required /></td>
				<td></td>
			</tr>
			<tr>
				<td><label>Size</label></td>
				<td><input type="text" name="size" required /></td>
				<td></td>
			</tr>
			<tr>
				<td><label>Image</label></td>
				<td><input type="file" accept="image/*" name="image" size="50"
					required /></td>
			</tr>
		</table>
		<input type="submit" value="Add" />
	</form>
	<table class="outputTable border margin">
		<thead>
			<tr class="border">
				<th class="border">S.No.</th>
				<th class="border">Title</th>
				<th class="border">Quantity</th>
				<th class="border">Size</th>
				<th class="border">image</th>
				<th class="border">Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
			ProductDAO productDAO = new ProductDAO();
			List<Product> products = productDAO.selectAllProduct();
			long size = 0;
			int serial = 1;
			for (Product p : products) {
				size = size + p.getImgSize();
			%>

			<tr class="border">
				<td class="border"><div class="center"><%=serial%></div></td>
				<td class="border"><div class="center"><%=p.getTitle()%></div></td>
				<td class="border"><div class="center"><%=p.getQuantity()%></div></td>
				<td class="border"><div class="center"><%=p.getSize()%></div></td>
				<td class="border"><div class="center">
						<img src="http://localhost:8080/uploaded_files/<%=p.getImg()%>"
							style="width: 100px; height: 100px; background-size: cover;" />
					</div></td>
				<td class="border">
					<div class="center">
						<input type="button" class="margin"
							onclick="location.href = 'update.jsp?id=<%=p.getId()%>';"
							value="edit" /> <input type="button" class="margin"
							onclick="del(<%=p.getId()%>)" value="delete" />
					</div>
				</td>

			</tr>

			<%
			serial = serial + 1;
			}
			%>
		</tbody>
	</table>
	<div align="right">
		Total size of image:
		<%=size%>
		bytes
	</div>
</body>
</html>
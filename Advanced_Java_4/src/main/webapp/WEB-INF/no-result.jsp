<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
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
		<h1>No matching results found! please search with different parameters</h1>
</body>
</html>
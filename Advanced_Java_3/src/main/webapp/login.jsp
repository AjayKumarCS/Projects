<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>PMS-Login</title>
<style>
	.imp{
		color: red;
	}
	.button{
		height: 40px;
		width: 80px;
	}
	.form{
		width: 60vw;
		margin: auto;
	}
	.table{
		width: 80%;
		margin: auto;
	}
</style>
</head>
<body>

	<form action="LoginServlet" method="post" class="form">
		<div class="">
			<h2>Login</h2>
		</div>
		<div class="">
			<table class="table">
				<tr>
					<td><label> Username: </label></td>
					<td><div class="imp">* <input type="text" name="usrName" /></div></td>
				</tr>
				<tr>
					<td><label> Password: </label></td>
					<td><div class="imp">* <input type="password" name="usrPass" /></div></td>
				</tr>
			</table>
		</div>
		<div align="right">
			<input type="submit" value="login >>" class="button" />
		</div>
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update</title>

<link rel="stylesheet" type="text/css" href="endeavors.css">
</head>
<body>

	<%@ page import="br.edu.insper.*, java.util.*, java.io.*"%>
	<%String updated_item = request.getParameter("updated_item"); %>
	<h1 class="formul">Atualize o dado!</h1>
	<form action="atualiza" method="post" class="formul">

		To Do:<br> <input type="text" name="to_do"><br>

		Doing:<br> <input type="text" name="doing"><br>

		Done:<br> <input type="text" name="done"><br>
		<button name="subject" type="submit" value=<%=updated_item%>>Atualiza</button>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
		<link rel="stylesheet" type="text/css" href="endeavors.css">

</head>
<body>
	<%@ page import="br.edu.insper.*, java.util.*"%>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="searchbar">
				<input class="search_input" type="text" name=""
					placeholder="Search..."> <a href="#" class="search_icon"><i
					class="fas fa-search"></i></a>
			</div>
		</div>
	</div>

	<table border='1'>
		<%
			DAO dao = new DAO();
			List<Endeavors> endeavors = dao.getEndeavor();
			for (Endeavors endeavor : endeavors) {
		%>
		<tr>
			<td><%=endeavor.getTodo()%></td>
			<td><%=endeavor.getDoing()%></td>
			<td><%=endeavor.getDone()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
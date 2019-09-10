<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="teste.css">
<script src="teste.js"></script>
</head>
<body>
	<%@ page import="br.edu.insper.*, java.util.*"%>
	
					
	<form id="login-form" action="endeavors.jsp" method="post" role="form">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2">
				<div class="input-group">
					<div class="input-group-btn search-panel">
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span id="search_concept">Filtrar por</span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#alfcres">Ordem alfabética (A-Z)</a></li>
							<li><a href="#alfdec">Ordem alfabética (Z-A)</a></li>
							<li class="divider"></li>
							<li><a href="#all">Tudo</a></li>
						</ul>
					</div>
					<input type="hidden" name="search" value="all"
						id="search_param"> <input type="text" class="form-control"
						name="joa" placeholder="Busca"> <span
						class="input-group-btn">
						<button class="btn btn-default" type="submit" >
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</div>
		</div>
	</div>
	</form>
	<p> <%= request.getParameter("search") %></p>
	<p> <%= request.getParameter("joa") %></p>

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
		
	</table>
	<form action="apaga" method="post">
			<button name="subject" type="submit" value=<%=endeavor.getId()%>>Apagar</button>
	</form>
	<form action="atualiza" method="post">

		to_do:<br> <input type="text" name="to_do"><br>
		
		Doing:<br> <input type="text" name="doing"><br>
		
		Done:<br> <input type="text" name="done"><br>
		<button name="subject" type="submit" value=<%=endeavor.getId()%>>Atualizar</button>
	</form>
	
	<%
		}
	%>
	<form action="adiciona" method="post">
		To do:<br> <input type="text" name="to_do"><br>
		<input type='submit' value='Adiciona'><br>
	</form>
	<form action="adiciona" method="post">
		Doing:<br> <input type="text" name="doing"><br>
		<input type='submit' value='Adiciona'><br>
	</form>
	<form action="adiciona" method="post">
		Done:<br> <input type="text" name="done"><br>
		<input type='submit' value='Adiciona'><br>
	</form>
</body>
</html>
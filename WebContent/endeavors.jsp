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
							<li><a href="#todo">To do</a></li>
							<li><a href="#doing">Doing</a></li>
							<li><a href="#done">Done</a></li>
							<li class="divider"></li>
							<li><a href="#all">Tudo</a></li>
						</ul>
					</div>
					<input type="hidden" name="filter" value="all"
						id="search_param"> <input type="text" class="form-control"
						name="search" placeholder="Busca"> <span
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
	<table border='1'>
		<%
			DAO dao = new DAO();
			List<Endeavors> endeavors = dao.getEndeavor();
			Map<String, List<String>> filtered_endeavors =  new HashMap<String,List<String>>();
			List<String> todoList = new ArrayList<>();
			List<String> doingList = new ArrayList<>();
			List<String> doneList = new ArrayList<>();
			filtered_endeavors.put("todo", todoList);
			filtered_endeavors.put("doing", doingList);
			filtered_endeavors.put("done", doneList);
			for (Endeavors endeavor : endeavors) {
				String search = request.getParameter("search");
				String filter = request.getParameter("filter");
				String todo = endeavor.getTodo();
				String doing = endeavor.getDoing();
				String done = endeavor.getDone();
				if (filter != null && filter.equals("todo")) {
					if (search != null && todo.contains(search)) {
						todoList.add(todo + "-" + endeavor.getId());
					}
				}
				else if (filter != null && filter.equals("doing")) {
					if (search != null && todo.contains(search)) {
						doingList.add(doing + "-" + endeavor.getId());
					}
				}
				else if (filter != null && filter.equals("done")) {
					if (search != null && todo.contains(search)) {
						doneList.add(done + "-" + endeavor.getId());
					}
				}
				else if (filter != null && filter.equals("all")) {
						if (search == "") {
							if (todo != null) {
								todoList.add(todo + "-" + endeavor.getId());
							}
							else if (doing != null) {
								doingList.add(doing + "-" + endeavor.getId());
							}
							else if (done != null) {
								doneList.add(done + "-" + endeavor.getId());
							}
						}
						
						else {
							if (todo != null) {
								if (todo.contains(search)) {
									todoList.add(todo + "-" + endeavor.getId());
								}
							}
							else if (doing != null) {
								if (doing.contains(search)) {
									doingList.add(doing + "-" + endeavor.getId());
								}
							}
							else if (done != null) {
								if (done.contains(search)) {
									doneList.add(done + "-" + endeavor.getId());
								}
							}
						}
						
				}
			}
			for (Map.Entry<String,List<String>> entry : filtered_endeavors.entrySet()) {

				Iterator<String> i = entry.getValue().iterator();
			      while (i.hasNext()) {
						String[] elemento = i.next().split("-"); 
						String assignment = elemento[0];
						String id = elemento[1];
			      
		
		%>
		<tr>
			<td><%=entry.getKey()%></td>
			<td><%=assignment%></td>
		</tr>
		
	</table>
	<form action="apaga" method="post">
		<button name="subject" type="submit" value=<%=id%>>Deleta</button>
	</form>
	<form action="atualiza" method="post">

		to_do:<br> <input type="text" name="to_do"><br>
		
		Doing:<br> <input type="text" name="doing"><br>
		
		Done:<br> <input type="text" name="done"><br>
		<button name="subject" type="submit" value=<%=id%>>Atualiza</button>
	</form>
	<%
			      }
		}
		System.out.println(filtered_endeavors);
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Endeavors</title>
<meta charset="ISO-8859-1">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
	
<style type="text/css">
  <%@include file="endeavors.css" %>
</style>
<script><%@include file="endeavors.js" %></script>
	
</head>
<body>
	<%@ page import="mvc.model.*, java.util.*, java.io.*"%>

	<img
		src="https://logoeps.com/wp-content/uploads/2014/09/39714-linked-in-logo-of-two-letters-icon-vector-icon-vector-eps.png"
		alt="Smiley face" height="42" width="42">

	<form id="login-form" action="endeavors" method="post" role="form">
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
						<input type="hidden" name="filter" value="all" id="search_param">
						<input type="text" class="form-control" name="search"
							placeholder="Busca"> <span class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
				</div>
			</div>
		</div>
	</form>


	<table border='1' class="tabela">
		<%
		EndeavorsDAO dao = new EndeavorsDAO();
		Integer contaToDo = dao.contaToDo();
		Integer contaDoing = dao.contaDoing();
		Integer contaDone = dao.contaDone();
		List<Endeavors> endeavors = dao.getEndeavor();
		Map<String, List<String>> filtered_endeavors =  new HashMap<String,List<String>>();
		List<String> todoList = new ArrayList<>();
		List<String> doingList = new ArrayList<>();
		List<String> doneList = new ArrayList<>();
		filtered_endeavors.put("todo", todoList);
		filtered_endeavors.put("doing", doingList);
		filtered_endeavors.put("done", doneList);
		String search = request.getParameter("search");
		String filter = request.getParameter("filter");
		for (Endeavors endeavor : endeavors) {
			String todo = endeavor.getTodo();
			String doing = endeavor.getDoing();
			String done = endeavor.getDone();
			if (filter != null && filter.equals("todo")) {
					if (search == "") {
					if (todo != null && todo.length() != 0) {
						todoList.add(todo + "-" + endeavor.getId());
					}
				}

				else {
					if (todo != null && todo.length() != 0) {
						if (todo.contains(search)) {
							todoList.add(todo + "-" + endeavor.getId());
						}
					}
				}
			}

			else if (filter != null && filter.equals("doing")) {
				if (search == "") {
					if (doing != null && doing.length() != 0) {
						doingList.add(doing + "-" + endeavor.getId());
					}
				}

				else {
					if (doing != null && doing.length() != 0) {
						if (doing.contains(search)) {
							doingList.add(doing + "-" + endeavor.getId());
						}
					}
				}
			} else if (filter != null && filter.equals("done")) {
				if (search == "") {
					if (done != null && done.length() != 0) {
						doneList.add(done + "-" + endeavor.getId());
					}
				}

				else {
					if (done != null && done.length() != 0) {
						if (done.contains(search)) {
							doneList.add(done + "-" + endeavor.getId());
						}
					}
				}
			} else if (filter != null && filter.equals("all")) {
				if (search == "") {
					if (todo != null && todo.length() != 0) {
						todoList.add(todo + "-" + endeavor.getId());
					} else if (doing != null && doing.length() != 0) {
						doingList.add(doing + "-" + endeavor.getId());
					} else if (done != null && done.length() != 0) {
						doneList.add(done + "-" + endeavor.getId());
					}
				}

				else {
					if (todo != null && todo.length() != 0) {
						if (todo.contains(search)) {
							todoList.add(todo + "-" + endeavor.getId());
						}
					} else if (doing != null && doing.length() != 0) {
						if (doing.contains(search)) {
							doingList.add(doing + "-" + endeavor.getId());
						}
					} else if (done != null && done.length() != 0) {
						if (done.contains(search)) {
							doneList.add(done + "-" + endeavor.getId());
						}
					}
				}

			} else {
				if (todo != null && todo.length() != 0) {
					todoList.add(todo + "-" + endeavor.getId());
				} else if (doing != null && doing.length() != 0) {
					doingList.add(doing + "-" + endeavor.getId());
				} else if (done != null && done.length() != 0) {
					doneList.add(done + "-" + endeavor.getId());
				}
			}
		}
		for (Map.Entry<String, List<String>> entry : filtered_endeavors.entrySet()) {

			Iterator<String> i = entry.getValue().iterator();
			while (i.hasNext()) {
				String[] elemento = i.next().split("-");
				String assignment = elemento[0];
				String id = elemento[1];
		%>
		<tr>
			<td><%=entry.getKey()%></td>
			<td><%=assignment%></td>
			<td>
				<form action="removeEndeavor" method="post">
					<button name="subject" type="submit" value=<%=id%>>Deleta</button>
				</form>

				<form action="updatePage" method="post">
					<button name="updated_item" type="submit" value=<%=id%>>Atualiza</button>
				</form>
			</td>
		</tr>


		<%
			      }
		}
	%>
	</table>
	<form action="addEndeavor" method="post" class="formul">
		To do:<br> <input type="text" name="to_do"><br> <input
			type='submit' value='Adiciona'><br>
	</form>
	<form action="addEndeavor" method="post" class="formul">
		Doing:<br> <input type="text" name="doing"><br> <input
			type='submit' value='Adiciona'><br>
	</form>
	<form action="addEndeavor" method="post" class="formul">
		Done:<br> <input type="text" name="done"><br> <input
			type='submit' value='Adiciona'><br>
	</form>

	<table border='1' class="tabela">
		<tr>
			<td>Quant To DO</td>
			<td><%=contaToDo%></td>
			<td>Quant Doing</td>
			<td><%=contaDoing%></td>
			<td>Quant Doing</td>
			<td><%=contaDone%></td>
		</tr>
	</table>
</body>
</html>
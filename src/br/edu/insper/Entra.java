package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/entra")
public class Entra extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		boolean autenticated = dao.autentica(username, pass);
		if (autenticated) {
			response.sendRedirect(request.getContextPath() + "/endeavors.jsp");
			dao.close();
		}
		else {
			PrintWriter out = response.getWriter();
			out.println("<html><h1>erro</h1><body>");
			out.println("<body><html>");
		}

	}
}
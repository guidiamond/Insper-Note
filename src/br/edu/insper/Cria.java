package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cria")
public class Cria extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<form method='post'>");
		out.println("Nome: <input type='text' name='username'><br>");
		out.println("Senha: <input type='text' name='password' step='0.01'><br>");
		out.println("Confirmar Senha: <input type='text' name='cpassword' step='0.01'><br>");
		out.println("Email: <input type=‘text' name='email'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<body><html>");
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		DAO dao = new DAO();
		Users user = new Users();
		String cpass = request.getParameter("cpassword");
		String pass = request.getParameter("password");
		if (cpass.equals(pass)) {
			user.setUsername(request.getParameter("username"));
			user.setPassword(pass);
			user.setEmail(request.getParameter("email"));
			try {
				dao.adiciona(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			response.sendRedirect(request.getContextPath() + "/lista");
			dao.close();
		}
		else {
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.print(cpass);
			out.print(pass);
			out.println("<body><html>");
		}

	}
}
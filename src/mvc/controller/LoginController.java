package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.*;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import mvc.model.UserDAO;
import mvc.model.Users;
@Controller
public class LoginController{
	@RequestMapping("logres")
	public String logres() {
		return "logres";
	}
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Users users, HttpSession session) {
		System.out.println("dasdas");
		try {
			if(new UserDAO().autentica(users)) {
				session.setAttribute("usuarioLogado", users.getUsername());
				return "endeavors";
			}
			else {
				session.setAttribute("usuarioLogado", null);
				return "redirect:logres";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:logres";
	}

	@RequestMapping("efetuaRegistro")
	public String efetuaRegistro(@RequestParam String username,
			@RequestParam String email, @RequestParam String password,
			@RequestParam String cpassword, HttpSession session) {
		System.out.println("registrando");
		UserDAO dao;
		try {
			dao = new UserDAO();
			Users user = new Users();
			if (cpassword .equals(password)) {
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
					dao.adiciona(user);
					// TODO Auto-generated catch block
				

				dao.close();
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return "redirect:logres";
	}

}
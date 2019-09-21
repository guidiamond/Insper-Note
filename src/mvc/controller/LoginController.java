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
		if(new UserDAO().autentica(users)) {
			session.setAttribute("usuarioLogado", users.getUsername());
			return "redirect:endeavors";
		}
		return "redirect:logres";
	}

	@RequestMapping("efetuaRegistro")
	public String efetuaRegistro(@RequestParam String username,
			@RequestParam String email, @RequestParam String password,
			@RequestParam String cpassword, HttpSession session) {
		UserDAO dao = new UserDAO();
		Users user = new Users();
		if (cpassword .equals(password)) {
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			try {
				dao.adiciona(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			dao.close();
		}
		return "redirect:logres";
	}

}
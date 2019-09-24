package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.*;

@Controller
public class EndeavorController {
	@RequestMapping("endeavors")
	public String home(HttpSession session) {
		return "endeavors";
	}
	@RequestMapping("addEndeavor")
	public String adiciona(@RequestParam(required = false) String to_do, @RequestParam(required = false) String doing, 
			@RequestParam(required = false) String done, HttpSession session) {
		// TODO Auto-generated method stub
		EndeavorsDAO dao;
		try {
			dao = new EndeavorsDAO();
			Endeavors endeavor = new Endeavors();
			endeavor.setTodo(to_do);
			endeavor.setDoing(doing);
			endeavor.setDone(done);
			dao.addEndeavor(endeavor);
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "endeavors";
	}
	@RequestMapping("updatePage")
	public String update(HttpSession session) {
		return "updateEndeavor";
	}

	@RequestMapping("updateEndeavor")
	public String atualiza(@RequestParam String to_do, @RequestParam String doing,
			@RequestParam String done, @RequestParam String subject,
			HttpSession session) {
		EndeavorsDAO dao;
		try {
			dao = new EndeavorsDAO();
			Endeavors endeavor = new Endeavors();
			endeavor.setId(Integer.valueOf(subject));
			endeavor.setTodo(to_do);
			endeavor.setDoing(doing);
			endeavor.setDone(done);
			dao.altera(endeavor);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return "redirect:endeavors";
	}
	@RequestMapping("removeEndeavor")
	public String apaga(@RequestParam String subject, HttpSession session) {
		try {
			EndeavorsDAO dao = new EndeavorsDAO();
			dao.remove(Integer.valueOf(subject));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:endeavors";
	}
}

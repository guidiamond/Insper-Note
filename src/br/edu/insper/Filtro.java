package br.edu.insper;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Filtro
 */
@WebServlet("/filtro")
public class Filtro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filtro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					todoList.add(todo);
				}
			}
			else if (filter != null && filter.equals("doing")) {
				if (search != null && todo.contains(search)) {
					doingList.add(doing);
				}
			}
			else if (filter != null && filter.equals("done")) {
				if (search != null && todo.contains(search)) {
					doneList.add(done);
				}
			}
			else if (filter != null && filter.equals("all")) {
					if (search == "") {
						if (todo != null) {
							todoList.add(todo);
						}
						else if (doing != null) {
							doingList.add(doing);
						}
						else if (done != null) {
							doneList.add(done);
						}
					}
					
					else {
						if (todo != null) {
							if (todo.contains(search)) {
								todoList.add(todo);
							}
						}
						else if (doing != null) {
							if (doing.contains(search)) {
								doingList.add(doing);
							}
						}
						else if (done != null) {
							if (done.contains(search)) {
								doneList.add(done);
							}
						}
					}
					
			}
				
			
	}

}

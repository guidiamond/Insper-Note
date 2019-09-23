package mvc.controller;

import javax.servlet.http.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object controller) throws Exception {
		System.out.println(request.getSession().getAttribute("usuarioLogado"));
		String uri = request.getRequestURI();
		if( uri.endsWith("logres") ||
				uri.endsWith("efetuaLogin") ||
				uri.endsWith("efetuaRegistro")) {
			return true;
		}
		if(request.getSession().getAttribute("usuarioLogado") != null) {
			System.out.println(request.getSession().getAttribute("usuarioLogado"));
			return true;
		}
		response.sendRedirect("logres");
		return false;
	}
}
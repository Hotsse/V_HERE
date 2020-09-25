package com.hotsse.vhere.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null || id.isEmpty()) {
			res.sendRedirect("/user/login");
			return false;
		}
		
		return true;
	}
}

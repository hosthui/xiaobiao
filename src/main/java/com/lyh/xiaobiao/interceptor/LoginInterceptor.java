package com.lyh.xiaobiao.interceptor;

import com.lyh.xiaobiao.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		User loginuser = (User)session.getAttribute("loginuser");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (loginuser==null) {
			response.sendRedirect(contextPath+"/index.html");
			return false;
		}
		return true;
	}
}

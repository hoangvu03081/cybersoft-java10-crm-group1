package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;

@WebFilter(urlPatterns = "*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String action = req.getServletPath();
		if (action.equals("/login") || action.equals("/logout")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession();
		UserDto user = (UserDto) session.getAttribute("USER_LOGIN");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		String roleName = user.getRoleName();

		if (action.startsWith("/role") && !roleName.equals("ROLE_ADMIN")) {
			resp.sendRedirect(req.getContextPath() + "/403");
			return;
		}

		if (action.startsWith("/user/edit") && (Integer.parseInt(req.getParameter("id")) == user.getUserId())
				&& (roleName.equals("ROLE_MEMBER") || roleName.equals("ROLE_LEADER"))) {
			chain.doFilter(request, response);
			return;
		}

		if (action.startsWith("/user") && roleName.equals("ROLE_MEMBER")) {
			resp.sendRedirect(req.getContextPath() + "/403");
			return;
		}

		if ( (action.startsWith("/user/delete") || action.startsWith("/user/edit")) && 
				roleName.equals("ROLE_LEADER")) {
			resp.sendRedirect(req.getContextPath() + "/403");
			return;
		}

		if ((action.equals("/project/edit") || action.equals("/project/delte")) && roleName.equals("ROLE_MEMBER")) {
			resp.sendRedirect(req.getContextPath() + "/403");
			return;
		}

		if ((action.equals("/task/edit") || action.equals("/task/delte")) && roleName.equals("ROLE_MEMBER")) {
			resp.sendRedirect(req.getContextPath() + "/403");
			return;
		}

		chain.doFilter(request, response);

	}
}

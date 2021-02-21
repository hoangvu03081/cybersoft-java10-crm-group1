package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;
import com.myclass.service.AuthService;
import com.myclass.service.UserService;

@WebServlet(urlPatterns = {"/login",
						"/logout"})
public class AuthController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	private AuthService authService;

	public AuthController() {
		userService = new UserService();
		authService = new AuthService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/login":	
			req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);;
			break;
		
		case "/logout":
			HttpSession session = req.getSession();
			session.removeAttribute("USER_LOGIN");
			resp.sendRedirect(req.getContextPath() + "/login");;
			
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/login":	
			String email = req.getParameter("email");
			String pass = req.getParameter("password");

			UserDto loginUser = authService.login(email, pass);
			if (loginUser == null) {
				req.setAttribute("message", "Sai mật khẩu hoặc email.");
				req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("USER_LOGIN", loginUser);
				resp.sendRedirect(req.getContextPath() + "/home");
			}

		default:
			break;
		}
	}
}

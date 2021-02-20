package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/403", 
						"/404"})	
public class ErrorController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "403":
			req.getRequestDispatcher("/WEB-INF/views/error/403.jsp");
			break;

		case "404":
			req.getRequestDispatcher("/WEB-INF/views/error/404.jsp");
			break;

		default:
			break;
		}
	}
}

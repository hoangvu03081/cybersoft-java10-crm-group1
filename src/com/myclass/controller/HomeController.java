package com.myclass.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;
import com.myclass.service.TaskService;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserDto checkAuth = (UserDto) session.getAttribute("USER_LOGIN");
		TaskService taskService = new TaskService();
		ArrayList<Integer> taskCompleted=(ArrayList<Integer>) taskService.getCompletedTaskFromUserId(checkAuth.getUserId());
		req.setAttribute("taskCompleted", taskCompleted);
		req.getRequestDispatcher("/WEB-INF/views/home/index.jsp").forward(req, resp);
	}
	
}

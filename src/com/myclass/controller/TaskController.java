package com.myclass.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.TaskDto;
import com.myclass.service.TaskService;

@WebServlet(urlPatterns = {"/task", "/task/add", "/task/edit", "/task/delete"})
public class TaskController extends HttpServlet{
	
	private TaskService taskService;
	@Override
		public void init() throws ServletException {
			taskService = new TaskService();
		}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int idProject = Integer.parseInt(req.getParameter("idP"));
	req.setAttribute("idP", idProject);
	switch(req.getServletPath()) {
		
	case "/task":
		ArrayList<TaskDto> taskList = (ArrayList<TaskDto>) taskService.getTaskByProjectId(idProject);
		req.setAttribute("taskList", taskList);
		req.getRequestDispatcher("/WEB-INF/views/task/index.jsp").forward(req, resp);
		break;
	case "/task/add":
		/*
		RoleService roleService = new RoleService();
		ArrayList<RoleDto> roleList = roleService.getRoleList();
		req.setAttribute("roleList", roleList);
		*/
		req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
		
		break;
	case "/task/delete":
		int deleteId = Integer.parseInt(req.getParameter("id"));
		taskService.deleteTask(deleteId);
		System.out.println(req.getParameter("idP"));
		resp.sendRedirect(req.getContextPath()+"/task?idP="+req.getParameter("idP"));
		break;
	case "/task/edit":
		
		break;
	}
	
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case "/task/add":
			
			break;
		case "/task/edit":
			
			break;
		}
	}
	
	}
}

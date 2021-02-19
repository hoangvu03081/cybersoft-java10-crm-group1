package com.myclass.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.RoleDto;
import com.myclass.dto.TaskDto;
import com.myclass.service.RoleService;
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
		req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
		
		break;
	case "/task/delete":
		int deleteId = Integer.parseInt(req.getParameter("id"));
		taskService.deleteTask(deleteId);
		System.out.println(req.getParameter("idP"));
		resp.sendRedirect(req.getContextPath()+"/task?idP="+idProject);
		break;
	case "/task/edit":
		int editId = Integer.parseInt(req.getParameter("id"));
		TaskDto taskEdit = taskService.getTaskById(editId);
		req.setAttribute("task", taskEdit);
		req.getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);
		break;
	}
	
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int idProject = Integer.parseInt(req.getParameter("idP"));
	switch(req.getServletPath()) {
	case "/task/add":
		String taskName = req.getParameter("taskName");
		LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
		LocalDate endDate   = LocalDate.parse(req.getParameter("endDate"));
		int statusId = Integer.parseInt(req.getParameter("statusId"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		taskService.addTask(new TaskDto(0,taskName,startDate,endDate,statusId,userId,idProject));
		resp.sendRedirect(req.getContextPath()+"/task?idP="+idProject);
		break;
	case "/task/edit":
		int taskIdEdit = Integer.parseInt(req.getParameter("taskId"));
		String taskNameEdit = req.getParameter("taskName");
		LocalDate startDateEdit = LocalDate.parse(req.getParameter("startDate"));
		LocalDate endDateEdit   = LocalDate.parse(req.getParameter("endDate"));
		int statusIdEdit = Integer.parseInt(req.getParameter("statusId"));
		int userIdEdit = Integer.parseInt(req.getParameter("userId"));
		taskService.editTask(new TaskDto(taskIdEdit,taskNameEdit,startDateEdit,endDateEdit,statusIdEdit,userIdEdit,idProject));	
		resp.sendRedirect(req.getContextPath()+"/task?idP="+idProject);
		break;
	}
}
	
}


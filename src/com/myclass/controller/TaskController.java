package com.myclass.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.RoleDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.service.ProjectService;
import com.myclass.service.RoleService;
import com.myclass.service.TaskService;
import com.myclass.service.UserService;

@WebServlet(urlPatterns = {"/task", "/task/add", "/task/edit", "/task/delete"})
public class TaskController extends HttpServlet{
	
	private TaskService taskService;
	private UserService userService;
	private ProjectService projectService;
	@Override
		public void init() throws ServletException {
			taskService = new TaskService();
			userService = new UserService();
			projectService = new ProjectService();
		}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	HttpSession session = req.getSession();
	UserDto checkAuth = (UserDto) session.getAttribute("USER_LOGIN");
	
	int idProject = Integer.parseInt(req.getParameter("idP"));
	req.setAttribute("idP", idProject);
	if(!projectService.checkParticipateProject(checkAuth.getUserId(), idProject))
		resp.sendRedirect(req.getContextPath()+"/403");
	
	else {
	switch(req.getServletPath()) {
		
	case "/task":
		ArrayList<TaskDto> taskList = (ArrayList<TaskDto>) taskService.getTaskByProjectId(idProject);
		req.setAttribute("taskList", taskList);
		if (!taskList.isEmpty()) {
			System.out.println("1");
		}
		ArrayList<Integer> taskCompleted=(ArrayList<Integer>) taskService.getCompletedTaskFromUserId(checkAuth.getUserId());
		req.setAttribute("taskCompleted", taskCompleted);
		System.out.println(taskCompleted.get(0));
		System.out.println(taskCompleted.get(1));
		req.getRequestDispatcher("/WEB-INF/views/task/index.jsp").forward(req, resp);
		break;
	case "/task/add":
		HashMap<Integer,String> userList1=userService.getHashMap();
		req.setAttribute("userList", userList1);
		req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
		break;
	case "/task/delete":
		
		int deleteId = Integer.parseInt(req.getParameter("id"));
		if(!taskService.checkTaskInProject(deleteId, idProject))
			resp.sendRedirect(req.getContextPath()+"/403");
		else {
			taskService.deleteTask(deleteId);
			resp.sendRedirect(req.getContextPath()+"/task?idP="+idProject);
		}
		break;
	case "/task/edit":
		int editId = Integer.parseInt(req.getParameter("id"));
		if(!taskService.checkTaskInProject(editId, idProject))
			resp.sendRedirect(req.getContextPath()+"/403");
		else if(checkAuth.getRoleId()==3&&!taskService.checkOwnTask(checkAuth.getUserId(), editId))
			resp.sendRedirect(req.getContextPath()+"/403");
		else {
			HashMap<Integer,String> userList2=userService.getHashMap();
			req.setAttribute("userList", userList2);
			
			TaskDto taskEdit = taskService.getTaskById(editId);
			req.setAttribute("task", taskEdit);
			req.getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);
		}
		break;
	}
	}
	
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int idProject = Integer.parseInt(req.getParameter("idP"));
	req.setAttribute("idP", idProject);
	
	switch(req.getServletPath()) {
	case "/task/add":
		String taskName = req.getParameter("taskName");
		LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
		LocalDate endDate   = LocalDate.parse(req.getParameter("endDate"));
		int statusId = Integer.parseInt(req.getParameter("statusId"));
		int userId = Integer.parseInt(req.getParameter("userId"));
		UserDto user1 = userService.getUserById(userId);
		if(user1==null) {
			String message="USER NOT FOUND";
			req.setAttribute("message", message);
			UserService userService = new UserService();
			HashMap<Integer,String> userList=userService.getHashMap();
			req.setAttribute("userList", userList);
			int editId = Integer.parseInt(req.getParameter("id"));
			TaskDto taskEdit = taskService.getTaskById(editId);
			req.setAttribute("task", taskEdit);
			req.getRequestDispatcher("/WEB-INF/views/task/add.jsp").forward(req, resp);
		}
		else {
			taskService.addTask(new TaskDto(0,taskName,startDate,endDate,statusId,userId,idProject));
			resp.sendRedirect(req.getContextPath()+"/task?idP="+idProject);
		}
		break;
	case "/task/edit":
		int taskIdEdit = Integer.parseInt(req.getParameter("taskId"));
		String taskNameEdit = req.getParameter("taskName");
		LocalDate startDateEdit = LocalDate.parse(req.getParameter("startDate"));
		LocalDate endDateEdit   = LocalDate.parse(req.getParameter("endDate"));
		int statusIdEdit = Integer.parseInt(req.getParameter("statusId"));
		int userIdEdit = Integer.parseInt(req.getParameter("userId"));
		UserDto user2 = userService.getUserById(userIdEdit);
		if(user2==null) {
			String message="USER NOT FOUND";
			req.setAttribute("message", message);
			UserService userService = new UserService();
			HashMap<Integer,String> userList=userService.getHashMap();
			req.setAttribute("userList", userList);
			TaskDto taskEdit = taskService.getTaskById(taskIdEdit);
			req.setAttribute("id", taskIdEdit);
			req.setAttribute("task", taskEdit);
			req.getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);
		}
		else {
			taskService.editTask(new TaskDto(taskIdEdit,taskNameEdit,startDateEdit,endDateEdit,statusIdEdit,userIdEdit,idProject));	
			resp.sendRedirect(req.getContextPath()+"/task?idP="+idProject);
		}
		break;
	}
}
	
}


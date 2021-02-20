package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.ProjectDto;
import com.myclass.dto.UserDto;
import com.myclass.service.ProjectService;

@WebServlet(urlPatterns = {"/project", "/project/add", "/project/edit", "/project/delete"})
public class ProjectController extends HttpServlet {
	private ProjectService projectService;
	
	@Override
	public void init() throws ServletException {
		projectService = new ProjectService();
	}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	HttpSession session = req.getSession();
	UserDto checkAuth = (UserDto) session.getAttribute("USER_LOGIN");
	
	switch(req.getServletPath()) {
	case "/project":
		ArrayList<ProjectDto> projectList = null;
		//Admin
		if(checkAuth.getRoleId()==1)
		projectList = (ArrayList<ProjectDto>) projectService.getAllProject();
		
		//Leader
		if(checkAuth.getRoleId()==2)
		projectList = (ArrayList<ProjectDto>) projectService.getProjectByUserLeaderId(checkAuth.getUserId());
				
		//User
		if(checkAuth.getRoleId()==3)
		projectList = (ArrayList<ProjectDto>) projectService.getProjectByUserId(checkAuth.getUserId());
		
		
		
		req.setAttribute("projectList", projectList);
		req.getRequestDispatcher("/WEB-INF/views/project/index.jsp").forward(req, resp);
		break;
		
	case "/project/add":
		req.getRequestDispatcher("/WEB-INF/views/project/add.jsp").forward(req, resp);
		break;
	case "/project/edit":
		int idEdit=Integer.parseInt(req.getParameter("id"));
		System.out.println(idEdit);
		ProjectDto temp = projectService.getProjectById(idEdit);
		req.setAttribute("project", temp);
		req.getRequestDispatcher("/WEB-INF/views/project/edit.jsp").forward(req, resp);
		break;
	case "/project/delete":
		int idDelete = Integer.parseInt(req.getParameter("id"));
		System.out.println(idDelete);
		projectService.deleteProject(idDelete);
		resp.sendRedirect(req.getContextPath()+"/project");
		break;
	}

}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	switch(req.getServletPath()) {
		case "/project/add":
			String 	projectName = req.getParameter("projectName");
			LocalDate startDate=LocalDate.parse(req.getParameter("startDate"));
			LocalDate endDate=LocalDate.parse(req.getParameter("endDate"));
			int leaderId = Integer.parseInt(req.getParameter("leaderId"));
			projectService.addProject(new ProjectDto(-1,projectName,startDate,endDate,leaderId));
			break;
		case "/project/edit":
			System.out.println("Begin");
			int idEdit=Integer.parseInt(req.getParameter("projectId"));
			System.out.println(idEdit);
			String nameEdit=req.getParameter("projectName");
			System.out.println(nameEdit);
			LocalDate startDateEdit = LocalDate.parse(req.getParameter("startDate"));
			System.out.println(startDateEdit);
			LocalDate endDateEdit = LocalDate.parse(req.getParameter("endDate"));
			System.out.println(endDateEdit);
			int leaderIdEdit = Integer.parseInt(req.getParameter("leaderId"));
			System.out.println(leaderIdEdit);
			projectService.editProject(new ProjectDto(idEdit,nameEdit,startDateEdit,endDateEdit,leaderIdEdit));
			break;
	}
	resp.sendRedirect(req.getContextPath()+"/project");
}
}

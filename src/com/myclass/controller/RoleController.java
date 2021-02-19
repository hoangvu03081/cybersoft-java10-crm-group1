package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@WebServlet(urlPatterns = {"/role", "/role/add", "/role/edit", "/role/delete"})
public class RoleController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	//private RoleRepository 	roleRepository;
	private RoleService 	roleService;
	
	public RoleController() {
		roleService = new RoleService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();
		
		switch (action) 
		{
		case "/role":
			List<RoleDto> roles = roleService.getAllRoles();
			
			req.setAttribute("roles", roles);
			req.getRequestDispatcher("/WEB-INF/views/role/index.jsp").forward(req, resp);
			break;
		
		case "/role/add":
			req.getRequestDispatcher("/WEB-INF/views/role/add.jsp").forward(req, resp);
			break;
		
		case "/role/edit":
			int editId = Integer.parseInt(req.getParameter("id"));
			
			RoleDto dto = roleService.getRoleById(editId);
			
			req.setAttribute("role", dto);
			req.getRequestDispatcher("/WEB-INF/views/role/edit.jsp").forward(req, resp);
			break;
			
		case "/role/delete":
			int id = Integer.valueOf(req.getParameter("id"));
			if (!roleService.removeRole(id)) {
				req.setAttribute("message", "Xóa thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/role/index.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/role");
			}
			
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String action = req.getServletPath();
		
		switch (action) {
		case "/role/add":
			RoleDto dto = new RoleDto(req.getParameter("name"), req.getParameter("description"));
			
			if (!roleService.addRole(dto)) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/role/edit.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/role");
			}
			break;
			
		case "/role/edit":
			int editId = Integer.parseInt(req.getParameter("id"));
			String editName = req.getParameter("name");
			String editDesc = req.getParameter("desc");
			// GỌI HÀM THỰC THI TRUY VẤN THÊM MỚI CỦA REPOSITORY
			RoleDto editRole = new RoleDto(editId, editName, editDesc);
			boolean editResult = roleService.editRole(editRole);
			// KIỂM TRA KẾT QUẢ TRUY VẤN
			
			if (!editResult) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/role/edit.jsp").forward(req, resp);
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/role");
			}
			break;
			
		default:
			break;
		}
	}
}

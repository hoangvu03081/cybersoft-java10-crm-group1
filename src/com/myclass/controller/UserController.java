package com.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@WebServlet(name="user", urlPatterns = {"/user",
										"/user/add",
										"/user/delete",
										"/user/edit"})
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private RoleService roleService;
	public UserController() {
		userService = new UserService();
		roleService = new RoleService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		switch (action) {
		case "/user":
			req.setAttribute("userList", userService.getAllWithRoleDescription());
			req.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(req, resp);
			break;
			
		case "/user/add":
			req.setAttribute("roles", roleService.getAllRoles());
			req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			break;
			
		case "/user/edit":
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("user", userService.getUserById(id));
			req.setAttribute("roles", roleService.getAllRoles());
			String previousPath = req.getHeader("Referer");
			String[] previousServletPath = previousPath.split("/");
			String previsousServlet = previousServletPath[ previousServletPath.length - 1 ];
			req.setAttribute("returnServlet", previsousServlet);
			req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			break;
			
		case "/user/delete":
			int idDelete= Integer.parseInt(req.getParameter("id"));
			userService.removeUser(idDelete);
			resp.sendRedirect(req.getContextPath() + "/user");
			break;
			
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getServletPath();
		switch (action) {
		case "/user/add":
			String email = req.getParameter("email");
			String fullname = req.getParameter("fullname");
			String password = req.getParameter("password");
			String avatar = req.getParameter("avatar");
			int roleId = Integer.parseInt(req.getParameter("roleId"));
			
			UserDto dto = new UserDto(email, password, fullname, avatar, roleId);
		
			
			
			if( userService.addUser(dto) < 0 ) {
				req.setAttribute("message" , "Thêm mới thất bại");
				req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/user");
			}
			break;
			
		case "/user/edit":
			int id = Integer.parseInt(req.getParameter("id"));
			String emailEdit = req.getParameter("email");
			String fullnameEdit = req.getParameter("fullname");
			String passwordEdit = req.getParameter("password");
			String avatarEdit = req.getParameter("avatar");
			int roleIdEdit = Integer.parseInt(req.getParameter("roleId"));
			
			UserDto dtoEdit = new UserDto(id, emailEdit, passwordEdit, fullnameEdit, avatarEdit, roleIdEdit);
			
			HttpSession session = req.getSession();
			UserDto loginDto = (UserDto)session.getAttribute("USER_LOGIN");	

			
			if( userService.editUser(dtoEdit, loginDto.getRoleId()) < 0 ) {
				req.setAttribute("message" , "Chỉnh sửa thất bại");
				req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			} else {
				if (loginDto.getUserId() == id) {
					session.removeAttribute("USER_LOGIN");
					session.setAttribute("USER_LOGIN", userService.getUserByEmail(emailEdit));
				}
				req.setAttribute("returnServlet", req.getParameter("returnServlet"));
				req.setAttribute("user", userService.getUserById(id));
				req.setAttribute("roles", roleService.getAllRoles());
				req.setAttribute("message", "Chỉnh sửa thành công");
				req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			}
			break;
			
		default:
			break;
		}
	}
}

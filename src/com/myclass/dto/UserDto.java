package com.myclass.dto;

public class UserDto {
	private int userId;
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private int roleId;
	private String roleDescription;
	private String roleName;
	
	public UserDto() {
	
	}

	public UserDto(int userId, String email, String password, String fullname, String avatar, int roleId,
			String roleDescription) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;
		this.roleDescription = roleDescription;
	}

	public UserDto(int userId, String email, String password, String fullname, String avatar, int roleId) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;	
	}
	
	public UserDto(String email, String password, String fullname, String avatar, int roleId) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;	
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}

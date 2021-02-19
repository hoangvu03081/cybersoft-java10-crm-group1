package com.myclass.dto;

public class RoleDto {
	// Properties
	private int 	roleId;
	private String	roleName;
	private String	roleDescription;
	
	// Getters and setters
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String name) {
		this.roleName = name;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String description) {
		this.roleDescription = description;
	}
	
	// Constructors
	public RoleDto() {
		
	}
	public RoleDto(String roleName, String roleDescription) {
		this.roleName 			= roleName;
		this.roleDescription	= roleDescription;
	}
	public RoleDto(int roleId, String roleName, String roleDescription) {
		this.roleId				= roleId;
		this.roleName 			= roleName;
		this.roleDescription	= roleDescription;
	}
	
	// Methods
	
	
}

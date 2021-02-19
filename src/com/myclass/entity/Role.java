package com.myclass.entity;

public class Role {
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
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	// Constructors
	public Role() {
		
	}
	public Role(String roleName, String roleDescription) {
		this.roleName 			= roleName;
		this.roleDescription	= roleDescription;
	}
	public Role(int roleId, String roleName, String roleDescription) {
		this.roleId				= roleId;
		this.roleName 			= roleName;
		this.roleDescription	= roleDescription;
	}
	
	// Methods	
}

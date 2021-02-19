package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.entity.Role;

public class RoleRepository {
	public List<Role> getAllRoles(){
		String query = "SELECT * FROM ROLE";
		List <Role> roles = new ArrayList<Role>();
		
		try {
			Connection conn = DbConnection.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Role entity = new Role(
						resultSet.getInt("ID"),
						resultSet.getString("NAME"),
						resultSet.getString("DESCRIPTION")
						);
				
				roles.add(entity);
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return roles;
	}
	
	public boolean addRole(Role entity) {
		String query = "INSERT INTO ROLE(NAME, DESCRIPTION) VALUES (?, ?)";
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.setNString(1, entity.getRoleName());
			statement.setNString(2, entity.getRoleDescription());
			
			
			int isSuccess = statement.executeUpdate();
			conn.close();
			if (isSuccess >= 0) {
				return true;
			}
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	public boolean editRole(Role entity) {
		String query = "UPDATE ROLE SET NAME = ?, DESCRIPTION = ? WHERE ID = ?";
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.setString	(1, entity.getRoleName());
			statement.setString	(2, entity.getRoleDescription());
			statement.setInt	(3, entity.getRoleId());
			
			int res = statement.executeUpdate();
			conn.close();
			if (res >= 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean removeRole(int id) {
		String query = "DELETE FROM ROLE WHERE ID = ?";
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(query);			
			statement.setInt(1, id);
			int res = statement.executeUpdate();
			conn.close();
			if (res >= 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Role getRoleById(int id) {
		String query = "SELECT * FROM ROLE WHERE ID = ?";
		
		Role role = null;
		
		PreparedStatement statement;
		try {
			Connection conn = DbConnection.getConnection();
			statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				role = new Role();
				role.setRoleId(result.getInt("ID"));
				role.setRoleName(result.getString("NAME"));
				role.setRoleDescription(result.getString("DESCRIPTION"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}
}

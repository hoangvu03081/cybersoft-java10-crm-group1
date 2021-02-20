package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserRepository {

	public List<UserDto> getAllWithRoleDescription() {

		String querry = "SELECT U.ID, U.EMAIL, U.PASSWORD,U.FULLNAME, U.AVATAR, U.ROLE_ID, R.DESCRIPTION "
				+ "FROM CRM.USER U INNER JOIN CRM.ROLE R ON U.ROLE_ID=R.ID";
		List<UserDto> dtos = new ArrayList<UserDto>();
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(querry);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				UserDto user = new UserDto();
				user.setUserId(res.getInt("ID"));
				user.setFullname(res.getNString("FULLNAME"));
				user.setPassword(res.getNString("PASSWORD"));
				user.setAvatar(res.getNString("AVATAR"));
				user.setEmail(res.getNString("EMAIL"));
				user.setRoleId(res.getInt("ROLE_ID"));
				user.setRoleDescription(res.getNString("DESCRIPTION"));
				dtos.add(user);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dtos;
	}
	
	public UserDto getByEmail(String email) {
		String query = "SELECT U.ID, U.EMAIL, U.PASSWORD,U.FULLNAME, U.AVATAR, U.ROLE_ID, R.DESCRIPTION, R.NAME " + 
							"FROM CRM.USER U INNER JOIN CRM.ROLE R ON U.ROLE_ID=R.ID WHERE U.EMAIL = ?";
		UserDto user = null;

		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setNString(1, email);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				user = new UserDto();
				user.setUserId(res.getInt("ID"));
				user.setFullname(res.getNString("FULLNAME"));
				user.setAvatar(res.getNString("AVATAR"));
				user.setEmail(res.getNString("EMAIL"));
				user.setRoleId(res.getInt("ROLE_ID"));
				user.setPassword(res.getNString("PASSWORD"));
				user.setRoleDescription(res.getNString("DESCRIPTION"));
				user.setRoleName(res.getNString("NAME"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

		return user;
	}

	public User getById(int id) {

		String querry = "SELECT * FROM CRM.USER WHERE ID=?";
		User user = null;

		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(querry);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				user = new User();
				user.setUserId(res.getInt("ID"));
				user.setFullname(res.getNString("FULLNAME"));
				user.setAvatar(res.getNString("AVATAR"));
				user.setEmail(res.getNString("EMAIL"));
				user.setRoleId(res.getInt("ROLE_ID"));
				user.setPassword(res.getNString("PASSWORD"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

		return user;
	}

	public List<User> getAllUsers() {

		String querry = "SELECT * FROM CRM.USER";
		List<User> users = new ArrayList<User>();
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(querry);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setUserId(res.getInt("ID"));
				user.setFullname(res.getNString("FULLNAME"));
				user.setAvatar(res.getNString("AVATAR"));
				user.setEmail(res.getNString("EMAIL"));
				user.setRoleId(res.getInt("ROLE_ID"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (SQLException e) {}
		}
		return users;

	}
	
	public HashMap<Integer,String> getAllUsersHashMap() {

		String querry = "SELECT * FROM CRM.USER";
		HashMap<Integer,String> users = new HashMap<Integer,String>();
		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(querry);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				int id 		= res.getInt("ID");
				String name	= res.getString("FULLNAME");
				users.put(id,name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {conn.close();} catch (SQLException e) {}
		}
		return users;

	}

	public int addUser(User user) {

		String querry = "INSERT INTO CRM.USER(EMAIL, FULLNAME, PASSWORD, AVATAR, ROLE_ID) VALUES (?, ?, ?, ?, ?)";

		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(querry);
			statement.setNString(1, user.getEmail());
			statement.setNString(2, user.getFullname());
			statement.setNString(3, user.getPassword());
			statement.setNString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch (SQLException e) {}
		}
		return -1;
	}

	public int editUser(User user) {

		String querry = "UPDATE CRM.USER SET EMAIL=?, FULLNAME=?, PASSWORD=?, AVATAR=?, ROLE_ID=? WHERE ID=?";

		Connection conn = DbConnection.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement(querry);
			statement.setNString(1, user.getEmail());
			statement.setNString(2, user.getFullname());
			statement.setNString(3, user.getPassword());
			statement.setNString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, user.getUserId());

			int result = statement.executeUpdate();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { conn.close();} catch (SQLException e) {}
		}
		return -1;
	}

	public int removeUser(int id) {

		String querry = "DELETE FROM CRM.USER WHERE ID=?";

		Connection conn = DbConnection.getConnection();

		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(querry);
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch (SQLException e) {}
		}

		return -1;
	}
}

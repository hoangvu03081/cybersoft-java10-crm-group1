package com.myclass.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.entity.Task;

public class TaskRepository {
	public Task getTaskById(int id)
	{
		String query="SELECT * FROM CRM.TASK WHERE ID = ?";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				String 	taskName 	= result.getString("NAME");
				LocalDate	startDate 	= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate		= result.getObject("END_DATE",LocalDate.class);
				int 	statusId	= result.getInt("STATUS_ID");
				int		projectId	= result.getInt("PROJECT_ID");
				int		userId		= result.getInt("USER_ID");
				return new Task(id,taskName,startDate,endDate,statusId,projectId,userId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Task> getAllTasks(){
		ArrayList<Task> taskList=null;
		String query = "SELECT * FROM CRM.TASK";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			taskList = new ArrayList<Task>();
			while(result.next())
			{
				int		id			= result.getInt("ID");
				String 	taskName 	= result.getString("NAME");
				LocalDate	startDate 	= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate		= result.getObject("END_DATE",LocalDate.class);
				int 	statusId	= result.getInt("STATUS_ID");
				int		projectId	= result.getInt("PROJECT_ID");
				int		userId		= result.getInt("USER_ID");
				taskList.add(new Task(id,taskName,startDate,endDate,statusId,projectId,userId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskList;
	}
	
	public List<Task> getTasksByUserId(int userId){
		ArrayList<Task> taskList=null;
		String query = "SELECT * FROM CRM.TASK WHERE USER_ID = ?";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			taskList = new ArrayList<Task>();
			while(result.next())
			{
				int		id			= result.getInt("ID");
				String 	taskName 	= result.getString("NAME");
				LocalDate	startDate 	= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate		= result.getObject("END_DATE",LocalDate.class);
				int 	statusId	= result.getInt("STATUS_ID");
				int		projectId	= result.getInt("PROJECT_ID");
				taskList.add(new Task(id,taskName,startDate,endDate,statusId,projectId,userId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskList;
	}
	
	public List<Task> getTasksByStatusId(int statusId){
		ArrayList<Task> taskList=null;
		String query = "SELECT * FROM CRM.TASK WHERE STATUS_ID = ?";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, statusId);
			ResultSet result = statement.executeQuery();
			taskList = new ArrayList<Task>();
			while(result.next())
			{
				int		id			= result.getInt("ID");
				String 	taskName 	= result.getString("NAME");
				LocalDate	startDate 	= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate		= result.getObject("END_DATE",LocalDate.class);
				int		projectId	= result.getInt("PROJECT_ID");
				int		userId		= result.getInt("USER_ID");
				taskList.add(new Task(id,taskName,startDate,endDate,statusId,projectId,userId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskList;
	}
	
	public List<Task> getTasksByProjectId(int projectId){
		ArrayList<Task> taskList=null;
		String query = "SELECT\r\n"
				+ "	T.ID,\r\n"
				+ "    T.NAME,\r\n"
				+ "    T.START_DATE,\r\n"
				+ "    T.END_DATE,\r\n"
				+ "    T.STATUS_ID,\r\n"
				+ "    T.PROJECT_ID,\r\n"
				+ "    T.USER_ID,\r\n"
				+ "    U.FULLNAME\r\n"
				+ "FROM CRM.TASK T INNER JOIN CRM.USER U ON T.USER_ID=U.ID WHERE PROJECT_ID = ?;";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, projectId);
			ResultSet result = statement.executeQuery();
			taskList = new ArrayList<Task>();
			while(result.next())
			{
				int		id			= result.getInt("ID");
				String 	taskName 	= result.getString("NAME");
				LocalDate	startDate 	= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate		= result.getObject("END_DATE",LocalDate.class);
				int		userId		= result.getInt("USER_ID");
				int 	statusId	= result.getInt("STATUS_ID");
				String userName		= result.getString("FULLNAME");
				taskList.add(new Task(id,taskName,startDate,endDate,statusId,userId,projectId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskList;
	}
	
	public boolean addTask(Task target)
	{
		String query = "INSERT INTO CRM.TASK(NAME,START_DATE,END_DATE,STATUS_ID,PROJECT_ID,USER_ID) VALUES (?,?,?,?,?,?)";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, target.getTaskName());
			statement.setObject(2, target.getStartDate());
			statement.setObject(3, target.getEndDate());
			statement.setInt(4,target.getStatusId());
			statement.setInt(5, target.getProjectId());
			statement.setInt(6,target.getUserId());
			if(statement.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editTask(int id, Task target) {
		String query = "UPDATE CRM.TASK SET NAME = ?, START_DATE = ?, END_DATE = ?, STATUS_ID = ?, PROJECT_ID = ?, USER_ID = ? WHERE ID = ?";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, target.getTaskName());
			statement.setObject(2, target.getStartDate());
			statement.setObject(3, target.getEndDate());
			statement.setInt(4,target.getStatusId());
			statement.setInt(5, target.getProjectId());
			statement.setInt(6,target.getUserId());
			statement.setInt(7, target.getTaskId());
			if(statement.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeTask(int id) {
		String query = "DELETE FROM CRM.TASK WHERE ID = ?";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			if(statement.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeTaskByProjectId(int id) {
		String query = "DELETE FROM CRM.TASK WHERE PROJECT_ID = ?";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			if(statement.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

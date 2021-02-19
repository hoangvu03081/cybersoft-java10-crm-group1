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
import com.myclass.entity.Project;

public class ProjectRepository {
	public Project getProjectById(int id) {
		String query="SELECT * FROM CRM.PROJECT WHERE id = ?";
		Connection connection=DbConnection.getConnection();
		Project project=null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result=statement.executeQuery();
			while(result.next())
			{
				String 	projectName=result.getString("NAME");
				LocalDate 	startDate=result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate=result.getObject("END_DATE",LocalDate.class);
				int		leaderId=result.getInt("LEADER_ID");
				project = new Project(id,projectName,startDate,endDate,leaderId);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	}
	
	public List<Project> getProjectByLeaderId(int id) {
		String query="SELECT * FROM CRM.PROJECT WHERE LEADER_ID = ?";
		Connection connection=DbConnection.getConnection();
		ArrayList<Project> projectList = null;
		try {
			projectList = new ArrayList<Project>();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result=statement.executeQuery();
			while(result.next())
			{
				int 	projectId		= result.getInt("ID");
				String 	projectName		= result.getString("NAME");
				LocalDate 	startDate		= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate			= result.getObject("END_DATE",LocalDate.class);
				int		leaderId		= result.getInt("LEADER_ID");
				projectList.add(new Project(projectId,projectName,startDate,endDate,leaderId));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectList;
	}
	
	public List<Project> getProjectByUserLeaderId(int id) {
		String query="SELECT DISTINCT\r\n"
				+ "	P.ID,\r\n"
				+ "    P.NAME,\r\n"
				+ "    P.START_DATE,\r\n"
				+ "    P.END_DATE,\r\n"
				+ "    P.LEADER_ID\r\n"
				+ "FROM CRM.PROJECT P INNER JOIN CRM.TASK T\r\n"
				+ "ON P.ID = T.PROJECT_ID WHERE USER_ID=? OR LEADER_ID=?";
		Connection connection=DbConnection.getConnection();
		ArrayList<Project> projectList = null;
		try {
			projectList = new ArrayList<Project>();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.setInt(2, id);
			ResultSet result=statement.executeQuery();
			while(result.next())
			{
				int 	projectId		= result.getInt("ID");
				String 	projectName		= result.getString("NAME");
				LocalDate 	startDate		= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate			= result.getObject("END_DATE",LocalDate.class);
				int		leaderId		= result.getInt("LEADER_ID");
				projectList.add(new Project(projectId,projectName,startDate,endDate,leaderId));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectList;
	}
	
	public List<Project> getProjectByUserId(int id) {
		String query="SELECT \r\n"
				+ "	P.ID,\r\n"
				+ "    P.NAME,\r\n"
				+ "    P.START_DATE,\r\n"
				+ "    P.END_DATE,\r\n"
				+ "    P.LEADER_ID\r\n"
				+ "FROM CRM.PROJECT P INNER JOIN CRM.TASK T\r\n"
				+ "ON P.ID = T.PROJECT_ID WHERE USER_ID=?";
		Connection connection=DbConnection.getConnection();
		ArrayList<Project> projectList = null;
		try {
			projectList = new ArrayList<Project>();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result=statement.executeQuery();
			while(result.next())
			{
				int 	projectId		= result.getInt("ID");
				String 	projectName		= result.getString("NAME");
				LocalDate 	startDate		= result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate			= result.getObject("END_DATE",LocalDate.class);
				int		leaderId		= result.getInt("LEADER_ID");
				projectList.add(new Project(projectId,projectName,startDate,endDate,leaderId));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectList;
	}
	
	public List<Project> getAllProjects(){
		String query="SELECT * FROM CRM.PROJECT";
		Connection connection=DbConnection.getConnection();
		ArrayList<Project> projectList = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result=statement.executeQuery();
			projectList=new ArrayList<Project>();
			while(result.next())
			{
				int id=result.getInt("ID");
				String 	projectName=result.getString("NAME");
				LocalDate 	startDate=result.getObject("START_DATE",LocalDate.class);
				LocalDate	endDate=result.getObject("END_DATE",LocalDate.class);
				int		leaderId=result.getInt("LEADER_ID");
				projectList.add(new Project(id,projectName,startDate,endDate,leaderId));
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projectList;
	}
	
	public boolean removeProject(int id)
	{
		String query="DELETE FROM CRM.PROJECT WHERE ID=?";
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
	
	public boolean addProject(Project target) {
		String query="INSERT INTO CRM.PROJECT(NAME,START_DATE,END_DATE,LEADER_ID) VALUES (?,?,?,?)";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, target.getProjectName());
			statement.setObject(2, target.getStartDate());
			statement.setObject(3, target.getEndDate());
			statement.setInt(4, target.getLeaderId());
			if(statement.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean editProject(int id,Project target) {
		String query="UPDATE CRM.PROJECT SET NAME=?,START_DATE=?,END_DATE=?,LEADER_ID=? WHERE ID=?";
		Connection connection = DbConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, target.getProjectName());
			statement.setObject(2, target.getStartDate());
			statement.setObject(3, target.getEndDate());
			statement.setInt(4, target.getLeaderId());
			statement.setInt(5, id);
			if(statement.executeUpdate()>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
